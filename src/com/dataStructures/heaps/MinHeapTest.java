package com.dataStructures.heaps;

import org.junit.jupiter.api.Test;

class MinHeapTest {

  @Test
  public void insertTest()
  {
    
    
    
    MinHeap minHeap = new MinHeap();
    minHeap.insert(9);
    minHeap.insert(4);
    minHeap.insert(3);
    minHeap.insert(8);
    minHeap.insert(5);
    minHeap.insert(7);
    minHeap.insert(2);
    minHeap.insert(66);
    
    minHeap.printHeap();
    
  }

}
