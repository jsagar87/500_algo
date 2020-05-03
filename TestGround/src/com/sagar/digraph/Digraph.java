package com.sagar.digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Digraph API
 *
 */
public class Digraph {

    private final String NEWLINE = System.lineSeparator();
    private final int V;                    // Number of vertex
    private int E;
    private final Bag<Integer>[] adj;       // Adjacency List
    private final int[] indegree;
    /**
     * Create a digraph from input stream
     *
     * @param in
     */
    public Digraph(In in){
        this.V = in.readInt();
        if (this.V < 0 ) throw new IllegalArgumentException("Number of vertices cannot be negative number");
        adj = (Bag<Integer>[]) new Bag[V];
        indegree = new int[V];
        for (int v = 0; v < this.V; v++)
            adj[v] = new Bag<Integer>();
        this.E = in.readInt();
        if (this.E < 0 ) throw new IllegalArgumentException("Number of edges cannot be negative number");
        for (int i = 0; i < this.E; i++) {
            addEdge(in.readInt(),in.readInt());
        }

    }

    /**
     * Create an empty digraph with V vertices
     *
     * @param V
     */
    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        indegree = new int[V];
        for (int v = 0; v<V ; v++)
            adj[v] = new Bag<Integer>();
    }

    /**
     * Add the directed edge v->W
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        // Below line from Graph is removed since this is Directional graph
        // adj[w].add(v);
        this.indegree[w]++;
        this.E ++;
    }

    /**
     *
     * @param v
     * @return
     */
    public int indegree(int v) {
        return indegree[v];
    }

    /**
     *
     * @param v
     * @return
     */
    public int outdegree(int v) {
        return adj[v].size();
    }

    /**
     * Vertices pointing from v
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
            return adj[v];
    }

    /**
     * Number of vertices
     *
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * Number of edges
     *
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * Reverse of this digraph
     *
     * @return
     */
    public  Digraph reverse() {
        Digraph reverse = new Digraph(this.V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * String representation
     * @return
     */
    public  String toString() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (Bag<Integer> B : adj) {
            builder.append(count++).append(" ");
            for (Integer I : B) {
                builder.append(I)
                        .append(" ");
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        // Read digraph from input stream
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        System.out.println(G.toString());
        // print out each edge (once)
//        for (int v = 0; v < G.V(); v++) {
//            for (int w : G.adj()) {
//                StdOut.println(v + "->" + w);
//            }
//        }
    }
}
