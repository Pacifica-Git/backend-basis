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
		//printf("栈为空\n");
		return true;
	}
	else
	{
		//printf("栈不为空\n");
		return false;
	}
}


Status pushLStack(LinkStack *s,NodePtr data)
{
	//链栈不用考虑容量
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
		//printf("已弹出栈顶元素\n");
		return true;
	}
}
