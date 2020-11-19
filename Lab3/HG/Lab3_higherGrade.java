package Java.HG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import Java.*;

/**
 * lab3_higherGrade.java // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Implement a program which takes as input a text file and allows
 *              the user to (repeatedly without re-reading the input file) ask
 *              questions: i) Which is the k:th most common word ii) Which are
 *              the k:th to the k+n:th most common words?
 *              Use https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt  as
 *              input. The questions should be answered in constant time. You
 *              need to be able to explain the choices of data structures and
 *              algorithms, and their complexities. The time to read the input
 *              and build the data structures must not be longer than 4 min.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-27
 * @contact: taxberg@kth.se
 * 
 */
public class Lab3_higherGrade {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {

        int minlen = 2, size = 0, val1 = 0, val2 = 0, index = 0;

        BinarySearchList<Integer, String> st = new BinarySearchList<>();
        BST<String, Integer> bst = new BST<>();
        List<String> wordsArray = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        File inFile;
        Scanner fs;
        String file = "";

        System.out.println("Please insert file name:");
        file = sc.next();
        inFile = new File(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Searching/Java/HG/"
                        + file);
        fs = new Scanner(inFile);

        while (fs.hasNext()) {
            String key = fs.next();
            if (key.length() < minlen)
                continue;
            if (bst.contains(key)) {
                bst.put(key, bst.get(key) + 1);
            } else {
                bst.put(key, 1);
            }
        }

        fs.close();

        for (String word : bst.keys()) {
            st.put(bst.get(word), word);
        }

        size = st.size();

        while (true) {

            System.out.print(
                    "Menu:\n\n1. Find K:th most common word\n2. Find the k:th to the k+n:th most common words.\n3. Exit. \n\nPlease type in a positive integer:\n");
            val1 = sc.nextInt();
            System.out.println();

            if (val1 == 1) {
                System.out.println("Please type in K as in K:th most common word:");
                val1 = sc.nextInt();
                wordsArray = st.selectList(size - val1);
                System.out.println("The " + val1 + " most common word(s) is:\n");
                for (int i = 0; i < wordsArray.size(); i++) {
                    System.out.println(wordsArray.get(i));
                }
                System.out.println();
            }
            if (val1 == 2) {
                System.out.println("Please type in K as in k:th to the k+n:th most common words:");
                val1 = sc.nextInt();
                System.out.println("Please type in n as in k:th to the k+n:th most common words:");
                val2 = sc.nextInt();
                System.out.println("The " + val1 + " to " + val2 + "most common word(s) is:\n");
                index = val1;
                for (int i = size - val1; i > size - val2; i--) {
                    wordsArray = st.selectList(i);
                    System.out.println(index++ + ":");
                    for (int j = 0; j < wordsArray.size(); j++) {
                        System.out.println(wordsArray.get(j));
                    }
                }
                System.out.println();

            }
            if (val1 == 3)
                break;
        }
        sc.close();
    }
}
