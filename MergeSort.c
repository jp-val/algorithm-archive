// JP Valdespino
// August 2021

// MergeSort.c
// ===========
// Time Complexity: O(n log n), where n is the length of the array.

#include <stdio.h>
#include <stdlib.h>

#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

void privateMergeSort(int *array, int low, int high)
{
	int mid = low + (high - low) / 2, i = low, j = mid + 1, k = 0;
	
	int *aux = NULL;
	
	if (low >= high) return;
	
	privateMergeSort(array, low, mid);
	privateMergeSort(array, mid+1, high);
	
	aux = (int*)malloc((high - low + 1) * sizeof(int));
	
	while (i <= mid || j <= high)
	{
		if (i > mid || (j <= high && array[j] < array[i]))
			aux[k++] = array[j++];
		else
			aux[k++] = array[i++];
	}
	
	// Copy everything from the auxiliary array back into the original array.
	for (i = low; i <= high; i++)
		array[i] = aux[i - low];
	
	free(aux);
}

void mergeSort(int *array, size_t length)
{
	if (array == NULL || length < 1) return;
	privateMergeSort(array, 0, length-1);
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

	mergeSort(array, length);
	displayArray(array, length);

	return 0;
}