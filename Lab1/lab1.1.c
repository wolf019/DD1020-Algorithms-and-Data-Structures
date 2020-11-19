//
//  assignment.c
//  ID 1020 @KTH
//
//  In C implement a recursive and an iterative version of a function which reads characters from stdin until a newline character is read and then prints them on stdout in reverse order. Hint: use getchar(), putchar() (or getc(), putc()). For the iterative version you may assume a fixed max length of the input.
//
//
// Author Tom K.Axberg
// Created  30.08.20
// Last edit 31.08.20
// Contact : taxberg @kth.se
//
// _____________________________________________________________

#include <stdio.h>
#include <stdlib.h>

/*
Following variables will be implemented inside the methods in a later verision.
*/
char *String;
int count = 0;
int i = 0;
int arrayLength;

/*
method dubleArray creates a dubble 
*/
void doubleArray()
{

    char *dubbleArray = (char *)malloc(arrayLength = arrayLength * 2);

    while (i < (arrayLength / 2))
    {
        dubbleArray[i] = String[i];
        i++;
    }

    i = 0;
    *String = *dubbleArray;
}

void iterative(char a)
{
    arrayLength = 16;
    String = (char *)malloc(arrayLength);

    while ((a = getchar()) != '\n')
    {
        if (count < arrayLength)
            String[count++] = a;
        else if (count == arrayLength)
        {
            doubleArray();
            String[count++] = a;
        }
    }

    while (arrayLength-- >= 0)
        putchar(String[arrayLength]);
}

void recursive(char a)
{
    if ((a = getchar()) != '\n')
    {
        recursive(a);
        putchar(a);
    }
}

int main()
{
    char a;
    recursice();
    iterative(a);
    printf("\n");
}
