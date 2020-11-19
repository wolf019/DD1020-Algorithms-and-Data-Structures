package io.axbergLabs;

/**
 * The {@code BreadthDirectedFirstPaths} class represents a data type for
 * finding shortest paths (number of edges) from a source vertex <em>s</em> (or
 * set of source vertices) to every other vertex in the digraph.
 * <p>
 * This implementation uses breadth-first search. The constructor takes
 * &Theta;(<em>V</em> + <em>E</em>) time in the worst case, where <em>V</em> is
 * the number of vertices and <em>E</em> is the number of edges. Each instance
 * method takes &Theta;(1) time. It uses &Theta;(<em>V</em>) extra space (not
 * including the digraph).
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BreathFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s->v path?
    private int[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo; // distTo[v] = length of shortest s->v path

    public BreathFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.getV()];
        distTo = new int[G.getV()];
        edgeTo = new int[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = INFINITY;
        validateVertex(s);
        bfs(G, s);
    }

    public BreathFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.getV()];
        distTo = new int[G.getV()];
        edgeTo = new int[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = INFINITY;
        validateVertices(sources);
        bfs(G, sources);
    }

    // BFS from single source
    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) { // adjacent to v
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    // BFS from multiple sources
    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path from the source {@code s} (or
     * sources) to vertex {@code v}?
     * 
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);

        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        for (Integer v : vertices) {
            if (v == null) {
                throw new IllegalArgumentException("vertex is null");
            }
            validateVertex(v);
        }
    }

    /**
     * Unit tests the {@code BreadthFirstDirectedPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        StringDigraph sd = new StringDigraph(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/contiguous-usa.txt",
                " ");
        Digraph G = sd.digraph();
        // StdOut.println(G);

        int s = 7;
        BreathFirstDirectedPaths bfs = new BreathFirstDirectedPaths(G, s);

        for (int v = 0; v < G.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%s to %s (%d):  ", sd.nameOf(s), sd.nameOf(v), bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s)
                        StdOut.print(sd.nameOf(x));
                    else
                        StdOut.print("->" + sd.nameOf(x));
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%s to %s (-):  not connected\n", sd.nameOf(s), sd.nameOf(v));
            }

        }
    }

}