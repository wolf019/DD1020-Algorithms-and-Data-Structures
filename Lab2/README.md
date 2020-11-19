## Lab 2 - E

   **Programming assignments for grade E**:

1. Implement insertionsort. Augment the sorting process so that all the content of the array that is being sorted is printed after each inner loop iteration. Write a unit test in main() which allows the user to define the size of the input (N) and then input (N) integers from stdin which is to be sorted.

2. Augment the above implementation so that it prints the number of swaps performed when sorting the array.

3. Add a method which counts the number of inversions in the input array and prints a list of all inversions on the format [i,a[i]], [j, a[j]] where i and j are indices and a[i], a[j] are the values of the elements. Call the method from main() before the array is sorted. Calculates the time complexity for the algorithm.

4. Implement a function in C which takes an array of integers (both positive and negative) and orders the elements in the array so that all negative elements come before the positive. You are not allowed to sort the array - only collect all  ****negative values first. The algorithm should only use O(1) extra memory (i.e. be in-place [Wikipedia: In-place algorithm (Länkar till en externa sida.)Länkar till en externa sida.](https://en.wikipedia.org/wiki/In-place_algorithm))

5. Compare the execution times for sorting large arrays of integers with insertionsort and merge sort. When should one select mergesort over insertionsort? Upload code, tests and a graphs.

6. Experiment with the cut-off to insertionsort in merge. How is the execution time affected by different values for the cut-off? A suitable range for cut-off values to test with could be [0-30]. Upload code, tests and a graphs.

## Lab 2 - Higher Grades

  **Higher grade problem**

All problems should be solved to pass

   1. Augment the test code from assignment 1 so that the array is sorted in descending order instead of ascending order (you may add O(N) operations)*Clarification: You should not change the sorting method, nor should you sort the array an extra time. You may traverse the array once before sorting and once after sorting. During these traversals you may not move any elements. (Hint: you need not and should not use any extra memory)*
   2. Compare the execution times for sorting large arrays of integers with quicksort and merge sort. When should one select quicksort over mergesort?
   3. Compare the execution times of quicksort where the first element in each sub-array is selected as partitioning element to that of quicksort with median-of-three partitioning

Note: your investigations to determine execution times should be good enough to be significant (i.e one should be able to draw conclusions from them that are valid)

