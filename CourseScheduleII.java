package com.amazon.oa;

import java.util.*;

/**
 * Directed graph
 *
 * 210. Course Schedule II
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        SolutionCourseScheduleII s = new SolutionCourseScheduleII();

        int m[][] = { {1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int arr[] = s.findOrder(4, m);

        System.out.println("answer: " + Arrays.toString(arr));
    }
}

class SolutionCourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        DiGraph graph = new DiGraph(numCourses);
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

    public void bfs(DiGraph G, List<Integer> sl) {
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

class DiGraph {
    public final int V;
    public List<Integer>[] adj;
    public int indegree[];

    public DiGraph(int V) {
        this.V = V;
        adj = (ArrayList<Integer>[]) new ArrayList[this.V];
        indegree = new int[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            indegree[i] = 0;
        }

    }
}
