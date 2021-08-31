// JP Valdespino
// August 2021

// BinarySearch_v1.c
// ==============
// Recursive and iterative implementations.
// Time Complexity: O(log n), where n is the length of the sorted array.

#include <stdio.h>
#include <stdlib.h>

#define NOT_FOUND -1
#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

// Recursive version helper.
int private_binary_search(int *array, int key, int low, int high)
{
	int mid;

	if (low > high) return NOT_FOUND;

	mid = low + (high - low) / 2;

	if (array[mid] < key)
		return private_binary_search(array, key, mid+1, high);
	else if (array[mid] > key)
		return private_binary_search(array, key, low, mid-1);
	else
		return mid;
}

// Recursive Version
int binary_search_v1(int *array, int key, size_t length) // Wrapper function.
{
	if (array == NULL || length < 1) return NOT_FOUND;
	return private_binary_search(array, key, 0, length-1);
}

// Iterative Version
int binary_search_v2(int *array, int key, size_t length)
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
	int array[] = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31, 33, 37 };
	size_t length = GET_LENGTH(array);

	printf("%d\n", binary_search_v1(array, 3, length));
	printf("%d\n", binary_search_v1(array, 17, length));
	printf("%d\n", binary_search_v1(array, 33, length));
	printf("%d\n", binary_search_v1(array, 15, length));

	printf("%d\n", binary_search_v2(array, 3, length));
	printf("%d\n", binary_search_v2(array, 17, length));
	printf("%d\n", binary_search_v2(array, 33, length));
	printf("%d\n", binary_search_v2(array, 15, length));

	return 0;
}