#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

void visit(NodePtr nodePtr)
{
	printf("%d-",nodePtr->value);
}

Status BST_init(BinarySortTreePtr bst)
{
	bst->root = (NodePtr)malloc(sizeof(Node));
	bst->root->value = 0;
	bst->root->left = NULL;
	bst->root->right = NULL;
	return succeed;
}

Status BST_insert(BinarySortTreePtr bst, ElemType num)
{
	if(bst)
	{
		NodePtr tem = bst->root;
		while(1)
		{
			//��Ϊ�ٳ�ʼ����ʱ��Ѹ��ڵ��ֵ��ֵΪ0��Ϊ�˲����û�����鷳
			//����û�ǡ�������˵�һ��������0���Ͳ��ٲ��룬����Ѹ��ڵ��ֵ�滻Ϊ
			//�û������ֵ
			if(tem->value == 0 && num == 0)
			{
				return succeed;
			}
			else if(tem->value == 0 && num != 0)
			{
				bst->root->value = num;
				return succeed;
			}
			if(tem->value == num)
			{
				//printf("%d�Ѿ����ڣ��޷�����\n",num);
				return failed;
			}
			else if(tem->value > num)
			{
				if(tem->left == NULL)
				{
					tem->left = (NodePtr)malloc(sizeof(Node));
					tem->left->value = num;
					tem->left->left = NULL;
					tem->left->right = NULL;
					//printf("�Ѳ���\n");
					return succeed;
				}
				tem = tem->left;
			}
			else if(tem->value < num)
			{
				if(tem->right == NULL)
				{
					tem->right = (NodePtr)malloc(sizeof(Node));
					tem->right->value = num;
					tem->right->left = NULL;
					tem->right->right = NULL;
					//printf("�Ѳ���\n");
					return succeed;
				}
				tem = tem->right;
			}
		}
	}
	else
	{
		bst = (BinarySortTreePtr)malloc(sizeof(BinarySortTree));
		bst->root = (NodePtr)malloc(sizeof(Node));
		bst->root->value = num;
		bst->root->left = NULL;
		bst->root->right = NULL;
		//printf("��Ϊԭ������ʼ��һ�����ڵ�\n");
		return succeed;
	}
}

Status BST_insert_level(BinarySortTreePtr bst, ElemType num)
{
	if(bst)
	{
		LQueue* lQueue = (LQueue*)malloc(sizeof(LQueue));
		InitLQueue(lQueue);
        EnLQueue(lQueue,bst->root);
        while(!IsEmptyLQueue(lQueue))
		{
			//(*visit)(lQueue->front->data);
			if(lQueue->front->data->left != NULL)
			{
				EnLQueue(lQueue,lQueue->front->data->left);
			}
			else
			{
				lQueue->front->data->left = (NodePtr)malloc(sizeof(Node));
				lQueue->front->data->left->value = num;
				lQueue->front->data->left->left = NULL;
				lQueue->front->data->left->right = NULL;
				break;
			}
			if(lQueue->front->data->right != NULL)
			{
				EnLQueue(lQueue,lQueue->front->data->right);
			}
			else
			{
				lQueue->front->data->right = (NodePtr)malloc(sizeof(Node));
				lQueue->front->data->right->value = num;
				lQueue->front->data->right->left = NULL;
				lQueue->front->data->right->right = NULL;
				break;
			}
			DeLQueue(lQueue);
		}
		return succeed;
	}
	return failed;
}


Status BST_delete(BinarySortTreePtr bst, ElemType num)
{
	if(bst)
	{
		//������ڵ���Ҫ��ɾ�Ľ��
		if(bst->root->value == num)
		{
			//���ڵ�ֻ��һ�������ؿ���
			if(bst->root->left == NULL && bst->root->right == NULL)
			{
				bst = NULL;return succeed;
			}
			//�ҽڵ�Ϊ��ֱ�ӽӵ���ڵ�
			else if(bst->root->left != NULL && bst->root->right == NULL)
			{
				bst->root = bst->root->right;return succeed;
			}
			//��ڵ�Ϊ��ֱ�ӵ��ҽڵ�
			else if(bst->root->right != NULL && bst->root->left == NULL)
			{
				bst->root = bst->root->left;return succeed;
			}
			//���Ҷ���Ϊ�������������ҽڵ�
			else
			{
				//����ڵ�û���ҽڵ�
				if(bst->root->left->right == NULL)
				{
					bst->root->left->right = bst->root->right;
					bst->root = bst->root->left;
					return succeed;
				}
				//ѭ�������ҽ��
				else
				{
					NodePtr t1 = bst->root->left,t2 = bst->root->left->right;
					while(t2->right != NULL)
					{
						t1 = t2;
						t2 = t2->right;
					}
					//�����ڵ�������ҽ���滻��ɾ������ʵֱ�ӰѸ��ڵ����ҽڵ�
					//�ӵ������ҽ�㣬���ø����ҽ�����һ�������ҽڵ�ָ��null����
					t1->right = NULL;
					t2->left = bst->root->left;
					t2->right = bst->root->right;
					bst->root = t2;
					return true;
				}
			}
		}
		//���ڵ㲻��Ҫɾ�ĵ��Ҫ�����ң�t1����ά�����ҽ��
		//����һ�ڵ㣬�����ҽ�㱻�Ƶ���ɾ����ʱ����t1���ҽ��ָ��null
		NodePtr t1 = bst->root,t2;
		//����˼����t1t2��ʼλ��
		if(t1->value > num)
		{
			t2 = t1->left;
		}
		else if(t1->value < num)
		{
			t2 = t1->right;
		}
		while(t2 != NULL)
		{
			//�ҵ�t2����Ҫ��ɾ��
			if(t2->value == num)
			{
				if(t2->left == NULL && t2->right == NULL)
				{
					if(t1->left == t2)
					{
						t1->left = NULL;
					}
					else
					{
						t1->right = NULL;
					}
					return succeed;
				}
				else if(t2->left != NULL && t2->right == NULL)
				{
					if(t1->left == t2)
					{
						t1->left = t2->left;
					}
					else
					{
						t1->right = t2->left;
					}
					return succeed;
				}
				else if(t2->right != NULL && t2->left == NULL)
				{
					if(t1->left == t2)
					{
						t1->left = t2->right;
					}
					else
					{
						t1->right = t2->right;
					}
					return succeed;
				}
				else
				{
					//tem�������ҽ�㣬tem1����������һ�ڵ�
					NodePtr tem = t2->left,tem1 = NULL;
					//��t2����ڵ�Ҳǡ����t2�����������ҽ��
					if(tem->right == NULL)
					{
						//���ֲ��ҵ�ʱ��֪��t2���������ı�ȥ��ÿ��
						//t1t2Ҫ�ı�ָ��Ҫ���ҵ�t2
						if(t1->left == t2)
						{
							t1->left = t2->left;
						}
						else
						{
							t1->right = t2->left;
						}
						return succeed;
					}
					while(tem->right != NULL)
					{
						tem1 = tem;
						tem = tem->right;
					}
					//�ҵ����ҽ�㣬��ʱ��ɾ�����t2�������ҽ�����ҽڵ���t2���ҽڵ�ƴ��
					tem1->right = NULL;
					tem->right = t2->right;
					tem->left = t2->left;
					if(t1->left == t2)
					{
						t1->left = tem;
					}
					else
					{
						t1->right = tem;
					}
					return succeed;
				}
			}
			else if(t2->value > num)
			{
				t1 = t2;
				t2 = t2->left;
			}
			else if(t2->value < num)
			{
				t1 = t2;
				t2 = t2->right;
			}
		}
	}
	return false;
}
//���ֲ���Ԫ��
Status BST_search(BinarySortTreePtr bst, ElemType num)
{
	if(bst)
	{
		//clock_t start = clock();
		NodePtr tem = bst->root;
		while(tem != NULL)
		{
			if(tem->value == num)
			{
				//printf("���ֲ�����ʱ��%dms",clock() - start);
				return true;
			}
			else if(tem->value > num)
			{
				tem = tem->left;
			}
			else if(tem->value < num)
			{
				tem = tem->right;
			}
		}
		//printf("���ֲ�����ʱ��%dms",clock() - start);
		return false;
	}
	return false;
}
//�����������Ԫ��
Status BST_search_level(BinarySortTreePtr bst, ElemType num)
{
	if(bst)
	{
		LQueue* lQueue = (LQueue*)malloc(sizeof(LQueue));
		InitLQueue(lQueue);
        EnLQueue(lQueue,bst->root);
        //clock_t start = clock();
        while(!IsEmptyLQueue(lQueue))
		{
			//(*visit)(lQueue->front->data);
			if(lQueue->front->data->value == num)
			{
				//printf("�������������ʱ��%dms",clock() - start);
				return true;
			}
			if(lQueue->front->data->left != NULL)
			{
				EnLQueue(lQueue,lQueue->front->data->left);
			}
			if(lQueue->front->data->right != NULL)
			{
				EnLQueue(lQueue,lQueue->front->data->right);
			}
			DeLQueue(lQueue);
		}
		//printf("�������������ʱ��%dms",clock() - start);
		return false;
	}
	return false;
}

//�����������
Status BST_preorderI(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		LinkStack* lStack = (LinkStack*)malloc(sizeof(LinkStack));
		initLStack(lStack);
		pushLStack(lStack,bst->root);
        while(!isEmptyLStack(lStack))
		{
			NodePtr now = lStack->top->data;
			(*visit)(lStack->top->data);
			popLStack(lStack);
			if(now->right != NULL)
			{
				pushLStack(lStack,now->right);
			}
			if(now->left != NULL)
			{
				pushLStack(lStack,now->left);
			}
		}
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
//�ݹ��������������Ϊ�����
Status BST_preorderR_R(NodePtr nodePtr,void(*visit)(NodePtr))
{
	if(nodePtr)
	{
		(*visit)(nodePtr);
		BST_preorderR_R(nodePtr->left,visit);
		BST_preorderR_R(nodePtr->right,visit);
		return succeed;
	}
	else
	{
		return failed;
	}
}
//�ݹ��������������Ϊ��������Ϊ�մ�����ڵ�
Status BST_preorderR(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		BST_preorderR_R(bst->root,visit);
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_inorderI(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		NodePtr t = bst->root;
		LinkStack* lStack = (LinkStack*)malloc(sizeof(LinkStack));
		initLStack(lStack);
		while(t != NULL || !isEmptyLStack(lStack))
		{
			while(t != NULL)
			{
				pushLStack(lStack,t);
				t = t->left;
			}
			if(!isEmptyLStack(lStack))
			{
				t = lStack->top->data;
				popLStack(lStack);
				(*visit)(t);
				t = t->right;
			}
		}
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_inorderR_R(NodePtr nodePtr,void(*visit)(NodePtr))
{
	if(nodePtr)
	{
		BST_inorderR_R(nodePtr->left,visit);
		(*visit)(nodePtr);
		BST_inorderR_R(nodePtr->right,visit);
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_inorderR(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		BST_inorderR_R(bst->root,visit);
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_postorderI(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		NodePtr t = bst->root,tem = NULL;
		LinkStack* lStack = (LinkStack*)malloc(sizeof(LinkStack));
		initLStack(lStack);
		while(t != NULL || !isEmptyLStack(lStack))
		{
			if(t)
			{
				pushLStack(lStack,t);
				t= t->left;
			}
			else
			{
				t = lStack->top->data;
				if(t->right && t->right != tem)
					t= t->right;
				else
				{
					popLStack(lStack);
					(*visit)(t);
					tem = t;
					t = NULL;
				}
			}
		}
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_postorderR_R(NodePtr nodePtr,void(*visit)(NodePtr))
{
	if(nodePtr)
	{
		BST_postorderR_R(nodePtr->left,visit);
		BST_postorderR_R(nodePtr->right,visit);
		(*visit)(nodePtr);
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_postorderR(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		BST_postorderR_R(bst->root,visit);
		putchar('\n');
		return succeed;
	}
	else
	{
		return failed;
	}
}
Status BST_levelOrder(BinarySortTreePtr bst, void (*visit)(NodePtr))
{
	if(bst)
	{
		LQueue* lQueue = (LQueue*)malloc(sizeof(LQueue));
		InitLQueue(lQueue);
        EnLQueue(lQueue,bst->root);
        while(!IsEmptyLQueue(lQueue))
		{
			/*ֱ�ӷ��ʶ�����Ԫ�غ󵯳�������ᶪʧ�����㵼���޷��жϸý���Ƿ����ӽڵ�
			(*visit)(lQueue->front->data);
			DeLQueue(lQueue);
			if(lQueue->front->data->left != NULL)
			{
				printf(",\n");
				EnLQueue(lQueue,lQueue->front->data->left);
				printf(",\n");
			}
			if(lQueue->front->data->right != NULL)
			{
				EnLQueue(lQueue,lQueue->front->data->right);
			}*/
			NodePtr now = lQueue->front->data;
			(*visit)(lQueue->front->data);
			DeLQueue(lQueue);
			if(now->left != NULL)
			{
				EnLQueue(lQueue,now->left);
			}
			if(now->right != NULL)
			{
				EnLQueue(lQueue,now->right);
			}
		}
		putchar('\n');
		return succeed;
	}
	return failed;
}


