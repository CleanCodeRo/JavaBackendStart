package com.utcb.javaBackendStart.shared.helpers;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARACTERS = UPPER + LOWER + DIGITS ;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 4) throw new IllegalArgumentException("Length must be at least 4");

        StringBuilder password = new StringBuilder(length);
        password.append(randomCharacter(UPPER))
                .append(randomCharacter(LOWER))
                .append(randomCharacter(DIGITS));

        for (int i = 3; i < length; i++) {
            password.append(randomCharacter(ALL_CHARACTERS));
        }

        return shuffleString(password.toString());
    }

    private static char randomCharacter(String chars) {
        return chars.charAt(RANDOM.nextInt(chars.length()));
    }

    private static String shuffleString(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = sb.length() - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        return sb.toString();
    }
}
