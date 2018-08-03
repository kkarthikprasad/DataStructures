package com.dataStructures.heaps;

import java.util.LinkedList;

public class MinHeap {

  private Node root = null, nextNewNodeParent = null;

  private static class Node {
    int data;
    Node parent = null, leftChild = null, rightChild = null;

    Node(int data) {
      this.data = data;
    }
  }


  public void insert(int data) {
    Node newNode = new Node(data);
    if (root == null) {
      root = newNode;
      nextNewNodeParent = root;
      return;
    }


    if (nextNewNodeParent.leftChild == null) {
      nextNewNodeParent.leftChild = newNode;
      newNode.parent = nextNewNodeParent;
    } else {
      nextNewNodeParent.rightChild = newNode;
      newNode.parent = nextNewNodeParent;

      // reassigning the next place node
      // traverse up and check if thats the last node in that level or else go up and go to the
      // right child and then traverse left left.. and assing the nextNewplacenode
      nextNewNodeParent = findNextNewNodeParent(nextNewNodeParent);
    }

    // heapify
    heapify_without_recursion(newNode);



  }

  public void delete(int data) {


    // TODO should reheapify after deleting the data
  }


  public int extractMin() {
    Node node = root;
    // FIXME - insert the logic to reheapify after deleting the root element
    return node.data;
  }


  public void printHeap() {
    LinkedList<Node> queue = new LinkedList<>();
    queue.offer(root);
    queue.offer(null);
    while (!queue.isEmpty()) {
      Node poppedNode = queue.poll();
      if (poppedNode == null) {
        if (!queue.isEmpty()) {
          queue.offer(null);
        }
        System.out.println();
        continue;
      }

      if (poppedNode.leftChild != null) {
        queue.offer(poppedNode.leftChild);
      }
      if (poppedNode.rightChild != null) {
        queue.offer(poppedNode.rightChild);
      }


      System.out.print(poppedNode.data + " ");
    }
  }

  private Node heapify_recursively(Node node) {


    // FIXME - return correct value
    return null;
  }

  private void heapify_without_recursion(Node node) {


    while (node.parent != null) {
      if (node.data < node.parent.data) {
        int temp = node.data;
        node.data = node.parent.data;
        node.parent.data = temp;
      }



      node = node.parent;
    }


  }


  private Node findNextNewNodeParent(Node node) {

    while (node.parent != null) {
      if (node.parent.leftChild == node) {
        // if its a left child
        node = node.parent.rightChild;
        break;
      }
      node = node.parent;
    }

    // if node is a subtree
    if (node.parent != null) {
      // there was a right child in the parent node
      while (node.leftChild != null) {
        node = node.leftChild;
      }
      // return that node
      return node;
    }

    // if node.parent == null .. node is the root
    if (node.parent == null) {
      if (node.leftChild == node) {
        node = node.rightChild;
        while (node.leftChild != null) {
          node = node.leftChild;
        }
        // return that node
        return node;
      }

      else {
        // if the node was right child of the root
        while (node.leftChild != null) {
          node = node.leftChild;
        }
      }
    }


    return node;
  }



}
