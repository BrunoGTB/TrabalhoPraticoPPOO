package gui;

/**
 * Representa o jogador
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Jogador extends Desenhavel{
    private Posicao posicaoDestino;

    public Jogador(){
        super(carregarImagem("/Imagens/pessoa.png"));
    }
    
    public Jogador(Posicao localizacao) {
        super(
            localizacao, 
            carregarImagem("/Imagens/pessoa.png")
        );
        posicaoDestino = null;
    }

    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    public void setPosicaoOrigem(Posicao localizacaoAtual) {
        super.setPosicaoOrigem(localizacaoAtual);
    }

    public void setPosicaoDestino(Posicao localizacaoDestino) {
        this.posicaoDestino = localizacaoDestino;
    }
    
    public void executarAcao(Posicao proximaLocalizacao) {
        Posicao destino = getPosicaoDestino();
        if(destino != null){
            setPosicaoOrigem(proximaLocalizacao);
        }
    } 
}