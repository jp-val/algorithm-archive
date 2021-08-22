// JP Valdespino
// August 2021

// HeapSort.c
// ==========
// O(n log n), where n is the number of elements in the array.

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define TRUE 1
#define FALSE 0
#define CAPACITY 420
#define ERROR INT_MIN
#define GETLEN(array)  (sizeof(array) / sizeof((array)[0]))

typedef struct Heap
{
	int *table, isMinHeap, size, capacity;

} Heap;

Heap *createHeap(int isMinHeap)
{
	Heap *new_Heap = (Heap*)malloc(sizeof(Heap));
	if (new_Heap == NULL)
	{
		printf("Error: out of memory.\n");
		return NULL;
	}

	new_Heap->table = (int*)malloc(CAPACITY * sizeof(int));
	new_Heap->size = 0;
	new_Heap->isMinHeap = isMinHeap;
	new_Heap->capacity = CAPACITY;

	return new_Heap;
}

Heap *createMinHeap(size_t capacity)
{
	Heap *minheap = (Heap*)malloc(sizeof(Heap));
	if (minheap == NULL)
	{
		printf("Error: out of memory.\n");
		return NULL;
	}

	minheap->table = (int*)malloc(capacity * sizeof(int));
	minheap->size = 0;
	minheap->isMinHeap = TRUE;
	minheap->capacity = capacity;

	return minheap;
}

int isFull(Heap *h)
{
	return h == NULL || h->table == NULL || h->size >= h->capacity;
}

int isEmpty(Heap *h)
{
	return h == NULL || h->table == NULL || h->size <= 0;
}

void swap(int *a, int *b)
{
	int tmp = *a; *a = *b; *b = tmp;
}

void perculateUp(Heap *h, int child)
{
	int parent, doSwap;

	if (h == NULL || h->table == NULL) return;

	while (TRUE)
	{
		parent = (child - 1) / 2;

		if (h->isMinHeap)
			doSwap = h->table[parent] > h->table[child];
		else
			doSwap = h->table[parent] < h->table[child];

		if (doSwap)
		{
			swap(&h->table[parent], &h->table[child]);
			child = parent;
		}
		else
		{
			break;
		}
	}
}

void perculateDown(Heap *h, int parent)
{
	int child, doSwap;

	if (h == NULL || h->table == NULL) return;

	while (TRUE)
	{
		if (parent*2+1 >= h->size)
			break;
		else if (parent*2+2 >= h->size)
			child = parent * 2 + 1;
		else if (h->isMinHeap && h->table[parent*2+1] < h->table[parent*2+2])
			child = parent * 2 + 1;
		else if (!h->isMinHeap && h->table[parent*2+1] > h->table[parent*2+2])
			child = parent * 2 + 1;
		else
			child = parent * 2 + 2;

		if (h->isMinHeap)
			doSwap = h->table[parent] > h->table[child];
		else
			doSwap = h->table[parent] < h->table[child];

		if (doSwap)
		{
			swap(&h->table[child], &h->table[parent]);
			parent = child;
		}
		else
		{
			break;
		}
	}
}

void add(Heap *h, int data)
{
	if (isFull(h)) return;

	h->table[h->size] = data;
	perculateUp(h, h->size);

	h->size++;
}

int poll(Heap *h)
{
	int retval;

	if (isEmpty(h)) return ERROR;
	
	retval = h->table[0];
	h->table[0] = h->table[h->size-1];

	if (--h->size > 0) perculateDown(h, 0);

	return retval;
}

Heap *destroyHeap(Heap *h)
{
	if (h == NULL) return NULL;

	if (h->table != NULL)
		free(h->table);

	free(h);
	
	return NULL;
}

void heapSort(int *array, size_t length)
{
	int i;
	Heap *minheap;

	if (array == NULL || length < 1) return;

	minheap = createMinHeap(length);
	if (minheap == NULL || minheap->table == NULL) return;

	for (i = 0; i < length; i++)
		add(minheap, array[i]);

	for (i = 0; i < length; i++)
		array[i] = poll(minheap);

	destroyHeap(minheap);
}

void displayHeap(Heap *h)
{
	int i;

	if (h == NULL || h->table == NULL)
	{
		printf("Heap is NULL.\n");
		return;
	}

	printf("%sHeap: %s", h->isMinHeap ? "Min" : "Max", (h->size > 0) ? "" : "<__EMPTY_>\n");

	for (i = 0; i < h->size; i++)
		printf("%d%s", h->table[i], (i < h->size-1) ? ", " : "\n");
}

void displayArray(int *array, size_t length)
{
	int i;

	for (i = 0; i < length; i++)
		printf("%d%s", array[i], (i < length-1) ? ", " : "\n");
}

int main(int argc, char **argv)
{
	int array[] = { 7, -5, 5, 3, -3, 2, 1, 0, -1, -2, 7, -3, -5, -7, 13, -11 };
	size_t length = GETLEN(array);

	displayArray(array, length);
	heapSort(array, length);
	displayArray(array, length);

	return 0;
}