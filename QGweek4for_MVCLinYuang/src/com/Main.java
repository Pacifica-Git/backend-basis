package com;

import com.linyuang.www.controller.LoginMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Lenovo
 */
public class Main extends Application{
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        //设置点击关闭键的操作
        stage.setOnCloseRequest(e->{
            LoginMenu.closeWindow(Main.stage,"退出","确认退出TiTiLiLi吃瓜吗");
            e.consume();
        });
        LoginMenu.loginFace(stage);
    }
}
