//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P101
// Course:   CS 400 Fall 2024
//
// Author:   Ankit Modi
// Email:    akmodi@wisc.edu
// Lecturer: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// None
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains methods to insert nodes into the tree, check if if it contains some data,
 * clear the tree, checks its size and if it is empty along with testers
 *
 * @param <T> Any datatype for the data in the tree
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    protected BSTNode<T> root;

    /**
     * Inserts a new data value into the sorted collection appropriately handling duplicate cases.
     *
     * @param data the new value being inserted
     * @throws NullPointerException if the data argument is null, as null values are not allowed
     */
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot insert null data.");
        }

        BSTNode<T> newNode = new BSTNode<>(data);

        if (root == null) {
            root = newNode; // Case for empty tree
        } else {
            insertHelper(newNode, root); // Recursive method call
        }
    }

    /**
     * Recursively inserts the newNode into the correct position in the subtree rooted at subtree.
     *
     * @param newNode the node to be inserted
     * @param subtree the root of the subtree where the newNode should be inserted
     */
    protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {
        // base case to exit the recursive method
        if (subtree == null) {
            return;
        }

        int comparison = newNode.getData().compareTo(subtree.getData());

        if (comparison > 0) {
            // node data is greater than the subtree data, we look at the right child
            if (subtree.getRight() == null) {
                newNode.setUp(subtree);
                subtree.setRight(newNode);
            } else {
                insertHelper(newNode, subtree.getRight());
            }
        } else {
            // node data is less than the subtree data, we look at the left child or duplicate case
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getLeft());
            }
        }
    }


    /**
     * Checks whether a given data value is present in the tree.
     *
     * @param data the value to check for
     * @return true if the data is found, false otherwise
     */
    public boolean contains(Comparable<T> data) {

        return containsHelper(root, data);
    }

    /**
     * Recursively searches for parameter data in the tree.
     *
     * @param node the root of the subtree to search
     * @param data the value to search for
     * @return true if the data is found, false otherwise
     */
    private boolean containsHelper(BSTNode<T> node, Comparable<T> data) {
        if (node == null) {
            return false; // Base case: data not found if node is null
        }

        int comparison = data.compareTo(node.getData());

        if (comparison > 0) {
            return containsHelper(node.getRight(), data); // Search in the right subtree
        } else if (comparison < 0) {
            return containsHelper(node.getLeft(), data); // Search in the left subtree
        } else {
            return true; // Data found
        }
    }

    /**
     * Counts the total number of nodes in the tree, including duplicates.
     *
     * @return the number of values in the tree
     */
    public int size() {
        return sizeHelper(root);
    }

    /**
     * Recursively counts the number of nodes in the subtree from the specified node.
     *
     * @param node the root of the subtree to count
     * @return the number of nodes in the subtree
     */
    private int sizeHelper(BSTNode<T> node) {
        if (node == null) {
            return 0; // Base case: empty subtree contributes 0 to the count
        }

        // Count the current node plus nodes in the left and right subtrees
        return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
    }

    /**
     * Checks if the tree is empty or not.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {

        return root == null; // Tree is empty if the root is null
    }

    /**
     * Removes all nodes and empties the search tree.
     */
    public void clear() {

        root = null; // Setting root to null effectively removes all nodes
    }

    // Test cases to verify the correctness of the BinarySearchTree implementation

    /**
     * Test case to check if binary search tree methods work properly for integer values
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean test1() {

        BinarySearchTree<Integer> testTree1 = new BinarySearchTree<>();

        // try catch block to check for null pointer string
        try {
            testTree1.insert(null);
            return false;
        } catch (NullPointerException ignored) {

        }

        if (!testTree1.isEmpty()) {
            return false; // Tree should be empty initially
        }

        // Insert elements and check size and containment
        testTree1.insert(25);
        testTree1.insert(10);
        testTree1.insert(18);
        testTree1.insert(40);
        testTree1.insert(15);

        // Checking if all elements are inserted in the appropriate order
        String expected = "[ 25, 10, 40, 18, 15 ]";
        String actualOrder = testTree1.root.toLevelOrderString();
        if (!(expected.equals(actualOrder))) {
            return false;
        }

        if (testTree1.size() != 5) {
            return false; // Size should be 5
        }
        if (!testTree1.contains(25)) {
            return false;
        }
        if (!testTree1.contains(10)) {
            return false;
        }
        if (!testTree1.contains(18)) {
            return false;
        }
        if (!testTree1.contains(40)) {
            return false;
        }
        if (!testTree1.contains(15)) {
            return false;
        }
        if (testTree1.contains(1)) {
            return false;
        }


        // Clear the tree and check if it's empty
        testTree1.clear();
        if (!(testTree1.isEmpty())) {
            return false;
        }
        if (testTree1.size() != 0) {
            return false;
        }
        return true;
    }

    /**
     * Test case to check if binary search tree methods work properly for integer values
     * along with duplicate data values as values.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean test2() {
        BinarySearchTree<Integer> testTree2 = new BinarySearchTree<>();

        if (!testTree2.isEmpty()) {
            return false; // Tree should be empty initially
        }

        // Insert elements and check size and containment
        testTree2.insert(60);
        testTree2.insert(20);
        testTree2.insert(80);
        testTree2.insert(20);
        testTree2.insert(30);
        testTree2.insert(100);

        // Checking if all elements are inserted in the appropriate order
        String expected = "[ 60, 20, 80, 20, 30, 100 ]";
        String actualOrder = testTree2.root.toLevelOrderString();
        if (!(expected.equals(actualOrder))) {
            return false;
        }

        if (testTree2.size() != 6) {
            return false; // Size should be 6
        }
        if (!testTree2.contains(60)) {
            return false;
        }
        if (!testTree2.contains(20)) {
            return false;
        }
        if (!testTree2.contains(80)) {
            return false;
        }
        if (!testTree2.contains(20)) {
            return false;
        }
        if (!testTree2.contains(30)) {
            return false;
        }
        if (!testTree2.contains(100)) {
            return false;
        }

        // Clear the tree and check if it's empty
        testTree2.clear();
        if (!(testTree2.isEmpty())) {
            return false;
        }
        if (!(testTree2.size() == 0)) {
            return false;
        }
        return true;
    }

    /**
     * Test case to check if binary search tree methods work properly for string values
     * along with duplicate data values as values.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean test3() {
        BinarySearchTree<String> testTree3 = new BinarySearchTree<>();

        if (!testTree3.isEmpty()) {
            return false; // Tree should be empty initially
        }

        // Insert elements and check size and containment
        testTree3.insert("Panda");
        testTree3.insert("Dog");
        testTree3.insert("Zebra");
        testTree3.insert("Horse");
        testTree3.insert("Monkey");
        testTree3.insert("Bat");
        testTree3.insert("Bat"); // Inserting duplicate

        // Checking if all elements are inserted in the appropriate order
        String expected = "[ Panda, Dog, Zebra, Bat, Horse, Bat, Monkey ]";
        String actualOrder = testTree3.root.toLevelOrderString();
        if (!(expected.equals(actualOrder))) {
            return false;
        }

        if (testTree3.size() != 7) {
            return false; // Size should be 7
        }
        if (!testTree3.contains("Panda")) {
            return false;
        }
        if (!testTree3.contains("Dog")) {
            return false;
        }
        if (!testTree3.contains("Zebra")) {
            return false;
        }
        if (!testTree3.contains("Horse")) {
            return false;
        }
        if (!testTree3.contains("Monkey")) {
            return false;
        }
        if (!testTree3.contains("Bat")) {
            return false;
        }
        if (testTree3.contains("Cat")) {
            return false;
        }


        // Clearing the tree and checking if it's empty
        testTree3.clear();
        if (!(testTree3.isEmpty())) {
            return false;
        }
        if (!(testTree3.size() == 0)) {
            return false;
        }
        return true;
    }

    /**
     * Test cases to check if binary search tree containing string data implements the
     * size(), isEmpty() and clear() methods properly
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean test4() {
        BinarySearchTree<String> testTree4 = new BinarySearchTree<>();

        // try catch block to check for null pointer string
        try {
            testTree4.insert(null);
            return false;
        } catch (NullPointerException ignored) {

        }

        if (!testTree4.isEmpty()) {
            return false; // Tree should be empty initially
        }

        // Insert elements and check size and containment
        testTree4.insert("Dog");
        testTree4.insert("Bat");
        testTree4.insert("Panda");
        testTree4.insert("Horse");
        testTree4.insert("Zebra");
        testTree4.insert("Bat"); // Inserting duplicate
        testTree4.insert("Monkey");

        if (testTree4.size() != 7) {
            return false; // Size should be 7
        }

        if (testTree4.isEmpty()) {
            return false; // Tree should not be empty
        }

        // Clearing the tree and checking if it's empty
        testTree4.clear();
        if (!(testTree4.size() == 0)) {
            return false;
        }
        return true;
    }

    /**
     * Test case to check for insertion of duplicates in BST
     * @return
     */
    public static boolean test5() {
        BinarySearchTree<String> testTree5 = new BinarySearchTree<>();


        testTree5.insert("R");
        testTree5.insert("A");
        testTree5.insert("Z");
        testTree5.insert("R");
        testTree5.insert("R");


        // Checking if all duplicte elements are inserted in the appropriate order
        String expected = "[ R, A, Z, R, R ]";
        String actualOrder = testTree5.root.toLevelOrderString();
        if (!(expected.equals(actualOrder))) {
            return false;
        }

        if (testTree5.size() != 5) {
            return false; // Size should be 7
        }


        return true;
    }

    /**
     * Main method to run all the test cases and get the result output values.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Test case 1: " + test1());
        System.out.println("Test case 2: " + test2());
        System.out.println("Test case 3: " + test3());
        System.out.println("Test case 4: " + test4());
        System.out.println("Test Case 5: " + test5());
    }
}
