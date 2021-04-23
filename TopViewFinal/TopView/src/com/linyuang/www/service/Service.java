package com.linyuang.www.service;
import com.linyuang.www.dao.MelonDao;
import com.linyuang.www.dao.UserDao;
import com.linyuang.www.po.*;
import com.linyuang.www.controller.AlertWindow;
import com.linyuang.www.util.CheckValid;
import com.linyuang.www.util.GetTimestamp;
import javafx.scene.shape.Circle;

import java.awt.color.CMMException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
                nowUser = userName;
                return -1;
            }else{
                return 0;
            }
        }else if(userName.length() != NINE){
            return 0;
        }else if(!(password.length() >= EIGHT && password.length() <= SIXTEEN)){
            return 2;
        }
        ArrayList<User> result = UserDao.checkUserExist(userName);
        if(result.size() == 0){
            return 3;
        }else if(Caesar.decryption(UserDao.checkPassword(userName)).equals(password)){
            nowUser = userName;
            return 1;
        }else{
            return 4;
        }
    }

    public static int register(String userName,String password){
        if(userName.length() != NINE){
            return 0;
        }else if(!(password.length() >= EIGHT && password.length() <= SIXTEEN)){
            return 1;
        }else{
            for(int i = 0;i < userName.length();i++){
                if(userName.charAt(i) < '0' || userName.charAt(i) > '9'){
                    return 2;
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
        return 3;
    }

    public static boolean postMelon(String title,String text){
        Melon newMelon = new Melon(0,title,text,0,0, GetTimestamp.getTimeStamp(),Service.nowUser);
        return MelonDao.postMelon(newMelon) == 1;
    }

    public static ArrayList<Folks> showInformation(){
        ArrayList<Folks> result = UserDao.getInformation();
        if(result.size() < 1){
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
 
    public static boolean updateFolks(String folksName, String string1, String date, String string2, String string3){
        Folks folks = new Folks(folksName,string1,date,string2,string3);
        int result = UserDao.updateFolks(folks);
        return result == 1;
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

    public static ArrayList<Melon> openMelon(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            return melons;
        }else{
            return null;
        }
    }
    public static ArrayList<Melon> openMelon(String title){
        ArrayList<Melon> melons = UserDao.selectMelon(title);
        if(melons.size() > 0){
            return melons;
        }else{
            return null;
        }
    }
    /**
     *@Auther Yuang
     *@Description //TODO 修改自己发的瓜的信息
     *@Param [melonId, title, text, hotDegree, belongCircleId, time, senterId]
     *@return void
    **/
     
    public static int modifyMelon(Melon melon,String text){
        melon.setMelonText(text);
        return UserDao.updateMelon(melon);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 点击修改时时先判断是否自己发的瓜
 *@Param [melonId]
 *@return boolean
**/
    public static int modifyCheckSenter(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            if(nowUser.equals(melons.get(0).getMelonSenterId())){
                return 1;
            }else{
                return 0;
            }
        }else{
            return -1;
        }
    }
    /**
 *@Auther Yuang
 *@Description //TODO 点击举报判断是不是自己发的瓜
 *@Param [melonId]
 *@return boolean
**/

    public static boolean checkSenter(int melonId){
        ArrayList<Melon> melons = UserDao.selectMelon(melonId);
        if(melons.size() > 0){
            return nowUser.equals(melons.get(0).getMelonSenterId());
        }else{
            return false;
        }
    }
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
 
    public static int like(int melonId){
        //更新folksmelon表
        int result = UserDao.updateFolksMelon(nowUser,melonId);
        if(result == 1){
            Melon melon = UserDao.selectMelon(melonId).get(0);
            Melon melon1 = new Melon(melon.getMelonId(),melon.getTitle(),melon.getMelonText(),melon.getHotDegree() + 1,melon.getBelongCircleId(),melon.getPostTime(),melon.getMelonSenterId());
            UserDao.updateMelon(melon1);
            return result;
        }else{
            return 0;
        }
    }
    /**
 *@Auther Yuang
 *@Description //TODO 举报
 *@Param [melonId, melonText, reportText]
 *@return void
**/
 
    public static boolean report(int melonId,String melonText,String reportText){
        Mail mail = new Mail(0,nowUser,null,"@举报的瓜名:" + melonId + "\n" + "\t\t@瓜的内容：" + melonText + "\n\t\t" + "举报原因:" + reportText, GetTimestamp.getTimeStamp(),GetTimestamp.getTimeStamp(),null);
        int result = UserDao.report(mail);
        return result == 1;
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
    /**
     *@Auther Yuang
     *@Description //TODO 管理员创建瓜圈
     *@Param [title]
     *@return void
    **/
     
    public static void createCircle(String title){
        if(!CheckValid.checkValidReport(title)){
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

    public static ArrayList<Melon> findUnBelongMelon(){
        ArrayList<Melon> melons = UserDao.findUnbelongMelon();
        if(melons.size() > 0){
            return melons;
        }else{
            AlertWindow.alertWarning("查无结果","所有瓜都所属了");
            return null;
        }
    }

    public static void setBelong(Melon melon,String string){
        if(CheckValid.checkValidId(string)){
            int circleId = Integer.parseInt(string);
            ArrayList<MelonCircle> melonCircles = UserDao.openCircle(circleId);
            if(melonCircles.size() > 0){
                melon.setBelongCircleId(melonCircles.get(0).getCircleId());
                AlertWindow.alertInformation("分配成功","这个瓜已有所属啦");
                UserDao.updateMelon(melon);
            }else{
                AlertWindow.alertWarning("瓜圈Id不合法","并没有这个瓜圈");
            }
        }else{
            AlertWindow.alertWarning("输入瓜圈Id不合法","请输入纯数字");
        }
    }

    public static void comment(Melon melon,String text){
        if(!CheckValid.checkValidReport(text)){
            AlertWindow.alertWarning("评论失败","请输入您的评论");
            return;
        }
        Comment comment = new Comment(nowUser,melon.getMelonId(),text);
        int result = UserDao.comment(comment);
        if(result == 1){
            AlertWindow.alertInformation("评论成功","您已经对这件事发表出自己的看法啦");
        }else{
            AlertWindow.alertWarning("评论失败","可能是保存数据时出错");
        }
    }

    public static void updateCircleHotDegree(MelonCircle circle,int hotDegree){
        circle.setHotDegree(hotDegree);
        int result = UserDao.updateCircleHotDegree(circle);
        if(result != 1){
            AlertWindow.alertWarning("数据更新失败","瓜圈热度更新失败");
        }
    }
    public static ArrayList<Comment> getComments(int melonId){
        ArrayList<Comment> comments = UserDao.getComment(melonId);
        if(comments.size() > 0){
            return comments;
        }else{
            return null;
        }
    }
    public static void deleteComments(ArrayList<Comment> comments){
        if(comments == null){
            AlertWindow.alertConfirm("删除失败","目前还没有评论噢");
        }else{
            for(Comment comment : comments){
                int result = UserDao.deleteComments(comment);
                if(result != 1){
                    AlertWindow.alertInformation("删除失败","删除一条评论时出现错误");
                }
            }
        }
    }

}
