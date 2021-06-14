//DFS, Time: O(n+m), Space: O(n+m)
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
        for (int v = 0; v < n; v++) components += dfs(v, graph, visited);
        return components;
    }
    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u]) return 0;
        visited[u] = true;
        for (int v : graph[u]) dfs(v, graph, visited);
        return 1;
    }
}

--------------------------------------------
//    edge is defined in  isConnected matrix
        public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int cc = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]) {
                cc++;
                dfs(isConnected, visited, i);
            }
        }
        
        return cc;
    }
    
    private void dfs(int[][] isConnected, boolean[] visited, int i){
        visited[i] = true;
        int n = isConnected.length;
        for (int j = 0; j < n; j++){
            if (isConnected[i][j] == 1) {
                if (!visited[j]){
                    dfs(isConnected, visited, j);
                }
            }
        }
    }
