package br.com.leofaria.devNetwork;

public enum MenuMainOptions {
    
    FUNCAO_RESERVADA(0,'#', "### RESERVADO ###"),
    
    ENTRAR(1, 'E', "ENTRAR no seu perfil"),
    CADASTRAR(2, 'C', "CADASTRAR um novo usuário"),
    LISTAR(3, 'L', "LISTAR perfis cadastrados"),
    FECHAR(4, 'X', "FECHAR a aplicação");
    
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
    
    MenuMainOptions(int num, char c, String description){
        this.indexNum = num;
        this.indexChar = c;
        this.description = description;
    }
}
