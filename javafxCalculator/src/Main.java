import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.ArrayList;

/**
 * @author Lenovo,implements a calculator
 * 将每个数字以及运算符都当做字符串来处理，这样栈的元素类型也是字符串
 *
 *
 *
 */
public class Main extends Application {

    //存放输入的每个数字和运算符，均以字符串的形式存放
    public static ArrayList<String> back = new ArrayList<>();
    //存放后缀表达式
    public static ArrayList<String> backLine = new ArrayList<>();

    public static double result = 0;
    //存放最后结果
    public static void main(String[] args) {
        /*System.out.println(Integer.parseInt("") + 1);
        System.out.println((int)')');
        System.out.println((int)'+');
        System.out.println((int)'-');
        System.out.println((int)'*');
        System.out.println((int)'a');*/
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Ui.show(primaryStage);
    }

}
