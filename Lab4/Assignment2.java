package io.axbergLabs;

/**
 * Assignment2.java // ID 1020 KTH
 * 
 * 
 * Code implements classes StringGraph.java, BreathFirstSearch.java and
 * MyQueue.java. Please see these files for documentation.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Write a program based on BFS which can answer questions of the
 *              type: "Find the a path from X to Y" Which should result in a
 *              list of vertices traversed from X to Y if there is a path.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-10-07
 * @contact: taxberg@kth.se
 * 
 */
public class Assignment2 {
    public static void main(String[] args) {
        int s; // The start node
        int f; // End node

        StdOut.println("Please input the data file.");
        String file = StdIn.readLine(); // name of the file to be read.

        file = "/Users/tomaxberg/Documents/School/KTH/ID1020 Algorithms and data structures/Code/Graphs/Lab4/data/"
                + file;
        StringGraph sg = new StringGraph(file, " ");

        StdOut.println("Which vertex do you want to find?");
        f = sg.indexOf(StdIn.readString().toUpperCase());
        StdOut.println("From where?");
        s = sg.indexOf(StdIn.readString().toUpperCase());
        MyQueue<String> path = new MyQueue<String>();// Queue to store path

        BreathFirstSearch search = new BreathFirstSearch(sg.graph(), s);

        for (int w : search.pathTo(f)) {
            path.InsertFromBack(sg.nameOf(w));
        }
        StdOut.println(path);
    }
}
