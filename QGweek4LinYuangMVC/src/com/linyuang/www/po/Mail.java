package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

import java.sql.Timestamp;


/**
 * @author 邮箱实体类
 */
@Table(name = "mail")
public class Mail {
    @Id(name = "mail_id",type = "int")
    private int mailId;
    @Column(name = "mailsenter_id")
    private String mailSenterId;
    @Column(name = "mailsolver_id")
    private String mailSolverId;
    @Column(name = "mailtext")
    private String mailText;
    @Column(name = "senttime",type = "timestamp")
    private Timestamp sentTime;
    @Column(name = "solvetime",type = "timestamp")
    private Timestamp solveTime;
    @Column(name = "solvetext")
    private String solveText;

    public Mail() {
    }

    public Mail(int mailId, String mailSenterId, String mailSolverId, String mailText, Timestamp sentTime, Timestamp solveTime, String solveText) {
        this.mailId = mailId;
        this.mailSenterId = mailSenterId;
        this.mailSolverId = mailSolverId;
        this.mailText = mailText;
        this.sentTime = sentTime;
        this.solveTime = solveTime;
        this.solveText = solveText;
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getMailSenterId() {
        return mailSenterId;
    }

    public void setMailSenterId(String mailSenterId) {
        this.mailSenterId = mailSenterId;
    }

    public String getMailSolverId() {
        return mailSolverId;
    }

    public void setMailSolverId(String mailSolverId) {
        this.mailSolverId = mailSolverId;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public Timestamp getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Timestamp solveTime) {
        this.solveTime = solveTime;
    }

    public String getSolveText() {
        return solveText;
    }

    public void setSolveText(String solveText) {
        this.solveText = solveText;
    }

    @Override
    public String toString() {
        return "@mailId=" + mailId + '\n' +
                "@mailSenterId=" + mailSenterId + '\n' +
                "@mailSolverId=" + mailSolverId + '\n' +
                "@mailText=" + mailText + '\n' +
                "@sentTime=" + sentTime + '\n' +
                "@solveTime=" + solveTime + '\n' +
                "@solveText=" + solveText + '\n';
    }
}
