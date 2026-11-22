package gui;
import java.util.List;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private final Jogador jogador;
    private final JanelaSimulacao janelaSimulacao;
    private final Mapa mapa;
    private final BuscaCaminho buscaCaminho;
    
    public Simulacao(Mapa mapa, Jogador jogador, BuscaCaminho buscaCaminho) { 
        this.mapa = mapa;
        this.jogador = jogador;
        this.buscaCaminho = buscaCaminho;
        this.janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(Posicao origem, Posicao destino){
        jogador.setPosicaoOrigem(origem);//Define a posicao origem do jogador
        jogador.setPosicaoDestino(destino);//Define a posicao destino do jogador
        mapa.adicionarItem(jogador);//Inicializando o mapa com o jogador
        janelaSimulacao.executarAcao();
        List<Posicao> caminho = buscaCaminho.encontrarCaminho(jogador.getPosicaoOrigem(), jogador.getPosicaoDestino());
        if (!caminho.isEmpty()) {
            for (Posicao posicao : caminho) {
                executarUmPasso(posicao);
                esperar(100);
            }
            mapa.removerItem(jogador);
        }else{
            System.out.println("Não foi possível encontrar um caminho para o destino.");
        }
        System.out.println("Movimentação concluída.");
    }

    private void executarUmPasso(Posicao proximaLocalizacao) {
        mapa.removerItem(jogador);
        jogador.executarAcao(proximaLocalizacao);
        mapa.adicionarItem(jogador);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
