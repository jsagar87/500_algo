package com.sagar.MST;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collection;

/**
 *  Edge-weighted graph: adjacency-lists implementation
 *
 * <p>
 *     <b>Convention.</b> Allow self loop and parallel edges
 * </p>
 */

public class EdgeWeightedGraph {

    private final String NEWLINE = System.lineSeparator();

    private int V;                       // Number of vertex
    private int E;
    private final Bag<Edge>[] adj;       // Adjacency List of edges

    /**
     * Create an empty digraph with V vertices
     *
     * @param V
     */
    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    /**
     * Create a Edge Weighted graph from input stream
     *
     * @param in
     */
    public EdgeWeightedGraph(In in){
        this.V = in.readInt();
//        StdOut.println(V);
        if (this.V < 0 ) throw new IllegalArgumentException("Number of vertices cannot be negative number");
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < this.V; v++)
            adj[v] = new Bag<Edge>();
        int E = in.readInt();
//        StdOut.println(E);
        if (E < 0 ) throw new IllegalArgumentException("Number of edges cannot be negative number");
        for (int i = 0; i < E-2 ; i++) {
            int v = in.readInt();
            int w = in.readInt();
//            StdOut.println(E + " ("+i+")"+v + " " + w);
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }
    }

    /**
     * Add the weighted edge v->W to both adjacency list
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v) ;
        adj[v].add(edge);
        adj[w].add(edge);
        this.E ++;
    }

    /**
     * Edges incident to v
     *
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     *  All edge in this graph
     *
     * @return
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
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
     * String representation
     * @return
     */
    @Override
    public  String toString() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (Bag<Edge> B : adj) {
            builder.append(count++).append(" ");
            for (Edge E : B) {
                builder.append(E.either())
                        .append(" - ")
                        .append(E.other(E.either()))
                        .append(" ");
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        // Read digraph from input stream
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        System.out.println(G.toString());
        // print out each edge (once)
//        for (int v = 0; v < G.V(); v++) {
//            for (int w : G.adj()) {
//                StdOut.println(v + "->" + w);
//            }
//        }
    }
}
