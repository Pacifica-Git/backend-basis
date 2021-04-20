package com.linyuang.www.util;

/**
 * @author Lenovo
 */
public class CheckValidReport {
    public static boolean checkValidReport(String text){
        for(int i = 0;i < text.length();i++){
            if(text.charAt(i) != ' '){
                return true;
            }
        }
        return false;
    }
}
