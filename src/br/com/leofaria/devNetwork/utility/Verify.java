package br.com.leofaria.devNetwork.utility;

public class Verify {
    public static boolean verifyNotEmptyOrBlankInput(String input) {
        return (!input.isEmpty() && !input.isBlank());
    }
    public static boolean verifyNotNullInput(String input) {
        return (input != null);
    }
}