package com.linyuang.www.po;

import com.linyuang.www.annotation.Column;
import com.linyuang.www.annotation.Id;
import com.linyuang.www.annotation.Table;

/**
 * @author Lenovo
 */
@Table(name = "admin")
public class Admin {
    @Id(name = "admin_id")
    private String adminId;
    @Column(name = "instruction")
    private String adminInstruction;

    public String getAdminInstruction() {
        return adminInstruction;
    }

    public void setAdminInstruction(String adminInstruction) {
        this.adminInstruction = adminInstruction;
    }

    public Admin(String adminId, String adminInstruction) {
        this.adminId = adminId;
        this.adminInstruction = adminInstruction;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                '}';
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
