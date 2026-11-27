package project;

/**
 * Representa o tesouro enterrado em um dos ambientes da casa, que é o objetivo principal
 * do jogador e só pode ser revelado ao explodir o chão do cômodo correto.
 */
public class Tesouro extends Item{
    @Override 
    public String getNome(){
        return "Tesouro";
    }

    @Override 
    public String serEncontrado(Cacador cacador){
        return null;
    }

    @Override 
    public boolean ehTesouro(){
        return true;
    }

    @Override 
    public String getDescricaoGabarito(){
        return "Tesouro";
    }
}