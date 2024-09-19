/**
 * This class implements a Binary Search Tree data structure
 * that stores elements in sorted order. Duplicate values are allowed.
 *
 * @param <T> the type of elements stored in the tree, must be Comparable
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    protected BSTNode<T> root;

    /**
     * Inserts a new data value into the binary search tree.
     * Duplicate values are allowed and will be inserted to the right.
     * This method relies exclusively on the tree referenced by the root field.
     *
     * @param data the new value being inserted
     * @throws NullPointerException if data argument is null
     */
    @Override
    public void insert(T data) throws NullPointerException {
        // Check for null input
        if (data == null) {
            throw new NullPointerException("Cannot insert null value");
        }

        // Create a new node with the input data
        BSTNode<T> newNode = new BSTNode<>(data);

        // If the tree is empty, make the new node the root
        if (root == null) {
            root = newNode;
        } else {
            // Otherwise, use the helper method to insert the node
            insertHelper(newNode, root);
        }
    }

    /**
     * Performs the naive binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree. When the provided subtree
     * is null, this method does nothing.
     *
     * @param newNode the node to be inserted
     * @param subtree the root of the subtree to insert into
     */
    protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {
        // Compare the new node's data with the current subtree's data
        int compareResult = newNode.getData().compareTo(subtree.getData());

        if (compareResult <= 0) {
            // If new node is smaller or equal, it goes to the left
            if (subtree.getLeft() == null) {
                // If there's no left child, insert here
                subtree.setLeft(newNode);
                newNode.setUp(subtree);
            } else {
                // Otherwise, recursively insert into the left subtree
                insertHelper(newNode, subtree.getLeft());
            }
        } else {
            // If new node is greater, it goes to the right
            if (subtree.getRight() == null) {
                // If there's no right child, insert here
                subtree.setRight(newNode);
                newNode.setUp(subtree);
            } else {
                // Otherwise, recursively insert into the right subtree
                insertHelper(newNode, subtree.getRight());
            }
        }
    }

    /**
     * Checks whether data is stored in the tree.
     * This method relies exclusively on the tree referenced by the root field.
     *
     * @param data the value to check for in the collection
     * @return true if the collection contains data, false otherwise
     */
    @Override
    public boolean contains(Comparable<T> data) {
        // Use the helper method starting from the root
        return containsHelper(data, root);
    }

    /**
     * Helper method for contains that recursively searches the tree.
     *
     * @param data the value to search for
     * @param node the current node being examined
     * @return true if the data is found, false otherwise
     */
    private boolean containsHelper(Comparable<T> data, BSTNode<T> node) {
        // Base case: if we've reached a null node, the data is not in the tree
        if (node == null) {
            return false;
        }

        // Compare the search data with the current node's data
        int compareResult = data.compareTo(node.getData());

        if (compareResult == 0) {
            // If they're equal, we've found the data
            return true;
        } else if (compareResult < 0) {
            // If search data is smaller, recursively search the left subtree
            return containsHelper(data, node.getLeft());
        } else {
            // If search data is larger, recursively search the right subtree
            return containsHelper(data, node.getRight());
        }
    }

    /**
     * Counts the number of values in the collection.
     * This method relies exclusively on the tree referenced by the root field.
     *
     * @return the number of values in the collection
     */
    @Override
    public int size() {
        return sizeHelper(root);
    }

    /**
     * Helper method for size that recursively counts nodes.
     *
     * @param node the current node being counted
     * @return the number of nodes in the subtree rooted at node
     */
    private int sizeHelper(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
    }

    /**
     * Checks if the collection is empty.
     * This method relies exclusively on the tree referenced by the root field.
     *
     * @return true if the collection contains 0 values, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all values from the collection.
     * This method relies exclusively on the tree referenced by the root field.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Helper method to verify that the tree satisfies the Binary Search Tree
     * property.
     * Each node's left child must have a lesser or equal value, and each node's
     * right child must have a greater or equal value.
     *
     * @return true if the tree satisfies the BST property, false otherwise
     */
    private boolean isBSTPropertySatisfied() {
        return isBSTPropertySatisfiedHelper(root, null, null);
    }

    /**
     * Helper method to recursively check the BST property for each node.
     *
     * @param node the current node being checked
     * @param min  the minimum value this node can have (null if no minimum)
     * @param max  the maximum value this node can have (null if no maximum)
     * @return true if the subtree rooted at node satisfies the BST property, false
     *         otherwise
     */
    private boolean isBSTPropertySatisfiedHelper(BSTNode<T> node, T min, T max) {
        if (node == null) {
            return true;
        }

        // Check if the current node's value is within the allowed range
        if ((min != null && node.getData().compareTo(min) < 0) ||
                (max != null && node.getData().compareTo(max) > 0)) {
            return false;
        }

        // Recursively check left and right subtrees
        return isBSTPropertySatisfiedHelper(node.getLeft(), min, node.getData()) &&
                isBSTPropertySatisfiedHelper(node.getRight(), node.getData(), max);
    }

    /**
     * Tests the insertion of multiple integer values to create a complex tree
     * shape,
     * verifies the correct functioning of contains() and size() methods,
     * and checks that the BST property is satisfied for all nodes.
     *
     * @return true if all test conditions pass, false otherwise
     */
    public boolean test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Create a complex tree shape with integers in a specific order
        bst.insert(8); // Root
        bst.insert(3); // Left subtree
        bst.insert(10); // Right subtree
        bst.insert(1); // Left-left leaf
        bst.insert(6); // Left-right subtree
        bst.insert(14); // Right-right leaf
        bst.insert(4); // Left-right-left leaf
        bst.insert(7); // Left-right-right leaf
        bst.insert(13); // Right-right-left leaf

        // Check if the size is correct after insertions
        boolean sizeCorrect = bst.size() == 9;

        // Test finding values in different positions of the tree
        boolean containsRoot = bst.contains(8); // Root
        boolean containsLeftLeaf = bst.contains(1); // Left-most leaf
        boolean containsRightLeaf = bst.contains(14); // Right-most leaf
        boolean containsInterior = bst.contains(3) && bst.contains(10) && bst.contains(6); // Interior nodes
        boolean containsDeepLeaves = bst.contains(4) && bst.contains(7) && bst.contains(13); // Deeper leaves
        boolean doesNotContain = !bst.contains(2) && !bst.contains(9) && !bst.contains(15); // Non-existent values

        // Check that the BST property is satisfied for all nodes
        boolean bstPropertySatisfied = bst.isBSTPropertySatisfied();

        // All conditions must be true for the test to pass
        return sizeCorrect && containsRoot && containsLeftLeaf && containsRightLeaf &&
                containsInterior && containsDeepLeaves && doesNotContain && bstPropertySatisfied;
    }

    /**
     * Tests the insertion of String values, including a duplicate, to create a
     * complex tree shape. Verifies the correct functioning of contains() and size()
     * methods,
     * and checks that the BST property is satisfied for all nodes.
     *
     * @return true if all test conditions pass, false otherwise
     */
    public boolean test2() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        // Insert strings to create a more complex tree
        bst.insert("monkey"); // Root
        bst.insert("cat"); // Left subtree
        bst.insert("zebra"); // Right subtree
        bst.insert("aardvark"); // Left-left leaf
        bst.insert("dog"); // Left-right subtree
        bst.insert("elephant"); // Left-right-left leaf
        bst.insert("fox"); // Left-right-right leaf
        bst.insert("yak"); // Right-left leaf
        bst.insert("monkey"); // Duplicate of root

        // Check if size is correct (should include the duplicate)
        boolean sizeCorrect = bst.size() == 9;

        // Test finding values in different positions of the tree
        boolean containsRoot = bst.contains("monkey"); // Root
        boolean containsLeftLeaf = bst.contains("aardvark"); // Left-most leaf
        boolean containsRightLeaf = bst.contains("zebra"); // Right-most leaf
        boolean containsInterior = bst.contains("cat") && bst.contains("dog"); // Interior nodes
        boolean containsDeepLeaves = bst.contains("elephant") && bst.contains("fox") && bst.contains("yak"); // Deeper
                                                                                                             // leaves
        boolean doesNotContain = !bst.contains("bear") && !bst.contains("lion"); // Non-existent values

        // Check that the BST property is satisfied for all nodes
        boolean bstPropertySatisfied = bst.isBSTPropertySatisfied();

        // All conditions must be true for the test to pass
        return sizeCorrect && containsRoot && containsLeftLeaf && containsRightLeaf &&
                containsInterior && containsDeepLeaves && doesNotContain && bstPropertySatisfied;
    }

    /**
     * Tests the functionality of isEmpty(), size(), and clear() methods.
     * This test builds, clears, and rebuilds a tree to ensure all operations work
     * correctly.
     * It also checks that the BST property is satisfied after each tree
     * modification.
     *
     * @return true if all test conditions pass, false otherwise
     */
    public boolean test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test initial empty state
        boolean initiallyEmpty = bst.isEmpty() && bst.size() == 0;

        // Build a tree
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);
        bst.insert(9);

        // Check non-empty state after insertions
        boolean nonEmpty = !bst.isEmpty() && bst.size() == 5;

        // Check that the BST property is satisfied after building
        boolean bstPropertySatisfied1 = bst.isBSTPropertySatisfied();

        // Clear the tree and check if it's empty
        bst.clear();
        boolean clearedEmpty = bst.isEmpty() && bst.size() == 0;

        // Rebuild a smaller tree
        bst.insert(10);
        bst.insert(20);

        // Check if the rebuilt tree is correct
        boolean rebuiltCorrect = !bst.isEmpty() && bst.size() == 2 && bst.contains(10) && bst.contains(20);

        // Check that the BST property is satisfied for the rebuilt tree
        boolean bstPropertySatisfied2 = bst.isBSTPropertySatisfied();

        // All conditions must be true for the test to pass
        return initiallyEmpty && nonEmpty && bstPropertySatisfied1 && clearedEmpty && rebuiltCorrect
                && bstPropertySatisfied2;
    }

    /**
     * Main method to run the test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println("Test 1 (Different insertion orders): " + (bst.test1() ? "PASS" : "FAIL"));
        System.out.println("Test 2 (String BST and duplicates): " + (bst.test2() ? "PASS" : "FAIL"));
        System.out.println("Test 3 (Size, clear, isEmpty): " + (bst.test3() ? "PASS" : "FAIL"));
    }
}