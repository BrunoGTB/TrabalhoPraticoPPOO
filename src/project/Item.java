package project;

public abstract class Item {
    
    public abstract String getNome();

    public abstract String serEncontrado(Cacador cacador);

    public boolean ehTesouro(){
        return false;
    }

    public abstract String getDescricaoGabarito();
}
