#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/AQueue.h>

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                      ��ѭ�����в�����                      *\n\
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
*                      ��ѭ�����в�����                      *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*  1.������Ԫ��        2.������ͷԪ��      3.��ö�ͷԪ��ֵ  *\n\
*                                                            *\n\
*  4.��ö��еĳ���    5.��ѯ�����Ƿ�Ϊ��  6.��ѯ�����Ƿ�����*\n\
*                                                            *\n\
*  7.�Ӷ�ͷ��������      8.��ն���         9.�ݻٶ���       *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}

/*typedef struct Aqueue
{
    int *data[MAXQUEUE];      //������
    int front;
    int rear;
    size_t length;        //���г���
} AQueue;

typedef enum
{
    FALSE=0, TRUE=1
} Status;
char type;
char datatype[MAXQUEUE];*/

//length �Ƕ��õ������ܳ��ȣ����ǵ�ǰ���ݴ�ŵĸ���
//data ��ָ������

/**
 *  @name        : void InitAQueue(AQueue *Q)
 *    @description : ��ʼ������
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void InitAQueue(AQueue *Q)
{
	//��ʼ��ʱ������Ϊ��ʱ��front��rear����-1��
	Q->front = -1;
	Q->rear = -1;
	Q->length = MAXQUEUE;
	printf("��ʼ���ɹ���\n");
}


/**
 *  @name        : void DestoryAQueue(AQueue *Q)
 *    @description : ���ٶ���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void DestoryAQueue(AQueue *Q)
{
	//�������Ϊ��
	if(IsEmptyAQueue(Q))
	{
		//free(Q->data);
		free(Q);
		printf("�����ٶ��У�\n");
		return;
	}
	//��front�ƶ�һȦ������ÿ����
	int initial = Q->front;
	do
	{
		free(Q->data + Q->front);
		Q->front = (Q->front + 1) % Q->length;
	}while(Q->front != initial);

	free(Q);
	printf("�����ٶ��У�\n");
	return;
}



/**
 *  @name        : Status IsFullAQueue(const AQueue *Q)
 *    @description : �������Ƿ�����
 *    @param         Q ����ָ��Q
 *    @return         : ��-TRUE; δ��-FALSE
 *  @notice      : None
 */
Status IsFullAQueue(const AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return TRUE;
	}
	if((Q->rear + 1) % (Q->length) == Q->front)
	{
		printf("����������\n");
		return TRUE;
	}
	printf("����δ����\n");
	return FALSE;
}

/**
 *  @name        : Status IsEmptyAQueue(const AQueue *Q)
 *    @description : �������Ƿ�Ϊ��
 *    @param         Q ����ָ��Q
 *    @return         : ��-TRUE; δ��-FALSE
 *  @notice      : None
 */
Status IsEmptyAQueue(const AQueue *Q)
{
	if(Q->front == -1)
	{
		//printf("����Ϊ�գ�\n");
		return TRUE;
	}
	//printf("���в�Ϊ�գ�\n");
	return FALSE;
}



/**
 *  @name        : Status GetHeadAQueue(AQueue *Q, void *e)
 *    @description : �鿴��ͷԪ��
 *    @param         Q ����ָ��Q�����Ԫ��e
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ��
 */
Status GetHeadAQueue(AQueue *Q, int *e)
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return FALSE;
	}
	*e = **(Q->data + Q->front);
	return TRUE;
}


/**
 *  @name        : int LengthAQueue(AQueue *Q)
 *    @description : ȷ�����г���
 *    @param         Q ����ָ��Q
 *    @return         : ���г���count
 *  @notice      : None
 */
int LengthAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return 0;
	}
	int count = 1;
	int tem = Q->front;
	while(tem != Q->rear)
	{
		count++;
		tem = (tem + 1) % Q->length;
	}
	return count;
}



/**
 *  @name        : Status EnAQueue(AQueue *Q, void *data)
 *    @description : ��Ӳ���
 *    @param         Q ����ָ��Q,�������ָ��data
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : �����Ƿ����˻�Ϊ��
 */
Status EnAQueue(AQueue *Q, int *data)
{
	if(IsEmptyAQueue(Q))
	{
		Q->front = Q->rear = 0;
		//����ֱ�Ӹ�ֵ��Ҫ�ȷ����ڴ�
		*(Q->data + Q->front) = *(Q->data + Q->rear) = (int *)malloc(sizeof(int));
		**(Q->data + Q->front) = **(Q->data + Q->rear) = *data;
		printf("����ӣ�\n");
		return TRUE;
	}
	if(IsFullAQueue(Q))
	{
		printf("�޷�������Ԫ��\n");
		return FALSE;
	}
	*(Q->data + ((Q->rear + 1) % Q->length)) = (int*)malloc(sizeof(int));
	**(Q->data + ((Q->rear + 1) % Q->length)) = *data;
	Q->rear = (Q->rear + 1) % Q->length;
	printf("����ӣ�\n");
	return TRUE;
}



/**
 *  @name        : Status DeAQueue(AQueue *Q)
 *    @description : ���Ӳ���
 *    @param         Q ����ָ��Q
 *    @return         : �ɹ�-TRUE; ʧ��-FALSE
 *  @notice      : None
 */
Status DeAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return FALSE;
	}
	if(Q->front == Q->rear)
	{
		Q->front = Q->rear = -1;
		printf("���н�ʣһ��Ԫ�أ��ѳ��ӣ���ǰ�����ѿ�!\n");
		return TRUE;
	}
	Q->front = (Q->front + 1) % Q->length;
	printf("�ѽ���ͷԪ�س��ӣ�\n");
	return TRUE;
}



/**
 *  @name        : void ClearAQueue(Queue *Q)
 *    @description : ��ն���
 *    @param         Q ����ָ��Q
 *  @notice      : None
 */
void ClearAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return;
	}
	Q->front = Q->rear =-1;
	printf("����ն��У�\n");
	return;
}



/**
 *  @name        : Status TraverseAQueue(const AQueue *Q, void (*foo)(void *q))
 *    @description : ������������
 *    @param         Q ����ָ��Q����������ָ��foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseAQueue(const AQueue *Q, void (*foo)(int *q))
{
	if(IsEmptyAQueue(Q))
	{
		printf("����Ϊ�գ�\n");
		return FALSE;
	}
	int tem = Q->front;
	while(tem != Q->rear)
	{
		(*foo)(*(Q->data + tem));printf("->");
		tem = (tem + 1) % Q->length;
	}
	(*foo)(*(Q->data + tem));putchar('\n');
	return TRUE;
}



/**
 *  @name        : void APrint(void *q)
 *    @description : ��������
 *    @param         q ָ��q
 *  @notice      : None
 */
void APrint(int *q)
{
	printf("%d",*q);
	return;
}
