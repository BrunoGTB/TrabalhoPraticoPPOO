package project;

import java.util.Random;

public class Jogo 
{
    private Analisador analisador;
    private Random randomizer;
    private Casa casa;
    private Cacador cacador;
       
    public Jogo() 
    {
        analisador = new Analisador();
        randomizer = new Random(); 
        casa = new Casa();
        casa.redefinirPortas(randomizer);
        int tentativas = randomizer.nextInt(21) + 10;
        cacador = new Cacador(casa.getAmbienteInicial(), tentativas);
    }

    public void jogar() 
    {            
        imprimirBoasVindas();
                
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
        }

        return querSair;
    }

    private void imprimirAjuda() 
    {
        System.out.println("Você entrou nessa casa para procurar o tesouro enterrado, mas os fantasmas presentes não querem que");
        System.out.println("você o encontre! Eles irão emperrar as portas de tempos em tempos, e você tem tentativas limitadas para encontrar o tesouro!");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println(analisador.mostrarComandos());
        imprimirTentativas();
    }

    private void imprimirTentativas(){
        System.out.println("Você tem " + cacador.getTentativas() + " tentativas restantes.");
    }

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
            cacador.moverPara(porta.getOutroLado(atual));
            imprimirLocalizacao();
        }
        casa.redefinirPortas(randomizer);
    }

    private void imprimirLocalizacao(){
        System.out.println(cacador.getAmbienteAtual().getDescricaoLonga());
    }

    private void olhar(){
        imprimirLocalizacao();
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