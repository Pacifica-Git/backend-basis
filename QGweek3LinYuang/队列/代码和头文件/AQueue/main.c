#include <stdio.h>
#include <stdlib.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/AQueue.h>

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
				AQueue *team = (AQueue *)malloc(sizeof(AQueue));
				InitAQueue(team);
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
								printf("请输入要加入的元素值：\n");
								while(scanf("%d",&input1) != 1)
								{
									while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
								}
								system("cls");
								EnAQueue(team,&input1);
								sleep(1);system("cls");
							}break;
							case 2:
							{
								DeAQueue(team);
								sleep(1);system("cls");
							}break;
							case 3:
							{
								if(GetHeadAQueue(team,&input1))
								{
									printf("已获得栈顶元素，为 %d\n",input1);
								}
								sleep(1);system("cls");
							}break;
							case 4:
							{
								int num = LengthAQueue(team);
								if(num > 0)
								{
									printf("已获得栈的长度为 %d\n",num);
								}
								sleep(1);system("cls");
							}break;
							case 5:
							{
								if(IsEmptyAQueue(team))
								{
									printf("队列为空！\n");
								}else{
									printf("队列不为空！\n");
								}
								sleep(1);system("cls");
							}break;
							case 6:
							{
								IsFullAQueue(team);
								sleep(1);system("cls");
							}break;
							case 7:
							{
								TraverseAQueue(team,APrint);
								system("pause");system("cls");
							}break;
							case 8:
							{
								ClearAQueue(team);
								sleep(1);system("cls");
							}break;
							case 9:
							{
								DestoryAQueue(team);backtomenu1 = 1;
								sleep(1);system("cls");
							}break;
							default:
							{
								printf("请输入正确指令！\n");sleep(1);system("cls");
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
				printf("请输入正确指令！\n");sleep(1);system("cls");
			}
		}
	}
    return 0;
}
