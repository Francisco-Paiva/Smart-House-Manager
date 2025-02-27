package model;

import model.Comercializador.Comercializador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Divisao implements Serializable {

    //----------VARIAVEIS----------
    private String nome;
    private  List<SmartDevice> listaDevices;



    //----------GETTERS/SETTERS----------

    /**
     * Getter do nome da divisão
     * @return String nome da divisão
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter do nome da divisão
     * @param nome nome da divisão
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter da lista de dispositivos da divisão
     * @return List de dispositivos
     */
    public List<SmartDevice> getListaDevices() {
        if (this.listaDevices.isEmpty()) return new ArrayList<>();
        List<SmartDevice> ret=new ArrayList<>();
        for (SmartDevice sd : this.listaDevices){
            ret.add(sd.clone());
        }
        return ret;
    }

    /**
     * Setter da lista de dispositivos da divisão
     * @param listaDevices lista de dispositivos
     */
    public void setListaDevices(List<SmartDevice> listaDevices) {
        this.listaDevices = new ArrayList<SmartDevice>();
        for(SmartDevice iteracao : listaDevices){
            this.listaDevices.add(iteracao.clone());
        }
    }

    //----------CONSTRUTORES----------

    /**
     * Contrutor vazio do objeto Divisao
     */
    public Divisao(){
        this.nome = "";
        this.listaDevices = new ArrayList<>();
    }

    /**
     * Contrutor do Objeto Divisao com o nome
     * @param nome nome da divisão
     */
    public Divisao(String nome) {
        this.nome = nome;
        this.listaDevices=new ArrayList<>();
    }

    /**
     * Construtor parametrizado do Objeto Divisao
     * @param nome Nome da divisão
     * @param listaDevices Lista de dispositivos
     */
    public Divisao(String nome, List<SmartDevice> listaDevices){
        this.nome = nome;
        this.listaDevices = new ArrayList<SmartDevice>();
        for(SmartDevice iteracao : listaDevices){
            this.listaDevices.add(iteracao.clone());
        }
    }

    /**
     * Construtor de cópia
     * @param div Objeto Divisao
     */
    public Divisao(Divisao div){
        this.nome = div.getNome();
        this.listaDevices = div.getListaDevices();
    }


    //----------Métodos---------

    /**
     * Método para adicionar um dispositivo à divisão
     * @param sd dispositivo a ser adicionado
     */
    public void adiciona_dispositivo(SmartDevice sd){
        List<SmartDevice> lista_sd=getListaDevices();
        lista_sd.add(sd);
        setListaDevices(lista_sd);
    }

    /**
     * Método para adicionar um dispositivo à divisão através dos seus parâmetros
     * @param dados dados do dispositivo
     * @param tipo tipo do dispositivo
     * @param id identificador do dispositivo
     */
    public void adiciona_dispositivo(String dados, String tipo,Integer id){
        List<SmartDevice> lista=getListaDevices();
        //dados[0]=consumo
        //dados[1]=custo_insta
        //dados[2]=estado
        //dados[3]=depende
        switch (tipo){
            case "1"://Model.SmartBulb
                String[] dados_sd=dados.split(";",5);
                lista.add(new SmartBulb(String.valueOf(id), Float.parseFloat(dados_sd[0]), 0.0f, Float.parseFloat(dados_sd[1]), SmartDevice.Estado.valueOf(dados_sd[2]), LocalDateTime.now(), SmartBulb.Tonalidade.valueOf(dados_sd[3]), Float.parseFloat(dados_sd[4])));
                break;
            case "2"://Model.SmartSpeaker
                dados_sd=dados.split(";",6);
                lista.add(new SmartSpeaker(String.valueOf(id), Float.parseFloat(dados_sd[0]), 0.0f, Float.parseFloat(dados_sd[1]), SmartDevice.Estado.valueOf(dados_sd[2]), LocalDateTime.now(), Integer.parseInt(dados_sd[3]),dados_sd[4],dados_sd[6]));
                break;
            case "3"://Model.SmartCamera
                dados_sd = dados.split(";", 3);
                lista.add(new SmartCamera(String.valueOf(id), Float.parseFloat(dados_sd[0]), 0.0f, Float.parseFloat(dados_sd[1]), SmartDevice.Estado.valueOf(dados_sd[2]), LocalDateTime.now(), dados_sd[3],Integer.parseInt(dados_sd[4])));
                break;
        }
        setListaDevices(lista);
    }

    /**
     * Método para mudar o estado dos dispositivos da divisão
     * @param estado estado objetivo
     */
    public void muda_estado_devices_divisao(int estado){
        List<SmartDevice> lista_nova_sd=this.getListaDevices();
        for ( SmartDevice sd : lista_nova_sd ){
            sd.muda_estado(estado);
        }
        setListaDevices(lista_nova_sd);
    }

    /**
     * Método para mudar de estado apenas um dispositivo de uma divisão
     * @param id identificador do dispositivo
     * @param estado Estado objetivo
     */
    public void muda_estado_device_unico(String id,int estado){
        List<SmartDevice> lista_nova_sd=this.getListaDevices();
        for ( SmartDevice sd : lista_nova_sd ){
            sd.muda_estado_device_unico(id,estado);
        }
        setListaDevices(lista_nova_sd);
    }

    /**
     * Método para calcular o consumo de energia de uma divisão
     * @return Float do consumo de energia
     */
    public Float calcula_consumo_divisao(){
        float total = 0;
        for(SmartDevice sd : this.listaDevices){
            total += sd.getConsumo_total();
        }
        return total;
    }

    /**
     * Método para simular uma viagem temporal
     * @param ldt Tempo a ser simulado
     * @param comercializador Comercializador
     */
    public void simula(LocalDateTime ldt, Comercializador comercializador, String nomeProp, Integer nifProp){
        for (SmartDevice sd : this.listaDevices){
            sd.simula(ldt, comercializador,nomeProp,nifProp);
        }
    }


    //----------FUNÇÕES AUXILIARES----------





    //----------OVERRIDES----------

    /**
     * Método equals do objeto Divisao
     * @param o Objeto
     * @return booleano T/F
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Divisao divisao = (Divisao) o;
        return Objects.equals(nome, divisao.nome) && Objects.equals(listaDevices, divisao.listaDevices);
    }

    /**
     * Método hashCode do objeto Divisão
     * @return int hashCode Correspondente
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, listaDevices);
    }

    /**
     * Método clone do objeto Divisão
     * @return objeto clonado
     */
    public Divisao clone(){
        return new Divisao(this);
    }

    /**
     * Método toString alternativo
     * @return String do objeto
     */
    public String toStringw(){
        StringBuilder stb=new StringBuilder();

        stb.append("Divisao:" + getNome() + "\n");
        for (SmartDevice sd : getListaDevices()){
            stb.append(sd.toStringw());
        }

        return stb.toString();
    }

    /**
     * Método toString
     * @return String do objeto
     */
    public String toString(){
        StringBuilder stb=new StringBuilder();

        stb.append("Divisao:" + getNome() + "\n");
        for (SmartDevice sd : getListaDevices()){
            stb.append(sd.toString());
        }

        return stb.toString();
    }


}
