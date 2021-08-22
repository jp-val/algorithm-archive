// JP Valdespino
// August 2021

// InsertionSort.c
// ===============
// Time Complexity:
// Worst Case: O(n^2), where n is the number of elemenst in the array.
// Best Case: O(n), where n is the number of elemenst in the array.

#include <stdio.h>
#include <stdlib.h>

#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

void swap(int *a, int *b)
{
	int tmp = *a; *a = *b; *b = tmp;
}

void insertionSort(int *array, size_t length)
{
	int i, j;
	
	if (array == NULL || length < 1) return;

	for (i = 1; i < length; i++)
		for (j = i; j > 0 && array[j] < array[j-1]; j--)
			swap(&array[j], &array[j-1]);
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
	size_t length = GETLEN(array);

	insertionSort(array, length);
	displayArray(array, length);

	return 0;
}