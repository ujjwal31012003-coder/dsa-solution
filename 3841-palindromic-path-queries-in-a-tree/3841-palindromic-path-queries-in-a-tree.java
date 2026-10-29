import java.util.*;

class Solution {
    private int[][] up;
    private int[] depth;
    private int[] tin;
    private int[] tout;
    private int[] parent;
    private int[] order;
    private int LOG;

    private List<Integer>[] graph;
    private Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        order = new int[n];

        buildTree(n);

        bit = new Fenwick(n + 1);

        char[] chars = s.toCharArray();

        // Add each node's character mask to its subtree.
        for (int u = 0; u < n; u++) {
            int mask = 1 << (chars[u] - 'a');
            rangeXor(tin[u], tout[u], mask);
        }

        List<Boolean> answers = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) {
                    continue;
                }

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                int delta = oldMask ^ newMask;

                rangeXor(tin[u], tout[u], delta);

                chars[u] = c;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = bit.query(tin[u]);
                int maskV = bit.query(tin[v]);

                int ancestorMask =
                        1 << (chars[ancestor] - 'a');

                int pathMask =
                        maskU ^ maskV ^ ancestorMask;

                // A palindrome is possible if at most
                // one character has an odd frequency.
                boolean possible =
                        (pathMask & (pathMask - 1)) == 0;

                answers.add(possible);
            }
        }

        return answers;
    }

    private void buildTree(int n) {
        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        int timer = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            up[0][u] = parent[u];

            for (int k = 1; k < LOG; k++) {
                up[k][u] = up[k - 1][up[k - 1][u]];
            }

            for (int v : graph[u]) {
                if (parent[v] != -1) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];

        for (int k = LOG - 1; k >= 0; k--) {
            if ((difference & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }

    private void rangeXor(int left, int right, int value) {
        bit.update(left, value);
        bit.update(right + 1, value);
    }

    private static class Fenwick {
        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            index++;

            while (index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        int query(int index) {
            index++;

            int result = 0;

            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }

            return result;
        }
    }
}