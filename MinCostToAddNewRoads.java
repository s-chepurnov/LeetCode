package org.algs4;

import java.util.*;

public class MinCostToAddNewRoads {

    public static void main(String[] args) {

        //Expected: 6
//        int N = 3;
//        int[][] connections = {{1,2,5}, {1,3,6}, {2,3,1}};

        //Expected: -1
//        int N = 4;
//        int[][] connections = {{1,2,3},{3,4,4}};


        //Expected 0
//        int N = 0;
//        int[][] connections = null;

        // Expected: 216
        int N = 9;
        int[][] connections = {{0, 1, 12},{0, 2, 100},{0, 3, 100},{0, 4, 100},{0, 5, 100},{0, 6, 100},{0, 7, 100},{0, 8, 25},{1, 2, 10},{1, 3, 100},{1, 4, 100},{1, 5, 100},{1, 6, 100},{1, 7, 40},{1, 8, 8},{2, 3, 18},{2, 4, 100},{2, 5, 100},{2, 6, 55},{2, 7, 100},{2, 8, 100},{3, 4, 44},{3, 5, 100},{3, 6, 100},{3, 7, 100},{3, 8, 100},{4, 5, 60},{4, 6, 38},{4, 7, 100},{4, 8, 100},{5, 6, 100},{5, 7, 100},{5, 8, 100},{6, 7, 35},{6, 8, 100},{7, 8, 35}};

        //Expected: -1
//        int N = 2;
//        int[][] connections = {};


        SolutionMinCostToAddNewRoads sl = new SolutionMinCostToAddNewRoads();
        int minCost = sl.minCostConnectNodes(N, connections);

        System.out.println("minimal cost to connect all cities: " + minCost);
    }

}

class SolutionMinCostToAddNewRoads {

    public int minCostConnectNodes(int n, int[][] connects) {
        if (connects == null)
            return 0;

        if (n == 0 && connects.length == 0)
            return 0;

        EdgeWeightedGraph g = new EdgeWeightedGraph(n);

        //init Graph
        for (int[] edge : connects) {
            if (connects.length >= 3)
                g.addEdge(new Edge(edge[0], edge[1], edge[2]));
        }

        UF uf = new UF(g.V);
        KruskalMST mst = new KruskalMST(g, uf);

        // number of components is 1, it means that all cities are connected
        if (uf.count == 1) {
            return mst.weight;
        } else {
            return -1;
        }


    }
}

class Edge implements Comparable<Edge> {

    int v;
    int w;
    int weight;

    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int other) {
        if (other == v) return w;
        else if (other == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public int compareTo(Edge that) {
        if( this.weight > that.weight )
            return 1;
        else if ( this.weight < that.weight )
            return -1;
        else
            return 0;
    }

    public String toString() {
        return String.format("%d-%d %d", v, w, weight);
    }
}

class EdgeWeightedGraph {

    private static final String NEWLINE = System.getProperty("line.separator");
    public final int V;
    public int E;
    public List<Edge>[] adj;

    public EdgeWeightedGraph(int N) {
        this.V = N;
        this.E = 0;
        adj = (ArrayList<Edge>[]) new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<Edge>();
        }

    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);

        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public List<Edge> adj(int v) {
        return adj[v];
    }

    public List<Edge> edges() {
        List<Edge> list = new ArrayList<Edge>();

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

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}


class KruskalMST {

    public Queue<Edge> mst = new LinkedList<Edge>();  // edges in MST
    public int weight;                             // weight of MST
    public UF uf;

    public KruskalMST(EdgeWeightedGraph g, UF uf) {
        this.uf = uf;

        //1. Edges sorted by weight (asc, e.g. 1,2,3,4 ... n)
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(10, Comparator.naturalOrder());
        for (Edge e : g.edges()) {
            minHeap.add(e);
        }

        //2. connect edge only if it is not create a cycle
        while ( !minHeap.isEmpty() && mst.size() <= (g.V - 1) ) {
            Edge edge = minHeap.poll();

            int p = edge.either();
            int q = edge.other(p);

            if ( !uf.connected(p,q)) { //edge p-q does not create cycle
                uf.union(p,q);
                mst.offer(edge);
                weight += edge.weight;
            }
        }

    }
}

class UF {

    public int[] parent;  // parent[i] = parent of i
    public byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    public int count;     // number of components

    public UF (int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
}
