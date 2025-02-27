package model.Comercializador;

public class FornecedorB extends Comercializador {
    /**
     * Construtor vazio
     */
    public FornecedorB(){
        super();
    }

    /**
     * construtor parametrizado
     * @param no nome
     * @param pre preço
     * @param imp imposto
     */
    public FornecedorB(String no,Float pre, Float imp){
        super(no,pre,imp);
    }

    /**
     * Construtor de cópia
     * @param fb Fornecedor
     */
    public FornecedorB(FornecedorB fb){
        super(fb);
    }

    /**
     * Método cálculo preço por dia por dispositivo
     * @return double preço
     */
    public Float precoDiaPorDispositivo(){
        return getPreço() + ( getPreço() * getImposto() * 1.10f);
    }

    /**
     * Método clone
     * @return objeto clonado
     */
    public FornecedorB clone(){
        return new FornecedorB(this);
    }
}
