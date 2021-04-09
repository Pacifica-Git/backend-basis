#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/LinkStack.h>

Status initLStack(LinkStack *s)
{
	s->top = NULL;
	s->count = 0;
	return SUCCESS;
}
Status isEmptyLStack(LinkStack *s)
{
	if(s->top == NULL || s->count == 0)
	{
		printf("栈为空\n");
		return SUCCESS;
	}
	else{printf("栈不为空\n");return ERROR;}
}
Status getTopLStack(LinkStack *s,ElemType *e)
{
	if(isEmptyLStack(s))
	{
		return ERROR;
	}
	else
	{
		*e = s->top->data;
		return SUCCESS;
	}
}
Status clearLStack(LinkStack *s)
{
	if(isEmptyLStack(s))
	{
		return ERROR;
	}
	else
	{
		while(s->top != NULL)
		{
			LinkStackPtr tem = (LinkStackPtr)malloc(sizeof(StackNode));
			tem = s->top;
			s->top = s->top->next;
			free(tem);
		}
		s->count = 0;
		printf("已清空栈！\n");
		return SUCCESS;
	}
}
Status destroyLStack(LinkStack *s)
{
	if(isEmptyLStack(s))
	{
		return ERROR;
	}
	else
	{
		while(s->top != NULL)
		{
			LinkStackPtr tem = (LinkStackPtr)malloc(sizeof(StackNode));
			tem = s->top;
			s->top = s->top->next;
			free(tem);
		}
		free(s);
		printf("已摧毁栈！\n");
		return SUCCESS;
	}
}
Status LStackLength(LinkStack *s,int *length)
{
	if(isEmptyLStack(s))
	{
		return ERROR;
	}
	else
	{
		int count = 0;
		LinkStackPtr tem = (LinkStackPtr)malloc(sizeof(StackNode));
		tem = s->top;
		while(tem)
		{
			count++;
			tem = tem->next;
		}
		*length = count;
		return SUCCESS;
	}
}
Status pushLStack(LinkStack *s,ElemType data)
{
	//链栈不用考虑容量
	LinkStackPtr newnode = (LinkStackPtr)malloc(sizeof(StackNode));
	newnode->data = data;
	newnode->next = s->top;
	s->top = newnode;
	s->count++;
	return SUCCESS;
}
Status popLStack(LinkStack *s,ElemType *data)
{
	if(isEmptyLStack(s))
	{
		return ERROR;
	}
	else
	{
		*data = s->top->data;
		LinkStackPtr tem = (LinkStackPtr)malloc(sizeof(StackNode));
		tem = s->top;
		s->top = s->top->next;
		free(tem);
		s->count--;
		printf("已弹出栈顶元素\n");
		return SUCCESS;
	}
}

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                        【链栈操作】                        *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*                         1.创建链栈                         *\n\
*                         2.退出程序                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                                                            *\n\
*                        【链栈操作】                        *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*  1.压入新元素        2.弹出栈顶元素      3.获得栈顶元素值  *\n\
*                                                            *\n\
*            4.获得栈的长度      5.查询栈是否为空            *\n\
*                                                            *\n\
*                6.清空栈            7.摧毁栈                *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}
