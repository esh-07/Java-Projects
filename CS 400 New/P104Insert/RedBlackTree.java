/**
 * This class represents a Red-Black Tree, a type of self-balancing binary
 * search tree.
 * Each node in the tree contains an extra bit for denoting the color of the
 * node, either red or black.
 * The class extends BSTRotation to leverage the rotation operations required
 * for maintaining tree balance.
 * The tree ensures balance by coloring nodes and performing certain operations
 * (rotations and color flips)
 * while inserting nodes and deleting nodes.
 * 
 * @param <T> the type of elements held in this tree. The type must be
 *            comparable.
 */
public class RedBlackTree<T extends Comparable<T>> extends BSTRotation<T> {

    /**
     * Inserts a new data value into the red-black tree.
     * Overrides the insert method from BinarySearchTree.
     *
     * @param data the new value being inserted
     * @throws NullPointerException if data argument is null
     */
    @Override
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot insert null value");
        }

        RBTNode<T> newNode = new RBTNode<>(data);

        if (root == null) {
            root = newNode;
        } else {
            super.insertHelper(newNode, root);
        }

        ensureRedProperty(newNode);

        // Ensure the root is always black
        ((RBTNode<T>) this.root).isRed = false;
    }

    /**
     * Checks if a new red node in the RedBlackTree causes a red property violation
     * by having a red parent. If this is not the case, the method terminates
     * without
     * making any changes to the tree. If a red property violation is detected, then
     * the method repairs this violation and any additional red property violations
     * that are generated as a result of the applied repair operation.
     * 
     * @param newRedNode a newly inserted red node, or a node turned red by previous
     *                   repair
     */
    protected void ensureRedProperty(RBTNode<T> newRedNode) {
        // TODO: Implement this method.
        // This method will be implemented in the final submission
    }

    /**
     * Main method to run basic tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert some values
        rbt.insert(10);
        rbt.insert(5);
        rbt.insert(15);
        rbt.insert(3);
        rbt.insert(7);

        // Basic tests
        System.out.println("Test 1 (Size of the tree): " + (rbt.size() == 5 ? "PASS" : "FAIL"));
        System.out.println("Test 2 (Contains 7): " + (rbt.contains(7) ? "PASS" : "FAIL"));
        System.out.println("Test 3 (Does not contain 20): " + (!rbt.contains(20) ? "PASS" : "FAIL"));
        System.out.println("Test 4 (Root is black): " + (!((RBTNode<Integer>) rbt.root).isRed() ? "PASS" : "FAIL"));

        // Test empty tree
        RedBlackTree<Integer> emptyRbt = new RedBlackTree<>();
        System.out.println("Test 5 (Empty tree size): " + (emptyRbt.size() == 0 ? "PASS" : "FAIL"));
        System.out.println("Test 6 (Empty tree is empty): " + (emptyRbt.isEmpty() ? "PASS" : "FAIL"));

        // Test insertion and contains with strings
        RedBlackTree<String> stringRbt = new RedBlackTree<>();
        stringRbt.insert("apple");
        stringRbt.insert("banana");
        stringRbt.insert("cherry");
        System.out.println("Test 7 (String tree size): " + (stringRbt.size() == 3 ? "PASS" : "FAIL"));
        System.out.println("Test 8 (Contains 'banana'): " + (stringRbt.contains("banana") ? "PASS" : "FAIL"));
        System.out.println("Test 9 (Does not contain 'date'): " + (!stringRbt.contains("date") ? "PASS" : "FAIL"));

        // Note: These tests don't verify all red-black properties
        // More comprehensive tests should be added in the final submission
    }
}