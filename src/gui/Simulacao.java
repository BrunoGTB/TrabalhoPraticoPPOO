package gui;
import java.util.List;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private final Jogador jogador;
    private final JanelaSimulacao janelaSimulacao;
    private final BuscaCaminho buscaCaminho;
    
    /**
    * Cria a simulação com os objetos que ela vai usar.
    * @param jogador jogador que se movimenta no mapa
    * @param buscaCaminho algoritmo usado para encontrar o caminho
    * @param janelaSimulacao janela onde o mapa é desenhado
    */
    public Simulacao(Jogador jogador, BuscaCaminho buscaCaminho, JanelaSimulacao janelaSimulacao) { 
        this.jogador = jogador;
        this.buscaCaminho = buscaCaminho;
        this.janelaSimulacao = janelaSimulacao;
    }
    
        public void executarSimulacao(Posicao origem, Posicao destino){
        jogador.moverPara(origem);
        janelaSimulacao.atualizar(jogador);
        List<Posicao> caminho = buscaCaminho.encontrarCaminho(origem, destino);
        if (!caminho.isEmpty()) {
            for (Posicao posicao : caminho) {
                executarUmPasso(posicao);
                esperar(100);
            }
        }else{
            System.out.println("Não foi possível encontrar um caminho para o destino.");
        }
        System.out.println("Movimentação concluída.");
    }

    private void executarUmPasso(Posicao proximaLocalizacao) {
        jogador.moverPara(proximaLocalizacao);
        janelaSimulacao.atualizar(jogador);
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
