#include<stdio.h>
#include<stdlib.h>
//#include<C:/Users/Lenovo1/Desktop/SqStack.h>

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                       ��˳��ջ������                       *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*                         1.������ջ                         *\n\
*                         2.�˳�����                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                                                            *\n\
*                       ��˳��ջ������                       *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*  1.ѹ����Ԫ��        2.����ջ��Ԫ��      3.���ջ��Ԫ��ֵ  *\n\
*                                                            *\n\
*            4.���ջ�ĳ���      5.��ѯջ�Ƿ�Ϊ��            *\n\
*                                                            *\n\
*                6.���ջ            7.�ݻ�ջ                *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}

Status initStack(SqStack *s,int sizes)
{
	if(sizes <= 0)
	{
		printf("������һ����������ʼ��ջ�Ĵ�С\n");
		return ERROR;
	}
	int *arr = (int *)calloc(10,sizeof(int));
	s->elem = arr;
	s->size = sizes;
	s->top = -1;
	printf("˳��ջ��ʼ���ɹ���\n");
	return SUCCESS;
}

Status isEmptyStack(SqStack *s)
{
	if(s->top < 0)
	{
		printf("ջΪ��!\n");
		return SUCCESS;
	}
	else{printf("ջ��Ϊ�գ�\n");return ERROR;}
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
	printf("ջ����գ�\n");
	return SUCCESS;
}
Status destroyStack(SqStack *s)
{
	s->size = 0;
	s->top = -1;
	free(s->elem);
	s->elem = NULL;
	printf("ջ�Ѵݻ٣�\n");
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
		printf("ջ������\n");
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
	printf("�ѵ���ջ��Ԫ�أ�\n");
	return SUCCESS;
}
