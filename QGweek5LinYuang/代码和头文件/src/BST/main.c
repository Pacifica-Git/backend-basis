#include <stdio.h>
#include <stdlib.h>
#include<time.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

int main()
{
	BinarySortTreePtr tree = (BinarySortTreePtr)malloc(sizeof(BinarySortTree));
	BST_init(tree);
	//isEmpty��������Ƿ���������
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
					printf("������Ҫ���������ֵ��\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_insert(tree,input1))
					{
						printf("�Ѳ��룺%d\n",input1);
						isEmpty = 0;
					}
					else
					{
						printf("%d�Ѵ��ڣ�δ�ܲ���\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 2:
				{
					printf("������Ҫ���������ֵ��\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_insert(tree,input1))
					{
						printf("�Ѳ��룺%d\n",input1);
						isEmpty = 0;
					}
					else
					{
						printf("δ�ܲ���%d\n",input1);
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
					printf("��������Ҫ���ҵ����֣�\n");
					while(scanf("%d",&input1) != 1)
					{
						while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
					}
					system("cls");
					if(BST_search(tree,input1))
					{
						printf("%d���ڣ�\n",input1);
					}
					else
					{
						printf("%d�����ڣ�\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 4:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("����ǰ�������");
					BST_preorderI(tree,visit);
					system("pause");
				}break;
				case 5:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("�������������");
					BST_inorderI(tree,visit);
					system("pause");
				}break;
				case 6:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("�������������");
					BST_postorderI(tree,visit);
					system("pause");
				}break;
				case 7:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("�ݹ�ǰ�������");
					BST_preorderR(tree,visit);
					system("pause");
				}break;
				case 8:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("�ݹ����������");
					BST_inorderR(tree,visit);
					system("pause");
				}break;
				case 9:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("�ݹ���������");
					BST_postorderR(tree,visit);
					system("pause");
				}break;
				case 10:
				{
					if(isEmpty == 1)
					{
						printf("����δ�������֣�\n");
						sleep(1);system("cls");
						break;
					}
					printf("���������");
					BST_levelOrder(tree,visit);
					system("pause");
				}break;
				case 11:
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
					if(BST_delete(tree,input1))
					{
						printf("%d�ѱ�ɾ����\n",input1);
					}
					else
					{
						printf("%d�����ڣ�\n",input1);
					}
					sleep(1);system("cls");
				}break;
				case 12:
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
