import java.util.*;

class Solution {

    static class State {
        int node;
        int count;
        int cost;

        State(int node, int count, int cost) {
            this.node = node;
            this.count = count;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build the directed graph.
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count] = minimum cost to reach this state.
        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.cost, b.cost)
        );

        // The starting node contributes its own character.
        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {

            State current = pq.poll();

            int node = current.node;
            int count = current.count;
            int cost = current.cost;

            // Ignore outdated states.
            if (cost != dist[node][count]) {
                continue;
            }

            // The first valid destination state removed has
            // the minimum possible cost.
            if (node == n - 1) {
                return cost;
            }

            for (int[] edge : graph[node]) {

                int nextNode = edge[0];
                int weight = edge[1];

                int nextCount;

                if (labels.charAt(node) == labels.charAt(nextNode)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // The path violates the consecutive-character limit.
                if (nextCount > k) {
                    continue;
                }

                int nextCost = cost + weight;

                if (nextCost < dist[nextNode][nextCount]) {

                    dist[nextNode][nextCount] = nextCost;

                    pq.offer(
                        new State(nextNode, nextCount, nextCost)
                    );
                }
            }
        }

        return -1;
    }
}