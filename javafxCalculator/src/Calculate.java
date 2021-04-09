/**
 * @author Lenovo
 */
public class Calculate {
    public static Astack astack = new Astack(null,0);
    public static void midToBack(){
        StackUtils.clearStack(astack);
        for(String i:(Main.back)){
            if(i.equals("(")){
                StackUtils.pushStack(astack,i);
            }else if(i.equals(")")){
                while(StackUtils.getTopStack(astack) != "("){
                    Main.backLine.add(StackUtils.getTopStack(astack));
                    StackUtils.popStack(astack);
                }
                StackUtils.popStack(astack);
            }else if(i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/")){
                if(i.equals("+")){
                    while(StackUtils.getTopStack(astack) != null && StackUtils.getTopStack(astack) != "(" && StackUtils.getTopStack(astack) !=")"){
                        Main.backLine.add(StackUtils.getTopStack(astack));
                        StackUtils.popStack(astack);
                    }
                    StackUtils.pushStack(astack,i);
                }else if(i.equals("-")){
                    while(StackUtils.getTopStack(astack) != null && StackUtils.getTopStack(astack) != "(" && StackUtils.getTopStack(astack) !=")"){
                        Main.backLine.add(StackUtils.getTopStack(astack));
                        StackUtils.popStack(astack);
                    }
                    StackUtils.pushStack(astack,i);
                }else if(i.equals("*")){
                    while(StackUtils.getTopStack(astack) != null && StackUtils.getTopStack(astack) != "(" && StackUtils.getTopStack(astack) !=")" && StackUtils.getTopStack(astack) != "+" && StackUtils.getTopStack(astack) != "-"){
                        Main.backLine.add(StackUtils.getTopStack(astack));
                        StackUtils.popStack(astack);
                    }
                    StackUtils.pushStack(astack,i);
                }else if(i.equals("/")){
                    while(StackUtils.getTopStack(astack) != null && StackUtils.getTopStack(astack) != "(" && StackUtils.getTopStack(astack) !=")" && StackUtils.getTopStack(astack) != "+" && StackUtils.getTopStack(astack) != "-"){
                        Main.backLine.add(StackUtils.getTopStack(astack));
                        StackUtils.popStack(astack);
                    }
                    StackUtils.pushStack(astack,i);
                }
            }else{
                Main.backLine.add(i);
            }
        }
        while(!StackUtils.isEmptyStack(astack)){
            Main.backLine.add(StackUtils.getTopStack(astack));
            StackUtils.popStack(astack);
        }
        for(String i:Main.backLine){
            System.out.println(i);
        }
    }

    public static void backToResult(){
        StackUtils.clearStack(astack);
        for(String i:Main.backLine){
            if(i.equals("+")){
                Main.result = Double.parseDouble(astack.top.data) + Double.parseDouble(astack.top.next.data);
                StackUtils.popStack(astack);StackUtils.popStack(astack);
                StackUtils.pushStack(astack,String.valueOf(Main.result));
            }else if(i.equals("-")){
                Main.result = Double.parseDouble(astack.top.next.data) - Double.parseDouble(astack.top.data);
                StackUtils.popStack(astack);StackUtils.popStack(astack);
                StackUtils.pushStack(astack,String.valueOf(Main.result));
            }else if(i.equals("*")){
                Main.result = Double.parseDouble(astack.top.data) * Double.parseDouble(astack.top.next.data);
                StackUtils.popStack(astack);StackUtils.popStack(astack);
                StackUtils.pushStack(astack,String.valueOf(Main.result));
            }else if(i.equals("/")){
                Main.result = Double.parseDouble(astack.top.next.data) / Double.parseDouble(astack.top.data);
                StackUtils.popStack(astack);StackUtils.popStack(astack);
                StackUtils.pushStack(astack,String.valueOf(Main.result));
            }else{
                StackUtils.pushStack(astack,i);
            }
        }
        if(astack.top.next == null){
            Main.result = Double.parseDouble(astack.top.data);
            StackUtils.popStack(astack);
        }
    }

}
