package com.linyuang.www.service;
import com.linyuang.www.dao.UserDao;
import com.linyuang.www.po.Folks;
import com.linyuang.www.po.Mail;
import com.linyuang.www.po.MelonCircle;
import com.linyuang.www.po.User;
import com.linyuang.www.controller.AlertWindow;

import java.util.ArrayList;
/**
 * @author Lenovo 登录以及注册,
 *                  用户id为9位
 *
 */
public class Service {
    public static String nowUser;
    private static final int FINE = 5;
    private static final int NINE = 9;
    private static final int EIGHT = 8;
    private static final int SIXTEEN = 16;

    public static int login(String userName,String password){
        if(userName.length() == FINE){
            if((Caesar.decryption(UserDao.checkPassword(userName)).equals(password))){
                AlertWindow.alertInformation("登陆结果","欢迎管理员");
                return -1;
            }else{
                AlertWindow.alertWarning("输入用户ID不合法","用户ID长度为9位！");
                return 0;
            }
        }else if(userName.length() != NINE){
            AlertWindow.alertWarning("输入用户ID不合法","用户ID长度为9位！");
            return 0;
        }else if(!(password.length() >= EIGHT && password.length() <= SIXTEEN)){
            AlertWindow.alertWarning("输入密码不合法","密码长度为8~16位！");
            return 0;
        }
        ArrayList<User> result = UserDao.checkUserExist(userName);
        if(result.size() == 0){
            AlertWindow.alertInformation("登录结果","登陆失败，该账号不存在！");
            return 0;
        }else if(Caesar.decryption(UserDao.checkPassword(userName)).equals(password)){
            AlertWindow.alertInformation("登录结果","登陆成功！");
            nowUser = userName;
            return 1;
        }else{
            AlertWindow.alertWarning("登陆结果","密码不正确！");
            return 0;
        }
    }

    public static boolean register(String userName,String password){
        if(userName.length() != NINE){
            AlertWindow.alertWarning("输入用户ID不合法","用户ID长度为9位！");
            return false;
        }else if(!(password.length() >= EIGHT && password.length() <= SIXTEEN)){
            AlertWindow.alertWarning("输入密码不合法","密码长度为8~16位！");
            return false;
        }
        String newPass = Caesar.encryption(password);
        ArrayList<User> result = UserDao.checkUserExist(userName);
        if(result.size() == 0){
            UserDao.register(userName,newPass);
            AlertWindow.alertInformation("注册结果","注册成功！");
            UserDao.insertFolks(userName);
            return true;
        }else{
            AlertWindow.alertWarning("注册失败","该用户名已存在！");
            return true;
        }
    }

    public static void postMelon(String title,String text){
        if(UserDao.postMelon(title,text) == 1){
            AlertWindow.alertInformation("发布结果","发布成功！一起来吃瓜吧");
        }else{
            AlertWindow.alertWarning("发布结果","发布失败！可能是创建事件时出错，请重新尝试");
        }
    }

    public static ArrayList<Folks> showInformation(){
        ArrayList<Folks> result = UserDao.getInformation();
        if(result.size() != 1){
            AlertWindow.alertWarning("查询结果","查询个人信息失败，可能是读取数据时出错，请重试");
            return null;
        }else{
            return result;
        }
    }

    public static void updateFolks(String folksName, String string1, String date, String string2, String string3){
        int result = UserDao.updateFolks(folksName,string1,date,string2,string3);
        if(result == 1){
            AlertWindow.alertInformation("修改结果","修改成功！请重新打开界面查看");
        }else{
            AlertWindow.alertWarning("修改失败","可能是更改数据时出错，请重新尝试");
        }
    }

    public static ArrayList<Mail> getMail(){
        ArrayList<Mail> result = UserDao.getMyMail();
        if(result.size() > 0){
            AlertWindow.alertInformation("查询结果","查询成功！正在打开您的邮箱");
            return result;
        }else {
            AlertWindow.alertWarning("查询失败","可能是获取数据时出错，请重新尝试");
            return null;
        }
    }

    public static ArrayList<MelonCircle> showPageCircle(int param){
        ArrayList<MelonCircle> result = UserDao.showPageCircle(param);
        if(result.size() > 0){
            return result;
        }else{
            AlertWindow.alertWarning("查询瓜圈失败","可能是获取数据时出错，请重新尝试");
            return null;
        }
    }
}
