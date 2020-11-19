package Java;

import java.util.Iterator;
import java.util.Scanner;

public class Lab3_2 {

    public static class Queue<Item> implements Iterable<Item> {

        public class Node {

            private Node next;
            private Item item;

            public Node() {

                this.item = null;
            }

            public Node(Item item, Node next) {

                this.next = next;
                this.item = item;
            }
        }

        public Node head = null;

        public void enqueue(Item item) {
            Node n = new Node(item, head);
            head = n;
        }

        public Item dequeue() {
            Node r = head;
            head = r.next;
            r.next = null;
            return r.item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        public class ListIterator implements Iterator<Item> {

            private Node n = head;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public Item next() {

                Item item = n.item;
                n = n.next;
                return item;
            }
        }
    }

    public static class BinarySearchST<Key extends Comparable<Key>, Value> {

        private Key[] keys;
        private Value[] vals;
        private int N = 0;
        private int arrayCapacity;

        @SuppressWarnings("unchecked")
        public BinarySearchST(int capacity) throws ClassCastException {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
            this.arrayCapacity = capacity;
        }

        public int size() {
            return N;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public Value get(Key key) {
            if (isEmpty())
                return null;
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0)
                return vals[i];
            else
                return null;
        }

        public int rank(Key key) {

            int lo = 0, hi = N - 1;

            while (lo <= hi) {

                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);

                if (cmp < 0)
                    hi = mid - 1;
                else if (cmp > 0)
                    lo = mid + 1;
                else
                    return mid;
            }
            return lo;
        }

        public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.

            int i = rank(key);

            if (i < N && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }
            for (int j = N; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
            N++;
        }

        public Key min() {
            return keys[0];
        }

        public Key max() {
            return keys[N - 1];
        }

        public Key select(int k) {
            return keys[k];
        }

        public Key ceiling(Key key) {
            int i = rank(key);
            return keys[i];
        }

        // public Key floor(Key key) {
        // // See Exercise 3.1.17.
        // }
        //
        // public Key delete(Key key) {
        // put(key, null);
        // }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public Iterable<Key> keys() {

            Key lo = keys[0];
            Key hi = keys[arrayCapacity - 1];

            Queue<Key> q = new Queue<Key>();

            for (int i = rank(lo); i < rank(hi); i++)
                q.enqueue(keys[i]);
            if (contains(hi))
                q.enqueue(keys[rank(hi)]);
            return q;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int minlen = 1;
        int i = 100;

        BinarySearchST<String, Integer> st = new BinarySearchST<>(100);

        while (i-- != 0) { // Build symbol table and count frequencies.
            String word = sc.next();
            if (word.length() < minlen)
                continue; // Ignore short keys. if (!st.contains(word)) st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }
        // Find a key with the highest frequency count.

        String max = "";
        st.put(max, 0);

        for (int j = 0; j < 100; j++)
            if (st.get(st.select(j)) > st.get(max))
                max = st.select(j);

        System.out.println(max + " " + st.get(max));
    }
}
