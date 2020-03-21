package org.algs4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * It is a brute force solution
 *
 * "Articulation point" method described below:
 * https://leetcode.com/discuss/interview-question/436073/
 *
 */
public class CriticalNodes {

    public static void main(String[] args) {

        //original input
        int nodeNum = 7;
        int[][] edges = {{0,1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3,4}};

        List<Integer> answer = new ArrayList<>();

        //find all unique vertices
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < edges.length; ++i) {
            for (int j = 0; j < edges[0].length; ++j) {

                if ( !vertices.contains(edges[i][j])) {
                    vertices.add(edges[i][j]);
                }

            }
        }

//        System.out.print("unique vertices: ");
//        vertices.forEach(System.out::print);
//        System.out.println("");

        for (Integer v : vertices) {

            //convert Array to List
            List<List<Integer>> edgeList = new ArrayList<>();
            for (int i = 0; i < edges.length; ++i) {
                ArrayList<Integer> vw = new ArrayList<>();
                for (int j = 0; j < edges[0].length; ++j) {
                    vw.add(edges[i][j]);
                }
                edgeList.add(vw);
            }

//            System.out.println("copy origin input");
//            edgeList.forEach(System.out::print);
//            System.out.println("");

            //Remove 1 Vertice from Input graph
            Iterator iterator = edgeList.iterator();
            while(iterator.hasNext()) {
                List<Integer> edge = (List<Integer>) iterator.next();

                if(edge.contains(v)) {
                    iterator.remove();
                }
            }

//            System.out.println("remove one (" + v + ") from input:");
//            edgeList.forEach(System.out::print);
//            System.out.println("");

            //Convert List to Array
            int[][] edgesWithoutOneVertice = new int[edgeList.size()][2];
            for(int x = 0; x < edgeList.size(); x++) {
                List<Integer> edge = edgeList.get(x);
                edgesWithoutOneVertice[x][0] = edge.get(0);
                edgesWithoutOneVertice[x][1] = edge.get(1);
            }
            //System.out.println("without: ");
            //System.out.println(Arrays.toString(without));

            //Check connection
            int quantityOfNodes = vertices.size() > 0 ? (vertices.size()) : 0;
            int n = solve(quantityOfNodes, edgesWithoutOneVertice);

            if (n > 2) {
                answer.add(v);
            }

            //System.out.println("# connected components: " + n);
        }


        System.out.println(Arrays.toString(answer.toArray()));
    }


    public static int solve(int nodeNum,  int[][] edges) {

        Graph g = new Graph(nodeNum);

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        CC cc = new CC(g);
        return cc.count;
    }

}


class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");
    public final int V;
    public int E;
    public List<Integer>[] adj;

    public Graph(int N) {
        this.V = N;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<Integer>();
        }

    }

    public void addEdge(int v, int w) {

        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public List<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

}

class CC {

    public boolean[] marked;   // marked[v] = has vertex v been marked?
    public int[] id;           // id[v] = id of connected component containing v
    public int[] size;         // size[id] = number of vertices in given component
    public int count;          // number of connected components

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
