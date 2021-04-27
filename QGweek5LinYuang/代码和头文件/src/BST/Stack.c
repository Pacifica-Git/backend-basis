#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

Status initLStack(LinkStack *s)
{
	s->top = NULL;
	s->count = 0;
	return true;
}

Status isEmptyLStack(LinkStack *s)
{
	if(s->top == NULL || s->count == 0)
	{
		//printf("ջΪ��\n");
		return true;
	}
	else
	{
		//printf("ջ��Ϊ��\n");
		return false;
	}
}


Status pushLStack(LinkStack *s,NodePtr data)
{
	//��ջ���ÿ�������
	LinkStackPtr newnode = (LinkStackPtr)malloc(sizeof(stackNode));
	newnode->data = data;
	newnode->next = s->top;
	s->top = newnode;
	s->count++;
	return true;
}
Status popLStack(LinkStack *s)
{
	if(isEmptyLStack(s))
	{
		return false;
	}
	else
	{
		LinkStackPtr tem = (LinkStackPtr)malloc(sizeof(stackNode));
		tem = s->top;
		s->top = s->top->next;
		free(tem);
		s->count--;
		//printf("�ѵ���ջ��Ԫ��\n");
		return true;
	}
}
