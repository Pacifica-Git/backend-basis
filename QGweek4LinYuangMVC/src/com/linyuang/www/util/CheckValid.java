package com.linyuang.www.util;

/**
 * @author
 */
public class CheckValid {
    /**
     *@Auther Yuang
     *@Description //TODO 返回false即无效举报
     *@Param [text]
     *@return boolean
    **/

    public static boolean checkValidReport(String text){
        for(int i = 0;i < text.length();i++){
            if(text.charAt(i) == ' '){
                return false;
            }
        }
        return true;
    }
    /**
     *@Auther Yuang
     *@Description //TODO 返回false即非法ID
     *@Param [string]
     *@return boolean
    **/

    public static boolean checkValidId(String string){
        for(int i = 0;i < string.length();i++){
            if(!(string.charAt(i) >= '0' && string.charAt(i) <= '9')){
                return false;
            }
        }
        return true;
    }
}
