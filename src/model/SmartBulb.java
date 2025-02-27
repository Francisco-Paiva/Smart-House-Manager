package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SmartBulb extends SmartDevice implements Serializable {
    /**
     * Enum das Tonalidades das lâmpadas inteligentes
     */
    public enum Tonalidade{
        Neutral,
        Warm,
        Cold
    }

    //----------VARIAVEIS----------
    private Tonalidade tonalidade;
    private Float dimensão;



    //----------GETTERS/SETTERS----------

    /**
     * Getter da dimensãoX da lâmpada
     * @return Float dimensãoX
     */
    public Float getDimensão() {
        return dimensão;
    }

    /**
     * Setter da dimensãoX da lâmpada
     * @param dimensão dimensão da lâmpada
     */
    public void setDimensão(Float dimensão) {
        this.dimensão = dimensão;
    }


    /**
     * Getter da tonalidade da lâmpada
     * @return Tonalidade
     */
    public Tonalidade getTonalidade() {
        return tonalidade;
    }

    /**
     * Setter da tonalidade da lâmpada
     * @param tonalidade Tonalidade
     */
    public void setTonalidade(Tonalidade tonalidade) {
        this.tonalidade = tonalidade;
    }



    //----------CONSTRUTORES----------

    /**
     * Construtor vazio
     */
    public SmartBulb(){
        super();
        this.tonalidade=Tonalidade.Cold;
        this.dimensão=Float.parseFloat("0");
    }

    /**
     * Construtor parametrizado
     * @param id Identificador
     * @param tonalidade Tonalidade
     * @param dimensãoX dimensão
     * @param consumo consumo
     */
    public SmartBulb(String id,Tonalidade tonalidade, Float dimensãoX, Float consumo){
        super(id, consumo);
        this.tonalidade=tonalidade;
        this.dimensão=dimensãoX;
    }

    /**
     * Construtor parametrizado
     * @param i Identificador
     * @param cond Consumo diário
     * @param cont Consumo total
     * @param instal Custo Instalação
     * @param est Estado do dispositivo
     * @param tempo tempo de consumo
     * @param tonalidade Tonalidade
     * @param dimensão dimensão
     */
    public SmartBulb(String i, Float cond,Float cont,  Float instal, Estado est, LocalDateTime tempo, Tonalidade tonalidade, Float dimensão){
        super(i,cond,cont,instal,est,tempo);
        this.tonalidade=tonalidade;
        this.dimensão=dimensão;
    }

    /**
     * Construtor de cópia
     * @param sb lâmpada a ser copiada
     */
    public SmartBulb(SmartBulb sb){
        super(sb.getId(),sb.getConsumo_diario(),sb.getConsumo_total(),sb.getCusto_instal(),sb.getEstado(),sb.getTempo_de_consumo());
        this.dimensão=sb.getDimensão();
        this.tonalidade=sb.getTonalidade();
    }



    //----------MÉTODOS----------



    //----------OVERRIDES----------

    /**
     * Método equals
     * @param o the reference object with which to compare.
     * @return Booleano true se for igual, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartBulb smartBulb = (SmartBulb) o;
        return Objects.equals(tonalidade, smartBulb.tonalidade) && Objects.equals(dimensão, smartBulb.dimensão);
    }

    /**
     * Método hashCode
     * @return int hashCode respetivo
     */
    @Override
    public int hashCode() {
        return Objects.hash(tonalidade, dimensão);
    }

    /**
     * Método toString
     * @return objeto SmartBulb em String
     */
    @Override
    public String toString() {
        return "SmartBulb:" + getTonalidade() + "," + getDimensão() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método toString alternativo
     * @return objeto SmartBulb em String
     */
    public String toStringw() {
        return "SmartBulb:" + getTonalidade() + "," + getDimensão() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método clone
     * @return Objeto clonado
     */
    public SmartBulb clone(){
        return new SmartBulb(this);
    }

    /**
     * Método para devolver o tipo de SmartDevice
     * @return String com o tipo "SmartBulb"
     */
    @Override
    public String getTipo(){
        return "SmartBulb";
    }
}
