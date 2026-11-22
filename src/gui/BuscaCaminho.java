package gui;

import java.util.List;

// Interface que permite pelo polimorfismo usar qualquer algoritmo de busca entre 2 posições
public interface BuscaCaminho {
    List<Posicao> encontrarCaminho(Posicao origem, Posicao destino);
}