package com.linyuang.www.controller;

import com.Main;
import com.linyuang.www.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author 展示登陆界面以及注册界面
 */
public class LoginMenu {
    public static Label label1,label2;
    public static TextField loginName, registerName;
    public static PasswordField loginPass, registerPass;
    public static Button buttonLogin,buttonRegister,confirmRegister;

    /**
     *@Auther Yuang
     *@Description //TODO 程序启动的界面
     *@Date
     *@Param [stage]
     *@return void
     **/
    public static void loginFace(Stage stage){
        stage.setTitle("TiLiTiLi吃瓜");
        GridPane gridPane1 = new GridPane();
        gridPane1.setAlignment(Pos.CENTER);

        label1 = new Label();
        label1.setText("用户ID:");
        loginName = new TextField();
        loginName.setEditable(true);
        loginName.setPromptText("请输入用户ID");
        label2 = new Label();
        label2.setText("密码:");
        loginPass = new PasswordField();
        loginPass.setEditable(true);
        loginPass.setPromptText("请输入密码");

        buttonLogin = new Button("             登录          ");
        //设置登录键
        buttonLogin.setOnMouseClicked(e->{
            int result = Service.login(loginName.getText(),loginPass.getText());
            if(result == 1){
                //返回1是吃瓜群众
                FolksMenu.mainMenu(Main.stage);
            }else if(result == -1){
                //返回-1说明是管理员，打开管理员界面
                AdminMenu.mainMenu(Main.stage);
            }
        });
        buttonRegister = new Button("首次使用？点我注册");
        buttonRegister.setTextFill(Color.BLUE);
        //设置注册键
        buttonRegister.setOnMouseClicked(e->{
            LoginMenu.registerFace();
        });

        VBox vBox1 = new VBox();vBox1.getChildren().addAll(buttonLogin,buttonRegister);

        //用网格布局
        gridPane1.add(label1,1,0);
        gridPane1.add(loginName,2,0);
        gridPane1.add(label2,1,1);
        gridPane1.add(loginPass,2,1);
        gridPane1.add(vBox1,2,8);

        Scene scene1 = new Scene(gridPane1,500,500);
        stage.setScene(scene1);
        stage.show();
    }

    /**
     *@Auther Yuang
     *@Description //TODO 注册吃瓜群众界面
     *@Param [stage]
     *@return void
     **/
    public static void registerFace(){

        Stage stage = new Stage();
        stage.setTitle("注册新账号");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        VBox vBox = new VBox();
        confirmRegister = new Button();
        confirmRegister.setText("马上注册！");
        confirmRegister.setTextFill(Color.BLUE);
        //用户确认注册由service层进行用户名长度是否符合要求等逻辑处理
        confirmRegister.setOnMouseClicked(e2->{
            if(Service.register(LoginMenu.registerName.getText(), LoginMenu.registerPass.getText())){
                stage.close();
            }
        });

        Label label1 = new Label("您的用户ID："),label2 = new Label("您的密码   ：");

        //注册的用户名
        registerName = new TextField();
        registerName.setMinSize(250,20);
        //注册的密码
        registerPass = new PasswordField();
        registerPass.setMinSize(250,20);
        registerName.setPromptText("请输入9位数字作为您的用户ID");
        registerPass.setPromptText("请输入8~16位字符作为您的密码");

        HBox hBox1 = new HBox(),hBox2 = new HBox(),hBox3 = new HBox();
        hBox1.getChildren().addAll(label1, registerName);hBox1.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label2, registerPass);hBox2.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(confirmRegister);
        hBox3.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,440,340);
        stage.setScene(scene);
        stage.showAndWait();
    }
/**
 *@Auther Yuang
 *@Description //TODO 完成点击主窗口的关闭键的操作
 *@Param [stage, title, message]
 *@return void
**/
 
    public static void closeWindow(Stage stage, String title, String message){
        if(AlertWindow.alertConfirm(title, message)){
            stage.close();
        }
    }
}
