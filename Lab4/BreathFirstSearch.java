package io.axbergLabs;

/**
 * BreathFirstSearch.java // ID 1020 KTH
 * 
 * Code is mostly copied from princeton. Just the necessary methods have been implemented.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-10-07
 * @contact: taxberg@kth.se
 * 
 */
public class BreathFirstSearch {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public BreathFirstSearch(Graph G, int s) {
        marked = new boolean[G.getV()];
        distTo = new int[G.getV()];
        edgeTo = new int[G.getV()];
        validateVertex(s);
        bfs(G, s);

    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

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

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int getEdge(int i) {
        return edgeTo[i];
    }

    public static void main(String[] args) {

        Integer s;
        Integer f;

        StdOut.println("Please input the data file.");
        String file = StdIn.readLine();

        In in = new In(
                "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/"
                        + file);
        Graph G = new Graph(in);

        StdOut.println("Which vertex do you want to find?");
        f = StdIn.readInt();
        StdOut.println("From where?");
        s = StdIn.readInt();

        BreathFirstSearch search = new BreathFirstSearch(G, s);
        StdOut.println(search.pathTo(f));
    }
}
