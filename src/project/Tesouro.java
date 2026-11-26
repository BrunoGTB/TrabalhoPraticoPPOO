package project;

public class Tesouro extends Item{
    @Override 
    public String getNome(){
        return "Tesouro";
    }

    @Override 
    public boolean ehTesouro(){
        return true;
    }
}
