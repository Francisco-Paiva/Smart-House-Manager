package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SmartSpeaker extends SmartDevice implements Serializable {

    //----------VARIAVEIS----------
    private int volume;
    private String radio_online;
    private String marca;


    //----------GETTERS/SETTERS----------

    /**
     * Getter Marca SmartSpeaker
     * @return String marca SmartSpeaker
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Setter Marca SmartSpeaker
     * @param marca Marca SmartSpeaker
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Getter da rádio que está a tocar
     * @return String nome da radio
     */
    public String getRadio_online() {
        return radio_online;
    }

    /**
     * Setter da rádio que está a tocar
     * @param radio_online nome da rádio
     */
    public void setRadio_online(String radio_online) {
        this.radio_online = radio_online;
    }

    /**
     * Getter volume da coluna
     * @return int volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Setter volume da coluna
     * @param volume volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }


    //----------CONSTRUTORES----------

    /**
     * Criação do construtor vazio
     */
    public SmartSpeaker(){
        super();
        this.volume=0;
        this.radio_online="";
        this.marca="";
    }

    /**
     * Criação do construtor parametrizado
     * @param id id da coluna
     * @param vol volume da coluna
     * @param radio rádio que está a tocar
     * @param mar marca da coluna
     * @param custo_instal custo da coluna
     */
    public SmartSpeaker(String id,int vol, String radio, String mar, Float custo_instal){
        super(id, custo_instal);
        this.volume=vol;
        this.radio_online=radio;
        this.marca=mar;
    }

    /**
     * Construtor parametrizado
     * @param i Identificador
     * @param cond consumo diário
     * @param cont consumo total
     * @param instal custo de instalação
     * @param est estado atual do dispositivo
     * @param tempo tempo
     * @param vol volume da coluna
     * @param radio rádio a tocar
     * @param mar marca
     */
    public SmartSpeaker(String i, Float cond, Float cont,  Float instal, Estado est, LocalDateTime tempo, int vol, String radio, String mar){
        super(i,cond,cont,instal,est,tempo);
        this.volume=vol;
        this.radio_online=radio;
        this.marca=mar;
    }

    /**
     * Criação do construtor cópia
     * @param ss Objeto SmartSpeaker
     */
    public SmartSpeaker(SmartSpeaker ss){
        super(ss.getId(),ss.getConsumo_diario(),ss.getConsumo_total(),ss.getCusto_instal(),ss.getEstado(),ss.getTempo_de_consumo());
        this.volume=ss.getVolume();
        this.radio_online=ss.getRadio_online();
        this.marca=ss.getMarca();
    }

    //----------MÉTODOS----------
    //TODO Fazer isto
    //@Override
    /*
    public Float consumo_energia(){
        return null;
    }
    */

    //----------OVERRIDES----------
    //TODO Fazer isto

    /**
     * Método equals
     * @param o the reference object with which to compare.
     * @return True caso seja igual, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return Objects.equals(volume, that.volume) && Objects.equals(radio_online, that.radio_online) && Objects.equals(marca, that.marca);
    }

    /**
     * Método hashCOde
     * @return int hashCode correspondente
     */
    //TODO Fazer isto
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, radio_online, marca);
    }

    /**
     * Método toString
     * @return String do objeto SmartSpeaker
     */
    //TODO Fazer isto
    @Override
    public String toString() {
        return "SmartSpeaker:" + getVolume() + "," + getRadio_online() + "," + getMarca() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método toString alternativo
     * @return String do objeto SmartSpeaker
     */
    //TODO Fazer isto acho que não é preciso
    public String toStringw() {
        return "SmartSpeaker:" + getVolume() + "," + getRadio_online() + "," + getMarca() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método clone
     * @return objeto SmartSpeaker clonado
     */
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }

    /**
     * Método para devolver o tipo de SmartDevice
     * @return String com o tipo "SmartSpeaker"
     */
    @Override
    public String getTipo(){
        return "SmartSpeaker";
    }

}
