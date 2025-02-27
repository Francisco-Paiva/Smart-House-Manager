package model.Comercializador;

public class FornecedorA extends Comercializador {

    /**
     * Construtor vazio
     */
    public FornecedorA(){
        super();
    }

    /**
     * construtor parametrizado
     * @param no nome
     * @param pre preço
     * @param imp imposto
     */
    public FornecedorA(String no,Float pre, Float imp){
        super(no,pre,imp);
    }

    /**
     * Construtor de cópia
     * @param fa Fornecedor
     */
    public FornecedorA(FornecedorA fa){
        super(fa);
    }

    /**
     * Método cálculo preço por dia por dispositivo
     * @return double preço
     */
    public Float precoDiaPorDispositivo(){
        return getPreço() + ( getPreço() * getImposto() );
    }

    /**
     * Método clone
     * @return objeto clonado
     */
    public FornecedorA clone(){
        return new FornecedorA(this);
    }
}
