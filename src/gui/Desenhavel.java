package gui;

import java.awt.*;
import javax.swing.ImageIcon;


/**
 * Elemento que pode ser desenhado no mapa, com uma posição e uma imagem.
 * Superclasse de Parede, Letra e Jogador.
 */
public abstract class Desenhavel {
    // ATRIBUTOS:
    private Posicao posicaoOrigem;
    private final Image imagem;

    //CONSTRUTORES:
    /**
     * Cria um elemento desenhável no mapa.
     * @param localizacao posição inicial no mapa (não pode ser nula)
     * @param imagem imagem usada para desenhar o elemento
     * @throws IllegalArgumentException se a localização for nula
     */
    public Desenhavel(Posicao localizacao, Image imagem){
        if (localizacao == null) {
            throw new IllegalArgumentException("A posição não pode ser nula.");
        }
        posicaoOrigem = localizacao;
        this.imagem = imagem;
    }

    // MÉTODOS:
    public Posicao getPosicaoOrigem(){
        return posicaoOrigem;
    }

    public Image getImagem(){
        return imagem;
    }

    protected void setPosicaoOrigem(Posicao localizacao){
        posicaoOrigem = localizacao;
    }

    /**
     * Informa se o elemento impede o jogador de passar pela sua posição.
     * Por padrão não bloqueia; Parede sobrescreve este método.
     * @return true se bloqueia a passagem
     */
    public boolean bloqueiaPassagem() {
        return false;
    }

    /**
     * Carrega uma imagem a partir do caminho de um recurso do projeto.
     * @param caminho caminho da imagem, por exemplo "/Imagens/tijolo.png"
     */
    public static Image carregarImagem(String caminho){
        return new ImageIcon(Desenhavel.class.getResource(caminho)).getImage();
    }
}