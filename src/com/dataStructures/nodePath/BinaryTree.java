package com.dataStructures.nodePath;

import java.util.LinkedList;

public class BinaryTree<T> {


  private Node root;

  public void setRoot(Node root) {
    this.root = root;
  }

  public Node getRoot() {
    return root;
  }

  static class Node<T> {
    Node<T> left;
    Node<T> right;
    T data;

    public Node(T data) {
      // TODO Auto-generated constructor stub
      this.data = data;
    }
  }


  public String findPath(Node<T> data) {

    LinkedList<Node> pathStack = new LinkedList<>();
    LinkedList<Node> dfsStack = new LinkedList<>();

    dfsStack.push(root);


//    while (!dfsStack.isEmpty()) {
//      Node poppedNode = dfsStack.pop();
//      System.out.print(poppedNode.data + " ");
//
//      if (poppedNode.right != null && poppedNode.left != null) {
//
//        dfsStack.push(poppedNode.right);
//        dfsStack.push(poppedNode.left);
//      }
//      else if(poppedNode.right != null)
//      {
//        dfsStack.push(poppedNode.right);
//      }
//      else if(poppedNode.left != null)
//      {
//        dfsStack.push(poppedNode.left);
//      }
//
//    }


    
    
    
    
    StringBuilder stringBuilder = new StringBuilder();
    int res = tracePath(root, data, pathStack);
    if(res == 1)
    {
      while(!pathStack.isEmpty())
      {
//        System.out.println(pathStack.pop().data);
        stringBuilder.append(pathStack.pop().data);
      }
    }
    else
    {
      System.out.println("Element not found");
    }
    

    return stringBuilder.reverse().toString();
  }

  
  private int tracePath(Node<T> node, Node<T> data , LinkedList<Node> stack)
  {
    int found = 1, notFound = 0;
    
    
    
    stack.push( node);
    if(node.data == data.data)
    {
      return found;
    }
    
    if(node.left == null && node.right == null)
    {
      stack.pop();
      return notFound;
    }
    
    int left = tracePath(node.left, data, stack);
    if(left == found) {
      return found;
    }
   
    int right = tracePath(node.right, data, stack);
    if(right == found)
    {
      return found;
    }
    
    stack.pop();
    
    
    
    return notFound;
    
    
    
    
  }


}

