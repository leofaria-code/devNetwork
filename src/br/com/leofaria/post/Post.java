package br.com.leofaria.post;

public class Post {
    
    public int idPostGlobal;
    public int idUser;
    
    public String timeStamp;
    public String content;
    
    public Post(int idPostGlobal, int idUser, String timeStamp, String content) {
        this.idPostGlobal = idPostGlobal;
        this.idUser = idUser;
        this.timeStamp = timeStamp;
        this.content = content;
    }
    
    public void printTimelineGlobal () {
        for (int i = 0; i < idPostGlobal; i++) {
            System.out.println();
            String s = (" Post Nº " + String.valueOf(this.idPostGlobal)
                    + ". Postado em " + this.timeStamp
                    + " pelo Usuário ID Nº" + String.valueOf(this.idUser)
                    + ". Conteúdo da postagem: " + this.content);
            System.out.printf("\n %s", s);
        };
        
    }
}