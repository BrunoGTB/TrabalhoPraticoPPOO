package project;

public class PalavrasComando
{
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "olhar", "explodir"
    };

    public boolean ehComando(String umaString)
    {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(umaString))
                return true;
        }
        return false;
    }

    public String mostrarComandos(){
        String comandos = "";
        for(String comando : comandosValidos){
            comandos += comando + " ";
        }
        return comandos;
    }
}
