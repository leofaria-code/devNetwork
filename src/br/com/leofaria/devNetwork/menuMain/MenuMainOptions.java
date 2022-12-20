package br.com.leofaria.devNetwork.menuMain;

public enum MenuMainOptions {
    FUNCAO_RESERVADA(0,'#', "### RESERVADO ###"),
    
    ENTRAR(1, 'E', "ENTRAR no seu perfil"),
    CADASTRAR(2, 'C', "CADASTRAR um novo usuário"),
    LISTAR(3, 'L', "LISTAR perfis cadastrados"),
    FECHAR(4, 'X', "FECHAR a aplicação");
    
    private final int indexNum;
    private final char indexChar;
    private final String description;
    
    MenuMainOptions(int indexNum, char indexChar, String description){
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
