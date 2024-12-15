class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.val + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.val + " ");
        }
    }

    public int findMin(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        } else {
            int minLeft = findMin(node.left);
            int minRight = findMin(node.right);
            return Math.min(node.val, Math.min(minLeft, minRight));
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val = findMin(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    public void createSampleTree() {
        this.root = new TreeNode(1);
        this.root.left = new TreeNode(2);
        this.root.right = new TreeNode(3);
        this.root.left.left = new TreeNode(4);
        this.root.left.right = new TreeNode(5);
        this.root.right.left = new TreeNode(6);
        this.root.right.right = new TreeNode(7);
    }
}

public class Viernes_Trees_Problems {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.createSampleTree();

        System.out.println("In-order traversal:");
        tree.inOrder(tree.root);
        System.out.println();
        
        System.out.println("Pre-order traversal:");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.println("Post-order traversal:");
        tree.postOrder(tree.root);
        System.out.println();

        int minValue = tree.findMin(tree.root);
        System.out.println("Minimum value in the tree: " + minValue);

        int keyToDelete = 3;
        tree.root = tree.deleteNode(tree.root, keyToDelete);

        System.out.println("In-order traversal after deleting node with value " + keyToDelete + ":");
        tree.inOrder(tree.root);
        System.out.println();
    }
}
