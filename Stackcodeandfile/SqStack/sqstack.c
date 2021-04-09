#include<stdio.h>
#include<stdlib.h>
//#include<C:/Users/Lenovo1/Desktop/SqStack.h>

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                       【顺序栈操作】                       *\n\
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
*                       【顺序栈操作】                       *\n\
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

Status initStack(SqStack *s,int sizes)
{
	if(sizes <= 0)
	{
		printf("请输入一个正数来初始化栈的大小\n");
		return ERROR;
	}
	int *arr = (int *)calloc(10,sizeof(int));
	s->elem = arr;
	s->size = sizes;
	s->top = -1;
	printf("顺序栈初始化成功！\n");
	return SUCCESS;
}

Status isEmptyStack(SqStack *s)
{
	if(s->top < 0)
	{
		printf("栈为空!\n");
		return SUCCESS;
	}
	else{printf("栈不为空！\n");return ERROR;}
}
Status getTopStack(SqStack *s,ElemType *e)
{
	if(isEmptyStack(s))
	{
		return ERROR;
	}
	*e = *(s->elem + s->top);
	return SUCCESS;
}
Status clearStack(SqStack *s)
{
	if(isEmptyStack(s))
	{
		return ERROR;
	}
	while(s->top >= 0)
	{
		*(s->elem + s->top) = 0;
		s->top--;
	}
	printf("栈已清空！\n");
	return SUCCESS;
}
Status destroyStack(SqStack *s)
{
	s->size = 0;
	s->top = -1;
	free(s->elem);
	s->elem = NULL;
	printf("栈已摧毁！\n");
	return SUCCESS;
}
Status stackLength(SqStack *s,int *length)
{
	if(isEmptyStack(s))
	{
		return ERROR;
	}
	*length = s->top + 1;
	return SUCCESS;
}
Status pushStack(SqStack *s,ElemType data)
{
	if(s->top + 1 == s->size)
	{
		printf("栈已满！\n");
		return ERROR;
	}
	s->top++;
	*(s->elem + s->top) = data;
	return SUCCESS;
}

Status popStack(SqStack *s,ElemType *data)
{
	if(isEmptyStack(s))
	{
		return ERROR;
	}
	*data = *(s->elem + s->top);
	s->top--;
	printf("已弹出栈顶元素！\n");
	return SUCCESS;
}
