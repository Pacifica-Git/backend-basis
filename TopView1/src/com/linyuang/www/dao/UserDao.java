package com.linyuang.www.dao;

import com.linyuang.www.po.*;
import com.linyuang.www.service.Service;
import com.linyuang.www.util.DbUtils;
import com.linyuang.www.util.GetTimestamp;
import com.linyuang.www.util.Orm;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * @author 与数据库交互
 */
public class UserDao {
    public static ArrayList<User> checkUserExist(String user){
        //用stringbuilder来构建sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from user where user_id = ").append(user);
        return DbUtils.queryUser(sql.toString());
    }
    public static void register(String userName,String password){
        User user1 = new User(userName,password);
        Orm<User> dbManipulate = new Orm<>();
        dbManipulate.insert(user1);
    }
    public static String checkPassword(String userName){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from user where user_id = ").append(userName);
        return DbUtils.queryUser(sql.toString()).get(0).getPassword();
    }

    public static int postMelon(String title,String text){
        Orm<Melon> dbManipulate = new Orm<>();
        Melon newMelon = new Melon(0,title,text,0,0, GetTimestamp.getTimeStamp());
        return dbManipulate.insert(newMelon);
    }

    public static ArrayList<Folks> getInformation(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from folks where folks_id = ").append(Service.nowUser);
        return DbUtils.queryFolks(sql.toString());
    }
    public static ArrayList<Mail> getMyMail(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from mail where mailsenter_id = ").append(Service.nowUser);
        return DbUtils.queryMail(sql.toString());
    }

    public static void insertFolks(String userId){
        Orm<Folks> folksOrm = new Orm<>();
        Folks folks = new Folks(userId,null,null,null,null);
        folksOrm.insert(folks);
    }
    public static int updateFolks(String folksName, String string1, String date, String string2, String string3){
        Orm<Folks> folksOrm = new Orm<>();
        Folks folks = new Folks(folksName,string1,date,string2,string3);
        return folksOrm.update(folks);
    }
    public static ArrayList<MelonCircle> showPageCircle(int param){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from meloncircle limit ").append(param * 6).append(",").append(6);
        return DbUtils.queryMelonCircle(sql.toString());
    }
}
