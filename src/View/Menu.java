package View;

import model.*;
import model.Comercializador.Comercializador;

import java.time.LocalDateTime;
import java.util.*;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /** Functional interface para handlers. */
    public interface Handler {
        void execute();
    }

    /** Functional interface para pré-condições. */
    public interface PreCondition {
        boolean validate();
    }

    private static Scanner is = new Scanner(System.in);

    private String titulo;                  // Titulo do menu (opcional)
    private List<String> opcoes;            // Lista de opções
    private List<PreCondition> disponivel;  // Lista de pré-condições
    private List<Handler> handlers;         // Lista de handlers

    /**
     * Constructor vazio para objectos da classe Menu.
     *
     * Cria um menu vazio, ao qual se podem adicionar opções.
     */
    public Menu() {
        this.titulo = "Menu";
        this.opcoes = new ArrayList<>();
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    /**
     * Constructor para objectos da classe Menu (com título e List de opções).
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param titulo O titulo do menu
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(String titulo, List<String> opcoes) {
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println(ANSI_RED + "\nATENÇÃO: Opção não implementada!" + ANSI_RESET));
        });
    }

    /**
     * Constructor para objectos da classe Menu (sem título e com List de opções).
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de listas é útil para definir menus dinâmicos.
     *
     * @param opcoes Uma lista de Strings com as opções do menu.
     */
    public Menu(List<String> opcoes) { this("Menu", opcoes); }

    /**
     * Constructor para objectos da classe Menu (com título e array de opções).
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * new Menu(String[]{
     *     "Opção 1",
     *     "Opção 2",
     *     "Opção 3"
     * })
     *
     * @param titulo O titulo do menu
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String titulo, String[] opcoes) {
        this(titulo, Arrays.asList(opcoes));
    }

    /**
     * Constructor para objectos da classe Menu (sem título e com array de opções).
     *
     * Cria um menu de opções sem event handlers.
     * Utilização de arrays é útil para definir menus estáticos. P.e.:
     *
     * new Menu(String[]{
     *     "Opção 1",
     *     "Opção 2",
     *     "Opção 3"
     * })
     *
     * @param opcoes Um array de Strings com as opções do menu.
     */
    public Menu(String[] opcoes) {
        this(Arrays.asList(opcoes));
    }

    // Métodos de instância

    /**
     * Adicionar opções a um Menu.
     *
     * @param name A opção a apresentar.
     * @param p A pré-condição da opção.
     * @param h O event handler para a opção.
     */
    public void option(String name, PreCondition p, Handler h) {
        this.opcoes.add(name);
        this.disponivel.add(p);
        this.handlers.add(h);
    }

    /**
     * Correr o menu uma vez.
     */
    public void runOnce() {
        int op;
        show();
        op = readOption();
        // testar pré-condição
        if (op>0 && !this.disponivel.get(op-1).validate()) {
            System.out.println(ANSI_RED + "Opção indisponível!" + ANSI_RESET);
        } else if (op>0) {
            // executar handler
            this.handlers.get(op-1).execute();
        }
    }

    /**
     * Correr o menu multiplas vezes.
     *
     * Termina com a opção 0 (zero).
     */
    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // testar pré-condição
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println(ANSI_RED + "Opção indisponível! Tente novamente." + ANSI_RESET);
            } else if (op>0) {
                // executar handler
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    /**
     * Método que regista uma uma pré-condição numa opção do menu.
     *
     * @param i índice da opção (começa em 1)
     * @param b pré-condição a registar
     */
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }

    /**
     * Método para registar um handler numa opção do menu.
     *
     * @param i indice da opção  (começa em 1)
     * @param h handlers a registar
     */
    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    // Métodos auxiliares
    /** Apresentar o menu */
    private void show() {
        System.out.println("\n *** "+this.titulo+" *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida */
    private int readOption() {
        int op;

        System.out.print("Opção: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println(ANSI_RED + "Opção Inválida!!!" + ANSI_RESET);
            op = -1;
        }
        return op;
    }





    //------------------------------

    /**
     * Método menu simples primeira versão
     * @param tam tamanho
     * @param str String
     * @param boo booleano
     * @return String menu simples
     */
    public String menu_simples_txt(int tam, String str,Boolean boo){
        StringBuilder stb=new StringBuilder();
        int tam_str=str.length();
        int tam_lado;
        if (boo){
            tam_lado=((tam-2-tam_str)/2)-1;
        }
        else {
            tam_lado=((tam-2-tam_str)/2);

        }
        for(int i=tam;i>0;i--){
            stb.append("*");
        }
        stb.append("\n");

        stb.append("*");
        int j=tam_lado;
        while (tam_lado>0){
            stb.append(" ");
            tam_lado--;
        }
        stb.append(str);
        int tam_act=j+1+str.length();
        while(tam_act<tam-1){
            stb.append(" ");
            tam_act++;
        }
        stb.append("*\n");
        for(int i=tam;i>0;i--){
            stb.append("*");
        }
        return stb.toString();
    }

    /**
     * Método menu simples segunda versão
     * @param tam tamanho
     * @param str String
     * @param boo booleano
     * @return String
     */
    public String menu_simples_txt_v2(int tam, String str,Boolean boo){
        StringBuilder stb = new StringBuilder();
        int tam_str=str.length();
        int tam_lado;
        if (boo){
            tam_lado=((tam-4-tam_str)/2)-1;
        }
        else {
            tam_lado=((tam-4-tam_str)/2);

        }
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        stb.append("\n");

        stb.append("||");
        int j=tam_lado;
        while (tam_lado>0){
            stb.append(" ");
            tam_lado--;
        }
        stb.append(str);
        int tam_act=j+3+str.length();
        while(tam_act<tam-1){
            stb.append(" ");
            tam_act++;
        }
        stb.append("||\n");
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        return stb.toString();
    }

    /**
     * Método menu simples terceira versão
     * @param tam tamanho
     * @param str1 String
     * @param boo booleano
     * @return String
     */
    public String menu_simples_txt_v3(int tam, String str1, String str2,Boolean boo){
        StringBuilder stb = new StringBuilder();
        int tam_str=str1.length();
        int tam_lado1,tam_lado2;
        if (boo){
            tam_lado1=((tam-4-str1.length())/2)-1;
            tam_lado2=((tam-4-str2.length())/2)-1;
        }
        else {
            tam_lado1=((tam-4-str1.length())/2);
            tam_lado2=((tam-4-str2.length())/2);
        }
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        stb.append("\n");

        stb.append("||");
        int j1=tam_lado1;
        while (tam_lado1>0){
            stb.append(" ");
            tam_lado1--;
        }
        stb.append(str1);
        int tam_act1=j1+3+str1.length();
        while(tam_act1<tam-1){
            stb.append(" ");
            tam_act1++;
        }
        stb.append("||\n");


        stb.append("||");
        int j2=tam_lado2;
        while (tam_lado2>0){
            stb.append(" ");
            tam_lado2--;
        }
        stb.append(str2);
        int tam_act2=j2+3+str2.length();
        while(tam_act2<tam-1){
            stb.append(" ");
            tam_act2++;
        }
        stb.append("||\n");

        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        return stb.toString();
    }

    /**
     * Método para as opções do menu
     * @param tam tamanho
     * @param str String
     * @param boo booleano
     * @param ops opções
     * @param zero zero
     * @return Menu das opções
     */
    public String menu_ops_txt_v2(int tam, String str,Boolean boo,Map<Integer,String> ops, Boolean zero){
        StringBuilder stb=new StringBuilder();

        //----------PARTE DE CIMA----------
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        stb.append("\n");

        //----------TITULO----------
        int tam_lado1;
        tam_lado1=((tam-4-str.length())/2);
        stb.append("||");
        int r=tam_lado1;
        while (tam_lado1>0){
            stb.append(" ");
            tam_lado1--;
        }
        stb.append(str);
        int tam_act1=r+4+str.length();
        while(tam_act1<tam){
            stb.append(" ");
            tam_act1++;
        }
        stb.append("||\n");

        //----------OPCOES----------
        int j=1;
        for (Map.Entry<Integer,String> entry: ops.entrySet()){
            if (entry.getKey()!=0){
                int mais=j+1;
                int tam_lado=(tam-4-3-ops.get(j).length()-String.valueOf(mais).length())/2;
                stb.append("||");
                int s=tam_lado;
                while (s>0){
                    stb.append(" ");
                    s--;
                }
                stb.append("[" + j + "] " + ops.get(j));
                int tam_act=tam_lado+3+3+String.valueOf(mais).length()+ops.get(j).length();
                while(tam_act<tam-1){
                    stb.append(" ");
                    tam_act++;
                }
                stb.append("||\n");
                j++;
            }
        }
        if (zero){
            stb.append("||");
            int tam_lado=(tam-4-3-ops.get(0).length()-String.valueOf(0).length())/2;
            int s=tam_lado;
            while (s>0){
                stb.append(" ");
                s--;
            }
            stb.append("[" + 0 + "] " + ops.get(0));
            int tam_act=tam_lado+3+3+1+ops.get(0).length();
            while(tam_act<tam-1){
                stb.append(" ");
                tam_act++;
            }
            stb.append("||\n");
        }
        //----------PARTE DE BAIXO----------
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        return stb.toString();
    }

    /**
     * Método para as opções do menu
     * @param tam tamanho
     * @param msg mensagem
     * @param str String
     * @param boo booleano
     * @param ops opções
     * @param zero zero
     * @return menu das opções
     */
    public String menu_ops_txt_v3(int tam,String msg, String str,Boolean boo,Map<Integer,String> ops, Boolean zero){
        StringBuilder stb=new StringBuilder();

        //----------PARTE DE CIMA----------
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        stb.append("\n");

        //----------TITULO----------
        int tam_lado1;
        tam_lado1=((tam-4-str.length())/2);
        stb.append("||");
        int r=tam_lado1;
        while (tam_lado1>0){
            stb.append(" ");
            tam_lado1--;
        }
        stb.append(str);
        int tam_act1=r+4+str.length();
        while(tam_act1<tam){
            stb.append(" ");
            tam_act1++;
        }
        stb.append("||\n");

        //----------OPCOES----------
        int j=1;
        for (Map.Entry<Integer,String> entry: ops.entrySet()){
            if (entry.getKey()!=0){
                int mais=j+1;
                int tam_lado=(tam-4-3-ops.get(j).length()-String.valueOf(mais).length())/2;
                stb.append("||");
                int s=tam_lado;
                while (s>0){
                    stb.append(" ");
                    s--;
                }
                stb.append("[" + j + "] " + ops.get(j));
                int tam_act=tam_lado+3+3+String.valueOf(mais).length()+ops.get(j).length();
                while(tam_act<tam-1){
                    stb.append(" ");
                    tam_act++;
                }
                stb.append("||\n");
                j++;
            }
        }
        if (zero){
            stb.append("||");
            int tam_lado=(tam-4-3-ops.get(0).length()-String.valueOf(0).length())/2;
            int s=tam_lado;
            while (s>0){
                stb.append(" ");
                s--;
            }
            stb.append("[" + 0 + "] " + ops.get(0));
            int tam_act=tam_lado+3+3+1+ops.get(0).length();
            while(tam_act<tam-1){
                stb.append(" ");
                tam_act++;
            }
            stb.append("||\n");
        }

        int tam_lado2;
        tam_lado2=((tam-4-msg.length())/2);
        stb.append("||");
        int r2=tam_lado2;
        while (tam_lado2>0){
            stb.append(" ");
            tam_lado2--;
        }
        stb.append(msg);
        int tam_act2=r2+4+msg.length();
        while(tam_act2<tam){
            stb.append(" ");
            tam_act2++;
        }
        stb.append("||\n");

        //----------PARTE DE BAIXO----------
        for(int i=tam;i>0;i--){
            stb.append("=");
        }
        return stb.toString();
    }

    /**
     * Método para dar print a uma string a azul
     * @param str String a dar print
     */
    public void print_Blue(String str){
        System.out.println(ANSI_BLUE + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a preto
     * @param str String a dar print
     */
    public void print_Black(String str){
        System.out.println(ANSI_BLACK + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a verde
     * @param str String a dar print
     */
    public void print_Green(String str){
        System.out.println(ANSI_GREEN + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a branco
     * @param str String a dar print
     */
    public void print_White(String str){
        System.out.println(ANSI_WHITE + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a ciano
     * @param str String a dar print
     */
    public void print_Cyan(String str){
        System.out.println(ANSI_CYAN + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a vermelho
     * @param str String a dar print
     */
    public void print_Red(String str){
        System.out.println(ANSI_RED + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a roxo
     * @param str String a dar print
     */
    public void print_Purple(String str){
        System.out.println(ANSI_PURPLE + str + ANSI_RESET);
    }

    /**
     * Método para dar print a uma string a amarelo
     * @param str String a dar print
     */
    public void print_Yellow(String str){
        System.out.println(ANSI_YELLOW + str + ANSI_RESET);
    }

    /**
     * Métodos para as barras no menu
     * @param tammenu tamanho do menu
     */
    public void menu_barras(int tammenu){
        for (int i=tammenu;i>0;i--){
            System.out.print("=");
        }
        System.out.print("\n");
    }

    public void menu_listar_allSD(int tammenu){
        int tamCol=(tammenu-2)/16;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("TIPO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str("ID",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str("CONSUMO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_YELLOW + this.tabela_str("CUSTO DE INSTALAÇÃO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_CYAN + this.tabela_str("ESTADO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_PURPLE + this.tabela_str("TEMPO DE CONSUMO",false,(tamCol*4)-1) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET
        );
    }
    /**
     * Método para listar um dispositivo inteligente
     * @param sd SmartDevice
     * @param tammenu tamanho do menu
     */
    public void listar_SD(SmartDevice sd, int tammenu){
        int tamCol=(tammenu-2)/16;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("SmartDevice",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(String.valueOf(sd.getId()),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str(String.valueOf(sd.getConsumo_diario()),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str(String.valueOf(sd.getCusto_instal()),false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str(sd.getString_by_estado(),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str(sd.getTempo_de_consumo().toString(),false,(tamCol*4)-1) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );

    }

    /**
     * Método para listar todas as lâmpadas inteligentes
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allSB(int tammenu){
        int tamCol=(tammenu-2)/20;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("TIPO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str("ID",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str("CONSUMO",false,(tamCol*2)-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str("CUSTO DE INSTALAÇÃO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str("ESTADO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str("TEMPO DE CONSUMO",false,(tamCol*4)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str("TONALIDADE",false,(tamCol*2)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str("DIMENSÃO",false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar uma lâmpada inteligente
     * @param sb SmartBulb
     * @param tammenu tamanho do menu
     */
    public void listar_SB(SmartBulb sb, int tammenu){
        int tamCol=(tammenu-2)/20;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("SmartBulb",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(String.valueOf(sb.getId()),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str(String.valueOf(sb.getConsumo_diario()),false,(tamCol*2)-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str(String.valueOf(sb.getCusto_instal()),false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str(sb.getString_by_estado(),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str(sb.getTempo_de_consumo().toString(),false,(tamCol*4)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str(sb.getTonalidade().toString(),false,(tamCol*2)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str(String.valueOf(sb.getDimensão()),false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar todas as colunas inteligentes
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allSS(int tammenu){
        int tamCol=(tammenu-2)/22;
        //30,10,20,30,20,40,20,30,20
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("TIPO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str("ID",false,(tamCol)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str("CONSUMO",false,(tamCol*2)-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str("CUSTO DE INSTALAÇÃO",false,(tamCol*3)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str("ESTADO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str("TEMPO DE CONSUMO",false,(tamCol*4)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str("VOLUME",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str("RADIO ONLINE",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("MARCA",false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar uma coluna inteligente
     * @param ss SmartSpeaker
     * @param tammenu tamanho do menu
     */
    public void listar_SS(SmartSpeaker ss, int tammenu){
        //SmartSpeaker sb=((SmartSpeaker) sd);
        int tamCol=(tammenu-2)/22;
        //30,10,20,30,20,40,20,30,20
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("SmartSpeaker",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(String.valueOf(ss.getId()),false,(tamCol)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str(String.valueOf(ss.getConsumo_diario()),false,(tamCol*2)-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str(String.valueOf(ss.getCusto_instal()),false,(tamCol*3)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str(ss.getString_by_estado(),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str(ss.getTempo_de_consumo().toString(),false,(tamCol*4)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str("" + ss.getVolume(),false,(tamCol*2)-2) + ANSI_RESET +  ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str(ss.getRadio_online(),false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str(ss.getMarca(),false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar todas as Câmaras inteligentes
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allSC(int tammenu){
        int tamCol=(tammenu-2)/19;
        //30,10,20,30,20,40,20,20
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("TIPO",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str("ID",false,(tamCol)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str("CONSUMO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str("CUSTO DE INSTALAÇÃO",false,(tamCol*3)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str("ESTADO",false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str("TEMPO DE CONSUMO",false,(tamCol*4)+8) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str("RESOLUÇÃO",false,(tamCol*2)+2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str("TAMANHO",false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar uma câmara inteligente
     * @param sc SmartCamera
     * @param tammenu tamanho do menu
     */
    public void listar_SC(SmartCamera sc,int tammenu){
        //SmartCamera sb=((SmartCamera) sd);
        int tamCol=(tammenu-2)/19;
        //30,10,20,30,20,40,20,20
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("SmartCamera",false,(tamCol*3)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(String.valueOf(sc.getId()),false,(tamCol)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str(String.valueOf(sc.getConsumo_diario()),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str(String.valueOf(sc.getCusto_instal()),false,(tamCol*3)) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str(sc.getString_by_estado(),false,(tamCol*2)-2) + ANSI_RESET + ANSI_WHITE + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_PURPLE + this.tabela_str(sc.getTempo_de_consumo().toString(),false,(tamCol*4)+8) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_WHITE + this.tabela_str(sc.getResolução(),false,(tamCol*2)+2) + ANSI_RESET +  ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLACK + this.tabela_str(String.valueOf(sc.getTamanho()),false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
        );
    }

    /**
     * Método para listar as informações de uma casa
     * @param tammenu tamanho do menu
     * @param num 0/1
     */
    public void menu_listar_casa(Integer tammenu, int num){
        int tamCol=(tammenu/3)-1;
        this.print_Black(this.menu_simples_txt_v2(tammenu,"INFOMAÇÕES DA CASA #" +num,false));
        this.menu_barras(tammenu);
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("NOME DO PROPRIETÁRIO",false,tamCol-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str("NIF DO PROPRIETÁRIO",false,tamCol-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str("NOME DO COMERCIALIZADOR",false,tamCol-1) +
                ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET
        );
        this.menu_barras(tammenu);
    }

    /**
     * Método para listar uma casa sem comercializador
     * @param tammenu tamanho menu
     * @param num 0/1
     */
    public void menu_listar_casa_noCOMER(Integer tammenu, int num){
        int tamCol=(tammenu/2)-1;
        this.print_Black(this.menu_simples_txt_v2(tammenu,"INFOMAÇÕES DA CASA #" +num,false));
        this.menu_barras(tammenu);
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("NOME DO PROPRIETÁRIO",false,tamCol-1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str("NIF DO PROPRIETÁRIO",false,tamCol-2) + ANSI_RESET +
                        ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET
        );
        this.menu_barras(tammenu);
    }

    /**
     * Método para listar as informações de uma casa
     * @param c Casa em questão
     * @param tammenu tamanho do menu
     */
    public void listar_casa(Casa c,Integer tammenu){
        int tamCol=(tammenu-1)/3;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str(c.getProprietario_nome(),false,tamCol-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str(String.valueOf(c.getProprietario_nif()),false,tamCol-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str(c.getComercia().getNome(),false,tamCol-2) + ANSI_RESET
                + ANSI_WHITE + "||" + ANSI_RESET
        );
        this.menu_barras(tammenu);
    }
    /**
     * Método para listar as informações de uma casa
     * @param info informação da casa
     * @param tammenu tamanho do menu
     */
    public void listar_casa(String info,Integer tammenu){
        int tamCol=(tammenu-1)/2;
        String[] casa=info.split(";",3);
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str(casa[0],false,tamCol-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str(String.valueOf(casa[1]),false,tamCol-3) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET
        );
        this.menu_barras(tammenu);
    }

    /**
     * Método para listar divisões da casa
     * @param c Casa
     * @param tammenu tamanho do menu
     */
    public void listar_casa_lDivisoes(Casa c,Integer tammenu){
        //linha esta bem acho mas experimentar
        if (c.get_all_devices_casa().isEmpty()){
            this.print_Red(this.menu_simples_txt_v2(tammenu,"ESTA CASA NÃO POSSUI DISPOSITIVOS",false));
            return;
        }
        this.print_Black(this.menu_simples_txt_v2(tammenu,"LISTA DE DIVISÕES",false));
        this.menu_barras(tammenu);
        int tamCol=(tammenu-2)/10;
        //20,30,10,20,20=100
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str("DIVISÃO",false,(tamCol*2)-2) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("TIPO",false,(tamCol*3)-2) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str("ID",false,(tamCol)+3) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_YELLOW + this.tabela_str("CONSUMO",false,(tamCol*2)-2) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_PURPLE + this.tabela_str("ESTADO",false,(tamCol*2)-2) + ANSI_RESET +
                ANSI_WHITE + "||" + ANSI_RESET
        );
        this.menu_barras(tammenu);
        for (Divisao div : c.getLista_divisao()){
            if ( div.getListaDevices().isEmpty() ){
                System.out.println(
                        ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(div.getNome(),false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str(div.getNome() + " NÃO POSSUI DISPOSITIVOS",false,(tamCol*8)-8) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
                );
            }
            else {
                int i=0;
                int tam=div.getListaDevices().size();
                for (SmartDevice sd : div.getListaDevices()){
                    if ( (tam%2==0) && i==tam/2){
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str(div.getNome(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str("",false,(tamCol*8)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str("",false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo_diario().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                    }
                    else if ((tam%2==1) && i==tam/2){
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str(div.getNome(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo_diario().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                    }
                    else {
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str("",false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo_diario().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );

                    }
                    i++;
                }

            }
            this.menu_barras(tammenu);

        }
        /*
        for (SmartDevice entry: c.get_all_devices_casa()){
            if (entry.getValue().isEmpty()){
                System.out.println(
                        ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(entry.getKey().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("DIVISÃO NÃO POSSUI DISPOSITIVOS",false,(tamCol*8)-8) + ANSI_RESET +
                        ANSI_WHITE + "||" + ANSI_RESET
                );
            }
            else {
                int i=0;
                for (SmartDevice sd : entry.getValue()){
                    if ( (entry.getValue().size()%2==0) && i==entry.getValue().size()/2){
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str(entry.getKey().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str("",false,(tamCol*8)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str("",false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                    }
                    else if ((entry.getValue().size()%2==1) && i==entry.getValue().size()/2){
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str(entry.getKey().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );
                    }
                    else {
                        System.out.println(
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_BLUE + this.tabela_str("",false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_RED + this.tabela_str(sd.getTipo(),false,(tamCol*3)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_GREEN + this.tabela_str(sd.getId(),false,(tamCol)+3) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_YELLOW + this.tabela_str(sd.getConsumo().toString(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET +
                                ANSI_PURPLE + this.tabela_str(sd.getEstado(),false,(tamCol*2)-2) + ANSI_RESET +
                                ANSI_WHITE + "||" + ANSI_RESET
                        );

                    }
                    i++;
                }

            }
            this.menu_barras(tammenu);

        }*/
    }

    /**
     * Método para listar todos os comercializadores
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allComer(Integer tammenu){
        int tamColuna=tammenu/4;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("NÚMERO",false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str("NOME",false,(tamColuna*2)-4) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str("PREÇO",false,tamColuna+1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET

        );
    }

    /**
     * Método para listar um comercializador e as suas informações
     * @param comer Comercializador
     * @param i
     * @param tammenu tamanho do menu
     */
    public void listar_comer(Comercializador comer, Integer i, Integer tammenu){
        int tamColuna=tammenu/4;
        //-"Comercializador".length()
        //comer.getNome().length()
        //-String.valueOf(comer.getPreço()).length()+5
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("#" + i,false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE +  this.tabela_str(comer.getNome(),false,(tamColuna*2)-4) + ANSI_RESET +  ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str(String.valueOf(comer.getPreço()), false,tamColuna+1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET


        );
    }

    /**
     * Método para listar todos os comercializadores sem o parâmetro num
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allComer_sem_num(Integer tammenu){
        int tamColuna=tammenu/4;
        this.menu_barras(tammenu);
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("COMERCIALIZDOR",false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE + this.tabela_str("NOME",false,(tamColuna*2)-4) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str("PREÇO",false,tamColuna+1) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET

        );
        this.menu_barras(tammenu);
    }

    /**
     * Método para listar um comercializador
     * @param comer comercializador
     * @param tammenu tamanho do menu
     */
    public void listar_comer(Comercializador comer, Integer tammenu){
        int tamColuna=(tammenu-2)/4;
        //-"Comercializador".length()
        //comer.getNome().length()
        //-String.valueOf(comer.getPreço()).length()+5
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_RED + this.tabela_str("Comercializador",false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_BLUE +  this.tabela_str(comer.getNome(),false,(tamColuna*2)-2) + ANSI_RESET +  ANSI_WHITE + "||" + ANSI_RESET +
                ANSI_GREEN + this.tabela_str(String.valueOf(comer.getPreço()), false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET


        );
    }

    /**
     * Método para listar as faturas
     * @param tammenu tamanho do menu
     */
    public void menu_listar_allFaturas(int tammenu){
        int tamColuna=tammenu/7;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str("COMERCIALIZADOR",false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str("DIRIGIDA A",false,(tamColuna*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str("VALOR",false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str("PERIODO",false,(tamColuna*2)+2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str("CONSUMO",false,tamColuna-5) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET

        );
    }

    /**
     * Método para listar as faturas
     * @param comer comercializador que possui a lista de faturas
     * @param tammenu tamanho do menu
     */
    public void listar_faturas(Comercializador comer, int tammenu){
        int tamColuna=(tammenu-2)/7;
        for (Fatura fat : comer.getLista_faturas()){
            System.out.println(
                    ANSI_WHITE + "||" + ANSI_RESET +
                            ANSI_RED + this.tabela_str(fat.getNome_comercializador(),false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                            ANSI_BLUE + this.tabela_str(fat.getNome_proprietario(),false,(tamColuna*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                            ANSI_GREEN + this.tabela_str(String.valueOf(fat.getValor()),false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                            ANSI_YELLOW + this.tabela_str(fat.getPeriodo().toString(),false,(tamColuna*2)+2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                            ANSI_CYAN + this.tabela_str(String.valueOf(fat.getConsumo()),false,tamColuna-5) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET

            );
        }


    }

    /**
     * Método para listar as faturas
     * @param fat fatura que contem os dados a imprimir
     * @param tammenu tamanho do menu
     */
    public void listar_fatura(Fatura fat, int tammenu){
        int tamColuna=(tammenu-2)/7;
        System.out.println(
                ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_RED + this.tabela_str(fat.getNome_comercializador(),false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_BLUE + this.tabela_str(fat.getNome_proprietario(),false,(tamColuna*2)-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_GREEN + this.tabela_str(String.valueOf(fat.getValor()),false,tamColuna-2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_YELLOW + this.tabela_str(fat.getPeriodo().toString(),false,(tamColuna*2)+2) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET +
                        ANSI_CYAN + this.tabela_str(String.valueOf(fat.getConsumo()),false,tamColuna-5) + ANSI_RESET + ANSI_WHITE + "||" + ANSI_RESET

        );

    }

    /**
     * Método auxiliar do menu
     * @param num número
     */
    public void menu_enters(int num){
        for (int i=0;i<num;i++){
            System.out.println("\n");
        }
    }

    /**
     * Possível método auxiliar para a simulação
     * @param tempo_inicial tempo inicial
     * @param tammenu tamanho do menu
     */
    public void menu_viagem_temporal(LocalDateTime tempo_inicial, int tammenu) {

        /*
        System.out.println( ANSI_GREEN + "****************************************************");
        System.out.println(              "*       INTRODUZA A DATA DE FIM DE SIMULAÇÃO       *");
        System.out.println(              "*   TEMPO ATUAL -> " + tempo_inicial.toString() + "   *");
        System.out.println(              "****************************************************" + ANSI_RESET);
        */
    }




    //----------FUNÇÕES AUXILIARES----------
    /*
    private String tabela_nome(int num){
        int tam = String.valueOf(num).length();
        StringBuilder stb=new StringBuilder();
        int tam_lado=((20-tam)/2);
        while(tam_lado>0){
            stb.append(" ");
            tam_lado--;
        }
        stb.append("#" + num);
        int tam2 = stb.toString().length();
        while(tam2!=20){
            stb.append(" ");
            tam2++;
        }
        return stb.toString();
    }*/

    /**
     * Método auxiliar para uma tabela
     * @param str String
     * @param boo booleano
     * @param tam tamanho
     * @return tabela
     */
    private String tabela_str(String str, boolean boo, Integer tam){
        StringBuilder stb = new StringBuilder();
        int tam_str= str.length();

        int tam_lado;
        if (boo) {
            tam_lado=((tam-tam_str)/2)-1;
        }
        else {
            tam_lado=((tam-tam_str)/2);
        }

        while(tam_lado>0){
            stb.append(" ");
            tam_lado--;
        }
        stb.append(str);
        int tam2 = stb.toString().length();
        while(tam2<tam){
            stb.append(" ");
            tam2++;
        }
        return stb.toString();
    }
    /*
    private String tabela_str_v2(String str, boolean boo, Integer tamCol){
        StringBuilder stb = new StringBuilder();
        int tam_str= str.length();

        int tam_lado;
        if (boo) {
            tam_lado=(tamCol/2)-1-tam_str;
        }
        else {
            tam_lado=(tamCol/2)-tam_str;
        }

        while(tam_lado>0){
            stb.append(" ");
            tam_lado--;
        }
        stb.append(str);
        int tam2 = stb.toString().length();
        while(tam2<tamCol){
            stb.append(" ");
            tam2++;
        }
        return stb.toString();
    }
*/
    private int tam_tabela(String str,int tam){

        return 1;
    }


    //----------ERROS----------
    public void erro() {
        System.out.println( ANSI_RED +  "************************************");
        System.out.println(             "*               ERRO               *");
        System.out.println(             "************************************" + ANSI_RESET);
    }
















    
}
