package com.sagar.MST;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * How to represent in MST
 *
 */
public abstract class MST {


    /**
     * Edges in MST
     *
     * @return
     */
    abstract Iterable<Edge> edges();

    /**
     * Weight of MST
     * @return
     */
    abstract double weight();

}
