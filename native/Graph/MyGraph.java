import edu.princeton.cs.algs4.*;
import java.util.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebEndpoint;
/**
 * This graph is adjacency list representation for Graph
 * Space Complexity of Graph with V number of vertex and 
 * E number of Edges is E + V.
 */
@WebService(serviceName = "MyGraphservice",  targetNamespace = "http://MyGraphservice")
public class MyGraph{
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    public MyGraph(){
                this.V = 10;
                this.E = 100;
                this.adj = (Bag<Integer>[]) new Bag[V];
    }
    public MyGraph(In in){
        this.V = 10;
        this.E = 100;
    }
    public MyGraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v<V; v++){
            adj[v] = new Bag<Integer>();
        }
    }

    @WebEndpoint(name = "addEdge")
    public static double addEdge(double a, double b){
        return a+b;
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
    }

    // public Iterable<Integer> adj(int v){
    //     return adj[v];
    // }
    // Number of vertex
    public int V(){
        return V;
    }
    // Number of edges
    public int E(){
        return E;
    }
 
    public static void main(String[] args){
        StdOut.println("HEllo");

        In in = new In(args[0]);
        Graph G = new Graph(in);   // read graph from the input stream

        // Lets say we just want to process graph
        // and for inllustration we will jut print out
        for (int v = 0; v<G.V(); v++)
            for(int w : G.adj(v))
                StdOut.println(v + "-" + "w");
    }
}