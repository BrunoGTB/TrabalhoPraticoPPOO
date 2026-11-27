package project;

import java.io.IOException;

/**
 * Ponto de entrada da aplicação que instancia e inicia o jogo.
 */
public class Programa {

	public static void main(String[] args) {
		try{
			LeitorArquivo leitor = new LeitorArquivo("src/casa.txt");
			Casa casa = new Casa(leitor);
			new Jogo(casa).jogar();
		}catch(IOException e){
			System.out.println("Não foi possível ler o arquivo da casa: " + e.getMessage());
		}catch(ArquivoInvalidoException e){
			System.out.println("Arquivo da casa inválido: " + e.getMessage());
		}
	}

}