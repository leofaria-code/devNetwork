package br.com.leofaria.devNetwork.user.post;

public class Post {
    public int idPostUser;
    public int idUser;
    public String timeStamp;
    public String content;
    
    public Post(int idPostUser, int idUser, String timeStamp, String content) {
        this.idPostUser = idPostUser;
        this.idUser = idUser;
        this.timeStamp = timeStamp;
        this.content = content;
    }
    
//    public int getIdPostUser() {
//        return idPostUser;
//    }
//    public int getIdUser() {
//        return idUser;
//    }
//    public String getTimeStamp() {
//        return timeStamp;
//    }
//    public String getContent() {
//        return content;
//    }
    
}