package com.dataStructures.boomFilter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BloomFilter<T> {

	private short[] array;
	private int bitArraySize;
	private List<T> list;

	BloomFilter(int sizeOfBitArray) {
		array = new short[sizeOfBitArray];
		bitArraySize = sizeOfBitArray;
		list = new ArrayList<T>();
	}

	public void insert(T t) {
		int index1 = hashFunction1(t);
		array[index1] = 1;
		int index2 = hashFunction2(t);
		array[index2] = 1;
		list.add(t);

	}

	public void search(T t) {
		int index1 = hashFunction1(t);
		int index2 = hashFunction2(t);
		if (array[index1] == 1 && array[index2] == 1) {
			System.out.println("It may be present");
			if (list.contains(t))
				System.out.println("It is in the list");
			else
				System.out.println("It is NOT in the list");
		} else {
			System.out.println("It's definitely not present");
			return;
		}
	}

	private int hashFunction1(T t) {
		return Math.abs(t.hashCode() % bitArraySize);
	}

	private int hashFunction2(T t) {
		return Math.abs((t.hashCode() * 31) % bitArraySize);
	}

}
