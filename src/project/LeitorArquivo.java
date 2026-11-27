package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {
    private List<String[]> registros;

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

    public List<String[]> getRegistros(String tipo){
        List<String[]> resultado = new ArrayList<String[]>();
        for(String[] registro : registros){
            if(registro[0].equals(tipo)){
                resultado.add(registro);
            }
        }
        return resultado;
    }

    public static void exigirCampos(String[] registro, int quantidade) throws ArquivoInvalidoException{
        if(registro.length < quantidade){
            throw new ArquivoInvalidoException("Faltam campos na linha: " + String.join(";", registro));
        }
    }

    public static int converterInteiro(String[] registro, int indice) throws ArquivoInvalidoException{
        try{
            return Integer.parseInt(registro[indice]);
        }catch(NumberFormatException e){
            throw new ArquivoInvalidoException("Número inválido '" + registro[indice]
                + "' na linha: " + String.join(";", registro));
        }
    }
}
