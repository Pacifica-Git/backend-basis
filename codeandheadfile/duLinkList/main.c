#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/duLinkedList.h>

void Visit(ElemType e);
void begininterface();
void adminmenu();
void adminmenu2();

int main()
{
	char input,c;ElemType temp;int input1,num=-1;/*input等用来接收输入，num记录除头结点外链表结点个数
	                                                      当只有头结点时为0*/
	//begininterface();system("cls");
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
			DuLinkedList head=NULL;InitList_DuL(&head);printf("头结点初始化成功！\n");num=0;sleep(1);system("cls");
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
						DestroyList_DuL(&head);printf("销毁成功！\n");sleep(1);system("cls");num=-1;
					}break;
					case 2:
					{
						if(num==0){printf("只有头结点，无法实现前插");sleep(1);system("cls");}
						else
						{
							DuLinkedList tem=(DuLinkedList)malloc(sizeof(DuLNode));tem->next=NULL,tem->prior=NULL;
							printf("请输入结点data值：\n");
							while(scanf("%d",&temp) != 1)
							{
								while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
							}
							tem->data=temp;
							while(1)
							{
								printf("请输入要插入在第几个结点之前：\n");
								while(scanf("%d",&input1) != 1)
								{
									while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
								}
								if(input1>num) {printf("链表中并没有这么多结点！请重新输入!\n");sleep(1);system("cls");}
								else if(input1==0) {printf("该结点是头结点！请重新输入！\n");sleep(1);system("cls");}
								else
								{
									int count=0;DuLinkedList t=head;
									while(count<input1)
									{
										t=t->next;count++;
									}
									if(InsertBeforeList_DuL(t,tem))
									{printf("前插成功！\n");num++;sleep(1);system("cls");break;}
									else {printf("前插失败！\n");sleep(1);system("cls");break;}
								}
							}
						}
					}break;
					case 3:
					{
						DuLinkedList tem=(DuLinkedList)malloc(sizeof(DuLNode));tem->next=NULL,tem->prior=NULL;
						printf("请输入结点data值：\n");
						while(scanf("%d",&temp) != 1)
						{
							while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
						}
						tem->data=temp;
						while(1)
						{
							printf("请输入要插入在第几个结点之后：\n");
							while(scanf("%d",&input1) != 1)
							{
								while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
							}
							if(input1>num) {printf("链表中并没有这么多结点！请重新输入!\n");sleep(1);system("cls");}
							else
							{
								int count=0;DuLinkedList t=head;
								while(count<input1)
								{
									t=t->next;count++;
								}
								if(InsertAfterList_DuL(t,tem))
								{printf("后插成功！\n");num++;sleep(1);system("cls");break;}
								else {printf("后插失败！\n");sleep(1);system("cls");break;}
							}
						}
					}break;
					case 4:
					{
						if(num==0){printf("当前只有头结点!无法删除");sleep(1);system("cls");break;}
						while(1)
						{
							printf("请输入要删除位于第几个结点后的结点:\n");
							DuLinkedList t=head;
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
								DeleteList_DuL(t,&e);
								printf("删除成功，被删结点值为：%d\n",e);num--;sleep(1);system("cls");break;
							}
						}
					}break;
					case 5:
					{
						if(num==-1 || num==0){printf("无有效结点！无法遍历！\n");sleep(1);system("cls");}
						else{TraverseList_DuL(head,Visit);putchar('\n');sleep(1);system("pause");system("cls");}
					}break;
					case 6:
					{
						DestroyList_DuL(&head);num=-1;system("cls");system("pause");system("cls");
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