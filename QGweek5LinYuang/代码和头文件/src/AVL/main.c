#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <C:/Users/Lenovo1/Desktop/AVL.h>
//全局区不能赋初值
int isEmpty;
AVLTree tree;
int main()
{
	tree = NULL;
	//isEmpty用来标记是否插入过数字
	char c;
	int input1,backtomenu1 = 0;
	isEmpty = 1;
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
					tree = avltree_insert(tree,input1);
					printf("插入 %d 成功\n",input1);
					sleep(1);system("cls");
				}break;
				case 2:
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
					Node* t = NULL;
					if((t = avltree_search(tree,input1)) != NULL)
					{
						printf("%d存在！\n",input1);
					}
					else
					{
						printf("%d不存在！\n",input1);
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
					printf("前序遍历：");
					preorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 4:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("中序遍历：");
					inorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 5:
				{
					if(isEmpty == 1)
					{
						printf("您还未插入数字！\n");
						sleep(1);system("cls");
						break;
					}
					printf("后序遍历：");
					postorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 6:
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
					tree = avltree_delete(tree,input1);
					sleep(1);system("cls");
				}break;
				case 7:
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
