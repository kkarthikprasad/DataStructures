package com.dataStructures.avlTree;

public class ItemObject {
	private int itemValue;
	
	public ItemObject(int itemValue)
	{
		this.itemValue = itemValue;
	}
	

	public int getItemValue() {
		return itemValue;
	}
	
	@Override
	public String toString() {
		return String.valueOf(itemValue);
	}

	
}
