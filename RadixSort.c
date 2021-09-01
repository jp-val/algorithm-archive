// JP Valdespino
// August 2021

// RadixSort.c
// ===========

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define ERROR INT_MIN
#define GET_LENGTH(array)  (sizeof(array) / sizeof((array)[0]))

typedef struct Node
{
	int data;
	struct Node *next;

} Node;

typedef struct LinkedList
{
	Node *head, *tail;
	int size;

} LinkedList;

Node *createNode(int data)
{
	Node *new_node = (Node*)malloc(sizeof(Node));
	if (new_node == NULL)
	{
		printf("Error: out of memory.\n");
		return NULL;
	}

	new_node->data = data;
	new_node->next = NULL;

	return new_node;
}

LinkedList *createLinkedList(void)
{
	Node *new_node;

	LinkedList *new_LinkedList = malloc(sizeof(LinkedList));
	if (new_LinkedList == NULL)
	{
		printf("Error: out of memory.\n");
		return NULL;
	}

	new_node = createNode(ERROR);
	if (new_node == NULL)
	{
		free(new_LinkedList);
		return NULL;
	}

	new_LinkedList->head = new_LinkedList->tail = new_node;
	new_LinkedList->size = 0;

	return new_LinkedList;
}

int add(LinkedList *list, int data)
{
	Node *new_node;

	if (list == NULL || list->head == NULL) return 0;

	new_node = createNode(data);
	if (new_node == NULL) return 0;

	if (list->head == list->tail)
		list->head->next = list->tail = new_node;
	else
		list->tail = list->tail->next = new_node;

	list->size++;

	return 1;
}

int pop(LinkedList *list)
{
	Node *tmp;
	int retval;

	if (list == NULL || list->head == NULL || list->head->next == NULL)
		return ERROR;
	
	retval = list->head->next->data;
	if (list->head->next == list->tail) 
		list->tail = list->head;

	tmp = list->head->next;
	list->head->next = list->head->next->next; // lolol
	
	free(tmp);
	list->size--;

	return retval;
}

int isEmpty(LinkedList *list)
{
	return list == NULL || list->head == NULL || list->head->next == NULL;
}

LinkedList *destroyLinkedList(LinkedList *list)
{
	Node *tmp;
	
	if (list == NULL) return NULL;
	
	while (list->head != NULL)
	{
		tmp = list->head;
		list->head = list->head->next;
		free(tmp);
	}

	free(list);
	return NULL;
}

void displayList(LinkedList *list)
{
	int i;
	Node *tmp;

	if (list == NULL || list->head == NULL || list->head->next == NULL)
		printf("List: <__EMPTY_LIST__>\n");
	
	i = 1;
	tmp = list->head->next;

	printf("size: %d\n", list->size);
	printf("List: ");

	while (tmp != NULL)
	{
		printf("%d%s", tmp->data, (i++ < list->size) ? ", " : "\n");
		tmp = tmp->next;
	}
}

int find_max(int *array, size_t length)
{
	int i, max;

	if (array == NULL || length < 1) return ERROR;

	max = array[0];
	for (i = 1; i < length; i++)
		if (array[i] > max)
			max = array[i];

	return max;
}

// This Radix Sort does not support negative numbers.
void radixSort(int *array, size_t length)
{
	int i = 0, j = 0, pow = 0 , max = 0;
	LinkedList *buckets[10];

	if (array == NULL || length < 1 || buckets == NULL) return;

	for (i = 0; i < 10; i++)
	{
		buckets[i] = createLinkedList();
		if (buckets[i] == NULL) return;
	}

	max = find_max(array, length);
	for (pow = 1; max / pow > 0; pow *= 10)
	{
		// place into buckets
		for (i = 0; i < length; i++)
			add(buckets[ array[i] / pow % 10 ], array[i]);
		
		// pull out of buckets and into the original array
		for (i = j = 0; i < 10; i++)
			while (!isEmpty(buckets[i]))
				array[j++] = pop(buckets[i]);
	}

	for (i = 0; i < 10; i++)
		destroyLinkedList(buckets[i]);
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
	int array[] = { 9678, 755, 769, 9821, 3, 37, 124, 567, 987, 1078, 99, 3489, 23, 8};
	size_t length = GET_LENGTH(array);

	radixSort(array, length);
	displayArray(array, length);

	return 0;
}