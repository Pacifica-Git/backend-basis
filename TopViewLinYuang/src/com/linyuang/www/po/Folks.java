package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;


/**
 * @author Lenovo 吃瓜群众对应实体类
 */
@Table(name = "folks")
public class Folks {
    @Id(name = "folks_id")
    private String folksId;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "hobby")
    private String hobby;
    @Column(name = "instruction")
    private String instruction;

    public Folks(String folksId, String name, String birthday, String hobby, String instruction) {
        this.folksId = folksId;
        this.name = name;
        this.birthday = birthday;
        this.hobby = hobby;
        this.instruction = instruction;
    }

    public Folks() {
    }

    public String getFolksId() {
        return folksId;
    }

    public void setFolksId(String folksId) {
        this.folksId = folksId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Folks{" +
                "folksId='" + folksId + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", hobby='" + hobby + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
