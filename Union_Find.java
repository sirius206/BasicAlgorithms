
public class QuickUnionUF
{
  private int[] father;
  private int[] size; // size of each cc
  private int count;  // num of cc;
  public QuickUnionUF(int N)
  {
    father = new int[N];
    for (int i = 0; i < N; i++) {
      father[i] = i;
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
        if (x == father[x])  return x;
        return father[x] = find[father[x]]; 
    }
  
  
  public void union(int p, int q)
  {
    int root_p = find(p);
    int root_q = find(q);
    if (root_p != root_q) {  //important to check if equal, avoid cycle
      father[root_p] = root_q;
      count--;
      size[root_q】 += size[root_p];
  }
  }
}
