// JP Valdespino
// August 2021

// BubbleSort.c
// ============
// Time Complexity:
// Worst Case Runtime: O(n^2), where n is the number of elemenst in the array.
// Best Case Runtime: O(n)

#include <stdio.h>
#include <stdlib.h>

#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

void swap(int *a, int *b)
{
	int tmp = *a; *a = *b; *b = tmp;
}

void bubbleSort(int *array, size_t length)
{
	int i, swapped;
	
	if (array == NULL || length < 1) return;

	swapped = 1;

	while (swapped)
	{
		swapped = 0;
		for (i = 0; i < length-1; i++)
		{
			if (array[i] > array[i+1])
			{
				swap(&array[i], &array[i+1]);
				swapped = 1;
			}
		}
	}
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

	bubbleSort(array, length);
	displayArray(array, length);

	return 0;
}