package com.dataStructures.nodePath;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.dataStructures.nodePath.BinaryTree.Node;

class BinaryTreeTest {

  BinaryTree<Integer> tree  = new BinaryTree<>() ;
  
  @Test
  void dfsTest() {
    
    
    Node<Integer> one = new Node<>(1);
    Node<Integer> two = new Node<>(2);
    Node<Integer> three = new Node<>(3);
    Node<Integer> four = new Node<>(4);
    Node<Integer> five = new Node<>(5); 
    Node<Integer> six = new Node<>(6);
    Node<Integer> seven = new Node<>(7);
    Node<Integer> eight = new Node<>(8);
    
    
    one.left = two;
    one.right = three;
    
    two.left = four;
    two.right = five;

    three.left = six;
    three.right = seven;
    
    
    tree.setRoot(one);
    
    
    
    String path = tree.findPath(seven);
    System.out.println("The path is "+path);
  }

}
