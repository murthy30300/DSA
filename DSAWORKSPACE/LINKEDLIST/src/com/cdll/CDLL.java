package com.cdll;
import java.util.*;
public class CDLL {
	static Scanner sc = new Scanner(System.in);
	static class NodeCDLL{
		int data;
		NodeCDLL prev;
		NodeCDLL next;
	}
	static NodeCDLL insertFront(NodeCDLL head) {
		
		NodeCDLL newNode = new NodeCDLL();
		newNode.data = sc.nextInt();
		if(head == null) {
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;
		}
		else {
			newNode.next = head;
			newNode.prev = head.prev;
			head.prev.next = newNode;
			head.prev = newNode;
			head = newNode;
		}
		return head;
	}
	static NodeCDLL insertEnd(NodeCDLL head) {
		
		NodeCDLL newNode = new NodeCDLL();
		newNode.data = sc.nextInt();
		if(head == null) {
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;
		}
		else {
			NodeCDLL cur = head;
			while(cur.next !=head) {
				cur = cur.next;
			}
			newNode.next = cur.next;
			newNode.prev = cur;
			cur.next.prev = newNode;
			cur.next = newNode;
			
		}
		return head;
	}
		
	static void display(NodeCDLL head) {
		NodeCDLL cur = head;
		if(cur == null) System.out.println("list is empty");
		else {
			do {
				System.out.println(cur.data+"<->");
				cur = cur.next;
			}while(cur!=head);
		}
	}
	
	public static void main(String[] args) {
		NodeCDLL head = null;
        char repeatMenu = 'y';
        int choice;
        displayMenu(); 
        do {
            System.out.println("\nEnter Your Choice:");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    head = insertFront(head);
                    display(head);
                    break;
                case 2:
                    head = insertEnd(head);
                    display(head);
                    break;
//                case 3:
//                    head = insertAfter(head);
//                    display(head);
//                    break;
//                case 4:
//                    head = insertBefore(head);
//                    display(head);
//                    break;
//                case 5:
//                    head = deleteFront(head);
//                    display(head);
//                    break;
//                case 6:
//                    head = deleteEnd(head);
//                    display(head);
//                    break;
//                case 7:
//                    head = deleteMid(head);
//                    display(head);
//                    break;
//                case 8:
//                    search(head);
//                    break;
//                case 9:
//                    head = reverse(head);
//                    display(head);
//                    break;
                default:
                    System.out.println("\nWrong Choice!!!");
                    break;
            }
            System.out.println("\nEnter More(Y/N)");
            repeatMenu = sc.next().charAt(0);
        } while (repeatMenu == 'y' || repeatMenu == 'Y');
		
	}
	public static void displayMenu() {
        System.out.println("==============================================");
        System.out.println("Menu:");
        System.out.println("1. Insert At Front");
        System.out.println("2. Insert At End");
        System.out.println("3. Insert After Element");
        System.out.println("4. Insert Before Element");
        System.out.println("5. Delete From Front");
        System.out.println("6. Delete From End");
        System.out.println("7. Delete A Node");
        System.out.println("8. Search for an element");
        System.out.println("9. Reverse the list");
        System.out.println("==============================================");
    }

}
