#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/LQueue.h>

/*front��rear��ָ��null���ǿգ�û��ͷ���
��ֻ��һ��Ԫ��ʱfront��rearҲ��ָ��ͬһ��λ��*/

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : ��ʼ������
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void InitLQueue(LQueue *Q){
	Q->front = NULL;
	Q->rear = NULL;
	Q->length = 0;
	printf("�ѳ�ʼ����ɣ�\n");
}

/**
 *  @name        : void DestoryLQueue(LQueue *Q)
 *    @description : ���ٶ���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void DestoryLQueue(LQueue *Q){
	if(IsEmptyLQueue(Q))
	{
		free(Q);
		printf("�Ѵݻٶ��У�\n");
		return;
	}
	Node *tem;
    while(Q->front != Q->rear)
	{
		tem = Q->front;
		Q->front = Q->front->next;
		free(tem);
	}
	free(Q->front);
	printf("�Ѵݻٸ����\n");
	free(Q);printf("�Ѵݻٶ��У�\n");
}

/**
 *  @name        : Status IsEmptyLQueue(const LQueue *Q)
 *    @description : �������Ƿ�Ϊ��
 *    @param         Q ����ָ��Q
 *    @return         : ��-TRUE; δ��-FALSE
 *  @notice      : None
 */
Status IsEmptyLQueue(const LQueue *Q)
{
	if(Q->front == NULL)
	{
		printf("����Ϊ�գ�\n");
		return TRUE;
	}
	printf("���в�Ϊ�գ�\n");
	return FALSE;
}

/**
 *  @name        : Status GetHeadLQueue(LQueue *Q, void *e)
 *    @description : �鿴��ͷԪ��
 *    @param         Q e ����ָ��Q,��������ָ��e
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ��
 */
Status GetHeadLQueue(LQueue *Q, int *e)
{
	if(IsEmptyLQueue(Q))
	{
		return FALSE;
	}
	*e = *((Q->front->data));
	//printf("�ѵõ���ͷԪ�أ�\n");
	return TRUE;
}

/**
 *  @name        : int LengthLQueue(LQueue *Q)
 *    @description : ȷ�����г���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : None
 */
int LengthLQueue(LQueue *Q)
{
	if(IsEmptyLQueue(Q))
	{
		return 0;
	}
	int count = 1;
	Node *tem = Q->front;
	if(Q->front == Q->rear)
	{
		//printf("�ѵõ����г��ȣ�\n");
		return 1;
	}
	while(tem != Q->rear)
	{
		count++;
		tem = tem->next;
	}
	//printf("�ѵõ����г��ȣ�\n");
	return count;
}

/**
 *  @name        : Status EnLQueue(LQueue *Q, void *data)
 *    @description : ��Ӳ���
 *    @param         Q ����ָ��Q,�������ָ��data
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ�Ϊ��
 */
Status EnLQueue(LQueue *Q, int *data)
{
	if(IsEmptyLQueue(Q))
	{
		//����ֱ�Ӷ�front��rearָ��ĵ�ַ��ֵ��Ҫ�ȷ����ڴ�
		Q->front = Q->rear = (Node*)malloc(sizeof(Node));
		*(Q->front->data) = *(Q->rear->data) = *data;
		Q->front->next = Q->rear->next = NULL;
		Q->length = 1;
		printf("����ӣ�\n");printf("��ǰ���в�Ϊ�գ�");
		return TRUE;
	}

	Q->rear->next = (Node*)malloc(sizeof(Node));
	Q->rear->next->data = (int*)malloc(sizeof(int));
	*(Q->rear->next->data) = *data;
	Q->rear->next->next = NULL;
	Q->rear = Q->rear->next;

	printf("����ӣ�\n");
	Q->length++;
	return TRUE;
}

/**
 *  @name        : Status DeLQueue(LQueue *Q)
 *    @description : ���Ӳ���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : None
 */
Status DeLQueue(LQueue *Q)
{
	//����Ϊ��
	if(IsEmptyLQueue(Q))
	{
		return FALSE;
	}
	//����ֻ��һ��Ԫ��
	if(Q->front == Q->rear)
	{
		Node *tem = Q->front;
		Q->front = Q->rear = NULL;
		free(tem);
		Q->length--;
		printf("�ѳ��ӣ�\n");
		return TRUE;
	}
	//����Ԫ�ظ�������0
	Node *tem = Q->front;
	Q->front = Q->front->next;
	free(tem);
	Q->length--;
	printf("�ѳ��ӣ�\n");
	return TRUE;
}

/**
 *  @name        : void ClearLQueue(AQueue *Q)
 *    @description : ��ն���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void ClearLQueue(LQueue *Q)
{
	if(IsEmptyLQueue(Q))
	{
		return;
	}
	Node *tem;
    while(Q->front != Q->rear)
	{
		tem = Q->front;
		Q->front = Q->front->next;
		free(tem);
	}
	free(Q->front);
	Q->front = Q->rear = NULL;
	Q->length = 0;
	printf("����ն���\n");
}

/**
 *  @name        : Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q))
 *    @description : ������������
 *    @param         Q ����ָ��Q����������ָ��foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseLQueue(const LQueue *Q, void (*foo)(int *q))
{
	if(IsEmptyLQueue(Q))
	{
		return FALSE;
	}
	Node *tem = Q->front;
	while(tem != Q->rear)
	{
 		(*foo)(tem->data);
 		tem = tem->next;
	}
	(*foo)(tem->data);
	putchar('\n');
	return TRUE;
}

/**
 *  @name        : void LPrint(void *q)
 *    @description : ��������
 *    @param         q ָ��q

 *  @notice      : None
 */
void LPrint(int *q)
{
	printf("%d ",*q);
}


void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                      ����ʽ���в�����                      *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*                         1.��������                         *\n\
*                         2.�˳�����                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                                                            *\n\
*                      ����ʽ���в�����                      *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*  1.������Ԫ��        2.������ͷԪ��      3.��ö�ͷԪ��ֵ  *\n\
*                                                            *\n\
*          4.��ö��еĳ���       5.��ѯ�����Ƿ�Ϊ��         *\n\
*                                                            *\n\
*  6.�Ӷ�ͷ��������     7.��ն���        8.�ݻٶ���         *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}


