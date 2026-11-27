package project;

/**
 * Representa uma pista que informa ao jogador um ambiente onde o tesouro não está.
 */
public class Dica extends Item{
    private Ambiente ambienteSemTesouro;

    /**
     * Cria uma nova dica indicando um ambiente específico que não contém o tesouro.
     *
     * @param ambienteSemTesouro ambiente descartado pela dica.
     */
    public Dica(Ambiente ambienteSemTesouro){
        this.ambienteSemTesouro = ambienteSemTesouro;
    }

    @Override 
    public String getNome(){
        return "Dica";
    }

    /**
     * Retorna o texto informativo da dica sobre a ausência do tesouro no ambiente associado.
     *
     * @return Texto indicando onde o tesouro não está.
     */
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