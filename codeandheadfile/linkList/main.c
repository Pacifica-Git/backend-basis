#include<stdio.h>
#include<stdlib.h> //system函数
#include<unistd.h> //sleep函数
#include<C:/Users/Lenovo1/Desktop/linkedList.h>

void Visit(ElemType e);//用于遍历时打印结点的函数声明
LinkedList headRe;//用于递归反转链表时作为新链表的头结点
void adminmenu();
void begininterface();
void adminmenu2();
LinkedList ReverseList_Recursion(LinkedList L);

int main()
{
	char input,c;ElemType temp;int input1,num=-1;/*input等用来接收输入，num记录除头结点外链表结点个数
	                                                      当只有头结点时为0*/
	begininterface();system("cls");
	while(1)
	{
		adminmenu();
		while(scanf("%c",&input) != 1)
		{
			while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
		}
		system("cls");
		if(input== '1')
		{
			system("cls");
			LinkedList head=NULL;InitList(&head);printf("头结点初始化成功！\n");num=0;sleep(1);system("cls");
			while(1)
			{
				if(num == -1) break;
				adminmenu2();
				while(scanf("%d",&input1) != 1)
				{
					while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
				}
				system("cls");
				switch(input1)
				{
					case 1:
					{
						DestroyList(&head);printf("销毁成功！\n");sleep(1);system("cls");num=-1;
					}break;
					case 2:
					{
						LinkedList t=head;//t用于在链表上移动，不用改变head指向
						LinkedList Node=(LinkedList)malloc(sizeof(LNode));Node->next=NULL;
						printf("请输入要插入的结点data值：\n");
						while(scanf("%d",&temp) != 1)
						{
							while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
						}
						Node->data=temp;
						while(1)
						{
							printf("请输入要插入在第几个结点之后：\n");
							while(scanf("%d",&input1) != 1)
							{
								while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
							}
							if(input1>num){printf("链表中并没有这么多结点！请重新输入!\n");sleep(1);system("cls");}
							else
							{
								int count=0;
								while(count<input1)
								{
                                    t=t->next;count++;
								}
								if(InsertList(t,Node)) {num++;printf("插入成功！\n");sleep(1);system("cls");break;}
							}
						}
					}break;
					case 3:
					{
						if(num==0){printf("当前只有头结点!无法删除");sleep(1);system("cls");break;}
						while(1)
						{
							printf("请输入要删除位于第几个结点后的结点:\n");
							LinkedList t=head;
							while(scanf("%d",&input1) != 1)
							{
								while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
							}
							system("cls");
							if(input1>num) {printf("链表中并没有这么多结点！请重新输入!\n");sleep(1);system("cls");}
							else if(input1==num) {printf("该结点已是最后一个结点！请重新输入！\n");sleep(1);system("cls");}
							else
							{
								int count=0;
								while(count<input1)
								{
									t=t->next;count++;
								}
								int e;
								DeleteList(t,&e);
								printf("删除成功，被删结点值为：%d\n",e);num--;sleep(1);system("cls");break;
							}
						}
					}break;
					case 4:
					{
						if(num==-1 || num==0){printf("无有效结点！无法遍历\n");sleep(1);system("cls");}
						else{TraverseList(head,Visit);putchar('\n');sleep(1);system("pause");system("cls");}
					}break;
					case 5:
					{
						if(!num){printf("当前只有头结点，无法查询！\n");sleep(1);system("cls");break;}
						printf("请输入要查询的data值：\n");
						while(scanf("%d",&temp) != 1)
						{
							while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
						}
						if(SearchList(head,temp)) {printf("有该值存在！\n");sleep(1);system("cls");}
						else {printf("无该值存在！\n");sleep(1);system("cls");}
					}break;
					case 6:
					{
						if(num==0){printf("当前只有头结点,无法反转！\n");sleep(1);system("cls");}
						else if(num==1){printf("当前只有一个有效结点,无法反转！\n");sleep(1);system("cls");}
						else if(ReverseList(&head)){printf("迭代法反转成功!\n");sleep(1);system("cls");}
						else {printf("迭代法反转失败！\n");sleep(1);system("cls");}
					}break;
					case 7:
					{
						if(num==0){printf("当前只有头结点,无法反转！\n");sleep(1);system("cls");}
						else if(num==1){printf("当前只有一个有效结点,无法反转！\n");sleep(1);system("cls");}
						else
						{
							headRe=(LinkedList)malloc(sizeof(LNode));headRe->next=NULL;
							(ReverseList_Recursion(head->next))->next=NULL;head=headRe;
							printf("递归反转成功！\n");sleep(1);system("cls");
						}
					}break;
					case 8:
					{
						if(IsLoopList(head)) {printf("链表成环！\n");sleep(1);system("cls");}
						else {printf("链表不成环！\n");sleep(1);system("cls");}
					}break;
					case 9:
					{
						if(num==0){printf("当前只有头结点,无法完成操作！\n");sleep(1);system("cls");}
						else if(num==1){printf("当前只有一个有效结点,无法完成操作！\n");sleep(1);system("cls");}
						else {ReverseEvenList(&head);printf("奇偶顺序打乱成功！\n");sleep(1);system("cls");}
					}break;
					case 10:
					{
						if(!(mid=FindMidNode(&head))) {printf("空链表或或只有头结点，无中间结点!\n");sleep(1);system("cls");}
						else{LinkedList mid=NULL;printf("找到中间结点，其值为：%d\n",mid->data);system("pause");system("cls");}
						free(mid);//结束要释放资源
					}break;
					case 11:
					{
						DestroyList(&head);num=-1;system("cls");system("pause");system("cls");
					}break;
				}
			}
		}
		else if(input == '2')
		{
			system("pause");break;
		}
	}
	system("cls");
	return 0;
}
