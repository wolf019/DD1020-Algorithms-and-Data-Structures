package Labs;

/**
 * lab1_6.java // ID 1020 KTH
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

public class lab1_6 {

    /**
     * Class Queue is an ordered queue. The elements stored in the queue is integer
     * values. The element are ordered at insertion so that all elements are stored
     * in ascending order.
     * 
     * @param <Item>
     */
    public static class Queue {

        /**
         * N is the number of elements in the queue.
         */
        private int N = 0;

        /**
         * Class Node is an object that contains an integer and a reference to an other
         * Node object. Attributes: next: reference to the next node data: the integer
         * that the node contains
         */

        public class Node {

            private Node next;
            private int data;

            public Node(int data) {

                this.data = data;
            }

            public Node(int data, Node next) {

                this.next = next;
                this.data = data;
            }
        }

        /**
         * head is a node that points to the first node, or null.
         */
        public Node head = null;

        /**
         * enqueue takes in param: data, creates a new node with that data and puts it
         * in the liked list so that ascending order is preserved.
         * 
         * @param data: the integer that will be stored in the node created.
         */
        public void enqueue(int data) {

            Node n = new Node(data);
            N++;

            if (head == null) {
                head = n;
                printQueue();
                return;
            }

            int i = 0;
            Node r = head;

            if (n.data < head.data) {
                n.next = head;
                head = n;
            } else {
                while (++i < N) {
                    if (r.next == null)
                        break;
                    else if (r.next.data < n.data)
                        r = r.next;
                }
                n.next = r.next;
                r.next = n;
            }

            printQueue();

        }

        /**
         * dequeue takes the first node in the queue / liked list and returns its data.
         * 
         * @return int value of dequeued node.
         */
        public int dequeue() {

            Node r = head;

            head = r.next;
            r.next = null;
            N--;

            printQueue();

            return r.data;
        }

        /**
         * printQueue prints all elements in ascending order in the following format:
         * [x_1, x_2, ... x_n]
         */
        public void printQueue() {

            Node n = head;
            StringBuilder sb = new StringBuilder();

            sb.append("[");

            if (N > 0) {

                while (n.next != null) {
                    sb.append(n.data);
                    sb.append(", ");
                    n = n.next;
                }
            }

            sb.append(n.data);
            sb.append("]");

            System.out.println(sb.toString());
        }

        /**
         * toString method to represent the list in a convenient way.
         */
        @Override
        public String toString() {

            Node n = head;

            StringBuilder sb = new StringBuilder();

            sb.append("[");

            while (n.next != null) {
                sb.append(n.data);
                sb.append(", ");
                n = n.next;
            }
            sb.append(n.data);
            sb.append("]");

            return sb.toString();
        }

    }

    public static void main(String[] args) {

        Queue queue = new Queue();

        queue.enqueue(2);
        queue.enqueue(7);
        queue.enqueue(7);
        queue.enqueue(4);
        queue.enqueue(10);
        queue.enqueue(7);
        queue.enqueue(-1);
        queue.enqueue(-10);
        queue.enqueue(3);
        queue.enqueue(11);
        queue.enqueue(8);
        queue.dequeue();

    }
}