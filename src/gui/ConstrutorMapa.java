package gui;

import project.ArquivoInvalidoException;
import project.LeitorArquivo;

/**
 * Monta o mapa da casa, adicionando paredes e letreiros a um Mapa.
 */
public class ConstrutorMapa {
    private final Mapa mapa;

    public ConstrutorMapa(Mapa mapa){
        this.mapa = mapa;
    }

    /**
     * Lê o arquivo do mapa e adiciona as paredes e os letreiros ao Mapa.
     * Linhas vazias, comentários (#) e linhas de outros tipos são ignoradas.
     * @param nomeArquivo nome do arquivo texto do mapa
     */
    public void montarMapa(LeitorArquivo leitor) throws ArquivoInvalidoException{
        for(String[] registro : leitor.getRegistros("PAREDE")){
            LeitorArquivo.exigirCampos(registro, 3);
            Posicao posicao = lerPosicao(registro, 1);
            mapa.adicionarItem(new Parede(posicao));
        }
        for(String[] registro : leitor.getRegistros("LETREIRO")){
            LeitorArquivo.exigirCampos(registro, 5);
            String direcao = registro[1];
            Posicao inicio = lerPosicao(registro, 2);
            String texto = registro[4];
            if(direcao.equals("H")){
                escreverHorizontal(texto, inicio.getX(), inicio.getY());
            }else if(direcao.equals("V")){
                escreverVertical(texto, inicio.getX(), inicio.getY());
            }else{
                throw new ArquivoInvalidoException("Direção do letreiro deve ser H ou V: " + direcao);
            }
        }
    }

    private Posicao lerPosicao(String[] registro, int indiceX) throws ArquivoInvalidoException{
        int x = LeitorArquivo.converterInteiro(registro, indiceX);
        int y = LeitorArquivo.converterInteiro(registro, indiceX + 1);
        if(!mapa.estaDentro(new Posicao(x, y))){
            throw new ArquivoInvalidoException("Posição fora do mapa: (" + x + ", " + y + ")");
        }
        return new Posicao(x, y);
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
