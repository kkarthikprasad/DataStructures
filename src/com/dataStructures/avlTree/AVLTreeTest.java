package com.dataStructures.avlTree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	@Test
	public void TestAvlTree() {
		Comparator<ItemObject> c = new Comparator<ItemObject>() {
			@Override
			public int compare(ItemObject o1, ItemObject o2) {
				if (o1.getItemValue() < o2.getItemValue())
					return -1;
				else if (o1.getItemValue() > o2.getItemValue())
					return 1;
				else
					return 0;
			}
		};

		ItemObject i1 = new ItemObject(5);
		ItemObject i2 = new ItemObject(4);
		ItemObject i3 = new ItemObject(6);
		ItemObject i4 = new ItemObject(3);

		ItemObject i5 = new ItemObject(76);

		AVLTree<ItemObject> tree = new AVLTree<>(c);
		tree.insert(i1);
		tree.insert(i2);
		tree.insert(i3);
		tree.insert(i4);

		tree.printTree();
		System.out.println();
		System.out.println("After deletion of 6");
		tree.delete(i3);
		tree.printTree();

		System.out.println();
		tree.search(i5);
		tree.search(i2);
		tree.search(i4);

		/*
		 * AVLTree<Integer> tree = new AVLTree<>(c); tree.insert(5); tree.insert(4);
		 * tree.insert(6); tree.insert(3);
		 * 
		 * tree.printTree();
		 * 
		 * System.out.println(); System.out.println("After deletion of 6");
		 * tree.delete(6); tree.printTree();
		 * 
		 * System.out.println(); tree.search(9); tree.search(3); tree.search(4);
		 * 
		 * // tree.balanceTree(); // System.out.println("After balancing"); //
		 * tree.printTree();
		 */ 
		}

}
