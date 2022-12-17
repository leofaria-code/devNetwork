package br.com.leofaria.devNetwork.socialNetwork.menu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuMain {
    
    private int indexNum;
    private char indexChar;
    private String description;
    
    public static List<MenuMain> mainMenu = new ArrayList<>();
    
    public MenuMain(int indexNum, char indexChar, String description) {
        this.indexNum = indexNum;
        this.indexChar = indexChar;
        this.description = description;
    }
    
    public int getIndexNum() {
        return indexNum;
    }
    
    public char getIndexChar() {
        return indexChar;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static List<MenuMain> getMainMenu() {
        return mainMenu;
    }
    
    public static void populateMenuMain() {
        final MenuMain ENTRAR = new MenuMain(EnumMenuOptions.ENTRAR.getIndexNum(),
                EnumMenuOptions.ENTRAR.getIndexChar(),
                EnumMenuOptions.ENTRAR.getDescription());
        final MenuMain CADASTRAR = new MenuMain(EnumMenuOptions.CADASTRAR.getIndexNum(),
                EnumMenuOptions.CADASTRAR.getIndexChar(),
                EnumMenuOptions.CADASTRAR.getDescription());
        final MenuMain LISTAR = new MenuMain(EnumMenuOptions.LISTAR.getIndexNum(),
                EnumMenuOptions.LISTAR.getIndexChar(),
                EnumMenuOptions.LISTAR.getDescription());
        final MenuMain FECHAR = new MenuMain(EnumMenuOptions.FECHAR.getIndexNum(),
                EnumMenuOptions.FECHAR.getIndexChar(),
                EnumMenuOptions.FECHAR.getDescription());
        
        mainMenu.add(ENTRAR);
        mainMenu.add(CADASTRAR);
        mainMenu.add(LISTAR);
        mainMenu.add(FECHAR);
    }
    
    public static void testeVisual() {
        String menu = "MENU PRINCIPAL: o que você deseja fazer?\n";
        for (MenuMain mainMenuOption : mainMenu) {
            String menu_num = String.valueOf(mainMenuOption.indexNum);
            String menu_char = String.valueOf(mainMenuOption.indexChar);
            String menu_description = mainMenuOption.description.toString();
            String menu_line = "\n"+ menu_num + " - " + menu_char + " : " + menu_description;
            menu += menu_line;
        }
        menu += "\n\n> Digite o caractere ou número correspondente à opção escolhida:\n";
        
        String strOpcao = JOptionPane.showInputDialog(null,menu).toUpperCase();
    
        System.out.println("\nDigitou: " + strOpcao);



//        String menu1_num = String.valueOf(mainMenu.get(0).indexNum);
//        String menu1_char = String.valueOf(mainMenu.get(0).indexChar);
//        String menu1_description = mainMenu.get(0).description.toString();
//
//        String menu1 = menu1_num + " - " + menu1_char + " : " + menu1_description;
    }
    
}
