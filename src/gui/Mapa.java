package gui;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Mapa {
    private final List<Desenhavel> itens = new ArrayList<Desenhavel>();
    private final int largura;
    private final int altura;
    private final boolean[][] bloqueados;
    
    private static final int LARGURA_PADRAO = 20;
    private static final int ALTURA_PADRAO = 17;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.bloqueados = new boolean[largura][altura];
    }

    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    /**
    * Adiciona um item ao mapa. Se o item bloqueia a passagem, a casa fica bloqueada.
    * @param d item a ser adicionado
    * @throws IllegalArgumentException se o item estiver fora dos limites do mapa
    */
    public void adicionarItem(Desenhavel d){
        Posicao p = d.getPosicaoOrigem();
        if (!estaDentro(p)) {
            throw new IllegalArgumentException("Item fora do mapa: " + p);
        }
        itens.add(d);
        if (d.bloqueiaPassagem()) {
            bloqueados[p.getX()][p.getY()] = true;
        }
    }

    public List<Desenhavel> getDesenhaveis() {
        return Collections.unmodifiableList(itens);
    }
    
    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    /**
    * Informa se o jogador pode ocupar uma posição.
    * @param p posição a verificar
    * @return true se a posição está dentro do mapa e não tem parede
    */
    public boolean podePassar(Posicao p) {
        return estaDentro(p) && !bloqueados[p.getX()][p.getY()];
    }

    /**
    * Informa se uma posição está dentro dos limites do mapa.
    */
    public boolean estaDentro(Posicao p) {
        return p.getX() >= 0 && p.getX() < largura
            && p.getY() >= 0 && p.getY() < altura;
    }
}
