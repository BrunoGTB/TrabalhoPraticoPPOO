package gui;

/**
 * Representa uma parede do mapa, que bloqueia a passagem do jogador.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Parede extends Desenhavel{
    // CONSTRUTOR:
    public Parede(Posicao posicao) {
        super(
            posicao, 
            carregarImagem("/Imagens/tijolo.png")
        );
    }

    // MÉTODO:
    @Override
    public boolean bloqueiaPassagem() {
        return true;
    }
}