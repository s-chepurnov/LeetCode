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
 * The solution based on Kruskal algorithm
 * Illustartion about this solution: https://www.youtube.com/watch?v=Gi9KeUO4UTo&list=PLRdD1c6QbAqJn0606RlOR6T3yUqFWKwmX&index=68
 * Additional information about data structures from this example: https://algs4.cs.princeton.edu/43mst/ 
 */
public class MinimalCostToRepairRoads {

    public static void main(String[] args) {
        int numTotalAvailableCities = 5;
        int numTotalAvailableRoads = 5;
        int numRoadsToBeRepaired = 3;

        EdgeWeightedGraph g = new EdgeWeightedGraph(numTotalAvailableRoads + 1);
        g.addEdge(new Edge(1,2,12));
        g.addEdge(new Edge(2,3,0));
        g.addEdge(new Edge(3,4,30));
        g.addEdge(new Edge(4,5,0));
        g.addEdge(new Edge(5,1,8));

        int minCost = solve(g);
        System.out.println("minimal cost to repair roads and connect all cities: " + minCost);
    }

    public static int solve(EdgeWeightedGraph g) {

        UF uf = new UF(g.V);
        //no need to repair, already connected
        uf.union(2,3);
        uf.union(4,5);

        KruskalMST mst = new KruskalMST(g, uf);

        for (Edge e : mst.mst) {
            System.out.println(" " + e);
        }

        return mst.weight;
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
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(10, (o1, o2) -> {
            return o1.compareTo(o2);
        });

        for (Edge e : g.edges()) {
            minHeap.add(e);
        }

        while ( !minHeap.isEmpty() && mst.size() <= (g.V - 1) ) {
            Edge e = minHeap.poll();

            int p = e.either();
            int q = e.other(p);

            if( !uf.connected(p,q)) {
                uf.union(p,q);
                mst.offer(e);
                weight += e.weight;
            }
        }

    }
}

class UF {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

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
