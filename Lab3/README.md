## Lab 3 - E

**NB**: This lab also covers material introduced in ch. 3.4 and 3.5 in hashing and applications

**Input for the programming assignments:** You should use this text as input for your programming assignments: [http://www.gutenberg.org/files/98/98-0.txt (Länkar till en externa sida.)Länkar till en externa sida.](http://www.gutenberg.org/files/98/98-0.txt) henceforth referred to as "*the text*".

**Programming assignments for grade E**

1. Write a simple filter to clean a text, i.e. to remove all characters that are not alphabetic, blank or newline - replacing every such character by a blank to keep the number of characters constant to the original text. Hint: this is easy to do in C using the "isalpha()" function (to find out more about it on a unix/linux system type: *man isalpha* as a command to the shell)

2. Use the first N (N in the order of hundred words) words from the text to compare the running times of the ordered array ST (algorithm 3.2) to the Binary Search Tree algorithm (Algorithm 3.3) (you need only implement the basic operations to put and get keys to/from the ST) Use the FrequencyCounter from page 372 as test program (you may need to change how you read the words if you do not use the libraries from Sedgewick&Wayne)..

3. Write a program that shows how evenly the built-in hash function for strings in Java distributes the hashes for the words found in the text.

4. Write an "index"-program which allows the user to ask questions "*on which positions in the text (i.e. the number of characters from the beginning) you find the word X"*. The program should list the position of all occurrences of X as answer to a query. In this assignment you may use the Java library (built-in) lists. Questions to the index should be answered in time less or equal to O(log(N)) where N is the number of keys.

    **Theory questions for grade E:**

    Show means that you should be able to project the code/files you have uploaded in Canvas from Canvas from your own computer or the teachers. I.e. you cannot use another file than the one you have uploaded in Canvas. You should be able to explain your code and your design choices in detail as well as how you have come up with the answers to theory questions. When comparing execution times make sure that the experiments are useful, i.e. show facts.

1. a) Show how a binary search tree is built when the following sequence of keys are inserted: 
      - [x]  W O E C A L H

   b) Show the output if the content of the trees is printed in pre-, in- and postfix order

2. Explain/show how you measured the execution times in programming assignment 2 and how the results compare to what the theoretical calculations would suggest

## Lab 3 - Higher Grades

**Higher grade problem**

**Note:** You should have solved all grade E problems and successfully presented the problems if you have been selected at the seminar to be able to account this problem

Implement a program which takes as input a text file and allows the user to (repeatedly without re-reading the input file) ask questions: i) Which is the k:th most common wordii) Which are the k:th to the k+n:th most common wordsUse [https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt](https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt)  as input. The questions should be answered in constant time. You need to be able to explain the choices of data structures and algorithms, and their complexities. The time to read the input and build the data structures must not be longer than 3 min.
