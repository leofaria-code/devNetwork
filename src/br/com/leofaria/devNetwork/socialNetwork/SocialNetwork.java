package br.com.leofaria.devNetwork.socialNetwork;

import br.com.leofaria.devNetwork.post.Post;
import br.com.leofaria.devNetwork.socialNetwork.menu.MenuMain;
import br.com.leofaria.devNetwork.utility.PrintFormat;
import br.com.leofaria.devNetwork.user.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SocialNetwork {
    Scanner input = new Scanner(System.in);
    
    List<Post> posts = new ArrayList<>();
    List<User> users = new ArrayList<>();
    
    SocialNetwork() {
        addAdm();
        MenuMain.populateMenuMain ();
        openMenuMain();
        
    }
    
    private void openMenuMain() {
        PrintFormat.clearConsole();
        welcomeStrange();
        printMenuMain();
        String optionAtMainMenu = input.nextLine().toUpperCase();
    }
    private void printMenuMain() {
//        String title = "MENU PRINCIPAL: o que você deseja fazer?";
//        PrintFormat.printHeader(title);
        MenuMain.testeVisual();
//        System.out.printf("\n| %-2s| %-6s| %-85s|", "#", "OPÇÃO","FUNÇÃO");
//        PrintFormat.printLine('-');
//        for (MenuMain mainMenuOption : MenuMain.mainMenu) {
//            System.out.printf("\n| %d |   %-4s| %-85s|",
//                    mainMenuOption.getIndexNum(), mainMenuOption.getIndexChar(),mainMenuOption.getDescription());
//        }
//        PrintFormat.printLine('=');
//        System.out.printf("\n%s ", askMenuOption);
    }
    
    public void welcomeStrange(){
        String msg = "Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA";
        PrintFormat.printHeader(msg);
    }
    private void addAdm() {
        final User ADMIN = new User(0,"ADM da SilvaSauro", "admin", "123");
        users.add(ADMIN);
    }
    
    private void createNewUser() {
        int newId = users.size();
        String title = " CADASTRO de novo usuário ";
        PrintFormat.printTitle(title);
        String newName = JOptionPane.showInputDialog("> Digite seu nome: ");
        String newUsername = askNewUsername();
        String newPassword = askNewPassword();
        users.add(new User(newId, newName, newUsername, newPassword));
        PrintFormat.printLine('=');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        PrintFormat.printLine('-');
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |",
                users.get(newId).getIdUser(),
                users.get(newId).getName(),
                users.get(newId).getUsername(),
                users.get(newId).getPassword());
        PrintFormat.printLine('=');
        PrintFormat.printMessage("Cadastrado com sucesso!!!");
//        followUp("Tecle ENTER para retornar ao MENU PRINCIPAL");
    
    }
    private String askNewUsername() {
        String usernameInput = JOptionPane.showInputDialog("> Digite um novo username: ");
//        if (verifyNewUsername(usernameInput)) {
//            askNewUsername();
//        } else {
//            usernameNotTaken = usernameInput;
//        }
        String usernameNotTaken = usernameInput;
        return usernameNotTaken;
    }
    private String askNewPassword() {
        String passwordInput = JOptionPane.showInputDialog("> Digite sua nova senha: ");
        String passwordInputCheck = JOptionPane.showInputDialog("> Digite novamente sua senha: ");
        if (!passwordInputCheck.equals(passwordInput)) {
            String text = "As senhas digitadas não coincidem. Cadastre nova senha!";
            PrintFormat.printMessage(text);
            System.out.println();
            askNewPassword();
        }
        return passwordInput;
    }
    
}
