import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.Principal;
import java.util.Arrays;

/**
 * @author Lenovo show the menu
 */
public class Ui {
    public static String temp = "";
    public static String lastString = "";
    public static int countLeft = 0,countRight = 0;
    public static void show(Stage stage){
        Scene scene;
        TextField text1 = new TextField(),text2 = new TextField();
        text1.setEditable(false);

        text1.setAlignment(Pos.TOP_LEFT);
        text2.setEditable(false);

        Button button1 = new Button("1");button1.setMinSize(40,40);
        Button button2 = new Button("2");button2.setMinSize(40,40);
        Button button3 = new Button("3");button3.setMinSize(40,40);
        Button button4 = new Button("4");button4.setMinSize(40,40);
        Button button5 = new Button("5");button5.setMinSize(40,40);
        Button button6 = new Button("6");button6.setMinSize(40,40);
        Button button7 = new Button("7");button7.setMinSize(40,40);
        Button button8 = new Button("8");button8.setMinSize(40,40);
        Button button9 = new Button("9");button9.setMinSize(40,40);
        Button button0 = new Button("0");button0.setMinSize(40,40);
        Button buttonLeft = new Button("(");buttonLeft.setMinSize(40,40);
        Button buttonRight = new Button(")");buttonRight.setMinSize(40,40);
        //Button buttonDel = new Button("del");buttonDel.setMinSize(40,40);
        Button buttonClr = new Button("clr");buttonClr.setMinSize(40,40);
        Button buttonPlus = new Button("+");buttonPlus.setMinSize(40,40);
        Button buttonMinus = new Button("-");buttonMinus.setMinSize(40,40);
        Button buttonMulti = new Button("*");buttonMulti.setMinSize(40,40);
        Button buttonDiv = new Button("/");buttonDiv.setMinSize(40,40);
        Button buttonEqual = new Button("=");buttonEqual.setMinSize(40,40);

        button1.setOnMouseClicked(e->{
            lastString = button1.getText();
            text1.setText(text1.getText() + button1.getText());
            connect(button1.getText()); System.out.println(temp+"-");
        });
        button2.setOnMouseClicked(e->{
            lastString = button2.getText();
            text1.setText(text1.getText() + button2.getText());
            connect(button2.getText()); System.out.println(temp+"-");
        });
        button3.setOnMouseClicked(e->{
            lastString = button3.getText();
            text1.setText(text1.getText() + button3.getText());
            connect(button3.getText()); System.out.println(temp+"-");
        });
        button4.setOnMouseClicked(e->{
            lastString = button4.getText();
            text1.setText(text1.getText() + button4.getText());
            connect(button4.getText()); System.out.println(temp+"-");
        });
        button5.setOnMouseClicked(e->{
            lastString = button5.getText();
            text1.setText(text1.getText() + button5.getText());
            connect(button5.getText()); System.out.println(temp+"-");
        });
        button6.setOnMouseClicked(e->{
            lastString = button6.getText();
            text1.setText(text1.getText() + button6.getText());
            connect(button6.getText()); System.out.println(temp+"-");
        });
        button7.setOnMouseClicked(e->{
            lastString = button7.getText();
            text1.setText(text1.getText() + button7.getText());
            connect(button7.getText()); System.out.println(temp+"-");
        });
        button8.setOnMouseClicked(e->{
            lastString = button8.getText();
            text1.setText(text1.getText() + button8.getText());
            connect(button8.getText()); System.out.println(temp+"-");
        });
        button9.setOnMouseClicked(e->{
            lastString = button9.getText();
            text1.setText(text1.getText() + button9.getText());
            connect(button9.getText()); System.out.println(temp+"-");
        });
        button0.setOnMouseClicked(e->{
            lastString = button0.getText();
            text1.setText(text1.getText() + button0.getText());
            connect(button0.getText()); System.out.println(temp+"-");
        });
        buttonLeft.setOnMouseClicked(e->{
            if(lastString.equals("") == false && (lastString.charAt(0) >= '0' && lastString.charAt(0) <= '9')){
                if((text1.getText().equals("") == false) && ((text1.getText().charAt(0) >= '0' && text1.getText().charAt(0) <= '9') || text1.getText().charAt(0) == '(')){
                    if(equal()){
                        text1.setText(text1.getText() + buttonMulti.getText());
                        if(temp.length() > 0){
                            Main.back.add(temp);
                            temp = "";
                        }
                        if(Main.back.size() != 0){

                            Main.back.add(buttonMulti.getText());
                        }
                        lastString = buttonMulti.getText();
                    }
                }
            }
            countLeft++;
            lastString = buttonLeft.getText();
            text1.setText(text1.getText() + buttonLeft.getText());
            Main.back.add(buttonLeft.getText());
        });
        buttonRight.setOnMouseClicked(e->{
            if(countLeft > countRight){
                countRight++;
                lastString = buttonRight.getText();
                text1.setText(text1.getText() + buttonRight.getText());
                if(temp.length() > 0){
                    Main.back.add(temp);
                    temp = "";
                }
                Main.back.add(buttonRight.getText());
            }

        });
        buttonPlus.setOnMouseClicked(e->{
            if((text1.getText().equals("") == false) && ((text1.getText().charAt(0) >= '0' && text1.getText().charAt(0) <= '9') || text1.getText().charAt(0) == '(')){
                if(equal()){
                    text1.setText(text1.getText() + buttonPlus.getText());
                    if(temp.length() > 0){
                        Main.back.add(temp);
                        temp = "";
                    }
                    if(Main.back.size() != 0){
                        Main.back.add(buttonPlus.getText());
                    }
                    lastString = buttonPlus.getText();
                }
            }

        });
        buttonMinus.setOnMouseClicked(e->{
            if((text1.getText().equals("") == false) && ((text1.getText().charAt(0) >= '0' && text1.getText().charAt(0) <= '9') || text1.getText().charAt(0) == '(')){
                if(equal()){
                    text1.setText(text1.getText() + buttonMinus.getText());
                    if(temp.length() > 0){
                        Main.back.add(temp);
                        temp = "";
                    }
                    if(Main.back.size() != 0){

                        Main.back.add(buttonMinus.getText());
                    }
                    lastString = buttonMinus.getText();
                }
            }

        });
        buttonMulti.setOnMouseClicked(e->{
            if((text1.getText().equals("") == false) && ((text1.getText().charAt(0) >= '0' && text1.getText().charAt(0) <= '9') || text1.getText().charAt(0) == '(')){
                if(equal()){
                    text1.setText(text1.getText() + buttonMulti.getText());
                    if(temp.length() > 0){
                        Main.back.add(temp);
                        temp = "";
                    }
                    if(Main.back.size() != 0){

                        Main.back.add(buttonMulti.getText());
                    }
                    lastString = buttonMulti.getText();
                }
            }

        });
        buttonDiv.setOnMouseClicked(e->{
            if((text1.getText().equals("") == false) && ((text1.getText().charAt(0) >= '0' && text1.getText().charAt(0) <= '9') || text1.getText().charAt(0) == '(')){
                if(equal()){
                    text1.setText(text1.getText() + buttonDiv.getText());
                    if(temp.length() > 0){
                        Main.back.add(temp);
                        temp = "";
                    }
                    if(Main.back.size() != 0){

                        Main.back.add(buttonDiv.getText());
                    }
                    lastString = buttonDiv.getText();
                }
            }

        });
        buttonEqual.setOnMouseClicked(e->{
            if(text1.getText().equals("") == false){
                if(temp.length() > 0){
                    Main.back.add(temp);
                    temp = "";
                }
                if(equal() == false) {
                    Main.back.remove((Main.back.size() - 1));
                }
            /*for(String i:Main.back){
                System.out.println(i);
            }*/
                for(int i = 1;i <= (countLeft - countRight);i++){
                    text1.setText(text1.getText() + buttonRight.getText());
                    Main.back.add(buttonRight.getText());
                }
                Calculate.midToBack();
                Calculate.backToResult();
                text2.setText(String.valueOf(Main.result));
            }

        });
       /* buttonDel.setOnMouseClicked(e->{
            Main.back.remove((Main.back.size() - 1));
            //text1.setText();
        });*/
        buttonClr.setOnMouseClicked(e->{
            lastString = buttonClr.getText();
            temp = "";
            lastString = "";
            Main.back.clear();
            Main.backLine.clear();
            Main.result = 0;
            text1.setText("");
            text2.setText("");
            countRight = 0;countLeft = 0;
        });
        HBox hBox1 = new HBox(),hBox2 = new HBox(),hBox3 = new HBox(),hBox4 = new HBox();
        hBox1.getChildren().addAll(button7,button8,button9,buttonClr);
        hBox2.getChildren().addAll(button4,button5,button6,buttonEqual);
        hBox3.getChildren().addAll(button1,button2,button3,buttonPlus,buttonMinus);
        hBox4.getChildren().addAll(buttonLeft,button0,buttonRight,buttonMulti,buttonDiv);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(text1,text2,hBox1,hBox2,hBox3,hBox4);

        scene = new Scene(vBox1);


        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void connect(String str){
        temp = temp + str;
    }
    //equal方法判断是否运算符
    public static boolean equal(){
        if(lastString.equals("+") || lastString.equals("-") || lastString.equals("*") || lastString.equals("/") || lastString.equals("(")){
            return false;
        }
        return true;
    }

}
