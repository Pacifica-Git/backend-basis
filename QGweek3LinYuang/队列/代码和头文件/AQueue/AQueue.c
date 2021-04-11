#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/AQueue.h>

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                      【循环队列操作】                      *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*                         1.创建队列                         *\n\
*                         2.退出程序                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                                                            *\n\
*                      【循环队列操作】                      *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*  1.加入新元素        2.弹出队头元素      3.获得队头元素值  *\n\
*                                                            *\n\
*  4.获得队列的长度    5.查询队列是否为空  6.查询队列是否已满*\n\
*                                                            *\n\
*  7.从队头遍历队列      8.清空队列         9.摧毁队列       *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}

/*typedef struct Aqueue
{
    int *data[MAXQUEUE];      //数据域
    int front;
    int rear;
    size_t length;        //队列长度
} AQueue;

typedef enum
{
    FALSE=0, TRUE=1
} Status;
char type;
char datatype[MAXQUEUE];*/

//length 是定好的数组总长度，不是当前数据存放的个数
//data 是指针数组

/**
 *  @name        : void InitAQueue(AQueue *Q)
 *    @description : 初始化队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void InitAQueue(AQueue *Q)
{
	//初始化时，队列为空时，front与rear都在-1处
	Q->front = -1;
	Q->rear = -1;
	Q->length = MAXQUEUE;
	printf("初始化成功！\n");
}


/**
 *  @name        : void DestoryAQueue(AQueue *Q)
 *    @description : 销毁队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void DestoryAQueue(AQueue *Q)
{
	//如果队列为空
	if(IsEmptyAQueue(Q))
	{
		//free(Q->data);
		free(Q);
		printf("已销毁队列！\n");
		return;
	}
	//让front移动一圈来销毁每个点
	int initial = Q->front;
	do
	{
		free(Q->data + Q->front);
		Q->front = (Q->front + 1) % Q->length;
	}while(Q->front != initial);

	free(Q);
	printf("已销毁队列！\n");
	return;
}



/**
 *  @name        : Status IsFullAQueue(const AQueue *Q)
 *    @description : 检查队列是否已满
 *    @param         Q 队列指针Q
 *    @return         : 满-TRUE; 未满-FALSE
 *  @notice      : None
 */
Status IsFullAQueue(const AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
		return TRUE;
	}
	if((Q->rear + 1) % (Q->length) == Q->front)
	{
		printf("队列已满！\n");
		return TRUE;
	}
	printf("队列未满！\n");
	return FALSE;
}

/**
 *  @name        : Status IsEmptyAQueue(const AQueue *Q)
 *    @description : 检查队列是否为空
 *    @param         Q 队列指针Q
 *    @return         : 空-TRUE; 未空-FALSE
 *  @notice      : None
 */
Status IsEmptyAQueue(const AQueue *Q)
{
	if(Q->front == -1)
	{
		//printf("队列为空！\n");
		return TRUE;
	}
	//printf("队列不为空！\n");
	return FALSE;
}



/**
 *  @name        : Status GetHeadAQueue(AQueue *Q, void *e)
 *    @description : 查看队头元素
 *    @param         Q 队列指针Q，存放元素e
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否空
 */
Status GetHeadAQueue(AQueue *Q, int *e)
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
		return FALSE;
	}
	*e = **(Q->data + Q->front);
	return TRUE;
}


/**
 *  @name        : int LengthAQueue(AQueue *Q)
 *    @description : 确定队列长度
 *    @param         Q 队列指针Q
 *    @return         : 队列长度count
 *  @notice      : None
 */
int LengthAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
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
 *    @description : 入队操作
 *    @param         Q 队列指针Q,入队数据指针data
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否满了或为空
 */
Status EnAQueue(AQueue *Q, int *data)
{
	if(IsEmptyAQueue(Q))
	{
		Q->front = Q->rear = 0;
		//不能直接赋值，要先分配内存
		*(Q->data + Q->front) = *(Q->data + Q->rear) = (int *)malloc(sizeof(int));
		**(Q->data + Q->front) = **(Q->data + Q->rear) = *data;
		printf("已入队！\n");
		return TRUE;
	}
	if(IsFullAQueue(Q))
	{
		printf("无法加入新元素\n");
		return FALSE;
	}
	*(Q->data + ((Q->rear + 1) % Q->length)) = (int*)malloc(sizeof(int));
	**(Q->data + ((Q->rear + 1) % Q->length)) = *data;
	Q->rear = (Q->rear + 1) % Q->length;
	printf("已入队！\n");
	return TRUE;
}



/**
 *  @name        : Status DeAQueue(AQueue *Q)
 *    @description : 出队操作
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
Status DeAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
		return FALSE;
	}
	if(Q->front == Q->rear)
	{
		Q->front = Q->rear = -1;
		printf("队列仅剩一个元素，已出队，当前队列已空!\n");
		return TRUE;
	}
	Q->front = (Q->front + 1) % Q->length;
	printf("已将队头元素出队！\n");
	return TRUE;
}



/**
 *  @name        : void ClearAQueue(Queue *Q)
 *    @description : 清空队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void ClearAQueue(AQueue *Q)
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
		return;
	}
	Q->front = Q->rear =-1;
	printf("已清空队列！\n");
	return;
}



/**
 *  @name        : Status TraverseAQueue(const AQueue *Q, void (*foo)(void *q))
 *    @description : 遍历函数操作
 *    @param         Q 队列指针Q，操作函数指针foo
 *    @return         : None
 *  @notice      : None
 */
Status TraverseAQueue(const AQueue *Q, void (*foo)(int *q))
{
	if(IsEmptyAQueue(Q))
	{
		printf("队列为空！\n");
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
 *    @description : 操作函数
 *    @param         q 指针q
 *  @notice      : None
 */
void APrint(int *q)
{
	printf("%d",*q);
	return;
}
