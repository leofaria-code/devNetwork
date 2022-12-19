package br.com.leofaria.devNetwork.utility;

public class DialogFormat {
    private static final int DIALOG_width = 70;
    
    public static String header(String msg){
        return "\n" + line('#') +
                message(msg) +
                line('#');
    }
    
    public static String line(char c){
        return String.valueOf(c).repeat(DIALOG_width) +
                "\n";
    }
    private static String message(String msg){
        return String.format("%-2s%-80s%2s", " ", msg, " \n");
    }

}
