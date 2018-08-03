package com.dataStructures.avlTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.sound.midi.Soundbank;

/**
 * 
 * @author PRASKAR
 *
 * @param <T>
 * 
 */
public class AVLTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;

	public AVLTree(Comparator<T> c) {
		comparator = c;
	}

	// for only mirror tree testing

	// private static class Node<T> {
	static class Node<T> {

		private T data;
		private Node<T> leftChild;
		private Node<T> rightChild;
		private int height;
		private Node<T> parent;

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

		// root = avlInsert(root, data);
		root = avlInsert_updated(root, data);

	}

	public Node mirrorTreesInsert(Node node) {
		if (root == null)
			root = node;

		return node;

	}

	private Node<T> avlInsert_updated(Node<T> root, T data) {
		Node<T> addedNode = getNewNode(data);
		if (root == null) {
			Node<T> newNode = addedNode;
			return newNode;
		}

		Node<T> node = root;
		Node<T> parentNode = node.parent;
		boolean toBeRightChild = false;

		while (node != null) {
			if (comparator.compare(node.getData(), data) == 1) {
				parentNode = node;
				node = node.getLeftChild();
				toBeRightChild = false;
			} else {
				parentNode = node;
				node = node.getRightChild();
				toBeRightChild = true;
			}
		}

		addedNode.parent = parentNode;
		if (toBeRightChild) {
			parentNode.rightChild = addedNode;
		} else {
			parentNode.leftChild = addedNode;
		}

		// updating the height
		node = addedNode;
		int updatedHeight = 1;
		while (node != null) {

			// height should be updated after balancing
			node.height = updatedHeight++;

			// balancing

			int balanceFactor = height(node.leftChild) - height(node.rightChild);

			// Left Case
			if (balanceFactor > 1) {
				if (height(node.leftChild.leftChild) > height(node.leftChild.rightChild)) {
					// LL case
					node = rightRotate(node);
					// to avoid the null pointer exception when rotated tree's root is the tree's
					// root node.. so node.parent will be null and null.leftnode will give
					// NullPointerException
					// the node.parent.leftchild or rightChild of parent is updated only when the
					// parent for that node is not null.. i.e, only when the parent node needs to
					// updated with the left and right child.
					if (node.parent != null) {
						node.parent.leftChild = node;
					}

				} else if (height(node.leftChild.leftChild) < height(node.leftChild.rightChild)) {
					// LR case
					node.leftChild = leftRotate(node.leftChild);
					node = rightRotate(node);
					if (node.parent != null) {
						node.parent.leftChild = node;
					}
				}
			}
			// Right Case
			else if (balanceFactor < -1) {
				if (height(node.rightChild.leftChild) > height(node.rightChild.rightChild)) {
					// RL case
					node.rightChild = rightRotate(node.rightChild);
					node = leftRotate(node);
					if (node.parent != null) {
						node.parent.rightChild = node;
					}
				} else if (height(node.rightChild.leftChild) < height(node.rightChild.rightChild)) {
					// RR case
					node = leftRotate(node);
					if (node.parent != null) {
						node.parent.rightChild = node;
					}

				}

			}

			node = node.parent;
		}

		// after balancing the height needs to updated
		node = addedNode;
		updatedHeight = 1;
		while (node != null) {
			node.height = updatedHeight++;
			node = node.parent;
		}

		// go to the root to find the changed root
		while (root.parent != null) {
			root = root.parent;
		}
		
		//correcting the all the node's  
		return root;

	}

	private Node<T> avlInsert(Node<T> node, T data) {
		if (node == null) {
			return getNewNode(data);
		} else if (comparator.compare(node.getData(), data) == 1) {
			node.leftChild = avlInsert(node.leftChild, data);
			node.leftChild.parent = node;
		} else {
			node.rightChild = avlInsert(node.rightChild, data);
			node.rightChild.parent = node;
		}

		// climb up the tree and then calculate the height

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
			} else if (comparator.compare(node.getData(), data) == 1) {
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

	
	private Node<T> delete_updated(Node<T> root , T data)
	{
		if(root.getData().equals(data) && root.leftChild == null && root.rightChild == null)
		{
			return null;
		}
		Node<T> node = root;
		boolean isElementFound = false;
		
		while(node != null)
		{
			if(comparator.compare(node.getData(), data)  == 0)
			{
				isElementFound = true;
				break;
			}
			else if ( comparator.compare(node.getData(),  data) > 0)
			{
				node = node.leftChild;
			}
			else
			{
				node = node.rightChild;
			}
		}
		
		if(!isElementFound)
		{
			System.out.println("Element " + data +" not found to delete");
			return root;
		}
		
		if(isLeafNode(node)) {
			// for deleting the leaf node
			if(node.parent.leftChild.getData().equals(data))
			{
				node.parent.leftChild = null;
			}
			else
			{
				node.parent.rightChild = null;
			}
			
			updateHeight(node.parent);
			node.parent = null;
		}
		else if ( node.leftChild != null && node.rightChild == null)
		{
			// if the node is the left child 
			if(node.parent.leftChild.getData().equals(data))
			{
				node.parent.leftChild = node.leftChild;
				node.leftChild.parent = node.parent;
			}
			else
			{
				node.parent.rightChild = node.leftChild;
				node.leftChild.parent = node.parent;
			}
			
			//FIXME the height of the upper nodes need to be updated
		}
		else if ( node.leftChild == null && node.rightChild != null)
		{
			// if the node is the left child 
			if(node.parent.leftChild.getData().equals(data))
			{
				node.parent.leftChild = node.rightChild;
				node.rightChild.parent = node.parent;
			}
			else
			{
				node.parent.rightChild = node.rightChild;
				node.rightChild.parent = node.parent;
			}
			//FIXME the height of the upper nodes need to be updated
		}
		else if ( node.leftChild != null && node.rightChild != null)
		{
			Node<T> replacingNode = inorderSuccessor(node);
			replacingNode.parent = node.parent;
			replacingNode.leftChild = node.leftChild;
			replacingNode.rightChild = node.rightChild;
			replacingNode.leftChild.parent = replacingNode;
			replacingNode.rightChild.parent = replacingNode;
			updateHeight(replacingNode);
		}
		
		
		// should balance on the node.
		
		//FIXME  return the updated root 
		
		return root;
		
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
				Node<T> temp = inorderSuccessor(node);
				node.rightChild = delete(node.rightChild, temp.getData());
				node.setData(temp.getData());
				return node;
			}

		} else if (comparator.compare(node.getData(), data) == 1) {
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
		node = node.rightChild;
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		if(node.parent.leftChild.equals(node))
		{
			node.parent.leftChild = null;
		}
		updateHeight(node.parent);
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
		newRoot.parent = node.parent;
		node.parent = newRoot;
		updateHeight(node);
		return newRoot;
	} 

	private Node<T> leftRotate(Node<T> node) {

		Node<T> newRoot = node.rightChild;
		node.rightChild = newRoot.leftChild;
		newRoot.leftChild = node;
//		node.height = 1;
//		newRoot.height = 2;
		newRoot.parent = node.parent;
		node.parent = newRoot;
		
		

		updateHeight(node);
		
		
		
		return newRoot;
	}

	
	private void updateHeight(Node nodeHeightBeingUpdated)
	{
		Node node = nodeHeightBeingUpdated;
		if(node == null )
		{
			return ;
		}
		// if the node is leaf node
		if(node.leftChild == null && node.rightChild == null)
		{
			node.height = 1;
			return;
		}
		
		int updatedHeight = 1;
		while(node != null)
		{
			// if reached the last node 
			if(node.leftChild == null && node.rightChild == null)
			{
				break;
			}
			if(height(node.leftChild) > height(node.rightChild))
			{
				node = node.leftChild;
			}
			else if(height(node.leftChild) < height(node.rightChild))
			{
				node = node.rightChild;
			}
			else
			{
				// if both children's height is same then choose any.. now going to rightChild
				node = node.rightChild;
			}
			
			updatedHeight++;
		}
		
		nodeHeightBeingUpdated.height = updatedHeight;
		
	}
	private  boolean isLeafNode(Node node)
	{
		if(node.leftChild == null && node.rightChild == null)
		{
			return true;
		}
		return false;
	}
	
	public Node<T> getRoot() {
		return root;
	}

	public static boolean areMirrorTrees(AVLTree t1, AVLTree t2) {
		Queue<Node> queue = new LinkedList<>();

		// for t1 do left and right
		List<Integer> list1 = new ArrayList<>();
		queue.offer(t1.getRoot());
		queue.offer(null);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node != null) {
				// FIXME Bad practise to type cast
				list1.add((Integer) node.getData());

				if (node.leftChild != null)
					queue.offer(node.leftChild);
				if (node.rightChild != null)
					queue.offer(node.rightChild);

			} else {
				if (!queue.isEmpty()) {
					queue.offer(null);
				}
			}
		}

		// for t2 do right and left
		List<Integer> list2 = new ArrayList<>();
		queue.offer(t2.root);
		queue.offer(null);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node != null) {
				// FIXME Bad pratise to type case
				list2.add((Integer) node.getData());

				if (node.rightChild != null)
					queue.offer(node.rightChild);
				if (node.leftChild != null)
					queue.offer(node.leftChild);

			} else {
				if (!queue.isEmpty()) {
					queue.offer(null);
				}
			}
		}

		if (list1.size() != list2.size()) {
			return false;
		}

		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i))) {
				return false;
			}
		}

		return true;

	}

	public static int[] arrayRepresentation(AVLTree tree) {

		StringBuffer stringBuff = new StringBuffer();

		Queue<Node> queue = new LinkedList<>();
		queue.offer(tree.getRoot());
		queue.offer(null);
		stringBuff.append(String.valueOf(tree.getRoot().getData()) + ",");

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node != null) {

				if (node.leftChild != null) {
					queue.offer(node.leftChild);
					stringBuff.append(String.valueOf(node.leftChild.getData()) + ",");

					if (node.rightChild == null) {
						stringBuff.append("*,");
					}
				}
				if (node.rightChild != null) {
					queue.offer(node.rightChild);
					stringBuff.append(String.valueOf(node.rightChild.getData()) + ",");
					if (node.leftChild == null) {
						stringBuff.append("*,");
					}
				}
			} else {
				if (!queue.isEmpty()) {
					queue.offer(null);
				}
			}
		}

		String str = stringBuff.toString();
		String[] splitResult = str.split(",");
		int[] arrayRepresentation = new int[splitResult.length];
		for (int i = 0; i < splitResult.length; i++) {
			if (splitResult[i].equals("*")) {
				arrayRepresentation[i] = -9999;
			} else {
				arrayRepresentation[i] = Integer.valueOf(splitResult[i]);

			}
		}
		return arrayRepresentation;
	}

}
