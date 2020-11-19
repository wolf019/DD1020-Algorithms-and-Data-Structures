package io.axbergLabs;

/**
 * StringGraph.java // ID 1020 KTH
 * 
 * Code is mostly copied from princeton. Just the necessary methods have been
 * implemented.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-10-07
 * @contact: taxberg@kth.se
 * 
 */

public class StringGraph {

    private BST<String, Integer> st; // string -> index
    private String[] keys; // index -> string
    private Graph graph; // the underlying graph

    /**
     * Initializes a graph from a file using the specified delimiter. Each line in
     * the file contains the name of a vertex, followed by a list of the names of
     * the vertices adjacent to that vertex, separated by the delimiter.
     * 
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     */
    public StringGraph(String filename, String delimiter) {
        st = new BST<String, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);

        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) 
                    st.put(a[i], st.size()); // If symbol table does not contain a[i], we add it and give it the index of the next none used index. 
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name; // Name on the index of node nr.
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     * 
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false}
     *         otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * 
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex
     *         named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * 
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph. It is the client's
     * responsibility not to mutate the graph.
     * 
     * @return the graph associated with the symbol graph
     */
    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.getV();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename = "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/contiguous-usa.txt";
        String delimiter = " ";
        StringGraph sg = new StringGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.nameOf(v));
                }
            } else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
