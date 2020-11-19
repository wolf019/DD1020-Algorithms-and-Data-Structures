package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

/**
 * lab3_4.java // ID 1020 KTH
 * 
 * 
 * How is the code used? (How to execute, input and what output) -
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Write a program that shows how evenly the built-in hash function
 *              for strings in Java distributes the hashes for the words found
 *              in the text.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-27
 * @contact: taxberg@kth.se
 * 
 */
public class Lab3_4 {
    /**
     * readFile reads the specified file in
     * /Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data
     * structures/Code/Searching/Java/ + file name, and creates an array
     * 
     * @param whichFile
     * @return
     * @throws FileNotFoundException
     */
    public static void fileInteractor() throws FileNotFoundException {

        Integer charCounter = 0;

        String key = "";
        char a;

        BinarySearchList<String, Integer> st = new BinarySearchList<>();

        File file = new File(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Searching/Java/the_filter_text.txt");
        Scanner fs = new Scanner(file).useDelimiter("");

        while (fs.hasNext()) {
            a = fs.next().charAt(0);
            charCounter++;
            if (a != ' ' && a != '\n') {
                while (Character.isLetter(a)) {
                    key = key + a;
                    a = fs.next().charAt(0);
                    charCounter++;
                }
                st.put(key, charCounter);
                key = "";
            }
        }

        Scanner sc = new Scanner(System.in);
        String val = "";
        List list = null;

        while (true) {

            System.out.println("Which word are you searching for?\n> ");
            val = sc.next();

            for (String word : st.keys()) { // Iterates through all keys in BinarySearchList
                if (word.equals(val)) {
                    list = st.get(word);
                    for (Object find : list) {
                        System.out.println((Integer) find);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        fileInteractor();
    }
}
