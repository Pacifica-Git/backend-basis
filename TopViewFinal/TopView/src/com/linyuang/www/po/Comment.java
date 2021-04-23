package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

import java.sql.Timestamp;

/**
 * @author 评论与评论的人
 */
@Table(name = "comment")
public class Comment {
    @Column(name = "commenter_id")
    private String commenterId;
    @Column(name = "commented_melon",type = "int")
    private int commentedMelonId;
    @Column(name = "commenttext")
    private String commentText;

    public Comment(String commenterId, int commentedMelonId, String commentText) {
        this.commenterId = commenterId;
        this.commentedMelonId = commentedMelonId;
        this.commentText = commentText;
    }

    public Comment() {
    }

    public String getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getCommentedMelonId() {
        return commentedMelonId;
    }

    public void setCommentedMelonId(int commentedMelonId) {
        this.commentedMelonId = commentedMelonId;
    }

    /*public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }*/

    @Override
    public String toString() {
        return "--" + commentText + "\n\t\t@" + commenterId + '\n';
    }
}
