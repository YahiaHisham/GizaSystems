package com.gizaSystems.utilities;

import java.util.Random;

public class Generators {

    public static String generateRandomText(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(c);
        }
        return sb.toString();
    }

    public static String generateRandomNumbers(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = digits.charAt(random.nextInt(digits.length()));
            sb.append(c);
        }
        return sb.toString();
    }

}
