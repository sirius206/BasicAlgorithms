//PreOrder



//In Order

void printTree(struct node* node) { 
  if (node == NULL) return; 

  printTree(node->left); 
  printf("%d ", node->data); 
  printTree(node->right); 
} 

//Post Order
void printPostorder(struct node* node) { 
  if (node == NULL) return; 

  // first recur on both subtrees 
  printTree(node->left); 
  printTree(node->right); 

  // then deal with the node 
  printf("%d ", node->data); 
} 


