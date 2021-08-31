// JP Valdespino
// August 2021

// Fibonacci.c
// ===========
// Recursive, Memoization, and Dynamic Programming solutions.

#include <stdio.h>
#include <stdlib.h>

int fibonacci_recursive(int n)
{
	if (n < 2) return n;
	return fibonacci_recursive(n-1) + fibonacci_recursive(n-2);
}

int fibonacci_memo_helper(int *fib, int n)
{
	if (n < 2) return n;

	if (fib[n-1] == -1)
		fib[n-1] = fibonacci_memo_helper(fib, n-1);

	if (fib[n-2] == -1)
		fib[n-2] = fibonacci_memo_helper(fib, n-2);

	return fib[n-1] + fib[n-2];
}

int fibonacci_memo(int n) // wrapper function
{
	int retval;
	int *fib = (int*)malloc(n * sizeof(int));
	
	fib[0] = 0;
	fib[1] = 1;

	for (int i = 2; i < n; i++)
		fib[i] = -1;

	retval = fibonacci_memo_helper(fib, n);	
	free(fib);

	return retval;
}

int fibonacci_dp(int n)
{
	int retval;
	int *fib = (int*)malloc((n + 1) * sizeof(int));

	fib[0] = 0;
	fib[1] = 1;

	for (int i = 2; i <= n; i++)
		fib[i] = fib[i-1] + fib[i-2];

	retval = fib[n];
	free(fib);

	return retval;
}

int main(int argc, char **argv)
{
	int arg;
	
	if (argc < 2)
	{
		printf("Expected a second command line arguement of type integer.\n");
		return 0;
	}

	arg = atoi(argv[1]);

	if (arg > 46)
	{
		printf("You should try a smaller value. Preferably something less than 47.\n");
		return 0;
	}

	printf("fib %d: %d\n", arg, fibonacci_recursive(arg));
	printf("fib %d: %d\n", arg, fibonacci_memo(arg));
	printf("fib %d: %d\n", arg, fibonacci_dp(arg));

	return 0;
}