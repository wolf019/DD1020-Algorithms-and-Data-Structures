package Labs;

import Other.Std.*;

/**
 * Lab2_5.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve?
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * @Assignment: Compare the execution times for sorting large arrays of integers
 *              with insertionsort and merge sort. When should one select
 *              mergesort over insertionsort? Upload code, tests and a graph.
 *              Insertion sort is faster for arraylength < 20.
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-12
 * @contact: taxberg@kth.se
 * 
 */

public class Lab2_5 extends Sort {

    public static class Insertion {

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

    public static class Merge {

        private static Integer[] aux; // auxiliary array for merges

        public static void sort(Integer[] a) {
            aux = new Integer[a.length]; // Allocate space just once.
            sort(a, 0, a.length - 1);
        }

        private static void sort(Integer[] a, int lo, int hi) { // Sort a[lo..hi].

            if (hi <= lo)
                return;

            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid); // Sort left half.
            sort(a, mid + 1, hi); // Sort right half.
            merge(a, lo, mid, hi); // Merge results (code on page 271).

        }

        public static void merge(Integer[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (less(aux[j], aux[i]))
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
        }

    }

    public static void main(String[] args) {

        StdOut.println("\n\nPlease type in the integer of the amount of random integers you want to sort: ");
        StdOut.print("> ");

        int i = StdIn.readInt();
        int k;
        int w = 0;

        StdRandom.setSeed(5794157);

        Integer[] a = new Integer[i];
        Integer[] b = new Integer[i];

        while (w++ < 11) {
            for (int j = 0; j < i; j++) {
                k = StdRandom.uniform(2567);
                a[j] = k;
                b[j] = k;
            }
            // System.out.println(isSorted(b));
            // show(b);
            long startTime1 = System.nanoTime();
            Insertion.sort(b);
            long endTime1 = System.nanoTime();
            // show(b);
            StdOut.println("\n\tTime insertionsort: " + (endTime1 - startTime1) * Math.pow(10, -3) + " microseconds.");
            // System.out.println(isSorted(b));

            // System.out.println(isSorted(a));
            long startTime2 = System.nanoTime();
            Merge.sort(a);
            long endTime2 = System.nanoTime();
            // show(a);
            StdOut.println("\n\tTime mergesort: " + (endTime2 - startTime2) * Math.pow(10, -3) + " ,microseconds.");
            // System.out.println(isSorted(a));
            // assert isSorted(a);

            if ((endTime1 - startTime1) < (endTime2 - startTime2))
                StdOut.println("\nInsertionsort was " + ((endTime2 - startTime2) - (endTime1 - startTime1))
                        + " nano seconds faster with array of length " + i + "\n");
            else
                StdOut.println("\nMergesort was " + ((endTime1 - startTime1) - (endTime2 - startTime2))
                        + " nano seconds faster with array of length " + i + "\n");
        }
    }

}