#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <C:/Users/Lenovo1/Desktop/AVL.h>
//ȫ�������ܸ���ֵ
int isEmpty;
AVLTree tree;
int main()
{
	tree = NULL;
	//isEmpty��������Ƿ���������
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
					printf("������Ҫ���������ֵ��\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					tree = avltree_insert(tree,input1);
					printf("���� %d �ɹ�\n",input1);
					sleep(1);system("cls");
				}break;
				case 2:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("��������Ҫ���ҵ����֣�\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					Node* t = NULL;
					if((t = avltree_search(tree,input1)) != NULL)
					{
						printf("%d���ڣ�\n",input1);
					}
					else
					{
						printf("%d�����ڣ�\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 3:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("ǰ�������");
					preorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 4:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("���������");
					inorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 5:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("���������");
					postorder_avltree(tree);
					putchar('\n');
					system("pause");system("cls");
				}break;
				case 6:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("��������Ҫɾ�������֣�\n");
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
					printf("��������ȷָ�\n");sleep(1);system("cls");
				}
			}
		}
	}
	system("pause");
    return 0;
}
