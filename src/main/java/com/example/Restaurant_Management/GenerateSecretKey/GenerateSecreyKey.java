package com.example.Restaurant_Management.GenerateSecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class GenerateSecreyKey {
    public static void main(String[] args) {
//        System.out.println("Hello World");
//        System.out.println("Số từ 0 đến 9 là:");
//        for (int i = 0; i < 10; i++) {
//            System.out.print(i + " ");
//        }

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Secret key: "+secretKey);
    }
}
