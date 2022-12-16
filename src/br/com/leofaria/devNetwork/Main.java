package br.com.leofaria.devNetwork;

import br.com.leofaria.devNetwork.socialNetwork.EnumSocialNetwork;

public class Main {
    public static void main(String[] args) {
        EnumSocialNetwork.INSTANCE.newNetwork();
    }
}
