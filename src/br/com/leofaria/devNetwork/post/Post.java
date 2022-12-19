package br.com.leofaria.devNetwork.post;

public class Post {
    
    public int idPostUser;
    public int idUser;
    
    public String timeStamp;
    public String content;
    
    
    private static Post instancia = null;
    public static void main() {
        Post instancia = obterInstancia();
    }
    private static synchronized Post obterInstancia() {
        if (instancia == null) instancia = new Post();
        return instancia;
    }
    
    Post() {
    
    }
    
    public Post(int idPostUser, int idUser, String timeStamp, String content) {
        this.idPostUser = idPostUser;
        this.idUser = idUser;
        this.timeStamp = timeStamp;
        this.content = content;
    }
    
    public int getIdPostUser() {
        return idPostUser;
    }
    
    public int getIdUser() {
        return idUser;
    }
    
    public String getTimeStamp() {
        return timeStamp;
    }
    
    public String getContent() {
        return content;
    }
    
    //    public void printTimelineGlobal () {
////        for (int i = 0; i < idPostGlobal; i++) {
////            System.out.println();
////            String s = (" Post Nº " + String.valueOf(this.idPostGlobal)
////                    + ". Postado em " + this.timeStamp
////                    + " pelo Usuário ID Nº" + String.valueOf(this.idUser)
////                    + ". Conteúdo da postagem: " + this.content);
////            System.out.printf("\n %s", s);
////        }
//    }
}