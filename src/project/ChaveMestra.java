package project;

public class ChaveMestra extends Item{
    private int durabilidade;

    public ChaveMestra(int durabilidade){
        this.durabilidade = durabilidade;
    }

    @Override 
    public String getNome(){
        return "Chave Mestra";
    }

    public int getDurabilidade(){
        return durabilidade;
    }

    public boolean podeSerUsada(){
        return durabilidade > 0;
    }

    public void desgastar(){
        if(podeSerUsada()) durabilidade--;
    }

    @Override 
    public String serEncontrado(Cacador cacador){
        cacador.receberChave(this);
        return "Você encontrou a chave mestra! Durabilidade: " + durabilidade;
    }

    @Override 
    public String getDescricaoGabarito(){
        return "Chave mestra (durabilidade " + durabilidade + ")";
    }
}
