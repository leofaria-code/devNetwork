package br.com.leofaria.devNetwork.socialNetwork.menu;

import br.com.leofaria.devNetwork.utility.DialogFormat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuMain {
    
    private final int indexNum;
    private final char indexChar;
    private final String description;
    
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
        mainMenu.add(ENTRAR); mainMenu.add(CADASTRAR); mainMenu.add(LISTAR); mainMenu.add(FECHAR);
    }
    
    public static int askInputAtMenuMain() {
        StringBuilder menu = new StringBuilder(DialogFormat.header(" MENU PRINCIPAL: o que você deseja fazer? "));
        for (MenuMain mainMenuOption : mainMenu) {
            String menu_line = "\n"+ mainMenuOption.indexNum
                    + " - " + mainMenuOption.indexChar
                    + " : " + mainMenuOption.description;
            menu.append(menu_line);
        }
        menu.append("\n\n>DIGITE o número correspondente à opção escolhida: \n\n");
        
        String dialogMenu = menu.toString();
        
        int inputNum = 99;
        String inputString;
        
        inputString = JOptionPane.showInputDialog(null, dialogMenu);
    
        switch (inputString) {
            case "e", "E", "1":
                inputString = inputString.toUpperCase();
                inputNum = 1;
                break;
            case "c", "C", "2":
                inputString = inputString.toUpperCase();
                inputNum = 2;
                break;
            case "l", "L", "3":
                inputString = inputString.toUpperCase();
                inputNum = 3;
                break;
            case "x", "X", "4":
                inputString = inputString.toUpperCase();
                inputNum = 4;
                break;
            default:
                System.out.println("Valor inválido! Digite um número!");
                askInputAtMenuMain();
                break;
        }
        
        return inputNum;
        
    }
    
}
