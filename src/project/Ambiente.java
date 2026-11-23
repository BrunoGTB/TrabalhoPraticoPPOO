package project;
import java.util.HashMap;

public class Ambiente 
{
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;

    public Ambiente(String nome, String descricao) 
    {
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<>();
    }

    public Porta getPorta(String nomeDestino){
        return saidas.get(nomeDestino);
    }

    public String getSaidasString(){
        String saidasDisponiveis = "Saídas: ";
        for(String direcao : saidas.keySet()){
            saidasDisponiveis += direcao + " ";
        }
        return saidasDisponiveis;
    }

    public void adicionarPorta(Porta porta){
        saidas.put(porta.getOutroLado(this).getNome(), porta);
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricaoLonga(){
        return "Você está " + descricao + " da casa mal-assombrada.\n" + getSaidasString() + "\n";
    }

}
