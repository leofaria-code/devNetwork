package br.com.leofaria.user;

import br.com.leofaria.post.Post;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    public int idUser;
    private String name;
    private String username;
    private String password;
    
    private List<Post> userPost;
    
    public User(int idUser, String name, String username, String password) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
        this.password = password;
        this.userPost = new ArrayList<Post>();
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
    
    private void setName(String name) {
        this.name = name;
    }
    private void setUsername(String username) {
        this.username = username;
    }
    private void setPassword(String password) {
        this.password = password;
    }
    
    public List<Post> getUserPost() {
        return userPost;
    }
    
    private void makeNewPost () {
        System.out.println("implementar user.novoPost!!!");
    }
}