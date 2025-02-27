package model;

import Excecoes.FornecedorNaoExisteException;
import model.Comercializador.Comercializador;
import model.Comercializador.FornecedorA;
import model.Comercializador.FornecedorB;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Model implements Serializable{


    //----------VARIAVEIS----------
    private static Integer ID_CURR;
    private LocalDateTime tempo;
    private List<Comercializador> lista_comercial;
    private List<Casa> lista_casas;

    //----------GETTERS/SETTERS----------

    /**
     * Getter Id atual
     * @return int Identificador
     */
    public Integer getID_CURR() {
        return this.ID_CURR;
    }

    /**
     * Setter id atual
     * @param id Identificador
     */
    public void setID_CURR(Integer id) {
        this.ID_CURR = id;
    }

    /**
     * Getter tempo
     * @return tempo
     */
    public LocalDateTime getTempo() {
        return tempo;
    }

    /**
     * Setter tempo
     * @param tempo
     */
    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }

    /**
     * Getter lista comercializadores
     * @return Lista de comercializadores
     */
    public List<Comercializador> getLista_comercial() {
        List<Comercializador> lista_nova=new ArrayList<>();
        for (Comercializador comer : this.lista_comercial){
            lista_nova.add(comer.clone());
        }
        return lista_nova;
    }

    /**
     * Setter da lista de comercializadores
     * @param lista_comercial
     */
    public void setLista_comercial(List<Comercializador> lista_comercial) {
        this.lista_comercial = lista_comercial;
    }

    /**
     * Getter da lista de casas
     * @return Lista de casas
     */
    public List<Casa> getLista_casas() {
        List<Casa> ret=new ArrayList<>();
        for (Casa casa : this.lista_casas){
            ret.add(casa.clone());
        }
        return ret;
    }

    /**
     * Setter da lista de casas
     * @param lista_casas lita de casas
     */
    public void setLista_casas(List<Casa> lista_casas) {
        this.lista_casas = lista_casas;
    }

    //----------CONSTRUTORES----------

    /**
     * Construtor vazio objeto Model
     */
    public Model(){
        this.tempo = LocalDateTime.now();
        this.lista_comercial=new ArrayList<>();
        this.lista_casas = new ArrayList<>();
        this.ID_CURR=0;
    }

    //----------MÉTODOS----------

    /**
     * Método para carregar um ficheiro txt
     * @param nome_ficheiro nome do ficheiro
     * @return Retorna true se conseguiu carregar o ficheiro
     */
    public boolean carregar_ficheiro_txt(String nome_ficheiro){
        List<String> linhas = lerFicheiro(nome_ficheiro);
        Casa ultima_CASA=null;
        Divisao ultima_DIV=null;
        Comercializador lastComer=null;
        Random random = new Random();
        this.setTempo(LocalDateTime.now());
        for (String linha: linhas){
            String[] linhapartida=linha.split(":",2);
            switch (linhapartida[0]){
                case "SmartBulb":
                    String[] variaveis_partidas=linhapartida[1].split(",");
                    /*** Criaçao de atributos para os sd's ***/
                    SmartDevice.Estado estado;
                    if (random.nextInt(2)==0){
                        estado = SmartDevice.Estado.OFF;
                    }
                    else {
                        estado = SmartDevice.Estado.ON;
                    }
                    Float custoInstala = random.nextFloat();
                    custoInstala += random.nextInt(2) + 1;//1 um preço minimo e pode variar 3 um
                    int ano=random.nextInt(32) + 1990;
                    int mes=random.nextInt(12)+1;
                    int diadomes=random.nextInt(28)+1;
                    int hora=random.nextInt(24);
                    int minuto=random.nextInt(60);
                    LocalDateTime ldt = LocalDateTime.of(ano,mes,diadomes,hora,minuto);
                    SmartBulb sb=new SmartBulb(String.valueOf(getID_CURR()), Float.parseFloat(variaveis_partidas[2]), 0.0f, custoInstala, estado, ldt, SmartBulb.Tonalidade.valueOf(variaveis_partidas[0]), Float.parseFloat(variaveis_partidas[1]));
                    ultima_DIV.adiciona_dispositivo(sb);
                    setID_CURR(getID_CURR()+1);
                    break;
                case "SmartCamera":
                    variaveis_partidas=linhapartida[1].split(",");
                    /*** Criaçao de atributos para os sd's ***/
                    if (random.nextInt(2)==0){
                        estado = SmartDevice.Estado.OFF;
                    }
                    else {
                        estado = SmartDevice.Estado.ON;
                    }
                    custoInstala = random.nextFloat();
                    custoInstala += random.nextInt(2) + 1;//1 um preço minimo e pode variar 3 um

                    ano=random.nextInt(32) + 1990;
                    mes=random.nextInt(12)+1;
                    diadomes=random.nextInt(28)+1;
                    hora=random.nextInt(24);
                    minuto=random.nextInt(60);

                    ldt = LocalDateTime.of(ano,mes,diadomes,hora,minuto);

                    SmartCamera sc=new SmartCamera(String.valueOf(getID_CURR()), Float.parseFloat(variaveis_partidas[2]), 0.0f, custoInstala, estado, ldt, variaveis_partidas[0], Integer.parseInt(variaveis_partidas[1]));
                    ultima_DIV.adiciona_dispositivo(sc);
                    setID_CURR(getID_CURR()+1);
                    break;
                case "SmartSpeaker":
                    variaveis_partidas=linhapartida[1].split(",");
                    /*** Criaçao de atributos para os sd's ***/
                    if (random.nextInt(2)==0){
                        estado = SmartDevice.Estado.OFF;
                    }
                    else {
                        estado = SmartDevice.Estado.ON;
                    }
                    custoInstala = random.nextFloat() + random.nextInt(2) + 1;//1 um preço minimo e pode variar 3 um
                    ano=random.nextInt(32) + 1990;
                    mes=random.nextInt(12)+1;
                    diadomes=random.nextInt(28)+1;
                    hora=random.nextInt(24);
                    minuto=random.nextInt(60);
                    ldt = LocalDateTime.of(ano,mes,diadomes,hora,minuto);

                    SmartSpeaker ss=new SmartSpeaker(String.valueOf(getID_CURR()), Float.parseFloat(variaveis_partidas[3]), 0.0f, custoInstala, estado, ldt, Integer.parseInt(variaveis_partidas[0]), variaveis_partidas[1], variaveis_partidas[2]);
                    ultima_DIV.adiciona_dispositivo(ss);
                    setID_CURR(getID_CURR()+1);
                    break;
                case "Fornecedor":
                    variaveis_partidas=linhapartida[1].split(",");
                    Float precoComer=random.nextFloat() + random.nextInt(21) + 30;//30 um é o preço minimo e pode variar 20 um
                    Float imposto=random.nextFloat();
                    while (imposto > 0.5) {
                        imposto = imposto / 2;
                    }
                    Comercializador comer;
                    if (random.nextInt(2)==0){
                        comer=new FornecedorA(
                                variaveis_partidas[0],
                                precoComer,
                                imposto
                        );
                    }
                    else {
                        comer=new FornecedorB(
                                variaveis_partidas[0],
                                precoComer,
                                imposto
                        );
                    }

                    this.lista_comercial.add(comer);
                    lastComer=comer;
                    break;
                case "Divisao":
                    ultima_DIV=new Divisao(linhapartida[1]);
                    ultima_CASA.adiciona_divisao(ultima_DIV);
                    break;
                case "Casa":
                    variaveis_partidas=linhapartida[1].split(",");
                    try {
                        comer=getComercial_String(variaveis_partidas[2]);
                    } catch (FornecedorNaoExisteException e) {
                        break;
                    }
                    ultima_CASA=new Casa(
                            variaveis_partidas[0],//nome do Proprietario
                            Integer.parseInt(variaveis_partidas[1]),//nif do Proprietario
                            comer//Fornecedor
                    );
                    this.lista_casas.add(ultima_CASA);
                    break;
                case "Fatura":
                    variaveis_partidas=linhapartida[1].split(",");
                    lastComer.adicionaFatura(
                            new Fatura(
                                    Integer.parseInt(variaveis_partidas[0]),
                                    variaveis_partidas[1],
                                    variaveis_partidas[2],
                                    LocalDateTime.parse(variaveis_partidas[3]),
                                    Float.parseFloat(variaveis_partidas[4]),
                                    Float.parseFloat(variaveis_partidas[5])
                            )
                    );
                    break;
                default:
                    System.out.println("ERRO");
                    break;
            }
        }
        return true;
    }

    /**
     * Método para escrever um ficheiro txt com os objetos atuais
     * @param nome_ficheiro nome do ficheiro
     * @return Retorna true após as escrita do ficheiro
     * @throws FileNotFoundException Ficheiro não encontrado
     */
    public boolean escrever_ficheiro_txt(String nome_ficheiro) throws FileNotFoundException {
        PrintWriter pw=new PrintWriter(nome_ficheiro);
        pw.print(this.toStringw());
        pw.flush();
        return true;
    }


    /**
     * Método para carregar ficheiro com os objetos
     * @param nome_ficheiro nome do ficheiro
     * @return Retorna um objeto Model com a informação
     * @throws IOException Exceção IO
     * @throws ClassNotFoundException Exceção quando uma classe não existe
     */
    public Model carregar_ficheiro_obj(String nome_ficheiro) throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream(nome_ficheiro);
        ObjectInputStream ois=new ObjectInputStream(fis);
        Model info = (Model) ois.readObject();
        ois.close();
        return info;
    }

    /**
     * Método para escrever um ficheiro com objetos
     * @param nome_ficheiro nome do ficheiro
     * @return Retorna true caso tenha escrito toda a informação pretendida
     * @throws IOException Exceção IO
     */
    public boolean escrever_ficheiro_obj(String nome_ficheiro) throws IOException {
        FileOutputStream fos=new FileOutputStream(nome_ficheiro);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        //return "Writing Completo";
        return true;
    }

    /*
    public Map<Comercializador,List<Fatura>> getFaturas(){
        Map<Comercializador,List<Fatura>> map=new TreeMap<>();
        for (Comercializador comer : getLista_comercial()){
            /*if (!contemComer(map,comer)){
                map.put(comer.clone(),new ArrayList<>());
            }
            for (Fatura f : comer.getLista_faturas()){
                map.get(comer).add(f.clone());
            }*
            List<Fatura> fat=new ArrayList<>();
            for (Fatura f : comer.getLista_faturas()){
                fat.add(f.clone());
            }
            map.put(comer.clone(),fat);
        }
        return map;
    }

    private boolean contemComer(Map<Comercializador,List<Fatura>> mapa,Comercializador comer){
        for (Map.Entry<Comercializador,List<Fatura>> entry : mapa.entrySet()){
            Comercializador c = entry.getKey();
            if (c.equals(comer)){
                return true;
            }
        }
        return false;
    }
    */


    // Funcoes de adicionar elementos

    /**
     * Método para adicionar um SmartDevice
     * @param str String que contém os dados
     * @return booleano true caso consiga adicionar
     */
    public Boolean adicionar_sd(String str){
        String[] string=str.split(";",4);
        //string[0] = tipo
        //string[1] = nomeprops
        //string[2] = nifprops
        //string[3] = resto dos dados
        List<Casa> lista=new ArrayList<>();
        Boolean boo=false;
        for (Casa casa : this.lista_casas){
            if (casa.getProprietario_nome().equals(string[1]) && casa.getProprietario_nif()==Integer.parseInt(string[2])){
                casa.adiciona_device(string[3], string[0],ID_CURR);
                ID_CURR++;
                boo=true;
            }
            lista.add(casa.clone());
        }
        setLista_casas(lista);
        return boo;
    }

    /**
     * Método para adicionar uma casa
     * @param str String com a informação necessária
     * @return 0 caso a casa já exista, -1 caso o comercializador não exista
     * @throws FornecedorNaoExisteException Exceção do fornecedor não existir
     */
    public Integer adicionar_casas(String str) throws FornecedorNaoExisteException{
        String[] string=str.split(";");
        List<Casa> lista=new ArrayList<>();
        for (Casa casa : getLista_casas()){
            if (casa.getProprietario_nome().equals(string[0]) && Integer.parseInt(string[1])==casa.getProprietario_nif()){
                return 0;
            }
            lista.add(casa.clone());
        }
        if ( getComercial_String(string[2])==null ){
            return -1;
        }
        lista.add(new Casa(string[0],Integer.parseInt(string[1]),getComercial_String(string[2]) ));
        setLista_casas(lista);
        return 1;
    }

    /**
     * Método para adicionar um comercializador
     * @param str Dados do comercializador
     * @return 0 se já existir, 1 se for adicionado com sucesso
     */
    public int adicionar_comer(String str){
        String[] string=str.split(";");
        List<Comercializador> lista=new ArrayList<>();
        for (Comercializador comer : getLista_comercial()){
            if (comer.getNome().equals(string[0])){
                return 0;
            }
            lista.add(comer.clone());
        }
        Random random=new Random();
        Comercializador comer;
        if (random.nextInt(2)==0){
            comer = new FornecedorA(string[0],Float.parseFloat(string[1]),Float.parseFloat(string[2]));
        }
        else {
            comer = new FornecedorB(string[0],Float.parseFloat(string[1]),Float.parseFloat(string[2]));
        }
        lista.add(comer);
        setLista_comercial(lista);
        return 1;
    }

    /**
     * Método para mudar de estado os dispositivos de uma casa
     * @param casaId informação sobre a casa
     * @param estado estado objetivo
     * @return Casa após as alterações
     */
    public Casa muda_estado_devices_casa(String casaId,int estado){
        String[] var_casa=casaId.split(";");
        //var_casa[0] = nome do props
        //var_casa[1] = nif do props
        List<Casa> lista=new ArrayList<>();
        Casa casinha=null;
        for (Casa c: getLista_casas()){
            if (c.getProprietario_nome().equals(var_casa[0]) && c.getProprietario_nif()==(Integer.parseInt(var_casa[1]))){
                c.muda_estado_devices_todos(estado);
                casinha=c.clone();
            }
            lista.add(c);
        }
        setLista_casas(lista);
        return casinha;
    }

    /**
     * Método para alterar o estado dos dispositivos de uma divisão
     * @param casaId informação sobre a divisão
     * @param estado estado objetivo
     * @return Casa após as alterações
     */
    public Casa muda_estado_devices_divisao(String casaId,int estado){
        String[] var_casa=casaId.split(";");
        //var_casa[0] = nome do props
        //var_casa[1] = nif do props
        //var_casa[2] = nome da divisao
        List<Casa> lista=new ArrayList<>();
        Casa casinha=null;
        for (Casa c: getLista_casas()){
            if (c.getProprietario_nome().equals(var_casa[0]) && c.getProprietario_nif()==Integer.parseInt(var_casa[1])){
                c.muda_estado_devices_divisao(var_casa[2],estado);
                casinha=c.clone();
            }
            lista.add(c);
        }
        setLista_casas(lista);
        return  casinha;
    }

    /**
     * Método para alterar o estado de um dispositivo
     * @param deviceId informação sobre o dispositivo
     * @param estado estado objetivo
     * @return Casa após alterações
     */
    public Casa muda_estado_devices_unico(String deviceId,int estado){
        String[] var_casa=deviceId.split(";");
        //var_casa[0] = nome do props
        //var_casa[1] = nif do props
        //var_casa[2] = nome da divisao
        List<Casa> lista=new ArrayList<>();
        Casa casinha=null;
        for (Casa casa: getLista_casas()){
            if (casa.getProprietario_nome().equals(var_casa[0]) && casa.getProprietario_nif()==Integer.parseInt(var_casa[1])){
                casa.muda_estado_device_unico(var_casa[2],estado);
                casinha=casa.clone();
            }
            lista.add(casa);
        }
        setLista_casas(lista);
        return casinha;
    }

    /**
     * Método para calcular o consumo energético de todos os dispositivos
     * @return consumo total
     */
    public Float calcular_consumo_geral(){
        float total = 0;
        for (SmartDevice sd : get_all_devices_sistema()){
                total += sd.getConsumo_total();
        }
        return total;

    }

    /**
     * Método que calcula o consumo da casa
     * @param info informação sobre a casa
     * @return consumo total da casa
     */
    public Float calcular_consumo_casa(String info){
        String[] inf=info.split(";",2);
        float consumo=-1;
        for (Casa casa : getLista_casas()){
            if (casa.getProprietario_nome().equals(inf[0]) && casa.getProprietario_nif()==Integer.parseInt(inf[1]) ){
                consumo=casa.calcula_consumo_casa();
            }
        }
        return consumo;
    }

    /**
     * Método para calcular o consumo energético de uma divisão
     * @param info informação sobre a divisão
     * @return consumo energético da divisão
     */
    public Float calcular_consumo_divisao(String info){
        String[] inf=info.split(";",3);
        float consumo=-1;
        for (Casa casa : getLista_casas()){
            if (casa.getProprietario_nome().equals(inf[0]) && casa.getProprietario_nif()==Integer.parseInt(inf[1]) ){
                for (Divisao div : casa.getLista_divisao()){
                    if (div.getNome().equals(inf[2])){
                        consumo=div.calcula_consumo_divisao();
                    }
                }
            }
        }
        return consumo;
    }

    /**
     * Método para calcular o consumo energético de um dispositivo
     * @param info Identificador do dispositivo
     * @return consumo energético do dispositivo
     */
    public Float calcular_consumo_especifico(String info){
        float consumo=-1;
        for (SmartDevice sd : get_all_devices_sistema()){
            if (sd.getId().equals(info)){
                consumo=sd.getConsumo_total();
            }
        }
        return consumo;
    }

    /**
     * Método que calcula o consumo maximo de uma casa
     * @return o consumo maximo
     */
    public Casa estatisticas_1(){
        float max=-1;
        Casa casinha=null;
        for (Casa casa : getLista_casas()){
            if (casa.calcula_consumo_casa()>max){
                max=casa.calcula_consumo_casa();
                casinha = casa.clone();
            }
        }
        return casinha;
    }/*** BEM ***/

    /**
     * Método que devolve a informação do comercializador com maior volume de facturação
     * @return informação do comercializador
     * @throws FornecedorNaoExisteException
     */
    public String estatisticas_2() throws FornecedorNaoExisteException{
        StringBuilder stb=new StringBuilder();
        float max=0;
        Comercializador comercializador= null;
        for (Comercializador c : getLista_comercial()){
            int total=0;
            for(Fatura factura : c.getLista_faturas()){
                total+=factura.getValor();
            }
            if(total > max){
                max=total;
                comercializador = c.clone();
            }
        }
        if(comercializador==null) throw new FornecedorNaoExisteException("Comercializador não existe");
        stb.append(comercializador.getNome() + "," + comercializador.getPreço() + "," + max);
        return stb.toString();
    }

    /**
     * Método que lista as facturas emitidas por um comercializador
     * @param nomeComercializador nome do comercializador
     * @return lista de faturas
     */
    public List<Fatura> estatisticas_3(String  nomeComercializador){
        return getComercializador_nome(nomeComercializador).getLista_faturas();
    }

    /**
     * Métoddo que ordena os maiores consumidores de energia durante um perı́odo a determinar
     * @param start data de inicio
     * @param end data de fim
     * @return um Set dos maiores consumidores de energia
     */
    public Set<Casa> estatisticas_4(LocalDateTime start, LocalDateTime end){
        Set<Casa> casas=new TreeSet<>((o1, o2) -> Float.compare(o2.calcula_consumo_casa(start,end), o1.calcula_consumo_casa(start,end)));
        casas.addAll(getLista_casas());
        return casas;
    }

    /**
     * Método para a simulação temporal por dias
     * @param dias até quando será simulado
     * @return retorna true caso a simulação seja concluída
     * @throws FornecedorNaoExisteException Exceção fornecedor não existente
     */
    public Boolean viagem_temporal_dias(String dias) throws FornecedorNaoExisteException{
        LocalDateTime new_ldt=getTempo();
        String[] nova=dias.split("/",4);
        new_ldt = new_ldt.plusDays(Long.parseLong(nova[0]));
        new_ldt = new_ldt.plusHours(Long.parseLong(nova[1]));
        new_ldt = new_ldt.plusMinutes(Long.parseLong(nova[2]));
        new_ldt = new_ldt.plusSeconds(Long.parseLong(nova[3]));
        //System.out.println(new_ldt);


        for (Casa casa: this.lista_casas){
            Comercializador comer = this.getComercial_String(casa.getComercia().getNome());
            casa.simula(new_ldt, comer);
            atualizaComercializador(comer);
        }
        setTempo(new_ldt);
        return true;
    }

    /**
     * Método que atualiza um comercializador
     * @param comer comercializador
     */
    public void atualizaComercializador(Comercializador comer){
        List<Comercializador> lista=new ArrayList<>();
        for (Comercializador c : this.lista_comercial){
            if (c.getNome().equals(comer.getNome()) && c.getImposto()==comer.getImposto() && c.getPreço()==comer.getPreço()){
                lista.add(comer.clone());
            }
            else {
                lista.add(c.clone());
            }
        }
        setLista_comercial(lista);
    }

    /**
     * Método da simulação através do Local Date Time
     * @param new_ldt nova data
     * @return True caso a simulação seja concluída
     * @throws FornecedorNaoExisteException
     */
    public Boolean viagem_temporal_LDT(LocalDateTime new_ldt) throws FornecedorNaoExisteException{
        List<Casa> lista_casa=getLista_casas();
        for (Casa casa:lista_casa){
            Comercializador comer = this.getComercial_String(casa.getComercia().getNome());
            casa.simula(new_ldt, comer);
            atualizaComercializador(comer);
        }
        setTempo(new_ldt);
        setLista_casas(lista_casa);
        return true;
    }

    /**
     * Método que muda o comercializador de uma casa
     * @param casa contem o nome do proprietário e o nif do proprietário
     */
    public void mudarComercializador_Casa(String casa){
        String[] dados=casa.split(";",3);
        Comercializador comer=getComercializador_nome(dados[2]);
        for (Casa casinha : this.lista_casas){
            if (casinha.getProprietario_nome().equals(dados[0]) && casinha.getProprietario_nif()==Integer.parseInt(dados[1])){
                casinha.setComercia(comer.clone());
                break;
            }
        }
    }

    //----------FUNÇÕES AUXILIARES----------
    /**
     * Função que permite ler os ficheiros
     * @param nomeFicheiro nome do ficheiro
     * @return Retorna uma lista com o conteúdo do ficheiro
     */
    /**
     * Método que le o ficheiro que recebe como input
     * @param nomeFicheiro ficheiro de input
     * @return List de String
     */
    private static List<String> lerFicheiro(String nomeFicheiro) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(nomeFicheiro), StandardCharsets.UTF_8);
        }
        catch(IOException exc) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    /*
    private Integer give_Estado_by_String(String estado){
        //sub por switch
        if (estado=="ON"){
            return 1;
        }
        else if (estado=="OFF"){
            return 0;
        }
        else{
            return -1;
        }
    }

    private Integer give_Tonalidade_by_String(String tonalidade){
        //sub por switch
        if (tonalidade=="NEUTRAL"){
            return 0;
        }
        else if (tonalidade=="WARM"){
            return 1;
        }
        else if (tonalidade=="COLD"){
            return 2;
        }
        else return null;
    }

    private SmartDevice.Estado get_Estado_by_String(String estado){
        if (estado.equals("ON") || estado.equals("On") || estado.equals("on") ){
            return SmartDevice.Estado.ON;
        }
        else if (estado.equals("OFF") || estado.equals("Off") || estado.equals("off") || estado.equals("OFf")){
            return SmartDevice.Estado.OFF;
        }
        return null;
    }

    private Comercializador getComercializador_by_SDID(String id){
        for (Casa casa: getLista_casas()){
            for (SmartDevice sd: casa.get_all_devices_casa()){
                if (sd.getId().equals(id)){
                    return casa.getComercia();
                }
            }
        }
        return null;
    }

    public SmartDevice getSD_ID(String id){
        /*for (Model.SmartDevice sd:devices){
            if (sd.getId().equals(id)){
                return sd.clone();
            }
        }

        return null;
    }

    public void replace_Casa_listaCasas(Casa casa){
        int ind=0;
        for (Casa c : lista_casas){
            if (c.getProprietario_nif().equals(casa.getProprietario_nif()) && c.getProprietario_nome().equals(casa.getProprietario_nome())){
                break;
            }
            ind++;
        }
        lista_casas.remove(ind);
        lista_casas.add(ind,casa);
    }

    public List<SmartDevice> getAllDevices_sistema_disponiveis(){
        List<SmartDevice> ret=new ArrayList<>();
        for (SmartDevice sd : get_all_devices_sistema()){
            Boolean boo=false;
            for (SmartDevice sdCasa : getAllDevices_das_casas()){
                if (sd.equals(sdCasa)){
                    boo=true;
                    break;
                }
            }
            if (!boo){
                ret.add(sd.clone());
            }
        }
        return ret;
    }

    public Casa getCasa_by_dados(String nome, Integer nif){
        for (Casa casa : getLista_casas()){
            if (casa.getProprietario_nome().equals(nome) && casa.getProprietario_nif()==nif){
                return casa.clone();
            }
        }
        return null;
    }
     */



    /**
     * Método que devolve o comercializador consoante o nome
     * @param nome do comercializador
     * @return comercializador
     */
    private Comercializador getComercializador_nome(String nome){
        for (Comercializador comer : getLista_comercial()){
            if (comer.getNome().equals(nome)){
                return comer.clone();
            }
        }
        return null;
    }


    //----------OVERRIDES----------
    /**
     * Método toString alternativo
     * @return String do objeto Casa
     */
    public String toStringw(){
        StringBuilder stb=new StringBuilder();

        for (Comercializador comer: lista_comercial){
            stb.append(comer.toStringw() + "\n");
        }

        for (Casa casa: lista_casas){
            stb.append( casa.toStringw());
        }

        return stb.toString();
    }

    /**
     * Método toString para o objeto Model
     * @return String do objeto Casa
     */
    public String toString(){
        StringBuilder stb=new StringBuilder();

        for (Comercializador comer: lista_comercial){
            stb.append(comer.toString() + "\n");
        }

        for (Casa casa: lista_casas){
            stb.append( casa.toString()+ "\n");
        }

        return stb.toString();
    }


    //----------FUNÇOES GET----------

    /**
     * Método que devolve um comercializador dado um nome
     * @param str string de um comercializador
     * @return comercializador
     * @throws FornecedorNaoExisteException
     */
    public Comercializador getComercial_String(String str) throws FornecedorNaoExisteException{
        for (Comercializador comer: getLista_comercial()){
            if (comer.getNome().equals(str)){
                return comer.clone();
            }
        }
        throw new FornecedorNaoExisteException(str);
    }

    /**
     * Método que devolve todos os sispositivos de uma lista de casas
     * @return lista de SmartDevices
     */
    public List<SmartDevice> getAllDevices_das_casas(){
        List<SmartDevice> ret=new ArrayList<>();
        for (Casa casa : getLista_casas()){
            for (SmartDevice sd : casa.get_all_devices_casa()){
                ret.add(sd.clone());
            }
        }
        return ret;
    }
    // Colocar funcoes das listagens

    /**
     * Método que devolve todos dispositivos de um sistema
     * @return lista de SmartDevices
     */
    public List<SmartDevice> get_all_devices_sistema(){
        List<SmartDevice> ret=new ArrayList<>();
        for (Casa casa : getLista_casas()){
            ret.addAll(casa.get_all_devices_casa());
        }
        return ret;
    }


}
