package com.example.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.IntStream;

public class RandomTokenUtil {

    private static final int TOKEN_LENGTH = 32;//32*2 = 64バイト

    public static String generateToken() throws NoSuchAlgorithmException {
        byte token[] = new byte[TOKEN_LENGTH];
        StringBuffer buf = new StringBuffer();
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.nextBytes(token);
        IntStream.range(0, token.length).forEachOrdered(i -> buf.append(String.format("%02x", token[i])));
        return buf.toString();
    }
}
