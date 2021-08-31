// JP Valdespino
// August 2021

// LinearSearch.c
// ==============
// Time Complexity: O(n), where n is the length of the array.

#include <stdio.h>

#define NOT_FOUND -1
#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

int linearSearch(int *array, int key, size_t length)
{
	int i;

	if (array == NULL || length < 1) return NOT_FOUND;

	for (i = 0; i < length; i++)
		if (array[i] == key)
			return i;

	return NOT_FOUND;
}

int main(int argc, char **argv)
{
	int array[] = { 7, -5, 5, 3, -3, 2, 1, 0, -1, -2, 7, -3, -5, -7, 13, -11 };
	size_t length = GET_LENGTH(array);

	printf("%d\n", linearSearch(array, 0, length));
	printf("%d\n", linearSearch(array, -11, length));
	printf("%d\n", linearSearch(array, 7, length));
	printf("%d\n", linearSearch(array, 15, length));

	return 0;
}