#include<stdio.h>
#include<stdlib.h>
//快排找第k大的数,每排一次，返回中间坐标
int quickSortK(int a[],int left,int right)
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
//找第k大的数
void selectK(int a[],int left,int right,int k)
{
	if(left >= right)
	{
		return;
	}
	else
	{
		int num = quickSortK(a,left,right);
		if(num == (k - 1))return;
		else if(num > (k - 1))
		{
			selectK(a,left,num - 1,k);
		}
		else
		{
			selectK(a,num + 1,right,k);
		}
	}
}

//只有0 1 2数组的排序函数
void colorSort(int a[],int n)
{

	int *p0 = &a[0],*p2 = &a[n-1],*p = &a[1];
	while(p != p2)
	{
		//先让p0，p2指向不是0或1的地方
		while(*p0 == 0)
		{
			p0 = p0 + 1;
		}
		while(*p2 == 2)
		{
			p2 = p2 - 1;
		}
		//遇到0或2就交换
		if(*p == 0)
		{
			//位运算提高效率
			*p = (*p) ^ (*p0);
			*p0 = (*p) ^ (*p0);
			*p = (*p) ^ (*p0);
			p0 = p0 + 1;
		}
		else if(*p == 2)
		{
			*p = (*p) ^ (*p2);
			*p2 = (*p) ^ (*p2);
			*p = (*p) ^ (*p2);
			p2 = p2 - 1;
		}
		//交换后再次判断是否要交换
		if(*p == 0)
		{
			*p = (*p) ^ (*p0);
			*p0 = (*p) ^ (*p0);
			*p = (*p) ^ (*p0);
			p0 = p0 + 1;
		}
		else if(*p == 2)
		{
			*p = (*p) ^ (*p2);
			*p2 = (*p) ^ (*p2);
			*p = (*p) ^ (*p2);
			p2 = p2 - 1;
		}
		p = p + 1;
	}
}
