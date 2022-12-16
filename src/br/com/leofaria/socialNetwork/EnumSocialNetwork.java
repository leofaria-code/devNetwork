package br.com.leofaria.socialNetwork;

public enum EnumSocialNetwork {
    INSTANCE;
    
    public SocialNetwork newNetwork() {
        System.out.println("Hello world!");
        return new SocialNetwork();
    }
    
}