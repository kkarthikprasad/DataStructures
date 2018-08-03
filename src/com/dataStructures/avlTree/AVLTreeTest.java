package com.dataStructures.avlTree;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.dataStructures.avlTree.AVLTree.Node;

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

		ItemObject i1 = new ItemObject(1);
		ItemObject i2 = new ItemObject(2);
		ItemObject i3 = new ItemObject(3);
		ItemObject i4 = new ItemObject(4);
		ItemObject i5 = new ItemObject(5);
		ItemObject i6 = new ItemObject(6);
		ItemObject i7 = new ItemObject(7);
		ItemObject i8 = new ItemObject(8);

		AVLTree<ItemObject> tree = new AVLTree<>(c);
//		tree.insert(i1);
//		tree.insert(i2);
//		tree.insert(i3);
//		tree.insert(i4);
//		tree.insert(i5);
		
		tree.insert(i5);
		tree.insert(i4);
		tree.insert(i3);
		tree.insert(i2);
		tree.insert(i1);
		
		tree.printTree();
		System.out.println();
		System.out.println("After deletion of 2 and 4");
		tree.delete(i2);
		tree.delete(i4);
		tree.printTree();
		
		
		tree.insert(i6);
		System.out.println();
		System.out.println("After 6 is inserted");
		tree.printTree();
		
		
		tree.insert(i7);
		System.out.println();
		System.out.println("After 7 is inserted");
		tree.printTree();
		
		
		tree.insert(i8);
		System.out.println();
		System.out.println("After insertion of 8");
		tree.printTree();

		System.out.println();
		tree.search(i4);
		
		
		
		}

	
	@Ignore
	@Test
	public void testMirrorTrees()
	{
		AVLTree<Integer> tree1 = new AVLTree<>( new Comparator<Integer>()
				{

					@Override
					public int compare(Integer o1, Integer o2) {
						if(o1 < o2)
							return -1;
						else if( o1 > o2)
							return 1;
						return 0;
					}
				});
		
		AVLTree<Integer> tree2 = new AVLTree<>( new Comparator<Integer>()
		{

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2)
					return -1;
				else if( o1 > o2)
					return 1;
				return 0;
			}
		});
		
		Node<Integer> node1 = new Node();
		node1.setData(1);
		Node<Integer> node2 = new Node();
		node2.setData(2);
		Node<Integer> node3 = new Node();
		node3.setData(3);
		Node<Integer> node4 = new Node();
		node4.setData(4);
		Node<Integer> node5 = new Node();
		node5.setData(5);
		Node<Integer> node6 = new Node();
		node6.setData(6);
		Node<Integer> node7 = new Node();
		node7.setData(7);
		
		node1.setRightChild(node4);
		node1.setLeftChild(node3);
		node3.setLeftChild(node7);
		node3.setRightChild(node6);
//		node4.setLeftChild(node5);
		node4.setRightChild(node2);
		
		tree1.mirrorTreesInsert(node1);
		tree1.printTree();
		
		
		System.out.println();
		
		Node<Integer> node11 = new Node();
		node11.setData(1);
		Node<Integer> node12 = new Node();
		node12.setData(2);
		Node<Integer> node13 = new Node();
		node13.setData(3);
		Node<Integer> node14 = new Node();
		node14.setData(4);
		Node<Integer> node15 = new Node();
		node15.setData(5);
		Node<Integer> node16 = new Node();
		node16.setData(6);
		Node<Integer> node17 = new Node();
		node17.setData(7);
		
		node11.setLeftChild(node14);
		node11.setRightChild(node13);
		node13.setRightChild(node17);
		node13.setLeftChild(node16);
//		node14.setRightChild(node15);
		node14.setLeftChild(node12);
		
		
		tree2.mirrorTreesInsert(node11);
		tree2.printTree();
		
		boolean areMirrorTrees = AVLTree.areMirrorTrees(tree1, tree2);
		System.out.println();
		System.out.println(areMirrorTrees);
//		assertTrue(areMirrorTrees);
		
		
		System.out.println();
		
		int [] arrOfTree1 = AVLTree.arrayRepresentation(tree1);
		int [] arrOfTree2 = AVLTree.arrayRepresentation(tree2);
		
		for(int i=0;i<arrOfTree1.length;i++) {
			System.out.println( arrOfTree1[i]);
		}
		System.out.println("next tree");
		for(int i=0;i<arrOfTree2.length;i++)
		{
			System.out.println(arrOfTree2[i]);
		}
		
		
		
	}
	
	
	
}
