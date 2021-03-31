import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("#a head node is prepared.please choose:");
        Scanner scan = new Scanner(System.in);
        LinkedList headNode = new LinkedList(0,null);
        int num=0,exitOrNot=0;
        try {
            while(true){
                System.out.println("\n##1.insert a Node");
                System.out.println("##2.traverse the list");
                System.out.println("##3.Reverse the list");
                System.out.println("##4.Reverse even list");
                System.out.println("##5.delete a node");
                System.out.println("##6.exit");
                int choice,inputData;
                choice = scan.nextInt();
                switch(choice){
                    case 1:{
                        while(true){
                            System.out.println("**please choose after which node to insert a new one");
                            System.out.print("--NO.");
                            int which;
                            which = scan.nextInt();
                            if(which > num){
                                System.out.println("&&there is only " + num + " node except the head node! please choose a proper number\n");
                            }
                            else if(which < 0){
                                System.out.println("^^please input a positive number!");
                            }
                            else{
                                System.out.println("%%please input the data of the new node:");
                                inputData = scan.nextInt();
                                LinkedList newNode = new LinkedList(inputData, null);
                                LinkedLists.insert(headNode, newNode, which);
                                num++;
                                break;
                            }
                        }
                    }break;
                    case 2:{
                        LinkedLists.traverse(headNode);
                    }break;
                    case 3:{
                        LinkedLists.reverse(headNode);
                    }break;
                    case 4:{
                        LinkedLists.reverseEvenList(headNode);
                    }break;
                    case 5:{
                        if(headNode.next == null){
                            System.out.println("there is only the head node!");
                        }else{
                            System.out.print("please input which node you would like to delete:\nNO.");
                            inputData = scan.nextInt();
                            if(inputData == 0){
                                System.out.println("please delete a valid node");
                            }
                            else if(inputData < 0){
                                System.out.println("^^please input a positive number!");
                            }
                            else if(inputData > num){
                                System.out.println("there is not enough node");
                            }
                            else{
                                LinkedLists.delete(headNode,inputData);
                            }
                        }
                    }break;
                    case 6:{
                        exitOrNot = 1;
                    }break;
                }
                if(exitOrNot == 1){
                    scan.close();break;
                }
                
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        finally{
            scan.close();
        }
    }
}
