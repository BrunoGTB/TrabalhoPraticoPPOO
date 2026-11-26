package gui;

/**
 * Nó usado pelo A*: guarda uma posição, o nó anterior no caminho,
 * o custo G (desde a origem) e o custo H (estimativa até o destino).
 */
public class No {

    private final Posicao posicao;
    private final No pai;

    private final int custoG;
    private final int custoH;

    public No(Posicao posicao, No pai, int custoG, int custoH) {
        this.posicao = posicao;
        this.pai = pai;
        this.custoG = custoG;
        this.custoH = custoH;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public No getPai() {
        return pai;
    }

    public int getCustoG() {
        return custoG;
    }

    public int getCustoH() {
        return custoH;
    }

    public int getCustoF() {
        return custoG + custoH;
    }
}
