package com.sagar.MST;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class PrimsMSTLazy extends MST {
    private boolean marked[] ;
    private MinPQ<Edge> PQ;
    private Queue<Edge> MST;

    public PrimsMSTLazy(EdgeWeightedGraph G) {
        marked = new boolean[G.V()] ;
        PQ = new MinPQ<>();
        MST = new Queue<>();

        // Put 0 on tree and incident to 0 on PQ
        visit(G, 0);

        while ( !PQ.isEmpty() ) {
            // PQ with delMin ensures we always process smallest in the remaining and
            Edge E = PQ.delMin() ;
            int v = E.either() , w = E.other(v) ;


            if ( marked[v] && marked[w]) continue ;
            // continue meaning both the vertices on tree, meaning edge is already added,
            // and we will not add it again
            MST.enqueue(E);
            // whichever edge is not marked is not on tree, so we will visit it, which means
            // put it on tree and also add all its incident edges (which are not to PQ
            if ( !marked[v] ) visit(G, v);
            else if ( !marked[w] ) visit(G, w); marked[w] = true;
        }


    }
    private void visit(EdgeWeightedGraph G, int v) {
        //
        marked[v] = true;
        for ( Edge E : G.adj(v) ) {
            int w = E.other(v);
            if ( !marked[w] ) PQ.insert(E);
        }
    }
    @Override
    Iterable<Edge> edges() {
        return MST;
    }
    @Override
    public double weight(){
        double weight = 0.0d;
        for (Edge w : MST) {
            weight += w.weight();
        }
        return weight;
    }
    // 8 16 4 5 0.35 4 7 0.37 5 7 0.28 0 7 0.16 1 5 0.32 0 4 0.38 2 3 0.17 1 7 0.19 0 2 0.26 1 2 0.36 1 3 0.29 2 7 0.34 6 2 0.40 3 6 0.52 6 0 0.58 6 4 0.93
    public static void main(String[] args) {
        In in =  new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimsMSTLazy mst = new PrimsMSTLazy(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%2f\n", mst.weight());
    }
}
