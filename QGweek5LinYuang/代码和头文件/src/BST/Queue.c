#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

void InitLQueue(LQueue *Q)
{
	Q->front = NULL;
	Q->rear = NULL;
	Q->length = 0;
	//printf("�ѳ�ʼ����ɣ�\n");
}
Status IsEmptyLQueue(const LQueue *Q)
{
	if(Q->front == NULL)
	{
		//printf("����Ϊ�գ�\n");
		return true;
	}
	//printf("���в�Ϊ�գ�\n");
	return false;
}
Status EnLQueue(LQueue *Q, NodePtr nodePtr)
{
	if(IsEmptyLQueue(Q))
	{
		//����ֱ�Ӷ�front��rearָ��ĵ�ַ��ֵ��Ҫ�ȷ����ڴ�
		Q->front = Q->rear = (queueNode*)malloc(sizeof(queueNode));
		(Q->front->data) = (Q->rear->data) = nodePtr;
		Q->front->next = Q->rear->next = NULL;
		Q->length += 1;
		//printf("����ӣ�\n");printf("��ǰ���в�Ϊ�գ�");
		return true;
	}

	Q->rear->next = (queueNode*)malloc(sizeof(queueNode));
	Q->rear->next->data = nodePtr;
	Q->rear->next->next = NULL;
	Q->rear = Q->rear->next;
	//printf("����ӣ�\n");
	Q->length++;
	return true;
}
Status DeLQueue(LQueue *Q)
{
	//����Ϊ��
	if(IsEmptyLQueue(Q))
	{
		return false;
	}
	//����ֻ��һ��Ԫ��,
	if(Q->front == Q->rear)
	{
		queueNode *tem = Q->front;
		Q->front = Q->rear = NULL;
		free(tem);
		Q->length--;
		//printf("�ѳ��ӣ�\n");
		return true;
	}
	//����Ԫ�ظ�������1
	queueNode *tem = Q->front;
	Q->front = Q->front->next;
	free(tem);
	Q->length--;
	//printf("�ѳ��ӣ�\n");
	return true;
}
