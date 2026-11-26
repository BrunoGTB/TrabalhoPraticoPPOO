package project;

/**
 * Armazena um comando digitado pelo jogador, dividido em uma palavra de ação principal(ir, sair, ajuda, olhar)
 * e uma segunda palavra opcional.
 */
public class Comando
{
    private String palavraDeComando;
    private String segundaPalavra;

    /**
     * Cria um objeto de comando a partir das palavras extraídas da entrada do usuário.
     *
     * @param primeiraPalavra a palavra de ação principal.
     * @param segundaPalavra a segunda palavra como o destino.
     */
    public Comando(String primeiraPalavra, String segundaPalavra)
    {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    public String getPalavraDeComando()
    {
        return palavraDeComando;
    }

    public String getSegundaPalavra()
    {
        return segundaPalavra;
    }

    /**
     * Verifica se o comando não foi reconhecido pelo sistema.
     *
     * @return true se a palavra de comando for null.
     */
    public boolean ehDesconhecido()
    {
        return (palavraDeComando == null);
    }

    /**
     * Verifica se o comando possui uma segunda palavra.
     *
     * @return true se houver segunda palavra definida, false caso contrário.
     */
    public boolean temSegundaPalavra()
    {
        return (segundaPalavra != null);
    }
}