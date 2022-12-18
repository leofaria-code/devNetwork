package br.com.leofaria.devNetwork.utility;

public class PrintFormat {
    static final int PRINT_width = 100;
    
    public static void printTitle(String msg){
        printLine('=');
        printMessage(msg);
        printLine('=');
    }
    public static void printHeader(String msg){
        printLine('#');
        printMessage(msg);
        printLine('#');
    }
    public static void printLine(char c){
        System.out.println();
        for (int i = 0; i < PRINT_width; i++) {
            System.out.printf("%c", c);
        }
    }
    public static void printMessage(String msg){
        System.out.printf("\n%s %-94s %3s", "|", msg, "|");
    }
    public static void clearConsole(){
        for (int i = 0; i < 6; ++i)
            System.out.println();
    }
}