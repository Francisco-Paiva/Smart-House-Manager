package Excecoes;

public class FornecedorNaoExisteException extends Exception {

    public FornecedorNaoExisteException(){
        super();
    }

    public FornecedorNaoExisteException(String msg){
        super(msg);
    }
}
