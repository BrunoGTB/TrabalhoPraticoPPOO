package gui;

/**
 * Representa o jogador que se movimenta pelo mapa.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Jogador extends Desenhavel{

    /**
     * Cria o jogador em uma posição do mapa.
     * @param localizacao posição inicial do jogador
     */
    public Jogador(Posicao localizacao) {
        super(localizacao, carregarImagem("/Imagens/pessoa.png"));
    }

    /**
     * Move o jogador para uma nova posição do mapa.
     * @param novaPosicao posição para onde o jogador vai
     */
    public void moverPara(Posicao novaPosicao) {
        setPosicaoOrigem(novaPosicao);
    }
}