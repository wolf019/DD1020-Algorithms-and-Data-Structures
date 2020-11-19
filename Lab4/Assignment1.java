package io.axbergLabs;

/**
 * Assignment1.java // ID 1020 KTH
 * 
 * 
 * Code implements classes StringGraph.java, DepthFirstSearch.java and
 * Stack.java. Please see these files for documentation.
 * 
 * What problem does the code solve?
 * 
 * @Assignment: Write a program based on DFS which can answer questions of the
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
public class Assignment1 {

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

        DepthFirstSearch search = new DepthFirstSearch(sg.graph(), s, f);
        boolean found = false;
        Stack<String> path = new Stack<>(); // Stack to store path

        while (!found) {
            path.push(sg.nameOf(f));
            f = search.pathTo(f);
            if (search.pathTo(f) == null) {
                break;
            } else if (search.pathTo(f) == s) {
                path.push(sg.nameOf(f));
                path.push(sg.nameOf(s));
                found = true;
            }
        }
        if (found) {
            StdOut.println("Path found!");
            StdOut.print(path);
            StdOut.println('\n');
        }
        if (!found) {
            StdOut.println("No path exists.");
        }

    }
}
