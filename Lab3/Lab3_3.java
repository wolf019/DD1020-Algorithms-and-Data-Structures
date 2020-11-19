package Java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
public class Lab3_3 {
    public static void main(String[] args) throws IOException {

        int N = 10971;

        BST<String, Integer> st = new BST<String, Integer>();

        File file = new File(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Searching/Java/the_filter_text.txt");
        Scanner fs = new Scanner(file);

        while (fs.hasNext()) {
            String key = fs.next();
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
            }
        }
        FileWriter myWriter = new FileWriter("Lab3.3_Hashes.txt");

        for (String word : st.keys()) {
            try {
                myWriter.write(String.valueOf((word.hashCode() & 0x7fffffff) % N) + "\n");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.\nPlease see file for hash distribution.");
        fs.close();
    }
}
