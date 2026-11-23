package project;

public class Cacador {
    private Ambiente ambienteAtual;
    private int tentativas;

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

    public void moverPara(Ambiente destino){
        ambienteAtual = destino;
    }

    public void gastarTentativa(){
        if(temTentativas()){
            tentativas--;
        }
    }

    public boolean temTentativas(){
        return tentativas > 0;
    }
}
