package gui;

import java.util.List;

/**
 * Algoritmo que encontra um caminho entre duas posições do mapa.
 * Permite trocar o algoritmo de busca sem alterar a Simulacao (polimorfismo).
 */
public interface BuscaCaminho {
    /**
     * @param origem posição de partida
     * @param destino posição de chegada
     * @return lista de posições da origem até o destino, ou lista vazia se não houver caminho
     */
    List<Posicao> encontrarCaminho(Posicao origem, Posicao destino);
}