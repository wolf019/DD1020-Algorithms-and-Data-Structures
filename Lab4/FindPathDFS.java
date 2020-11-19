package io.axbergLabs;

public class FindPathDFS {

    private boolean[] marked;
    private int count;
    private Queue<Integer> path;

    /**
     * Method for setting up frame for executing fap method. Creates a boolean array
     * and checks if params s and f is a valid vertex.
     * 
     * @param G: the graph to be searched.
     * @param s: the integer to start from.
     * @param f: the integer to find.
     */
    public FindPathDFS(Graph G, int s, int f) {
        marked = new boolean[G.getV()]; // boolean array for marking vertices as visited.
        validateVertex(s);
        validateVertex(f);
        path = new Queue<Integer>();
        fp(G, s, f);
        StdOut.println(path);
    }

    void fp(Graph G, int v, int w) {

        marked[v] = true;
        path.enqueue(v);
        for (int r : G.adj(v)) {
            if (!marked[r]) {
                if (r != w) {
                    fp(G, r, w);
                } else {
                    path.enqueue(w);
                    return;
                }
                return;
            } else if (!path.includes(r))
                path.dequeue();
        }
        return;
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

    public static void main(String[] args) {

        In in = new In(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/tinyG.txt");
        Graph G = new Graph(in);
        int s = 0;
        int f = 1;
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
