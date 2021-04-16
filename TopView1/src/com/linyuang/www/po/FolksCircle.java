package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

/**
 * @author 群众与其点赞的瓜圈
 */
@Table(name = "folkscircle")
public class FolksCircle {
    @Id(name = "liker_id")
    private String likerId;
    @Column(name = "likedcircle_id",type = "int")
    private int likedCircleId;

    public FolksCircle(String likerId, int likedCircleId) {
        this.likerId = likerId;
        this.likedCircleId = likedCircleId;
    }

    public FolksCircle() {
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }

    public int getLikedCircleId() {
        return likedCircleId;
    }

    public void setLikedCircleId(int likedCircleId) {
        this.likedCircleId = likedCircleId;
    }

    @Override
    public String toString() {
        return "FolksCircle{" +
                "likerId='" + likerId + '\'' +
                ", likedCircleId='" + likedCircleId + '\'' +
                '}';
    }
}
