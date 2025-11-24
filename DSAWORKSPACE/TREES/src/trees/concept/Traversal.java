package trees.concept;
import java.util.*;
class Node{
	int data;
	Node left;
	Node right;
	Node(int data){
		this.data = data;
	}
}
public class Traversal {
	public static void inorder(Node root) {
		if(root==null)
			return;
		inorder(root.left);
		System.out.print(root.data);
		inorder(root.right);	
	}
	public static void preorder(Node root) {
		if(root == null)
			return;
		System.out.print(root.data);
		preorder(root.left);
		preorder(root.right);
	}
	public static void postorder(Node root) {
		if(root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data);	
	}
	public static List<List<Integer>> levelorder(Node root) {
		Queue<Node> qu = new LinkedList<Node>();
		List<List<Integer>> wl = new LinkedList<List<Integer>>();
		if(root == null) {
			return wl;
		}
		qu.offer(root);
		while(!qu.isEmpty()) {
			int levelNum = qu.size();
			List<Integer> subList = new LinkedList<Integer>();
			for(int i = 0;i<levelNum;i++) {
				if(qu.peek().left!=null)
					qu.offer(qu.peek().left);
				if(qu.peek().right!=null)
					qu.offer(qu.peek().right);
				subList.add(qu.poll().data);
				
			}
			wl.add(subList);
		}
		return wl;
		
	}
	public static void main(String [] args) {
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        inorder(root);
        preorder(root);
        postorder(root);
        System.out.println("level order"+levelorder(root));
        
	}

}
