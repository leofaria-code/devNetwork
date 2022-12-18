package br.com.leofaria.devNetwork.utility;

public class DialogFormat {
    private static final int DIALOG_width = 65;
    
    public static String header(String msg){
        StringBuilder s = new StringBuilder("\n");
        s.append(line('#'));
        s.append(message(msg));
        s.append(line('#'));
        return s.toString();
    }
    
    public static String line(char c){
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < DIALOG_width; i++) {
            s.append(String.valueOf(c));
        }
        s.append("\n");
        return s.toString();
    }
    private static String message(String msg){
        String s = String.format("%-20s%-20s%20s", " ", msg, " \n");
        return s;
    }

}
