#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<time.h>
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

void strCatArray();
void insertSort();
void colorSort();
void quickSort();
int quickSortK();
void selectK();
void countSort();
void MergeSort();
void MergeSortDivide();
int getNum();
void basicNumSort();
void quickSortUnRecru();
Status popLStack(LinkStack *s);
Status pushLStack();
void adminmenu();
void adminmenu2();
void Clock1520();
void generateData();
void getData();
int main()
{
	char c;int input,exitornot = 0;
	while(1)
	{
		if(exitornot == 1) break;
		adminmenu();
		while(scanf("%d",&input) != 1)
		{
			while((c=getchar()) != '\n' && c!=' ' && c!=EOF);
		}
		system("cls");
		switch(input)
		{
			case 1:
			{
				Clock1520(10000);
				Clock1520(40000);
				Clock1520(200000);
				system("pause");system("cls");
			}break;
			case 2:
			{
				adminmenu2();system("pause");system("cls");
			}break;
			case 3:
			{
				system("pause");exitornot  = 1;break;
			}break;
			default:
			{
				printf("请输入正确指令！\n");sleep(1);system("cls");
			}
		}
	}
    return 0;
}
