package model;

import model.Comercializador.Comercializador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public abstract class SmartDevice implements Comparator, Serializable {

    /**
     * Enum dos estados possíveis de um dispositivo
     */
    public enum Estado{
        ON,
        OFF
    }
    //----------VARIAVEIS----------

    private String id;
    private Float consumo_diario;
    private Float consumo_total;
    private Float custo_instal;
    private Estado estado;//alterar estado de int para outra cena
    private Map<LocalDateTime, Float> consumos;
    private LocalDateTime tempo_de_consumo;

    //----------GETTERS/SETTERS----------

    /**
     * Getter custo de instalação de um dispostivo
     * @return Float custo
     */
    public Float getCusto_instal() {
        return custo_instal;
    }
    /**
     * Setter custo de instalação de um dispostivo
     * @param custo_instal custo
     */
    public void setCusto_instal(Float custo_instal) {
        this.custo_instal = custo_instal;
    }

    /**
     * Getter estado do dispostivo
     * @return estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Setter estado do dispostivo
     * @param estado estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Getter do consumo diário de um dispositvo
     * @return consumo diário
     */
    public Float getConsumo_diario() {
        return consumo_diario;
    }

    /**
     * Setter do consumo diário de um dispositvo
     * @param consumod consumo diário
     */
    public void setConsumo_diario(Float consumod) {
        this.consumo_diario = consumod;
    }

    /**
     * Getter do consumo total de um dispositvo
     * @return consumo total
     */
    public Float getConsumo_total() {
        return consumo_total;
    }

    /**
     * Setter do consumo total de um dispositvo
     * @param consumot consumo total
     */
    public void setConsumo_total(Float consumot) {
        this.consumo_total = consumot;
    }

    /**
     * Getter id do dispositvo
     * @return Identificador
     */
    public String getId() {
        return id;
    }

    /**
     * Setter id do dispostivo
     * @param id Identificador
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Getter Map dos consumos consoante um certo tempo
     * @return Mapa <tempo,consumo>
     */
    public Map<LocalDateTime, Float> getConsumos() {
        Map<LocalDateTime,Float> cons=new TreeMap<>();
        for (Map.Entry<LocalDateTime,Float> entry : this.consumos.entrySet()){
            cons.put(entry.getKey(), entry.getValue());
        }
        return cons;
    }

    /**
     * Setter Map dos consumos consoante um certo tempo
     * @param consumos Mapa <tempo,consumo>
     */
    public void setConsumos(Map<LocalDateTime, Float> consumos) {
        this.consumos = new TreeMap<>();
        for(Map.Entry<LocalDateTime,Float> entry : consumos.entrySet()){
            this.consumos.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Getter tempo de consumo
     * @return tempo
     */
    public LocalDateTime getTempo_de_consumo() {
        return tempo_de_consumo;
    }

    /**
     * Setter tempo de consumo
     * @param tempo_de_consumo tempo
     */
    public void setTempo_de_consumo(LocalDateTime tempo_de_consumo) {
        this.tempo_de_consumo = tempo_de_consumo;
    }

    //----------CONSTRUTORES----------

    /**
     * Construtor vazio
     */
    public SmartDevice(){
        this.id="";
        this.consumo_diario=Float.parseFloat("0");
        this.consumo_total=Float.parseFloat("0");
        this.custo_instal=Float.parseFloat("0");
        this.estado=Estado.OFF;
        this.tempo_de_consumo=LocalDateTime.now();
        this.consumos = new TreeMap<>();

    }

    /**
     * Construtor do SmartDevice apenas com o identificador
     * @param id Identificador
     */
    public SmartDevice(String id){
        this.id=id;
        this.consumo_diario=Float.parseFloat("0");
        this.consumo_total=Float.parseFloat("0");
        this.custo_instal=Float.parseFloat("0");
        this.estado=Estado.OFF;
        this.tempo_de_consumo=LocalDateTime.now();
        this.consumos = new TreeMap<>();
    }

    /**
     * Construtor do SmartDevice com o Identificador e o consumo di´ário
     * @param id Identificador
     * @param consumoDiario consumo diário
     */
    public SmartDevice(String id, Float consumoDiario){
        this.id=id;
        this.consumo_diario=consumoDiario;
        this.consumo_total=Float.parseFloat("0");
        this.custo_instal=Float.parseFloat("0");
        this.estado=Estado.OFF;
        this.tempo_de_consumo=LocalDateTime.now();
        this.consumos = new TreeMap<>();
    }

    /**
     * Construtor parametrizado
     * @param i Identificador
     * @param cond consumo di´ário
     * @param cont consumo total
     * @param instal custo instalação
     * @param est estado
     * @param tempo tempo
     */
    public SmartDevice(String i, Float cond, Float cont, Float instal, Estado est, LocalDateTime tempo ){
        this.id=i;
        this.consumo_diario=cond;
        this.consumo_total=cont;
        this.custo_instal=instal;
        this.estado=est;
        this.tempo_de_consumo=tempo;
        this.consumos = new TreeMap<>();
    }

    /**
     * Construtor de cópia
     * @param sd SmartDevice
     */
    public SmartDevice(SmartDevice sd){
        this.id=sd.getId();
        this.consumo_diario= sd.getConsumo_diario();
        this.consumo_total= sd.getConsumo_total();
        this.custo_instal=sd.getCusto_instal();
        this.estado=sd.getEstado();
        this.tempo_de_consumo=sd.getTempo_de_consumo();
        this.consumos = sd.getConsumos();
    }

    //----------MÉTODOS----------

    /**
     * Método para receber uma String correspondente ao estado
     * @return String ON/OFF
     */
    public String getString_by_estado(){
        if (this.getEstado().equals(Estado.ON)){
            return "ON";
        }
        else if (this.getEstado().equals(Estado.OFF)){
            return "OFF";
        }
        return null;
    }

    /**
     * Método para alterar o estado de um dispositivo
     * @param estado
     */
    public void muda_estado(int estado){
        if(estado==1){
            this.setEstado(Estado.ON);
        }
        else if(estado==0){
            this.setEstado(Estado.OFF);
        }
    }

    /**
     * Método para alterar o estado de um dispositivo através do seu identificador
     * @param id Identificador
     * @param estado Estado objetivo
     */
    public void muda_estado_device_unico(String id, int estado){
        if (getId().equals(id)){
            this.muda_estado(estado);
        }
    }

    /**
     * Método para simular uma viagem no tempo
     * @param ldt tempo
     * @param comercializador Comercializador
     * @param nomeProp Nome do proprietário da casa
     * @param nifProp Nif do proprietário da casa
     */
    public void simula(LocalDateTime ldt, Comercializador comercializador, String nomeProp, Integer nifProp){
        if (getEstado().equals(Estado.ON)){
            /*
            LocalDateTime ldtFat = LocalDateTime.of(
                    ldt.getYear()-getTempo_de_consumo().getYear(),
                    ldt.getMonthValue()-getTempo_de_consumo().getMonthValue(),
                    ldt.getDayOfMonth()-getTempo_de_consumo().getDayOfMonth(),
                    ldt.getHour()-getTempo_de_consumo().getHour(),
                    ldt.getMinute()-getTempo_de_consumo().getMinute(),
                    ldt.getSecond()-getTempo_de_consumo().getSecond(),
                    ldt.getNano()-getTempo_de_consumo().getNano()
            );*/

            Float mes = Float.valueOf((ldt.getDayOfYear() - getTempo_de_consumo().getDayOfYear())/30);
            Float hora = Float.valueOf((ldt.getHour()-getTempo_de_consumo().getHour())/(30*24));
            Float minutos = Float.valueOf((ldt.getMinute()-getTempo_de_consumo().getMinute())/(30*24*60));
            Float segundos = Float.valueOf((ldt.getSecond()-getTempo_de_consumo().getSecond())/(30*24*60*60));
            Float soma=Math.abs(mes+hora+minutos+segundos);

            Float consumo=soma*getConsumo_diario();
            Float precoTotal=soma*comercializador.precoDiaPorDispositivo();
            setConsumo_total(getConsumo_total() + (float) precoTotal);
            Fatura f=new Fatura(nifProp,nomeProp,comercializador.getNome(),ldt,precoTotal,consumo);
            comercializador.adicionaFatura(f);
            setTempo_de_consumo(ldt);
            this.consumos.put(ldt,precoTotal);
        }
    }

    //----------FUNÇÕES AUXILIARES----------



    /**
     * Método compare
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return int 1 caso sejam iguais, 0 caso contrário
     */
    @Override
    public int compare(Object o1, Object o2) {
        SmartDevice sd1=(SmartDevice) o1;
        SmartDevice sd2=(SmartDevice) o2;
        if (sd1.getId().equals(sd2.getId())){
            return 1;
        }
        return 0;
    }

    //----------OVERRIDES----------

    /**
     * Método equals
     * @param o the reference object with which to compare.
     * @return true caso seja igual, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice that = (SmartDevice) o;
        return Objects.equals(id, that.id) && Objects.equals(consumo_diario, that.consumo_diario) && estado == that.estado && Objects.equals(custo_instal, that.custo_instal) && Objects.equals(tempo_de_consumo, that.tempo_de_consumo);
    }

    /**
     * Método hashCode
     * @return int respetivo hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, consumo_diario, estado, custo_instal, tempo_de_consumo);
    }

    @Override
    public abstract String toString();

    /**
     * Método toString do objeto SmartDevice
     * @return String do objeto SmartDevice
     */
    public String toStringw() {
        return "SmartDevice:" + getId() + ";" + getConsumo_diario() + ";" + getConsumo_total() + ";" +
                getCusto_instal() + ";" + getEstado() + ";" + getTempo_de_consumo() + "\n";
    }

    /**
     * Método clone
     * @return objeto SmartDevice clonado
     */
    public abstract SmartDevice clone();

    /**
     * Método para devolver o tipo
     * @return string "SmartDevice"
     */
    public String getTipo(){
        return "SmartDevice";
    }
}
