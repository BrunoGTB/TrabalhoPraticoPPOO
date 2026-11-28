package project;

public interface OuvinteJogo {
    void exibirMensagem(String texto);
    void cacadorMoveu(Ambiente origem, Ambiente destino);
    void estadoAtualizado(Cacador cacador);
    void jogoEncerrado();
}
