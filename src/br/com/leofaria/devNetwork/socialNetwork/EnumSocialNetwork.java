package br.com.leofaria.devNetwork.socialNetwork;

public enum EnumSocialNetwork {
    INSTANCE;
    
    public void newNetwork() {
        new SocialNetwork();
    }
}