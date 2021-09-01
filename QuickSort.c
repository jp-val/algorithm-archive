// JP Valdespino
// August 2021

// QuickSort.c
// ===========

#include <stdio.h>
#include <stdlib.h>

#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

void swap(int *a, int *b)
{
	int	tmp = *a; *a = *b; *b = tmp;
}

void privateQuickSort(int *array, int start, int end)
{
	int pivot = start, leftPtr = start + 1, rightPtr = end;

	if (start >= end) return;

	while (leftPtr <= rightPtr)
	{
		if (array[leftPtr] > array[pivot] && array[rightPtr] <= array[pivot])
		{
			swap(&array[leftPtr++], &array[rightPtr--]);
		}
		else
		{
			if (array[leftPtr] <= array[pivot]) leftPtr++;
			if (array[rightPtr] > array[pivot]) rightPtr--;
		}
	}

	swap(&array[pivot], &array[rightPtr]);

	privateQuickSort(array, pivot, rightPtr - 1);
	privateQuickSort(array, rightPtr + 1, end);
}

void quickSort(int *array, size_t length)
{
	if (array == NULL || length < 1) return;
	privateQuickSort(array, 0, length-1);
}

void displayArray(int *array, size_t length)
{
	int i;

	printf("Array: ");

	if (array == NULL || length < 1)
	{
		printf("<__EMPTY__>\n");
		return;
	}
	
	for (i = 0; i < length; i++)
		printf("%d%s", array[i], (i < length-1) ? ", " : "\n");
}

int main(int argc, char **argv)
{
	int array[] = { 7, -5, 5, 3, -3, 2, 1, 0, -1, -2, 7, -3, -5, -7, 13, -11 };
	size_t length = GET_LENGTH(array);

	quickSort(array, length);
	displayArray(array, length);

	return 0;
}