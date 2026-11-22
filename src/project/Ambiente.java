package project;
import java.util.HashMap;

public class Ambiente 
{
    private String descricao;
    private HashMap<String, Ambiente> saidas;

    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
        saidas = new HashMap<>();
    }

    public Ambiente getSaida(String direcao){
        return saidas.get(direcao);
    }

    public String getSaidasString(){
        String saidasDisponiveis = "Saídas: ";
        for(String direcao : saidas.keySet()){
            saidasDisponiveis += direcao + " ";
        }
        return saidasDisponiveis;
    }

    public void ajustarSaida(String direcao, Ambiente destino) 
    {
        saidas.put(direcao, destino);
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getDescricaoLonga(){
        return "Você está " + descricao + "\n" + getSaidasString() + "\n";
    }

}
