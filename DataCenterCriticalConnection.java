package org.algs4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataCenterCriticalConnection {

    public static void main(String[] args) {

        // Expected: 3-4
//        int serversNum = 4;
//        int connectionsNum = 4;
//        int[][] connections = {{1, 2}, {1, 3}, {3, 2}, {3, 4}};

        //Expected: {}
        int serversNum = 8;
        int connectionsNum = 7;
        int[][] connections = {{1, 2},{2, 3},{4, 3},{4, 5},{1, 6},{2, 6},{5, 6}};

        //Expected: {} Output: {{1, 2},{2, 3},{4, 3},{4, 5},{1, 6},{2, 6},{5, 6}}

        SolutionDataCenterCriticalConnection sl = new SolutionDataCenterCriticalConnection();
        List<List<Integer>> list = sl.findCriticalConn(serversNum, connectionsNum, connections);

        System.out.println("Critical connections: ");
        list.forEach(i-> System.out.println(Arrays.toString(i.toArray())));
    }

}

class SolutionDataCenterCriticalConnection {

    public List<List<Integer>> findCriticalConn(int serversNum, int connectionsNum, int[][] connections) {

        List<List<Integer>> result = new ArrayList<>();

        //check if input is valid
        Graph originG = new Graph(serversNum + 1);
        for (int[] edge: connections) {
            if (edge.length >= 2) {
                originG.addEdge(edge[0], edge[1]);
            }
        }

        // not all nodes are connected initially, then return {}
        if ( !connected(originG)) {
            return result;
        }

            //There is a 'connections.length' Graphs
        for (int counter = 0; counter<connections.length; ++counter) {

            //create the Graph without one edge
            Graph g = new Graph(serversNum + 1);
            for (int j = 0; j<connections.length; ++j) {
                //skip one edge
                if(j == counter)
                    continue;

                if (connections[j].length >= 2) {
                    g.addEdge(connections[j][0], connections[j][1]);
                }
            }

            //check connections without one edge
            if ( !connected(g)) {
                int[] criticalConnection = connections[counter];
                List edge = new ArrayList();

                edge.add(criticalConnection[0]);
                edge.add(criticalConnection[1]);

                result.add(edge);
            }

        }

        return result;
    }

    public boolean connected(Graph g) {
        CC cc = new CC(g);
        if ( (cc.count - 1) == 1 ) {
            return true;
        }

        return false;
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

