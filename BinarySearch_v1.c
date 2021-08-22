// JP Valdespino
// August 2021

// BinarySearch_v1.c
// ==============
// Recursive implementation.
// Time Complexity: O(log n), where n is the length of the sorted array.

#include <stdio.h>

#define NOT_FOUND -1
#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

int privateBinarySearch(int *array, int key, int low, int high)
{
	int mid;

	if (low > high) return NOT_FOUND;

	mid = low + (high - low) / 2;

	if (array[mid] < key)
		return privateBinarySearch(array, key, mid+1, high);
	else if (array[mid] > key)
		return privateBinarySearch(array, key, low, mid-1);
	else
		return mid;
}

int binarySearch(int *array, int key, size_t length) // Wrapper function.
{
	if (array == NULL || length < 1) return NOT_FOUND;
	return privateBinarySearch(array, key, 0, length-1);
}

int main(int argc, char **argv)
{
	int array[] = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31, 33, 37 };
	size_t length = GETLEN(array);

	printf("%d\n", binarySearch(array, 3, length));
	printf("%d\n", binarySearch(array, 17, length));
	printf("%d\n", binarySearch(array, 33, length));
	printf("%d\n", binarySearch(array, 15, length));

	return 0;
}