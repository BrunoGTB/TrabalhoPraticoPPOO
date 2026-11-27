package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GravadorGabarito {
    private String caminhoArquivo;

    public GravadorGabarito(String caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

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
