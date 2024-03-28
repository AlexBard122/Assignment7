package accidentpack;

/**
 * @author surajsubramanian & Devin C
 * @version 28 March, 2024
 * This class implements an AVL search tree for report objects.
 * Original code pulled from https://github.com/surajsubramanian/AVL-Trees/blob/master/AVLT.java
 * and modified by Devin.
 */
public class AVLTree {
    // NODE structure
    class Node {
        report data;
        int height;
        Node left;
        Node right;

        // Constructor for Node
        public Node(report data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    // Returns the height of the node
    int Height(Node key) {
        if (key == null)
            return 0;
        else
            return key.height;
    }

    // Computes the balance factor of the node
    int Balance(Node key) {
        if (key == null)
            return 0;
        else
            return (Height(key.right) - Height(key.left));
    }

    // Updates the height of the node
    void updateHeight(Node key) {
        int l = Height(key.left);
        int r = Height(key.right);
        key.height = Math.max(l, r) + 1;
    }

    // Performs left rotation
    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // Performs right rotation
    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // Balances the tree using rotations after an insertion or deletion
    Node balanceTree(Node root) {
        updateHeight(root);
        int balance = Balance(root);
        if (balance > 1) { // Right subtree is heavier
            if (Balance(root.right) < 0) { // Right-Left Case
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            } else { // Right-Right Case
                return rotateLeft(root);
            }
        }
        if (balance < -1) { // Left subtree is heavier
            if (Balance(root.left) > 0) { // Left-Right Case
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            } else { // Left-Left Case
                return rotateRight(root);
            }
        }
        return root;
    }

    Node Root;

    // Performs BST insertion and balances the tree
    Node BSTInsert(Node root, report key) {
        if (root == null)
            return new Node(key);
        else if (key.compareTo(root.data) < 0)
            root.left = BSTInsert(root.left, key);
        else
            root.right = BSTInsert(root.right, key);
        return balanceTree(root);
    }

    // Finds the successor of a node
    Node Successor(Node root) {
        if (root.left != null)
            return Successor(root.left);
        else
            return root;
    }

    // Performs BST deletion and balances the tree
    Node Remove(Node root, report key) {
        if (root == null)
            return root;
        else if (key.compareTo(root.data) < 0)
            root.left = Remove(root.left, key);
        else if (key.compareTo(root.data) > 0)
            root.right = Remove(root.right, key);
        else {
            if (root.right == null)
                root = root.left;
            else if (root.left == null)
                root = root.right;
            else {
                Node temp = Successor(root.right);
                root.data = temp.data;
                root.right = Remove(root.right, root.data);
            }
        }
        if (root == null)
            return root;
        else
            return balanceTree(root);
    }

    // Searches for a node with given value
    Node findNode(Node root, report key) {
        if (root == null || key.compareTo(root.data) == 0)
            return root;
        if (key.compareTo(root.data) < 0)
            return findNode(root.left, key);
        else
            return findNode(root.right, key);
    }

    // Inserts a node with given value into the tree
    void add(report key) {
        if (findNode(Root, key) == null) {
            Root = BSTInsert(Root, key);
            System.out.println("Insertion successful");
        } else
            System.out.println("\nKey with the entered value already exists in the tree");
    }

    // Searches for a node with given value in the tree
    int search(report key) {
        if (findNode(Root, key) == null)
            return 0;
        else
            return 1;
    }

    // Deletes a node with given value from the tree
    void delete(report key) {
        if (findNode(Root, key) != null) {
            Root = Remove(Root, key);
            System.out.println("\nDeletion successful ");
        } else
            System.out.println("\nNo node with entered value found in tree");
    }

    // Performs in-order traversal of the tree
    void InOrder(Node root) {
        if (root == null) {
            System.out.println("\nNo nodes in the tree");
            return;
        }
        if (root.left != null)
            InOrder(root.left);
        System.out.print(root.data.getID() + " ");
        if (root.right != null)
            InOrder(root.right);
    }

    // Performs pre-order traversal of the tree
    void PreOrder(Node root) {
        if (root == null) {
            System.out.println("No nodes in the tree");
            return;
        }
        System.out.print(root.data.getID() + " ");
        if (root.left != null)
            PreOrder(root.left);
        if (root.right != null)
            PreOrder(root.right);
    }

    // Performs post-order traversal of the tree
    void PostOrder(Node key) {
        if (key == null) {
            System.out.println("No nodes in the tree");
            return;
        }
        if (key.left != null)
            PostOrder(key.left);
        if (key.right != null)
            PostOrder(key.right);
        System.out.print(key.data.getID() + " ");
    }
}
