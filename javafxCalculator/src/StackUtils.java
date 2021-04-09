import com.sun.org.apache.bcel.internal.generic.DALOAD;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Lenovo
 */
public class StackUtils {
    public static char left = '(',right = ')',equal = '=',plus = '+',minus = '-',multi = '*',div = '/';
    public static boolean isEmptyStack(Astack s){
        if(s.top == null || s.count == 0){
            return true;
        }
        return false;
    }

    public static String getTopStack(Astack s){
        if(isEmptyStack(s)) {
            //System.out.println("the stack is empty");
            return null;
        }else{
            return s.top.data;
        }
    }

    public static boolean pushStack(Astack s,String data){
        StackNode newNode = new StackNode();
        newNode.data = data;
        newNode.next = s.top;
        s.top = newNode;
        s.count++;
        return true;
    }

    public static boolean popStack(Astack s){
        if(isEmptyStack(s)){
            return false;
        }else {
            s.top = s.top.next;
            s.count--;
            return true;
        }
    }

    public static boolean clearStack(Astack s){
        if(isEmptyStack(s)){
            return false;
        }else{
            s.count = 0;
            s.top = null;
            return true;
        }
    }

    public static void turnBack(Astack s,String[] obj, String c){

    }
}
