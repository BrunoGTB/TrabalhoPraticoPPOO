package gui;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import project.ArquivoInvalidoException;
import project.Casa;
import project.Jogo;
import project.LeitorArquivo;
/**
 *
 * @author Luiz Merschmann
 */
public class Principal {

    public static void main(String[] args) {
        try{
            LeitorArquivo leitor = new LeitorArquivo("src/casa.txt");
            Casa casa = new Casa(leitor);
            Mapa mapa = new Mapa();
            new ConstrutorMapa(mapa).montarMapa(leitor);
            Map<String, Posicao> posicoes = lerPosicoesAmbientes(leitor);
            Posicao inicio = posicoes.get(casa.getAmbienteInicial().getNome());

            JanelaJogo janela = new JanelaJogo(mapa, posicoes, inicio);
            Jogo jogo = new Jogo(casa, janela);
            janela.conectar(jogo);
            jogo.iniciar();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo da casa:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }catch(ArquivoInvalidoException e){
            JOptionPane.showMessageDialog(null, "Arquivo da casa inválido:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
