## Lab 4 - E

**Programming assignments for grade E**:

**Part 1: Undirected graphs**

For this part you should assume that the edges defined by the vertex pairs are two-way.

1. Write a program based on DFS which can answer questions of the type: "Find the a path from X to Y" Which should result in a list of vertices traversed from X to Y if there is a path.

2. Change the program to use BFS.


**Part 2: Directed graphs**

For this part you should assume that the edges defined by the vertex pairs in the data base are one-way.

3. Write a program that can answer if there is a path between any to vertices.



## Lab 4 - Higher Grades

**Higher grade problem**

**Note:** You should have solved all grade E problems and successfully presented the problems if you have been selected at the seminar to be able to account this problem

Implement a program which allows the user to find the shortest path between two nodes in a graph possibly passing through a third node. I.e. the user should be able to ask questions like:*Which is the shortest path from A to B passing through C?* The program should output an ordered list of the nodes to traverse from A to B if such a path exists. If no such path exists then the program should output that no such path exists.Use [NYC.txt](https://algs4.cs.princeton.edu/44sp/NYC.txt) as input when not executing tests (in the case that the tests should be executed you may use another input). This is the undirected road network of New York City. The graph contains 264346 vertices and 733846 edges. It is connected, contains parallel edges, but no self-loops. The edge weights are travel times and are strictly positive. You should also calculate/show the time complexity of your algorithm.
