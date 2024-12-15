class Node {
    int data;
    Node left, right;
    
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    Node root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
    
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    public int height(Node node) {
        if (node == null)
            return 0;
        else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    
    public int countNodes(Node node) {
        if (node == null)
            return 0;
        else
            return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    public boolean search(Node node, int key) {
        if (node == null)
            return false;
        
        if (node.data == key)
            return true;
        
        boolean leftSearch = search(node.left, key);
        boolean rightSearch = search(node.right, key);
        
        return leftSearch || rightSearch;
    }
    
    public Node deleteNode(Node root, int key) {
        if (root == null)
            return root;
        
        if (key < root.data)
            root.left = deleteNode(root.left, key);
        else if (key > root.data)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            

            root.data = minValue(root.right);
            
            root.right = deleteNode(root.right, root.data);
        }
        
        return root;
    }
    
    private int minValue(Node node) {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        
        System.out.println("In-order traversal:");
        tree.inOrder(tree.root);
        System.out.println();
        
        System.out.println("Pre-order traversal:");
        tree.preOrder(tree.root);
        System.out.println();
        
        System.out.println("Post-order traversal:");
        tree.postOrder(tree.root);
        System.out.println();
        
        System.out.println("Height of the tree: " + tree.height(tree.root));
        
        System.out.println("Number of nodes in the tree: " + tree.countNodes(tree.root));
        
        int key = 5;
        System.out.println("Search for key " + key + ": " + tree.search(tree.root, key));
        
        key = 6;
        System.out.println("Search for key " + key + ": " + tree.search(tree.root, key));
        
        System.out.println("Deleting node with key 3...");
        tree.root = tree.deleteNode(tree.root, 3);
        System.out.println("In-order traversal after deletion:");
        tree.inOrder(tree.root);
        System.out.println();
    }
}
