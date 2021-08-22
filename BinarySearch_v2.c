// JP Valdespino
// August 2021

// BinarySearch.c
// ==============
// Iterative implementation.
// O(log n), where n is the length of the sorted array.

#include <stdio.h>

#define NOT_FOUND -1
#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

int binarySearch(int *array, int key, size_t length)
{
	int low = 0, high = length - 1, mid;

	if (array == NULL || length < 1) return NOT_FOUND;

	while (low <= high)
	{
		mid = low + (high - low) / 2;

		if (array[mid] < key)
			low = mid + 1;
		else if (array[mid] > key)
			high = mid - 1;
		else
			return mid;
	}

	return NOT_FOUND;
}

int main(int argc, char **argv)
{
	size_t length;
	int array[] = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31, 33, 37 };

	length = GETLEN(array);

	printf("%d\n", binarySearch(array, 3, length));
	printf("%d\n", binarySearch(array, 17, length));
	printf("%d\n", binarySearch(array, 33, length));
	printf("%d\n", binarySearch(array, 15, length));

	return 0;
}