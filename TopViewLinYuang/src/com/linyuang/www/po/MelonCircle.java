package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Lenovo
 */
@Table(name = "meloncircle")
public class MelonCircle {
    @Id(name = "circle_id",type = "int")
    private int circleId;
    @Column(name = "circle_name")
    private String circleName;
    @Column(name = "create_time",type = "timestamp")
    private Timestamp createTime;
    @Column(name = "hotdegree",type = "int")
    private int hotDegree;
    @Column(name = "administrator_id")
    private String administratorId;

    public MelonCircle(int circleId, String circleName, Timestamp createTime, int hotDegree, String administratorId) {
        this.circleId = circleId;
        this.circleName = circleName;
        this.createTime = createTime;
        this.hotDegree = hotDegree;
        this.administratorId = administratorId;
    }

    public MelonCircle() {
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(int hotDegree) {
        this.hotDegree = hotDegree;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    @Override
    public String toString() {
        return "@瓜圈：" + circleName +
                " @创建时间：" + createTime +
                " @热度：" + hotDegree;
    }
}
