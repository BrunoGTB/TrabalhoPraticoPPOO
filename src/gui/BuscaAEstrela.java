package gui;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Encontra o menor caminho entre duas posições do mapa usando o algoritmo A*,
 * com a distância Manhattan como heurística.
 */
public class BuscaAEstrela implements BuscaCaminho {
    private final Mapa mapa;

    public BuscaAEstrela(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override 
    public List<Posicao> encontrarCaminho(
            Posicao origem,
            Posicao destino) {

        PriorityQueue<No> abertos =
                new PriorityQueue<>(
                    Comparator.comparingInt(No::getCustoF)
                );

        Set<Posicao> fechados = new HashSet<>();

        Map<Posicao, Integer> melhoresCustos =
                new HashMap<>();

        No noInicial = new No(
            origem,
            null,
            0,
            distanciaManhattan(origem, destino)
        );

        abertos.add(noInicial);
        melhoresCustos.put(origem, 0);
        
        while (!abertos.isEmpty()) {
            No atual = abertos.poll();

            Posicao posicaoAtual = atual.getPosicao();

            if (posicaoAtual.equals(destino)) {
                return reconstruirCaminho(atual);
            }

            fechados.add(posicaoAtual);

            for (Posicao vizinho : obterVizinhos(posicaoAtual)) {

                if (fechados.contains(vizinho)) {
                    continue;
                }

                int novoCustoG =
                    atual.getCustoG() + 1;

                Integer melhorCusto =
                    melhoresCustos.get(vizinho);

                if (melhorCusto == null ||
                    novoCustoG < melhorCusto) {

                    melhoresCustos.put(
                        vizinho,
                        novoCustoG
                    );

                    int custoH =
                        distanciaManhattan(
                            vizinho,
                            destino
                        );

                    No novoNo = new No(
                        vizinho,
                        atual,
                        novoCustoG,
                        custoH
                    );

                    abertos.add(novoNo);
                }
            }
        }

        // Não existe caminho
        return Collections.emptyList();
    }

    private List<Posicao> reconstruirCaminho(No no) {

        List<Posicao> caminho = new ArrayList<>();

        while (no != null) {
            caminho.add(no.getPosicao());
            no = no.getPai();
        }

        Collections.reverse(caminho);

        return caminho;
    }

    private int distanciaManhattan(
            Posicao a,
            Posicao b) {

        return Math.abs(a.getX() - b.getX())
             + Math.abs(a.getY() - b.getY());
    }

    private List<Posicao> obterVizinhos(
            Posicao posicao) {

        List<Posicao> vizinhos = new ArrayList<>();

        int x = posicao.getX();
        int y = posicao.getY();

        adicionarSePossivel(vizinhos, x + 1, y);
        adicionarSePossivel(vizinhos, x - 1, y);
        adicionarSePossivel(vizinhos, x, y + 1);
        adicionarSePossivel(vizinhos, x, y - 1);

        return vizinhos;
    }

    private void adicionarSePossivel(
            List<Posicao> vizinhos,
            int x,
            int y) {

        Posicao vizinho = new Posicao(x, y);
        if (mapa.podePassar(vizinho)) {
            vizinhos.add(vizinho);
        }
    }
}
