//BFS O(n+m), Space: O(n+m)
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += bfs(v, graph, visited);
        return components;
    }
    int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src]) return 0;
        visited[src] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        return 1;
    }
}


//BFS, 判断从一点出发能否到其它点
//valid tree
public boolean validTree(int n, int[][] edges) {
    if (n == 0) return false;
    if (edges.length != n - 1) return false;

    Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> nodeSet = new HashSet<>();

    queue.offer(0);
    nodeSet.add(0);

    while (!queue.isEmpty()){
        int node = queue.poll();

        for (int neighbor : graph.get(node)) {
            if (nodeSet.contains(neighbor)) continue;
            queue.offer(neighbor);
            nodeSet.add(neighbor);
        }
    }

    return nodeSet.size() == n;
}
