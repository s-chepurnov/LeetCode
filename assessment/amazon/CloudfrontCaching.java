package com.amazon;

import java.util.ArrayList;
import java.util.List;

public class CloudfrontCaching {

    public static void main(String[] args) {

//        int n = 10;
//        int[][] edges = {{1,2}, {1,3}, {2,4}, {3,5}, {7,8}};
//        8

        int n = 8;
        int edges[][] = {{8,1}, {5,8}, {7,3}, {8,6}};
        //6

        SolutionCloudfrontCaching solution = new SolutionCloudfrontCaching();
        int sum = solution.solve(n, edges);

        System.out.println("# sum: " + sum);
    }
}

class SolutionCloudfrontCaching {
    public int solve(int n, int[][] edges) {

        if(n==0 || edges == null || edges.length == 0 || edges[0].length == 0)
            return 0;

        Graph_Cashing G = new Graph_Cashing(n+1);

        //init graph
        for(int[] edge : edges) {

            int v = edge[0];
            int w =  edge[1];
            G.addEdge(v,w);
        }

        //cc
        CC_Cashing cc = new CC_Cashing(G);

        int sum = 0;

        for(int size : cc.size) {
            if(size == 0)
                continue;

            sum+=Math.ceil(Math.sqrt(size));
        }

        return sum-1; //because size of G is n+1 to avoid arrayoutofbounds
    }
}

class Graph_Cashing {

    private static final String NEWLINE = System.getProperty("line.separator");
    public final int V;
    public int E;
    public List<Integer>[] adj;

    public Graph_Cashing(int N) {
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


class CC_Cashing {

    public boolean[] marked;
    public int[] id;
    public int[] size;
    public int count;

    public CC_Cashing(Graph_Cashing G) {
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

    private void dfs(Graph_Cashing G, int v) {
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