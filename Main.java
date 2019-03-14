import java.util.*;

/**
 * 210. Course Schedule II
 *
 * Directed graph
 */
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        int m[][] = { {1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int arr[] = s.findOrder(4, m);

        System.out.println("answer: " + Arrays.toString(arr));
    }
}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for(int[] pair : prerequisites) {
            graph.adj[pair[1]].add(pair[0]);
            graph.indegree[pair[0]]++;
        }

        BFS bfs = new BFS(graph.V);

        List<Integer> sl = new ArrayList<>();
        for (int s = 0; s < graph.V; s++) {
            if(graph.indegree[s] == 0) {
                sl.add(s);
            }
        }
        bfs.bfs(graph, sl);

        if(bfs.index == numCourses) {
            return bfs.topologicalOrder;
        }

        return new int[0];
    }
}

class BFS {
    public boolean[] marked;
    public int[] topologicalOrder;
    public int index = 0;

    public BFS(int V) {
        marked = new boolean[V];
        topologicalOrder = new int[V];
    }

    public void bfs(Graph G, List<Integer> sl) {
        Deque<Integer> queue = new LinkedList<>();
        for(Integer s : sl){
            queue.add(s);
        }

        while(!queue.isEmpty()) {
            int v = queue.pop();
            topologicalOrder[index++]=v;

            for(Integer w: G.adj[v]){
                if(!marked[w]) {
                    G.indegree[w]--;
                    if(G.indegree[w] == 0) {
                        queue.add(w);
                        marked[w] = true;
                    }
                }
            }
        }
    }
}

class Graph {
    public final int V;
    public List<Integer>[] adj;
    public int indegree[];

    public Graph(int V) {
        this.V = V;
        adj = (ArrayList<Integer>[]) new ArrayList[this.V];
        indegree = new int[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            indegree[i] = 0;
        }

    }
}
