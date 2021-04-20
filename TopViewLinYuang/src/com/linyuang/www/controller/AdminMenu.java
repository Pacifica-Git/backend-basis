package com.linyuang.www.controller;

import com.Main;
import com.linyuang.www.po.Mail;
import com.linyuang.www.po.Melon;
import com.linyuang.www.po.MelonCircle;
import com.linyuang.www.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 管理员界面,当事人很后悔，非常后悔，一开始没有设计一个接口实现管理员界面跟吃瓜群众界面的共同动作
 */
public class AdminMenu {
    //菜单中6个选项
    public static Button showMelon,showCircle,postMelon,showMail,createCircle;
    public static Button circle1,circle2,circle3,circle4,circle5,circle6;
    //流式窗格放置6个选项
    public static FlowPane flowPane1;
    /**
     *@Auther Yuang
     *@Description //TODO 展示主界面,主界面即展示所有瓜
     *@Date
     *@Param [stage]
     *@return void
     **/
    public static void mainMenu(Stage stage){
        showMelon = new Button("所有瓜");
        showMelon.setOnMouseClicked(e-> showMelons(stage));

        showCircle = new Button("所有瓜圈");
        showCircle.setOnMouseClicked(e->{
            circleFace(Main.stage);
        });

        postMelon = new Button("我要发瓜");
        postMelon.setOnMouseClicked(e-> FolksMenu.postMelon(Main.stage));

        showMail = new Button("收件箱");
        showMail.setOnMouseClicked(e->{
            showMail(stage);
        });

        createCircle = new Button("创建瓜圈");
        createCircle.setOnMouseClicked(e->{
            createCircle();
        });

        flowPane1 = new FlowPane();
        flowPane1.getChildren().addAll(showMelon, showCircle, postMelon, showMail);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        showMelons(stage);
    }

    private static void createCircle() {
        Label label = new Label("   瓜圈名 ：  ");
        TextField textField = new TextField();
        textField.setPromptText("请输入瓜圈名：");
        Label empty = new Label("\n\n");
        Button button = new Button("确认");
        button.setOnMouseClicked(e->{
            Service.createCircle(textField.getText());
        });
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label,textField);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,empty,button);
    }

    /**
     *@Auther Yuang
     *@Description //TODO 所有瓜展示界面
     *@Param [stage]
     *@return void
     **/
    public static void showMelons(Stage stage){
        generateMelons(stage, flowPane1);
    }

    public static void generateMelons(Stage stage, FlowPane flowPane1) {
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        ListView<String> listView;
        ArrayList<Melon> melons = Service.getAllMelons();
        if(melons != null){
            listView = getStringListView(melons);
            vBox.setMinSize(700,900);
            vBox.getChildren().addAll(flowPane1,listView);
            scrollPane.setContent(vBox);
        }
        Scene scene3 = new Scene(scrollPane,800,800);
        stage.setScene(scene3);
        stage.show();
    }

    public static ListView<String> getStringListView(ArrayList<Melon> melons) {
        ListView<String> listView;
        List<String> melonList = new ArrayList<>();
        for(Melon melon:melons){
            melonList.add(melon.getTitle());
        }
        ObservableList<String> obArray = FXCollections.observableArrayList(melonList);
        listView = new ListView<>(obArray);
        listView.setMinSize(780,1000);
        listView.setOnMouseClicked(e->{
            int length = listView.getSelectionModel().getSelectedItems().toString().length();
            boolean res = AlertWindow.alertConfirm("查看瓜","是否查看" + listView.getSelectionModel().getSelectedItems().toString().substring(1,length - 1));
            if(res){
                Service.openMelon(listView.getSelectionModel().getSelectedItems().toString().substring(1,length - 1));
            }
        });
        return listView;
    }

    /**
     *@Auther Yuang
     *@Description //TODO 展示所有的瓜圈
     *@Param [stage]
     *@return void
     **/

    public static void circleFace(Stage stage){
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

    public static void showMail(Stage stage){
        VBox vBox1 = new VBox();
        vBox1.getChildren().add(flowPane1);
        flowPane1.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane1 = new ScrollPane();
        ArrayList<Mail> mail1 = Service.adminMail();
        if(mail1 != null){
            for (Mail mail : mail1) {
                TextArea textArea = new TextArea();
                textArea.setMinSize(780, 100);
                textArea.setEditable(false);
                //重写toString方法是展示信件信息
                textArea.setText(mail.toString());
                textArea.setOnMouseClicked(e->{
                    AlertWindow.alertSolve(mail);
                });
                vBox1.getChildren().add(textArea);
            }
            vBox1.setMinSize(700,1000);
            scrollPane1.setContent(vBox1);
            Scene scene1 = new Scene(scrollPane1,800,800);
            stage.setScene(scene1);
            stage.show();
        }
    }
}
