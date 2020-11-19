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

public class lab1_higherGrades_DoubleStack {

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

    public static boolean checkIfBalanced() throws IOException {

        boolean isBalanced = true;
        char a = (char) System.in.read();
        char checkInp;
        char checkBrackets;

        Stack inpStack = new Stack();
        Stack checkStack = new Stack();

        while (a != '\n') {
            inpStack.push(a);
            a = (char) System.in.read();
        }

        while (isBalanced && !inpStack.isEmpty()) {

            checkInp = inpStack.pop();

            if (checkInp == ')' || checkInp == '}' || checkInp == ']') {

                checkStack.push(checkInp);

            } else if ((checkInp == '(' || checkInp == '{' || checkInp == '[') && !checkStack.isEmpty()) {

                checkBrackets = checkStack.pop();

                if ((checkBrackets == '(' && checkInp != ')') || (checkBrackets == '{' && checkInp != '}')
                        || (checkBrackets == '[' && checkInp != ']'))
                    isBalanced = false;

            } else
                isBalanced = false;
        }

        return isBalanced && checkStack.isEmpty();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("The input of brackets is balanced: " + checkIfBalanced());

    }
}
