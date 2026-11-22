package gui;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Letra extends Desenhavel{
    // CONSTRUTOR:
    public Letra(Posicao posicao, char letra) {
        super(posicao, charParaImagem(letra));
    }

    // MÉTODO:
    public static Image charParaImagem(char c) {
        int largura = 40;
        int altura = 40;

        BufferedImage imagem = new BufferedImage(
            largura,
            altura,
            BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = imagem.createGraphics();

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);

        g.drawString(String.valueOf(c), 5, 30);

        g.dispose();

        return imagem;
    }
}