package io.axbergLabs;

/**
 * DepthFirstSearch.java // ID 1020 KTH
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
public class DepthFirstSearch {

    private boolean[] marked;
    public Integer[] pathTo;
    private int count; // connected vertices 
    public Stack<Integer> path;

    /**
     * Method for setting up frame for executing dfg method. Creates a boolean array
     * and checks if param s is a valid vertex.
     * 
     * @param G: the graph to be searched.
     * @param s: the integer to start from.
     */
    public DepthFirstSearch(Graph G, int s, int f) {
        marked = new boolean[G.getV()];
        pathTo = new Integer[G.getV()]; // boolean array for marking vertices as visited.
        path = new Stack<Integer>();

        validateVertex(s);
        dfs(G, s); // parameters are valid. Now run dfs.

        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }

        fp(G, s, f);
        // StdOut.println(path);
    }

    // depth first search from v
    void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                pathTo[w] = v;
                dfs(G, w);
            }
        }
    }

    void fp(Graph G, int v, int w) {

        marked[v] = true;
        path.push(v);
        for (int r : G.adj(v)) {
            if (!marked[r]) {
                if (r != w) {
                    fp(G, r, w);
                } else {
                    path.push(w);
                    return;
                }
                return;
            } else if (!pathIncludes(r))
                path.pop();
        }
        return;
    }

    public boolean pathIncludes(int r) {
        boolean includes = false;
        for (Integer in : path)
            if (in.equals(r))
                includes = true;
        return includes;
    }

    /**
     * Validates that the parameter v is a valid vertex.
     * 
     * @param v: vertex value
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * returns boolean of if the vertex is visited or not.
     * 
     * @param v
     * @return boolean if v is visited or not.
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 
     * @return the amount of connected vertices.
     */
    public int connectedVertices() {
        return count;
    }

    public Integer pathTo(Integer f) {
        return pathTo[f.intValue()];
    }

    public static void main(String[] args) {

        In in = new In(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/tinyG.txt");
        Graph G = new Graph(in);
        int s = 0;
        int f = 4;
        DepthFirstSearch search = new DepthFirstSearch(G, s, f);
        for (int v = 0; v < G.getV(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.connectedVertices() != G.getV())
            StdOut.println("NOT connected");
        else
            StdOut.println("connected");
    }

}
