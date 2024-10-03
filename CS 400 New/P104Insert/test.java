/**
 * Extends the BSTRotation class and contains methods to insert a new node into
 * the
 * tree and then fix all violations of the red black tree.
 *
 * @param <T> Any data type for the red black tree
 */
public class test<T extends Comparable<T>> extends BSTRotation<T> {

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
        ensureRedPropertyHelper(newRedNode);
    }

    private void ensureRedPropertyHelper(RBTNode<T> newNode) {

        if (newNode.getUp() == null || !(newNode.getUp().isRed)) { // base case no violation
            return;
        }
        // if(newNode.getUp().getUp() == null){
        // return;
        // }

        RBTNode<T> aunt;
        RBTNode<T> parent = newNode.getUp();
        RBTNode<T> gParent = parent.getUp();
        // find out parent is left or right child of grandparent
        boolean child = parent.isRightChild();

        if (child) {
            aunt = gParent.getLeft();
        } else {
            aunt = gParent.getRight();
        }

        // red aunt case parent and gparent swap color
        if (aunt != null && aunt.isRed) {
            newNode.getUp().getUp().flipColor();
            newNode.getUp().flipColor();
            aunt.flipColor();
            ensureRedPropertyHelper(gParent);
        } else {
            // black aunt or null aunt case
            if ((parent.isRightChild() && newNode.isRightChild())
                    || (!parent.isRightChild() && !newNode.isRightChild())) {
                // subcase of black line rotate and swap color bw parent and gparent
                BSTRotation<T> object = new BSTRotation<>();
                object.rotate(parent, gParent);
                parent.flipColor();
                gParent.flipColor();
                ensureRedPropertyHelper(parent);
            }

            else {
                // subcase of black zig rotate parent and child nodes
                BSTRotation<T> object = new BSTRotation<>();
                object.rotate(newNode, parent);
                ensureRedPropertyHelper(parent);
            }

        }

    }

    /**
     * Inserts a new node into the red black tree, ensuring that the new node is red
     * and that post
     * inserting the root node is black. This is done usng the isRed and flipColor
     * methods.
     *
     * @param data the new value being insterted
     * @throws NullPointerException when data is null.
     */
    @Override
    public void insert(T data) throws NullPointerException {

        if (data == null) {
            throw new NullPointerException("Cannot insert null data.");
        }
        // Creating an instance of RBTNode
        RBTNode<T> newNode = new RBTNode<>(data);

        // New node inserted should always be red
        boolean nodeColor = ((RBTNode<T>) newNode).isRed;
        if (!nodeColor) {
            ((RBTNode<T>) newNode).flipColor();
        }

        if (root == null) {
            root = newNode; // Case for empty tree
        } else {

            insertHelper(newNode, root); // Recursive method call
            ensureRedProperty(newNode);
        }

        // Making the root node black.
        boolean rootColor = ((RBTNode<T>) this.root).isRed;
        if (rootColor) {
            ((RBTNode<T>) this.root).flipColor();
        }
    }

    /**
     *
     */
    public static boolean testCase1() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(12);
        tree.insert(37);
        tree.insert(65);
        tree.insert(90);
        tree.insert(30);
        tree.insert(45);

        // Inserting to check for proper functioning in red aunt case w/o recursion need
        tree.insert(95);

        RBTNode<Integer> root = (RBTNode<Integer>) tree.root;

        String expectedTraversal = "[ 50(b), 25(r), 75(r), 12(b), 37(b), 65(b), 90(b), 30(r), 45(r), 95(r) ]";

        String actualTraversal = tree.root.toLevelOrderString();
        System.out.println(actualTraversal);
        if (!(expectedTraversal.equals(actualTraversal))) {
            return false;
        }

        if (((RBTNode<Integer>) tree.root).isRed) {
            return false;
        }

        return true;
    }

    /**
     *
     */
    public static boolean testCase2() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(12);
        tree.insert(37);
        tree.insert(65);
        tree.insert(90);
        tree.insert(30);
        tree.insert(45);

        // Inserting to check for proper functioning in red aunt case with recursion
        // need
        tree.insert(47);

        RBTNode<Integer> root = (RBTNode<Integer>) tree.root;

        String expectedTraversal = "[ 50(b), 25(r), 75(r), 12(b), 37(b), 65(b), 90(b), 30(r), 45(r), 95(r) ]";

        String actualTraversal = tree.root.toLevelOrderString();
        System.out.println(actualTraversal);
        if (!(expectedTraversal.equals(actualTraversal))) {
            return false;
        }

        if (((RBTNode<Integer>) tree.root).isRed) {
            return false;
        }

        return true;
    }

    /**
     *
     */
    public static boolean testCase3() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        return true;
    }

    /**
     *
     */
    public static boolean testCase4() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        return true;
    }

    /**
     *
     */
    public static boolean testCase5() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        return true;
    }

    /**
     *
     */
    public static boolean testCase6() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        return true;
    }

    /**
     *
     */
    public static boolean testCase7() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("testCase1:" + testCase1());
        System.out.println("testCase2:" + testCase2());
        System.out.println("testCase3:" + testCase3());
        System.out.println("testCase4:" + testCase4());
        System.out.println("testCase5:" + testCase5());
        System.out.println("testCase6:" + testCase6());
        System.out.println("testCase7:" + testCase7());

    }

    // /**
    // * Tests the handling of the 'black aunt' scenario in a Red-Black Tree. This
    // scenario involves
    // * more complex rotations and recolorings when a node's aunt (the sibling of
    // its parent) is black
    // * or null, which is crucial for tree balancing. The test verifies that the
    // tree properly adjusts
    // * its structure to maintain the red-black properties.Used this example from
    // Professor Florian's
    // * lecture
    // */

    // @Test
    // public void testBlackAuntCase() {
    // RedBlackTree<Integer> tree = new RedBlackTree<>();
    // // Various insertions
    // tree.insert(7);
    // tree.insert(14);
    // tree.insert(18);
    // tree.insert(23);
    // tree.insert(1);
    // tree.insert(11);
    // tree.insert(20);
    // tree.insert(29);
    // tree.insert(25);
    // tree.insert(27);
    //
    // // With the root as RBTNode for direct access to BSTNode methods
    // RBTNode<Integer> rootNode = (RBTNode<Integer>) tree.root;
    //
    //
    // // Define expected structure using in level order and in-order
    // String expectedLevelOrder =
    // "[ 20(b), 14(r), 25(r), 7(b), 18(b), 23(b), 29(b), 1(r), 11(r), 27(r) ]";
    //
    // // Assertions using the traversal methods from BSTNode
    // Assertions.assertEquals(expectedLevelOrder, rootNode.toLevelOrderString(),
    // "Level order traversal does not match expected output.");
    //
    // Assertions.assertFalse(rootNode.isRed(), "Root should be black.");
    //
    // }

}