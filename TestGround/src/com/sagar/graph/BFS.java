package com.sagar.graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

/**
 * Goal : Systematically search through a graph
 * Idea : Mimic maze exploration
 *
 * <br />
 *
 * Algorithm :
 *     BFS
 *      Repeat until Queue is empty
 *          - Remove vertex v from queue
 *          - Add to queue all unmarked vertices adjacent to v and mark them
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
public class BFS {

    private int[] distTo;
    private int[] edgeTo;
    // s represents source vertex
    private int s;

    /**
     *
     * @param G - Graph object
     * @param s - Source vertex
     */
    public BFS(Graph G, int s){
        int size = G.V();
        this.distTo = new int[size];
        this.edgeTo = new int[size];
        this.s = s;

        for(int i = 0; i<size; i++){
            distTo[i] = -1 ;         // This means we mark all node from source for visited as false
//            edgeTo[i] = -1;           // Stores vertex from where edge to this vertex is reachable
        }
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> processQ = new Queue<>();
        int dist = 0;

        // Add source and distance for source
        processQ.enqueue(s);
        distTo[s] = dist;

        //  Now for all adjacent
        while (!processQ.isEmpty()) {
            int next = processQ.dequeue();
            dist = dist + 1;
            for (int i : G.adj(next)) {
                if (distTo[i]==-1) {    // if distTo is -1 then it means it is not marked
                    distTo[i] = dist;
                    edgeTo[i] = next;
                    processQ.enqueue(i);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return distTo[v] != -1;
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> path = new Stack<>();

        if (!hasPathTo(v))
            return path;

        while ( v != this.s ) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(this.s);
        return path;
    }

    public void printTable(){
        System.out.println("  Sn  |  edgeTo  |  markedTo  ");
        for(int i = 0;i< edgeTo.length; i++){
            System.out.println(i + " | " + edgeTo[i]+ " | " + distTo[i]);
        }
    }


    /**
     * Below is the client code that reads/loads the graph from
     *      Standard input file.
     *      Provided in the form :
     *
     *       6   // First line number of edges E
     *       8   // Second line number of vertices
     *       0 5  // 3rd line onwards up to next E lines as edges
     *       2 4
     *       :
     *       0 2
     *      Running with argument
     *      6 8 0 5 2 4 2 3 1 2 0 1 3 4 3 5 0 2
     *
     *      e.g.
     *      java --class-path C:\Dev\Computing\out\production\Computing;C:\Dev\Computing\lib\algs4.jar com.sagar.graph.BFS 6 8 0 5 2 4 2 3 1 2 0 1 3 4 3 5 0 2
     *
     *      Output:
     */
    public static void main(String[] args) {
        // Loading the graphs from argument
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
        // try changing destinations and source to experimen
        int Source = 7;
        int Destination = 3 ;

        BFS bfs = new BFS(G, Source);
        System.out.println("Is source " + Source + " and destination " + Destination + " connected " + bfs.hasPathTo(Destination));
        bfs.printTable();
        System.out.println("\n ========================================== \n");

        Iterator<Integer> paths = bfs.pathTo(Destination).iterator();

        System.out.println("Path for destination : " + Destination);
        while ( paths.hasNext()) {
            System.out.println(paths.next());
        }

        // For each vertex in graph G
        for (int v = 0; v<G.V(); v++){
            System.out.println("Is there a path from source " + Source + " to  " + v + " " + bfs.hasPathTo(v));
        }
    }
}

// 4+2^4+2^7=128+16+4=148
// 10101010
// 01010101
// 11111111
