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
    }

    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public void adicionarItem(Desenhavel d){
        itens.add(d);
    }
    
    public void removerItem(Desenhavel d){
        itens.remove(d);
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

    public boolean podePassar(int x, int y) {
        return estaDentro(x, y) && !bloqueado(x, y);
    }

    private boolean bloqueado(int x, int y) {
        for (Desenhavel d : itens) {
            Posicao p = d.getPosicaoOrigem();
            if(d.bloqueiaPassagem() && p.getX() == x && p.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean estaDentro(int x, int y) {
        return x >= 0 && x < largura
            && y >= 0 && y < altura;
    }
}
