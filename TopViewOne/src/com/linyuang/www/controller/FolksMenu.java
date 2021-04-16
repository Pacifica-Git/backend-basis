package com.linyuang.www.controller;
import com.Main;
import com.linyuang.www.po.Folks;
import com.linyuang.www.po.Mail;
import com.linyuang.www.po.MelonCircle;
import com.linyuang.www.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.script.ScriptContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author 吃瓜群众页面
 */
public class FolksMenu {
    //吃瓜群众菜单中6个选项
    public static Button showMelon, showCircle, showLikedMelon, postMelon, showInformation, showMail, other;
    /**
     *@Auther Yuang
     *@Description //TODO 瓜圈展示界面，瓜圈的入口，每个页面呈现6个瓜圈
     *@Param
     *@return
    **/
    public static Button circle1,circle2,circle3,circle4,circle5,circle6;
    //流式窗格放置6个选项
    public static FlowPane flowPane1;
    /**
     *@Auther Yuang
     *@Description //TODO 展示吃瓜群众主界面,即展示所有瓜
     *@Date
     *@Param [stage]
     *@return void
     **/
    public static void mainMenu(Stage stage){
        showMelon = new Button("所有瓜");
        showMelon.setOnMouseClicked(e->{
            showMelons(stage);
        });

        showCircle = new Button("所有瓜圈");
        showCircle.setOnMouseClicked(e->{
            circleFace(Main.stage);
        });

        showLikedMelon = new Button("我的点赞");
        showLikedMelon.setOnMouseClicked(e->{
            showLikedMelon(stage);
        });

        postMelon = new Button("我要发瓜");
        postMelon.setOnMouseClicked(e-> FolksMenu.postMelon(Main.stage));

        showInformation = new Button("我的个人信息");
        showInformation.setOnMouseClicked(e->{
            showInformation(stage);
        });

        showMail = new Button("收件箱");
        showMail.setOnMouseClicked(e->{
            showMail(stage);
        });

        other = new Button("其他");

        flowPane1 = new FlowPane();
        flowPane1.getChildren().addAll(showMelon, showCircle, showLikedMelon, postMelon, showInformation, showMail);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        showMelons(stage);
    }
/**
 *@Auther Yuang
 *@Description //TODO 瓜圈展示界面
 *@Param [stage]
 *@return void
**/
    public static void showMelons(Stage stage){
        /*Pagination pagination1 = new Pagination(10,0);
        pagination1.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                ArrayList<MelonCircle> melonCircles = Service.showPageCircle(param);
                return null;
            }
        });*/
        //将瓜的信息转化为obversable再用listview展示   ,pagination1
        List<String> melonList = new ArrayList<>();
        Collections.addAll(melonList,"正经！@#￥%……&*（","232","23423","2333234");
        ObservableList<String> obArray = FXCollections.observableArrayList(melonList);
        ListView<String> listView = new ListView<>(obArray);
        listView.setPrefSize(500,800);
        /*listView.setOnMouseClicked(e->{
            String selectMelon = new String();
            boolean res = AlertWindow.display("查看瓜","是否查看"+ (selectMelon = listView.getSelectionModel().getSelectedItems().toString()));
            if(res){
                //showMelon(stage,selectMelon);
            }
        });*/
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(flowPane1,listView);
        Scene scene2 = new Scene(vBox1,800,800);

        stage.setScene(scene2);
        stage.show();
    }

    public static void showLikedMelon(Stage stage){

    }

    public static void circleFace(Stage stage){
        Pagination pagination1 = new Pagination(10,0);
        pagination1.setPageFactory(param -> {
            ArrayList<MelonCircle> melonCircles = Service.showPageCircle(param);
            VBox vBox1 = new VBox();
            if(melonCircles != null){
                circle1 = new Button("title");circle1.setMinSize(240,240);circle1.setTextFill(Color.BLUE);
                circle2 = new Button("title");circle2.setMinSize(240,240);circle2.setTextFill(Color.RED);
                Label label1 = new Label("           ");
                HBox hBox1 = new HBox();hBox1.getChildren().addAll(circle1,label1,circle2);hBox1.setAlignment(Pos.CENTER);

                circle3 = new Button("title");circle3.setMinSize(240,240);circle3.setTextFill(Color.GOLD);
                circle4 = new Button("title");circle4.setMinSize(240,240);circle4.setTextFill(Color.BROWN);
                Label label2 = new Label("           ");
                HBox hBox2 = new HBox();hBox2.getChildren().addAll(circle3,label2,circle4);hBox2.setAlignment(Pos.CENTER);

                circle5 = new Button("title");circle5.setMinSize(240,240);circle5.setTextFill(Color.GRAY);
                circle6 = new Button("title");circle6.setMinSize(240,240);circle6.setTextFill(Color.GREEN);
                Label label3 = new Label("           ");
                HBox hBox3 = new HBox();hBox3.getChildren().addAll(circle5,label3,circle6);hBox3.setAlignment(Pos.CENTER);

                vBox1 = new VBox();vBox1.getChildren().addAll(flowPane1,hBox1,hBox2,hBox3,pagination1);vBox1.setAlignment(Pos.TOP_CENTER);
            }
            return vBox1;
        });
        Scene scene1 = new Scene(pagination1,800,800);
        stage.setScene(scene1);
        stage.show();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 群众发布瓜的界面
     *@Param [stage]
     *@return void
    **/
    public static void postMelon(Stage stage){
        Label label1 = new Label("为您的瓜起个瓜名叭："),label2 = new Label("您想分享什么瓜呢："),label3 = new Label("           ");
        TextField melonTitle = new TextField();
        TextArea melonText = new TextArea();
        melonTitle.setMaxSize(250,1);
        melonText.setMaxSize(400,600);

        Button buttonOk = new Button("瓜写好了！"),buttonClr = new Button("重置");
        buttonClr.setOnMouseClicked(e->{
            melonText.clear();
            melonTitle.clear();
        });
        buttonOk.setOnMouseClicked(e2->{
            Service.postMelon(melonTitle.getText(),melonText.getText());
        });
        HBox hBox1 = new HBox();hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(label1,melonTitle);
        HBox hBox2 = new HBox();hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label2,melonText);
        HBox hBox3 = new HBox();hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(buttonOk,label3,buttonClr);

        Label empty1 = new Label("\n\n");
        Label empty2 = new Label("\n");
        Label empty3 = new Label("\n");
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(flowPane1,empty1,hBox1,empty2,hBox2,empty3,hBox3);

        Scene scene = new Scene(vBox1,600,600);

        stage.setScene(scene);
        stage.show();
    }

    public static void showInformation(Stage stage){
        Folks folks = Service.showInformation().get(0);
        Label label1 = new Label("  ID  : ");
        TextField textField1 = new TextField(folks.getFolksId());
        textField1.setEditable(false);

        Label label2 = new Label("昵称 : ");
        TextField textField2 = new TextField(folks.getName());
        textField2.setEditable(false);

        Label label3 = new Label("生日 : ");
        TextField textField3 = new TextField(folks.getBirthday());
        textField3.setEditable(false);

        Label label4 = new Label("   爱好    : ");
        TextArea textArea1 = new TextArea(folks.getHobby());
        textArea1.setEditable(false);

        Label label5 = new Label("个人签名 : ");
        TextArea textArea2 = new TextArea(folks.getInstruction());
        textArea2.setEditable(false);

        Label empty1 = new Label("\n\n\n\n\n");
        Label empty2 = new Label("\n");
        Label empty3 = new Label("\n");
        Label empty4 = new Label("\n");
        Label empty5 = new Label("\n");
        Label empty6 = new Label("\n");
        Button button1 = new Button("修改");
        button1.setOnMouseClicked(e1->{
            textField2.setEditable(true);
            textField3.setEditable(true);
            textArea1.setEditable(true);
            textArea2.setEditable(true);
            button1.setText("保存");
            button1.setOnMouseClicked(e2->{
                Service.updateFolks(Service.nowUser,textField2.getText(),textField3.getText(),textArea1.getText(),textArea2.getText());
            });
        });
        HBox hBox1 = new HBox(),hBox2 = new HBox(),hBox3 = new HBox(),hBox4 = new HBox(),hBox5 = new HBox();
        hBox1.getChildren().addAll(label1,textField1);hBox1.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label2,textField2);hBox2.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(label3,textField3);hBox3.setAlignment(Pos.CENTER);
        hBox4.getChildren().addAll(label4,textArea1);hBox4.setAlignment(Pos.CENTER);
        hBox5.getChildren().addAll(label5,textArea2);hBox5.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(flowPane1,empty1,hBox1,empty2,hBox2,empty3,hBox3,empty4,hBox4,empty5,hBox5,empty6,button1);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        vBox1.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(vBox1,800,800);
        stage.setScene(scene1);
        stage.show();
    }

    public static void showMail(Stage stage){
        //ArrayList<TextArea> textAreas = new ArrayList<>();
        VBox vBox1 = new VBox();
        vBox1.getChildren().add(flowPane1);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane1 = new ScrollPane();
        ArrayList<Mail> mail1 = Service.getMail();
        if(mail1 != null){
            for (Mail mail : mail1) {
                TextArea textArea = new TextArea();
                textArea.setMinSize(900, 100);
                textArea.setText(mail.toString());
                vBox1.getChildren().add(textArea);
            }
            vBox1.setMinSize(800,1000);
            scrollPane1.setContent(vBox1);
            Scene scene1 = new Scene(scrollPane1,800,800);
            stage.setScene(scene1);
            stage.show();
        }
    }
}
