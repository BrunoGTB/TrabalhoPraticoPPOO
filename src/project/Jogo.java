package project;

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
       
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
    }

    private void criarAmbientes()
    {
        Ambiente fora, anfiteatro, cantina, laboratorio, escritorio;

        fora = new Ambiente("do lado de fora da entrada principal de uma universidade");
        anfiteatro = new Ambiente("no anfiteatro");
        cantina = new Ambiente("na cantina do campus");
        laboratorio = new Ambiente("no laboratorio de computacao");
        escritorio = new Ambiente("na sala de administracao dos computadores");

        fora.ajustarSaida("leste", anfiteatro);
        fora.ajustarSaida("sul", laboratorio);
        fora.ajustarSaida("oeste", cantina);
        anfiteatro.ajustarSaida("oeste", fora);
        cantina.ajustarSaida("leste", fora);
        laboratorio.ajustarSaida("norte", fora);
        laboratorio.ajustarSaida("leste", escritorio);
        escritorio.ajustarSaida("oeste", laboratorio);

        ambienteAtual = fora;
    }

    public void jogar() 
    {            
        imprimirBoasVindas();
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
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
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println(analisador.mostrarComandos());
    }

    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            imprimirLocalizacao();
        }
    }

    private void imprimirLocalizacao(){
        System.out.println(ambienteAtual.getDescricaoLonga());
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