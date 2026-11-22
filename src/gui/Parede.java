package gui;

import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Parede extends Desenhavel{
    // CONSTRUTOR:
    public Parede(Posicao posicao) {
        super(
            posicao, 
            new ImageIcon(Parede.class.getResource("/Imagens/tijolo.png")).getImage()
        );
    }
}