package br.com.leofaria.socialNetwork;

import br.com.leofaria.user.User;
import br.com.leofaria.utility.PrintFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SocialNetwork {
    Scanner input = new Scanner(System.in);
    
    List<User> users = new ArrayList<>();
    List<User> posts = new ArrayList<>();
    
    static User ADMIN = new User(0,"ADM da Silva", "admin", "123");
    
    SocialNetwork() {
        addAdm();
        createNewUser();
    }
    
    private void addAdm() {
        users.add(ADMIN);
    }
    
    private void createNewUser() {
        int newId = users.size();
        String title = " CADASTRO de novo usuário ";
        PrintFormat.printTitle(title);
        System.out.print("\n> Digite seu nome: ");
        String newName = input.nextLine();
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
        System.out.print("> Digite um novo username: ");
        String usernameInput = input.nextLine();
//        if (verifyNewUsername(usernameInput)) {
//            askNewUsername();
//        } else {
//            usernameNotTaken = usernameInput;
//        }
        String usernameNotTaken = usernameInput;
        return usernameNotTaken;
    }
    private String askNewPassword() {
        System.out.print("> Digite sua nova senha: ");
        String passwordInput = input.next();
        input.nextLine();
        System.out.print("> Digite novamente sua senha: ");
        String passwordInputCheck = input.next();
        input.nextLine();
        if (!passwordInputCheck.equals(passwordInput)) {
            String text = "As senhas digitadas não coincidem. Cadastre nova senha!";
//            printMessage(text);
            System.out.println();
            askNewPassword();
        }
        return passwordInput;
    }
    
}
