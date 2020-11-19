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

public class lab1_higherGrades_SingleStack {

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
        char inp = (char) System.in.read();
        char checkBrackets;

        Stack stack = new Stack();

        while (inp != '\n') {

            if (inp == '(' || inp == '{' || inp == '[') {
                stack.push(inp);
            } else if ((inp == ')' || inp == '}' || inp == ']') && !stack.isEmpty()) {
                checkBrackets = stack.pop();

                if ((checkBrackets == '(' && inp != ')') || (checkBrackets == '{' && inp != '}')
                        || (checkBrackets == '[' && inp != ']')) {
                    isBalanced = false;
                    return isBalanced;
                }
            } else
                isBalanced = false;

            inp = (char) System.in.read();
        }

        return isBalanced && stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("The input of brackets is balanced: " + checkIfBalanced());

    }
}
