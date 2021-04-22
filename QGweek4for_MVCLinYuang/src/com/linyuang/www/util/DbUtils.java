package com.linyuang.www.util;

import com.linyuang.www.po.*;

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
            System.out.println("获取连接对象失败.");
            e.printStackTrace();
        }
        return conn;
    }

    public static int excuteUpdate(String sql, Object[] params){
        Connection connection = null;
        PreparedStatement preStatement =null;
        int result = -1;
        try {
            connection = getConn();
            preStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i ++){
                preStatement.setObject(i+1, params[i]);
            }
            result = preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("更新数据出现异常.");
            System.out.println(e.getMessage());
        } finally {
            release(preStatement, connection);
        }
        return result;
        // 更新数据失败
    }

    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Admin> queryAdmin(String sql){
        ArrayList<Admin> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Admin temp = new Admin();
                temp.setAdminId(resultSet.getString(1));
                temp.setAdminInstruction(resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<Comment> queryComment(String sql){
        ArrayList<Comment> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Comment temp = new Comment();
                temp.setCommenterId(resultSet.getString(1));
                temp.setCommentText(resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<Folks> queryFolks(String sql){
        ArrayList<Folks> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
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
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<FolksCircle> queryFolksCircle(String sql){
        ArrayList<FolksCircle> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                FolksCircle temp = new FolksCircle();
                temp.setLikerId(resultSet.getString(1));
                temp.setLikedCircleId(resultSet.getInt(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<FolksMelon> queryFolksMelon(String sql){
        ArrayList<FolksMelon> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                FolksMelon temp = new FolksMelon();
                temp.setLikerId(resultSet.getString(1));
                temp.setLikedMelonId(resultSet.getInt(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<Mail> queryMail(String sql){
        ArrayList<Mail> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
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
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<Melon> queryMelon(String sql){
        ArrayList<Melon> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
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
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<MelonCircle> queryMelonCircle(String sql){
        ArrayList<MelonCircle> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
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
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }

    public static ArrayList<User> queryUser(String sql){
        ArrayList<User> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try{
            connection = getConn();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                User temp = new User();
                temp.setUserId(resultSet.getString(1));
                temp.setPassword(resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(statement,connection);
        }
        return result;
    }
}
