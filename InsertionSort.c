// JP Valdespino
// August 2021

// InsertionSort.c
// ===============
// Worst Case Runtime: O(n^2), where n is the number of elemenst in the array.
// Best Case Runtime: O(n), where n is the number of elemenst in the array.

#include <stdio.h>
#include <stdlib.h>

#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

void swap(int *a, int *b)
{
	int tmp;

	tmp = *a;
	*a = *b;
	*b = tmp;
}

void insertionSort(int *array, size_t length)
{
	int i, j;
	
	if (array == NULL || length < 1) return;

	for (i = 1; i < length; i++)
		for (j = i; j > 0 && array[j] < array[j-1]; j--)
			swap(&array[j], &array[j-1]);
}

void display(int *array, size_t length)
{
	int i;

	for (i = 0; i < length; i++)
		printf("%d%s", array[i], (i < length-1) ? ", " : "\n");
}

int main(int argc, char **argv)
{
	size_t length;
	int array[] = { 7, -5, 5, 3, -3, 2, 1, 0, -1, -2, 7, -3, -5, -7, 13, -11 };
	
	length = GETLEN(array);

	insertionSort(array, length);
	display(array, length);

	return 0;
}