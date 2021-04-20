#include<stdio.h>
#include<stdlib.h>
//n为要生成的数据个数
void generateData(int n)
{
	FILE *p;
	if((p = fopen("data.dat","w")) == NULL)
	{
		printf("无法打开数据文件\n");
		fclose(p);
		return;
	}
	else
	{
		for(int i = 0;i < n;i++)
		{
			fprintf(p,"%d%c",(rand() % 101),'-');
		}
		fclose(p);
	}
}
//获取数据到a数组中
void getData(int a[],int leng)
{
	FILE *p;
	if((p = fopen("data.dat","r")) == NULL)
	{
		printf("无法打开数据文件\n");
		fclose(p);
		return;
	}
	else
	{
		char c;
		for(int i = 0;i < leng;i++)
		{
			fscanf(p,"%d%c",&a[i],&c);
		}
		fclose(p);
	}
}
