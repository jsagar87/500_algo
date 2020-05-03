package com.sagar.MST;

import edu.princeton.cs.algs4.*;

public class KruskalMST extends MST{
    private Queue<Edge> mst = new Queue<>();
    /**
     * Constructor
     *
     * @param G
     */
    public KruskalMST(EdgeWeightedGraph G)
    {
        // Considering Edges in ascending order,
        // Lets build Priority Queue for that
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V()-1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);   //  Greedily add the edge
            // If there is no cycle
            if (!uf.connected(v, w)) {
                uf.union(v, w);                    // Merge the sets
                mst.enqueue(e);                    // add edge to MST
            }
        }

    }

    /**
     * Edges in MST
     *
     * @return
     */
    @Override
    Iterable<Edge> edges()
    {
        return mst;
    }

    /**
     * Weight of MST
     * @return
     */
    @Override
    public double weight(){
        double weight = 0.0d;
        for (Edge w : mst) {
            weight += w.weight();
        }
        return weight;
    }

    // 8 16 4 5 0.35 4 7 0.37 5 7 0.28 0 7 0.16 1 5 0.32 0 4 0.38 2 3 0.17 1 7 0.19 0 2 0.26 1 2 0.36 1 3 0.29 2 7 0.34 6 2 0.40 3 6 0.52 6 0 0.58 6 4 0.93
    public static void main(String[] args) {
        In in =  new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%2f\n", mst.weight());
    }
}
