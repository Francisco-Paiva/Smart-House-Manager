package model;

import model.Comercializador.Comercializador;
import model.Comercializador.FornecedorA;
import model.Comercializador.FornecedorB;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Casa implements Comparable<Casa>, Serializable {

    //----------VARIAVEIS----------
    private String proprietario_nome;
    private Integer proprietario_nif;
    private Comercializador comercia;
    private List<Divisao> lista_divisao;

    //----------GETTERS/SETTERS----------

    /**
     * Getter do nome do proprietário da casa
     * @return String com o nome do proprietário
     */
    public String getProprietario_nome() {
        return proprietario_nome;
    }

    /**
     * Setter do nome do proprietário da casa
     * @param proprietario_nome String nome do proprietário
     */
    public void setProprietario_nome(String proprietario_nome) {
        this.proprietario_nome = proprietario_nome;
    }

    /**
     * Getter do nif do proprietário
     * @return int nif do priprietário
     */
    public Integer getProprietario_nif() {
        return proprietario_nif;
    }

    /**
     * Setter do nif do proprietário da casa
     * @param proprietario_nif nif do proprietário
     */
    public void setProprietario_nif(Integer proprietario_nif) {
        this.proprietario_nif = proprietario_nif;
    }

    /**
     * Getter do Comercializador
     * @return objeto Comercializador
     */
    public Comercializador getComercia() {
        return comercia.clone();
    }

    /**
     * Setter do Comercializador
     * @param comercia objeto Comercializador
     */
    public void setComercia(Comercializador comercia) {
        this.comercia = comercia;
    }

    /**
     * Getter da lista de divisões da casa
     * @return List diviões da casa
     */
    public List<Divisao> getLista_divisao() {
        List<Divisao> ret=new ArrayList<>();
        for (Divisao div : this.lista_divisao){
            ret.add(div.clone());
        }
        return ret;
    }

    /**
     * Setter da lista de divisões da casa
     * @param lista_divisao Lista com as divisões da casa
     */
    public void setLista_divisao(List<Divisao> lista_divisao) {
        this.lista_divisao = lista_divisao;
    }

    //----------CONSTRUTORES----------

    /**
     * Construtor vazio do objeto Casa
     */
    public Casa() {
        this.proprietario_nome = "";
        this.proprietario_nif = -1;
        Random random=new Random();
        if (random.nextInt(2) == 0){
            this.comercia = new FornecedorA();
        }
        else {
            this.comercia = new FornecedorB();
        }
        this.lista_divisao = new ArrayList<>();
    }


    /**
     * Construtor parametrizado do objeto Casa
     * @param lista_devices lista de dispositivos
     * @param map_divisoes mapa das divisões e dos seus dispositivos
     * @param nome_proprietario nome do proprietário
     * @param nif_proprietario nif do proprietário
     * @param comercia Comercializador
     * @param lista_divisao lista de divisões da casa
     */
    public Casa(List<SmartDevice> lista_devices, Map<Divisao, List<SmartDevice>> map_divisoes,
                String nome_proprietario, Integer nif_proprietario, Comercializador comercia, List<Divisao> lista_divisao) {
//        this.lista_devices = new ArrayList<>();
//        for(Model.SmartDevice lista : lista_devices){
//            this.lista_devices.add(lista.clone());
//        }
//
//        this.map_divisoes = new HashMap<>();
//        for(Map.Entry<Model.Divisao,List<Model.SmartDevice>> entry : map_divisoes.entrySet()){
//            List<Model.SmartDevice> aux = new ArrayList<>();
//            for(List<Model.SmartDevice> lista : map_divisoes.values()){
//                aux.addAll(lista);
//            }
//            this.map_divisoes.put(entry.getKey(), aux);
//        }
        this.lista_divisao = new ArrayList<>();
        for (Divisao lista : lista_divisao) {
            this.lista_divisao.add(lista.clone());
        }
        this.proprietario_nome = nome_proprietario;
        this.proprietario_nif = nif_proprietario;
        this.comercia = comercia;

    }

    /**
     * Construtor do objeto Casa com apenas estes 3 argumentos
     * @param nome nome do proprietário da casa
     * @param nif nif do proprietário da casa
     * @param comer Comercializador
     */
    public Casa(String nome, Integer nif, Comercializador comer) {
        this.proprietario_nome = nome;
        this.proprietario_nif = nif;
        this.comercia = comer;
        this.lista_divisao = new ArrayList<>();
    }

    /**
     * Construtor do objeto Casa com os seguintes 4 argumentos
     * @param nome nome do proprietário da casa
     * @param nif nif do proprietário da casa
     * @param comer Comercializador
     * @param lista_divisao Lista de divisões da casa
     */
    public Casa(String nome, Integer nif, Comercializador comer, String lista_divisao) {
        this.proprietario_nome = nome;
        this.proprietario_nif = nif;
        this.comercia = comer;
        this.lista_divisao = this.getLista_divisao_String(lista_divisao);
    }

    /**
     * Construtor de cópia do objeto Casa
     * @param c objeto Casa a ser copiado
     */
    public Casa(Casa c) {
        this.proprietario_nome = c.getProprietario_nome();
        this.proprietario_nif = c.getProprietario_nif();
        this.comercia = c.getComercia();
        this.lista_divisao = c.getLista_divisao();
    }

    //----------MÉTODOS---------

    /**
     * Método para adicionar uma divisão a uma casa
     * @param div divisão a ser adicionada
     */
    public void adiciona_divisao(Divisao div){
        //if (){}  -> verificar se já existe
        List<Divisao> lista_div=getLista_divisao();
        lista_div.add(div);
        setLista_divisao(lista_div);
    }

    /**
     * Método para adicionar um dispositivo à Casa
     * @param dados_sd Dados do Smart Device (dispositivo)
     * @param tipo Tipo do dispositivo
     * @param id id do dispositivo
     */
    public void adiciona_device(String dados_sd, String tipo,Integer id){
        String[] str=dados_sd.split(";",2);
        //str[0] = nome da divisao
        //str[1] = resto dos dados
        List<Divisao> lista=new ArrayList<>();
        for (Divisao div : this.lista_divisao){
            if (div.getNome().equals(str[0])){
                String[] dados_s;
                SmartDevice sd=null;
                switch (tipo){
                    case "1"://SmartBulb
                        dados_s=str[1].split(";");
                        sd=new SmartBulb(String.valueOf(id), Float.parseFloat(dados_s[0]), 0.0f, Float.parseFloat(dados_s[1]), SmartDevice.Estado.valueOf(dados_s[2]), LocalDateTime.now(), SmartBulb.Tonalidade.valueOf(dados_s[3]), Float.parseFloat(dados_s[4]));
                        break;
                    case "2"://SmartSpeaker
                        dados_s=str[1].split(";");
                        sd=new SmartSpeaker(String.valueOf(id), Float.parseFloat(dados_s[0]), 0.0f, Float.parseFloat(dados_s[1]), SmartDevice.Estado.valueOf(dados_s[2]), LocalDateTime.now(), Integer.parseInt(dados_s[3]),dados_s[4],dados_s[5]);
                        break;
                    case "3"://SmartCamera
                        dados_s = str[1].split(";");
                        sd=new SmartCamera(String.valueOf(id), Float.parseFloat(dados_s[0]), 0.0f, Float.parseFloat(dados_s[1]), SmartDevice.Estado.valueOf(dados_s[2]), LocalDateTime.now(), dados_s[3],Integer.parseInt(dados_s[4]));
                        break;
                }
                div.adiciona_dispositivo(sd);
            }
            lista.add(div.clone());
        }
        setLista_divisao(lista);
    }

    /**
     * Método para mudar o estado de todos os dispositivos da casa
     * @param estado estado final dos dispositvos
     */
    public void muda_estado_devices_todos(int estado) {
        List<Divisao> lista_nova = getLista_divisao();
        for (Divisao div : lista_nova){
            div.muda_estado_devices_divisao(estado);
        }
        setLista_divisao(lista_nova);
    }

    /**
     * Método para mudar o estado de todos os dispositivos de uma divisão da casa
     * @param div divisão da casa
     * @param estado estado final dos dispositvos
     */
    public void muda_estado_devices_divisao(String div,int estado){
        List<Divisao> lista_div = getLista_divisao();
        for ( Divisao divisao : lista_div ){
            if (divisao.getNome().equals(div)){
                divisao.muda_estado_devices_divisao(estado);
            }
        }
        setLista_divisao(lista_div);
    }

    /**
     * Método para alterar o estado de um dispositivo da casa
     * @param id identificador do dispositivo
     * @param estado estado final do dispositivo
     */
    public void muda_estado_device_unico(String id,int estado){
        List<Divisao> lista_div = getLista_divisao();
        for ( Divisao div : lista_div ){
            div.muda_estado_device_unico(id,estado);
        }
        setLista_divisao(lista_div);
    }

    /**
     * Método para calcular o consumo de energia de uma casa
     * @return Float com o valor total do consumo
     */
    public Float calcula_consumo_casa(){
        float total = 0;
        for (Divisao div : getLista_divisao()){
            for(SmartDevice list : div.getListaDevices()){
                total += list.getConsumo_total();
            }
        }
        return total;
    }

    /**
     * Método para calcular o consumo de energia da casa num intervalo de tempo
     * @param start início do intervalo de tempo
     * @param end final do intervalo de tempo
     * @return Float do valor do consumo de energia
     */
    public Float calcula_consumo_casa(LocalDateTime start, LocalDateTime end){
        float total = 0;
        for (Divisao div : getLista_divisao()){
            for(SmartDevice smartDevice : div.getListaDevices()) {
                for(Map.Entry<LocalDateTime, Float> entry : smartDevice.getConsumos().entrySet()) {
                    if(start.isBefore(entry.getKey()) && end.isBefore(entry.getKey())) {
                        total += entry.getValue();
                    }
                }
            }
        }
        return total;
    }

    /**
     * Método para retornar todos os dispositivos da casa
     * @return List dos dispositivos da casa
     */
    public List<SmartDevice> get_all_devices_casa(){
        List<SmartDevice> lista=new ArrayList<>();
        for (Divisao div : getLista_divisao()){
            for (SmartDevice sd: div.getListaDevices()){
                lista.add(sd.clone());
            }
        }
        return lista;
    }

    /**
     * Simula uma viagem no tempo para a casa
     * @param ldt tempo a ser simulado
     * @param comercializador Comercializador
     */
    public void simula(LocalDateTime ldt, Comercializador comercializador){
        for (Divisao div : this.lista_divisao)
            div.simula(ldt, comercializador,getProprietario_nome(),getProprietario_nif());
    }

    //----------FUNÇÕES AUXILIARES----------

    /**
     * Método para através de uma string com as divisões devolver a Lista de divisões
     * @param divisoes String com as divisões
     * @return List das divisões
     */

    private List<Divisao> getLista_divisao_String(String divisoes){
        String[] conf=divisoes.split(":",2);
        if (!conf[0].equals("DIVISÕES")){
            return null;
        }
        List<Divisao> lista_divs=new ArrayList<>();
        String[] divs=conf[1].split("@");
        for (String div_toda : divs){
            String[] nome_div=div_toda.split(":",2);
            String[] all_devs=nome_div[1].split("%");
            List<SmartDevice> lista_sd=new ArrayList<>();
            for (String tipo : all_devs){
                String[] device=tipo.split(":",2);
                if (device[0].equals("SmartSpeaker")){
                    String[] vars=device[1].split("#",8);
                    lista_sd.add(
                            new SmartSpeaker(
                                    vars[0],
                                    Float.parseFloat(vars[1]),
                                    Float.parseFloat(vars[1]),

                                    Float.parseFloat(vars[2]),
                                    get_Estado_by_String(vars[3]),
                                    LocalDateTime.parse(vars[4]),
                                    Integer.parseInt(vars[5]),
                                    vars[6],
                                    vars[7]
                            )
                    );
                }
                else if (device[0].equals("SmartBulb")){
                    String[] vars=device[1].split("#",7);
                    String[] dim=vars[6].split("x");
                    lista_sd.add(
                            new SmartBulb(
                                    vars[0],
                                    Float.parseFloat(vars[1]),
                                    Float.parseFloat(vars[1]),
                                    Float.parseFloat(vars[2]),
                                    get_Estado_by_String(vars[3]),
                                    LocalDateTime.parse(vars[4]),
                                    SmartBulb.Tonalidade.valueOf(vars[5]),
                                    Float.parseFloat(vars[6])
                            )
                    );
                }
                else if (device[0].equals("SmartCamera")){
                    String[] vars=device[1].split("#",7);
                    lista_sd.add(
                            new SmartCamera(
                                    vars[0],
                                    Float.parseFloat(vars[1]),
                                    Float.parseFloat(vars[1]),

                                    Float.parseFloat(vars[2]),
                                    get_Estado_by_String(vars[3]),
                                    LocalDateTime.parse(vars[4]),
                                    vars[5],
                                    Integer.parseInt(vars[6])
                            )
                    );
                }
            }
            Divisao di=new Divisao(nome_div[0],lista_sd);
            lista_divs.add(di);
        }
        return lista_divs;
    }

    /**
     * Método para através da string do estado devolver o estado do objeto SmartDevice
     * @param estado String do estado
     * @return Estado do dispositivo
     */
    private SmartDevice.Estado get_Estado_by_String(String estado){
        if (estado.equals("ON") || estado.equals("On") || estado.equals("on") ){
            return SmartDevice.Estado.ON;
        }
        else if (estado.equals("OFF") || estado.equals("Off") || estado.equals("off") || estado.equals("OFf")){
            return SmartDevice.Estado.OFF;
        }
        return null;
    }



    //----------OVERRIDES----------

    /**
     * Método toString para o objeto Casa
     * @return String do objeto Casa
     */
    @Override
    public String toString() {
        StringBuilder stb=new StringBuilder();
        stb.append("Casa:" + getProprietario_nome() + "," + getProprietario_nif() + "," + getComercia().getNome() + "\n");
        for (Divisao div: getLista_divisao()){
            stb.append(div.toString());

        }
        return stb.toString();
    }

    /**
     * Método toString alternativo
     * @return String do objeto Casa
     */
    public String toStringw() {
        StringBuilder stb=new StringBuilder();
        stb.append("Casa:" + getProprietario_nome() + "," + getProprietario_nif() + "," + getComercia().getNome() + "\n");
        for (Divisao div: getLista_divisao()){
            stb.append(div.toStringw());

        }
        return stb.toString();
    }

    /**
     * Método clone do objeto Casa
     * @return objeto Casa clonado
     */
    public Casa clone(){
        return new Casa(this);
    }

    /**
     * Método compareTo do objeto Casa
     * @param casa the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Casa casa) {
        return this.calcula_consumo_casa().compareTo(casa.calcula_consumo_casa());
    }
}