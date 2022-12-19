package br.com.leofaria.devNetwork.socialNetwork;

import br.com.leofaria.devNetwork.socialNetwork.menu.EnumMenuOptions;
import br.com.leofaria.devNetwork.socialNetwork.menu.MenuMain;
import br.com.leofaria.devNetwork.utility.DialogFormat;
import br.com.leofaria.devNetwork.user.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class SocialNetwork {
//    Scanner input = new Scanner(System.in);
    
    public List<MenuMain> menuMain = new ArrayList<>();
    public List<User> users = new ArrayList<>();
//    public List<Post> posts = new ArrayList<>();
    
    private String dialogMenuMain;
    private String inputString;
    private int inputNum;
    private int optionAtMenuMain;
    
    private int newUserID = 0;
    private String newName = null;
    private String newUsername = null;
    private String newPassword = null;
    
    SocialNetwork() {
        addAdm();                                          // adiciona user[0] como ADMIN
        firstRun();
        constructMenuMain();
        menuMain();
    }
    
    private void addAdm() {
        final User ADMIN = new User(0,"ADM da SilvaSauro", "admin", "123");
        users.add(ADMIN);
    }
    private void firstRun() {
        clearConsole();
        welcomeStrange();
    }
    public void welcomeStrange(){
        String msg = " Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA ";
        String welcomeMSG = DialogFormat.header(msg);
        JOptionPane.showMessageDialog(null, welcomeMSG);
    }
    public void constructMenuMain() {
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
        menuMain.add(ENTRAR);
        menuMain.add(CADASTRAR);
        menuMain.add(LISTAR);
        menuMain.add(FECHAR);
    }
    private void menuMain() {
        getInputAtMenuMainConvertedtoNum();
        socialNetworkActions(optionAtMenuMain);
    }
    public void getInputAtMenuMainConvertedtoNum() {
        showMenuMain();
        inputString = JOptionPane.showInputDialog(null, dialogMenuMain);
        boolean inputNotNull = verifyNotNullInput(inputString);
        if (!inputNotNull) {
            close();
        }
        boolean inputNotEmpty = verifyNotEmptyOrBlankInput(inputString);
        if (inputNotEmpty) {
            inputString = inputString.toUpperCase();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Opção VAZIA ou BRANCA é inválida!"
                    + "\nTente novamente!");
            getInputAtMenuMainConvertedtoNum();
        }
        optionAtMenuMain = convertInputStringToNum(inputString);
    }
    private void showMenuMain() {
        String menuTitle = " MENU PRINCIPAL: o que você deseja fazer? ";
        StringBuilder menu = new StringBuilder(DialogFormat.header(menuTitle));
        for (MenuMain mainMenuOption : menuMain) {
            String menu_line = "\n" + mainMenuOption.indexNum
                    + " - " + mainMenuOption.indexChar
                    + " : " + mainMenuOption.description;
            menu.append(menu_line);
        }
        menu.append("\n\n>DIGITE o 'número' ou o 'caractere' correspondente à opção escolhida: \n\n");
        dialogMenuMain = menu.toString();
    }
    private int convertInputStringToNum(String inputString) {
        switch (inputString) {
            case "E", "1" -> inputNum = 1;
            case "C", "2" -> inputNum = 2;
            case "L", "3" -> inputNum = 3;
            case "X", "4" -> inputNum = 4;
            default -> {
                JOptionPane.showMessageDialog(null,
                        "Opção '%S' inválida! Tente novamente".formatted(inputString));
                getInputAtMenuMainConvertedtoNum();
            }
        }
        return inputNum;
    }
    private void socialNetworkActions(int num) {
        switch (num) {
            case 1 -> System.out.println("Log in!");
//                signIn();
            case 2 -> createNewUser();
            case 3 -> showAllUsers();
            case 4 -> close();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente");
        }
        menuMain();
    }
    int verifyUsername(String usernameInput) {
        String usernameToVerify = usernameInput;
        boolean validUser = false;
        int idVerified = -1;
        for (int i = 0; i < users.size(); i++) {
            if (usernameToVerify.equals(users.get(i).getUsername())) {
                validUser = true;
                idVerified = i;
            }
        }
        if (!validUser) {
            System.out.println("Usuário não cadastrado!");
//            verifyUsername();
        }
        return (idVerified);
    }
    
    int verifyPassword(int p) {
        String title = "VERIFICAÇÃO de senha";
        String concat = "USERNAME: " + users.get(p).getUsername();
        System.out.print("\n> Digite sua senha: ");
        String passwordInput = null;
//                = input.next();
        boolean validPassword = false;
        int idPassword = -1;
        if (!passwordInput.equals(users.get(p).getPassword())) {
            System.out.println("Acesso NEGADO!!! Tente novamente!");
        } else {
            validPassword = true;
            idPassword = p;
        }
        if (!validPassword) {
            verifyPassword(p);
        }
        return idPassword;
    }
    
    private void createNewUser() {
        getNewUserID();
        askNewName();
        JOptionPane.showMessageDialog(null, "Seu NOME é: " + newName);
        askNewUsername();
        JOptionPane.showMessageDialog(null, "Seu USERNAME é: " + newUsername);
        askNewPassword();
        JOptionPane.showMessageDialog(null, "Sua SENHA é: " + newPassword);
        String userToConfirm = String.format("\n ID: %d \n NOME: %s \n USERNAME: %s \n SENHA: %s \n",
                newUserID, newName, newUsername, newPassword);
        String msgToConfirm = DialogFormat.header(" CONFIRMAÇÃO DE CADASTRO? ") + userToConfirm
                + "\n\n Seus dados estão corretos?  \n\n";
        int confirmExit = confirmationDialog(msgToConfirm);
        if (confirmExit == JOptionPane.YES_OPTION) {
            users.add(new User(newUserID, newName, newUsername, newPassword));
        } else {
            menuMain();
        }
        showThisUser(newUserID);
    }
    private String listThisUser(int id) {
        return String.format("\n ID: %06d - NOME: %-50s - USERNAME: %-20s",
                users.get(id).getIdUser(), users.get(id).getName(), users.get(id).getUsername());
    }
    private void showThisUser(int id) {
        String title = DialogFormat.header("CADASTRO CONFIRMADO!");
        String thisUser = listThisUser(id);
        JOptionPane.showMessageDialog(null, title + thisUser);
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
        String nameInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE seu nome: \n\n");
        boolean inputNotNull = verifyNotNullInput(nameInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean nameNotEmpty = verifyNotEmptyOrBlankInput(nameInput);
        if (nameNotEmpty) {
            newName = nameInput;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Você precisa preencher o seu NOME!"
                            + "\nTente novamente.");
            askNewName();
        }
    }
    private void askNewUsername() {
        String usernameInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE seu novo username exclusivo: \n\n");
        boolean inputNotNull = verifyNotNullInput(usernameInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean usernameNotEmpty = verifyNotEmptyOrBlankInput(usernameInput);
        boolean usernameUnique = verifyUsernameAvialable(usernameInput);
        if (usernameNotEmpty && usernameUnique) {
            newUsername = usernameInput.toLowerCase();
        } else {
            if (!usernameUnique) {
                JOptionPane.showMessageDialog(null,
                        "Esse 'username' já existe!" + "\nTente um diferente.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Seu 'username' não pode ser 'VAZIO'!" + "\nTente novamente.");
            }
            askNewUsername();
        }
    }
    private void askNewPassword() {
        String passwordInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE sua senha: \n\n");
        boolean inputNotNull = verifyNotNullInput(passwordInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean passwordNotEmpty = verifyNotEmptyOrBlankInput(passwordInput);
        boolean samePasswordInputTwice = verifySameInputTwice(passwordInput);
        if (passwordNotEmpty && samePasswordInputTwice) {
            newPassword = passwordInput;
        } else {
            if (!samePasswordInputTwice) {
                JOptionPane.showMessageDialog(null,
                        "As senhas não coincidem!" + "\nTente novamente.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "A sua senha não pode ser 'VAZIA'!" + "\nTente novamente.");
            }
            askNewPassword();
        }
    }
    private void showAllUsers() {
        String title = DialogFormat.header(" USUÁRIOS CADASTRADOS ");
        StringBuilder allUsers = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            allUsers.append(listThisUser(i));
        }
        JOptionPane.showMessageDialog(null, title + allUsers);
    }
    boolean verifyNotNullInput(String input) {
        return (input != null);
    }
    public boolean verifyNotEmptyOrBlankInput(String userInput) {
        return (!userInput.isEmpty() && !userInput.isBlank());
    }
    private boolean verifyUsernameAvialable(String usernameInput) {
        boolean usernameAlreadyTaken = false;
        for (User user:users) {
            if (usernameInput.equalsIgnoreCase(users.get(user.idUser).getUsername())) {
                usernameAlreadyTaken = true;
                break;
            }
        }
        return !usernameAlreadyTaken;
    }
    private boolean verifySameInputTwice(String input) {
        String inputAgain = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> Digite NOVAMENTE a mesma senha: \n\n");
        return (input.equals(inputAgain));
    }
    private void close() {
        String msgToConfirm = DialogFormat.header(" SAIR DA APLICAÇÃO? ") +
                "\n\n> Você deseja fechar o programa? \n\n";
        int confirmExit = confirmationDialog(msgToConfirm);
        if (confirmExit == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            menuMain();
        }
    }
    public int confirmationDialog (String question) {
        return JOptionPane.showConfirmDialog(null, question);
    }
    public void clearConsole(){
        for (int i = 0; i < 6; ++i)
            System.out.println();
    }
}