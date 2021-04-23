package com.linyuang.www.util;

import com.linyuang.www.controller.AlertWindow;
import com.linyuang.www.po.*;
import com.linyuang.www.service.Service;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author  database utils
 */
public class DbUtils {
    private static final String MOTIVATION = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/topview?characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(MOTIVATION);
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
        }catch(Exception e){
            AlertWindow.alertInformation("出错","获取数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    public static int excuteUpdate(String sql, Object[] params){
        Connection connection = null;
        PreparedStatement preStatement = null;
        int result = -1;
        try {
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i ++){
                preStatement.setObject(i+1, params[i]);
            }
            result = preStatement.executeUpdate();
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","更新数据异常");
            System.out.println(e.getMessage());
        } finally {
            release(preStatement, connection);
        }
        return result;
        // 更新数据失败
    }

    public static void release(Statement stmt, Connection conn){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                AlertWindow.alertWarning("出错","关闭数据更新对象失败");
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                AlertWindow.alertWarning("出错","关闭数据连接对象失败");
                e.printStackTrace();
            }
        }
    }
    public static void release(ResultSet resultSet,Statement stmt, Connection conn) {
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                AlertWindow.alertWarning("出错","关闭获取数据的结果集失败");
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                AlertWindow.alertWarning("出错","关闭数据更新对象失败");
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                AlertWindow.alertWarning("出错","关闭数据连接对象失败");
                e.printStackTrace();
            }
        }
    }


    public static ArrayList<Comment> queryComment(String sql,int melonId){
        ArrayList<Comment> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1,melonId);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                Comment temp = new Comment();
                temp.setCommenterId(resultSet.getString(1));
                temp.setCommentedMelonId(resultSet.getInt(2));
                temp.setCommentText(resultSet.getString(3));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取评论信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<Folks> queryFolks(String sql,String userId){
        ArrayList<Folks> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1,userId);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                Folks temp = new Folks();
                temp.setFolksId(resultSet.getString(1));
                temp.setName(resultSet.getString(2));
                temp.setBirthday(resultSet.getString(3));
                temp.setHobby(resultSet.getString(4));
                temp.setInstruction(resultSet.getString(5));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取吃瓜群众信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }



    public static ArrayList<FolksMelon> queryFolksMelon(String sql,String userId,int melonId){
        ArrayList<FolksMelon> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement  = connection.prepareStatement(sql);
            preStatement.setString(1, userId);
            preStatement.setInt(2, melonId);
            resultSet = preStatement .executeQuery();
            while(resultSet.next()) {
                FolksMelon temp = new FolksMelon();
                temp.setLikerId(resultSet.getString(1));
                temp.setLikedMelonId(resultSet.getInt(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取点赞信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement ,connection);
        }
        return result;
    }
    public static ArrayList<FolksMelon> queryFolksMelon(String sql,String userId){
        ArrayList<FolksMelon> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement  = connection.prepareStatement(sql);
            preStatement.setString(1, userId);
            resultSet = preStatement .executeQuery();
            while(resultSet.next()) {
                FolksMelon temp = new FolksMelon();
                temp.setLikerId(resultSet.getString(1));
                temp.setLikedMelonId(resultSet.getInt(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取点赞信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement ,connection);
        }
        return result;
    }

    public static ArrayList<Mail> queryMail(String sql,String userId){
        ArrayList<Mail> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, userId);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                Mail temp = new Mail();
                temp.setMailId(resultSet.getInt(1));
                temp.setMailSenterId(resultSet.getString(2));
                temp.setMailSolverId(resultSet.getString(3));
                temp.setMailText(resultSet.getString(4));
                temp.setSentTime(resultSet.getTimestamp(5));
                temp.setSolveTime(resultSet.getTimestamp(6));
                temp.setSolveText(resultSet.getString(7));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取信件信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<Mail> queryMail(String sql){
        ArrayList<Mail> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery(sql);
            while(resultSet.next()) {
                Mail temp = new Mail();
                temp.setMailId(resultSet.getInt(1));
                temp.setMailSenterId(resultSet.getString(2));
                temp.setMailSolverId(resultSet.getString(3));
                temp.setMailText(resultSet.getString(4));
                temp.setSentTime(resultSet.getTimestamp(5));
                temp.setSolveTime(resultSet.getTimestamp(6));
                temp.setSolveText(resultSet.getString(7));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取信件信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<Melon> queryMelon(String sql){
        ArrayList<Melon> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery(sql);
            while(resultSet.next()) {
                Melon temp = new Melon();
                temp.setMelonId(resultSet.getInt(1));
                temp.setTitle(resultSet.getString(2));
                temp.setMelonText(resultSet.getString(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setBelongCircleId(resultSet.getInt(5));
                temp.setPostTime(resultSet.getTimestamp(6));
                temp.setMelonSenterId(resultSet.getString(7));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜的信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }
    //可以同时根据瓜或瓜圈Id查询
    public static ArrayList<Melon> queryMelon(String sql,int id){
        ArrayList<Melon> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                Melon temp = new Melon();
                temp.setMelonId(resultSet.getInt(1));
                temp.setTitle(resultSet.getString(2));
                temp.setMelonText(resultSet.getString(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setBelongCircleId(resultSet.getInt(5));
                temp.setPostTime(resultSet.getTimestamp(6));
                temp.setMelonSenterId(resultSet.getString(7));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜的信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<Melon> queryMelon(String sql,String title){
        ArrayList<Melon> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, title);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                Melon temp = new Melon();
                temp.setMelonId(resultSet.getInt(1));
                temp.setTitle(resultSet.getString(2));
                temp.setMelonText(resultSet.getString(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setBelongCircleId(resultSet.getInt(5));
                temp.setPostTime(resultSet.getTimestamp(6));
                temp.setMelonSenterId(resultSet.getString(7));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜的信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<MelonCircle> queryMelonCircle(String sql,int param1,int param2){
        ArrayList<MelonCircle> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, param1);
            preStatement.setInt(2, param2);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                MelonCircle temp = new MelonCircle();
                temp.setCircleId(resultSet.getInt(1));
                temp.setCircleName(resultSet.getString(2));
                temp.setCreateTime(resultSet.getTimestamp(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setAdministratorId(resultSet.getString(5));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜圈信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }
    public static ArrayList<MelonCircle> queryMelonCircle(String sql,String circleName){
        ArrayList<MelonCircle> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, circleName);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                MelonCircle temp = new MelonCircle();
                temp.setCircleId(resultSet.getInt(1));
                temp.setCircleName(resultSet.getString(2));
                temp.setCreateTime(resultSet.getTimestamp(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setAdministratorId(resultSet.getString(5));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜圈信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }
    public static ArrayList<MelonCircle> queryMelonCircle(String sql,int melonId){
        ArrayList<MelonCircle> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, melonId);
            resultSet = preStatement.executeQuery();
            while(resultSet.next()) {
                MelonCircle temp = new MelonCircle();
                temp.setCircleId(resultSet.getInt(1));
                temp.setCircleName(resultSet.getString(2));
                temp.setCreateTime(resultSet.getTimestamp(3));
                temp.setHotDegree(resultSet.getInt(4));
                temp.setAdministratorId(resultSet.getString(5));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取瓜圈信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preStatement,connection);
        }
        return result;
    }

    public static ArrayList<User> queryUser(String sql,String userId){
        ArrayList<User> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConn();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User temp = new User();
                temp.setUserId(resultSet.getString(1));
                temp.setPassword(resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            AlertWindow.alertWarning("出错","从数据库获取用户信息时出错");
            e.printStackTrace();
        }finally {
            release(resultSet,preparedStatement,connection);
        }
        return result;
    }
}
