package Labs;

import Other.Std.*;

/**
 * Lab2_2.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve?
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * @Assignment: Add a method which counts the number of inversions in the input
 *              array and prints a list of all inversions on the format
 *              [i,a[i]], [j, a[j]] where i and j are indices and a[i], a[j] are
 *              the values of the elements. Call the method from main() before
 *              the array is sorted. Calculates the time complexity for the
 *              algorithm.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.2
 * @since 2020-09-11
 * @contact: taxberg@kth.se
 * 
 */
public class Lab2_3 {

    public static class Insertion extends Sort {

        public static void sort(Integer[] a) { // Sort a[] into increasing order.

            int N = a.length;

            for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                    exch(a, j, j - 1);
                    slowDownHalfSec();
                }
            }
        }

    }

    public static class inversionCounter extends Sort {

        public static void count(Integer[] a) {

            int N = a.length;

            int invrsCount = 0;
            StringBuilder invrsString = new StringBuilder();

            for (int i = 0; i < N - 1; i++)
                for (int j = i + 1; j < N; j++)
                    if (less(a[j], a[i])) {
                        invrsString.append("[" + j + ", " + a[j] + "], [" + i + ", " + a[i] + "]\n");
                        invrsCount++;
                    }

            StdOut.println("\nNumbers of inversions is " + invrsCount
                    + ". Inversions are:\n\n [index(smaller), value(smaller)], [index(greater), value(greater)]\n");
            StdOut.println(invrsString);
            StdOut.println("\nTime complexity of method count is O(N^2). As we have two for loops.\n");
        }

    }

    public static void main(String[] args) {

        StdOut.println("Please type in the integer of the amount of integers you want to sort: ");
        StdOut.print("> ");

        int i = StdIn.readInt();
        Integer[] a = new Integer[i];

        StdOut.println("Please type in the integers you want to sort: ");
        for (int j = 0; j < i; j++) {
            StdOut.print("> ");
            a[j] = StdIn.readInt();
        }

        inversionCounter.count(a);
    }
}