package br.com.leofaria.devNetwork.socialNetwork;

import br.com.leofaria.devNetwork.post.Post;
import br.com.leofaria.devNetwork.socialNetwork.menu.MenuMain;
import br.com.leofaria.devNetwork.utility.DialogFormat;
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
        MenuMain.populateMenuMain();
        firstRun();
        int inputAtMenuMain = openMenuMain();
        socialNetworkOptions(inputAtMenuMain);
    }
    
    private void socialNetworkOptions(int num) {
        switch (num) {
            case 1:
                System.out.println("Log in!");
//                signIn();
                break;
            case 2:
                createNewUser();
                break;
            case 3:
                System.out.println("Usuários atuais!");
//                showAllUsers();
                break;
            case 4:
                close();
                break;
            default:
                System.out.println("Valor inválido! Digite um número!");
                break;
        }
    }
    
    private void close() {
        String msg = "O programa será fechado e os dados não serão salvos!!!!!!!";
        PrintFormat.printHeader(msg);
        System.out.printf("\n%s " + "%s ", "| Digite SAIR para confirmar", "ou tecle ENTER para voltar ao MENU PRINCIPAL:");
        String exit = input.nextLine().toUpperCase();
        if (exit.equals("SAIR")) {
            System.exit(0);
        } else {
            close();
        }
    }
    
    private int openMenuMain() {
        int optionAtMenuMain = MenuMain.askInputAtMenuMain();
        return optionAtMenuMain;
    }
    
    private void firstRun() {
        PrintFormat.clearConsole();
        welcomeStrange();
        PrintFormat.clearConsole();
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
//        String title = " CADASTRO de novo usuário ";
//        PrintFormat.printTitle(title);
//        inputString = JOptionPane.showInputDialog(null, dialogMenu);
//        String newName = JOptionPane.showInputDialog("> Digite seu nome: ");
        StringBuilder dialogNewUser = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewUser.append("\n\n> DIGITE seu nome: \n\n");
        String newName = JOptionPane.showInputDialog(null, dialogNewUser.toString());
        String newUsername = askNewUsername().toLowerCase();
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
        StringBuilder dialogNewUsername = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewUsername.append("\n\n> CADASTRE seu novo username exclusivo: \n\n");
        String usernameInput = null;
        String usernameInput1 = JOptionPane.showInputDialog(null, dialogNewUsername.toString()).toLowerCase();
        usernameInput = usernameInput1;
        String usernameValidated = usernameInput;
        if (usernameInput.isEmpty()) {
            System.out.println("\n USERNAME NÃO PODE SER VAZIO!!! \n");
            askNewUsername();
        } else {
            if (!verifyUsernameAvialable(usernameInput)){
                System.out.println("\n ESSE USERNAME JÁ EXISTE! Escolha outro! \n");
                askNewUsername();
            } else {
                System.out.println("\n PERFEITO, USERNAME DISPONÍVEL \n");
                usernameValidated = usernameInput;
            }
        }
//        } else if (!verifyUsernameAvialable(usernameInput)) {
//            System.out.println("\n ESSE USERNAME JÁ EXISTE! Escolha outro! \n");
//            askNewUsername();
//        } else {
//            System.out.println("\n PERFEITO, USERNAME DISPONÍVEL \n");
//            usernameNotTaken = usernameInput;
//        }
        return usernameValidated;
    }
    
    private boolean verifyUsernameAvialable(String usernameInput) {
        boolean usernameTaken = false;
        for (User user:users) {
            if (usernameInput.equals(users.get(user.idUser).getUsername())) {
                usernameTaken = true;
            }
        }
        if (usernameTaken) {
            System.out.println("\n ESSE USERNAME JÁ EXISTE\n");
        } else {
            System.out.println("\n PERFEITO, USERNAME DISPONÍVEL\n");
        }
        boolean usernameAvailable = !usernameTaken;
        return usernameAvailable;
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
