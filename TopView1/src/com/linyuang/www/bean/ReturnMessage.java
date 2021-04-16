package com.linyuang.www.bean;

/**
 * @author 返回给用户的信息对应的实体类
 */
public class ReturnMessage {
    private String title;
    private String message;

    @Override
    public String toString() {
        return "ReturnMessage{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ReturnMessage() {
    }
}
