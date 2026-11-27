package project;

/**
 * Representa uma chave mestra com durabilidade limitada.
 */
public class ChaveMestra extends Item{
    private int durabilidade;

    /**
     * Cria uma nova chave mestra com a quantidade de usos especificada.
     *
     * @param durabilidade número de vezes que a chave pode ser utilizada.
     */
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

    /**
     * Verifica se a chave ainda possui durabilidade para ser usada.
     *
     * @return true se a durabilidade for maior que zero, false caso contrário.
     */
    public boolean podeSerUsada(){
        return durabilidade > 0;
    }

    /**
     * Reduz em uma unidade a durabilidade da chave, caso ela ainda possa ser usada.
     */
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