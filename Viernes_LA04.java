import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int item) {
        val = item;
        left = right = null;
    }
}

class BST {
    TreeNode root;

    BST() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.val) {
            root.left = insertRec(root.left, key);
        } else if (key > root.val) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    void preorderTraversal(TreeNode node, ArrayList<Integer> traversal) {
        if (node != null) {
            traversal.add(node.val);
            preorderTraversal(node.left, traversal);
            preorderTraversal(node.right, traversal);
        }
    }

    void inorderTraversal(TreeNode node, ArrayList<Integer> traversal) {
        if (node != null) {
            inorderTraversal(node.left, traversal);
            traversal.add(node.val);
            inorderTraversal(node.right, traversal);
        }
    }

    void postorderTraversal(TreeNode node, ArrayList<Integer> traversal) {
        if (node != null) {
            postorderTraversal(node.left, traversal);
            postorderTraversal(node.right, traversal);
            traversal.add(node.val);
        }
    }

    ArrayList<Integer> arrayRepresentation() {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }
}

public class Viernes_LA04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            BST bst = new BST();
            System.out.println("Enter integers to insert into the BST (type 'Done' when finished):");

            while (true) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("Done")) {
                    break;
                }

                try {
                    int key = Integer.parseInt(input);
                    bst.insert(key);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, Enter an integers/numbers only or 'Done' to finish.");
                }
            }

            ArrayList<Integer> preorder = new ArrayList<>();
            ArrayList<Integer> inorder = new ArrayList<>();
            ArrayList<Integer> postorder = new ArrayList<>();

            bst.preorderTraversal(bst.root, preorder);
            bst.inorderTraversal(bst.root, inorder);
            bst.postorderTraversal(bst.root, postorder);

            System.out.println("1-D Array Representation of the BST: " + bst.arrayRepresentation());
            System.out.println("Preorder Traversal: " + preorder);
            System.out.println("Inorder Traversal: " + inorder);
            System.out.println("Postorder Traversal: " + postorder);

            System.out.print("Do you want to try again? (yes/no): ");
            String retry = scanner.next().toLowerCase();
            if (!retry.equals("yes")) {
                break;
            }
        }

        scanner.close();
    }
}
