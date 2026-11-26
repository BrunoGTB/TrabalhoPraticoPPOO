package gui;

import java.awt.*;
import javax.swing.ImageIcon;

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

    /*
        - No código Original o mapa descobria se podia passar com parede[x][y] != null
        - O método que criamos abaixo já faz com que qualquer Desenhavel responda se pode passar ou não
        - Por padrão não bloqueia; Parede sobrescreve.
    */
    public boolean bloqueiaPassagem() {
        return false;
    }

    // Elimina duplicação no carregamento de imagens.
    public static Image carregarImagem(String caminho){
        return new ImageIcon(Desenhavel.class.getResource(caminho)).getImage();
    }
}