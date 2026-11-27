package project;

/**
 * Mantém o dicionário de palavras de comando válidas do jogo.
 */
public class PalavrasComando
{
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "olhar", "explodir", "chave", "informacoes"
    };

    /**
     * Verifica se uma determinada string corresponde a um comando válido do jogo.
     *
     * @param umaString a palavra a ser verificada.
     * @return true se a palavra estiver na lista de comandos válidos.
     */
    public boolean ehComando(String umaString)
    {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(umaString))
                return true;
        }
        return false;
    }

    /**
     * Concatena todos os comandos válidos em uma única string para exibição.
     *
     * @return String contendo os comandos disponíveis.
     */
    public String mostrarComandos(){
        String comandos = "";
        for(String comando : comandosValidos){
            comandos += comando + " ";
        }
        return comandos;
    }
}