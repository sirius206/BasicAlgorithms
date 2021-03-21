
public class QuickUnionUF
{
  private int[] parent;
  private int[] size; // size of each cc
  private int count;  // num of cc;
  public QuickUnionUF(int N)
  {
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
      size[i] = 1;
    }
    count = n;
  }
//  public int find(int i)
//  {
//    while (i != id[i])
//  {
//      id[i] = id[id[i]];
//      i = id[i];
//  }
//    return i;
//  }
    public int find(int x) {
        if (x == parent[x])  return x;
        return parent[x] = find[parent[x]]; 
    }
  
  
  public void union(int p, int q)
  {
    int root_p = find(p);
    int root_q = find(q);
    if (root_p != root_q) {  //important to check if equal, avoid cycle
      parent[root_p] = root_q;
      count--;
      size[root_q】 += size[root_p];
  }
  }
}
