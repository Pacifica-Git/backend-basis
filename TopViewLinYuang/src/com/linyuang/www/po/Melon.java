package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

import java.sql.Timestamp;

/**
 * @author 瓜实体类
 */
@Table(name = "melon")
public class Melon {
    @Id(name = "melon_id",type = "int")
    private  int melonId;
    @Column(name = "title")
    private String title;
    @Column(name = "melontext")
    private String melonText;
    @Column(name = "hotdegree",type = "int")
    private int hotDegree;
    @Column(name = "belongcircle_id",type = "int")
    private int belongCircleId;
    @Column(name = "posttime",type = "timestamp")
    private Timestamp postTime;
    @Column(name = "melon_senter_id")
    private String melonSenterId;

    public Melon(int melonId, String title, String melonText, int hotDegree, int belongCircleId, Timestamp postTime, String melonSenterId) {
        this.melonId = melonId;
        this.title = title;
        this.melonText = melonText;
        this.hotDegree = hotDegree;
        this.belongCircleId = belongCircleId;
        this.postTime = postTime;
        this.melonSenterId = melonSenterId;
    }

    public Melon() {
    }


    public int getMelonId() {
        return melonId;
    }

    public void setMelonId(int melonId) {
        this.melonId = melonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMelonText() {
        return melonText;
    }

    public void setMelonText(String melonText) {
        this.melonText = melonText;
    }

    public int getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(int hotDegree) {
        this.hotDegree = hotDegree;
    }

    public int getBelongCircleId() {
        return belongCircleId;
    }

    public void setBelongCircleId(int belongCircleId) {
        this.belongCircleId = belongCircleId;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public String getMelonSenterId() {
        return melonSenterId;
    }

    public void setMelonSenterId(String melonSenterId) {
        this.melonSenterId = melonSenterId;
    }

    @Override
    public String toString() {
        return title + '\n' +
                "热度:" + hotDegree + '\n' +
                "postTime:" + postTime;
    }
}
