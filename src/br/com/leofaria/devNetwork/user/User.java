package br.com.leofaria.devNetwork.user;

import br.com.leofaria.devNetwork.SocialNetwork;
import br.com.leofaria.devNetwork.post.Post;
import br.com.leofaria.devNetwork.user.menuUser.EnumMenuUserOptions;
import br.com.leofaria.devNetwork.user.menuUser.MenuUser;
import br.com.leofaria.devNetwork.utility.DialogFormat;
import br.com.leofaria.devNetwork.utility.TimeStamp;
import br.com.leofaria.devNetwork.utility.Verifies.Verify;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public int idUser;
    private String name;
    private String username;
    private String password;
    
    private List<Post> userPosts;
    public List<MenuUser> menuUser = new ArrayList<>();
    
    private static User instancia = null;
    public static void main() {
        User instancia = obterInstancia();
    }
    private static synchronized User obterInstancia() {
        if (instancia == null) instancia = new User();
        return instancia;
    }
    
    User() {
        constructMenuUser();
        menuUser();
    }


    public User(int idUser, String name, String username, String password) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
        this.password = password;
        this.userPosts = new ArrayList<Post>();
    }


    private String dialogMenuUser;
    private int inputNum;
    private int optionAtMenuUser;
    
    public void menuUser() {
        getInputAtMenuUserConvertedtoNum();
        userActions(optionAtMenuUser);
    }

    private void userActions(int num) {
        switch (num) {
            case 1 -> userMakeNewPost(idUser);
            case 2 -> showUserTimeline();
            case 3 -> showAllUsersFeed();
            case 4 -> logout();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente");
        }
        menuUser();
    }
    
    private void logout() {
        SocialNetwork.main(null);
    }
    
    private void showAllUsersFeed() {
        System.out.println("FEED da rede xxxxxxxxxx");
    }

    private void showUserTimeline() {
        String msg = "Usuário: ";
        String concat = msg + this.username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");

        for (int i = 0; i < userPosts.size(); i++) {
            printFormatedPost(i);
        }
//        for (int iP = 0; iP < userPosts.size(); iP++) {
//            Post.printFormatedPost(iP);
//            Main.printLine('.');
//        }
    }
    public void printFormatedPost(int idPostUser) {
        System.out.printf("\n| %-92s %03d |", "POST Nº", userPosts.get(idPostUser).idPostUser);
        System.out.printf("\n| %96s |", userPosts.get(idPostUser).timeStamp);
        System.out.printf("\n| %-96s |", userPosts.get(idPostUser).content);
        System.out.printf("\n| %91s %04d |", "ID do usuário:", userPosts.get(idPostUser).idUser);
    }

    private void getInputAtMenuUserConvertedtoNum() {
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
                    "Opção VAZIA ou BRANCA é inválida!"
                            + "\nTente novamente!");
            getInputAtMenuUserConvertedtoNum();
        }
        optionAtMenuUser = convertInputStringToNum(inputString);
    }
    private void showMenuUser() {
        String menuTitle = " MENU DO USUÁRIO: o que você deseja fazer? ";
        StringBuilder menu = new StringBuilder(DialogFormat.header(menuTitle));
        for (MenuUser menuUserOption : menuUser) {
            String menuLine = "\n" + menuUserOption.indexNum
                    + " - " + menuUserOption.indexChar
                    + " : " + menuUserOption.description;
            menu.append(menuLine);
        }
        menu.append("\n\n>DIGITE o 'número' ou o 'caractere' correspondente à opção escolhida: \n\n");
        dialogMenuUser = menu.toString();
    }
    private int convertInputStringToNum(String inputString) {
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

//    private void setName(String name) {
//        this.name = name;
//    }
//    private void setUsername(String username) {
//        this.username = username;
//    }
//    private void setPassword(String password) {
//        this.password = password;
//    }

    public void constructMenuUser() {
        final MenuUser POSTAR = new MenuUser(EnumMenuUserOptions.POSTAR.getIndexNum(),
                EnumMenuUserOptions.POSTAR.getIndexChar(),
                EnumMenuUserOptions.POSTAR.getDescription());
        final MenuUser TIMELINE = new MenuUser(EnumMenuUserOptions.TIMELINE.getIndexNum(),
                EnumMenuUserOptions.TIMELINE.getIndexChar(),
                EnumMenuUserOptions.TIMELINE.getDescription());
        final MenuUser FEED = new MenuUser(EnumMenuUserOptions.FEED.getIndexNum(),
                EnumMenuUserOptions.FEED.getIndexChar(),
                EnumMenuUserOptions.FEED.getDescription());
        final MenuUser LOGOUT = new MenuUser(EnumMenuUserOptions.LOGOUT.getIndexNum(),
                EnumMenuUserOptions.LOGOUT.getIndexChar(),
                EnumMenuUserOptions.LOGOUT.getDescription());
        menuUser.add(POSTAR);
        menuUser.add(TIMELINE);
        menuUser.add(FEED);
        menuUser.add(LOGOUT);
    }

//    public List<Post> getUserPost() {
//        return userPost;
//    }
//
    private void userMakeNewPost(int idUser) {
        int newPostIdUser;
//        if (this.userPosts.isEmpty()) {
//            newPostIdUser = 0;
//        } else {
//            newPostIdUser = userPosts.size();
//        }
        newPostIdUser = 0;

        String msg = "Criação de novo POST de ";
        String name = SocialNetwork.getUsers().get(idUser).getUsername().toUpperCase();
        String concat = msg + name;
        System.out.printf("\n%s %-94s %3s", "|",  concat, "|");

        System.out.printf("\n> Post Nº %03d - usuário: %s ", newPostIdUser, SocialNetwork.getUsers().get(idUser).getUsername());
        String timestamp = TimeStamp.getTimeStamp();
        System.out.print("\n> Digite o conteúdo: ");
        String content = "TEssste";
        userPosts.add(new Post(newPostIdUser, idUser, timestamp, content));
    }

}

