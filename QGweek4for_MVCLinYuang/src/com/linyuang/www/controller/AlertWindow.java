package com.linyuang.www.controller;
import com.linyuang.www.po.Mail;
import com.linyuang.www.po.Melon;
import com.linyuang.www.po.MelonCircle;

import com.linyuang.www.service.Service;
import com.linyuang.www.util.CheckValid;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
/**
 * @author LinYuang
 * 这个类的方法用来弹出窗口
 */
public class AlertWindow {
    /**
     *@Auther Yuang
     *@Description //TODO 弹出普通的提示操作成功的窗口
     *@Param [title, message]
     *@return void
    **/
    public static void alertInformation(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
 *@Auther Yuang
 *@Description //TODO 弹出需要用户选择是否的窗口
 *@Param [title, message]
 *@return boolean
**/
    public static boolean alertConfirm(String title,String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }
    /**
 *@Auther Yuang
 *@Description //TODO 弹出操作失败等警示信息的窗口
 *@Param [title, message]
 *@return void
**/

    public static void alertWarning(String title,String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 点击瓜圈界面的瓜圈时弹出属于其下的所有瓜
     *@Param [circles, melons]
     *@return void
    **/

    public static void alertCircle(ArrayList<MelonCircle> circles, ArrayList<Melon> melons){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        MelonCircle circle = circles.get(0);
        //重写了toString方法作为窗口标题
        stage.setTitle(circle.toString());
        VBox vBox1 = new VBox();
        //添加滚动条
        ScrollPane scrollPane1 = new ScrollPane();
        if(melons != null){
            int circleHotDegree = 0;
            for (Melon melon : melons) {
                TextArea textArea = new TextArea();
                textArea.setMinSize(200, 50);
                textArea.setEditable(false);
                //重写了toString方法，展示界面省略了瓜文内容的其他信息
                textArea.setText(melon.toString());
                textArea.setOnMouseClicked(e -> {
                    //点击后向service发送发开事件的请求
                    Service.openMelon(melon.getMelonId());
                });
                circleHotDegree += melon.getHotDegree();
                vBox1.getChildren().add(textArea);
            }
            Service.updateCircleHotDegree(circle,circleHotDegree);
            vBox1.setMinSize(700,1000);
        }
        scrollPane1.setContent(vBox1);
        Scene scene1 = new Scene(scrollPane1,800,800);
        stage.setScene(scene1);
        stage.showAndWait();
    }
    /**
 *@Auther Yuang
 *@Description //TODO 点击瓜概览后弹出瓜的详细信息
 *@Param [melons]
 *@return void
**/

    public static void alertMelon(ArrayList<Melon> melons){
        Melon melon = melons.get(0);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("@瓜名  " + melon.getTitle());

        TextArea textArea1 = new TextArea();
        textArea1.setEditable(false);
        textArea1.setText(melon.getMelonText());

        Label label1 = new Label("@热度：" + melon.getHotDegree());
        Label label2 = new Label("@发布时间：" + melon.getPostTime());
        //空白或换行标签用来使页面组件分布均匀，美观
        Label empty1 = new Label("    ");
        Label empty2 = new Label("    ");
        Label empty3 = new Label("    ");
        Label empty5 = new Label("    ");
        Label empty4 = new Label("\n\n");

        Button buttonModify = new Button("修改"),buttonLike = new Button("点赞"),buttonReport = new Button("举报"),buttonComment = new Button("评论");
        //先检查当前瓜是否已经被当前用户nowUser点过赞，点过显示红色
        if(Service.checkLike(melon.getMelonId())){
            buttonLike.setTextFill(Color.RED);
        }
        //设置修改键
        buttonModify.setOnMouseClicked(e->{
            //让service完成是否是当前用户写的瓜的判断
            if(Service.modifyCheckSenter(melon.getMelonId())){
                textArea1.setEditable(true);
                //如果可以修改，将修改键变为完成键
                buttonModify.setText("完成");
                //完成后在数据库中修改对应信息
                buttonModify.setOnMouseClicked(e1->{
                    textArea1.setText(textArea1.getText());
                    textArea1.setEditable(false);
                    Service.modifyMelon(melon,textArea1.getText());
                    stage.close();
                });
            }
        });
        //设置点赞键
        buttonLike.setOnMouseClicked(e->{
            //如果点过了没有任何反应
            if(!Service.checkLike(melon.getMelonId())){
                Service.like(melon.getMelonId());
                buttonLike.setTextFill(Color.RED);
            }
        });
        //设置举报键
        buttonReport.setOnMouseClicked(e->{
            //不反对举报自己!
            if(Service.reportCheckSenter(melon.getMelonId())){
                if(AlertWindow.alertConfirm("您确定举报自己的瓜吗","虽然这不好但我们会尊重你的决定")){
                    //弹出举报的窗口
                    alertReport(melon.getMelonId(),melon.getMelonText());
                }
            }else{
                alertReport(melon.getMelonId(),melon.getMelonText());
            }
        });
        //评论键
        buttonComment.setOnMouseClicked(e->{
            alertComment(melon);
        });
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(buttonModify,empty1,buttonLike,empty2,buttonReport,empty5,buttonComment);
        hBox1.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(textArea1,empty4,label1,label2,empty3,hBox1);

        Scene scene1 = new Scene(vBox1,600,600);
        stage.setScene(scene1);
        stage.showAndWait();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 弹出用于举报的窗口
     *@Param [melonId, melonText]
     *@return void
    **/

    public static void alertReport(int melonId,String melonText){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setPromptText("请输入您的举报原因");
        Button button = new Button("确定");
        Label empty1 = new Label("\n\n");
        Label empty2 = new Label("      ");

        VBox vBox = new VBox();vBox.getChildren().addAll(textArea,empty1,empty2,button);
        Scene scene = new Scene(vBox,400,400);

        button.setOnMouseClicked(e->{
            //调用util工具类中的方法判断输入内容是否都是空格，是则无效举报
            if(!CheckValid.checkValidReport(textArea.getText())){
                AlertWindow.alertWarning("无效举报","请输入举报原因！");
            }else{
                //有效举报，更新mail表
                Service.report(melonId,melonText,textArea.getText());
                stage.close();
            }
        });
        stage.setScene(scene);
        stage.showAndWait();
    }
    /**
 *@Auther Yuang
 *@Description //TODO 弹出处理举报的窗口
 *@Param [mail]
 *@return void
**/

    public static void alertSolve(Mail mail){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setPromptText("请输入您的回复");
        Button button = new Button("确定");
        Label empty1 = new Label("\n\n");
        Label empty2 = new Label("         ");

        VBox vBox = new VBox();vBox.getChildren().addAll(textArea,empty1,empty2,button);
        Scene scene = new Scene(vBox,400,400);

        button.setOnMouseClicked(e->{
            //调用util工具类中的方法判断输入内容是否都是空格
            if(CheckValid.checkValidReport(textArea.getText())){
                AlertWindow.alertWarning("回复失败","请输入有效回复");
            }else{
                //有效举报，更新mail表
                Service.updateMail(mail,textArea.getText());
                stage.close();
            }
        });
        stage.setScene(scene);
        stage.showAndWait();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 弹出把瓜要分配至哪个瓜圈的窗口
     *@Param
    **/
    public static void alertSetBelong(Melon melon){
        Stage stage = new Stage();
        stage.setResizable(false);
        Label label1 = new Label("请输入瓜圈id"),label2 = new Label("\n   ");
        TextField textField1 = new TextField();
        textField1.setMaxSize(100,10);
        Button button1 = new Button("确定");
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(label1,textField1);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox1,label2,button1);
        hBox1.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        button1.setOnMouseClicked(e->{
            Service.setBelong(melon, textField1.getText());
        });

        Scene scene = new Scene(vBox,400,200);
        stage.setScene(scene);
        stage.show();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 弹出进行评论的窗口
     *@Param [melon]
     *@return void
    **/
     
    public static void alertComment(Melon melon){
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        TextArea textArea = new TextArea();
        textArea.setPromptText("友善评论，温暖评论区");
        Button button = new Button("确认");
        Label empty = new Label("\n\n\t\t\t");
        button.setOnMouseClicked(e->{
            Service.comment(melon,textArea.getText());
            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea,empty,button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,200,200);
        stage.setScene(scene);
        stage.show();
    }
}
