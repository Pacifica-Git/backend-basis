#include "../head/linkedList.h"
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<C:/Users/Lenovo1/Desktop/duLinkedList.h>
/**
 *  @name        : Status InitList(LinkList *L);
 *	@description : initialize an empty linked list with only the head node without value
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status InitList(LinkedList *L) {
	return !(*L=((LinkedList)malloc(sizeof(LNode))))?ERROR:((*L)->next=NULL,SUCCESS);
}

/**
 *  @name        : void DestroyList(LinkedList *L)
 *	@description : destroy a linked list, free all the nodes
 *	@param		 : L(the head node)
 *	@return		 : None
 *  @notice      : None
 */
void DestroyList(LinkedList *L) {
	if(!(*L)) return;
	LinkedList p1=*L,p2=p1->next;
	while(p2)
	{
		free(p1);
		p1=p2;
		p2=p2->next;
	}
	free(p1);
	return;
}

/**
 *  @name        : Status InsertList(LNode *p, LNode *q)
 *	@description : insert node q after node p
 *	@param		 : p, q
 *	@return		 : Status
 *  @notice      : None
 */
Status InsertList(LNode *p, LNode *q) {
	q->next=p->next;
	p->next=q;
	return SUCCESS;
}
/**
 *  @name        : Status DeleteList(LNode *p, ElemType *e)
 *	@description : delete the first node after the node p and assign its value to e
 *	@param		 : p, e
 *	@return		 : Status
 *  @notice      : None
 */
Status DeleteList(LNode *p, ElemType *e) {
	*e=p->next->data;
	LinkedList tem=p->next; //使tem指向被删结点，最后free
	p->next=p->next->next;
	free(tem);
	return SUCCESS;
}

/**
 *  @name        : void TraverseList(LinkedList L, void (*visit)(ElemType e))
 *	@description : traverse the linked list and call the funtion visit
 *	@param		 : L(the head node), visit
 *	@return		 : None
 *  @notice      : None
 */
void Visit(ElemType e) //用于遍历时打印结点的data
{
	printf("%d ",e);
}
void TraverseList(LinkedList L, void (*visit)(ElemType e)) {
	if(!(L)) return;
	LinkedList p=L->next;//传入头结点，从下一结点开始遍历
	while(p)
	{
		(*visit)(p->data);
		p=p->next;
	}
}

/**
 *  @name        : Status SearchList(LinkedList L, ElemType e)
 *	@description : find the first node in the linked list according to e
 *	@param		 : L(the head node), e
 *	@return		 : Status
 *  @notice      : None
 */
Status SearchList(LinkedList L, ElemType e) {
	if(!(L)) return ERROR;
	LinkedList p=L;
	while(p)
	{
		if(p->data==e) return SUCCESS;
		p=p->next;
	}
	return ERROR;
}

/**
 *  @name        : Status ReverseList(LinkedList *L)
 *	@description : reverse the linked list
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status ReverseList(LinkedList *L) {  //迭代法反转
	if(!L || !((*L)->next) || !((*L)->next->next)) return SUCCESS;/*空链表或只有头结点或只有一个有效结点，不用反转*/
	int record=0;
	LinkedList p1=(*L)->next,p2=p1->next,p3=p2->next;//带头结点故从头结点下一结点开始翻转
	while(p3)
	{
		if(!record)/*record用于记录p1是否位于第一个有效结点，是就让其指向NULL*/
		{
			p1->next=NULL;
			record++;
		}
		p2->next=p1;
		p1=p2;
		p2=p3;
		p3=p3->next;
	}
	if(!record)
	{
		p1->next=NULL;
		record++;
	}
	p2->next=p1;
	LinkedList head=(LinkedList)malloc(sizeof(LNode));head->data=0;head->next=p2;//新创建一个头结点
	*L=head;
	return SUCCESS;
}
LinkedList ReverseList_Recursion(LinkedList L)  //递归法反转链表,新头结点为headRe
{
	if(!L->next) {headRe->next=L;return L;}//遍历到最后一个结点开始向前反转，headRe是新链表的头结点
	ReverseList_Recursion(L->next)->next=L;return L;/*最后返回的是新链表的最后一个结点，在主函数中将其令其指向NULL才算大功告成*/
}

/**
 *  @name        : Status IsLoopList(LinkedList L)
 *	@description : judge whether the linked list is looped
 *	@param		 : L(the head node)
 *	@return		 : Status
 *  @notice      : None
 */
Status IsLoopList(LinkedList L) {
	if(!L || !(L->next))return ERROR; //空链表或只有头结点肯定不成环
	LinkedList pfast=NULL,pslow=NULL;
	pfast=pslow=L;
	while(pfast)
	{
		pfast=pfast->next;
		if(!pfast) return ERROR;
		pfast=pfast->next;
		if(!pfast) return ERROR;
		pslow=pslow->next;
		if(pfast==pslow)
		{
			return SUCCESS;
		}
	}
	return ERROR;
}

/**
 *  @name        : LNode* ReverseEvenList(LinkedList *L)
 *	@description : reverse the nodes which value is an even number in the linked list, input: 1 -> 2 -> 3 -> 4  output: 2 -> 1 -> 4 -> 3
 *	@param		 : L(the head node)
 *	@return		 : LNode(the new head node)
 *  @notice      : choose to finish
 */
LNode* ReverseEvenList(LinkedList *L) {
	if(!(*L))return NULL; //对空链表或只有头结点或除头结点外只有1，2，3个结点的情况特判
	if(!((*L)->next) || !((*L)->next->next)) return *L;
	if(!((*L)->next->next->next))
	{
		LinkedList p1=(*L)->next,p2=p1->next;
		(*L)->next=p2;
		p2->next=p1;
		p1->next=NULL;
		return (*L);
	}
	if(!((*L)->next->next->next->next))
	{
		LinkedList p1=(*L)->next,p2=p1->next,p3=p2->next;
		(*L)->next=p2;
		p2->next=p1;
		p1->next=p3;
		p3->next=NULL;
		return (*L);
	}
	LinkedList sub=(*L),p1=sub->next,p2=p1->next,p3=p2->next,p4=p3->next;//用四个指针，每次按顺序分别指向四个连续结点
	while(1)
	{
		sub->next=p2;p2->next=p1;sub=p1;//每次都把p1，p2与前面的部分连接起来；sub用于记录前面部分的最后一个结点
		if(!(p4->next)) //当后面没有结点时，直接对p3，p4一起处理
		{
			sub->next=p4;
			p4->next=p3;
			p3->next=NULL;
			return (*L);
		}
		if(!(p4->next->next)) //当后面只有一个结点时，对p3，p4及p4后的最后一结点处理
		{
			LinkedList tem=p4->next;
			sub->next=p4;
			p4->next=p3;
			p3->next=tem;
			tem->next=NULL;
			return (*L);
		}
		p1=p3;//若后面有至少两个结点，4个指针向后移动两位
		p2=p4;
		p3=p4->next;
		p4=p3->next;
	}
}

/**
 *  @name        : LNode* FindMidNode(LinkedList *L)
 *	@description : find the middle node in the linked list
 *	@param		 : L(the head node)
 *	@return		 : LNode
 *  @notice      : choose to finish
 */
LNode* FindMidNode(LinkedList *L) {
		if(!(*L) || !((*L)->next))return NULL;//空链表或只有一个头结点
	if(!((*L)->next->next))return (*L)->next;//除头结点外只有一个结点
	LinkedList pfast=NULL,pslow=NULL;
	pfast=pslow=(*L);
	while(1)
	{
		pfast=pfast->next;
		if(pfast)pfast=pfast->next;
		pslow=pslow->next;
		if(!pfast)return pslow;
	}
}

