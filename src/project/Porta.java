package project;

import java.util.Random;

public class Porta {
    private Ambiente ladoA;
    private Ambiente ladoB;
    private boolean emperrada;

    public Porta(Ambiente ladoA, Ambiente ladoB){
        this.ladoA = ladoA;
        this.ladoB = ladoB;
    }

    public boolean estaEmperrada(){
        return emperrada;
    }

    public void redefinirEmperrada(Random sorteador){
        emperrada = sorteador.nextInt(100) < 40; //40% de chance de emperrar
    }

    public Ambiente getOutroLado(Ambiente origem){
        if(origem == ladoA){
            return ladoB;
        }
        return ladoA;
    }
}
