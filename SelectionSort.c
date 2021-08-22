// JP Valdespino
// August 2021

// SelectionSort.c
// ===============
// Time Complexity:
// Best and Worst Case Runtime: 
// O(n^2), where n is the number of elemenst in the array.

#include <stdio.h>
#include <stdlib.h>

#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

void swap(int *a, int *b)
{
	int	tmp = *a; *a = *b; *b = tmp;
}

void selectionSort(int *array, size_t length)
{
	int i, j, min;

	if (array == NULL || length < 1) return;

	for (i = 0; i < length-1; i++)
	{
		min = i;
		
		for (j = i+1; j < length; j++)
			if (array[j] < array[min])
				min = j;
		
		swap(&array[i], &array[min]);
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
	size_t length;
	int array[] = { 7, -5, 5, 3, -3, 2, 1, 0, -1, -2, 7, -3, -5, -7, 13, -11 };
	
	length = GETLEN(array);

	selectionSort(array, length);
	displayArray(array, length);

	return 0;
}