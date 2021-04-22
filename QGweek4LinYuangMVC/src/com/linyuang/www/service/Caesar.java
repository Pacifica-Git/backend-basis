package com.linyuang.www.service;

import java.security.PublicKey;

/**
 * @author 凯撒加解密对密码进行保护
 */
public class Caesar {
    private static final int KEY = 7;

    public static String encryption(String clearText){
        char[] charArray = clearText.toCharArray();
        for (int i = 0;i < charArray.length;i++) {
            char c;
            if ('a' <= charArray[i] && charArray[i] <= 'z') {
                if(charArray[i] + KEY > 'z'){
                    c = (char) (charArray[i] + KEY - 26);
                }else{
                    c = (char) (charArray[i] + KEY);
                }
                charArray[i] = c;
            }else if('A' <= charArray[i] && charArray[i] <= 'Z'){
                if(charArray[i] + KEY > 'Z'){
                    c = (char) (charArray[i] + KEY - 26);
                }else{
                    c = (char) (charArray[i] + KEY);
                }
                charArray[i] = c;
            }else if('0' <= charArray[i] && charArray[i] <= '9'){
                if(charArray[i] + KEY > '9'){
                    c = (char) (charArray[i] + KEY - 10);
                }else{
                    c = (char) (charArray[i] + KEY);
                }
                charArray[i] = c;
            }else{
                c = (char)(charArray[i]);
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }

    public static String decryption(String password){
        char[] charArray = password.toCharArray();
        for (int i = 0;i < charArray.length;i++) {
            char c;
            if ('a' <= charArray[i] && charArray[i] <= 'z') {
                if (charArray[i] - KEY < 'a') {
                    c = (char)(charArray[i] - KEY + 26);
                }else{
                    c = (char) (charArray[i] - KEY);
                }
                charArray[i] = c;
            }else if('A' <= charArray[i] && charArray[i] <= 'Z'){
                if (charArray[i] - KEY < 'A') {
                    c = (char) (charArray[i] - KEY + 26);
                }
                else {
                    c = (char) (charArray[i] - KEY);
                }
                charArray[i] = c;
            }else if('0' <= charArray[i] && charArray[i] <= '9'){
                if (charArray[i] - KEY < '0') {
                    c = (char) (charArray[i] - KEY + 10);
                }
                else {
                    c = (char) (charArray[i] - KEY);
                }
                charArray[i] = c;
            }else{
                c = (char)(charArray[i]);
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }
}
