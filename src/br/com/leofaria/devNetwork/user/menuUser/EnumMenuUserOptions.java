package br.com.leofaria.devNetwork.user.menuUser;

public enum EnumMenuUserOptions {
    FUNCAO_RESERVADA(0,'#', "### RESERVADO ###"),
    
    POSTAR(1, 'P', "POSTAR alguma coisa"),
    TIMELINE(2, 'T', "Exibir minha TIMELINE"),
    FEED(3, 'F', "Exibir FEED de todos os usuários"),
    LOGOUT(4, 'X', "LOGOUT");
    
    private final int indexNum;
    private final char indexChar;
    private final String description;
    
    EnumMenuUserOptions(int num, char c, String description){
        this.indexNum = num;
        this.indexChar = c;
        this.description = description;
    }
    public int getIndexNum(){
        return indexNum;
    }
    public char getIndexChar() {
        return indexChar;
    }
    public String getDescription() {
        return description;
    }
}
