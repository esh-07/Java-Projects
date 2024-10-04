import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class represents a Red-Black Tree, a type of self-balancing binary
 * search tree. Each node in the tree contains an extra bit for denoting the
 * color of the node, either red or black.
 * 
 * Red-Black Trees maintain the following properties:
 * 1. Every node is either red or black.
 * 2. The root is black.
 * 3. Every leaf (null node) is black.
 * 4. If a node is red, then both its children are black.
 * 5. For each node, all simple paths from the node to descendant leaves contain
 * the same number of black nodes.
 *
 * These properties ensure that the tree remains approximately balanced,
 * guaranteeing O(log n) time complexity for basic operations like insertion,
 * deletion, and search.
 *
 * This class extends BSTRotation to leverage the rotation operations required
 * for maintaining tree balance during insertions.
 * 
 * @param <T> the type of elements held in this tree. The type must be
 *            comparable to support the binary search tree property.
 */
public class RedBlackTree<T extends Comparable<T>> extends BSTRotation<T> {

    /**
     * Inserts a new data value into the red-black tree.
     * This method overrides the insert method from BinarySearchTree to implement
     * the Red-Black Tree insertion algorithm.
     *
     * The insertion process involves the following steps:
     * 1. Perform a standard BST insertion.
     * 2. Color the new node red.
     * 3. Repair any Red-Black Tree property violations.
     * 4. Ensure the root is black.
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

        // Create a new red node
        RBTNode<T> newNode = new RBTNode<>(data);

        // If the tree is empty, make the new node the root
        if (root == null) {
            root = newNode;
        } else {
            // Otherwise, use the helper method to insert the node
            super.insertHelper(newNode, root);
        }

        // Repair any Red-Black Tree property violations
        ensureRedProperty(newNode);

        // Ensure the root is always black
        if (((RBTNode<T>) this.root).isRed()) {
            ((RBTNode<T>) this.root).flipColor();
        }
    }

    /**
     * Checks if a new red node in the RedBlackTree causes a red property violation
     * by having a red parent. If this is not the case, the method terminates
     * without making any changes to the tree. If a red property violation is
     * detected, then the method repairs this violation and any additional red
     * property violations that are generated as a result of the applied repair
     * operation.
     * 
     * This method implements the core logic of maintaining the Red-Black Tree
     * properties
     * after an insertion. It handles three main cases:
     * 1. Uncle is red: Recolor parent, uncle, and grandparent.
     * 2. Uncle is black (Left cases): Perform rotations and recolor.
     * 3. Uncle is black (Right cases): Perform rotations and recolor.
     *
     * @param newRedNode a newly inserted red node, or a node turned red by previous
     *                   repair
     */
    protected void ensureRedProperty(RBTNode<T> newRedNode) {
        // If the node is the root, we're done
        if (newRedNode == root)
            return;

        RBTNode<T> parent = newRedNode.getUp();
        // If the parent is black, we're done
        if (!parent.isRed())
            return;

        // At this point, we know we have a red-red violation
        RBTNode<T> grandparent = parent.getUp();
        RBTNode<T> uncle = (grandparent.getLeft() == parent) ? grandparent.getRight() : grandparent.getLeft();

        if (uncle != null && uncle.isRed()) {
            // Case 1: Uncle is red
            // Recolor parent, uncle, and grandparent
            parent.flipColor();
            uncle.flipColor();
            grandparent.flipColor();
            // Recursively check grandparent for violations
            ensureRedProperty(grandparent);
        } else {
            // Case 2 & 3: Uncle is black (or null)
            if (parent == grandparent.getLeft()) {
                if (newRedNode == parent.getRight()) {
                    // Case 2: Left-Right
                    // Perform left rotation on parent
                    rotate(newRedNode, parent);
                    parent = newRedNode;
                }
                // Case 3: Left-Left
                // Perform right rotation on grandparent
                rotate(parent, grandparent);
                parent.flipColor();
                grandparent.flipColor();
            } else {
                if (newRedNode == parent.getLeft()) {
                    // Case 2: Right-Left
                    // Perform right rotation on parent
                    rotate(newRedNode, parent);
                    parent = newRedNode;
                }
                // Case 3: Right-Right
                // Perform left rotation on grandparent
                rotate(parent, grandparent);
                parent.flipColor();
                grandparent.flipColor();
            }
        }
    }

    /**
     * Test case for inserting into an empty tree.
     * This test corresponds to the first step of Q03.RBTInsert quiz.
     */
    @Test
    public void testInsertIntoEmptyTree() {
        // Create a new Red-Black Tree
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert the first node (10) into the empty tree
        rbt.insert(10);

        // Check that the root is black and has the correct value
        RBTNode<Integer> root = (RBTNode<Integer>) rbt.root;
        Assertions.assertFalse(root.isRed(), "Root should be black after insertion into empty tree");
        Assertions.assertEquals(10, root.getData(), "Root should contain the inserted value");

        // Verify that the root has no children
        Assertions.assertNull(root.getLeft(), "Root should have no left child");
        Assertions.assertNull(root.getRight(), "Root should have no right child");
    }

    /**
     * Test case for inserting a node that causes a red violation and requires
     * recoloring. This test implements question 3 from
     * the Q03.RBTInsert quiz.
     */
    @Test
    public void testInsertCausingRedViolationRecolor() {
        RedBlackTree<Character> rbt = new RedBlackTree<>();

        // Step 1: Insert 'Q' as the root
        rbt.insert('Q');

        // Step 2: Insert 'H' as the left child of 'Q'
        rbt.insert('H');

        // Step 3: Insert 'U' as the right child of 'Q'
        rbt.insert('U');

        // Step 4: Insert 'E' as the left child of 'H', causing a red violation
        rbt.insert('E');

        // Check the structure and colors after insertion
        RBTNode<Character> root = (RBTNode<Character>) rbt.root;
        Assertions.assertFalse(root.isRed(), "Root 'Q' should be black");
        Assertions.assertEquals('Q', root.getData(), "Root should be 'Q'");

        RBTNode<Character> left = root.getLeft();
        Assertions.assertFalse(left.isRed(), "'H' should be black after recoloring");
        Assertions.assertEquals('H', left.getData(), "Left child of root should be 'H'");

        RBTNode<Character> right = root.getRight();
        Assertions.assertFalse(right.isRed(), "'U' should be black after recoloring");
        Assertions.assertEquals('U', right.getData(), "Right child of root should be 'U'");

        RBTNode<Character> leftLeft = left.getLeft();
        Assertions.assertTrue(leftLeft.isRed(), "'E' should remain red");
        Assertions.assertEquals('E', leftLeft.getData(), "Left child of 'H' should be 'E'");
    }

    /**
     * Test case for inserting a node that causes a red violation and requires
     * rotation. This test implements question 2 from
     * the Q03.RBTInsert quiz.
     */
    @Test
    public void testInsertCausingRedViolationRotation() {
        RedBlackTree<Character> rbt = new RedBlackTree<>();

        // Step 1: Build initial tree structure
        rbt.insert('L');
        rbt.insert('D');
        rbt.insert('R');
        rbt.insert('B');
        rbt.insert('G');
        rbt.insert('W');

        // Step 2: Insert 'S', which should cause a rotation
        rbt.insert('S');

        // Check the structure and colors after insertion and rotation
        RBTNode<Character> root = (RBTNode<Character>) rbt.root;
        Assertions.assertFalse(root.isRed(), "Root 'L' should be black");
        Assertions.assertEquals('L', root.getData(), "Root should remain 'L'");

        RBTNode<Character> left = root.getLeft();
        Assertions.assertFalse(left.isRed(), "'D' should be black");
        Assertions.assertEquals('D', left.getData(), "Left subtree root should be 'D'");

        RBTNode<Character> right = root.getRight();
        Assertions.assertFalse(right.isRed(), "'S' should be black after rotation");
        Assertions.assertEquals('S', right.getData(), "Right subtree root should now be 'S'");

        RBTNode<Character> rightLeft = right.getLeft();
        Assertions.assertTrue(rightLeft.isRed(), "'R' should be red");
        Assertions.assertEquals('R', rightLeft.getData(), "Left child of 'S' should be 'R'");

        RBTNode<Character> rightRight = right.getRight();
        Assertions.assertTrue(rightRight.isRed(), "'W' should be red");
        Assertions.assertEquals('W', rightRight.getData(), "Right child of 'S' should be 'W'");

        // Check 'B' and 'G' as children of 'D'
        RBTNode<Character> leftLeft = left.getLeft();
        Assertions.assertTrue(leftLeft.isRed(), "'B' should be red");
        Assertions.assertEquals('B', leftLeft.getData(), "Left child of 'D' should be 'B'");

        RBTNode<Character> leftRight = left.getRight();
        Assertions.assertTrue(leftRight.isRed(), "'G' should be red");
        Assertions.assertEquals('G', leftRight.getData(), "Right child of 'D' should be 'G'");
    }

    /**
     * Test case for inserting a node that causes a red violation.
     * This test implements an example from Q03.RBTInsert quiz.
     */
    @Test
    public void testInsertCausingRedViolation() {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Step 1: Insert root node
        rbt.insert(10);

        // Step 2: Insert left and right children
        rbt.insert(5);
        rbt.insert(15);

        // Step 3: Insert 3, causing a red violation
        rbt.insert(3);

        // Check the structure and colors after insertion
        RBTNode<Integer> root = (RBTNode<Integer>) rbt.root;
        Assertions.assertFalse(root.isRed(), "Root 10 should be black");
        Assertions.assertEquals(10, root.getData(), "Root should be 10");

        RBTNode<Integer> left = root.getLeft();
        Assertions.assertFalse(left.isRed(), "5 should be black after recoloring");
        Assertions.assertEquals(5, left.getData(), "Left child of root should be 5");

        RBTNode<Integer> right = root.getRight();
        Assertions.assertFalse(right.isRed(), "15 should be black after recoloring");
        Assertions.assertEquals(15, right.getData(), "Right child of root should be 15");

        RBTNode<Integer> leftLeft = left.getLeft();
        Assertions.assertTrue(leftLeft.isRed(), "3 should be red");
        Assertions.assertEquals(3, leftLeft.getData(), "Left child of 5 should be 3");
    }

    /**
     * Test case for a complex insertion sequence that requires multiple rotations
     * and recoloring. This test ensures that the Red-Black Tree properties are
     * maintained even after a series of insertions that trigger various cases
     * of the balancing algorithm.
     */
    @Test
    public void testComplexInsertionSequence() {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Step 1: Insert root and its immediate children
        rbt.insert(10);
        rbt.insert(5);
        rbt.insert(15);

        // Step 2: Insert nodes that will cause rotations and recoloring
        rbt.insert(3);
        rbt.insert(7);
        rbt.insert(12);
        rbt.insert(18);

        // Check the final structure and colors
        RBTNode<Integer> root = (RBTNode<Integer>) rbt.root;
        Assertions.assertFalse(root.isRed(), "Root 10 should be black");
        Assertions.assertEquals(10, root.getData(), "Root should be 10");

        RBTNode<Integer> left = root.getLeft();
        Assertions.assertFalse(left.isRed(), "5 should be black");
        Assertions.assertEquals(5, left.getData(), "Left subtree root should be 5");

        RBTNode<Integer> right = root.getRight();
        Assertions.assertFalse(right.isRed(), "15 should be black");
        Assertions.assertEquals(15, right.getData(), "Right subtree root should be 15");

        RBTNode<Integer> leftLeft = left.getLeft();
        Assertions.assertTrue(leftLeft.isRed(), "3 should be red");
        Assertions.assertEquals(3, leftLeft.getData(), "Left child of 5 should be 3");

        RBTNode<Integer> leftRight = left.getRight();
        Assertions.assertTrue(leftRight.isRed(), "7 should be red");
        Assertions.assertEquals(7, leftRight.getData(), "Right child of 5 should be 7");

        RBTNode<Integer> rightLeft = right.getLeft();
        Assertions.assertTrue(rightLeft.isRed(), "12 should be red");
        Assertions.assertEquals(12, rightLeft.getData(), "Left child of 15 should be 12");

        RBTNode<Integer> rightRight = right.getRight();
        Assertions.assertTrue(rightRight.isRed(), "18 should be red");
        Assertions.assertEquals(18, rightRight.getData(), "Right child of 15 should be 18");
    }
}