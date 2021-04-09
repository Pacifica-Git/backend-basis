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
		printf("ջΪ��\n");
		return SUCCESS;
	}
	else{printf("ջ��Ϊ��\n");return ERROR;}
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
		printf("�����ջ��\n");
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
		printf("�Ѵݻ�ջ��\n");
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
	//��ջ���ÿ�������
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
		printf("�ѵ���ջ��Ԫ��\n");
		return SUCCESS;
	}
}

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                        ����ջ������                        *\n\
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
*                        ����ջ������                        *\n\
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
