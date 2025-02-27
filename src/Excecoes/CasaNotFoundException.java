package Excecoes;

public class CasaNotFoundException extends Exception{
    public CasaNotFoundException(){
        super();
    }

    public CasaNotFoundException(String msg){
        super(msg);
    }
}