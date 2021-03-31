public class LinkedLists{
    public static void insert(LinkedList head,LinkedList q,int n) {
        int count = 0;LinkedList tem = head;
        while(count < n){
            tem = tem.next;
            count+=1;
        }
            q.next = tem.next;
            tem.next = q;
        System.out.println("succeed at insert a new node!");;
    }

    public static void traverse(LinkedList head){
        if(head.next == null){
            System.out.println("@@there is only the head node");
        }
        else{
            LinkedList temp = new LinkedList();
            temp = head.next;
            System.out.print("head ->");
            while(temp.next != null){
                System.out.print(temp.data + " ->");
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }

    public static void reverse(LinkedList head) {
        if(head.next == null){
            System.out.println("~~there is only the head node!");
        }
        else if(head.next.next == null){
            System.out.println("``there is only one valid node!");
        }
        else{
            int flag=0;
            LinkedList p1=head.next,p2=p1.next,p3=p2.next;
            while(p3 != null)
            {
                if(flag ==0)
                {
                    p1.next=null;
                    flag=1;
                }
                p2.next=p1;
                p1=p2;
                p2=p3;
                p3=p3.next;
            }
            if(flag == 0)
            {
                p1.next=null;
                flag=1;
            }
            p2.next=p1;
            head.next = p2;
            System.out.println("succeed at reverse list!");
        }    
    }
    
    public static void reverseEvenList(LinkedList head) {
        if(head.next == null){
            System.out.println("++there is only the head node!");
        }else if(head.next.next == null){
            System.out.println("--there is only one valid node!");
        }else if(head.next.next.next == null){
            LinkedList p1=head.next,p2=p1.next;
            head.next=p2;
            p2.next=p1;
            p1.next=null;
            System.out.println("succeed at reverse even list!");

        }else if(head.next.next.next.next == null){
            LinkedList p1=head.next,p2=p1.next,p3=p2.next;
            head.next=p2;
            p2.next=p1;
            p1.next=p3;
            p3.next=null;
            System.out.println("succeed at reverse even list!");
        }else{
            LinkedList sub = head,p1 = sub.next,p2 = p1.next,p3 = p2.next,p4 = p3.next;//用四个指针，每次按顺序分别指向四个连续结点
            while(true)
            {
                sub.next=p2;p2.next=p1;sub=p1;//每次都把p1，p2与前面的部分连接起来；sub用于记录前面部分的最后一个结点
                if(p4.next == null) //当后面没有结点时，直接对p3，p4一起处理
                {
                    sub.next=p4;
                    p4.next=p3;
                    p3.next=null;
                    System.out.println("succeed at reverse even list!");
                    return;
                }
                if(p4.next.next == null) //当后面只有一个结点时，对p3，p4及p4后的最后一结点处理
                {
                    LinkedList tem=p4.next;
                    sub.next=p4;
                    p4.next=p3;
                    p3.next=tem;
                    tem.next=null;
                    System.out.println("succeed at reverse even list!");
                    return;
                }
                p1=p3;//若后面有至少两个结点，4个指针向后移动两位
                p2=p4;
                p3=p4.next;
                p4=p3.next;
            }
        }
    }
    
    public static void delete(LinkedList head,int n) {
        LinkedList tem = head;int count = 1;
        while(count < n){
            tem = tem.next;
            count++;
        }
        tem.next = tem.next.next;
        System.out.println("succeed at delete a node");
    }
}