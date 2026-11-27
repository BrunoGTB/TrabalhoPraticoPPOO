package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public void montarMapa(String nomeArquivo) {
        try (BufferedReader arq = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = arq.readLine();
            while (linha != null) {
                if (!linha.isEmpty() && !linha.startsWith("#")) {
                    String[] campos = linha.split(";");
                    processarLinha(campos);
                }
                linha = arq.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Impossível abrir o arquivo " + nomeArquivo);
        }
        catch (IOException e) {
            System.out.println("Problema na leitura do arquivo " + nomeArquivo);
        }
    }

    /**
     * Interpreta uma linha do arquivo já separada em partes.
     */
    private void processarLinha(String[] partes) {
        String tipo = partes[0];
        if (tipo.equals("PAREDE")) {
            int x = Integer.parseInt(partes[1]);
            int y = Integer.parseInt(partes[2]);
            mapa.adicionarItem(new Parede(new Posicao(x, y)));
        } else if (tipo.equals("LETREIRO")) {
            String direcao = partes[1];
            int x = Integer.parseInt(partes[2]);
            int y = Integer.parseInt(partes[3]);
            String texto = partes[4];
            if (direcao.equals("H")) {
                escreverHorizontal(texto, x, y);
            } else if (direcao.equals("V")) {
                escreverVertical(texto, x, y);
            } else {
                throw new IllegalArgumentException("a direção do letreiro deve ser H ou V");
            }
        }
        // Outros tipos (ex.: AMBIENTE) pertencem ao jogo e são ignorados aqui.
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
