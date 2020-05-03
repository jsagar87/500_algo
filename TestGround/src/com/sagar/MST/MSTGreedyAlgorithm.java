package com.sagar.MST;


/**
 * <p>
 * <b>Given.</b> <br /> Edge weighted (Connected) undirected graph G
 * </p>
 * <p>
 * <b>Def.</b> <br /> spanning tree T of undirected graph G is tree with weight and also spanning.
 * </p>
 * <p>
 *     <b>Goal.</b> <br /> Find spanning tree with minimum weight.
 * </p>
 *
 * Assumptions.
 *  <ul>
 *      <li>Edge weights are distinct</li>
 *      <li>Graph is connected</li>
 *  </ul>
 * Consequence of this assumption is MST exists and is unique.
 *
 * <p>
 *     <b>Def.</b> <br />
 *      A <i>cut</i> in a graph is partition of its vertices into two (nonempty) sets.
 * </p>
 *
 * General operation in a graph making a cut
 *
 * <p>
 *     <b>Def.</b> <br />
 *      A <i>crossing edge</i> connects a vertex in one set with a vertex in the other.
 * </p>
 * <p>
 *     <b>Cut property.</b> <br />
 *          Given any cut, the crossing edge of min weight is in the MST.
 * </p>
 */
public class MSTGreedyAlgorithm {
    public static void main(String[] args) {

    }
}
