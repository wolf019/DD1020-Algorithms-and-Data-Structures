package Labs;

import java.io.IOException;

/**
 * lab1_higherGrades.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve? Code API implements a LIFO ADT Queue. Is to
 * show that the author know how to implement this queue ADT.
 * 
 * How is the code used? (How to execute, input and what output) - There are
 * three main methods that are available for the user. enqueue, dequeue and
 * printQueueRecursive. Enqueue adds an element in front of the first element
 * refered to by head by taking in a generic parameter. Dequeue removes the
 * first element in the queue and returns its generic value. printQueueRecursive
 * prints out all generic items recursively.
 * 
 * What it has been based upon ? Based upon FIFO queue from chapter 1.3 in
 * course book Algorithms by Robert Sedgewich and Robert Wayne.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.2
 * @since 2020-09-03
 * @contact: taxberg@kth.se
 * 
 */

public class lab1_higherGrades {

    /**
     * Class Queue is an ordered queue. The elements stored in the queue is integer
     * values. The element are ordered at insertion so that all elements are stored
     * in ascending order.
     * 
     * @param <Item>
     */
    public static class Stack {

        private Node head;

        private class Node {
            char data;
            Node next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void push(char data) {

            Node n = head;

            head = new Node();
            head.data = data;
            head.next = n;
        }

        public char pop() {
            char data = head.data;
            head = head.next;
            return data;
        }
    }

    public static char[] resize(char[] arrayInUse) {

        char[] newArray = new char[arrayInUse.length * 2];

        for (int i = 0; i < arrayInUse.length; i++)
            newArray[i] = arrayInUse[i];

        return newArray;
    }

    public static boolean checkIfBalanced() throws IOException {

        char[] bracketsToCheck = new char[4];
        boolean isBalanced = true;
        char a = (char) System.in.read();
        char check;
        int i = 0;
        int current = 0;

        Stack stack = new Stack();

        while (a != '\n') {
            if (i < bracketsToCheck.length) {
                bracketsToCheck[i++] = a;
                a = (char) System.in.read();
            } else
                bracketsToCheck = resize(bracketsToCheck);
        }

        while (isBalanced && (current != i)) {
            if (bracketsToCheck[current] == '(' || bracketsToCheck[current] == '{' || bracketsToCheck[current] == '[') {
                stack.push(bracketsToCheck[current++]);
            } else if ((bracketsToCheck[current] == ')' || bracketsToCheck[current] == '}'
                    || bracketsToCheck[current] == ']') && !stack.isEmpty()) {
                check = stack.pop();

                if ((check == '(' && bracketsToCheck[current] != ')')
                        || (check == '{' && bracketsToCheck[current] != '}')
                        || (check == '[' && bracketsToCheck[current] != ']')) // pop and current should not be the same.
                    isBalanced = false;
                current++;
            } else
                isBalanced = false;
        }

        return isBalanced && stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("The input of brackets is balanced: " + checkIfBalanced());

    }
}
