#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/binary_sort_tree.h>

void InitLQueue(LQueue *Q)
{
	Q->front = NULL;
	Q->rear = NULL;
	Q->length = 0;
	//printf("已初始化完成！\n");
}
Status IsEmptyLQueue(const LQueue *Q)
{
	if(Q->front == NULL)
	{
		//printf("队列为空！\n");
		return true;
	}
	//printf("队列不为空！\n");
	return false;
}
Status EnLQueue(LQueue *Q, NodePtr nodePtr)
{
	if(IsEmptyLQueue(Q))
	{
		//不能直接对front与rear指向的地址赋值，要先分配内存
		Q->front = Q->rear = (queueNode*)malloc(sizeof(queueNode));
		(Q->front->data) = (Q->rear->data) = nodePtr;
		Q->front->next = Q->rear->next = NULL;
		Q->length += 1;
		//printf("已入队！\n");printf("当前队列不为空！");
		return true;
	}

	Q->rear->next = (queueNode*)malloc(sizeof(queueNode));
	Q->rear->next->data = nodePtr;
	Q->rear->next->next = NULL;
	Q->rear = Q->rear->next;
	//printf("已入队！\n");
	Q->length++;
	return true;
}
Status DeLQueue(LQueue *Q)
{
	//队列为空
	if(IsEmptyLQueue(Q))
	{
		return false;
	}
	//队列只有一个元素,
	if(Q->front == Q->rear)
	{
		queueNode *tem = Q->front;
		Q->front = Q->rear = NULL;
		free(tem);
		Q->length--;
		//printf("已出队！\n");
		return true;
	}
	//队列元素个数大于1
	queueNode *tem = Q->front;
	Q->front = Q->front->next;
	free(tem);
	Q->length--;
	//printf("已出队！\n");
	return true;
}
