// JP Valdespino
// August 2021

// BucketSort.c
// ============
// Time Complexity: O(m + n), where m is the range of values in the array 
// and n is the length of the gievn array.

#include <stdio.h>
#include <stdlib.h>

#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

int *get_range(int *array, size_t length)
{
	int i, *range = (int*)malloc(2 * sizeof(int));
	
	range[0] = array[0];
	range[1] = array[0];

	for (i = 0; i < length; i++)
	{
		if (array[i] < range[0])
			range[0] = array[i];

		if (array[i] > range[1])
			range[1] = array[i];
	}

	return range;
}

void bucketSort(int *array, size_t length)
{
	int i, j, *range, *bucket;

	if (array == NULL || length < 1) return;
	
	range = get_range(array, length);
	bucket = (int*)malloc((range[1] - range[0] + 1) * sizeof(int));

	for (i = 0; i < length; i++)
		bucket[array[i]-range[0]]++;

	j = 0;
	for (int i = 0; i < range[1]-range[0]+1; i++)
		while (bucket[i] > 0)
		{
			array[j++] = i + range[0];
			bucket[i]--;
		}

	free(range);
	free(bucket);
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

	bucketSort(array, length);
	displayArray(array, length);

	return 0;
}