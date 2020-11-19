package io.axbergLabs;

/**
 * Assignment3.java // ID 1020 KTH
 * 
 * 
 * Code implements classes StringDigraph.java, BreadthFirstDirectedPaths.java
 * and MyQueue.java. Please see these files for documentation.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Write a program based on BFS which can answer questions of the
 *              type: "Find the a path from X to Y" Which should result in a
 *              list of vertices traversed from X to Y if there is a path.
 *              Assume that the edges defined by the vertex pairs in the data
 *              base are one-way.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-10-07
 * @contact: taxberg@kth.se
 * 
 */
public class Assignment3 {
    public static void main(String[] args) {
        int s; // The start node
        int f; // End node

        StdOut.println("Please input the data file.");
        String file = StdIn.readLine(); // name of the file to be read.

        file = "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/"
                + file;
        StringDigraph sd = new StringDigraph(file, " ");

        while (true) {
            StdOut.println("Which vertex do you want to find?");
            f = sd.indexOf(StdIn.readString().toUpperCase());
            StdOut.println("From where?");
            s = sd.indexOf(StdIn.readString().toUpperCase());
            if (s != -1 && f != -1) {
                Queue<String> path = new Queue<>();// Queue to store path
                BreathFirstDirectedPaths search = new BreathFirstDirectedPaths(sd.digraph(), s);

                try {
                    for (int w : search.pathTo(f))
                        path.enqueue(sd.nameOf(w));
                    StdOut.println(path);
                } catch (Exception e) {
                    StdOut.println("There is no path between " + sd.nameOf(s) + " and " + sd.nameOf(f));
                }
            } else
                StdOut.println("\n\tOne of the selected vertices does not exist. Please try again.\n");
        }
    }
}
