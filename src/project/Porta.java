package project;

import java.util.Random;

/**
 * Representa uma ligação entre dois ambientes da casa,
 * podendo ficar emperrada.
 */
public class Porta {
    private Ambiente ladoA;
    private Ambiente ladoB;
    private boolean emperrada;

    /**
     * Cria uma porta conectando dois ambientes distintos.
     *
     * @param ladoA Primeiro ambiente conectado pela porta.
     * @param ladoB Segundo ambiente conectado pela porta.
     */
    public Porta(Ambiente ladoA, Ambiente ladoB){
        this.ladoA = ladoA;
        this.ladoB = ladoB;
    }

    /**
     * Informa se a porta está atualmente emperrada e bloqueando a passagem.
     *
     * @return true se estiver emperrada, false se estiver livre.
     */
    public boolean estaEmperrada(){
        return emperrada;
    }

    /**
     * Atualiza aleatoriamente o estado da porta com 40% de chance de ficar emperrada.
     *
     * @param sorteador instância de Random utilizada no sorteio.
     */
    public void redefinirEmperrada(Random sorteador){
        emperrada = sorteador.nextInt(100) < 40; //40% de chance de emperrar
    }

    /**
     * Retorna o ambiente no lado oposto ao ambiente de origem.
     *
     * @param origem ambiente atual de onde o jogador está tentando sair.
     * @return O Ambiente conectado do outro lado da porta.
     */
    public Ambiente getOutroLado(Ambiente origem){
        if(origem == ladoA){
            return ladoB;
        }
        return ladoA;
    }
}