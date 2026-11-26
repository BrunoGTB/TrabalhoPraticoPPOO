package project;
import java.util.HashMap;

/**
 * Representa uma localização dentro da casa mal-assombrada do jogo.
 * Cada ambiente possui um nome, uma descrição e um mapeamento de portas que levam
 * a outros ambientes vizinhos.
 */
public class Ambiente 
{
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;

    /**
     * Cria um novo ambiente com nome e descrição definidos.
     *
     * @param nome nome do ambiente.
     * @param descricao descrição do ambiente.
     */
    public Ambiente(String nome, String descricao) 
    {
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<>();
    }

    /**
     * Retorna a porta associada a um ambiente de destino específico.
     *
     * @param nomeDestino nome do ambiente para o qual se deseja ir.
     * @return O objeto Porta correspondente, ou null se não houver passagem.
     */
    public Porta getPorta(String nomeDestino){
        return saidas.get(nomeDestino);
    }

    /**
     * Monta uma string listando todas as saídas disponíveis a partir do ambiente.
     *
     * @return Texto contendo os nomes dos ambientes vizinhos acessíveis.
     */
    public String getSaidasString(){
        String saidasDisponiveis = "Saídas: ";
        for(String direcao : saidas.keySet()){
            saidasDisponiveis += direcao + " ";
        }
        return saidasDisponiveis;
    }

    /**
     * Registra uma porta conectando este ambiente ao ambiente do outro lado.
     *
     * @param porta a porta a ser vinculada como saída deste ambiente.
     */
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

    /**
     * Retorna a descrição completa do ambiente atual junto com suas saídas disponíveis.
     *
     * @return Texto descritivo para exibição ao jogador.
     */
    public String getDescricaoLonga(){
        return "Você está " + descricao + " da casa mal-assombrada.\n" + getSaidasString() + "\n";
    }
}