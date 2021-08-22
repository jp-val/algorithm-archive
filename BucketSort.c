// JP Valdespino
// August 2021

// BucketSort.c
// ============
// O(m + n), where m is the range of values in the array 
// and n is the length of the gievn array.

#include <stdio.h>
#include <stdlib.h>

#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

int *getRange(int *array, size_t length)
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
	
	range = getRange(array, length);
	bucket = (int*)malloc((range[1] - range[0] + 1) * sizeof(int));

	for (i = 0; i < length; i++)
	{
		bucket[array[i]-range[0]]++;
	}

	j = 0;
	for (int i = 0; i < range[1]-range[0]+1; i++)
	{
		while (bucket[i] > 0)
		{
			array[j++] = i + range[0];
			bucket[i]--;
		}
	}

	free(range);
	free(bucket);
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

	bucketSort(array, length);
	display(array, length);

	return 0;
}