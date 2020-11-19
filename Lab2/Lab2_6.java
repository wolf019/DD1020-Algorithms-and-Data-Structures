package Labs;

import Other.Std.*;

/**
 * Lab2_6.java // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Experiment with the cut-off to insertionsort in merge. How is
 *              the execution time affected by different values for the cut-off?
 *              A suitable range for cut-off values to test with could be
 *              [0-30]. Upload code, tests and a graphs.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-12
 * @contact: taxberg@kth.se
 * 
 */

public class Lab2_6 {

    public static class Merge {

        private static Integer[] aux1; // auxiliary array for merges

        public static void sort(Integer[] b) {
            aux1 = new Integer[b.length]; // Allocate space just once.
            sort(b, 0, b.length - 1);
        }

        private static void sort(Integer[] b, int lo, int hi) { // Sort a[lo..hi].

            int mid = lo + (hi - lo) / 2;

            if (hi <= lo)
                return;

            sort(b, lo, mid); // Sort left half.
            sort(b, mid + 1, hi); // Sort right half.
            merge(b, lo, mid, hi); // Merge results

        }

        public static void merge(Integer[] b, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
                aux1[k] = b[k];
            for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                if (i > mid)
                    b[k] = aux1[j++];
                else if (j > hi)
                    b[k] = aux1[i++];
                else if (Sort.less(aux1[j], aux1[i]))
                    b[k] = aux1[j++];
                else
                    b[k] = aux1[i++];
        }

    }

    public static class MergeWithInsertionsort {

        private static Integer[] aux; // auxiliary array for merges

        public static void sort(Integer[] a) {
            aux = new Integer[a.length]; // Allocate space just once.
            sort(a, 0, a.length - 1);
        }

        public static void InsertionSort(Integer[] a, int lo, int hi) { // Sort a[] into increasing order.

            for (int i = lo; i <= hi; i++)
                for (int j = i; j > 0 && Sort.less(a[j], a[j - 1]); j--)
                    Sort.exch(a, j, j - 1);
        }

        private static void sort(Integer[] a, int lo, int hi) { // Sort a[lo..hi].

            int mid = lo + (hi - lo) / 2;

            if (hi <= lo)
                return;

            if (mid <= 30) { // Put in the cut of value here. If mid <= to x we will do insertion sort.
                InsertionSort(a, lo, mid); // Sort left half.
                InsertionSort(a, mid + 1, hi); // Sort right half.
                merge(a, lo, mid, hi); // Merge results

            } else {
                sort(a, lo, mid); // Sort left half.
                sort(a, mid + 1, hi); // Sort right half.
                merge(a, lo, mid, hi); // Merge results
            }
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
                else if (Sort.less(aux[j], aux[i]))
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
        Integer[] a = new Integer[i];
        Integer[] b = new Integer[i];

        StdRandom.setSeed(1874359);

        int w = 0;
        while (w++ < 500) {

            for (int j = 0; j < i; j++) {
                k = StdRandom.uniform(256);
                a[j] = k;
                b[j] = k;
            }

            // show(b);
            long startTime3 = System.nanoTime();
            MergeWithInsertionsort.sort(a);
            long endTime3 = System.nanoTime();
            // show(b);
            System.out.println("\n\tTime MergeWithInsertionsort: " + (endTime3 - startTime3) * Math.pow(10, -3)
                    + " microseconds. Sorted = " + Sort.isSorted(a));

            long startTime2 = System.nanoTime();
            Merge.sort(b);
            long endTime2 = System.nanoTime();
            // show(b);
            System.out.println("\n\tTime Mergesort: " + (endTime2 - startTime2) * Math.pow(10, -3)
                    + " microseconds. Sorted = " + Sort.isSorted(b) + "\n");

        }
    }
}
