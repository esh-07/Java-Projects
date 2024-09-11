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
        if (data == null) {
            throw new NullPointerException("Cannot insert null value");
        }
        BSTNode<T> newNode = new BSTNode<>(data);
        if (root == null) {
            root = newNode;
        } else {
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
        if (subtree == null) {
            return; // Do nothing when subtree is null
        }
        int compareResult = newNode.getData().compareTo(subtree.getData());
        if (compareResult < 0) {
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getLeft());
            }
        } else {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                newNode.setUp(subtree);
            } else {
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
        if (node == null) {
            return false;
        }
        int compareResult = data.compareTo(node.getData());
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return containsHelper(data, node.getLeft());
        } else {
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
     * Tests the insertion of multiple values to create a complex tree shape,
     * and verifies the correct functioning of contains() and size() methods.
     * This test uses an Integer BST.
     *
     * @return true if all test conditions pass, false otherwise
     */
    public boolean test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Create a complex tree shape with integers

        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        // Check if the size is correct after insertions
        boolean sizeCorrect = bst.size() == 7;

        // Test finding values in different positions of the tree
        boolean containsLeftLeaf = bst.contains(1); // Left leaf
        boolean containsRightLeaf = bst.contains(8); // Right leaf
        boolean containsInterior = bst.contains(3) && bst.contains(7); // Interior nodes
        boolean containsRoot = bst.contains(5); // Root
        boolean doesNotContain = !bst.contains(2) && !bst.contains(9); // Non-existent values

        // All conditions must be true for the test to pass
        return sizeCorrect && containsLeftLeaf && containsRightLeaf &&
                containsInterior && containsRoot && doesNotContain;
    }

    /**
     * Tests the insertion of String values, including a duplicate.
     * Verifies the correct functioning of contains() and size() methods with
     * strings.
     * This test uses a String BST.
     *
     * @return true if all test conditions pass, false otherwise
     */
    public boolean test2() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        // Insert strings to create a tree

        bst.insert("dog");
        bst.insert("cat");
        bst.insert("elephant");
        bst.insert("bear");
        bst.insert("fox");

        // Insert a duplicate to test handling of duplicates
        bst.insert("dog");

        // Check if size is correct (should include the duplicate)
        boolean sizeCorrect = bst.size() == 6;

        // Test finding values in different positions of the tree
        boolean containsRoot = bst.contains("dog"); // Root
        boolean containsLeaves = bst.contains("bear") && bst.contains("fox"); // Leaves
        boolean containsInterior = bst.contains("cat") && bst.contains("elephant"); // Interior
        boolean doesNotContain = !bst.contains("zebra"); // Non-existent value

        // All conditions must be true for the test to pass
        return sizeCorrect && containsRoot && containsLeaves && containsInterior && doesNotContain;
    }

    /**
     * Tests the functionality of isEmpty(), size(), and clear() methods.
     * This test builds, clears, and rebuilds a tree to ensure all operations work
     * correctly.
     * This test uses an Integer BST.
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

        // Clear the tree and check if it's empty
        bst.clear();
        boolean clearedEmpty = bst.isEmpty() && bst.size() == 0;

        // Rebuild a smaller tree
        bst.insert(10);
        bst.insert(20);

        // Check if the rebuilt tree is correct
        boolean rebuiltCorrect = !bst.isEmpty() && bst.size() == 2 && bst.contains(10) && bst.contains(20);

        // All conditions must be true for the test to pass
        return initiallyEmpty && nonEmpty && clearedEmpty && rebuiltCorrect;
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