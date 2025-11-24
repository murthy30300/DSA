package com.lpractice;

public class Demo {
	private Node head;
	class Node{
		String data;
		Node next;
		Node(String data){
			this.data = data;
			this.next = null;
		}
	}
	public void addFirst(String data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
			return;	
		}
		newNode.next = head;
		head = newNode;
	}
	public void addLast(String data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
			return;		
		}
		Node cn = head;
		while(cn.next!=null) {
			cn = cn.next;		
		}
		cn.next = newNode;
		newNode.next = null;	
	}
	public void print() {
		if(head == null) {
			System.out.println("empty");
			return;		
		}
		Node cn = head;
		while(cn!=null) {
			System.out.print(cn.data+"-->");
			cn = cn.next;		
		}
	}
	public void deleteFirst() {
		if(head == null);
	}
	public static void main(String[] args) {
		Demo list = new Demo();
		list.addFirst("vishnu");
		list.addLast("murthy");
		list.print();

	}

}
