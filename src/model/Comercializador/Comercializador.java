package model.Comercializador;

import model.Fatura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Comercializador implements Serializable {

    //----------VARIAVEIS----------
    private String nome;
    private Float preço;
    private Float imposto;
    private List<Fatura> lista_faturas;

    //----------GETTERS/SETTERS----------

    /**
     * Getter do preço do comercializador
     * @return preço do comercializador
     */
    public Float getPreço() {
        return preço;
    }

    /**
     * Setter do preço do comercializador
     * @param preço
     */
    public void setPreço(Float preço) {
        this.preço = preço;
    }

    /**
     * Getter do nome do comercializador
     * @return nome do comercializador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter do nome do comercializador
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter da lista de faturas
     * @return Lista de faturas
     */
    public List<Fatura> getLista_faturas() {
        if(lista_faturas==null) return new ArrayList<>();
        return lista_faturas;
    }

    /**
     * Setter da lista de faturas
     * @param lista_faturas
     */
    public void setLista_faturas(List<Fatura> lista_faturas) {
        this.lista_faturas = lista_faturas;
    }

    /**
     * Getter do imposto do comercializador
     * @return imposto do comercializador
     */
    public Float getImposto(){
        return this.imposto;
    }

    /**
     * Setter do imposto do comercializador
     * @param imposto
     */
    public void setImposto(Float imposto) {
        this.imposto = imposto;
    }

    //----------CONSTRUTORES----------

    /**
     * Construtor vazio
     */
    public Comercializador(){
        this.nome="";
        this.preço= Float.valueOf(0);
        this.imposto=Float.valueOf(0);
        this.lista_faturas=new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     * @param no nome do comercializador
     * @param pre preço do comercializador
     * @param imp imposto do comercializador
     */
    public Comercializador(String no,Float pre, Float imp){
        this.nome=no;
        this.preço=pre;
        this.imposto=imp;
        this.lista_faturas=new ArrayList<>();
    }

    /**
     * Construtor cópia
     * @param comer Objeto Comercializador
     */
    public Comercializador(Comercializador comer){
        this.nome=comer.getNome();
        this.preço=comer.getPreço();
        this.imposto=comer.getImposto();
        this.lista_faturas=new ArrayList<>();
        for (Fatura f : comer.getLista_faturas()){
            this.lista_faturas.add(f.clone());
        }
    }

    //----------MÉTODOS----------

    /**
     * Método que calcula do preço
     * @return preço
     */
    public abstract Float precoDiaPorDispositivo();

    /**
     * Método para adicionar uma fatura
     * @param f Fatura a ser adicionada
     */
    public void adicionaFatura(Fatura f){
        List<Fatura> lista = getLista_faturas();
        lista.add(f.clone());
        setLista_faturas(lista);
    }

    //----------OVERRIDES----------

    /**
     * Método parse do objeto Comercializador
     * @param str String que sofrerá o parsing
     * @return Comercializador
     */
    //TODO Fazer isto se bem que nao sei se é preciso
    public static Comercializador parse(String str){
        String[] var_comer=str.split(":",2);
        if (var_comer[0].equals("Model.Comercializador")){
            String[] var=var_comer[1].split("#");
            Random random= new Random();
            if (random.nextInt(2) == 0){
                return new FornecedorA(var[0],Float.parseFloat(var[1]),Float.parseFloat(var[2]));
            }
            else {
                return new FornecedorB(var[0],Float.parseFloat(var[1]),Float.parseFloat(var[2]));
            }

        }
        return null;
    }

    /**
     * Método toString do objeto Comercializador
     * @return String do objeto em String
     */
    public String toStringw(){
        StringBuilder stb = new StringBuilder();
        stb.append("Fornecedor:" + getNome() + "," + getPreço() + "," + getImposto() );
        /*
        if (!getLista_faturas().isEmpty()){
            stb.append("\n");
        }*/
        for (Fatura f : getLista_faturas()){
            stb.append(f.toStringw());
        }
        return stb.toString();
    }

    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append("Fornecedor:" + getNome());
        return stb.toString();
    }

    /**
     * Método clone do objeto Comercializador
     * @return Comercializador clonado
     */
    public abstract Comercializador clone();

    /**
     * Método equals do objeto Comercializador
     * @param o Objeto Comercializador
     * @return booleano
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comercializador that = (Comercializador) o;
        return (nome.equals(that.getNome())) && (preço==that.getPreço()) && (imposto==that.getImposto());
    }

    /**
     * Método hashCode do objeto Comercializador
     * @return int hashCode correspondente
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, preço, imposto);
    }
}
