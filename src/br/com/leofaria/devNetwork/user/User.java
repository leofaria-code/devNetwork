package br.com.leofaria.devNetwork.user;

import br.com.leofaria.devNetwork.user.post.Post;
import br.com.leofaria.devNetwork.user.menuUser.MenuUserOptions;
import br.com.leofaria.devNetwork.user.menuUser.MenuUser;
import br.com.leofaria.devNetwork.utility.DialogFormat;
import br.com.leofaria.devNetwork.utility.TimeStamp;
import br.com.leofaria.devNetwork.utility.Verify;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public int idUser;
    private String name;
    private String username;
    private String password;
    
    public List<Post> userPosts = new ArrayList<>();
    public List<MenuUser> menuUser = new ArrayList<>();
    
    public User(int idUser, String name, String username, String password) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
        this.password = password;
//        this.userPosts = new ArrayList<Post>();
//        this.menuUser = new ArrayList<MenuUser>();
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
    public List<Post> getUserPosts() {
        return userPosts;
    }
    
    
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
    
    private String dialogMenuUser;
    private int inputNum;
    private int optionAtMenuUser;
    
    public void constructMenuUser() {
        final MenuUser POSTAR = new MenuUser(MenuUserOptions.POSTAR.getIndexNum(),
                MenuUserOptions.POSTAR.getIndexChar(),
                MenuUserOptions.POSTAR.getDescription());
        final MenuUser TIMELINE = new MenuUser(MenuUserOptions.TIMELINE.getIndexNum(),
                MenuUserOptions.TIMELINE.getIndexChar(),
                MenuUserOptions.TIMELINE.getDescription());
        final MenuUser FEED = new MenuUser(MenuUserOptions.FEED.getIndexNum(),
                MenuUserOptions.FEED.getIndexChar(),
                MenuUserOptions.FEED.getDescription());
        final MenuUser LOGOUT = new MenuUser(MenuUserOptions.SAIR.getIndexNum(),
                MenuUserOptions.SAIR.getIndexChar(),
                MenuUserOptions.SAIR.getDescription());
        menuUser.add(POSTAR);
        menuUser.add(TIMELINE);
        menuUser.add(FEED);
        menuUser.add(LOGOUT);
    }
    
    void menuUser() {
        getInputAtMenuUserConvertedtoNum();
        userActions(optionAtMenuUser);
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
                    "Opção VAZIA ou BRANCA é inválida!" + "\nTente novamente!");
            getInputAtMenuUserConvertedtoNum();
        }
        optionAtMenuUser = convertInputStringToNum(inputString);
    }
        private void showMenuUser() {
        String menuTitle = " MENU DO USUÁRIO: o que você deseja fazer? ";
        StringBuilder menu = new StringBuilder(DialogFormat.header(menuTitle));
        for (MenuUser menuUserOption : this.menuUser) {
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
        
    private void userActions(int num) {
        switch (num) {
            case 1 -> userMakeNewPost();
            case 2 -> showUserTimeline();
            case 3 -> showAllUsersFeed();
            case 4 -> logout();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente");
        }
        menuUser();
    }
    
    private void userMakeNewPost() {
        int newPostIdUser;
        
        if (getUserPosts().isEmpty()) {
            newPostIdUser = 0;
        } else {
            newPostIdUser = this.userPosts.size();
        }
        
        String msg = "Criação de novo POST de ";
        String concat = msg + this.name;
        System.out.printf("\n%s %-94s %3s", "|",  concat, "|");
        System.out.printf("\n> Post Nº [%03d] do usuário: [%s] ", newPostIdUser, this.username);
        String timestamp = TimeStamp.getTimeStamp();
        System.out.print("\n> Digite o conteúdo: ");
        String content = "TESTE DE POSTAGEM DO USER";
        
        userPosts.add(new Post(newPostIdUser, this.idUser, timestamp, content));
        printFormatedPost(this.userPosts.size() - 1);
    }
    
    private void showUserTimeline() {
        String msg = "Usuário: ";
        String concat = msg + this.username;
        System.out.printf("\n%s %-94s %3s", "|", concat, "|");
        for (int i = 0; i < userPosts.size(); i++) {
            printFormatedPost(i);
        }
    }
        public void printFormatedPost(int idPostUser) {
        System.out.printf("\n| %-92s %03d |", "POST Nº", this.userPosts.get(idPostUser).idPostUser);
        System.out.printf("\n| %96s |", this.userPosts.get(idPostUser).timeStamp);
        System.out.printf("\n| %-96s |", this.userPosts.get(idPostUser).content);
        System.out.printf("\n| %91s %04d |", "ID do usuário:", this.userPosts.get(idPostUser).idUser);
        }
    
    private void showAllUsersFeed() {
        System.out.println("FEED de todos os usuário da rede!");
    } // ???
    
    private void logout() {
        System.out.println("VOLTAR AO MENU MAIN"); //SocialNetwork.main(null);
    }
    
}

