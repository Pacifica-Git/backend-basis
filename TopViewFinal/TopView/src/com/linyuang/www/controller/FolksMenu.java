package com.linyuang.www.controller;
import com.Main;
import com.linyuang.www.po.*;
import com.linyuang.www.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

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
     *@Description //TODO 展示吃瓜群众主界面,主界面即展示所有瓜
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
            showCircle(Main.stage);
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
 *@Description //TODO 所有瓜展示界面
 *@Param [stage]
 *@return void
**/
    public static void showMelons(Stage stage){
        //添加滚动条
        AdminMenu.generateMelons(stage, flowPane1);
    }
    /**
 *@Auther Yuang
 *@Description //TODO 展示点赞的瓜
 *@Param [stage]
 *@return void
**/
 
    public static void showLikedMelon(Stage stage){
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        vBox.getChildren().add(flowPane1);
        ArrayList<Melon> melons = Service.showLikedMelon();
        ListView<String> listView;
        if(melons != null){
            listView = AdminMenu.getStringListView(melons);
            vBox.getChildren().add(listView);
        }
        vBox.setMinSize(700,900);
        scrollPane.setContent(vBox);
        Scene scene3 = new Scene(scrollPane,800,800);

        stage.setScene(scene3);
        stage.show();
    }

    /**
 *@Auther Yuang
 *@Description //TODO 展示所有的瓜圈
 *@Param [stage]
 *@return void
**/
    public static void showCircle(Stage stage){
        //用分页的方式显示瓜圈
        Pagination pagination1 = new Pagination(10,0);
        final VBox[] vBox1 = new VBox[1];
        //按钮内容默认为null，查询瓜圈时再修改，之后点击的按钮如果文字内容是null说明没有那么多瓜圈，不会给出反馈
        circle1 = new Button("null");circle1.setMinSize(240,240);circle1.setTextFill(Color.BLUE);
        circle1.setOnMouseClicked(e->{
            Service.openCircle(circle1.getText());
        });
        circle2 = new Button("null");circle2.setMinSize(240,240);circle2.setTextFill(Color.RED);
        circle2.setOnMouseClicked(e->{
            Service.openCircle(circle2.getText());
        });
        circle3 = new Button("null");circle3.setMinSize(240,240);circle3.setTextFill(Color.GOLD);
        circle3.setOnMouseClicked(e->{
            Service.openCircle(circle3.getText());
        });
        circle4 = new Button("null");circle4.setMinSize(240,240);circle4.setTextFill(Color.BROWN);
        circle4.setOnMouseClicked(e->{
            Service.openCircle(circle4.getText());
        });
        circle5 = new Button("null");circle5.setMinSize(240,240);circle5.setTextFill(Color.GRAY);
        circle5.setOnMouseClicked(e->{
            Service.openCircle(circle4.getText());
        });
        circle6 = new Button("null");circle6.setMinSize(240,240);circle6.setTextFill(Color.GREEN);
        circle6.setOnMouseClicked(e->{
            Service.openCircle(circle6.getText());
        });
        //每个界面展示6个瓜圈
        Button[] buttons = new Button[]{circle1,circle2,circle3,circle4,circle5,circle6};
        //添加分页控件工厂类
        pagination1.setPageFactory(pageIndex -> {
            ArrayList<MelonCircle> melonCircles = Service.showPageCircle(pageIndex);
            vBox1[0] = new VBox();
            int j = 0;
            //将分页查询得到的瓜圈包装到按钮上
            if(melonCircles != null){
                for(MelonCircle i:melonCircles){
                    buttons[j].setText(i.getCircleName());
                    buttons[j++].setMinSize(240,240);
                }
            }else{
                AlertWindow.alertWarning("查询瓜圈失败","并没有这么多瓜圈哦");
            }
            //空白标签是为了制造按钮间的距离，使页面美观
            Label label1 = new Label("           ");
            HBox hBox1 = new HBox();hBox1.getChildren().addAll(circle1,label1,circle2);hBox1.setAlignment(Pos.CENTER);
            Label label2 = new Label("           ");
            HBox hBox2 = new HBox();hBox2.getChildren().addAll(circle3,label2,circle4);hBox2.setAlignment(Pos.CENTER);
            Label label3 = new Label("           ");
            HBox hBox3 = new HBox();hBox3.getChildren().addAll(circle5,label3,circle6);hBox3.setAlignment(Pos.CENTER);

            vBox1[0].getChildren().addAll(hBox1,hBox2,hBox3);
            vBox1[0].setAlignment(Pos.TOP_CENTER);
            return vBox1[0];
        });

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(flowPane1,pagination1);
        vBox2.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(vBox2,800,800);
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
        //设置重置键
        buttonClr.setOnMouseClicked(e->{
            melonText.clear();
            melonTitle.clear();
        });
        ///设置确认键
        buttonOk.setOnMouseClicked(e2->{
            boolean result = Service.postMelon(melonTitle.getText(),melonText.getText());
            if(result){
                AlertWindow.alertInformation("发布结果","发布成功！一起来吃瓜吧");
            }else{
                AlertWindow.alertWarning("发布结果","发布失败！可能是创建事件时出错，请重新尝试");
            }
        });
        HBox hBox1 = new HBox();hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(label1,melonTitle);
        HBox hBox2 = new HBox();hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label2,melonText);
        HBox hBox3 = new HBox();hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(buttonOk,label3,buttonClr);

        //换行标签也是为了拉开距离，使页面美观
        Label empty1 = new Label("\n\n");
        Label empty2 = new Label("\n");
        Label empty3 = new Label("\n");
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(flowPane1,empty1,hBox1,empty2,hBox2,empty3,hBox3);

        Scene scene = new Scene(vBox1,600,600);

        stage.setScene(scene);
        stage.show();
    }
    /**
     *@Auther Yuang
     *@Description //TODO 展示用户作为吃瓜群众的个人信息
     *@Param [stage]
     *@return void
    **/
 
    public static void showInformation(Stage stage){
        //先从数据库获取信息
        Folks folks = new Folks();
        if(Service.showInformation() != null){
            folks = Service.showInformation().get(0);
        }else{
            AlertWindow.alertWarning("查询结果","查询个人信息失败，可能是读取数据时出错，请重试");
        }
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
        //设置修改键
        button1.setOnMouseClicked(e1->{
            textField2.setEditable(true);
            textField3.setEditable(true);
            textArea1.setEditable(true);
            textArea2.setEditable(true);
            button1.setText("保存");
            button1.setOnMouseClicked(e2->{
                //修改完毕修改数据库中信息
                boolean result = Service.updateFolks(Service.nowUser,textField2.getText(),textField3.getText(),textArea1.getText(),textArea2.getText());
                if(result){
                    AlertWindow.alertInformation("修改结果","修改成功！请重新打开界面查看");
                }else{
                    AlertWindow.alertWarning("修改失败","可能是更改数据时出错，请重新尝试");
                }
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
    /**
 *@Auther Yuang
 *@Description //TODO 展示收件箱，收件箱主要是用户发出举报，管理员的反馈，管理员跟吃瓜群众都能看到，是同一个邮箱
 *@Param [stage]
 *@return void
**/
 
    public static void showMail(Stage stage){
        VBox vBox1 = new VBox();
        vBox1.getChildren().add(flowPane1);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane1 = new ScrollPane();
        ArrayList<Mail> mail1 = Service.getMyMail();
        if(mail1 != null){
            for (Mail mail : mail1) {
                TextArea textArea = new TextArea();
                textArea.setMinSize(780, 100);
                textArea.setEditable(false);
                //重写toString方法是展示信件信息
                textArea.setText(mail.toString());
                vBox1.getChildren().add(textArea);
            }
        }else{
            AlertWindow.alertWarning("查询失败","您已经没有更多举报信件了");
        }
        vBox1.setMinSize(700,1000);
        scrollPane1.setContent(vBox1);
        Scene scene1 = new Scene(scrollPane1,800,800);
        stage.setScene(scene1);
        stage.show();
    }
}
