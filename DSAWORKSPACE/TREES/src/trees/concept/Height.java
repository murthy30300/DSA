package trees.concept;

class TreeNode{
	int item;
	TreeNode left;
	TreeNode right;
	TreeNode(int item){
		this.item = item;
	}
}

public class Height {
	public static int height(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int left = height(root.left);
		int right = height(root.right);
		
		return Math.max(left, right)+1;
		
	}
	public static int diameter(TreeNode root) {
		int[] d = new int[1];
		findDiameter(root,d);
		return d[0];
	}
	public static int findDiameter(TreeNode root, int[] d) {
		if(root == null) {
			return 0;
		}
		int left = findDiameter(root.left,d);
		int right = findDiameter(root.right,d);
		
		d[0] = Math.max(left+right,d[0])+1;
		return d[0];
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		root.right.left.left = new TreeNode(6);
		int h = height(root);
		int d = diameter(root);
		System.out.println(d);
	}

}
