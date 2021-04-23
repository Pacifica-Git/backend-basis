package com.linyuang.www.dao;
import com.linyuang.www.po.*;
import com.linyuang.www.service.Service;
import com.linyuang.www.util.DbUtils;
import com.linyuang.www.util.Orm;
import com.mysql.cj.jdbc.MysqlParameterMetadata;

import java.util.ArrayList;
/**
 * @author 与数据库交互
 */
public class UserDao {
    /**
     *@Auther Yuang
     *@Description //TODO 查询输入用户名是否存在
     *@Param [user]
     *@return java.util.ArrayList<com.linyuang.www.po.User>
     **/
    public static ArrayList<User> checkUserExist(String user){
        //用stringbuilder来构建sql语句
        String sql = "select * from user where user_id = ?";
        return DbUtils.queryUser(sql,user);
    }
    /**
     *@Auther Yuang
     *@Description //TODO
     *@Param [userName, password] 注册新用户
     *@return void
     **/
    public static void register(User user){
        Orm<User> dbManipulate = new Orm<>();
        dbManipulate.insert(user);
    }
    /**
     *@Auther Yuang
     *@Description //TODO 检查密码
     *@Param [userName]
     *@return java.lang.String
     **/
    public static String checkPassword(String userName){
        String sql = "select * from user where user_id = ?";
        return DbUtils.queryUser(sql,userName).get(0).getPassword();
    }


    /**
 *@Auther Yuang
 *@Description //TODO 从folks表读取个人信息
 *@Param []
 *@return java.util.ArrayList<com.linyuang.www.po.Folks>
**/
 
    public static ArrayList<Folks> getInformation(){
        String sql = "select * from folks where folks_id = ?";
        return DbUtils.queryFolks(sql,Service.nowUser);
    }
    /**
     *@Auther Yuang
     *@Description //TODO 从mail表读取自己写的举报反馈
     *@Param []
     *@return java.util.ArrayList<com.linyuang.www.po.Mail>
    **/
     
    public static ArrayList<Mail> getMyMail(){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from mail where mailsenter_id = ").append(Service.nowUser);
        return DbUtils.queryMail(sql.toString());*/
        String sql = "select * from mail where mailsenter_id = ?";
        return DbUtils.queryMail(sql,Service.nowUser);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 插入新的吃瓜群众
 *@Param [userId]
 *@return void
**/
 
    public static void insertFolks(Folks folks){
        Orm<Folks> folksOrm = new Orm<>();
        folksOrm.insert(folks);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 更新folks信息
 *@Param [folks]
 *@return int
**/
 
    public static int updateFolks(Folks folks){
        Orm<Folks> folksOrm = new Orm<>();
        return folksOrm.update(folks);
    }

    public static int updateMelon(Melon melon){
        Orm<Melon> melonOrm = new Orm<>();
        return melonOrm.update(melon);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 查看所有瓜圈时的分页查询
 *@Param [param]
 *@return java.util.ArrayList<com.linyuang.www.po.MelonCircle>
**/
 
    public static ArrayList<MelonCircle> showPageCircle(int param){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from meloncircle limit ").append(param * 6).append(",").append(6);
        return DbUtils.queryMelonCircle(sql.toString());*/
        String sql = "select * from meloncircle limit ?,?";
        return DbUtils.queryMelonCircle(sql,param * 6,6);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 根据瓜圈名打开瓜圈
 *@Param [circleName]
 *@return java.util.ArrayList<com.linyuang.www.po.MelonCircle>
**/
 
    public static ArrayList<MelonCircle> openCircle(String circleName){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from meloncircle where circle_name = ").append("\"").append(circleName).append("\"");
        return DbUtils.queryMelonCircle(sql.toString());*/
        String sql = "select * from meloncircle where circle_name = ?";
        return DbUtils.queryMelonCircle(sql,circleName);
    }
    public static ArrayList<MelonCircle> openCircle(int circleId){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from meloncircle where circle_id = ").append(circleId);
        return DbUtils.queryMelonCircle(sql.toString());*/
        String sql = "select * from meloncircle where circle_id = ?";
        return DbUtils.queryMelonCircle(sql,circleId);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 查询瓜圈下的瓜
 *@Param [circleId]
 *@return java.util.ArrayList<com.linyuang.www.po.Melon>
**/
 
    public static ArrayList<Melon> selectBelongMelon(int circleId){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from melon where belongcircle_id = ").append(circleId);
        return DbUtils.queryMelon(sql.toString());*/
        String sql = "select * from melon where belongcircle_id = ?";
        return DbUtils.queryMelon(sql, circleId);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 根据瓜id查找瓜
 *@Param [melonId]
 *@return java.util.ArrayList<com.linyuang.www.po.Melon>
**/
 
    public static ArrayList<Melon> selectMelon(int melonId){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from melon where melon_id = ").append(melonId);
        return DbUtils.queryMelon(sql.toString());*/
        String sql = "select * from melon where melon_id = ?";
        return DbUtils.queryMelon(sql, melonId);
    }
    /**
     *@Auther Yuang
     *@Description //TODO 根据瓜名查询瓜
     *@Param [title]
     *@return java.util.ArrayList<com.linyuang.www.po.Melon>
    **/

    public static ArrayList<Melon> selectMelon(String title){
        /*StringBuilder sql = new StringBuilder();
        //标题为全英文时查询会出错，要加上双引号
        sql.append("select * from melon where title = ").append("\"").append(title).append("\"");
        return DbUtils.queryMelon(sql.toString());*/
        String sql = "select * from melon where title = ?";
        return DbUtils.queryMelon(sql,title);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 检查是否点赞
 *@Param [melonId]
 *@return java.util.ArrayList<com.linyuang.www.po.FolksMelon>
**/
 
    public static ArrayList<FolksMelon> checkLike(int melonId){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from folksmelon where liker_id = ").append(Service.nowUser).append(" AND likedmelon_id = ").append(melonId);
        return DbUtils.queryFolksMelon(sql.toString());*/
        String sql = "select * from folksmelon where liker_id = ? and likedmelon_id = ?";
        return DbUtils.queryFolksMelon(sql,Service.nowUser,melonId);
    }

    public static int updateFolksMelon(String likerId,int likedMelonId){
        Orm<FolksMelon> melonOrm = new Orm<>();
        FolksMelon folksMelon = new FolksMelon(likerId,likedMelonId);
        return melonOrm.insert(folksMelon);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 举报
 *@Param [mail]
 *@return int
**/
 
    public static int report(Mail mail){
        Orm<Mail> mailOrm = new Orm<>();
        return mailOrm.insert(mail);
    }
    /**
     *@Auther Yuang
     *@Description //TODO 首页展示所有的瓜，按热度降序排列
     *@Param []
     *@return java.util.ArrayList<com.linyuang.www.po.Melon>
    **/
     
    public static ArrayList<Melon> getAllMelon(){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from melon order by hotdegree DESC");
        return DbUtils.queryMelon(sql.toString());*/
        String sql = "select * from melon order by hotdegree DESC";
        return DbUtils.queryMelon(sql);
    }

    public static ArrayList<FolksMelon> showLikedMelon(){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from folksmelon where liker_id = ").append(Service.nowUser);
        return DbUtils.queryFolksMelon(sql.toString());*/
        String sql = "select * from folksmelon where liker_id = ?";
        return DbUtils.queryFolksMelon(sql,Service.nowUser);
    }
    public static ArrayList<Melon> showLikedMelon(ArrayList<FolksMelon> folksMelons){
        ArrayList<Melon> melons = new ArrayList<>();
        for(FolksMelon folksMelon : folksMelons){
            /*StringBuilder sql = new StringBuilder();
            sql.append("select * from melon where melon_id = ").append(folksMelon.getLikedMelonId());*/
            String sql = "select * from melon where melon_id = ?";
            melons.add(DbUtils.queryMelon(sql,folksMelon.getLikedMelonId()).get(0));
        }
        return melons;
    }

    public static int createCircle(MelonCircle melonCircle){
        Orm<MelonCircle> melonCircleOrm = new Orm<>();
        return melonCircleOrm.insert(melonCircle);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 管理员管理未被处理的信件
 *@Param []
 *@return java.util.ArrayList<com.linyuang.www.po.Mail>
**/
 
    public static ArrayList<Mail> adminMail(){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from mail where mailsolver_id is null");
        return DbUtils.queryMail(sql.toString());*/
        String sql = "select * from mail where mailsolver_id is null";
        return DbUtils.queryMail(sql);
    }
    public static int updateMail(Mail mail){
        Orm<Mail> mailOrm = new Orm<>();
        return mailOrm.update(mail);
    }

    public static ArrayList<Melon> findUnbelongMelon(){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from melon where belongcircle_id = 0");
        return DbUtils.queryMelon(sql.toString());*/
        String sql = "select * from melon where belongcircle_id = 0";
        return DbUtils.queryMelon(sql);
    }

    public static int comment(Comment comment){
        Orm<Comment> commentOrm = new Orm<>();
        return commentOrm.insert(comment);
    }

    public static int updateCircleHotDegree(MelonCircle circle){
        Orm<MelonCircle> melonCircleOrm = new Orm<>();
        return melonCircleOrm.update(circle);
    }

    public static ArrayList<Comment> getComment(int melonId){
        /*StringBuilder sql = new StringBuilder();
        sql.append("select * from comment where commented_melon = ").append(melonId);
        return DbUtils.queryComment(sql.toString());*/
        String sql = "select * from comment where commented_melon = ?";
        return DbUtils.queryComment(sql,melonId);
    }

    public static int deleteComments(Comment comment){
        Orm<Comment> commentOrm = new Orm<>();
        return commentOrm.delete(comment);
    }
}

