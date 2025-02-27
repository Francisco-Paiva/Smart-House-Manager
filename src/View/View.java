package View;

import model.*;
import model.Comercializador.Comercializador;

import java.time.LocalDateTime;
import java.util.*;

public class View {

    private final static int tammenu=227;//227

    private Menu menu;
    private Scanner sc;

    /**
     * Construtor vazio
     */
    public View(){
        this.menu=new Menu();
        this.sc=new Scanner(System.in);
    }

    /**
     * Método de boas vindas para o menu
     */
    public void menuWelcome(){
        //menu.menuWelcome();
        //menu.print_Blue(menu.menu_simples_txt(tammenu,"WELCOME",false));
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"WELCOME",false));
    }


    /**
     * Método do menu de despedida
     */
    public void menuDespedida() {
        //menu.menuDespedida();
        //menu.print_Blue(menu.menu_simples_txt(tammenu,"ATÉ À PRÓXIMA",false));
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"ATÉ À PRÓXIMA",false));
    }

    /**
     * Método para o menu pedir a introdução do nome do ficheiro
     * @return path para o ficheiro
     */
    public String menu_ficheiro(){
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO FICHEIRO",false));
        return "input_files/".concat(sc.nextLine());
    }

    /**
     * Método das várias opções de listagens
     * @return opção escolhida
     */
    public Integer listar_geral(){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Listar SmartDevices");
        map.put(2,"Listar Casas");
        map.put(3,"Listar Comercializadores");
        map.put(4,"Listar Faturas");
        map.put(0,"Voltar ao Menu");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"ESCOLHA UMA OPÇÃO",false,map,true));

        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método para listar todos os SmartDevices
     * @param lista lista de dispositivos
     */
    public void listar_all_types_SD(List<SmartDevice> lista){
        List<SmartDevice> lista_sd=new ArrayList<>();
        List<SmartBulb> lista_sb=new ArrayList<>();
        List<SmartSpeaker> lista_ss=new ArrayList<>();
        List<SmartCamera> lista_sc=new ArrayList<>();

        for (SmartDevice sd: lista){
            if (sd instanceof SmartBulb){
                lista_sb.add((SmartBulb) sd);
            }
            else if (sd instanceof SmartCamera){
                lista_sc.add((SmartCamera) sd);
            }
            else if (sd instanceof SmartSpeaker){
                lista_ss.add((SmartSpeaker) sd);
            }
            else if ( !(sd instanceof SmartSpeaker) && !(sd instanceof SmartCamera) && !(sd instanceof SmartBulb) ){
                lista_sd.add(sd);
            }
        }
        this.listar_allSD(lista_sd);
        this.listar_allSS(lista_ss);
        this.listar_allSB(lista_sb);
        this.listar_allSC(lista_sc);
    }

    /**
     * Método para listar os dispositivos inteligentes
     * @param lista lista de SmartDevices
     */
    public void listar_allSD(List<SmartDevice> lista){
        if (lista.isEmpty()){
            return;
        }
        menu.menu_barras(tammenu);
        menu.menu_listar_allSD(tammenu);
        menu.menu_barras(tammenu);
        for (SmartDevice sd: lista){
            if ( !(sd instanceof SmartCamera) && !(sd instanceof SmartSpeaker) && !(sd instanceof SmartBulb) )
            menu.listar_SD(sd,tammenu);
        }
        menu.menu_barras(tammenu);
    }

    /**
     * Método para listar as lâmpadas inteligentes
     * @param lista lista de SmartBulbs
     */
    public void listar_allSB(List<SmartBulb> lista){
        if (lista.isEmpty()){
            return;
        }
        menu.menu_barras(tammenu);
        menu.menu_listar_allSB(tammenu);
        menu.menu_barras(tammenu);
        for (SmartDevice sd: lista){
            if (sd instanceof SmartBulb){
                menu.listar_SB((SmartBulb) sd,tammenu);
            }
        }
        menu.menu_barras(tammenu);
    }

    /**
     * Método para listar as colunas inteligentes
     * @param lista lista de SmartSpeakers
     */
    public void listar_allSS(List<SmartSpeaker> lista){
        if (lista.isEmpty()){
            return;
        }
        menu.menu_barras(tammenu);
        menu.menu_listar_allSS(tammenu);
        menu.menu_barras(tammenu);
        for (SmartDevice sd: lista){
            if (sd instanceof SmartSpeaker){
                menu.listar_SS((SmartSpeaker) sd,tammenu);
            }
        }
        menu.menu_barras(tammenu);
    }

    /**
     * Método para listar as câmaras inteligentes
     * @param lista lista de SmartCameras
     */
    public void listar_allSC(List<SmartCamera> lista){
        if (lista.isEmpty()){
            return;
        }
        menu.menu_barras(tammenu);
        menu.menu_listar_allSC(tammenu);
        menu.menu_barras(tammenu);
        for (SmartDevice sd: lista){
            if (sd instanceof SmartCamera){
                menu.listar_SC((SmartCamera) sd,tammenu);
            }
        }
        menu.menu_barras(tammenu);
    }

    /**
     * Método para listar todas as casas
     * @param lista Lista de casas
     */
    public void listar_allCasas(List<Casa> lista){
        int i=1;
        for (Casa casa: lista){
            menu.menu_listar_casa(tammenu,i);
            menu.listar_casa(casa,tammenu);
            menu.listar_casa_lDivisoes(casa,tammenu);
            if (lista.size()>i){
                menu.menu_enters(2);
            }
            i++;
        }
    }

    /**
     * Método para listar todos os comercializadores
     * @param lista lista de comercializadores
     */
    public void listar_allComer(List<Comercializador> lista){
        menu.menu_barras(tammenu);
        menu.menu_listar_allComer(tammenu);
        menu.menu_barras(tammenu);
        int i=1;
        for (Comercializador comer: lista){
            menu.listar_comer(comer,i,tammenu);
            i++;
        }
        menu.menu_barras(tammenu);
    }

    public void listar_allFaturas(List<Comercializador> lista){
        menu.menu_barras(tammenu);
        menu.menu_listar_allFaturas(tammenu);
        menu.menu_barras(tammenu);
        for (Comercializador comer : lista){
            menu.listar_faturas(comer,tammenu);

        }
        menu.menu_barras(tammenu);
        /*
        for (Map.Entry<Comercializador,List<Fatura>> entry : map.entrySet()){
            menu.menu_barras(tammenu);
            menu.listar_faturas(entry,tammenu);
            menu.menu_barras(tammenu);
        }*/
    }

    /**
     * Métodos das opções para adiconar um elemento
     * @return opção escolhida
     */
    public Integer adicionar_elem(){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"SmartDevice");
        map.put(2,"Casa");
        map.put(3,"Comercializador");
        map.put(0,"Voltar ao Menu");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"ESCOLHA UMA OPÇÃO",false,map,true));
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método para adicionar um SmartDevice onde se pedirá ao utilizador as informações do dispositivo
     * @return Dispositivo em String
     */
    public String adicionar_sd() {
        StringBuilder stb=new StringBuilder();
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"SmartBulb");
        map.put(2,"SmartSpeaker");
        map.put(3,"SmartCamera");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"SELECIONE TIPO DE SMARTDEVICE",false,map,false));
        Integer tipo=Integer.parseInt(sc.nextLine());
        stb.append(tipo + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() +";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A DIVISÃO DA CASA",false));
        stb.append(sc.nextLine() + ";");

        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O CONSUMO DIÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O CUSTO DE INSTALAÇÃO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O ESTADO",false));
        stb.append(sc.nextLine());

        switch (tipo){
            case 1://Bulb
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A TONALIDADE",false));
                stb.append(";" + sc.nextLine());
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A DIMENSÃO",false));
                stb.append(";" + sc.nextLine());

                break;
            case 2://Speaker
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O VOLUME",false));
                stb.append(";" + sc.nextLine());
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A RÁDIO ONLINE",false));
                stb.append(";" + sc.nextLine());
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A MARCA",false));
                stb.append(";" + sc.nextLine());
                break;
            case 3://Camera
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A RESOLUÇÃO",false));
                stb.append(";" + sc.nextLine());
                menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O TAMANHO",false));
                stb.append(";" + sc.nextLine());
                break;
        }
        return stb.toString();
    }

    /**
     * Método de resposta à tentativa de adição de um dispositvo novo
     * @param resp resposta
     */
    public void adicionar_sd_conf(Boolean resp){
        if (!resp){
            menu.print_Red(menu.menu_simples_txt_v2(tammenu,"DISPOSITIVO INVÁLIDO",false));
        }
        else {
            menu.print_Green(menu.menu_simples_txt_v2(tammenu,"DISPOSITIVO ADICIONADO AO SISTEMA",false));
        }
    }

    /**
     * Método auxiliar para a adição de uma casa nova, que pede as informações da casa
     * @return informações da casa numa String
     */
    public String adicionar_casas() {
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        String nome_proprietario=sc.nextLine();
        stb.append(nome_proprietario + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        Integer nif_proprietario=Integer.parseInt(sc.nextLine());
        stb.append(nif_proprietario + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO COMERCIALIZADOR",false));
        String nome_comercializador=sc.nextLine();
        stb.append(nome_comercializador);
        return stb.toString();
    }

    /**
     * Método de resposta à tentativa de adição da casa
     * @param resp resposta
     */
    public void adicionar_casas_conf(int resp){
        switch (resp){
            case 0:
                menu.print_Red(menu.menu_simples_txt_v2(tammenu,"JÁ EXISTE UMA CASA COM DETERMINADO PROPRIETÁRIO",false));
                break;
            case -1:
                menu.print_Red( menu.menu_simples_txt_v2(tammenu,"COMERCIALIZADOR INVÁLIDO",false));
                break;
            case 1:
                menu.print_Green( menu.menu_simples_txt_v2(tammenu,"CASA ADICIONADA COM SUCESSO",false));
                break;
        }
    }

    /**
     * Método auxiliar da adição de um comercializador que pede as infõomaçes
     * @return informações do comercializador
     */
    public String adicionar_comer() {
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O PREÇO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O IMPOSTO",false));
        stb.append(sc.nextLine());
        return stb.toString();
    }

    /**
     * Método da confirmação da adição do comercializador ou se há algum erro
     * @param resp resposta
     */
    public void adicionar_comer_conf(int resp){
        switch (resp){
            case 1:
                menu.print_Green(menu.menu_simples_txt_v2(tammenu,"COMERCIALIZADOR ADICIONADO COM SUCESSO",false));
                break;
        }
    }

    /**
     * Método das opções do menu de que maneira este quer mudar o estado dos dispositivos
     * @return resposta do utilizador
     */
    public Integer mudar_devices_geral(){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Casa");
        map.put(2,"Divisão");
        map.put(3,"Específico");
        map.put(0,"Voltar ao Menu");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"ESCOLHA UMA OPÇÃO",false,map,true));

        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método de auxílio de quando o utilizador pretende alterar o estado de dispositivo/s
     * @return resposta do utilizador
     */
    public Integer mudar_devices_lig_des(){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Ligar");
        map.put(2,"Desligar");
        map.put(0,"Voltar atrás");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"ESCOLHA UMA OPÇÃO",false,map,true));

        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método do menu caso o utilizador pretenda mudar o estado dos dispositivos de uma casa
     * @return resposta do utilizador
     */
    public String muda_estado_devices_casa(){
        StringBuilder stb=new StringBuilder();

        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine());

        return stb.toString();
    }

    /**
     * Método de confirmação da alteração do estado dos dispositivos de uma casa
     * @param casa Casa
     * @param estado Estado objetivo
     */
    public void muda_estado_devices_casa_CONF(Casa casa, int estado){
        menu.menu_listar_casa(tammenu,1);
        menu.menu_barras(tammenu);
        menu.listar_casa(casa,tammenu);
        menu.listar_casa_lDivisoes(casa,tammenu);

        if (estado==1){
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"TODOS OS DISPOSITIVOS DA CASA FORAM LIGADOS COM SUCESSO",false));
        }
        else {
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"TODOS OS DISPOSITIVOS DA CASA FORAM DESLIGADOS COM SUCESSO",false));
        }
    }

    /**
     * Método que apresenta o menu para o utilizador caso este queira alterar o estado dos dispositivos de uma divisão
     * @return resposta do utilizador
     */
    public String muda_estado_devices_divisao(){
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A DIVISÃO DA CASA",false));
        stb.append(sc.nextLine());
        return stb.toString();
    }


    /**
     * Método de confirmação da alteração do estado dos dispositivos de uma divisão
     * @param casa Casa
     * @param estado estado objetivo
     * @param dados_casa dados da casa fornecidos anteriormente
     */
    public void muda_estado_devices_divisao_CONF(Casa casa,int estado,String dados_casa){
        String[] dados=dados_casa.split(";",3);
        menu.menu_barras(tammenu);
        menu.menu_listar_casa(tammenu,0);
        menu.listar_casa(casa,tammenu);
        menu.listar_casa_lDivisoes(casa,tammenu);

        if (estado==1){
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"TODOS OS DISPOSITIVOS DA CASA DA " + dados[2] + "FORAM LIGADOS COM SUCESSO",false));
        }
        else {
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"TODOS OS DISPOSITIVOS DA CASA DA " + dados[2] + "FORAM DESLIGADOS COM SUCESSO",false));
        }
    }

    /**
     * Método do menu caso o utilizador decida mudar o estado apenas de um dispositivo
     * @return respostas do utilizador
     */
    public String muda_estado_devices_unico(){
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O ID DO DISPOSITIVO",false));
        stb.append(sc.nextLine());
        return stb.toString();
    }

    /**
     * Método de confirmação da alteração do estado do dispositivo
     * @param casa Casa
     * @param estado Estado objetivo
     * @param dados dados fornecidos anteriormente
     */
    public void muda_estado_devices_unico_CONF(Casa casa,int estado,String dados){
        //this.listar_all_types_SD(lista_SD);
        String[] dados_device=dados.split(";",3);
        if (estado==1){
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"O DISPOSITIVO COM ID: " + dados_device[2] + " FOI LIGADO COM SUCESSO",false));
        }
        else {
            menu.print_Blue( menu.menu_simples_txt_v2(tammenu,"O DISPOSITIVO COM ID: " + dados_device[2] + " FOI DESLIGADO COM SUCESSO",false));
        }

    }


    /**
     * Método do menu caso o utilizador deseje saber qual é o consumo energético
     * @return opção escolhida
     */
    public Integer calcular_consumo(){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Geral");
        map.put(2,"Casa Especifica");
        map.put(3,"Divisão de uma Casa");
        map.put(4,"Dispositivo especifico");
        map.put(0,"Voltar Atrás");
        menu.print_Blue( menu.menu_ops_txt_v2(tammenu,"O VALOR DO CONSUMO",false,map,true));
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método para apresentar ao utilizador o consumo de todos os dispositivos do sistema
     * @param consumo consumo total
     */
    public void calcular_consumo_geral_CONF(Float consumo){
        menu.print_Green(menu.menu_simples_txt_v2(tammenu,"O CONSUMO DE TODOS OS DISPOSITIVOS NO SISTEMA É: " + consumo,false));
    }

    /**
     * Método para determinar a casa que o utilizador pretende saber o consumo energético
     * @return respostas do utilizador
     */
    public String calcular_consumo_casa(){
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine());

        return stb.toString();
    }

    /**
     * Método que apresenta ao utilizador o consumo de uma certa casa escolhida
     * @param consumo consumo
     * @param casaInfo informações da casa
     */
    public void calcular_consumo_casa_CONF(Float consumo,String casaInfo){
        String[] info=casaInfo.split(";",2);
        menu.menu_listar_casa_noCOMER(tammenu,0);
        menu.listar_casa(casaInfo,tammenu);
        menu.menu_barras(tammenu);
        menu.print_Green(menu.menu_simples_txt_v2(tammenu,"O CONSUMO DESTA CASA FOI DE: " + consumo,false));
        menu.menu_barras(tammenu);
    }

    /**
     * Método que pede as informações da divisão que o utilizador pretende descobrir o consumo energético
     * @return respostas do utilizador
     */
    public String calcular_consumo_divisao(){
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DA DIVISÃO",false));
        stb.append(sc.nextLine());

        return stb.toString();
    }

    /**
     * Método que apresenta ao utilizador o consumo da divisão que escolheu
     * @param consumo consumo
     * @param divisao informações sobre a divisão escolhida
     */
    public void calcular_consumo_divisao_CONF(Float consumo, String divisao){
        String[] info=divisao.split(";",3);
        menu.menu_listar_casa_noCOMER(tammenu,0);
        menu.listar_casa(divisao,tammenu);
        menu.menu_barras(tammenu);
        menu.print_Green(menu.menu_simples_txt_v2(tammenu,"O CONSUMO DA DIVISÃO: " + info[2] + " FOI DE: " + consumo,false));
        menu.menu_barras(tammenu);
    }

    /**
     * Método que pede ao utilizador o id do dispositivo que pretende saber o consumo
     * @return resposta do utilizador
     */
    public String calcular_consumo_especifico(){
        StringBuilder stb=new StringBuilder();

        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O ID DO DISPOSITIVO",false));
        stb.append(sc.nextLine());

        return stb.toString();
    }

    /**
     * Método que apresenta para o utilizador o consumo do dispositivo pretendido
     * @param consumo consumo
     * @param id identificador
     */
    public void calcular_consumo_especifico_CONF(Float consumo,String id){
        //menu.menu_listar_Consumo_sd(tammenu);
        menu.print_Green(menu.menu_simples_txt_v2(tammenu,"O CONSUMO DO DISPOSITIVO: " + id + " FOI DE: " + consumo,false));
    }

    /**
     * Método que apresenta ao utilizador as estatisticas que este pode escolher
     * @return opção do utilizador
     */
    public Integer estatisticas_pergunta(){
        Map<Integer,String> ops=new HashMap<>();
        ops.put(1,"Qual a casa que mais gastou num certo intervalo de tempo");
        ops.put(2,"Qual o comercializador com maior volume de facturação");
        ops.put(3,"Listar as facturas emitidas por um comercializador");
        ops.put(4,"Ordenação dos maiores consumidores de enegia num certo intervalo de tempo");
        ops.put(5,"Todas");
        ops.put(0,"Voltar ao Menu");
        menu.print_Blue(menu.menu_ops_txt_v2(tammenu,"ESCOLHA UMA OPÇÃO",false,ops,true));
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método da primeira opção das estatisticas
     * @param casa casa
     */
    public void estatisticas_1(Casa casa){
        menu.menu_listar_casa(tammenu,0);
        menu.listar_casa(casa,tammenu);
        menu.listar_casa_lDivisoes(casa,tammenu);
    }

    /**
     * Método da opção 2 das estatisticas
     * @param str informação do comercializador que faturou mais
     */
    public void estatisticas_2(String str){
        String[] split = str.split(",",3);
        menu.print_Blue(menu.menu_simples_txt_v3(tammenu,"O Fornecedor -> " + split[0] + ", cujo preço é -> " + split[1], "Faturou um total de -> " + split[2], false));
    }

    /**
     * Método que pede o nome do fornecedor para a opção 3 das estatisticas
     * @return nome do fornecedor dado pelo utilizador
     */
    public String estatisticas_3_1(){
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO FORNECEDOR",false));
        return sc.nextLine();
    }

    /**
     * Método que lista as faturas do comercializador se estas as possuir
     * @param faturas lista de faturas
     */
    public void estatisticas_3(List<Fatura> faturas){
        menu.menu_listar_allFaturas(tammenu);
        if (faturas.isEmpty()){
            menu.print_Red(menu.menu_simples_txt_v2(tammenu,"O FORNECEDOR NÃO POSSUI FATURAS",false));
            return;
        }

        for (Fatura f : faturas){
            menu.listar_fatura(f,tammenu);
        }
    }

    /**
     * Método que pede e guarda as datas introduzidas pelo utilizador quando este escolhe a 4ª opção das estatisticas
     * @return datas fornecidas pelo utilizador
     */
    public List<LocalDateTime> estatisticas_4_1(){
        List<LocalDateTime> ret = new ArrayList<>();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A DATA DE LIMITE INFERIOR",false));
        ret.add(LocalDateTime.parse(sc.nextLine()));
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA A DATA DE LIMITE SUPERIOR",false));
        ret.add(LocalDateTime.parse(sc.nextLine()));
        return ret;
    }

    /**
     * Método que vai apresentar a estatistica numero 4
     * @param set um set com as casas que consumiram mais
     */
    public void estatisticas_4(Set<Casa> set){
        int i = 1;
        for (Casa casa : set){
            menu.menu_listar_casa(tammenu,i);
            menu.listar_casa(casa,tammenu);
            menu.listar_casa_lDivisoes(casa,tammenu);
        }
    }

    /*
    public void estatisticas_resposta(String resposta,int op){
        String[] str=resposta.split(":",2);

        if (op==1 && str[0].equals("1")){
            //while (op==2){}
            String[] s1=str[1].split(":",2);
            if (s1[0].equals("Casa")){
                String[] s2=s1[1].split(";");
                menu.listar_casa(
                        new Casa(
                                s2[0],
                                Integer.parseInt(s2[1]),
                                Comercializador.parse(s2[2])
                        ),
                        tammenu
                );
            }
        }
        else if (op==2 && str[0].equals("2")){
            menu.listar_comer(
                    Comercializador.parse(str[1]),
                    1,
                    tammenu
            );
        }
        else if (op==3 && str[0].equals("3")){

        }
        else if (op==4 && str[0].equals("4")){
            String[] s1=str[1].split(":");

        }
        else if (op==5 && str[0].equals("5")){
            String[] str_all=str[1].split("\n");
            //op == 1
            String[] s1=str_all[0].split(":",2);
            if (s1[0].equals("Casa")){
                String[] s2=s1[1].split(";");
                menu.listar_casa(
                        new Casa(
                                s2[0],
                                Integer.parseInt(s2[1]),
                                Comercializador.parse(s2[2])
                        ),
                        tammenu
                );
            }
            //op == 2
            menu.listar_comer(
                    Comercializador.parse(str_all[1]),
                    1,
                    tammenu
            );

            //op == 3


            //op == 4

            ;
        }
        else if (op!=0){
            menu.print_Red(menu.menu_simples_txt_v2(tammenu,"OPÇÃO INVÁLIDA",false));
        }
    }
    */

    /**
     * Método do menu correspondente à viagem temporal
     * @param tempo_inicial tempo inicial
     * @return opção do utilizador
     */
    public Integer menu_viagem_temporal_geral(LocalDateTime tempo_inicial) {
        //menu.print_Blue(menu.menu_simples_txt_v3(tammenu,"O ATUAL TEMPO É " + tempo_inicial.toString() , "INTRODUZA A OPÇÃO QUE PRETENDE",false));
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"ADICIONAR POR DIA, HORA OU SEGUNDO (dd/hh/mm/ss)");
        map.put(2,"ADICIONAR DATA ESPECIFICA");
        map.put(0,"VOLTAR ATRÁS");
        menu.print_Blue(menu.menu_ops_txt_v2(  tammenu,"INTRODUZA UMA OPÇÃO",false,map,true));
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Método que apresentará ao utilizador o formato necessário para simular por dias
     * @param tempo_inicial tempo inicial
     * @return data dada pelo utilizador
     */
    public String menu_viagem_temporal_dias(LocalDateTime tempo_inicial){
        menu.print_Blue(menu.menu_simples_txt_v3(tammenu,"O ATUAL TEMPO É " + tempo_inicial.toString(),"INTRODUZA OS DADOS NO FORMATO -> dia(s)/hora(s)/minuto(s)/segundo(s)",false));
        return sc.nextLine();
    }

    /*
    /**
     *
     * @param dias
     * @param conf
     *
    public void menu_viagem_temporal_dias_CONF(String dias,Boolean conf){
        if (conf){

        }
        else {

        }
    }
*/

    /**
     * Método do menu caso o utilizador queira fazer uma viagem temporal até uma data especifica
     * @param tempo_inicial tempo inicial
     * @return data escolhida
     */
    public LocalDateTime menu_viagem_temporal_LDT(LocalDateTime tempo_inicial){
        menu.print_Blue(menu.menu_simples_txt_v3(tammenu,"O ATUAL TEMPO É " + tempo_inicial.toString() , "INTRODUZA A OPÇÃO QUE PRETENDE",false));
        return LocalDateTime.parse(sc.nextLine());
    }

    /**
     * Método de confirmação que a simulação foi feita com sucesso ou não
     * @param tempo_final tempo final
     * @param conf boolean de confirmação
     */
    public void muda_tempo_LDT_CONF(LocalDateTime tempo_final,Boolean conf) {
        if (conf){
            menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"O SISTEMA SIMULOU ATÉ: " + tempo_final.toString() ,false));
        }
        else {
            menu.print_Blue(menu.menu_simples_txt_v3(tammenu,"O SISTEMA NÃO CONSEGUIU SIMULAR ATÉ: " + tempo_final.toString(),"TENTE NOVAMENTE" ,false));
        }
    }

    public String mudarComercializador_Casa(){
        StringBuilder stb=new StringBuilder();
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NIF DO PROPRIETÁRIO",false));
        stb.append(sc.nextLine() + ";");
        menu.print_Blue(menu.menu_simples_txt_v2(tammenu,"INTRODUZA O NOME DO NOVO COMERCIALIZADOR",false));
        stb.append(sc.nextLine());
        return stb.toString();
    }

    //----------FUNÇÕES AUXILIARES----------
    private void print_espaço_opção(int i){
        while(i>0){
            System.out.print(" ");
            i--;
        }
    }


    //----------PRINTS----------


    //----------ERRO----------

    /**
     * Método de erro no menu
     */
    public void menu_erro(){
        menu.print_Red(menu.menu_simples_txt_v2(tammenu,"ERRO",false));
    }

}
