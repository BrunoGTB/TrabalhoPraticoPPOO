package gui;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Desenhavel {
    // ATRIBUTOS:
    private Posicao posicaoOrigem;
    private Image imagem;

    //CONSTRUTORES:
    public Desenhavel(Posicao localizacao, Image imagem){
        posicaoOrigem = localizacao;
        this.imagem = imagem;
    }

    public Desenhavel(Image imagem){
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

    // Elimina duplicação no carregamento de imagens.
    public static Image carregarImagem(String caminho){
        return new ImageIcon(Desenhavel.class.getResource(caminho)).getImage();
    }
}