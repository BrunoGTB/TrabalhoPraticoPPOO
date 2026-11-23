package project;

import java.util.Random;

public class Jogo 
{
    private Analisador analisador;
    private Random randomizer;
    private Cacador cacador;
       
    public Jogo() 
    {
        analisador = new Analisador();
        randomizer = new Random(); 
        int tentativas = randomizer.nextInt(21) + 10;
        cacador = new Cacador(criarAmbientes(), tentativas);
    }

    private Ambiente criarAmbientes()
    {
        Ambiente salaTv, areaServico, salaJantar, cozinha, corredor, wc1, wc2, quarto1, quarto2;

        salaTv      = new Ambiente("na Sala de TV");
        areaServico = new Ambiente("na Área de Serviço");
        salaJantar  = new Ambiente("na Sala de Jantar");
        cozinha     = new Ambiente("na Cozinha");
        corredor    = new Ambiente("no Corredor");
        wc1         = new Ambiente("no WC 1");
        wc2         = new Ambiente("no WC 2");
        quarto1     = new Ambiente("no Quarto 1");
        quarto2     = new Ambiente("no Quarto 2");

        salaTv.ajustarSaida("Sala de Jantar", salaJantar);
        salaJantar.ajustarSaida("Sala de TV", salaTv);
        salaJantar.ajustarSaida("Corredor", corredor);
        corredor.ajustarSaida("Sala de Jantar", salaJantar);
        corredor.ajustarSaida("WC 1", wc1);
        corredor.ajustarSaida("WC 2", wc2);
        corredor.ajustarSaida("Quarto 1", quarto1);
        corredor.ajustarSaida("Quarto 2", quarto2);
        corredor.ajustarSaida("Cozinha", cozinha);
        quarto1.ajustarSaida("Corredor", corredor);
        quarto2.ajustarSaida("Corredor", corredor);
        wc1.ajustarSaida("Corredor", corredor);
        wc2.ajustarSaida("Corredor", corredor);
        cozinha.ajustarSaida("Corredor", corredor);
        cozinha.ajustarSaida("Área de Serviço", areaServico);
        areaServico.ajustarSaida("Cozinha", cozinha);

        return salaTv;
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

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = cacador.getAmbienteAtual().getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            cacador.moverPara(proximoAmbiente);
            cacador.gastarTentativa();
            imprimirLocalizacao();
        }
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