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
    
//    int inputAtMenuMain;
    int optionAtMenuMain;
    
    private int newUserID = 0;
    private String newName = null;
    private String newUsername = null;
    private String newPassword = null;
    
    SocialNetwork() {
        addAdm();                                          // adiciona user[0] como ADMIN
        MenuMain.populateMenuMain();
        firstRun();
        menuMain();
    }
    
    private void menuMain() {
        openMenuMain();
        socialNetworkOptions(optionAtMenuMain);
    }
    
    private void socialNetworkOptions(int num) {
        switch (num) {
            case 1:
                System.out.println("Log in!");
//                signIn();
                break;
            case 2:
                createNewUser();
                menuMain();
                break;
            case 3:
                System.out.println("Usuários atuais!");
                menuMain();
//                showAllUsers();
                break;
            case 4:
                close();
                break;
            default:
                System.out.println("Valor inválido! Digite um número!");
                menuMain();
                break;
        }
    }
    
    private void openMenuMain() {
        optionAtMenuMain = MenuMain.askInputAtMenuMain();
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
        getNewUserID();
        JOptionPane.showMessageDialog(null, "Seu ID de usuário é: " + newUserID);
        askNewName();
        JOptionPane.showMessageDialog(null, "Seu NOME é: " + newName);
        askNewUsername();
        JOptionPane.showMessageDialog(null, "Seu USERNAME é: " + newUsername);
        askNewPassword();
        JOptionPane.showMessageDialog(null, "Sua SENHA é: " + newPassword);
        users.add(new User(newUserID, newName, newUsername, newPassword));
        PrintFormat.printLine('=');
        System.out.printf("\n| %-4s | %-41s | %-21s | %-21s |", " ID ", "NOME", "USERNAME", "PASSWORD");
        PrintFormat.printLine('-');
        System.out.printf("\n| %04d | %-41s | %-21s | %-21s |",
                users.get(newUserID).getIdUser(),
                users.get(newUserID).getName(),
                users.get(newUserID).getUsername(),
                users.get(newUserID).getPassword());
        PrintFormat.printLine('=');
        PrintFormat.printMessage("Cadastrado com sucesso!!!");
        
//        followUp("Tecle ENTER para retornar ao MENU PRINCIPAL");
    
    }
    
    private void getNewUserID() {
        if (users.isEmpty()) {
            newUserID = 0;
        } else {
            newUserID = users.size();
        }
        JOptionPane.showMessageDialog(null, "Seu ID de usuário é: " + newUserID);
    }
    
    private void askNewName() {
        StringBuilder dialogNewUser = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewUser.append("\n\n> CADASTRE seu nome: \n\n");
        String nameInput = JOptionPane.showInputDialog(null, dialogNewUser.toString());
        boolean nameNotEmpty = verifyNotEmptyInput(nameInput);
        if (nameNotEmpty) {
            newName = nameInput;
        } else {
            askNewName();
        }
    }
    
    private void askNewUsername() {
        StringBuilder dialogNewUsername = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewUsername.append("\n\n> CADASTRE seu novo username exclusivo: \n\n");
        String usernameInput = JOptionPane.showInputDialog(null, dialogNewUsername.toString());
        boolean usernameNotEmpty = verifyNotEmptyInput(usernameInput);
        boolean usernameUnique = verifyUsernameAvialable(usernameInput);
        if (usernameNotEmpty && usernameUnique) {
            newUsername = usernameInput;
        } else {
            askNewUsername();
        }
    }
    private boolean verifyUsernameAvialable(String usernameInput) {
        boolean usernameAlreadyTaken = false;
        for (User user:users) {
            if (usernameInput.equalsIgnoreCase(users.get(user.idUser).getUsername())) {
                usernameAlreadyTaken = true;
            }
        }
        boolean usernameIsAvailable = !usernameAlreadyTaken;
        return usernameIsAvailable;
    }
    
    private boolean verifyNotEmptyInput(String userInput) {
        return (!userInput.isEmpty() && !userInput.isBlank());
    }
    
    private void askNewPassword() {
        StringBuilder dialogNewPassword = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewPassword.append("\n\n> CADASTRE sua senha: \n\n");
        String passwordInput = JOptionPane.showInputDialog(null,dialogNewPassword.toString());
        boolean passwordNotEmpty = verifyNotEmptyInput(passwordInput);
        boolean samePasswordInputTwice = verifySameInputTwice(passwordInput);
        if (passwordNotEmpty && samePasswordInputTwice) {
            newPassword = passwordInput;
        } else {
            askNewPassword();
        }
    }
    
    private boolean verifySameInputTwice(String input) {
        StringBuilder dialogNewPassword = new StringBuilder(DialogFormat.header(" CADASTRO de novo usuário "));
        dialogNewPassword.append("\n\n> Digite NOVAMENTE sua senha: \n\n");
        String inputAgain = JOptionPane.showInputDialog(null,dialogNewPassword.toString());
        return (input.equals(inputAgain));
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
}