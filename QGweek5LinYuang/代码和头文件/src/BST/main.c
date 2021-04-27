#include <stdio.h>
#include <stdlib.h>
#include<time.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

int main()
{
	BinarySortTreePtr tree = (BinarySortTreePtr)malloc(sizeof(BinarySortTree));
	BST_init(tree);
	//isEmpty用来标记是否插入过数字
	char c;int input1,backtomenu1 = 0,isEmpty = 1;
	while(1)
	{
		if(backtomenu1 == 1){break;}
		else if(backtomenu1 == 0)
		{
			adminmenu();
			while(scanf("%d",&input1) != 1)
			{
				while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
			}
			system("cls");
			switch(input1)
			{
				case 1:
				{
					printf("请输入要加入的数字值：\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_insert(tree,input1))
					{
						printf("已插入：%d\n",input1);
						isEmpty = 0;
					}
					else
					{
						printf("%d已存在，未能插入\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 2:
				{
					printf("请输入要加入的数字值：\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_insert(tree,input1))
					{
						printf("已插入：%d\n",input1);
						isEmpty = 0;
					}
					else
					{
						printf("未能插入%d\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 3:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("请输入您要查找的数字：\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_search(tree,input1))
					{
						printf("%d存在！\n",input1);
					}
					else
					{
						printf("%d不存在！\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 4:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("迭代前序遍历：");
					BST_preorderI(tree,visit);
					system("pause");
				}break;
				case 5:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("迭代中序遍历：");
					BST_inorderI(tree,visit);
					system("pause");
				}break;
				case 6:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("迭代后序遍历：");
					BST_postorderI(tree,visit);
					system("pause");
				}break;
				case 7:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("递归前序遍历：");
					BST_preorderR(tree,visit);
					system("pause");
				}break;
				case 8:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("递归中序遍历：");
					BST_inorderR(tree,visit);
					system("pause");
				}break;
				case 9:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("递归后序遍历：");
					BST_postorderR(tree,visit);
					system("pause");
				}break;
				case 10:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("层序遍历：");
					BST_levelOrder(tree,visit);
					system("pause");
				}break;
				case 11:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("请输入您要删除的数字：\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_delete(tree,input1))
					{
						printf("%d已被删除！\n",input1);
					}
					else
					{
						printf("%d不存在！\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 12:
				{
					backtomenu1 = 1;
				}break;
				default:
				{
					printf("请输入正确指令！\n");sleep(1);system("cls");
				}
			}
		}
	}
	system("pause");
    return 0;
}
