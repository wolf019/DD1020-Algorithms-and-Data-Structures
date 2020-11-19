package io.axbergLabs;

/**
 * AssignmentHG.java // ID 1020 KTH
 * 
 * Code implements classes DoubleEdgeWeightedDigraph.java, DijkstraSP.java and
 * MyQueue.java. Please see these files for documentation.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Implement a program which allows the user to find the shortest
 *              path between two nodes in a graph possibly passing through a
 *              third node. I.e. the user should be able to ask questions
 *              like:Which is the shortest path from A to B passing through
 *              C? The program should output an ordered list of the nodes to
 *              traverse from A to B if such a path exists. If no such path
 *              exists then the program should output that no such path
 *              exists.Use NYC.txt as input when not executing tests (in the
 *              case that the tests should be executed you may use another
 *              input). This is the undirected road network of New York City.
 *              The graph contains 264346 vertices and 733846 edges. It is
 *              connected, contains parallel edges, but no self-loops. The edge
 *              weights are travel times and are strictly positive. You should
 *              also calculate/show the time complexity of your algorithm.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-10-07
 * @contact: taxberg@kth.se
 * 
 */
public class AssignmentHG {
    public static void main(String[] args) {

        int st; // The start node
        int pass; // Node to pass
        int to; // End node
        boolean search = true;
        String val = "";

        StdOut.println("Please input the data file.");
        String file = StdIn.readLine(); // name of the file to be read.

        file = "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/"
                + file;
        In in = new In(file);
        DoubleEdgeWeightedDigraph G = new DoubleEdgeWeightedDigraph(in);

        while (search) {

            StdOut.println("\n\tWelcome to roadfinder!\n");

            StdOut.println("Where do you want to go?");
            to = StdIn.readInt();
            StdOut.println("\nFrom where?");
            st = StdIn.readInt();
            StdOut.println("\nWhere do you want to pass?");
            pass = StdIn.readInt();

            DijkstraSP sp = new DijkstraSP(G, pass);
            MyQueue<Integer> way = new MyQueue<Integer>(); // Queue to store path

            if (sp.hasPathTo(st) && sp.hasPathTo(to)) {
                StdOut.printf("\n%d to %d (%.2f)  ", st, pass, sp.distTo(st));
                for (DirectedEdge e : sp.pathTo(st)) {
                    way.InsertInFront(e.to());
                }

                StdOut.println();

                way.InsertFromBack(pass);
                StdOut.printf("%d to %d (%.2f)  \n", pass, to, sp.distTo(to));
                for (DirectedEdge e : sp.pathTo(to)) {
                    way.InsertFromBack(e.to());
                }
                StdOut.println();
                StdOut.println(way);
                StdOut.println("\n\tSearch completed.\nTo exit type y and enter, or n to make a new search.");
                val = StdIn.readString();
                if (val.compareTo("y") == 0) {
                    StdOut.println("Happy trip!");
                    search = false;
                }
            } else
                StdOut.println("Can´t find a path that satisfies the one you want. Please try again.");

        }
    }
}
