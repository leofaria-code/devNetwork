package br.com.leofaria.devNetwork.socialNetwork.menu;

public enum EnumMenuOptions {
    
    FUNCAO_RESERVADA(0,'#', "### RESERVADO ###"),
    
    ENTRAR(1, 'E', "ENTRAR"),
    CADASTRAR(2, 'C', "CADASTRAR"),
    LISTAR(3, 'L', "LISTAR"),
    FECHAR(4, 'X', "FECHAR");
    
    private final int indexNum;
    private final char indexChar;
    private final String description;
    
    public int getIndexNum(){
        return indexNum;
    }
    
    public char getIndexChar() {
        return indexChar;
    }
    
    public String getDescription() {
        return description;
    }
    
    EnumMenuOptions(int num, char c, String description){
        this.indexNum = num;
        this.indexChar = c;
        this.description = description;
    }
}
