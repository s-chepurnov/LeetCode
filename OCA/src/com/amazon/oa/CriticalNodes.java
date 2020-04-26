package com.amazon.oa;

import java.util.*;

/**
 * Given an undirected graph, find out all the vertices when removed will make the graph disconnected.
 * Initially the graph is connected.
 *
 * It is a brute force solution
 */

public class CriticalNodes {

    public static void main(String[] args) {

        /*
        // Expected [2,3,5]
        int nodeNum = 7;
        int[][] graph = {{0,1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3,4}};
        */

        int nodeNum = 8;
        int[][] graph = {{0, 1},{1, 2},{3, 4},{4, 6},{5, 6},{6, 7}};
        //Expected: [6, 4, 1]
        //Output: [0, 1, 2, 3, 4, 6, 5, 7]

        SolutionCN solutionCN = new SolutionCN();
        int[] result = solutionCN.findCriticalNodes(nodeNum, graph);

        System.out.println(Arrays.toString(result));
    }

}

class SolutionCN {

    public int[] findCriticalNodes(int nodeNum, int[][] graph) {

        int ccInitial = checkConnection(nodeNum, graph);

        List<Integer> answer = new ArrayList<>();

        //find all unique vertices
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < graph.length; ++i) {
            for (int j = 0; j < graph[0].length; ++j) {

                if (!vertices.contains(graph[i][j])) {
                    vertices.add(graph[i][j]);
                }

            }
        }

//        System.out.print("unique vertices: ");
//        vertices.forEach(System.out::print);
//        System.out.println("");

        for (Integer v : vertices) {

            //convert Array to List
            List<List<Integer>> edgeList = new ArrayList<>();
            for (int i = 0; i < graph.length; ++i) {
                ArrayList<Integer> vw = new ArrayList<>();
                for (int j = 0; j < graph[0].length; ++j) {
                    vw.add(graph[i][j]);
                }
                edgeList.add(vw);
            }

//            System.out.println("copy origin input");
//            edgeList.forEach(System.out::print);
//            System.out.println("");

            //Remove 1 Vertice from Input graph
            Iterator iterator = edgeList.iterator();
            while (iterator.hasNext()) {
                List<Integer> edge = (List<Integer>) iterator.next();

                if (edge.contains(v)) {
                    iterator.remove();
                }
            }

//            System.out.println("remove one (" + v + ") from input:");
//            edgeList.forEach(System.out::print);
//            System.out.println("");

            //Convert List to Array
            int[][] edgesWithoutOneVertice = new int[edgeList.size()][2];
            for (int x = 0; x < edgeList.size(); x++) {
                List<Integer> edge = edgeList.get(x);
                edgesWithoutOneVertice[x][0] = edge.get(0);
                edgesWithoutOneVertice[x][1] = edge.get(1);
            }
            //System.out.println("without: ");
            //System.out.println(Arrays.toString(without));

            //Check connection
            int quantityOfNodes = vertices.size() > 0 ? (vertices.size()) : 0;
            int ccAfterRemoveOneVertice = checkConnection(quantityOfNodes, edgesWithoutOneVertice);
            if (ccAfterRemoveOneVertice > (ccInitial+1)) {
                answer.add(v);
            }

        }

        return answer.stream().sorted(Comparator.reverseOrder()).mapToInt(i->i).toArray();
    }

    public int checkConnection(int nodeNum, int[][] edges) {

        Graph g = new Graph(nodeNum);

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        CC cc = new CC(g);
        return cc.count;
    }
}

/*
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

    public boolean[] marked;
    public int[] id;
    public int[] size;
    public int count;

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
*/