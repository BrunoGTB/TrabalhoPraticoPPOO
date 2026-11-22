package gui;

// Responsável por montar o Mapa do jogo (paredes e letreiros) em um Mapa.
public class ConstrutorMapa {
    private final Mapa mapa;

    public ConstrutorMapa(Mapa mapa){
        this.mapa = mapa;
    }

    public void montarMapa(){
        adicionarParedes();
        adicionarLetreiros();
    }

    private void adicionarParedes(){
        Posicao[] posicoes = {
            new Posicao(0,0), new Posicao(1,0), new Posicao(2,0), new Posicao(3,0),
            new Posicao(4,0), new Posicao(5,0), new Posicao(6,0), new Posicao(7,0),
            new Posicao(8,0), new Posicao(9,0), new Posicao(10,0), new Posicao(11,0),
            new Posicao(12,0), new Posicao(13,0), new Posicao(14,0), new Posicao(15,0),
            new Posicao(16,0), new Posicao(17,0), new Posicao(18,0), new Posicao(19,0),

            new Posicao(0,1), new Posicao(7,1), new Posicao(12,1), new Posicao(19,1),

            new Posicao(0,2), new Posicao(19,2),

            new Posicao(0,3), new Posicao(7,3), new Posicao(12,3), new Posicao(19,3),

            new Posicao(0,4), new Posicao(1,4), new Posicao(2,4), new Posicao(3,4),
            new Posicao(4,4), new Posicao(5,4), new Posicao(6,4), new Posicao(7,4),
            new Posicao(12,4), new Posicao(13,4), new Posicao(14,4), new Posicao(15,4),
            new Posicao(16,4), new Posicao(17,4), new Posicao(18,4), new Posicao(19,4),

            new Posicao(0,5), new Posicao(7,5), new Posicao(12,5), new Posicao(19,5),

            new Posicao(0,6), new Posicao(19,6),

            new Posicao(0,7), new Posicao(7,7), new Posicao(12,7), new Posicao(19,7),

            new Posicao(0,8), new Posicao(1,8), new Posicao(2,8), new Posicao(3,8),
            new Posicao(4,8), new Posicao(5,8), new Posicao(6,8), new Posicao(7,8),
            new Posicao(12,8), new Posicao(13,8), new Posicao(14,8),
            new Posicao(15,8), new Posicao(16,8), new Posicao(17,8),
            new Posicao(18,8), new Posicao(19,8),

            new Posicao(0,9), new Posicao(7,9), new Posicao(12,9), new Posicao(19,9),

            new Posicao(0,10), new Posicao(19,10),

            new Posicao(0,11), new Posicao(7,11), new Posicao(12,11), new Posicao(19,11),

            new Posicao(0,12), new Posicao(1,12),
            new Posicao(7,12), new Posicao(8,12),
            new Posicao(9,12), new Posicao(10,12), new Posicao(11,12),
            new Posicao(12,12), new Posicao(18,12), new Posicao(19,12),

            new Posicao(0,13), new Posicao(9,13), new Posicao(19,13),

            new Posicao(0,14), new Posicao(9,14),new Posicao(19,14),

            new Posicao(0,15), new Posicao(9,15), new Posicao(19,15),

            new Posicao(0,16), new Posicao(1,16), new Posicao(2,16),
            new Posicao(3,16), new Posicao(4,16), new Posicao(5,16),
            new Posicao(6,16), new Posicao(7,16), new Posicao(8,16),
            new Posicao(9,16), new Posicao(10,16), new Posicao(11,16),
            new Posicao(12,16), new Posicao(13,16), new Posicao(14,16),
            new Posicao(15,16), new Posicao(16,16), new Posicao(17,16),
            new Posicao(18,16), new Posicao(19,16)
        };

        for (Posicao posicao : posicoes) {
            mapa.adicionarItem(new Parede(posicao));
        }
    }

    private void adicionarLetreiros() {
        escreverHorizontal("QUARTO1", 1, 2);
        escreverHorizontal("QUARTO2", 12, 2);
        escreverHorizontal("WC1", 2, 6);
        escreverHorizontal("WC2", 14, 6);
        escreverHorizontal("SALA", 2, 9);
        escreverHorizontal("JANTAR", 1, 10);
        escreverHorizontal("COZINHA", 12, 10);
        escreverHorizontal("SALA", 1, 14);
        escreverHorizontal("TV", 6, 14);
        escreverHorizontal("AREA", 14, 13);
        escreverHorizontal("SERVIÇO", 12, 14);
        escreverVertical("CORREDOR", 9, 2);
    }

    private void escreverHorizontal(String texto, int x, int y) {
        for (int i = 0; i < texto.length(); i++) {
            mapa.adicionarItem(new Letra(new Posicao(x + i, y), texto.charAt(i)));
        }
    }

    private void escreverVertical(String texto, int x, int y) {
        for (int i = 0; i < texto.length(); i++) {
            mapa.adicionarItem(new Letra(new Posicao(x, y + i), texto.charAt(i)));
        }
    }
}
