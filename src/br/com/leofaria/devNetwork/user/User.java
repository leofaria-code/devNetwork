package br.com.leofaria.devNetwork.user;

import br.com.leofaria.devNetwork.user.menuUser.MenuUser;
import br.com.leofaria.devNetwork.user.menuUser.MenuUserOptions;
import br.com.leofaria.devNetwork.user.post.Post;
import br.com.leofaria.devNetwork.utility.DialogFormat;
import br.com.leofaria.devNetwork.utility.TimeStamp;
import br.com.leofaria.devNetwork.utility.Verify;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public static int idUser;
    private static String name;
    private static String username;
    private String password;
    
    public static List<Post> userPosts = new ArrayList<>();
    public static List<MenuUser> menuUser = new ArrayList<>();
    
    public User(int idUser, String name, String username, String password) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
        this.password = password;
        
    }
    
    public int getIdUser() {
        return idUser;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public static List<Post> getUserPosts() {
        return userPosts;
    }
    
    
    private static User instancia = null;
    
    public static void main(User user) {
        User instancia = obterInstancia(user);
    }
    
    private static synchronized User obterInstancia(User user) {
        if (instancia == null) instancia = new User();
        return instancia;
    }
    
    User() {
        menuUser();
    }
    
    private static String dialogMenuUser;
    private static int inputNum;
    private static int optionAtMenuUser;
    
    public static void constructMenuUser() {
        MenuUserOptions postar = MenuUserOptions.POSTAR;
        MenuUserOptions timeline = MenuUserOptions.TIMELINE;
        MenuUserOptions feed = MenuUserOptions.FEED;
        MenuUserOptions logout = MenuUserOptions.SAIR;
        
        final MenuUser POSTAR = new MenuUser(postar.getIndexNum(), postar.getIndexChar(), postar.getDescription());
        final MenuUser TIMELINE = new MenuUser(timeline.getIndexNum(), timeline.getIndexChar(), timeline.getDescription());
        final MenuUser FEED = new MenuUser(feed.getIndexNum(), feed.getIndexChar(), feed.getDescription());
        final MenuUser LOGOUT = new MenuUser(logout.getIndexNum(), logout.getIndexChar(), logout.getDescription());
        
        menuUser.add(POSTAR);
        menuUser.add(TIMELINE);
        menuUser.add(FEED);
        menuUser.add(LOGOUT);
    }
    
    public static void menuUser() {
        if (menuUser.isEmpty()) {
            constructMenuUser();
        }
        getInputAtMenuUserConvertedtoNum();
        userActions(optionAtMenuUser);
    }
    
    private static void getInputAtMenuUserConvertedtoNum() {
        showMenuUser();
        String inputString = JOptionPane.showInputDialog(null, dialogMenuUser);
        boolean inputNotNull = Verify.verifyNotNullInput(inputString);
        if (!inputNotNull) {
            menuUser();
        }
        boolean inputNotEmpty = Verify.verifyNotEmptyOrBlankInput(inputString);
        if (inputNotEmpty) {
            inputString = inputString.toUpperCase();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Opção VAZIA ou BRANCA é inválida!" + "\nTente novamente!");
            getInputAtMenuUserConvertedtoNum();
        }
        optionAtMenuUser = convertInputStringToNum(inputString);
    }
    
    private static void showMenuUser() {
        String menuTitle = " MENU DO USUÁRIO: o que você deseja fazer? ";
        StringBuilder menu = new StringBuilder(DialogFormat.header(menuTitle));
        for (MenuUser mex : menuUser) {
            String menuLine = "\n" + mex.indexNum
                    + " - " + mex.indexChar
                    + " : " + mex.description;
            menu.append(menuLine);
        }
        menu.append("\n\n>DIGITE o 'número' ou o 'caractere' correspondente à opção escolhida: \n\n");
        dialogMenuUser = menu.toString();
    }
    
    private static int convertInputStringToNum(String inputString) {
        switch (inputString) {
            case "P", "1" -> inputNum = 1;
            case "T", "2" -> inputNum = 2;
            case "F", "3" -> inputNum = 3;
            case "X", "4" -> inputNum = 4;
            default -> {
                JOptionPane.showMessageDialog(null,
                        "Opção '%S' inválida! Tente novamente".formatted(inputString));
                getInputAtMenuUserConvertedtoNum();
            }
        }
        return inputNum;
    }
    
    private static void userActions(int num) {
        switch (num) {
            case 1 -> userMakeNewPost();
            case 2 -> showUserTimeline();
            case 3 -> showAllUsersFeed();
            case 4 -> logout();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente");
        }
        menuUser();
    }
    
    private static void userMakeNewPost() {
        int newPostIdUser;
        
        if (getUserPosts().isEmpty()) {
            newPostIdUser = 0;
        } else {
            newPostIdUser = userPosts.size();
        }
        
        String msg = "Criação de novo POST de ";
        String concat = msg + name;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        System.out.printf("\n> Post Nº [%03d] do usuário: [%s] ", newPostIdUser, username);
        String timestamp = TimeStamp.getTimeStamp();
        System.out.print("\n> Digite o conteúdo: ");
        String content = "TESTE DE POSTAGEM DO USER";
        
        userPosts.add(new Post(newPostIdUser, idUser, timestamp, content));
        printFormatedPost(userPosts.size() - 1);
    }
    
    private static void showUserTimeline() {
        String msg = "Usuário: ";
        String concat = msg + username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        for (int i = 0; i < userPosts.size(); i++) {
            printFormatedPost(i);
        }
    }
    
    public static void printFormatedPost(int idPostUser) {
        System.out.printf("\n| %-92s %03d |", "POST Nº", userPosts.get(idPostUser).idPostUser);
        System.out.printf("\n| %96s |", userPosts.get(idPostUser).timeStamp);
        System.out.printf("\n| %-96s |", userPosts.get(idPostUser).content);
        System.out.printf("\n| %91s %04d |", "ID do usuário:", userPosts.get(idPostUser).idUser);
    }
    
    private static void showAllUsersFeed() {
        System.out.println("FEED de todos os usuário da rede!");
    } // ???
    
    private static void logout() {
        System.out.println("VOLTAR AO MENU MAIN"); //SocialNetwork.main(null);
        System.exit(0);
    }
    
}

