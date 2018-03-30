package com.dataStructures.boomFilter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BloomFilterTest {

	@Test
	public void bloomFilterTest()
	{
		BloomFilter<String> filter = new BloomFilter<>(30);
		filter.search("as");
		filter.insert("asf");
		filter.insert("meow");
		filter.insert("quack");
		filter.insert("blu blu");
		filter.search("blu blu");
		filter.search("asf");
		filter.search("meow");
		filter.search("quack");
		filter.search("asdf");
		
	}

}
