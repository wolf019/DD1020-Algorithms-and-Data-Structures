package io.axbergLabs;

import java.util.NoSuchElementException;

// import edu.princeton.cs.algs4.*;
/*
 * Graph.java // ID 1020 KTH
 * 
 * 
 * Graph is a undirected graph based on Robert Widgewick and Kevin Waynes class Graph. 
 * 
 * 
 * What it has been based upon ? Based upon FIFO queue from chapter 1.3 in
 * course book Algorithms by Robert Sedgewich and Robert Wayne.s
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.2
 * @since 2020-08-29
 * @contact: taxberg@kth.se
 * 
 */

public class Graph {

    private final int V; // Number of vertices
    private int E; // Number of Edges
    private Queue<Integer>[] adj; // List of circular Queues which will hold all the edges from the vertex
    // specified by the index of the adj array.

    /**
     * constructor for Graph. Creates a new graph with V vertices.
     * 
     * @throws IllegalArgumentException: Throws exception if v is negative.
     * @param V: Number of vertices in Graph.
     */
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = new Queue[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Queue<Integer>();
        }
    }

    /**
     * constructor for Graph. Creates a new graph with V vertices and E edges where
     * V and E is integers defined in the beginning of the document in respective
     * order.
     * 
     * @throws IllegalArgumentException: Throws exception if v is negative.
     * @param in: a file which includes pair vertices. ex. "A B" \n "A C" \n a.s.o.
     */
    @SuppressWarnings("unchecked")
    public Graph(In in) {
        if (in == null)
            throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt(); // first integer of the document.
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = new Queue[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Queue<Integer>();
            }
            int E = in.readInt(); // second integer of the document.
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Initializes a new graph that is a deep copy of parameter G
     *
     * @param G: the graph to copy
     * @throws IllegalArgumentException if parameter G is null
     */
    @SuppressWarnings("unchecked")
    public Graph(Graph G) {
        this.V = G.getV();
        this.E = G.getE();
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices must be nonnegative");

        // update adjacency lists
        adj = new Queue[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Queue<Integer>();
        }

        for (int v = 0; v < G.getV(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].enqueue(w);
            }
        }
    }

    /**
     * method for number of vertices
     * 
     * @return integer of amount of vertices
     */
    public int getV() {
        return V;
    }

    /**
     * method for number of edges
     * 
     * @return integer of amount of edges
     */
    public int getE() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].enqueue(w);
        adj[w].enqueue(v);
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges
     *         <em>E</em>, followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n\n" + V + " vertices, " + E + " edges \n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Object w : adj[v]) {
                s.append(w + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}. adj is an array of queues.
     * Queue has iterator method, which is returned.
     *
     * @param v: the vertex v
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public static void main(String[] args) {
        In in = new In(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/test18.txt");
        Graph G = new Graph(in);
        System.out.println(G);
    }
}
