package br.com.leofaria.devNetwork;

import br.com.leofaria.devNetwork.menuMain.MenuMain;
import br.com.leofaria.devNetwork.menuMain.MenuMainOptions;
import br.com.leofaria.devNetwork.user.User;
import br.com.leofaria.devNetwork.utility.DialogFormat;
import br.com.leofaria.devNetwork.utility.TimeStamp;
import br.com.leofaria.devNetwork.utility.Verify;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    public List<MenuMain> menuMain = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    
    private int newUserID = 0;
    private String newName = null;
    private String newUsername = null;
    private String newPassword = null;
    
    private String dialogMenuMain;
    private int inputNum;
    private int optionAtMenuMain;
    private int idUserOfThisUsername;
    private int idUserAfterPasswordChecked;
    
    private static SocialNetwork instancia = null;
    
    public static void main(String[] args) {
        SocialNetwork instancia = obterInstancia();
    }
    
    public static synchronized SocialNetwork obterInstancia() {
        if (instancia == null) instancia = new SocialNetwork();
        return instancia;
    }
    
    SocialNetwork() {
        run();
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    private void run() {
        if (menuMain.isEmpty()) {
            constructMenuMain();
        }
        notFirstRun();
    }
    
    private void notFirstRun() {
        addAdm();
        welcomeStranger();
        menuMain();
    }
    
    private void addAdm() {                     // adiciona user[0] como ADMIN
        if (getUsers().isEmpty()) {
            final User ADMIN = new User(0, "ADM da Silva Sauro", "admin", "123");
            getUsers().add(ADMIN);
        }
    }
    
    public void constructMenuMain() {
        final MenuMain ENTRAR = new MenuMain(MenuMainOptions.ENTRAR.getIndexNum(),
                MenuMainOptions.ENTRAR.getIndexChar(),
                MenuMainOptions.ENTRAR.getDescription());
        final MenuMain CADASTRAR = new MenuMain(MenuMainOptions.CADASTRAR.getIndexNum(),
                MenuMainOptions.CADASTRAR.getIndexChar(),
                MenuMainOptions.CADASTRAR.getDescription());
        final MenuMain LISTAR = new MenuMain(MenuMainOptions.LISTAR.getIndexNum(),
                MenuMainOptions.LISTAR.getIndexChar(),
                MenuMainOptions.LISTAR.getDescription());
        final MenuMain FECHAR = new MenuMain(MenuMainOptions.FECHAR.getIndexNum(),
                MenuMainOptions.FECHAR.getIndexChar(),
                MenuMainOptions.FECHAR.getDescription());
        menuMain.add(ENTRAR);
        menuMain.add(CADASTRAR);
        menuMain.add(LISTAR);
        menuMain.add(FECHAR);
    }
    
    public void welcomeStranger() {
        String msg = " Seja bem vindo à rede social SINQUIA #dev_makers2, Let's Code by ADA ";
        String welcomeMSG = DialogFormat.header(msg);
        JOptionPane.showMessageDialog(null, welcomeMSG);
    }
    
    public void menuMain() {
        getInputAtMenuMainConvertedtoNum();
        socialNetworkActions(optionAtMenuMain);
    }
    
    public void getInputAtMenuMainConvertedtoNum() {
        showMenuMain();
        String inputString = JOptionPane.showInputDialog(null, dialogMenuMain);
        boolean inputNotNull = Verify.verifyNotNullInput(inputString);
        if (!inputNotNull) {
            close();
        }
        boolean inputNotEmpty = Verify.verifyNotEmptyOrBlankInput(inputString);
        if (inputNotEmpty) {
            inputString = inputString.toUpperCase();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Opção VAZIA ou BRANCA é inválida!" + "\nTente novamente!");
            getInputAtMenuMainConvertedtoNum();
        }
        this.optionAtMenuMain = convertInputStringToNum(inputString);
    }
    
    private String showMenuMain() {
        String menuTitle = " MENU PRINCIPAL: o que você deseja fazer? ";
        StringBuilder menu = new StringBuilder(DialogFormat.header(menuTitle));
        for (MenuMain mainMenuOption : menuMain) {
            String menuLine = "\n" + mainMenuOption.indexNum
                    + " - " + mainMenuOption.indexChar
                    + " : " + mainMenuOption.description;
            menu.append(menuLine);
        }
        menu.append("\n\n>DIGITE o 'número' ou o 'caractere' correspondente à opção escolhida: \n\n");
        this.dialogMenuMain = menu.toString();
        return this.dialogMenuMain;
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
            case 1 -> signIn();
            case 2 -> createNewUser();
            case 3 -> showAllUsers();
            case 4 -> close();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente");
        }
        menuMain();
    }
    
    private void signIn() {
        verifyUsername();
        verifyPassword(idUserOfThisUsername);
        openUserMenu(idUserAfterPasswordChecked);
    }
    
    void verifyUsername() {
        String usernameInputToLogin = JOptionPane.showInputDialog(null,
                DialogFormat.header(" LOGIN de usuário cadastrado ")
                        + "\n\n> DIGITE seu username: \n\n");
        boolean inputNotNull = Verify.verifyNotNullInput(usernameInputToLogin);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean validUser = false;
        idUserOfThisUsername = -1;
        for (int i = 0; i < getUsers().size(); i++) {
            if (usernameInputToLogin.equals(getUsers().get(i).getUsername())) {
                validUser = true;
                idUserOfThisUsername = i;
                break;
            }
        }
        if (!validUser) {
            System.out.println("Usuário não cadastrado!");
            verifyUsername();
        }
    }
    
    void verifyPassword(int userSelectedID) {
        String passwordInputToLogin = JOptionPane.showInputDialog(null,
                DialogFormat.header(" LOGIN de usuário cadastrado ")
                        + "USERNAME: " + getUsers().get(userSelectedID).getUsername()
                        + "\n\n> DIGITE sua senha: \n\n");
        boolean inputNotNull = Verify.verifyNotNullInput(passwordInputToLogin);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean passwordMatch = false;
        idUserAfterPasswordChecked = -1;
        if (!passwordInputToLogin.equals(getUsers().get(userSelectedID).getPassword())) {
            System.out.println("Acesso NEGADO!!! Tente novamente!");
        } else {
            passwordMatch = true;
            idUserAfterPasswordChecked = userSelectedID;
        }
        if (!passwordMatch) {
            verifyPassword(userSelectedID);
        }
    }
    
    private void openUserMenu(int userLogedIn) {
        System.out.printf("\nMenu do Usuário: %s", getUsers().get(userLogedIn).getName());
        User.menuUser();
//        User.main(getUsers().get(userLogedIn));
    }
    
    private void createNewUser() {
        getNewUserID();
        askNewName();
        askNewUsername();
        askNewPassword();
        String userToConfirm = String.format("\n ID: %d \n NOME: %s \n USERNAME: %s \n SENHA: %s \n",
                newUserID, newName, newUsername, newPassword);
        String msgToConfirm = DialogFormat.header(" CONFIRMAÇÃO DE CADASTRO? ") + userToConfirm
                + "\n\n Seus dados estão corretos?  \n\n";
        int confirmExit = confirmationDialog(msgToConfirm);
        if (confirmExit == JOptionPane.YES_OPTION) {
            getUsers().add(new User(newUserID, newName, newUsername, newPassword));
        } else {
            menuMain();
        }
        showThisUser(newUserID);
    }
    
    private void getNewUserID() {
        if (getUsers().isEmpty()) {
            this.newUserID = 0;
        } else {
            this.newUserID = getUsers().size();
        }
        JOptionPane.showMessageDialog(null, "Seu ID de usuário é: " + newUserID);
    }
    
    private void askNewName() {
        String nameInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE seu nome: \n\n");
        boolean inputNotNull = Verify.verifyNotNullInput(nameInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean nameNotEmpty = Verify.verifyNotEmptyOrBlankInput(nameInput);
        if (nameNotEmpty) {
            this.newName = nameInput;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Você precisa preencher o seu NOME!"
                            + "\nTente novamente.");
            askNewName();
        }
        JOptionPane.showMessageDialog(null, "Seu NOME é: " + newName);
    }
    
    private void askNewUsername() {
        String usernameInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE seu novo username exclusivo: \n\n");
        boolean inputNotNull = Verify.verifyNotNullInput(usernameInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean usernameNotEmpty = Verify.verifyNotEmptyOrBlankInput(usernameInput);
        boolean usernameUnique = verifyUsernameAvialable(usernameInput);
        if (usernameNotEmpty && usernameUnique) {
            this.newUsername = usernameInput.toLowerCase();
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
//            JOptionPane.showMessageDialog(null, "Seu USERNAME é: " + newUsername);
    }
    
    private void askNewPassword() {
        String passwordInput = JOptionPane.showInputDialog(null,
                DialogFormat.header(" CADASTRO de novo usuário ")
                        + "\n\n> CADASTRE sua senha: \n\n");
        boolean inputNotNull = Verify.verifyNotNullInput(passwordInput);
        if (!inputNotNull) {
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada!"
                            + "\nRetornando ao MENU INICIAL");
            menuMain();
        }
        boolean passwordNotEmpty = Verify.verifyNotEmptyOrBlankInput(passwordInput);
        boolean samePasswordInputTwice = verifySameInputTwice(passwordInput);
        if (passwordNotEmpty && samePasswordInputTwice) {
            this.newPassword = passwordInput;
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
//            JOptionPane.showMessageDialog(null, "Sua SENHA é: " + newPassword);
    }
    
    private void showThisUser(int id) {
        String title = DialogFormat.header("CADASTRO CONFIRMADO!");
        String thisUser = listThisUser(id);
        JOptionPane.showMessageDialog(null, title + thisUser);
    }
    
    private void showAllUsers() {
        String title = DialogFormat.header(" USUÁRIOS CADASTRADOS ");
        StringBuilder allUsers = new StringBuilder();
        for (int i = 0; i < getUsers().size(); i++) {
            allUsers.append(listThisUser(i));
        }
        JOptionPane.showMessageDialog(null, title + allUsers);
    }
    
    private String listThisUser(int id) {
        User user = getUsers().get(id);
        return String.format("\n ID: %06d - NOME: %-50s - USERNAME: %-20s",
                user.getIdUser(), user.getName(), user.getUsername());
    }
    
    private boolean verifyUsernameAvialable(String usernameInput) {
        boolean usernameAlreadyTaken = false;
        for (User user : getUsers()) {
            if (usernameInput.equalsIgnoreCase(getUsers().get(user.idUser).getUsername())) {
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
        String msgToConfirm = DialogFormat.header(" SAIR DA APLICAÇÃO? ")
                + TimeStamp.getTimeStamp()
                + "\n\n> Você deseja fechar o programa? \n\n";
        int confirmExit = confirmationDialog(msgToConfirm);
        if (confirmExit == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            menuMain();
        }
    }
    
    public int confirmationDialog(String question) {
        return JOptionPane.showConfirmDialog(null, question);
    }
}