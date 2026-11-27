package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Gerencia o mapa da casa mal-assombrada, armazenando todos os ambientes,
 * as portas que os interligam e o ponto de partida do jogador.
 */
public class Casa {
    private ArrayList<Ambiente> ambientes;
    private ArrayList<Porta> portas;
    private Map<String, Ambiente> ambientesPorNome;
    private Ambiente ambienteInicial;
    private Ambiente ambienteTesouro;
    private static final int NUMERO_DE_DICAS = 2;

    /**
     * Constrói a casa e inicializa toda a estrutura de ambientes e conexões.
     */
    public Casa(LeitorArquivo leitor) throws ArquivoInvalidoException{
        ambientes = new ArrayList<Ambiente>();
        portas = new ArrayList<Porta>();
        ambientesPorNome = new HashMap<String, Ambiente>();
        carregarAmbientes(leitor);
        carregarPortas(leitor);
        carregarAmbienteInicial(leitor);
    }

    private void carregarAmbientes(LeitorArquivo leitor) throws ArquivoInvalidoException{
        for(String[] registro : leitor.getRegistros("AMBIENTE")){
            LeitorArquivo.exigirCampos(registro, 3);
            String nome = registro[1];
            if(ambientesPorNome.containsKey(nome)){
                throw new ArquivoInvalidoException("Ambiente repetido: " + nome);
            }
            Ambiente ambiente = new Ambiente(nome, registro[2]);
            ambientes.add(ambiente);
            ambientesPorNome.put(nome, ambiente);
        }
        if(ambientes.isEmpty()){
            throw new ArquivoInvalidoException("O arquivo não define nenhum AMBIENTE.");
        }
    }

    private void carregarPortas(LeitorArquivo leitor) throws ArquivoInvalidoException{
        for(String[] registro : leitor.getRegistros("PORTA")){
            LeitorArquivo.exigirCampos(registro, 3);
            ligar(buscarAmbiente(registro[1]), buscarAmbiente(registro[2]));
        }
    }

    private void carregarAmbienteInicial(LeitorArquivo leitor) throws ArquivoInvalidoException{
        List<String[]> registros = leitor.getRegistros("INICIAL");
        if(registros.size() != 1){
            throw new ArquivoInvalidoException("O arquivo deve ter exatamente uma linha INICIAL");
        }
        LeitorArquivo.exigirCampos(registros.get(0), 2);
        ambienteInicial = buscarAmbiente(registros.get(0)[1]);
    }

    private Ambiente buscarAmbiente(String nome) throws ArquivoInvalidoException{
        Ambiente ambiente = ambientesPorNome.get(nome);
        if(ambiente == null){
            throw new ArquivoInvalidoException("Ambiente não declarado: " + nome);
        }
        return ambiente;
    }

    public Ambiente sortearAmbiente(Random randomizer){
        return ambientes.get(randomizer.nextInt(ambientes.size()));
    }

    public Ambiente getAmbienteTesouro(){
        return ambienteTesouro;
    }

    public void distribuirItens(Random randomizer){
        ambienteTesouro = sortearAmbiente(randomizer);
        ambienteTesouro.adicionarItem(new Tesouro());

        List<Ambiente> jaUsados = new ArrayList<Ambiente>();
        jaUsados.add(ambienteTesouro);
        for(int i = 0; i < NUMERO_DE_DICAS; i++){
            Ambiente semTesouro = sortearAmbienteFora(jaUsados, randomizer);
            jaUsados.add(semTesouro);
            sortearAmbiente(randomizer).adicionarItem(new Dica(semTesouro));
        }

        int durabilidade = randomizer.nextInt(10) + 1;
        sortearAmbiente(randomizer).adicionarItem(new ChaveMestra(durabilidade));
    }

    private Ambiente sortearAmbienteFora(List<Ambiente> excluidos, Random randomizer){
        Ambiente sorteado;
        do{
            sorteado = sortearAmbiente(randomizer);
        }while(excluidos.contains(sorteado));
        return sorteado;
    }

    private void ligar(Ambiente a, Ambiente b){
        Porta porta = new Porta(a, b);
        a.adicionarPorta(porta);
        b.adicionarPorta(porta);
        portas.add(porta);
    }

    /**
     * Redefine novamente o estado (emperrada ou não) de todas as portas da casa.
     *
     * @param randomizer gerador de números aleatórios usado para definir o estado de cada porta.
     */
    public void redefinirPortas(Random randomizer){
        for(Porta porta : portas){
            porta.redefinirEmperrada(randomizer);
        }
    }

    public Ambiente getAmbienteInicial(){
        return ambienteInicial;
    }

    public List<String> gerarGabarito(){
        List<String> linhas = new ArrayList<String>();
        for(Ambiente ambiente : ambientes){
            for(Item item : ambiente.getItens()){
                linhas.add(ambiente.getNome() + ": " + item.getDescricaoGabarito());
            }
        }
        return linhas;
    }
}
