package br.com.leofaria.devNetwork.socialNetwork.menu;

import br.com.leofaria.devNetwork.utility.DialogFormat;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public record MenuMain(int indexNum, char indexChar, String description) {
    
    static List<MenuMain> mainMenu = new ArrayList<>();
    public static String dialogMenuMain;
    
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
    
    static void showMenuMain() {
        StringBuilder menu = new StringBuilder(DialogFormat.header(" MENU PRINCIPAL: o que você deseja fazer? "));
        for (MenuMain mainMenuOption : mainMenu) {
            String menu_line = "\n" + mainMenuOption.indexNum
                    + " - " + mainMenuOption.indexChar
                    + " : " + mainMenuOption.description;
            menu.append(menu_line);
        }
        menu.append("\n\n>DIGITE o número correspondente à opção escolhida: \n\n");
        dialogMenuMain = menu.toString();
    }
    
    public static int askInputAtMenuMain() {
        showMenuMain();
        
        int inputNum = 9999999;
        String inputString;
        
        inputString = JOptionPane.showInputDialog(null, dialogMenuMain).toUpperCase();
    
        switch (inputString) {
            case "E", "1":
                inputNum = 1;
                break;
            case "C", "2":
                inputNum = 2;
                break;
            case "L", "3":
                inputNum = 3;
                break;
            case "X", "4":
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
