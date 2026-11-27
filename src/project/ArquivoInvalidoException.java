package project;

/**
 * Exceção lançada quando o arquivo de configuração do mapa da casa possui
 * formatação incorreta, dados ausentes ou inconsistências.
 */
public class ArquivoInvalidoException extends Exception{
    
    /**
     * Cria uma nova exceção com a descrição do erro encontrado no arquivo.
     *
     * @param mensagem mensagem explicando a inconsistência detectada.
     */
    public ArquivoInvalidoException(String mensagem){
        super(mensagem);
    }
}
