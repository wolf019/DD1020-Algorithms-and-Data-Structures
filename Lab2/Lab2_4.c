/**
 * lab2_4.c // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Implement a function in C which takes an array of integers (both positive and negative) 
 *              and orders the elements in the array so that all negative elements come before the positive. 
 *              You are not allowed to sort the array - only collect all negative values first. 
 *              The algorithm should only use O(1) extra memory.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-11
 * @contact: taxberg@kth.se
 * 
 */

#include <stdlib.h>
#include <stdio.h>

void swap(int *n1, int *n2)
{
    int temp; // Extra memory
    temp = *n1;
    *n1 = *n2;
    *n2 = temp;
}

void negOrderer(int *arrayToOrder, int size, int i, int j)
{
    j = 0;

    for (i = 0; i < size; i++)
        if (arrayToOrder[i] < 0)
            swap(&arrayToOrder[i], &arrayToOrder[j++]);
}

int main()
{
    int i;
    int size;
    int inp;

    printf("Please type in the integer of the amount of integers you want to sort :\n");
    scanf("%d", &size);                             // Save input at location size.
    int *arrayToOrder = malloc(sizeof(int) * size); // arrayToOrder points to allocated space of size.

    printf("Please type in the integers: \n");
    for (i = 0; i < size; i++)
    {
        scanf("%d", &inp);     // Save input at location inp
        arrayToOrder[i] = inp; // store value of inp in arrayToOrder + i
    }

    negOrderer(arrayToOrder, size, i, inp); // Reusing i and inp for algorithm.<

    printf("[");
    for (i = 0; i < size - 1; i++)
        printf("%d, ", arrayToOrder[i]);
    printf("%d]\n", arrayToOrder[i]);
    free(arrayToOrder);
    return 0;
}