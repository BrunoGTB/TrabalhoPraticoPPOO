package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PainelMapa extends JPanel{
    private static final int TAMANHO_CELULA = 25;
    private final Mapa mapa;
    private final Jogador jogador;
    
    public PainelMapa(Mapa mapa, Jogador jogador){
        this.mapa = mapa;
        this.jogador = jogador;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(mapa.getLargura() * TAMANHO_CELULA, mapa.getAltura() * TAMANHO_CELULA));
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int largCelula = getWidth() / mapa.getLargura();
        int altCelula = getHeight() / mapa.getAltura();

        g.setColor(Color.LIGHT_GRAY);
        for(int x = 0; x <= mapa.getLargura(); x++){
            g.drawLine(x * largCelula, 0, x * largCelula, mapa.getAltura() * altCelula);
        }
        for(int y = 0; y <= mapa.getAltura(); y++){
            g.drawLine(0, y * altCelula, mapa.getLargura() * largCelula, y * altCelula);
        }

        for(Desenhavel d : mapa.getDesenhaveis()){
            desenhar(g, d, largCelula, altCelula);
        }
        desenhar(g, jogador, largCelula, altCelula);
    }

    private void desenhar(Graphics g, Desenhavel d, int largCelula, int altCelula){
        Posicao p = d.getPosicaoOrigem();
        g.drawImage(d.getImagem(), p.getX() * largCelula + 1, p.getY() * altCelula + 1, largCelula - 1, altCelula - 1, this);
    }
}
