package com.amazon;

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
