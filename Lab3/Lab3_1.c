/**
 * lab2_4.c // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Write a simple filter to clean a text, i.e. to remove all characters that are not alphabetic, blank or newline - replacing every such character by a blank to keep the number of characters constant to the original text Hint: this is easy to do in C using the "isalpha( function (to find out more about it on a unix/linux system type: man isalpha as a command to the shell)
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
#include <ctype.h>

void simpleFilter()
{
    char a;
    printf("\n");
    FILE *file = fopen("the_HG_filter_text.txt", "w");

    while ((a = getchar()) != EOF)
    {
        if (!isalpha(a) && a != '\n' && a != ' ')
        {
            fputc(' ', file);
        }
        else
        {
            fputc(a, file);
        }
    }

    printf("\n\n");
}

int main()
{
    simpleFilter();
    return 0;
}
