#include <stdio.h>
#include <stdlib.h>
#include<unistd.h>
//#include<C:/Users/Lenovo1/Desktop/LinkStack.h>

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
				LinkStack *node = (LinkStack *)malloc(sizeof(LinkStack));
				if(initLStack(node))
				{
					printf("��ջ��ʼ���ɹ���\n");
				}
				else
				{
					//��ʼ��ʧ��Ҫ����
					printf("��ջ��ʼ��ʧ�ܣ�\n");break;
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
								if(pushLStack(node,input1))
								{
									printf("ѹ��ɹ���\n");
									sleep(1);system("cls");
								}
							}break;
							case 2:
							{
								popLStack(node,&input1);
								sleep(1);system("cls");
							}break;
							case 3:
							{
								if(getTopLStack(node,&input1))
								{
									printf("�ѻ��ջ��Ԫ�أ�Ϊ %d\n",input1);
								}
								sleep(1);system("cls");
							}break;
							case 4:
							{
								if(LStackLength(node,&input1))
								{
									printf("�ѻ��ջ�ĳ���Ϊ %d\n",input1);
								}
								sleep(1);system("cls");
							}break;
							case 5:
							{
								isEmptyLStack(node);
								sleep(1);system("cls");
							}break;
							case 6:
							{
								clearLStack(node);
								sleep(1);system("cls");
							}break;
							case 7:
							{
								destroyLStack(node);backtomenu1 = 1;
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
