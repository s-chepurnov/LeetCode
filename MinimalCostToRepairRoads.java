package org.algs4;

import java.util.*;

/**
 * Using a set of roads, broken roads and repair costs,
 *write an algorithm to calculate the minimum total cost to repair some of the roads so that
 *all the cities are once again accessible from each other.
 *
 *Input: The input consists of five arguments:
 *numTotalAvailableCities: an integer representing the total number of cities (eg: if N = 3, then cities are represented as 1,2,3).
 *numTotalAvailableRoads: an integer representing the total number of roads.
 *roadsAvailable: a list of integers where each element of the list consists of a pair representing
 *                the cities directly connected by a road.
 *numRoadsToBeRepaired: an integer representing the total number of roads that are unusable.
 *costRoadsToBeRepaired: a list of integers where each element of the list consists  of
 *                       triplet representing the pair of cities between which a road is currently
 *                       unusable and the cost of repairing that road, respectively
 *                       (e.g. [1,3,10] means to repair a road between cities 1 and 3 the cost would be 10).
 *
 *Output: Return an integer representing the minimum total cost to repair some roads so that all
 *the cities are accessible from each other.
 *
 * Additional information about data structures: https://algs4.cs.princeton.edu/43mst/ used in this example
 */
public class MinimalCostToRepairRoads {

    public static void main(String[] args) {


        //Expected: 20
        int numTotalAvailableRoads = 5;
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int[][] edgesToRepair = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};

        //Expected: 410
//        int numTotalAvailableRoads = 6;
//        int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
//        int[][] edgesToRepair = {{1, 6, 410}, {2, 4, 800}};

        //Expected: 79
//        int numTotalAvailableRoads = 6;
//        int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
//        int[][] edgesToRepair = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};

        //Expected: 22
//        int numTotalAvailableRoads = 8;
//        int[][] edges = {{1, 2},{4, 5},{5, 7},{6, 7},{7, 8}};
//        int[][] edgesToRepair = {{2, 4, 100},{2, 4, 16},{2, 3, 7},{2, 5, 15},{3, 8, 17}};

        //Expected: -1
//        int numTotalAvailableRoads = 6;
//        int[][] edges = {};
//        int[][] edgesToRepair = {{1, 4, 100},{2, 4, 10},{2, 3, 7},{2, 5, 15},{2, 1, 17},{5, 3, 1}};

        //Expected: 0
//        int numTotalAvailableRoads = 8;
//        int[][] edges = {{1, 2}, {2, 3}, {4, 3}, {4, 5}, {5, 7}, {6, 7}, {7, 8}};
//        int[][] edgesToRepair = {{3, 8, 1}, {4, 7, 9}};

        //Expected: -1
//        int numTotalAvailableRoads = 1;
//        int[][] edges = {};
//        int[][] edgesToRepair = {};

        SolutionMinCostToRepairRoads sl = new SolutionMinCostToRepairRoads();
        int minCost = sl.minCostToRepairEdges(numTotalAvailableRoads, edges, edgesToRepair);

        System.out.println("minimal cost to repair roads and connect all cities: " + minCost);
    }

}

class SolutionMinCostToRepairRoads {

    public int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair) {

        if (edges == null || edgesToRepair == null || (edges.length == 0 && edgesToRepair.length == 0)) {
            return -1;
        }

        //node 0 exists only for convenience, but the code ignore it
        EdgeWeightedGraph g = new EdgeWeightedGraph(n + 1);

        //init Graph e.g.
//        g.addEdge(new Edge(1,2,12));
//        g.addEdge(new Edge(2,3,0));
//        g.addEdge(new Edge(3,4,30));
//        g.addEdge(new Edge(4,5,0));
//        g.addEdge(new Edge(5,1,8));

        //connect roads, init Graph
        for (int[] edge : edges) {
            g.addEdge(new Edge(edge[0], edge[1], 0));
        }

        //check Connected Components for this Graph with weights, if all connected (cc.count == 1) then return 0$ cost
        if (isNodesConnected(g, n)) {
            return 0;
        }

        //set weight for some edges
        for (int[] edge : edgesToRepair) {
            g.addEdge(new Edge(edge[0], edge[1], edge[2]));
        }

        UF uf = new UF(g.V);

        //no need to repair, already connected
        //uf.union(2,3);
        //uf.union(4,5);

        KruskalMST mst = new KruskalMST(g, uf);

        //print graph
        //for (Edge e : mst.mst) {
        //    System.out.println(" " + e);
        //}

        // number of components when all cities are connected
        // 2 because we use node0 in the code that doesn't count, so 2 cc means 1 cc.
        if (uf.count == 2) {
            return mst.weight;
        } else {
            return -1;
        }

    }

    public boolean isNodesConnected(EdgeWeightedGraph G, int n) {

        boolean[] marked = new boolean[n+1];

        //mark unexisted element; node0 is only for convenience
        marked[0] = true;
        dfs(marked, 1, G);

        for (int i = 0; i < n; ++i) {
            if ( !marked[i]) {
                return false;
            }
        }

        return true;
    }

    public void dfs(boolean[] marked, int v, EdgeWeightedGraph G) {
        marked[v] = true;
        for (Edge edge : G.adj(v)) {
            if (!marked[edge.w]) {
                dfs(marked, edge.w, G);
            }
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

            //3 connect p-q in UF if edge p-q does not create a cycle
            if ( !uf.connected(p,q)) {
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
