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
			//因为再初始化的时候把根节点的值赋值为0，为了不给用户造成麻烦
			//如果用户恰好输入了第一个数字是0，就不再插入，否则把根节点的值替换为
			//用户输入的值
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
				//printf("%d已经存在，无法插入\n",num);
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
					//printf("已插入\n");
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
					//printf("已插入\n");
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
		//printf("已为原空树初始化一个根节点\n");
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
		//如果根节点是要被删的结点
		if(bst->root->value == num)
		{
			//根节点只有一个，返回空树
			if(bst->root->left == NULL && bst->root->right == NULL)
			{
				bst = NULL;return succeed;
			}
			//右节点为空直接接到左节点
			else if(bst->root->left != NULL && bst->root->right == NULL)
			{
				bst->root = bst->root->right;return succeed;
			}
			//左节点为空直接到右节点
			else if(bst->root->right != NULL && bst->root->left == NULL)
			{
				bst->root = bst->root->left;return succeed;
			}
			//左右都不为空找左子树最右节点
			else
			{
				//若左节点没有右节点
				if(bst->root->left->right == NULL)
				{
					bst->root->left->right = bst->root->right;
					bst->root = bst->root->left;
					return succeed;
				}
				//循环找最右结点
				else
				{
					NodePtr t1 = bst->root->left,t2 = bst->root->left->right;
					while(t2->right != NULL)
					{
						t1 = t2;
						t2 = t2->right;
					}
					//将根节点与该最右结点替换后删除，其实直接把根节点左右节点
					//接到该最右结点，再让该最右结点的上一个结点的右节点指向null即可
					t1->right = NULL;
					t2->left = bst->root->left;
					t2->right = bst->root->right;
					bst->root = t2;
					return true;
				}
			}
		}
		//根节点不是要删的点就要向下找，t1用于维护最右结点
		//的上一节点，在最右结点被移到被删结点的时候令t1的右结点指向null
		NodePtr t1 = bst->root,t2;
		//二分思想找t1t2初始位置
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
			//找到t2就是要被删的
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
					//tem来找最右结点，tem1来保存它上一节点
					NodePtr tem = t2->left,tem1 = NULL;
					//若t2的左节点也恰好是t2左子树上最右结点
					if(tem->right == NULL)
					{
						//二分查找的时候不知道t2是往左右哪边去，每次
						//t1t2要改变指向都要先找到t2
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
					//找到最右结点，此时被删结点是t2，将最右结点左右节点与t2左右节点拼接
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
//二分查找元素
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
				//printf("二分查找用时：%dms",clock() - start);
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
		//printf("二分查找用时：%dms",clock() - start);
		return false;
	}
	return false;
}
//层序遍历查找元素
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
				//printf("层序遍历查找用时：%dms",clock() - start);
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
		//printf("层序遍历查找用时：%dms",clock() - start);
		return false;
	}
	return false;
}

//迭代先序遍历
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
//递归先序遍历，参数为树结点
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
//递归先序遍历，参数为树，树不为空传入根节点
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
			/*直接访问队列首元素后弹出，后面会丢失这个结点导致无法判断该结点是否还有子节点
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


