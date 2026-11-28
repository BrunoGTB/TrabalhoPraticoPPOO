package project;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Classe principal de controle do jogo "World of Zuul".
 * Gerencia o loop da partida, inicializa o mapa e o jogador, e processa os comandos inseridos.
 */
public class Jogo 
{
    private Analisador analisador;
    private Random randomizer;
    private Casa casa;
    private Cacador cacador;
    private OuvinteJogo ouvinte;
    private boolean terminado;
       
    /**
     * Inicializa uma nova partida a partir de uma casa já carregada, sorteia o estado inicial
     * das portas, distribui os itens, grava o gabarito e define aleatoriamente o número
     * de tentativas do caçador (entre 10 e 30).
     *
     * @param casa estrutura do mapa contendo os ambientes e portas do jogo.
     */
    public Jogo(Casa casa, OuvinteJogo ouvinte) 
    {
        analisador = new Analisador();
        randomizer = new Random(); 
        this.casa = casa;
        this.ouvinte = ouvinte;
        casa.redefinirPortas(randomizer);
        casa.distribuirItens(randomizer);
        int tentativas = randomizer.nextInt(21) + 10;
        cacador = new Cacador(casa.getAmbienteInicial(), tentativas);
    }

    public void iniciar(){
        GravadorGabarito gravador = new GravadorGabarito("gabarito.txt");
        try{
            gravador.gravar(casa.gerarGabarito());
        }catch(IOException e){
            mensagem("Não foi possível gravar o gabarito: " + e.getMessage());
        }
        imprimirBoasVindas();
        recolherItens(casa.getAmbienteInicial());
        ouvinte.estadoAtualizado(cacador);
    }

    /**
     * Inicia e mantém o loop principal do jogo até que o jogador decida sair,
     * tente explodir o chão em busca do tesouro ou esgote suas tentativas disponíveis.
     */
    public void executarComando(String linha) {            
        if(terminado) return;
        
        mensagem("> " + linha);
        boolean encerrar = processarComando(analisador.analisar(linha));
        ouvinte.estadoAtualizado(cacador);

        if(!encerrar && !cacador.temTentativas()){
            mensagem("Suas tentativas acabaram! Você perdeu.");
            encerrar = true;
        }
        if(encerrar){
            terminado = true;
            mensagem("Obrigado por jogar. Até mais!");
            ouvinte.jogoEncerrado();
        }
    }

    private void mensagem(String texto){
        ouvinte.exibirMensagem(texto);
    }

    /**
     * Exibe a mensagem inicial, as instruções básicas e a localização de início.
     */
    private void imprimirBoasVindas()
    {
        mensagem("");
        mensagem("Bem-vindo ao World of Zuul! Esse é um jogo de aventura em que você tem que achar o tesouro enterrado nesta casa mal-assombrada.");
        mensagem("Digite 'ajuda' se voce precisar de ajuda.");
        imprimirTentativas();
        mensagem("");
        imprimirLocalizacao();
    }

    /**
     * Interpreta o comando fornecido pelo jogador e executa a ação correspondente.
     *
     * @param comando Objeto Comando contendo a ação e o argumento digitados.
     * @return true se a partida deve ser encerrada; false para continuar jogando.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            mensagem("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }else if( palavraDeComando.equals("olhar")){
            olhar();
        }else if(palavraDeComando.equals("explodir")){
            explodir();
            querSair = true;
        }else if(palavraDeComando.equals("chave")){
            usarChave(comando);
        }else if(palavraDeComando.equals("informacoes")){
            imprimirInformacoes();
        }

        return querSair;
    }

    /**
     * Exibe o contexto do jogo, as regras dos itens e os comandos válidos.
     */
    private void imprimirAjuda() 
    {
        mensagem("Você entrou nessa casa para procurar o tesouro enterrado, mas os fantasmas presentes não querem que você o encontre!");
        mensagem("Eles irão emperrar as portas de tempos em tempos, e você tem tentativas limitadas para encontrar o tesouro!");
        mensagem("Se achar que encontrou a sala que contém o tesouro, exploda o chão dela! Mas cuidado, você só tem uma carga!");
        mensagem("Você pode encontrar itens para auxiliar sua busca. Se encontrar uma chave mestra, pode usá-la digitando 'chave Cozinha', por exemplo.");
        mensagem("Isso garante que nenhum fantasma vai emperrar a sua porta, e você não usará tentativas. Mas cuidado! A chave tem durabilidade, então a use com sabedoria.");
        mensagem("Você também pode encontrar dicas que vão te dizer onde o tesouro não está.");
        mensagem("Você pode ver suas tentativas restantes, a durabilidade da sua chave e as dicas que já achou digitando 'informacoes'");
        mensagem("");
        mensagem("Suas palavras de comando são:");
        mensagem(analisador.mostrarComandos());
    }

    /**
     * Exibe o resumo atual do jogador: dicas coletadas, tentativas restantes e durabilidade da chave.
     */
    private void imprimirInformacoes(){
        imprimirDicas();
        imprimirTentativas();
        imprimirDurabilidade();
    }

    /**
     * Mostra no console a quantidade de tentativas que o caçador ainda possui.
     */
    private void imprimirTentativas(){
        mensagem("Você tem " + cacador.getTentativas() + " tentativas restantes.");
    }

    /**
     * Lista no console todas as dicas que o caçador já encontrou até o momento.
     */
    private void imprimirDicas(){
        List<Dica> dicas = cacador.getDicas();
        if(dicas.isEmpty()){
            mensagem("Você ainda não encontrou nenhuma dica.");
            return;
        }
        mensagem("Suas dicas encontradas são: ");
        for(Dica dica : dicas){
            mensagem("- " + dica.getTexto());
        }
    }

    /**
     * Informa se o jogador possui a chave mestra e quantos usos ela ainda tem.
     */
    private void imprimirDurabilidade(){
        if(!cacador.temChave()){
            mensagem("Você ainda não encontrou a chave!");
        }else{
            mensagem("Usos restantes da chave mestra: " + cacador.getDurabilidadeChave());
        }
    }

    /**
     * Tenta mover o caçador para o ambiente especificado na segunda palavra do comando.
     * Consome uma tentativa caso exista passagem e atualiza o estado das portas da casa.
     *
     * @param comando comando "ir" acompanhado do nome do ambiente de destino.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            mensagem("Ir pra onde?");
            return;
        }

        Ambiente atual = cacador.getAmbienteAtual();
        Porta porta = atual.getPorta(comando.getSegundaPalavra());

        if(porta == null){
            mensagem("Não há passagem!");
            return;
        }
        cacador.gastarTentativa();
        if(porta.estaEmperrada()){
            mensagem("A porta está emperrada!");
        }else{
            entrarEm(porta.getOutroLado(atual));
        }
        casa.redefinirPortas(randomizer);
    }

    /**
     * Move o caçador para o ambiente de destino, exibe sua descrição e recolhe os itens presentes.
     *
     * @param destino ambiente em que o caçador está entrando.
     */
    private void entrarEm(Ambiente destino){
        Ambiente origem = cacador.getAmbienteAtual();
        cacador.moverPara(destino);
        ouvinte.cacadorMoveu(origem, destino);
        imprimirLocalizacao();
        recolherItens(destino);
    }

    /**
     * Coleta e exibe as mensagens de todos os itens no ambiente atual.
     *
     * @param atual ambiente onde os itens serão verificados e entregues ao caçador.
     */
    private void recolherItens(Ambiente atual){
        for(String mensagem : atual.entregarItens(cacador)){
            mensagem(mensagem);
        }
    }

    /**
     * Utiliza a chave mestra para atravessar uma porta sem gastar tentativas e ignorando
     * se ela está emperrada, consumindo uma unidade de durabilidade da chave.
     *
     * @param comando Comando "chave" acompanhado do nome do ambiente de destino.
     */
    private void usarChave(Comando comando){
        if(!cacador.podeUsarChave()){
            mensagem("Você não tem uma chave mestra utilizável.");
            return;
        }

        if(!comando.temSegundaPalavra()){
            mensagem("Usar a chave em qual porta?");
            return;
        }

        Ambiente atual = cacador.getAmbienteAtual();
        Porta porta = atual.getPorta(comando.getSegundaPalavra());
        if(porta == null){
            mensagem("Não há passagem!");
            return;
        }
        cacador.usarChave();
        entrarEm(porta.getOutroLado(atual));
        casa.redefinirPortas(randomizer);
    }

    /**
     * Exibe a descrição e as saídas do ambiente em que o caçador está.
     */
    private void imprimirLocalizacao(){
        mensagem(cacador.getAmbienteAtual().getDescricaoLonga());
    }

    /**
     * Executa o comando "olhar", exibindo a descrição do ambiente atual.
     */
    private void olhar(){
        imprimirLocalizacao();
    }

    /**
     * Detona a carga explosiva no ambiente atual para tentar revelar o tesouro,
     * encerrando o jogo com vitória ou derrota.
     */
    private void explodir(){
        Ambiente atual = cacador.getAmbienteAtual();
        mensagem("BOOM! Você explodiu o chão " + atual.getDescricao() + "...");
        if(atual.temTesouro()){
            mensagem("Você encontrou o tesouro! Você venceu!");
        }else{
            mensagem("Não havia nada aqui, e sua carga acabou. Você perdeu!");
        }
    }

    /**
     * Valida o comando "sair", garantindo que não haja argumentos extras antes de encerrar.
     *
     * @param comando Comando "sair" digitado pelo jogador.
     * @return true se o comando estiver correto para sair, false caso tenha segunda palavra.
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            mensagem("Sair o que?");
            return false;
        }
        else {
            return true;
        }
    }
}