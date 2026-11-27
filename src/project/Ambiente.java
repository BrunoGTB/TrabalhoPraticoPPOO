package project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Representa uma localização dentro da casa mal-assombrada do jogo.
 * Cada ambiente possui um nome, uma descrição, um mapeamento de portas que levam
 * a outros ambientes vizinhos e uma lista de itens escondidos no local.
 */
public class Ambiente 
{
    private String nome;
    private String descricao;
    private HashMap<String, Porta> saidas;
    private List<Item> itens;

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
        saidas = new HashMap<String, Porta>();
        itens = new ArrayList<Item>();
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

    /**
     * Adiciona um item à lista de itens presentes neste ambiente.
     *
     * @param item item a ser colocado no ambiente.
     */
    public void adicionarItem(Item item){
        itens.add(item);
    }

    /**
     * Verifica se o tesouro está escondido neste ambiente.
     *
     * @return true se algum dos itens do ambiente for o tesouro, false caso contrário.
     */
    public boolean temTesouro(){
        for(Item item : itens){
            if(item.ehTesouro()) return true;
        }
        return false;
    }

    /**
     * Entrega ao caçador os itens encontrados no ambiente, removendo-os do local
     * conforme forem coletados.
     *
     * @param cacador jogador que está explorando o ambiente.
     * @return Lista de mensagens descrevendo os itens encontrados.
     */
    public List<String> entregarItens(Cacador cacador){
        List<String> mensagens = new ArrayList<String>();
        Iterator<Item> it = itens.iterator();
        while(it.hasNext()){
            String mensagem = it.next().serEncontrado(cacador);
            if(mensagem != null){
                mensagens.add(mensagem);
                it.remove();
            }
        }
        return mensagens;
    }

    public List<Item> getItens(){
        return Collections.unmodifiableList(itens);
    }
}