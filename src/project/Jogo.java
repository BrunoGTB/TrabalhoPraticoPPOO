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
       
    /**
     * Inicializa uma nova partida, configurando o mapa, sorteando o estado inicial
     * das portas e definindo aleatoriamente o número de tentativas do caçador (entre 10 e 30).
     */
    public Jogo() 
    {
        analisador = new Analisador();
        randomizer = new Random(); 
        casa = new Casa();
        casa.redefinirPortas(randomizer);
        casa.distribuirItens(randomizer);

        GravadorGabarito gravador = new GravadorGabarito("gabarito.txt");
        try{
            gravador.gravar(casa.gerarGabarito());
        }catch(IOException e){
            System.out.println("Não foi possível gravar o gabarito: " + e.getMessage());
        }

        int tentativas = randomizer.nextInt(21) + 10;
        cacador = new Cacador(casa.getAmbienteInicial(), tentativas);
    }

    /**
     * Inicia e mantém o loop principal do jogo até que o jogador decida sair
     * ou esgote suas tentativas disponíveis.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();
        recolherItens(casa.getAmbienteInicial());
                
        boolean terminado = false;
        while (!terminado && cacador.temTentativas()) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }

        if(!cacador.temTentativas()){
            System.out.println("Suas tentativas acabaram!");
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Exibe a mensagem inicial, as instruções básicas e a localização de inicio.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.print("Bem-vindo ao World of Zuul! ");
        System.out.println("Esse é um jogo de aventura em que você tem que achar o tesouro enterrado nesta casa mal-assombrada.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        imprimirTentativas();
        System.out.println();

        imprimirLocalizacao();
    }

    /**
     * Interpreta o comando fornecido pelo jogador e executa a ação correspondente.
     *
     * @param comando Objeto Comando contendo a ação e o argumento digitados.
     * @return true se o jogador escolheu encerrar o jogo; false para continuar a partida.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
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
     * Exibe o contexto do jogo, os comandos válidos e as tentativas restantes.
     */
    private void imprimirAjuda() 
    {
        System.out.println("Você entrou nessa casa para procurar o tesouro enterrado, mas os fantasmas presentes não querem que você o encontre!");
        System.out.println("Eles irão emperrar as portas de tempos em tempos, e você tem tentativas limitadas para encontrar o tesouro!");
        System.out.println("Se achar que encontrou a sala que contém o tesouro, exploda o chão dela! Mas cuidado, você só tem uma carga!");
        System.out.println("Você pode encontrar itens para auxiliar sua busca. Se encontrar uma chave mestra, pode usá-la digitando 'chave Cozinha', por exemplo.");
        System.out.println("Isso garante que nenhum fantasma vai emperrar a sua porta, e você não usará tentativas. Mas cuidado! A chave tem durabilidade, então a use com sabedoria.");
        System.out.println("Você também pode encontrar dicas que vão te dizer onde o tesouro não está.");
        System.out.println("Você pode ver suas tentativas restantes, a durabilidade da sua chave e as dicas que já achou digitando 'informacoes'");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        System.out.println(analisador.mostrarComandos());
        
    }

    private void imprimirInformacoes(){
        imprimirDicas();
        imprimirTentativas();
        imprimirDurabilidade();
    }

    /**
     * Mostra no console a quantidade de tentativas que o caçador ainda possui.
     */
    private void imprimirTentativas(){
        System.out.println("Você tem " + cacador.getTentativas() + " tentativas restantes.");
    }

    private void imprimirDicas(){
        List<Dica> dicas = cacador.getDicas();
        if(dicas.isEmpty()){
            System.out.println("Você ainda não encontrou nenhuma dica.");
            return;
        }
        System.out.println("Suas dicas encontradas são: ");
        for(Dica dica : dicas){
            System.out.println("- " + dica.getTexto());
        }
    }

    private void imprimirDurabilidade(){
        if(!cacador.temChave()){
            System.out.println("Você ainda não encontrou a chave!");
        }else{
            System.out.println("Usos restantes da chave mestra: " + cacador.getDurabilidadeChave());
        }
    }
    /**
     * Tenta mover o caçador para o ambiente especificado na segunda palavra do comando.
     * Consome uma tentativa caso exista passagem e atualiza o estado das portas da casa.
     *
     * @param comando Comando "ir" acompanhado do nome do ambiente de destino.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            System.out.println("Ir pra onde?");
            return;
        }

        Ambiente atual = cacador.getAmbienteAtual();
        Porta porta = atual.getPorta(comando.getSegundaPalavra());

        if(porta == null){
            System.out.println("Não há passagem!");
            return;
        }
        cacador.gastarTentativa();
        if(porta.estaEmperrada()){
            System.out.println("A porta está emperrada!");
        }else{
            entrarEm(porta.getOutroLado(atual));
        }
        casa.redefinirPortas(randomizer);
    }

    private void entrarEm(Ambiente destino){
        cacador.moverPara(destino);
        imprimirLocalizacao();
        recolherItens(destino);
    }

    private void recolherItens(Ambiente atual){
        for(String mensagem : atual.entregarItens(cacador)){
            System.out.println(mensagem);
        }
    }

    private void usarChave(Comando comando){
        if(!cacador.podeUsarChave()){
            System.out.println("Você não tem uma chave mestra utilizável.");
            return;
        }

        if(!comando.temSegundaPalavra()){
            System.out.println("Usar a chave em qual porta?");
            return;
        }

        Ambiente atual = cacador.getAmbienteAtual();
        Porta porta = atual.getPorta(comando.getSegundaPalavra());
        if(porta == null){
            System.out.println("Não há passagem!");
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
        System.out.println(cacador.getAmbienteAtual().getDescricaoLonga());
    }

    /**
     * Executa o comando "olhar", exibindo a descrição do ambiente atual.
     */
    private void olhar(){
        imprimirLocalizacao();
    }

    private void explodir(){
        Ambiente atual = cacador.getAmbienteAtual();
        System.out.println("BOOM! Você explodiu o chão " + atual.getDescricao() + "...");
        if(atual.temTesouro()){
            System.out.println("Você encontrou o tesouro! Você venceu!");
        }else{
            System.out.println("Não havia nada aqui, e sua carga acabou. Você perdeu!");
        }
    }

    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;
        }
    }
}