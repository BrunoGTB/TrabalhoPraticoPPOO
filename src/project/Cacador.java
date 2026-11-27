package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa o jogador, controlando sua localização atual
 * na casa, o número de tentativas restantes e os itens coletados (chave mestra e dicas).
 */
public class Cacador {
    private Ambiente ambienteAtual;
    private int tentativas;
    private ChaveMestra chave;
    private List<Dica> dicas = new ArrayList<Dica>();

    /**
     * Cria um novo caçador posicionado em um ambiente inicial e com um limite de tentativas.
     *
     * @param ambienteInicial ambiente onde o jogador começa.
     * @param tentativas quantidade inicial de tentativas disponíveis.
     */
    public Cacador(Ambiente ambienteInicial, int tentativas){
        ambienteAtual = ambienteInicial;
        this.tentativas = tentativas;
    }

    public Ambiente getAmbienteAtual(){
        return ambienteAtual;
    }

    public int getTentativas(){
        return tentativas;
    }

    /**
     * Registra uma nova dica coletada pelo caçador.
     *
     * @param dica dica encontrada durante a exploração.
     */
    public void adicionarDica(Dica dica){
        dicas.add(dica);
    }

    /**
     * Equipa o caçador com uma chave mestra encontrada no mapa.
     *
     * @param chave instância de ChaveMestra coletada.
     */
    public void receberChave(ChaveMestra chave){
        this.chave = chave;
    }

    public List<Dica> getDicas(){
        return Collections.unmodifiableList(dicas);
    }

    /**
     * Verifica se o caçador possui uma chave mestra e se ela ainda tem durabilidade.
     *
     * @return true se a chave puder ser utilizada, false caso contrário.
     */
    public boolean podeUsarChave(){
        return chave != null && chave.podeSerUsada();
    }

    /**
     * Consome um ponto de durabilidade da chave mestra do caçador.
     */
    public void usarChave(){
        chave.desgastar();
    }

    /**
     * Verifica se o caçador já encontrou a chave mestra.
     *
     * @return true se o jogador possuir uma chave, false caso contrário.
     */
    public boolean temChave(){
        return chave != null;
    }

    /**
     * Retorna a durabilidade da chave mestra.
     *
     * @return A durabilidade da chave mestra, se não tiver chave retorna 0.
     */
    public int getDurabilidadeChave(){
        if(!temChave()){
            return 0;
        }
        return chave.getDurabilidade();
    }

    /**
     * Atualiza a localização atual do caçador para um novo ambiente.
     *
     * @param destino o Ambiente para onde o jogador irá se mover.
     */
    public void moverPara(Ambiente destino){
        ambienteAtual = destino;
    }

    /**
     * Decrementa a quantidade tentativas do jogador, caso ainda tenha tentativas restantes.
     */
    public void gastarTentativa(){
        if(temTentativas()){
            tentativas--;
        }
    }

    /**
     * Verifica se o jogador ainda possui tentativas para continuar jogando.
     *
     * @return true se houver pelo menos uma tentativa restante, false se não tiver.
     */
    public boolean temTentativas(){
        return tentativas > 0;
    }
}