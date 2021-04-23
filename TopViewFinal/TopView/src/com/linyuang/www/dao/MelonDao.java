package com.linyuang.www.dao;

import com.linyuang.www.po.Melon;
import com.linyuang.www.util.Orm;

/**
 * @author Lenovo
 */
public class MelonDao {
    /**
     *@Auther Yuang
     *@Description //TODO 发布新瓜，插入表中
     *@Param [title, text]
     *@return int
     **/
    public static int postMelon(Melon newMelon){
        Orm<Melon> dbManipulate = new Orm<>();
        return dbManipulate.insert(newMelon);
    }

}
