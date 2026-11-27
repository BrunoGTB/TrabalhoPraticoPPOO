package project;

public class Dica extends Item{
    private Ambiente ambienteSemTesouro;

    public Dica(Ambiente ambienteSemTesouro){
        this.ambienteSemTesouro = ambienteSemTesouro;
    }

    @Override 
    public String getNome(){
        return "Dica";
    }

    public String getTexto(){
        return "O tesouro não está " + ambienteSemTesouro.getDescricao();
    }

    @Override 
    public String serEncontrado(Cacador cacador){
        cacador.adicionarDica(this);
        return "Você encontrou uma dica: " + getTexto();
    }

    @Override 
    public String getDescricaoGabarito(){
        return "Dica: " + getTexto();
    }
}
