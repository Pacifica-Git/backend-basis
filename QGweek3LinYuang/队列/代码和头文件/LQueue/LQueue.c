#include<stdio.h>
#include<stdlib.h>
#include<C:/Users/Lenovo1/Desktop/LQueue.h>

/*front跟rear都指向null才是空，没有头结点
当只有一个元素时front跟rear也是指向同一个位置*/

/**
 *  @name        : void InitLQueue(LQueue *Q)
 *    @description : 初始化队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void InitLQueue(LQueue *Q){
	Q->front = NULL;
	Q->rear = NULL;
	Q->length = 0;
	printf("已初始化完成！\n");
}

/**
 *  @name        : void DestoryLQueue(LQueue *Q)
 *    @description : 销毁队列
 *    @param         Q 队列指针Q
 *  @notice      : None
 */
void DestoryLQueue(LQueue *Q){
	if(IsEmptyLQueue(Q))
	{
		free(Q);
		printf("已摧毁队列！\n");
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
	printf("已摧毁各结点\n");
	free(Q);printf("已摧毁队列！\n");
}

/**
 *  @name        : Status IsEmptyLQueue(const LQueue *Q)
 *    @description : 检查队列是否为空
 *    @param         Q 队列指针Q
 *    @return         : 空-TRUE; 未空-FALSE
 *  @notice      : None
 */
Status IsEmptyLQueue(const LQueue *Q)
{
	if(Q->front == NULL)
	{
		printf("队列为空！\n");
		return TRUE;
	}
	printf("队列不为空！\n");
	return FALSE;
}

/**
 *  @name        : Status GetHeadLQueue(LQueue *Q, void *e)
 *    @description : 查看队头元素
 *    @param         Q e 队列指针Q,返回数据指针e
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否空
 */
Status GetHeadLQueue(LQueue *Q, int *e)
{
	if(IsEmptyLQueue(Q))
	{
		return FALSE;
	}
	*e = *((Q->front->data));
	//printf("已得到队头元素！\n");
	return TRUE;
}

/**
 *  @name        : int LengthLQueue(LQueue *Q)
 *    @description : 确定队列长度
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
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
		//printf("已得到队列长度！\n");
		return 1;
	}
	while(tem != Q->rear)
	{
		count++;
		tem = tem->next;
	}
	//printf("已得到队列长度！\n");
	return count;
}

/**
 *  @name        : Status EnLQueue(LQueue *Q, void *data)
 *    @description : 入队操作
 *    @param         Q 队列指针Q,入队数据指针data
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : 队列是否为空
 */
Status EnLQueue(LQueue *Q, int *data)
{
	if(IsEmptyLQueue(Q))
	{
		//不能直接对front与rear指向的地址赋值，要先分配内存
		Q->front = Q->rear = (Node*)malloc(sizeof(Node));
		*(Q->front->data) = *(Q->rear->data) = *data;
		Q->front->next = Q->rear->next = NULL;
		Q->length = 1;
		printf("已入队！\n");printf("当前队列不为空！");
		return TRUE;
	}

	Q->rear->next = (Node*)malloc(sizeof(Node));
	Q->rear->next->data = (int*)malloc(sizeof(int));
	*(Q->rear->next->data) = *data;
	Q->rear->next->next = NULL;
	Q->rear = Q->rear->next;

	printf("已入队！\n");
	Q->length++;
	return TRUE;
}

/**
 *  @name        : Status DeLQueue(LQueue *Q)
 *    @description : 出队操作
 *    @param         Q 队列指针Q
 *    @return         : 成功-TRUE; 失败-FALSE
 *  @notice      : None
 */
Status DeLQueue(LQueue *Q)
{
	//队列为空
	if(IsEmptyLQueue(Q))
	{
		return FALSE;
	}
	//队列只有一个元素
	if(Q->front == Q->rear)
	{
		Node *tem = Q->front;
		Q->front = Q->rear = NULL;
		free(tem);
		Q->length--;
		printf("已出队！\n");
		return TRUE;
	}
	//队列元素个数大于0
	Node *tem = Q->front;
	Q->front = Q->front->next;
	free(tem);
	Q->length--;
	printf("已出队！\n");
	return TRUE;
}

/**
 *  @name        : void ClearLQueue(AQueue *Q)
 *    @description : 清空队列
 *    @param         Q 队列指针Q
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
	printf("已清空队列\n");
}

/**
 *  @name        : Status TraverseLQueue(const LQueue *Q, void (*foo)(void *q))
 *    @description : 遍历函数操作
 *    @param         Q 队列指针Q，操作函数指针foo
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
 *    @description : 操作函数
 *    @param         q 指针q

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
*                      【链式队列操作】                      *\n\
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
*                      【链式队列操作】                      *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*  1.加入新元素        2.弹出队头元素      3.获得队头元素值  *\n\
*                                                            *\n\
*          4.获得队列的长度       5.查询队列是否为空         *\n\
*                                                            *\n\
*  6.从队头遍历队列     7.清空队列        8.摧毁队列         *\n\
*                                                            *\n\
*                                                            *\n\
**************************************************************\n");
}


