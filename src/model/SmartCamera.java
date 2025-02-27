package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SmartCamera extends SmartDevice implements Serializable {

    //----------VARIAVEIS----------
    private String resolução;//megapixels
    private Integer tamanho;



    //----------GETTERS/SETTERS----------

    /**
     * Getter Tamanho do ficheiro que serão guardados eventos da SmartCamera
     * @return tamanho do ficheiro
     */
    public Integer getTamanho() {
        return tamanho;
    }

    /**
     * Setter Tamanho do ficheiro que serão guardados eventos da SmartCamera
     * @param tamanho tamanho do ficheiro
     */
    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Getter Resolução da SmartCamera
     * @return String resolução
     */
    public String getResolução() {
        return resolução;
    }

    /**
     * Setter Resolução da SmartCamera
     * @param resolução resolução
     */
    public void setResolução(String resolução) {
        this.resolução = resolução;
    }


    //----------CONSTRUTORES----------

    /**
     * Construtor vazio
     */
    public SmartCamera(){
        super();
        this.resolução="";
        this.tamanho=0;
    }

    /**
     * Construtor parametrizado
     * @param id Identificador
     * @param resol resolução
     * @param tam tamanho ficheiro
     * @param custo_instal custo de instalação
     */
    public SmartCamera(String id,String resol, Integer tam, Float custo_instal){
        super(id, custo_instal);
        this.resolução=resol;
        this.tamanho=tam;
    }

    /**
     * Construtor parametrizado
     * @param i Identificador
     * @param cond consumo diário
     * @param cont consumo total
     * @param instal custo instalação
     * @param est estado do dispositivo
     * @param tempo tempo
     * @param resol resolução
     * @param tam tamanho do ficheiro
     */
    public SmartCamera(String i, Float cond,Float cont, Float instal, Estado est, LocalDateTime tempo, String resol, Integer tam){
        super(i,cond,cont,instal,est,tempo);
        this.resolução=resol;
        this.tamanho=tam;
    }

    /**
     * Construtor de cópia
     * @param sc SmartCamera
     */
    public SmartCamera(SmartCamera sc){
        super(sc.getId(),sc.getConsumo_diario(),sc.getConsumo_total(),sc.getCusto_instal(),sc.getEstado(),sc.getTempo_de_consumo());
        this.resolução=sc.getResolução();
        this.tamanho=sc.getTamanho();
    }

    //----------MÉTODOS----------

    //----------OVERRIDES----------

    /**
     * Método equals
     * @param o   the reference object with which to compare.
     * @return boolean True caso seja igual, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartCamera that = (SmartCamera) o;
        return Objects.equals(resolução, that.resolução) && Objects.equals(tamanho, that.tamanho);
    }

    /**
     * Método hashCode
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), resolução, tamanho);
    }

    /**
     * Método toString
     * @return String com o objeto SmartCamera
     */
    @Override
    public String toString() {
        return "SmartCamera:" + getResolução() + "," + getTamanho() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método toString alternativo
     * @return String com o objeto SmartCamera
     */
    public String toStringw() {
        return "SmartCamera:" + getResolução() + "," + getTamanho() + "," + getConsumo_diario() + "\n";
    }

    /**
     * Método clone
     * @return objeto clonado
     */
    @Override
    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    /**
     * Método para devolver o tipo de SmartDevice
     * @return String com o tipo "SmartCamera"
     */
    @Override
    public String getTipo(){
        return "SmartCamera";
    }
}
