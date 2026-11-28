package project;
import java.util.Scanner;

/**
 * Responsável por ler a entrada de texto do usuário no terminal e convertê-la
 * em objetos Comando compreensíveis pelo jogo.
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;

    /**
     * Inicializa o analisador com o dicionário de comandos válidos e o leitor de entrada padrão.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
    }

    /**
     * Lê a próxima linha digitada pelo jogador, separa a primeira palavra e o restante
     * do texto e retorna o comando correspondente.
     *
     * @return Um objeto Comando contendo a ação identificada (ou null se inválida).
     */
    public Comando analisar(String linha){
        String palavra1 = null;
        String palavra2 = null;
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()){
            palavra1 = tokenizer.next();
            if(tokenizer.hasNext()){
                palavra2 = tokenizer.nextLine().trim();
            }
        }
        if(palavrasDeComando.ehComando(palavra1)){
            return new Comando(palavra1, palavra2);
        }
        return new Comando(null, palavra2);
    }

    /**
     * Retorna todas as palavras de comando aceitas pelo jogo.
     *
     * @return String com os comandos válidos.
     */
    public String mostrarComandos(){
        return palavrasDeComando.mostrarComandos();
    }
}