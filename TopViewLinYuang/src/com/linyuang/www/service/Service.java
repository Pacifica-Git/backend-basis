package com.linyuang.www.service;
import com.linyuang.www.dao.UserDao;
import com.linyuang.www.po.*;
import com.linyuang.www.controller.AlertWindow;
import com.linyuang.www.util.CheckValidReport;
import com.linyuang.www.util.GetTimestamp;

import java.sql.Timestamp;
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
    private static final String NUL = "null";

    public static int login(String userName,String password){
        //程序不会有管理员用户登录的提醒，管理员id都为5位，如果用户恰好输入五位就会进行管理员密码确认
        if(userName.length() == FINE){
            if((Caesar.decryption(UserDao.checkPassword(userName)).equals(password))){
                AlertWindow.alertInformation("登陆结果","欢迎管理员");
                nowUser = userName;
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
        }else{
            for(int i = 0;i < userName.length();i++){
                if(userName.charAt(i) < '0' || userName.charAt(i) > '9'){
                    AlertWindow.alertWarning("输入用户Id不合法！","用户Id应为数字组成");
                    return false;
                }
            }
        }
        //密码进行加密后再存入数据库
        String newPass = Caesar.encryption(password);
        ArrayList<User> result = UserDao.checkUserExist(userName);
        if(result.size() == 0){
            User newUser = new User(userName,newPass);
            UserDao.register(newUser);
            AlertWindow.alertInformation("注册结果","注册成功！");
            Folks folks = new Folks(userName,null,null,null,null);
            UserDao.insertFolks(folks);
        }else{
            AlertWindow.alertWarning("注册失败","该用户名已存在！");
        }
        return true;
    }

    public static void postMelon(String title,String text){
        Melon newMelon = new Melon(0,title,text,0,0, GetTimestamp.getTimeStamp(),Service.nowUser);
        if(UserDao.postMelon(newMelon) == 1){
            AlertWindow.alertInformation("发布结果","发布成功！一起来吃瓜吧");
        }else{
            AlertWindow.alertWarning("发布结果","发布失败！可能是创建事件时出错，请重新尝试");
        }
    }

    public static ArrayList<Folks> showInformation(){
        ArrayList<Folks> result = UserDao.getInformation();
        if(result.size() < 1){
            AlertWindow.alertWarning("查询结果","查询个人信息失败，可能是读取数据时出错，请重试");
            return null;
        }else{
            return result;
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 修改个人信息
 *@Param [folksName, string1, date, string2, string3]
 *@return void
**/
 
    public static void updateFolks(String folksName, String string1, String date, String string2, String string3){
        Folks folks = new Folks(folksName,string1,date,string2,string3);
        int result = UserDao.updateFolks(folks);
        if(result == 1){
            AlertWindow.alertInformation("修改结果","修改成功！请重新打开界面查看");
        }else{
            AlertWindow.alertWarning("修改失败","可能是更改数据时出错，请重新尝试");
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 查看自己发的举报及反馈
 *@Param []
 *@return java.util.ArrayList<com.linyuang.www.po.Mail>
**/
 
    public static ArrayList<Mail> getMyMail(){
        ArrayList<Mail> result = UserDao.getMyMail();
        if(result.size() > 0){
            return result;
        }else {
            AlertWindow.alertWarning("查询失败","已经没有更多举报信件了");
            return null;
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 分页获取瓜圈
 *@Param [param]
 *@return java.util.ArrayList<com.linyuang.www.po.MelonCircle>
**/
 
    public static ArrayList<MelonCircle> showPageCircle(int param){
        ArrayList<MelonCircle> result = UserDao.showPageCircle(param);
        if(result.size() > 0){
            return result;
        }else{
            AlertWindow.alertWarning("查询瓜圈失败","并没有这么多瓜圈哦");
            return null;
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 根据瓜圈名获取瓜圈信息
 *@Param [circleName]
 *@return void
**/
 
    public static void openCircle(String circleName){
        //先判断传来的瓜圈名是不是null，是说明并没有对应瓜圈
        if(NUL.equals(circleName)){
            return;
        }
        ArrayList<MelonCircle> circles = UserDao.openCircle(circleName);
        ArrayList<Melon> belong = UserDao.selectBelongMelon(circles.get(0).getCircleId());
        if(circles.size() > 0){
            AlertWindow.alertCircle(circles,belong);
        }else{
            AlertWindow.alertWarning("打开结果","打开瓜圈失败，可能是查询数据时出错");
        }
    }

    public static void openMelon(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            AlertWindow.alertMelon(melons);
        }else{
            AlertWindow.alertWarning("打开失败！","可能是查询数据时出错");
        }
    }
    public static void openMelon(String title){
        ArrayList<Melon> melons = UserDao.selectMelon(title);
        if(melons.size() > 0){
            AlertWindow.alertMelon(melons);
        }else{
            AlertWindow.alertWarning("查询瓜详情失败","可能是获取数据时出现错误");
        }
    }
    /**
     *@Auther Yuang
     *@Description //TODO 修改自己发的瓜的信息
     *@Param [melonId, title, text, hotDegree, belongCircleId, time, senterId]
     *@return void
    **/
     
    public static void modifyMelon(int melonId, String title, String text, int hotDegree, int belongCircleId, Timestamp time,String senterId){
        Melon melon = new Melon(melonId,title,text,hotDegree,belongCircleId,time,senterId);
        int result = UserDao.updateMelon(melon);
        if(result == 1){
            AlertWindow.alertInformation("修改结果","修改成功！请重新打开界面查看");
        }else{
            AlertWindow.alertWarning("修改失败","可能是更改数据时出错，请重新尝试");
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 点击修改时时先判断是否自己发的瓜
 *@Param [melonId]
 *@return boolean
**/
    public static boolean modifyCheckSenter(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            if(nowUser.equals(melons.get(0).getMelonSenterId())){
                AlertWindow.alertInformation("这确实是你种的瓜！","进行修改吧");
                return true;
            }else{
                AlertWindow.alertWarning("无法修改！","这可不是你种的瓜哦");
                return false;
            }
        }else{
            return false;
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 点击举报判断是不是自己发的瓜
 *@Param [melonId]
 *@return boolean
**/
 
    public static boolean reportCheckSenter(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            return nowUser.equals(melons.get(0).getMelonSenterId());
        }else{
            return false;
        }
    }
    /**
     *@Auther Yuang
     *@Description //TODO 查询是否点过某个瓜
     *@Param [melonId]
     *@return boolean
    **/
     
    public static boolean checkLike(int melonId){
        ArrayList<FolksMelon> folksMelons = UserDao.checkLike(melonId);
        return folksMelons.size() > 0;
    }
/**
 *@Auther Yuang
 *@Description //TODO 点赞
 *@Param [melonId]
 *@return void
**/
 
    public static void like(int melonId){
        //更新folksmelon表
        int result = UserDao.updateFolksMelon(nowUser,melonId);
        if(result == 1){
            AlertWindow.alertInformation("点赞成功","感谢您为这个瓜的热度做出了贡献");
            Melon melon = UserDao.selectMelon(melonId).get(0);
            Melon melon1 = new Melon(melon.getMelonId(),melon.getTitle(),melon.getMelonText(),melon.getHotDegree() + 1,melon.getBelongCircleId(),melon.getPostTime(),melon.getMelonSenterId());
            UserDao.updateMelon(melon1);
        }else{
            AlertWindow.alertWarning("点赞失败","可能是修改数据时出现错误，请重新尝试");
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 举报
 *@Param [melonId, melonText, reportText]
 *@return void
**/
 
    public static void report(int melonId,String melonText,String reportText){
        Mail mail = new Mail(0,nowUser,null,"@举报的瓜名:" + melonId + "\n" + "@瓜的内容：" + melonText + '\n' + reportText, GetTimestamp.getTimeStamp(),GetTimestamp.getTimeStamp(),null);
        int result = UserDao.report(mail);
        if(result == 1){
            AlertWindow.alertInformation("举报成功","请等待管理员处理回复");
        }else{
            AlertWindow.alertWarning("举报失败","可能是保存数据时出错，请重新尝试");
        }
    }
/**
 *@Auther Yuang
 *@Description //TODO 获取所有的瓜
 *@Param []
 *@return java.util.ArrayList<com.linyuang.www.po.Melon>
**/
 
    public static ArrayList<Melon> getAllMelons(){
        ArrayList<Melon> melons = UserDao.getAllMelon();
        if(melons.size() <= 0){
            AlertWindow.alertWarning("获取所有瓜失败","找不到数据，请重新尝试或导入数据后尝试");
            return null;
        }else{
            return melons;
        }
    }
    /**
     *@Auther Yuang
     *@Description //TODO 展示点赞的瓜
     *@Param []
     *@return java.util.ArrayList<com.linyuang.www.po.Melon>
    **/

    public static ArrayList<Melon> showLikedMelon(){
        ArrayList<FolksMelon> folksMelons = UserDao.showLikedMelon();
        if(folksMelons.size() <= 0){
            AlertWindow.alertConfirm("查询失败","您可能没有点过赞噢");
            return null;
        }else{
            return UserDao.showLikedMelon(folksMelons);
        }
    }
    public static void createCircle(String title){
        if(!CheckValidReport.checkValidReport(title)){
            AlertWindow.alertWarning("创建失败","请输入合适的名字");
            return;
        }
        MelonCircle melonCircle = new MelonCircle(0,title,GetTimestamp.getTimeStamp(),0,nowUser);
        int result = UserDao.createCircle(melonCircle);
        if(result == 1){
            AlertWindow.alertInformation("创建成功","这个瓜圈归你管了");
        }else{
            AlertWindow.alertWarning("创建失败","可能是保存数据时出错");
        }
    }

    public static ArrayList<Mail> adminMail(){
        ArrayList<Mail> result = UserDao.adminMail();
        if(result.size() > 0){
            return result;
        }else {
            AlertWindow.alertWarning("查询失败","已经没有未被处理的举报信件了");
            return null;
        }
    }

    public static void updateMail(Mail mail,String solveText){
        mail.setSolveText(solveText);
        mail.setSolveTime(GetTimestamp.getTimeStamp());
        mail.setMailSolverId(nowUser);
        int result = UserDao.updateMail(mail);
        if(result == 1){
            AlertWindow.alertInformation("回复成功","辛苦您了");
        }else{
            AlertWindow.alertWarning("回复失败","可能是保存数据时出错");
        }
    }
}
