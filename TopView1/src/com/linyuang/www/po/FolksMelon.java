package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

/**
 * @author 群众与其关注的瓜圈
 */
@Table(name = "folksmelon")
public class FolksMelon {
    @Id(name = "liker_id")
    private String likerId;
    @Column(name = "likedmelon_id",type = "int")
    private int likedMelonId;

    public FolksMelon(String likerId, int likedMelonId) {
        this.likerId = likerId;
        this.likedMelonId = likedMelonId;
    }

    public FolksMelon() {
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }

    public int getLikedMelonId() {
        return likedMelonId;
    }

    public void setLikedMelonId(int likedMelonId) {
        this.likedMelonId = likedMelonId;
    }

    @Override
    public String toString() {
        return "FolksMelon{" +
                "likerId='" + likerId + '\'' +
                ", likedCircleId='" + likedMelonId + '\'' +
                '}';
    }
}
