// Binary tree pre-order traversal using DC
// DC有return value， 每次的局部变量
// traverse 没有return value， list是全局变量
//eg: 1 2 3 4 5 null null
public ArrayList<Integer> preorderTraversal(TreeNode root){
  ArrayList<Integer> result = new ArrayList<>();
  
  //null or leaf
  if (root == null){
    return result;
  }
  
  //Divide
  ArrayList<Integer> left = preorderTraversal(root.left);  //[2,4,5]
  ArrayList<Integer> right = preorderTraversal(root.right);  //[3]
  
  //Conquer (Merge)
  result.add(root.val);
  result.addAll(left);
  result.addAll(right);
  
  return result;  //[1]+[2, 4, 5]+[3]
  }
}
