#include <stdio.h>
#include <stdlib.h>
#include<time.h>
void generateData();
void getData();
//用来复制数组
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
	//链栈不用考虑容量
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
		//printf("已弹出栈顶元素\n");
		return SUCCESS;
	//}
}

//快排找第k大的数,每排一次，返回中间坐标
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
//非递归
void quickSortUnRecru(int a[],int left,int right)
{
	//用栈
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


//插入排序
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



//递归快排
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


//计数排序
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

//基数排序中获取第no位上的数字
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
	//b数组存放个位排序后的数,每一个一维数组对应0到9一个数字，c为十位数字的排序；
	//b1为b数组每一个一维数组的下标，对应每一个个位数字为0-9某个数的个数，c1同理
	//举例：b[4][5],即表示原数组个位数为4的数，现在查询到了五个，这个5由b1[4]来维护
	int b[10][n],c[10][n],b1[10] = {0},c1[10] = {0};
	//初始化为0
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
		//100单独处理
		if(a[i] == 100) continue;
		b[getNum(a[i],1)][b1[getNum(a[i],1)]++] = a[i];
	}
	//再把b中数据复制到a，相当于a做了一次个位数上的排序
	int i = 0;
	for(int j = 0;j < 10;j++)
	{
		for(int k = 0;k < b1[j];k++)
		{
			a[i++] = b[j][k];
		}
	}
	//不足n个数的说明是100，都补上
	if(i < n)
	{
		while(i < n)
		{
			a[i++] = 100;
		}
	}
	//再处理十位数
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
*                      【排序算法操作】                      *\n\
*                                                            *\n\
*                           请选择:                          *\n\
*                                                            *\n\
*               1.10000，50000，200000数据下排序时间         *\n\
*                     2.大量小数组下排序顺序                 *\n\
*                         3.退出程序                         *\n\
*                                                            *\n\
**************************************************************\n");
}
void adminmenu2()
{
	int leng = 0,time = 0;clock_t count = 0,start = 0;char c;
	printf("请输入数组大小规模（个）：\n");scanf("%d",&leng);
	while(scanf("%d",leng) != 1)
	{
		while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
	}
	printf("请输入排序次数规模（次）：\n");scanf("%d",&time);
	while(scanf("%d",&time) != 1)
	{
		while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
	}
	int *a = (int *)calloc(leng,sizeof(int));
	for(int i = 0;i < leng;i++)
	{
		//生成随机数组
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
	printf("插入排序：%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		MergeSortDivide(b,0,leng - 1);
		count += clock() - start;
	}
	printf("归并排序：%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		quickSort(b,0,leng - 1);
		count += clock() - start;
	}
	printf("递归快排：%dms\n",count);
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		countSort(b,leng);
		count += clock() - start;
	}
	printf("计数排序：%dms\n",count);
	/*count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		basicNumSort(b,leng);
		count += clock() - start;
	}
	printf("基数排序：%dms\n",count);*/
	count = 0;
	for(int i = 0;i < time;i++)
	{
		strCatArray(b,a,leng);
		start = clock();
		quickSortUnRecru(b,0,leng - 1);
		count += clock() - start;
	}
	printf("非递归快排：%dms\n",count);
}

void Clock1520(int n)
{
	printf("%d数据规模:\n",n);
	int a[n];clock_t start;
	generateData(n);
	getData(a,n);
	int *b = (int *)malloc(n * sizeof(int));

	strCatArray(b,a,n);
	start = clock();
	insertSort(b,n);
	printf("插入排序：%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	MergeSortDivide(b,0,n - 1);
	printf("归并排序：%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	quickSort(b,0,n - 1);
	printf("递归快排：%dms\n",clock() - start);

	strCatArray(b,a,n);
	start = clock();
	countSort(b,n);
	printf("计数排序：%dms\n",clock() - start);

	/*strCatArray(b,a,n);
	start = clock();
	basicNumSort(b,n);
	printf("基数排序：%dms\n",clock() - start);*/

	strCatArray(b,a,n);
	start = clock();
	quickSortUnRecru(b,0,n - 1);
	printf("非递归排序：%dms\n",clock() - start);
}
