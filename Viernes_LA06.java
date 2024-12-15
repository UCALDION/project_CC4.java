import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class AVLTree {
    private class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    private Node root;

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private Node insert(Node node, int key) {
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    private Node deleteNode(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private void preOrder(Node node, List<Integer> traversal) {
        if (node != null) {
            traversal.add(node.key);
            preOrder(node.left, traversal);
            preOrder(node.right, traversal);
        }
    }

    public List<Integer> preOrder() {
        List<Integer> traversal = new ArrayList<>();
        preOrder(root, traversal);
        return traversal;
    }

    private void inOrder(Node node, List<Integer> traversal) {
        if (node != null) {
            inOrder(node.left, traversal);
            traversal.add(node.key);
            inOrder(node.right, traversal);
        }
    }

    public List<Integer> inOrder() {
        List<Integer> traversal = new ArrayList<>();
        inOrder(root, traversal);
        return traversal;
    }

    private void postOrder(Node node, List<Integer> traversal) {
        if (node != null) {
            postOrder(node.left, traversal);
            postOrder(node.right, traversal);
            traversal.add(node.key);
        }
    }

    public List<Integer> postOrder() {
        List<Integer> traversal = new ArrayList<>();
        postOrder(root, traversal);
        return traversal;
    }

    public List<Integer> to1DArray() {
        List<Integer> arr = new ArrayList<>();
        to1DArray(root, arr);
        return arr;
    }

    private void to1DArray(Node node, List<Integer> arr) {
        if (node != null) {
            to1DArray(node.left, arr);
            arr.add(node.key);
            to1DArray(node.right, arr);
        }
    }
}

public class Viernes_LA06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree avl = new AVLTree();
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("AVL Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display 1-D array");
            System.out.println("4. Display Preorder Traversal");
            System.out.println("5. Display Inorder Traversal");
            System.out.println("6. Display Postorder Traversal");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter integer to insert: ");
                    int insertKey = scanner.nextInt();
                    avl.insert(insertKey);
                    break;
                case 2:
                    System.out.print("Enter integer to delete: ");
                    int deleteKey = scanner.nextInt();
                    avl.delete(deleteKey);
                    break;
                case 3:
                    List<Integer> arr = avl.to1DArray();
                    System.out.println("1-D array representation: " + arr);
                    break;
                case 4:
                    List<Integer> preOrder = avl.preOrder();
                    System.out.println("Preorder Traversal: " + preOrder);
                    break;
                case 5:
                    List<Integer> inOrder = avl.inOrder();
                    System.out.println("Inorder Traversal: " + inOrder);
                    break;
                case 6:
                    List<Integer> postOrder = avl.postOrder();
                    System.out.println("Postorder Traversal: " + postOrder);
                    break;
                case 7:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }

        scanner.close();
    }
}