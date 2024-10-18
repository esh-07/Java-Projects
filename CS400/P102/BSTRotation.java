/**
 * This class implements a rotation operation for a Binary Search Tree.
 * It extends the BinarySearchTree_Placeholder class and provides methods
 * to perform left and right rotations on the tree nodes.
 *
 * @param <T> the type of elements stored in the tree, must extend Comparable
 */
public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree_Placeholder<T> {

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a right
     * child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this
     * method will either throw a NullPointerException: when either reference is
     * null, or otherwise will throw an IllegalArgumentException.
     *
     * @param child  is the node being rotated from child to parent position
     * @param parent is the node being rotated from parent to child position
     * @throws NullPointerException     when either passed argument is null
     * @throws IllegalArgumentException when the provided child and parent
     *                                  nodes are not initially (pre-rotation)
     *                                  related that way
     */
    protected void rotate(BSTNode<T> child, BSTNode<T> parent)
            throws NullPointerException, IllegalArgumentException {

        // Check for null nodes
        if (child == null || parent == null) {
            throw new NullPointerException("Child or parent node is null");
        }

        // Check if child and parent are in the correct relationship
        if (child.getUp() != parent) {
            throw new IllegalArgumentException("The provided nodes are not in a parent-child relationship");
        }

        // Get the grandparent node
        BSTNode<T> grandparent = parent.getUp();
        // Determine if parent is a left child of grandparent
        boolean isParentLeftChild = (grandparent != null && grandparent.getLeft() == parent);
        // Determine if child is a left child of parent
        boolean isChildLeftChild = (parent.getLeft() == child);

        if (isChildLeftChild) {
            // Perform right rotation
            parent.setLeft(child.getRight());
            if (child.getRight() != null) {
                child.getRight().setUp(parent);
            }
            child.setRight(parent);
        } else {
            // Perform left rotation
            parent.setRight(child.getLeft());
            if (child.getLeft() != null) {
                child.getLeft().setUp(parent);
            }
            child.setLeft(parent);
        }

        // Update parent pointers
        child.setUp(grandparent);
        parent.setUp(child);

        // Update grandparent's child pointer or root if necessary
        if (grandparent == null) {
            this.root = child;
        } else if (isParentLeftChild) {
            grandparent.setLeft(child);
        } else {
            grandparent.setRight(child);
        }
    }

    /**
     * Tests a left rotation not including the root.
     *
     * @return true if the rotation is performed correctly, false otherwise
     */
    public boolean test1() {
        // Test left rotation
        BSTNode<T> parent = new BSTNode<>(null);
        BSTNode<T> child = new BSTNode<>(null);
        BSTNode<T> grandchild = new BSTNode<>(null);

        // Set up initial tree structure
        this.root = parent;
        parent.setRight(child);
        child.setUp(parent);
        child.setLeft(grandchild);
        grandchild.setUp(child);

        // Perform rotation
        rotate(child, parent);

        // Check if the rotation was performed correctly
        return this.root == child &&
                child.getLeft() == parent &&
                parent.getUp() == child &&
                parent.getRight() == grandchild &&
                grandchild.getUp() == parent;
    }

    /**
     * Tests a right rotation including the root.
     *
     * @return true if the rotation is performed correctly, false otherwise
     */
    public boolean test2() {
        // Test right rotation including root
        BSTNode<T> parent = new BSTNode<>(null);
        BSTNode<T> child = new BSTNode<>(null);
        BSTNode<T> grandchild = new BSTNode<>(null);

        // Set up initial tree structure
        this.root = parent;
        parent.setLeft(child);
        child.setUp(parent);
        child.setRight(grandchild);
        grandchild.setUp(child);

        // Perform rotation
        rotate(child, parent);

        // Check if the rotation was performed correctly
        return this.root == child &&
                child.getRight() == parent &&
                parent.getUp() == child &&
                parent.getLeft() == grandchild &&
                grandchild.getUp() == parent;
    }

    /**
     * Tests a rotation with 3 shared children.
     *
     * @return true if the rotation is performed correctly, false otherwise
     */
    public boolean test3() {
        // Test rotation with 3 shared children
        BSTNode<T> parent = new BSTNode<>(null);
        BSTNode<T> child = new BSTNode<>(null);
        BSTNode<T> grandchild1 = new BSTNode<>(null);
        BSTNode<T> grandchild2 = new BSTNode<>(null);
        BSTNode<T> grandchild3 = new BSTNode<>(null);

        // Set up initial tree structure
        this.root = parent;
        parent.setLeft(child);
        child.setUp(parent);
        child.setLeft(grandchild1);
        grandchild1.setUp(child);
        child.setRight(grandchild2);
        grandchild2.setUp(child);
        parent.setRight(grandchild3);
        grandchild3.setUp(parent);

        // Perform rotation
        rotate(child, parent);

        // Check if the rotation was performed correctly
        return this.root == child &&
                child.getRight() == parent &&
                parent.getUp() == child &&
                child.getLeft() == grandchild1 &&
                grandchild1.getUp() == child &&
                parent.getLeft() == grandchild2 &&
                grandchild2.getUp() == parent &&
                parent.getRight() == grandchild3 &&
                grandchild3.getUp() == parent;
    }

    /**
     * Tests a right rotation with 1 shared child, not including the root.
     *
     * @return true if the rotation is performed correctly, false otherwise
     */
    public boolean test4() {
        // Test right rotation with 1 shared child (not including root)
        BSTNode<T> parent = new BSTNode<>(null);
        BSTNode<T> child = new BSTNode<>(null);
        BSTNode<T> grandchild = new BSTNode<>(null);
        BSTNode<T> root = new BSTNode<>(null);

        // Set up initial tree structure
        this.root = root;
        root.setLeft(parent);
        parent.setUp(root);
        parent.setLeft(child);
        child.setUp(parent);
        child.setRight(grandchild);
        grandchild.setUp(child);

        // Perform rotation
        rotate(child, parent);

        // Check if the rotation was performed correctly
        return root.getLeft() == child &&
                child.getUp() == root &&
                child.getRight() == parent &&
                parent.getUp() == child &&
                parent.getLeft() == grandchild &&
                grandchild.getUp() == parent;
    }

    /**
     * Tests a rotation with 0 shared children.
     * This test creates a simple tree with a parent and a child node,
     * performs a rotation, and checks if the resulting structure is correct.
     *
     * @return true if the rotation is performed correctly, false otherwise
     */
    public boolean test5() {
        // Test rotation with 0 shared children
        BSTNode<T> parent = new BSTNode<>(null);
        BSTNode<T> child = new BSTNode<>(null);

        this.root = parent;
        parent.setLeft(child);
        child.setUp(parent);

        rotate(child, parent);

        return this.root == child &&
                child.getRight() == parent &&
                parent.getUp() == child &&
                parent.getLeft() == null &&
                parent.getRight() == null;
    }

    /**
     * Main method to run all tests and display results.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        BSTRotation<Integer> bst = new BSTRotation<>();
        System.out.println("Test 1 (Left rotation, not including root): " + (bst.test1() ? "PASS" : "FAIL"));
        System.out.println("Test 2 (Right rotation including root): " + (bst.test2() ? "PASS" : "FAIL"));
        System.out.println("Test 3 (Rotation with 3 shared children): " + (bst.test3() ? "PASS" : "FAIL"));
        System.out.println(
                "Test 4 (Right rotation with 1 shared child, not including root): " + (bst.test4() ? "PASS" : "FAIL"));
        System.out.println("Test 5 (Rotation with 0 shared children): " + (bst.test5() ? "PASS" : "FAIL"));
    }
}