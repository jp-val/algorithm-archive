// JP Valdespino
// August 2021

// GCD.c
// =====
// Greatest Common Denominator

#include <stdio.h>

int gcd(int a, int b)
{
	return (b == 0) ? a : gcd(b, a%b);
}

int main(int argc, char **argv)
{
	printf("%d\n", gcd(9, 6));
	printf("%d\n", gcd(21, 14));
	printf("%d\n", gcd(12, 10));

	return 0;
}