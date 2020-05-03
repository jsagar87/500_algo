package com.sagar.MST;

public class Edge implements Comparable<Edge>{

    private final int v, w;
    private final double weight;

    Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    double weight() {
        return weight;
    }
    int either()
    {
        return v;
    }
    int other(int vertex)
    {
        if (this.v == vertex) return this.w;
        else return this.v;
    }
    @Override
    public int compareTo(Edge that)
    {
        if      (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else                                return  0;
    }
    @Override
    public String toString() {
        return v + " - " + w;
    }
//    // Alternate implementation
//    @Override
//    public int compareTo(Edge that){
//        return Double.compare(this.weight, that.weight);
//    }
}
