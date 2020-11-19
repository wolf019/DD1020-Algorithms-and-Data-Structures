package Labs;

import Other.Std.*;

/**
 * Lab2_2.java // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) - Code is just
 * executed as it is. Program asks the user hos to input and then sorts the
 * input. In this case there is only integers thats accepted.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Implement insertionsort. Augment the sorting process so that all
 *              the content of the array that is being sorted is printed after
 *              each inner loop iteration. Write a unit test in main() which
 *              allows the user to define the size of the input (N) and then
 *              input (N) integers from stdin which is to be sorted.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-11
 * @contact: taxberg@kth.se
 * 
 */

public class Lab2_1 {

    public static class Insertion extends Sort {

        public static void sort(Integer[] a) { // Sort a[] into increasing order.

            int N = a.length;

            for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                    // show(a); // Shows the array
                    exch(a, j, j - 1);
                    // slowDownHalfSec(); // Slows down the execution for better overview.
                }
            }
        }

    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.

        StdOut.println("Please type in the integer of the amount of integers you want to sort: ");
        StdOut.print("> ");

        int i = StdIn.readInt();
        Integer[] a = new Integer[i];

        StdOut.println("Please type in the integers you want to sort: ");

        for (int j = 0; j < i; j++) {
            StdOut.print("> ");
            a[j] = StdIn.readInt();
        }

        StdOut.println("The input is sorted: " + Insertion.isSorted(a));
        Insertion.sort(a);
        Insertion.show(a);
        StdOut.println("The input is sorted: " + Insertion.isSorted(a));

    }

}
