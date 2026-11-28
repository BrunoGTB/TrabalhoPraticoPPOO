package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

public class AnimadorJogador implements ActionListener{
    private static final int INTERVALO_MS = 100;
    private final Jogador jogador;
    private final BuscaCaminho buscaCaminho;
    private final PainelMapa painel;
    private final JanelaJogo janela;
    private final Timer timer;
    private List<Posicao> caminho;
    private int indice;

    public AnimadorJogador(Jogador jogador, BuscaCaminho buscaCaminho, PainelMapa painel, JanelaJogo janela){
        this.jogador = jogador;
        this.buscaCaminho = buscaCaminho;
        this.painel = painel;
        this.janela = janela;
        timer = new Timer(INTERVALO_MS, this);
    }

    public void animar(Posicao destino){
        caminho = buscaCaminho.encontrarCaminho(jogador.getPosicaoOrigem(), destino);
        indice = 0;
        if(caminho.isEmpty()){
            jogador.moverPara(destino);
            painel.repaint();
            janela.animacaoConcluida();
            return;
        }
        timer.start();
    }

    @Override 
    public void actionPerformed(ActionEvent e){
        if(indice >= caminho.size()){
            timer.stop();
            janela.animacaoConcluida();
            return;
        }
        jogador.moverPara(caminho.get(indice));
        indice++;
        painel.repaint();
    }
}
