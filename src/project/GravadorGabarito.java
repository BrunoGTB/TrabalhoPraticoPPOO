package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Responsável por salvar em um arquivo de texto o gabarito da partida.
 */
public class GravadorGabarito {
    private String caminhoArquivo;

    /**
     * Cria um gravador configurado para escrever no caminho de arquivo especificado.
     *
     * @param caminhoArquivo caminho do arquivo onde o gabarito será salvo.
     */
    public GravadorGabarito(String caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

    /**
     * Grava as linhas do gabarito no arquivo de destino.
     *
     * @param linhas lista de informações sobre a localização dos itens a serem gravadas.
     * @throws IOException se ocorrer um erro de escrita ou acesso ao arquivo.
     */
    public void gravar(List<String> linhas) throws IOException{
        try(PrintWriter escritor = new PrintWriter(new OutputStreamWriter(
            new FileOutputStream(caminhoArquivo), StandardCharsets.UTF_8))){
                escritor.println("=== GABARITO DO JOGO ===");
                for(String linha : linhas){
                    escritor.println(linha);
                }
            }
    }
}