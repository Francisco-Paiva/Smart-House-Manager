package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Fatura implements Serializable {

    //----------VARIAVEIS----------
    private int nif_proprietario;
    private String nome_proprietario;
    private String nome_comercializador;
    private LocalDateTime periodo;
    private Float consumo;
    private Float valor_custo;

    //----------GETTERS/SETTERS----------

    /**
     * Getter do nif do proprietário
     * @return nif do proprietário
     */
    public int getNif_proprietario() {
        return nif_proprietario;
    }

    /**
     * Setter do nif do proprietário
     * @param nif_proprietario
     */
    public void setNif_proprietario(int nif_proprietario) {
        this.nif_proprietario = nif_proprietario;
    }

    /**
     * Getter do nome do proprietário
     * @return nome do propritário
     */
    public String getNome_proprietario() {
        return nome_proprietario;
    }

    /**
     * Setter do nome do proprietário
     * @param nome_proprietario
     */
    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }

    /**
     * Getter do nome do comercializador
     * @return nome do comercializador
     */
    public String getNome_comercializador() {
        return nome_comercializador;
    }

    /**
     * Setter do nome do comercializador
     * @param nome_comercializador
     */
    public void setNome_comercializador(String nome_comercializador) {
        this.nome_comercializador = nome_comercializador;
    }

    /**
     * Getter do valor de custo
     * @return valor de custo
     */
    public Float getValor() {
        return valor_custo;
    }

    /**
     * Setter do valor de custo
     * @param valor de custo
     */
    public void setValor(Float valor) {
        this.valor_custo = valor;
    }

    /**
     * Getter do consumo
     * @return consumo
     */
    public Float getConsumo() {
        return consumo;
    }

    /**
     * Setter do consumo
     * @param consumo
     */
    public void setConsumo(Float consumo) {
        this.consumo = consumo;
    }

    /**
     * Getter do periodo da fatura
     * @return periodo da fatura
     */
    public LocalDateTime getPeriodo() {
        return periodo;
    }

    /**
     * Setter do periodo da fatura
     * @param periodo da fatura
     */
    public void setPeriodo(LocalDateTime periodo) {
        this.periodo = periodo;
    }

    //----------CONSTRUTORES----------
    /**
     * Criação do construtor vazio
     */
    public Fatura(){
        this.nif_proprietario=-1;
        this.nome_proprietario="";
        this.nome_comercializador="";
        this.valor_custo=0.0f;
        this.periodo=LocalDateTime.MIN;
        this.consumo=0.0f;
    }

    /**
     * Criação do construtor parametrizado
     * @param nif nif do prproprietário
     * @param nome_prop nome do proprietário
     * @param nome_come nome do comercializador
     * @param periodo periodo da fatura
     * @param val valor de custo
     * @param consumo consumo
     */
    public Fatura(int nif, String nome_prop, String nome_come, LocalDateTime periodo, Float val, Float consumo){
        this.nif_proprietario=nif;
        this.nome_proprietario=nome_prop;
        this.nome_comercializador=nome_come;
        this.valor_custo=val;
        this.consumo=consumo;
        this.periodo=periodo;
    }

    /**
     * Criação do construtor cópia
     * @param fat Objeto Fatura
     */
    public Fatura(Fatura fat){
        this.nif_proprietario=fat.getNif_proprietario();
        this.nome_proprietario=fat.getNome_proprietario();
        this.nome_comercializador=fat.getNome_comercializador();
        this.valor_custo=fat.getValor();
        this.consumo=fat.getConsumo();
        this.periodo=fat.getPeriodo();
    }

    //----------MÉTODOS---------

    //----------OVERRIDES----------

    /**
     * Método que faz o clone do Objeto
     * @return clone do Objeto Fatura
     */
    public Fatura clone(){
        return new Fatura(this);
    }

    /**
     * Método que indica a informação que pretende ser impressa
     * @return Informação imprimida
     */
    @Override
    public String toString() {
        return "Model.Fatura{" +
                "nif_proprietario=" + nif_proprietario +
                ", nome_proprietario='" + nome_proprietario + '\'' +
                ", nome_comercializador='" + nome_comercializador + '\'' +
                ", valor=" + valor_custo +
                '}';
    }

    /**
     * Método que indica a informação que pretende ser impressa
     * @return Informação imprimida
     */
    public String toStringw(){
        return "\nModel.Fatura:" + getNif_proprietario() + "," + getNome_proprietario() + "," + getNome_comercializador() + "," + getPeriodo() + "," + getConsumo() + "," + getValor() ;
    }

    /**
     * Método que verifica a igualdade dos objetos
     * @param o Objeto da classe
     * @return Boolean que indica se é igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fatura)) return false;

        Fatura fatura = (Fatura) o;

        if (nif_proprietario != fatura.nif_proprietario) return false;
        if (!Objects.equals(nome_proprietario, fatura.nome_proprietario))
            return false;
        if (!Objects.equals(nome_comercializador, fatura.nome_comercializador))
            return false;
        return Objects.equals(valor_custo, fatura.valor_custo);
    }


}
