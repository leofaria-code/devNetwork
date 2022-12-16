package br.com.leofaria.devNetwork.socialNetwork;

public enum EnumSocialNetwork {
    INSTANCE;
    
    public SocialNetwork newNetwork() {
        System.out.println("Hello world!");
        return new SocialNetwork();
    }
    
}