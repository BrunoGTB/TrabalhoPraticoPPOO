package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por ler e interpretar arquivos de configuração delimitados por ponto e vírgula,
 * ignorando linhas em branco e comentários iniciados por '#'.
 */
public class LeitorArquivo {
    private List<String[]> registros;

    /**
     * Lê o arquivo especificado e armazena suas linhas válidas divididas em campos.
     *
     * @param caminho caminho do arquivo de configuração a ser lido.
     * @throws IOException se ocorrer falha na abertura ou leitura do arquivo.
     */
    public LeitorArquivo(String caminho) throws IOException{
        registros = new ArrayList<String[]>();
        try(BufferedReader arq = new BufferedReader(new FileReader(caminho, StandardCharsets.UTF_8))){
            String linha = arq.readLine();
            while(linha != null){
                linha = linha.trim();
                if(!linha.isEmpty() && !linha.startsWith("#")){
                    String[] campos = linha.split(";");
                    for(int i = 0; i < campos.length; i++){
                        campos[i] = campos[i].trim();
                    }
                    registros.add(campos);
                }
                linha = arq.readLine();
            }
        }
    }

    /**
     * Filtra e retorna todos os registros cujo primeiro campo corresponde ao tipo informado.
     *
     * @param tipo identificador do tipo de registro.
     * @return Lista de vetores de strings contendo os campos dos registros encontrados.
     */
    public List<String[]> getRegistros(String tipo){
        List<String[]> resultado = new ArrayList<String[]>();
        for(String[] registro : registros){
            if(registro[0].equals(tipo)){
                resultado.add(registro);
            }
        }
        return resultado;
    }

    /**
     * Valida se um registro possui a quantidade mínima de campos esperada.
     *
     * @param registro vetor de campos extraídos de uma linha do arquivo.
     * @param quantidade número mínimo de campos exigidos.
     * @throws ArquivoInvalidoException se o registro tiver menos campos que o exigido.
     */
    public static void exigirCampos(String[] registro, int quantidade) throws ArquivoInvalidoException{
        if(registro.length < quantidade){
            throw new ArquivoInvalidoException("Faltam campos na linha: " + String.join(";", registro));
        }
    }

    /**
     * Converte um campo específico de um registro para número inteiro.
     *
     * @param registro vetor de campos do registro.
     * @param indice posição do campo que deve ser convertido.
     * @return Valor inteiro convertido.
     * @throws ArquivoInvalidoException se o texto no índice informado não for um número válido.
     */
    public static int converterInteiro(String[] registro, int indice) throws ArquivoInvalidoException{
        try{
            return Integer.parseInt(registro[indice]);
        }catch(NumberFormatException e){
            throw new ArquivoInvalidoException("Número inválido '" + registro[indice]
                + "' na linha: " + String.join(";", registro));
        }
    }
}