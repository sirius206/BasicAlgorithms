//level order traversal
//1. only print, one while loop, use queue
public void printLeverOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while (!q.isEmpty){
      TreeNode node = queue.poll();
      System.out.println(node.val + " ");
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }      
    }
}


//2. print each level, while loop + for loop, use queue
public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> res = new ArrayList<>();
  if (root == null) {return res;}
  Queue<TreeNode> queue = new LinkedList<>();
  queue.offer(root);
  
  while (!q.isEmpty()){
    List<Integer> currentLevel = new ArrayList<>();
    int size = queue.size();
    for (int i = 0; i < size; i++){
      TreeNode node = queue.poll();
      currentLevel.add(node.val);
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
    }  
      res.add(currentLevel);
  }
    return res;
}
