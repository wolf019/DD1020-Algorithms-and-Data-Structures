package Labs.HigherGradesAssignments;

import java.io.IOException;
import java.util.Scanner;
import Other.Std.StdRandom;

/**
 * Lab2_6.java // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) - The code
 * gives the user three options. One for each assignment to check. Exit with 4.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: 1. Augment the test code from assignment 1 so that the array is
 *              sorted in descending order instead of ascending order (you may
 *              add O(N) operations) Clarification: You should not change the
 *              sorting method, nor should you sort the array an extra time. You
 *              may traverse the array once before sorting and once after
 *              sorting. During these traversals you may not move any elements.
 *              (Hint: you need not and should not use any extra memory)
 *              ******** SOLVED: Make all negative first and again after *******
 *              2. Compare the execution times for sorting large arrays of
 *              integers with quicksort and merge sort. When should one select
 *              quicksort over mergesort? *************************************
 *              SOLVED: depends on how big the elements are *******************
 *              3.Compare the execution times of quicksort where the first
 *              element in each sub-array is selected as partitioning element to
 *              that of quicksort with median-of-three partitioning. ********
 *              SOLVED: Depending on the element size. MotMerge is faster for a
 *              smaller amount of elements. But with larger sized elements,
 *              MotMerge is slightly faster for array length of > 1M. But not
 *              much. So we could say that MotMerge is faster for element number
 *              of < 500k
 * 
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-12
 * @contact: taxberg@kth.se
 * 
 */

public class lab2_higherGrades {

    /**
     * Method less compares the first parameter v with the second w.
     * 
     * @param v
     * @param w
     * @return boolean that answers the question if the first argument is lesser
     *         than the second.
     */
    public static boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Method exch exchanges takes in the Comparable array that is to be sorted and
     * the two indices that is to swap places.
     * 
     * @param a: Comparable array
     * @param i: element which is to be exchanged with j.
     * @param j: elements which is to be exchanged.
     */
    public static void exch(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Method show prints out the Comparable array that is to be sorted in its
     * current state. Prints out array in format [x1, x2, ... , xn].
     * 
     * * @param a: Comparable array
     */
    public static void show(Integer[] a) { // Print the array, on a single line.
        System.out.print("[");
        for (int i = 0; i < a.length - 1; i++)
            System.out.print(a[i] + ", ");
        System.out.print(a[a.length - 1] + "]\n");
    }

    /**
     * isSorted takes in the parameter a, which is the Comparable array that is to
     * be sorted, and checks if it's sorted.
     * 
     * @param a: Comparable array
     * @return : boolean that answers the question if a is sorted or not.
     */
    public static boolean isSorted(Integer[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    /**
     * Sets a pause for half a second. Used to make the process go slower.
     */
    public static void slowDownHalfSec() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sets a pause for two second. Used to make the process go slower.
     */
    public static void slowDownTwoSec() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static class Insertion {

        public static void sort(Integer[] a) { // Sort a[] into increasing order.

            int N = a.length;
            int i = 0;

            for (i = 0; i < N; i++) {
                a[i] = a[i] * -1;
            }

            for (i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) { //
                    // show(a);
                    exch(a, j, j - 1);
                    // slowDownHalfSec();
                }
            }

            for (i = 0; i < N; i++) {
                a[i] = a[i] * -1;
            }
        }
    }

    public static class Merge {

        private static Integer[] aux1; // auxiliary array for merges

        public static void sort(Integer[] b) {
            aux1 = new Integer[b.length]; // Allocate space just once.
            divide(b, 0, b.length - 1);
        }

        private static void divide(Integer[] b, int lo, int hi) { // Sort a[lo..hi].

            int mid = lo + (hi - lo) / 2;

            if (hi <= lo)
                return;

            divide(b, lo, mid); // Sort left half.
            divide(b, mid + 1, hi); // Sort right half.
            conquer(b, lo, mid, hi); // Merge results

        }

        public static void conquer(Integer[] b, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
                aux1[k] = b[k];
            for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                if (i > mid)
                    b[k] = aux1[j++];
                else if (j > hi)
                    b[k] = aux1[i++];
                else if (less(aux1[j], aux1[i]))
                    b[k] = aux1[j++];
                else
                    b[k] = aux1[i++];
        }
    }

    /**
     * Quick is quicksort.
     */
    public static class Quick {

        /**
         * 
         * @param a:  The Integer array that is to be sorted.
         * @param lo: The Partitioning element index
         * @param hi: The last element index.
         * @return j: The element index where partitioning element is
         */
        private static int partition(Integer[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].

            int i = lo, j = hi + 1; // left and right scan indices
            Integer v = a[lo]; // partitioning item

            while (true) { // This is the conquer step. We check so that i and j finds the place
                           // where to place v.

                while (less(a[++i], v)) // If i is smaller than v, increment i
                    if (i == hi)
                        break;

                while (less(v, a[--j])) // If j is bigger than v, decrement j
                    if (j == lo)
                        break;

                if (i >= j) // If j has passed i we are done.
                    break;
                exch(a, i, j); // If we find items that are out of order, exchange them.
            }

            exch(a, lo, j); // Put v in position (after j)
            return j;
        }

        public static void sort(Integer[] a) {

            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }

        private static void sort(Integer[] a, int lo, int hi) {

            if (hi <= lo)
                return;

            int j = partition(a, lo, hi); // j divides the array
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
    }

    /**
     * QuickThreePart is quicksort with median-of-three partitioning.
     */
    public static class QuickThreePart {

        /**
         * 
         * @param a:  The Integer array that is to be sorted.
         * @param lo: The Partitioning element index
         * @param hi: The last element index.
         * @return j: The element index where partitioning element is
         */
        private static int partition(Integer[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].

            int i = lo, j = hi + 1; // left and right scan indices
            Integer v = a[lo]; // partitioning item

            while (true) { // This is the conquer step. We check so that i and j finds the place
                           // where to place v.

                while (less(a[++i], v)) // If i is smaller than v, increment i
                    if (i == hi)
                        break;

                while (less(v, a[--j])) // If j is bigger than v, decrement j
                    if (j == lo)
                        break;

                if (i >= j) // If j has passed i we are done.
                    break;
                exch(a, i, j); // If we find items that are out of order, exchange them.
            }

            exch(a, lo, j); // Put v in position (after j)
            return j;
        }

        /**
         * medianOf3 takes in three indices, checks which element in a ,corresponding to
         * the index, that is the median.
         * 
         * @return the median index of the three elements
         */
        public static int medianOf3(Integer[] a, int lo, int mid, int hi) {

            int m = hi;

            if ((a[lo] > a[mid] && a[lo] < a[hi]) || (a[lo] < a[mid] && a[lo] > a[hi]))
                m = lo;
            else if ((a[mid] > a[lo] && a[mid] < a[hi]) || (a[mid] < a[lo] && a[mid] > a[hi]))
                m = mid;

            return m;

        }

        public static void sort(Integer[] a) {

            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }

        private static void sort(Integer[] a, int lo, int hi) {

            if (hi <= lo)
                return;

            int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
            exch(a, lo, m); // Puts element of index m to partition place.43

            int j = partition(a, lo, hi); // j divides the array
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int val = 0;

        while (true) {

            System.out.println("\n\n\nPlease type in the assignment number (1, 2 or 3) exit with 4: ");
            System.out.print("> ");

            val = sc.nextInt();

            if (val == 4)
                break;

            System.out.println("\nPlease type in the integer of the amount of integers you want to sort: ");
            System.out.print("> ");

            int i = sc.nextInt();

            int k = 0;
            Integer[] a = new Integer[i];
            Integer[] b = new Integer[i];

            StdRandom.setSeed(531245);

            for (int j = 0; j < i; j++) {
                k = StdRandom.uniform(256);
                a[j] = k;
                b[j] = k;
            }
            if (val == 1) {
                System.out.println(
                        "\nNote: In insertionsort we now exchange the element if the first is NOT less then the other. \n");
                slowDownTwoSec();
                show(a);
                long startTime0 = System.nanoTime();
                Insertion.sort(a);
                long endTime0 = System.nanoTime();

                System.out.println(
                        "\n\tTime Insertionsort reverse:" + (endTime0 - startTime0) * Math.pow(10, -9) + " seconds.\n");
                show(a);

            }

            if (val == 2) {

                k = 0;
                while (k++ < 10) {

                    long startTime2 = System.nanoTime();
                    Quick.sort(a);
                    long endTime2 = System.nanoTime();

                    System.out.println("\n\tTime Quicksort: " + (endTime2 - startTime2) * Math.pow(10, -3)
                            + " microseconds. The input is sorted: " + isSorted(a) + "\n");

                    long startTime1 = System.nanoTime();
                    Merge.sort(b);
                    long endTime1 = System.nanoTime();

                    System.out.println("\n\tTime Mergesort: " + (endTime1 - startTime1) * Math.pow(10, -3)
                            + " microseconds. The input is sorted: " + isSorted(b) + "\n");
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    slowDownHalfSec();
                }
            }

            if (val == 3) {
                k = 0;
                while (k++ < 11) {
                    {
                        long startTime3 = System.nanoTime();
                        Quick.sort(a);
                        long endTime3 = System.nanoTime();

                        System.out.println("\n\tTime Quicksort: \t\t\t " + (endTime3 - startTime3) * Math.pow(10, -6)
                                + " milliseconds. The input is sorted: " + isSorted(a) + "\n");

                        long startTime4 = System.nanoTime();
                        QuickThreePart.sort(b);
                        long endTime4 = System.nanoTime();

                        System.out.println("\n\tTime Quicksort with m.o.t. partitioning: "
                                + (endTime4 - startTime4) * Math.pow(10, -6) + " milliseconds. The input is sorted: "
                                + isSorted(b) + "\n");
                        System.out.println(
                                "----------------------------------------------------------------------------------");
                        slowDownHalfSec();
                    }
                }
            }
        }
        sc.close();
    }
}
