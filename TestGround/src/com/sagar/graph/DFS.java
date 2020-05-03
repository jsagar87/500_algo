package com.sagar.graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

/**
 * Goal : Systematically search through a graph
 * Idea : Mimic maze exploration
 *
 * <br />
 *
 * Algorithm :
 *      DFS(To visit vertex v):
 *          Mark v as visited.
 *          Recursively visit all unmarked
 *              vertices w adjacent to v
 *
 * <br />
 * Typical application :
 *  -> Find all the vertices connected to given Source vertex.
 *  -> Find path between two vertices.
 *
 * Design Challenge : How to implement?
 *
 * Design Pattern for graph processing:
 * Design Pattern : Decouple graph data type from graph processing routine
 *  -> Create a graph object
 *  -> Pass the Graph to a graph-processing routine.
 *  -> Query the graph-processing routine for information.
 *
 *  There are hundreds of routines/algorithms were developed for processing of the graph. As an option
 *  we might put all of the those algorithms in one big data-type. That is so called FAT interface. And that
 *  would be bad plan because this things may be are not so very well
 *  related to each other, all iterating through the graph and doing different type of processing.
 *
 *  With this way we are going to separate out what the graph processing clients are doing. And the real
 *  application can be clients of these graph processing routines.
 *
 * <br />
 * @author Bhishma
 * @implNote
 * @implSpec This class puts sample code and comments being covered in lecture,
 * in order to understand it better.
 *
 */
public class DFS {

    /*
    Goal: Find All vertices connected to s
    Idea: Mimic maze exploration
    Algorithm:
        - Use recursion (ball of string)
        - Mark each visited vertex (and keep track of edge taken to visit it)
        - Return (retrace steps) when no unvisited options
     */

    private boolean[] marked;
    private int[] edgeTo;
    // s represents source vertex
    private int s;

    /**
     *
     * @param G - Graph object
     * @param s - Source vertex
     */
    public DFS(Graph G, int s){
        int size = G.V();
        this.marked = new boolean[size];
        this.edgeTo = new int[size];
        this.s = s;

        for(int i = 0; i<size; i++){
            marked[i] = false ;         // This means we mark all node from source for visited as false
            edgeTo[i] = 0;              // Stores vertex from where edge to this vertex is reachable
        }
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        System.out.println(s + "->");
        // once we reach node, mark that node
        marked[s] = true;
        // Source is first marked by it self and the other recursive call to adjacent
        for(Integer adjacent : G.adj(s)){
            if (!marked[adjacent]){
                dfs(G, adjacent);
                edgeTo[adjacent] = s;
            }
        }

    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<>();

        while ( v != this.s ) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(this.s);
        return path;
    }

    public void printTable(){
        System.out.println("  Sn  |  marked  |  edge-to  ");
        for(int i = 0;i<marked.length; i++){
            System.out.println(i + " | " + marked[i]+ " | " + edgeTo[i]);
        }
    }


    /**
     * Below is the client code that reads/loads the graph from
     *      Standard input file.
     *      Provided in the form :
     *
     *       13   // First line number of edges E
     *       13   // Second line number of vertices
     *       1 4  // 3rd line onwards up to next E lines as edges
     *       5 6
     *       :
     *       3 4
     *      Running with argument
     *      13 13 0 5 4 3 0 1 9 12 6 4 5 4 0 2 11 12 9 10 0 6 7 8 9 11 5 3
     *
     *      e.g.
     *      java --class-path C:\Dev\Computing\out\production\Computing;C:\Dev\Computing\lib\algs4.jar com.sagar.graph.DFS 13 13 0 5 4 3 0 1 9 12 6 4 5 4 0 2 11 12 9 10 0 6 7 8 9 11 5 3
     *
     *      Output:
     *      Path for destination : 3
     * 0
     * 6
     * 4
     * 5
     * 3
     * Is there a path from source 0 to  0 true
     * Is there a path from source 0 to  1 true
     * Is there a path from source 0 to  2 true
     * Is there a path from source 0 to  3 true
     * Is there a path from source 0 to  4 true
     * Is there a path from source 0 to  5 true
     * Is there a path from source 0 to  6 true
     * Is there a path from source 0 to  7 false
     * Is there a path from source 0 to  8 false
     * Is there a path from source 0 to  9 false
     * Is there a path from source 0 to  10 false
     * Is there a path from source 0 to  11 false
     * Is there a path from source 0 to  12 false
     */
    public static void main(String[] args) {
        int edges = Integer.parseInt(args[0]);
        int vertices = Integer.parseInt(args[1]);

        Graph G = new Graph(vertices);
        int argV = 0;
        int argW = 1;
        for(int i = 0; i < edges; i++) {
            argV = argV + 2;
            argW = argW + 2;
            int v = Integer.parseInt(args[argV]);
            int w = Integer.parseInt(args[argW]);
            System.out.println("v " + v + " w " + w);
            G.addEdge(v, w);
        }

        // DFS for Source 0, destination 3
        // try changing destinations and source to experiment
        int Source = 0;
        int Destination = 3 ;

        DFS sdfs = new DFS(G, Source);
        System.out.println("Is source " + Source + " and destination " + Destination + " connected " + sdfs.hasPathTo(Destination));
        sdfs.printTable();
        System.out.println("\n ========================================== \n");

        Iterator<Integer> paths = sdfs.pathTo(Destination).iterator();

        System.out.println("Path for destination : " + Destination);
        while ( paths.hasNext()) {
            System.out.println(paths.next());
        }

        // For each vertex in graph G
        for (int v = 0; v<G.V(); v++){
            System.out.println("Is there a path from source " + Source + " to  " + v + " " + sdfs.hasPathTo(v));
        }
    }
}
