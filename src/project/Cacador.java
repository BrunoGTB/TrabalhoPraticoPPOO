package project;

/**
 * Representa o jogador, controlando sua localização atual
 * na casa e o número de tentativas restantes.
 */
public class Cacador {
    private Ambiente ambienteAtual;
    private int tentativas;

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
