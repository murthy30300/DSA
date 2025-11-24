package com.build;
import java.util.*;
class BinaryHeap{
	private ArrayList<Integer> al;
	private int size;
	BinaryHeap(){
		al = new ArrayList<>();
		size = 0;
	}
	public void initialize() {
		al.clear();
		size =0;
		return;
	}
	
	public void heapifyUp(ArrayList<Integer> al, int index) {
		int parent = index/2;
		if(index>0 && al.get(index)<al.get(parent)) {
			int temp = al.get(index);
			al.set(index, al.get(parent));
			al.set(parent, temp);
			heapifyUp(al,parent);
		}
		return;
	}
	public void heapifyDown(ArrayList<Integer> al, int index) {
		int smallestIndex = index;
		int left = 2*index+1;
		int right = 2*index+2;
		if(left<al.size() && al.get(smallestIndex)>al.get(left)) {
			smallestIndex = left;
		}
		if(right<al.size() && al.get(smallestIndex)>al.get(right)) {
			smallestIndex = right;
		}
		if(smallestIndex!=index) {
			int temp = al.get(index);
			al.set(smallestIndex, al.get(index));
			al.set(index, temp);
			heapifyDown(al,smallestIndex);		
		}
		return;
	}
	public void insertKey(int key) {
		al.add(key);
		heapifyUp(al,size);
		size++;
		return;
	}
	public void extractMin() {
		int ele = al.get(0);
		int temp = al.get(size-1);
		al.set(size-1, al.get(0));
		al.set(0, temp);
		al.remove(size-1);
		size--;
		if(size>0) {
			heapifyDown(al,0);
		}
		
	}
}
public class Heap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryHeap heap = new BinaryHeap();
		heap.initialize();
		heap.insertKey(4); System.out.println("Inserting 4 in the min-heap");
        heap.insertKey(5); System.out.println("Inserting 5 in the min-heap");
        heap.insertKey(10); System.out.println("Inserting 10 in the min-heap");
    //   System.out.println("Minimum value in the min-heap is: " + heap.getMin());
//        System.out.println("Size of min-heap is: " + heap.heapSize());
//        System.out.println("Is heap empty: " + heap.isEmpty());
        System.out.println("Extracting minimum value from the heap");
        heap.extractMin();
        System.out.println("Changing value at index 0 to 10");
     //   heap.changeKey(0, 10);
     //   System.out.println("Minimum value in the min-heap is: " + heap.getMin());
	}

}
