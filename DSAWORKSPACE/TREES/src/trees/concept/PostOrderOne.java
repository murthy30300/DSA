package trees.concept;

import java.util.ArrayList;
import java.util.Stack;

//class Node {
//    int data;
//    Node left, right;
//
//    Node(int item) {
//        data = item;
//        left = right = null;
//    }
//}

public class PostOrderOne {
    Node root;

    // Post-order traversal using the algorithm provided
    ArrayList<Integer> postOrderIterative(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        if (node == null) {
            return result; // Empty tree
        }

        Node current = node;

        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (current != null) {
                // Push right child first if it exists
                if (current.right != null) {
                    stack.push(current.right);
                }
                // Push the current node
                stack.push(current);

                // Move to the left child
                current = current.left;
            }

            // Pop the top element from the stack
            current = stack.pop();

            // If the right child exists and is the next node on the stack
            if (!stack.isEmpty() && current.right == stack.peek()) {
                stack.pop(); // Remove the right child from the stack
                stack.push(current); // Push the current node back to the stack
                current = current.right; // Process the right child
            } else {
                // Add current node to result
                result.add(current.data);
                current = null; // Mark current as processed
            }
        }

        return result;
    }

    // Driver program to test the implementation
    public static void main(String[] args) {
        PostOrderOne tree = new PostOrderOne();

        // Create the tree shown in the example
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        // Perform post-order traversal
        ArrayList<Integer> result = tree.postOrderIterative(tree.root);
        System.out.println("Post-order traversal of the binary tree:");
        System.out.println(result); // Expected Output: [4, 5, 2, 6, 7, 3, 1]
    }
}
