package project;

import java.util.ArrayList;
import java.util.Random;

/**
 * Gerencia o mapa da casa mal-assombrada, armazenando todos os ambientes,
 * as portas que os interligam e o ponto de partida do jogador.
 */
public class Casa {
    private ArrayList<Ambiente> ambientes;
    private ArrayList<Porta> portas;
    private Ambiente ambienteInicial;

    /**
     * Constrói a casa e inicializa toda a estrutura de ambientes e conexões.
     */
    public Casa(){
        ambientes = new ArrayList<>();
        portas = new ArrayList<>();
        criarAmbientes();
    }

    private void criarAmbientes()
    {
        Ambiente salaTv      = criarAmbiente("Sala de TV", "na Sala de TV");
        Ambiente areaServico = criarAmbiente("Área de Serviço", "na Área de Serviço");
        Ambiente salaJantar  = criarAmbiente("Sala de Jantar", "na Sala de Jantar");
        Ambiente cozinha     = criarAmbiente("Cozinha", "na Cozinha");
        Ambiente corredor    = criarAmbiente("Corredor", "no Corredor");
        Ambiente wc1         = criarAmbiente("WC 1", "no WC 1");
        Ambiente wc2         = criarAmbiente("WC 2", "no WC 2");
        Ambiente quarto1     = criarAmbiente("Quarto 1", "no Quarto 1");
        Ambiente quarto2     = criarAmbiente("Quarto 2", "no Quarto 2");

        ligar(salaTv, salaJantar);
        ligar(salaJantar, corredor);
        ligar(corredor, wc1);
        ligar(corredor, wc2);
        ligar(corredor, quarto1);
        ligar(corredor, quarto2);
        ligar(corredor, cozinha);
        ligar(cozinha, areaServico);

        ambienteInicial = salaTv;
    }

    private Ambiente criarAmbiente(String nome, String descricao){
        Ambiente ambiente = new Ambiente(nome, descricao);
        ambientes.add(ambiente);
        return ambiente;
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
     * @param sorteador gerador de números aleatórios usado para definir o estado de cada porta.
     */
    public void redefinirPortas(Random sorteador){
        for(Porta porta : portas){
            porta.redefinirEmperrada(sorteador);
        }
    }

    public Ambiente getAmbienteInicial(){
        return ambienteInicial;
    }
}
