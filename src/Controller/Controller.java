package Controller;

import Excecoes.FornecedorNaoExisteException;
import View.Menu;
import View.View;
import model.Model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Controller {

    private Model mod;
    private View vie;

    /**
     * Construtor vazio
     */
    public Controller(){
        this.mod=new Model();
        this.vie=new View();
    }

    /**
     * Método principal para executar o Controller
     */
    public void execute() {
        vie.menuWelcome();
        try {
            boolean certo = mod.carregar_ficheiro_txt("input_files/logs.txt");
            this.menu();
        } catch (FornecedorNaoExisteException e) {
            throw new RuntimeException(e);
        }



    }

    /**
     * Método para carregar o ficheiro
     * @throws IOException Exceção IO
     * @throws ClassNotFoundException Exceção classe não encontrada
     * @throws FornecedorNaoExisteException Exceção fornecedor não existe
     */
    private void carregar_ficheiro() throws IOException, ClassNotFoundException, FornecedorNaoExisteException {
        String nome_ficheiro=vie.menu_ficheiro();
        //ver a cena do endswith();
        if (nome_ficheiro.endsWith(".txt")){
            mod.carregar_ficheiro_txt(nome_ficheiro);
        }
        else if (nome_ficheiro.endsWith(".bin") | nome_ficheiro.endsWith(".DEFAULT")){
            mod = mod.carregar_ficheiro_obj(nome_ficheiro);

        }
    }

    /**
     * Método para escrever o ficheiro
     * @throws IOException Exceção IO
     */
    private void escrever_ficheiro() throws IOException {
        String nome_ficheiro=vie.menu_ficheiro();
        if (nome_ficheiro.endsWith(".txt")){
            mod.escrever_ficheiro_txt(nome_ficheiro);
        }
        else if (nome_ficheiro.endsWith(".bin") | nome_ficheiro.endsWith(".DEFAULT")){
            mod.escrever_ficheiro_obj(nome_ficheiro);
        }
    }

    /**
     * Método para listar tudo
     */
    private void listar_geral(){
        int op=vie.listar_geral();
        if (op==1){
            this.listar_allSD();
        }
        else if (op==2){
            this.listar_allCasas();
        }
        else if (op==3){
            this.listar_allComer();
        }
        else if (op==4){
            this.listar_allFaturas();
        }
        else if (op==0){
            return;
        }
        this.listar_geral();
    }

    /**
     * Método para listar os dispositivos inteligentes
     */
    private void listar_allSD(){
        vie.listar_all_types_SD(mod.get_all_devices_sistema());
    }

    /**
     * Método para listar as casas
     */
    private void listar_allCasas(){
        vie.listar_allCasas(mod.getLista_casas());
    }

    /**
     * Método para listar todos os Comercializadores
     */
    private void listar_allComer(){
        vie.listar_allComer(mod.getLista_comercial());
    }

    private void listar_allFaturas(){
        vie.listar_allFaturas(mod.getLista_comercial());
    }

    /**
     * Método para adicionar dispositivo, casa ou comercializador
     * @throws FornecedorNaoExisteException Exceção caso não exista fornecedor
     */
    private void adicionar_elem() throws FornecedorNaoExisteException{
        int op=vie.adicionar_elem();
        if (op==1){
            this.adicionar_sd();
        }
        else if (op==2){
            this.adicionar_casas();
        }
        else if (op==3){
            this.adicionar_comer();
        }
    }

    /**
     * Método para adicionar um SmartDevice
     */
    private void adicionar_sd(){
        vie.adicionar_sd_conf(mod.adicionar_sd(vie.adicionar_sd()));
    }

    /**
     * Método para adicionar uma casa
     * @throws FornecedorNaoExisteException Exceção caso o fornecedor não exista
     */
    private void adicionar_casas() throws FornecedorNaoExisteException{
        vie.adicionar_casas_conf(mod.adicionar_casas(vie.adicionar_casas()));
    }

    /**
     * Método para adicionar um Comercializador
     */
    private void adicionar_comer(){
        vie.adicionar_comer_conf(mod.adicionar_comer(vie.adicionar_comer()));
    }

    /**
     * Método para mudar dispositivos
     */
    private void mudar_devices_geral(){
        int op=vie.mudar_devices_geral();
        if(op==1){
            this.mudar_devices_casa();
        }
        else if (op==2){
            this.mudar_devices_divisao();
        }
        else if (op==3){
            this.mudar_devices_unico();
        }
        else if (op!=0){
            this.mudar_devices_geral();
        }
    }

    /**
     * Método para mudar estado dos dispositivos
     */
    private void mudar_devices_casa(){
        int op=vie.mudar_devices_lig_des();
        if (op==1){
            this.ligar_devices_casa();
        }
        else if (op==2){
            this.desligar_devices_casa();
        }
        else if (op==0){
            this.mudar_devices_geral();
        }
    }

    /**
     * Método para ligar dispostivos de uma casa
     */
    private void ligar_devices_casa(){
        String dados_casa=vie.muda_estado_devices_casa();
        vie.muda_estado_devices_casa_CONF(mod.muda_estado_devices_casa(dados_casa,1),1);
    }

    /**
     * Método para desligar dispostivos de uma casa
     */
    private void desligar_devices_casa(){
        vie.muda_estado_devices_casa_CONF(mod.muda_estado_devices_casa(vie.muda_estado_devices_casa(),0),0);
    }

    /**
     * Método para ligar/desligar dispositivos de uma divisão
     */
    private void mudar_devices_divisao(){
        int op=vie.mudar_devices_lig_des();
        if (op==1){
            this.ligar_devices_divisao();
        }
        else if (op==2){
            this.desligar_devices_divisao();
        }
        else if (op==0){
            this.mudar_devices_geral();
        }
    }

    /**
     * Método para ligar dispositivos de uma divisãao
     */
    private void ligar_devices_divisao(){
        String dados_casa=vie.muda_estado_devices_divisao();
        vie.muda_estado_devices_divisao_CONF(mod.muda_estado_devices_divisao(dados_casa,1),1,dados_casa);
    }

    /**
     * Método para desligar dispositivos de uma divisãao
     */
    private void desligar_devices_divisao(){
        String dados_casa=vie.muda_estado_devices_divisao();
        vie.muda_estado_devices_divisao_CONF(mod.muda_estado_devices_divisao(dados_casa, 0), 0,dados_casa);
    }

    /**
     * Método para mudar algo apenas de um dispositivo
     */
    private void mudar_devices_unico(){
        int op=vie.mudar_devices_lig_des();
        if (op==1){
            this.ligar_device_unico();
        }
        else if (op==2){
            this.desligar_device_unico();
        }
        else if (op==0){
            this.mudar_devices_geral();
        }
        else {
            this.mudar_devices_unico();
        }
    }//6554

    /**
     * Método para ligar um dispositivo
     */
    private void ligar_device_unico(){
        String dados_device=vie.muda_estado_devices_unico();
        vie.muda_estado_devices_unico_CONF(mod.muda_estado_devices_unico(dados_device,1),1,dados_device);
    }

    /**
     * Método para desligar um dispositivo
     */
    private void desligar_device_unico(){
        String dados_device=vie.muda_estado_devices_unico();
        vie.muda_estado_devices_unico_CONF(mod.muda_estado_devices_unico(dados_device,0),0,dados_device);
    }

    /**
     * Método para calcular o consumo
     */
    private void calcular_consumo(){
        int op = vie.calcular_consumo();
        if (op==1){
            vie.calcular_consumo_geral_CONF(mod.calcular_consumo_geral());

        }
        else if (op==2){
            String casa=vie.calcular_consumo_casa();
            vie.calcular_consumo_casa_CONF( mod.calcular_consumo_casa( casa ), casa);
        }
        else if (op==3){
            String divisao=vie.calcular_consumo_divisao();
            vie.calcular_consumo_divisao_CONF(mod.calcular_consumo_divisao(divisao),divisao);
        }
        else if (op==4){
            String espec=vie.calcular_consumo_especifico();
            vie.calcular_consumo_especifico_CONF(mod.calcular_consumo_especifico(espec),espec);
        }
    }

    //qual é a casa que mais gastou naquele perı́odo
    //qual o comercializador com maior volume de facturação
    //listar as facturas emitidas por um comercializador
    //dar uma ordenação dos maiores consumidores de energia durante um perı́odo a determinar

    /**
     * Método principal das estatísticas
     */
    private void estatisticas(){
        int op = vie.estatisticas_pergunta();
        if (op==0){
            return;
        }
        else if (op==1){
            vie.estatisticas_1(mod.estatisticas_1());
        }
        else if (op==2){
            try {
                vie.estatisticas_2(mod.estatisticas_2());
            } catch (FornecedorNaoExisteException e) {
                throw new RuntimeException(e);
            }
        }
        else if (op==3){
            vie.estatisticas_3(mod.estatisticas_3(vie.estatisticas_3_1()));
        }
        else if (op==4){
            List<LocalDateTime> lista = vie.estatisticas_4_1();
            vie.estatisticas_4(mod.estatisticas_4(lista.get(0), lista.get(1)));
        }
        else if (op==5){
            vie.estatisticas_1(mod.estatisticas_1());
            try {
                vie.estatisticas_2(mod.estatisticas_2());
            } catch (FornecedorNaoExisteException e) {
                throw new RuntimeException(e);
            }
            vie.estatisticas_3(mod.estatisticas_3(vie.estatisticas_3_1()));
            List<LocalDateTime> lista = vie.estatisticas_4_1();
            vie.estatisticas_4(mod.estatisticas_4(lista.get(0), lista.get(1)));
        }
        this.estatisticas();
    }


    /**
     * Método viagem temporal
     * @throws FornecedorNaoExisteException Exceção caso não exista fornecedor
     */
    private void menu_viagem_temporal_geral() throws FornecedorNaoExisteException{
        int op=vie.menu_viagem_temporal_geral(mod.getTempo());
        switch (op){
            case 0:
                return;
            case 1:
                this.menu_viagem_temporal_dias();
                break;
            case 2:
                this.menu_viagem_temporal_LDT();
                break;

        }
        this.menu_viagem_temporal_geral();
    }

    /**
     * Método viagem temporal em dias
     * @throws FornecedorNaoExisteException
     */
    private void menu_viagem_temporal_dias() throws FornecedorNaoExisteException{
        String dias=vie.menu_viagem_temporal_dias(mod.getTempo());
        Boolean conf=mod.viagem_temporal_dias(dias);

    }

    /**
     * Método da viagem temporal
     * @throws FornecedorNaoExisteException
     */
    private void menu_viagem_temporal_LDT() throws FornecedorNaoExisteException{
        LocalDateTime ldtFinal = vie.menu_viagem_temporal_LDT(mod.getTempo());

        Boolean conf =mod.viagem_temporal_LDT(ldtFinal);
        vie.muda_tempo_LDT_CONF(ldtFinal,conf);
    }

    public void mudarComercializador_Casa(){
        mod.mudarComercializador_Casa(vie.mudarComercializador_Casa());
    }

    /**
     * Método principal do menu com as opções
     * @throws FornecedorNaoExisteException
     */
    public void menu() throws FornecedorNaoExisteException{
        Menu menu = new Menu(new String[]{
                "Carregar Ficheiro",//por a opcao para obj ou txt
                "Escrever Ficheiro",//por a opcao para obj ou txt
                "Listagens",
                "Adicionar Elementos ao Sistema",
                "Mudar Estado de Dispositivo/s",
                "Calcular Consumo",
                "Estatisticas",
                "Viajar temporalmente",//simular automaticamente
                "Mudar Comercializador de uma Casa"
        });

        // Registar os handlers das transições
        menu.setHandler(1,()-> {
            try {
                carregar_ficheiro();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FornecedorNaoExisteException e) {
                throw new RuntimeException(e);
            }
        });
        menu.setHandler(2,()-> {
            try {
                escrever_ficheiro();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Completo --> escrever e ler txt
        menu.setHandler(3,()->listar_geral());//Completo --> Sd's, Casas e Fornecedores
        menu.setHandler(4,()-> {
            try {
                adicionar_elem();
            } catch (FornecedorNaoExisteException e) {
                throw new RuntimeException(e);
            }
        });//Completo --> Sd's, Casas e Fornecedores
        menu.setHandler(5,()->mudar_devices_geral());//Completo --> Model.Casa, Model.Divisao e Unico completas
        menu.setHandler(6,()->calcular_consumo());
        menu.setHandler(7,()->estatisticas());
        menu.setHandler(8,()-> {
            try {
                menu_viagem_temporal_geral();
            } catch (FornecedorNaoExisteException e) {
                throw new RuntimeException(e);
            }
        });
        menu.setHandler(9,()->mudarComercializador_Casa());

        // Executar o menu
        menu.run();
        vie.menuDespedida();
    }
}
