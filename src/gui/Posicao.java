package gui;

/**
 * Representa uma localização no mapa
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Posicao {
    private final int x;
    private final int y;
    
    /**
     * Cria uma posição no mapa
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     */
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Posicao.
     * @return true: se as coordenadas são iguais.
     *         false: caso contrario.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }else if(!(obj instanceof Posicao)){
            return false;
        }else{
            Posicao outro = (Posicao) obj;
            return x == outro.x && y == outro.y;
        }
    }

    /**
     * Gera o código hash a partir das coordenadas.
     * Precisa ser coerente com equals para Posicao funcionar em HashMap e HashSet.
     */
    @Override 
    public int hashCode() {
        return 31 * x + y;
    }
    
    /**
     * @return A posição no formato (x, y).
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    
}
