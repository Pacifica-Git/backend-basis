#include <stdio.h>
#include <stdlib.h>
#include<time.h>
void generateData();
void getData();
//������������
void strCatArray(int b[],int a[],int n)
{
	for(int i = 0;i < n;i++)
	{
		b[i] = a[i];
	}
}
typedef enum Status
{
    ERROR = 0,
	SUCCESS = 1
} Status;
typedef  struct StackNode
{
	int *a;
	int left;
	int right;
	struct StackNode *next;
}StackNode, *LinkStackPtr;
typedef  struct  LinkStack
{
	LinkStackPtr top;
	int	count;
}LinkStack;
Status pushLStack(LinkStack *s,int data[],int left,int right)
{
	//��ջ���ÿ�������
	LinkStackPtr newnode = (LinkStackPtr)malloc(sizeof(StackNode));
	newnode->a = data;
	newnode->left = left;
	newnode->right = right;
	newnode->next = s->top;
	s->top = newnode;
	s->count++;
	return SUCCESS;
}
Status popLStack(LinkStack *s)
{
	//if(isEmptyLStack(s))
	//{
	//	return ERROR;
	//}
	//else
	//{
		//*data = s->top->data;
		LinkStackPtr tem = s->top;
		s->top = s->top->next;
		free(tem);
		s->count--;
		//printf("�ѵ���ջ��Ԫ��\n");
		return SUCCESS;
	//}
}

//�����ҵ�k�����,ÿ��һ�Σ������м�����
int findMid(int a[],int left,int right)
{
	if(left >= right) return 0;
	int i = left,j = right, t = a[left];
	while(i < j)
	{
		while(a[j] >= t && i < j)
		{
			j--;
		}
		while(a[i] <= t && i < j)
		{
			i++;
		}
		if(i < j)
		{
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	a[left] = a[i];
	a[i] = t;
	return i;
}
//�ǵݹ�
void quickSortUnRecru(int a[],int left,int right)
{
	//��ջ
	LinkStack stack;stack.top = NULL;stack.count = 0;
	pushLStack(&stack,a,left,right);
	while(stack.top != NULL)
	{
		int i = findMid(stack.top->a,stack.top->left,stack.top->right);
		popLStack(&stack);
		if(i == 0) continue;
		pushLStack(&stack,a,i + 1,right);
		pushLStack(&stack,a,left,i - 1);
	}
}


//��������
void insertSort(int a[],int n)
{
	int temp,j;
	for(int i = 1;i < n;i++)
	{
		temp = a[i];
		for(j = i;j > 0 && a[j - 1] > temp;j--)
		{
			a[j] = a[j-1];
		}
		a[j] = temp;
	}
}



//�ݹ����
void quickSort(int a[],int left,int right)
{
	if(left >= right) return;
	int i = left,j = right, t = a[left];
	while(i < j)
	{
		while(a[j] >= t && i < j)
		{
			j--;
		}
		while(a[i] <= t && i < j)
		{
			i++;
		}
		if(i < j)
		{
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	a[left] = a[i];
	a[i] = t;
	quickSort(a,left,i - 1);
	quickSort(a,i + 1,right);
}


//��������
void countSort(int a[],int n)
{
	int c[n];
	strCatArray(c,a,n);
	int b[101] = {0};
	for(int i = 0;i < n;i++)
	{
		b[c[i]]++;
	}
	for(int i = 1;i < 101;i++)
	{
		b[i] = b[i] + b[i-1];
	}
	for(int i = n - 1;i >= 0;i--)
	{
		a[--b[c[i]]] = c[i];
	}
}

void MergeSort(int a[], int left, int mid, int right)
{
    int *temp = (int *)calloc(right - left + 1,4);//(int *)malloc((right-left+1) * sizeof(int));
    int i = left;
    int j = mid + 1;
    int k = 0;
    while (i <= mid && j <= right)
    {
        if (a[i] <= a[j])
        {
            temp[k++] = a[i++];
        }
        else
        {
            temp[k++] = a[j++];
        }
    }
    while (i <= mid)
    {
        temp[k++] = a[i++];
    }
    while (j <= right)
    {
        temp[k++] = a[j++];
	}
    for (i = 0; i < k; i++)
    {
        a[left + i] = temp[i];
    }
    free(temp);
}
void MergeSortDivide(int a[],int left,int right)
{
    int mid = left + (right - left) / 2;

    if (left >= right)
    {
        return;
    }

    MergeSortDivide(a, left, mid);
    MergeSortDivide(a, mid + 1, right);

    MergeSort(a, left, mid, right);
}

//���������л�ȡ��noλ�ϵ�����
int getNum(int number,int no)
{
	while(no > 1)
	{
		number /= 10;
		no--;
	}
	return number % 10;
}
void basicNumSort(int a[],int n)
{
	//b�����Ÿ�λ��������,ÿһ��һά�����Ӧ0��9һ�����֣�cΪʮλ���ֵ�����
	//b1Ϊb����ÿһ��һά������±꣬��Ӧÿһ����λ����Ϊ0-9ĳ�����ĸ�����c1ͬ��
	//������b[4][5],����ʾԭ�����λ��Ϊ4���������ڲ�ѯ������������5��b1[4]��ά��
	int b[10][n],c[10][n],b1[10] = {0},c1[10] = {0};
	//��ʼ��Ϊ0
	for(int i = 0;i < 10;i++)
	{
		for(int j = 0;j < n;j++)
		{
			b[i][j] = 0;
		}
	}
	for(int i = 0;i < 10;i++)
	{
		for(int j = 0;j < n;j++)
		{
			c[i][j] = 0;
		}
	}
	for(int i = 0;i < n;i++)
	{
		//100��������
		if(a[i] == 100) continue;
		b[getNum(a[i],1)][b1[getNum(a[i],1)]++] = a[i];
	}
	//�ٰ�b�����ݸ��Ƶ�a���൱��a����һ�θ�λ���ϵ�����
	int i = 0;
	for(int j = 0;j < 10;j++)
	{
		for(int k = 0;k < b1[j];k++)
		{
			a[i++] = b[j][k];
		}
	}
	//����n������˵����100��������
	if(i < n)
	{
		while(i < n)
		{
			a[i++] = 100;
		}
	}
	//�ٴ���ʮλ��
	for(int i = 0;i < n;i++)
	{
		if(a[i] == 100) continue;
		c[getNum(a[i],2)][c1[getNum(a[i],2)]++] = a[i];
	}
	i = 0;
	for(int j = 0;j < 10;j++)
	{
		for(int k = 0;k < c1[j];k++)
		{
			a[i++] = c[j][k];
		}
	}
	if(i < n)
	{
		while(i < n)
		{
			a[i++] = 100;
		}
	}
}

void adminmenu()
{
	printf("**************************************************************\n\
*                                                            *\n\
*                      �������㷨������                      *\n\
*                                                            *\n\
*                           ��ѡ��:                          *\n\
*                                                            *\n\
*               1.10000��50000��200000����������ʱ��         *\n\
*                     2.����С����������˳��                 *\n\
*                         3.�˳�����                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	int leng = 0,time = 0;clock_t count = 0,start = 0;char c;
	printf("�����������С��ģ��������\n");scanf("%d",&leng);
	while(scanf("%d",leng) != 1)
	{
		while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
	}
	printf("���������������ģ���Σ���\n");scanf("%d",&time);
	while(scanf("%d",&time) != 1)
	{
		while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
	}
	int *a = (int *)calloc(leng,sizeof(int));
	for(int i = 0;i < leng;i++)
	{
		//�����������
		a[i] = rand()%101;
	}
	int *b = (int *)calloc(leng,sizeof(int));
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		insertSort(b,leng);
		count += clock() - start;
	}
	printf("��������%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		MergeSortDivide(b,0,leng - 1);
		count += clock() - start;
	}
	printf("�鲢����%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		quickSort(b,0,leng - 1);
		count += clock() - start;
	}
	printf("�ݹ���ţ�%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		countSort(b,leng);
		count += clock() - start;
	}
	printf("��������%dms\n",count);
	/*count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		basicNumSort(b,leng);
		count += clock() - start;
	}
	printf("��������%dms\n",count);*/
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		quickSortUnRecru(b,0,leng - 1);
		count += clock() - start;
	}
	printf("�ǵݹ���ţ�%dms\n",count);
}

void Clock1520(int n)
{
	printf("%d���ݹ�ģ:\n",n);
	int a[n];clock_t start;
	generateData(n);
	getData(a,n);
	int *b = (int *)malloc(n * sizeof(int));

	strCatArray(b,a,n);
	start = clock();
	insertSort(b,n);
	printf("��������%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	MergeSortDivide(b,0,n - 1);
	printf("�鲢����%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	quickSort(b,0,n - 1);
	printf("�ݹ���ţ�%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	countSort(b,n);
	printf("��������%dms\n",clock() - start);

	/*strCatArray(b,a,n);
	start = clock();
	basicNumSort(b,n);
	printf("��������%dms\n",clock() - start);*/

	strCatArray(b,a,n);
	start = clock();
	quickSortUnRecru(b,0,n - 1);
	printf("�ǵݹ�����%dms\n",clock() - start);
}
