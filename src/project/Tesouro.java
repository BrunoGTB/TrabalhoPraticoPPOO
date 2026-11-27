package project;

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
