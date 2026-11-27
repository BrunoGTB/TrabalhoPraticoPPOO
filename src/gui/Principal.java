package gui;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import project.ArquivoInvalidoException;
import project.LeitorArquivo;
/**
 *
 * @author Luiz Merschmann
 */
public class Principal {

    public static void main(String[] args) {
        try{
            LeitorArquivo leitor = new LeitorArquivo("src/casa.txt");
            Map<String, Posicao> referencias = lerPosicoesAmbientes(leitor);
            Mapa mapa = new Mapa();
            new ConstrutorMapa(mapa).montarMapa(leitor);
            Jogador jogador = new Jogador(referencias.get("Sala de TV"));
            JanelaSimulacao janela = new JanelaSimulacao(mapa);
            Simulacao sim = new Simulacao(jogador, new BuscaAEstrela(mapa), janela);
            
            for(String origem: referencias.keySet()){
                for(String destino: referencias.keySet()){
                    if(!origem.equals(destino)){
                        System.out.println("Origem: " + origem + "\tDestino: " + destino);
                        System.out.println("Procurando o caminho ...");
                        sim.executarSimulacao(referencias.get(origem),referencias.get(destino));
                    }
                }    
            }
            System.out.println("Simulação concluída.");
        }catch(IOException e){
            System.out.println("Não foi possível ler o arquivo da casa: " + e.getMessage());
        }catch(ArquivoInvalidoException e){
            System.out.println("Arquivo da casa inválido: " + e.getMessage());
        }

    }

    private static Map<String, Posicao> lerPosicoesAmbientes(LeitorArquivo leitor) throws ArquivoInvalidoException{
        Map<String, Posicao> posicoes = new HashMap<String, Posicao>();
        for(String[] registro : leitor.getRegistros("AMBIENTE")){
            LeitorArquivo.exigirCampos(registro, 5);
            int x = LeitorArquivo.converterInteiro(registro, 3);
            int y = LeitorArquivo.converterInteiro(registro, 4);
            posicoes.put(registro[1], new Posicao(x, y));
        }
        return posicoes;
    }
}
