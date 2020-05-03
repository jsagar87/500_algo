package com.sagar.graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

/**
 * We're going to look at a slightly different graph processing application. It's a graph processing algorithm that's
 * useful in many applications and slightly different than, depth and breadth first search. It's
 * called computing connected components.
 *<br />
 * <p></p>
 * Idea is that if there's a path between two vertices we say they're connected. And what we want to do is preprocess
 * the graph that is to build a data type that can answer queries of the form, <i> "is V connected to W" </i> in
 * constant time. Now, we want to be able to do that for a huge, sparse graph of the type that appears in practice.
 * If we could use the adjacency matrix data structure, maybe we could do that but we can't. So we're going to build
 * a class that uses our standard representation adjacency list, that will enable clients to find connective components.
 * It's really interesting to think about this one. We're getting the job done that we could get done if we get a huge,
 * sparse matrix. But if we have billions of vertices, there's no way we can have billions squared in the matrix. So we
 * have to find another way to do it. So here's the data type that we want to implement.
 *<br />
 * <p>
 * Connectivity queries
 * ====================
 * </p>
 * <br />
 *
 * <b>Def.</b> Vertices V and W are <i> Connected </i> if there is a path between them.
 *
 * <b>Goal.</b> Preprocess the graph to answer queries of the form "is v connected to w" in constant time.
 *
 * <p> Can we Use Union-Find? No. Is Connected cannot be answer in constant time</p>
 * <p> Can we Use Depth first Search? Yes. </p>
 *
 * <br />
 * <p>
 * Connected Components
 * ====================
 * </p>
 * The relation "is connected to" is an <b>equivalence relation:</b>
 * <ul>
 *     <li>Reflexive: v is connected to v</li>
 *     <li>Symmetric: if v is connected to w, then w is connected to v</li>
 *     <li>Transitive: if v is connected to w and w is connected to x, then v connected to x</li>
 * </ul>
 *
 * <p>
 *     <b>Def.</b> A <b><i>connected component</i></b> is a maximal set of vertices.
 * </p>
 * <p>
 *     <b>Remark.</b> Given connected component can answer queries in constant time.
 * </p>
 */
public class ConnectedComponents {

    private boolean[] marked;
    private int[] id;           // id[v] = id of component containing v
    private int count;          // Number of components

    /**
     * Find Connected components in G
     *
     * @param G - Graph object
     */
    public ConnectedComponents(Graph G) {
        int size = G.V();
        this.marked = new boolean[size];
        this.id = new int[size];

        /*
            run DFS from one vertex in
            each component
         */
        for (int v = 0; v<size; v++) {
            if (!marked[v]) {
                dfs(G, v);
                count ++;
            }
        }
    }

    private void dfs(Graph G, int s) {
        System.out.println(s + "->");
        // once we reach node, mark that node
        marked[s] = true;
        id[s] = count;
        // Source is first marked by it self and the other recursive call to adjacent
        for(Integer adjacent : G.adj(s)){
            if (!marked[adjacent]){
                dfs(G, adjacent);

//                edgeTo[adjacent] = s;
            }
        }

    }

    /**
     * Are v and w connected?
     *
     * @param v
     * @param w
     * @return
     */
    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * Number of connected components
     *
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * Component identifier for v
     *
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

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

        ConnectedComponents cc = new ConnectedComponents(G);

        System.out.println(" Total number of components in the graph are : " + cc.count());
        System.out.println("is 0 and 6 connected? "+ cc.connected(0, 6));
        System.out.println("is 0 and 8 connected? "+ cc.connected(0, 8));



    }
}
