#include<stdio.h>
#include<stdlib.h>
//nΪҪ���ɵ����ݸ���
void generateData(int n)
{
	FILE *p;
	if((p = fopen("data.dat","w")) == NULL)
	{
		printf("�޷��������ļ�\n");
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
//��ȡ���ݵ�a������
void getData(int a[],int leng)
{
	FILE *p;
	if((p = fopen("data.dat","r")) == NULL)
	{
		printf("�޷��������ļ�\n");
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
