package br.com.leofaria.devNetwork.user.menuUser;

public enum MenuUserOptions {
    FUNCAO_RESERVADA(0,'#', "### RESERVADO ###"),
    
    POSTAR(1, 'P', "POSTAR alguma coisa"),
    TIMELINE(2, 'T', "Exibir minha TIMELINE"),
    FEED(3, 'F', "Exibir FEED de todos os usuários"),
    SAIR(4, 'X', "ENCERRAR sua sessão");
    
    private final int indexNum;
    private final char indexChar;
    private final String description;
    
    MenuUserOptions(int indexNum, char indexChar, String description){
        this.indexNum = indexNum;
        this.indexChar = indexChar;
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
