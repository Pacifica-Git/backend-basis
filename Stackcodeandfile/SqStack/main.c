#include <stdio.h>
#include <stdlib.h>
#include<unistd.h>
//#include<C:/Users/Lenovo1/Desktop/SqStack.h>
void adminmenu();
void adminmenu2();
int main()
{
	char c;int input,input1,backtomenu1,exitornot = 0;
	while(1)
	{
		if(exitornot == 1) break;
		adminmenu();backtomenu1 = 0;
		while(scanf("%d",&input) != 1)
		{
			while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
		}
		system("cls");
		switch(input)
		{
			case 1:
			{
				SqStack *node = (SqStack *)malloc(sizeof(SqStack));
				printf("������һ��������ʾ����ջ�Ĵ�С��\n");
				while(scanf("%d",&input1) != 1)
				{
					while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
				}
				if(initStack(node,input1))
				{
				}
				else
				{
					printf("˳��ջ��ʼ��ʧ�ܣ�\n");sleep(1);system("cls");break;
				}
				sleep(1);system("cls");
				while(1)
				{
					if(backtomenu1 == 1){break;}
					else if(backtomenu1 == 0)
					{
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
								printf("������Ҫѹ���Ԫ��ֵ��\n");
								while(scanf("%d",&input1) != 1)
								{
									while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
								}
								system("cls");
								if(pushStack(node,input1))
								{
									printf("ѹ��ɹ���\n");
								}
								sleep(1);system("cls");
							}break;
							case 2:
							{
								popStack(node,&input1);
								sleep(1);system("cls");
							}break;
							case 3:
							{
								if(getTopStack(node,&input1))
								{
									printf("�ѻ��ջ��Ԫ�أ�Ϊ %d\n",input1);
								}
								sleep(1);system("cls");
							}break;
							case 4:
							{
								if(stackLength(node,&input1))
								{
									printf("�ѻ��ջ�ĳ���Ϊ %d\n",input1);
								}
								sleep(1);system("cls");
							}break;
							case 5:
							{
								isEmptyStack(node);
								sleep(1);system("cls");
							}break;
							case 6:
							{
								clearStack(node);
								sleep(1);system("cls");
							}break;
							case 7:
							{
								destroyStack(node);backtomenu1 = 1;
								sleep(1);system("cls");
							}break;
							default:
							{
								printf("��������ȷָ�\n");sleep(1);system("cls");
							}
						}
					}
				}
			}break;
			case 2:
			{
				system("pause");exitornot  = 1;break;
			}break;
			default:
			{
				printf("��������ȷָ�\n");sleep(1);system("cls");
			}
		}
	}
    return 0;
}

