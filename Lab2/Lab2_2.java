package Labs;

import Other.Std.*;

/**
 * Lab2_2.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve?
 * 
 * How is the code used? (How to execute, input and what output) - Program asks
 * user to type in an integer value for the amount of inputs. User then inputs
 * that many inputs.
 * 
 * @Assignment: Augment the previous implementation so that it prints the number
 *              of swaps performed when sorting the array.
 * 
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-11
 * @contact: taxberg@kth.se
 * 
 */

public class Lab2_2 {

    public static class Insertion extends Sort {

        public static void sort(Integer[] a) { // Sort a[] into increasing order.

            int swaps = 0;
            int N = a.length;

            for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                    exch(a, j, j - 1);
                    slowDownHalfSec();
                    StdOut.println("Swap! Nr or swaps: " + ++swaps); // Here se print out if a swap is made.
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
        StdOut.println("The input is sorted: " + Insertion.isSorted(a));
        Insertion.show(a);
    }

}
