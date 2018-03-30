package com.dataStructures.avlTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;

	public AVLTree(Comparator<T> c) {
		comparator = c;
	}

	private static class Node<T> {

		private T data;
		private Node<T> leftChild;
		private Node<T> rightChild;
		private int height;
		private Node<T> parent;
		// add the parent

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public Node<T> getParent() {
			return parent;
		}

		public void setParent(Node<T> parent) {
			this.parent = parent;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node<T> leftChild) {
			this.leftChild = leftChild;
		}

		public Node<T> getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node<T> rightChild) {
			this.rightChild = rightChild;
		}
	}

	public void insert(T data) {

		root = avlInsert(root, data);
	}

	private Node<T> avlInsert(Node<T> node, T data) {
		if (node == null) {
			return getNewNode(data);
		}
		if (comparator.compare(node.getData(), data) == 1) {
			node.leftChild = avlInsert(node.leftChild, data);
		} else {
			node.rightChild = avlInsert(node.rightChild, data);
		}

		int balance = height(node.leftChild) - height(node.rightChild);
		if (balance > 1) {
			if (height(node.leftChild.leftChild) >= height(node.leftChild.rightChild)) {
				return rightRotate(node);
			} else {
				node.leftChild = leftRotate(node.leftChild);
				return rightRotate(node);
			}
		}
		if (balance < -1) {
			if (height(node.rightChild.leftChild) < height(node.rightChild.rightChild)) {
				return leftRotate(node);
			} else {
				node.rightChild = rightRotate(node.rightChild);
				return leftRotate(node);
			}
		}
		node.height = max(height(node.leftChild), height(node.rightChild)) + 1;
		
		return node;
	}

	public void search(T data) {
		if (root == null) {
			System.out.println("Empty Tree");
			return;
		}
		Node<T> node = root;
		while (node != null) {
			if (node.getData().equals(data)) {
				System.out.println(data + " is found ");
				return;
			}
			else if (comparator.compare(node.getData(), data) == 1) {
				node = node.leftChild;
			} else {
				node = node.rightChild;
			}
		}
		System.out.println(data + " not found");
	}

	public void printTree() {
		Node<T> node = root;
		if (node == null) {
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		q.offer(node);
		q.offer(null);
		while (!q.isEmpty()) {
			node = q.poll();
			if (node != null) {
				System.out.print(node.data + "(" + node.height + ") ");
				if (node.leftChild != null) {
					q.offer(node.leftChild);
				}
				if (node.rightChild != null) {
					q.offer(node.rightChild);
				}
			} else {
				if (!q.isEmpty()) {
					System.out.println();
					q.offer(null);
				}
			}
		}
		System.out.println();
	}

	public void delete(T data) {
		root = delete(root, data);
	}

	private Node<T> delete(Node<T> node, T data) {
		if (node == null) {
			return node;
		}
		if (node.getData().equals(data)) {
			if (node.leftChild == null && node.rightChild == null) {
				return null;
			} else if (node.leftChild == null && node.rightChild != null) {
				return node.rightChild;
			} else if (node.leftChild != null && node.rightChild == null) {
				return node.leftChild;
			} else {
				Node<T> temp = inorderSuccessor(node.rightChild);
				node.rightChild = delete(node.rightChild, temp.getData());
				node.setData(temp.getData());
				return node;
			}

		}
		else if (comparator.compare(node.getData(), data) == 1) {
			node.leftChild = delete(node.leftChild, data);
		} else {
			node.rightChild = delete(node.rightChild, data);
		}

		node.height = max(height(node.leftChild), height(node.rightChild)) + 1;

		// balancing after node deletion
		int balance = height(node.leftChild) - height(node.rightChild);
		if (balance > 1) {
			if (height(node.leftChild.leftChild) >= height(node.leftChild.rightChild)) {
				// LL case
				return rightRotate(node);
			} else {
				// LR case
				node.leftChild = leftRotate(node.leftChild);
				return rightRotate(node);
			}
		} else if (balance < -1) {
			if (height(node.rightChild.rightChild) >= height(node.rightChild.leftChild)) {
				// RR case
				return leftRotate(node);
			} else {
				// RL case
				node.rightChild = rightRotate(node.rightChild);
				return leftRotate(node);

			}
		}

		return node;
	}

	private Node<T> inorderSuccessor(Node<T> node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}

	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	private int max(int a, int b) {
		return (a > b) ? a : b;
	}

	private Node<T> getNewNode(T data) {

		Node<T> node = new Node<>();
		node.setData(data);
		node.height = 1;
		return node;
	}

	private Node<T> rightRotate(Node<T> node) {
		Node<T> newRoot = node.leftChild;
		node.leftChild = newRoot.rightChild;
		newRoot.rightChild = node;
		node.height = max(height(node.leftChild), height(node.rightChild)) + 1;
		newRoot.height = max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
		return newRoot;
	}

	private Node<T> leftRotate(Node<T> node) {
		Node<T> newRoot = node.rightChild;
		node.rightChild = newRoot.leftChild;
		newRoot.leftChild = node;
		node.height = max(height(node.leftChild), height(node.rightChild)) + 1;
		newRoot.height = max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
		return newRoot;
	}
}
