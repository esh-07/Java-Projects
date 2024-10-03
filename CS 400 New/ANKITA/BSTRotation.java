/**
 * This class extends the BinarySearchTree_Placeholder class and contains the rotate method which appropriately
 * rotates the BST about its parent-child pair in the left or right direction. It also contains multiple test
 * cases to check the functioning of the method and its performance on exceptions and edge cases.
 *
 * @param <T> Any datatype for the Binary Search Tree for data it holds
 */

public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree<T> {

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
     *                                  nodes are not initially (pre-rotation) related that way
     */
    protected void rotate(BSTNode<T> child, BSTNode<T> parent)
            throws NullPointerException, IllegalArgumentException {
// Throwing null pointer exception if parameter is null
        if (child == null || parent == null) {
            throw new NullPointerException();
        }

        // Throwing Illegal argument exception if parameters are not connected
        if ((parent.left != child && parent.right != child)) {
           throw new IllegalArgumentException();
        }
// Setting parent of the parent node as a grandparent node
        BSTNode<T> gParent = parent.getUp();

        // If grandparent node is null, and parent node is null
        if (gParent == null) {
            // child becomes new node.
            this.root = child;
            child.setUp(null);
            // Case for right rotation
            if (parent.getLeft() == child) {
                // Updating left child of parent
                parent.setLeft(child.getRight());
                //Checking if right child of child node is null
                if (child.getRight() != null) {
                    child.getRight().setUp(parent);
                }
                child.setRight(parent);
                parent.setUp(child);

            }
            // Case for left rotation
            else {
                // Updating right child of parent
                parent.setRight(child.getLeft());
                //Checking if right child of child node is null
                if (child.getLeft() != null) {
                    child.getLeft().setUp(parent);
                }

                child.setLeft(parent);
                parent.setUp(child);
            }

            // When grand parent node exists
        } else {
            // Right Rotation case
            if (parent.getLeft() == child) {
                // Updating left child of parent
                parent.setLeft(child.getRight());

                if (child.getRight() != null) {
                    child.getRight().setUp(parent);
                }
//Updating right child of child node
                child.setRight(parent);
                parent.setUp(child);
// Updating child of grandparent node
                if (gParent.getLeft() == parent) {
                    gParent.setLeft(child);

                } else {
                    gParent.setRight(child);
                }

            }

            // Left rotation case
            else {
                // Updating right child of parent
                parent.setRight(child.getLeft());

                if (child.getLeft() != null) {
                    child.getLeft().setUp(parent);
                }
// Updating left child of child node
                child.setLeft(parent);
                parent.setUp(child);

                // Updating child of grandparent node
                if (gParent.getLeft() == parent) {
                    gParent.setLeft(child);

                } else {
                    gParent.setRight(child);
                }

            }
        }

    }

    /**
     * Tests left and right rotation in the rotate() method when none of the parameters are the root node.
     *
     * @return returns true if all cases pass and false otherwise
     */
    public boolean test01() {
        // Standard rotation cases
// E, B, H, A, D, F, J, C, G, I, K
        BSTRotation<String> testTree1 = new BSTRotation<>();
        testTree1.insert("E");
        testTree1.insert("B");
        testTree1.insert("H");
        testTree1.insert("A");
        testTree1.insert("D");
        testTree1.insert("F");
        testTree1.insert("J");
        testTree1.insert("C");
        testTree1.insert("G");
        testTree1.insert("I");
        testTree1.insert("K");


        //Right Rotation standard case
        testTree1.rotate(testTree1.root.getRight().getLeft(), testTree1.root.getRight());
        String expectedResult1 = "[ E, B, F, A, D, H, C, G, J, I, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult1 = testTree1.root.toLevelOrderString();

        if (!(expectedResult1.equals(actualResult1))) {
            return false;
        }

        //Left Rotation standard case
        BSTRotation<String> testTree2 = new BSTRotation<>();
        testTree2.insert("E");
        testTree2.insert("B");
        testTree2.insert("H");
        testTree2.insert("A");
        testTree2.insert("D");
        testTree2.insert("F");
        testTree2.insert("J");
        testTree2.insert("C");
        testTree2.insert("G");
        testTree2.insert("I");
        testTree2.insert("K");
        testTree2.rotate(testTree2.root.getLeft().getRight(), testTree2.root.getLeft());
        String expectedResult2 = "[ E, D, H, B, F, J, A, C, G, I, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult2 = testTree2.root.toLevelOrderString();

// Comparing rotation result with expected result
        if (!(expectedResult2.equals(actualResult2))) {
            return false;
        }


        return true;
    }

    /**
     * Tests left and right rotation in the rotate() method when one of the parameters is the root node.
     *
     * @return returns true if all cases pass and false otherwise
     */
    public boolean test02() {
        //Root Node rotation cases

        //Right Rotation root case
        BSTRotation<String> testTree3 = new BSTRotation<>();
        testTree3.insert("E");
        testTree3.insert("B");
        testTree3.insert("H");
        testTree3.insert("A");
        testTree3.insert("D");
        testTree3.insert("F");
        testTree3.insert("J");
        testTree3.insert("C");
        testTree3.insert("G");
        testTree3.insert("I");
        testTree3.insert("K");
        testTree3.rotate(testTree3.root.getLeft(), testTree3.root);
        String expectedResult3 = "[ B, A, E, D, H, C, F, J, G, I, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult3 = testTree3.root.toLevelOrderString();

// Comparing rotation result with expected result
        if (!(expectedResult3.equals(actualResult3))) {
            return false;
        }

        //Left Rotation root case
        BSTRotation<String> testTree4 = new BSTRotation<>();
        testTree4.insert("E");
        testTree4.insert("B");
        testTree4.insert("H");
        testTree4.insert("A");
        testTree4.insert("D");
        testTree4.insert("F");
        testTree4.insert("J");
        testTree4.insert("C");
        testTree4.insert("G");
        testTree4.insert("I");
        testTree4.insert("K");
        testTree4.rotate(testTree4.root.getRight(), testTree4.root);
        String expectedResult4 = "[ H, E, J, B, F, I, K, A, D, G, C ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult4 = testTree4.root.toLevelOrderString();

// Comparing rotation result with expected result
        if (!(expectedResult4.equals(actualResult4))) {
            return false;
        }
        return true;
    }

    /**
     * Tests rotate() method for when parent-child pairs of nodes that have between them 0 and 1 shared children.
     *
     * @return returns true if all cases pass and false otherwise
     */
    public boolean test03() {
        // 0 shared children case
        BSTRotation<String> testTree5 = new BSTRotation<>();
        testTree5.insert("E");
        testTree5.insert("B");
        testTree5.insert("H");
        testTree5.insert("A");
        testTree5.insert("D");
        testTree5.insert("F");
        testTree5.insert("J");
        testTree5.insert("C");
        testTree5.insert("G");
        testTree5.insert("I");
        testTree5.insert("K");
        testTree5.rotate(testTree5.root.getRight().getRight().getLeft(), testTree5.root.getRight().getRight());

        String expectedResult5 = "[ E, B, H, A, D, F, I, C, G, J, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult5 = testTree5.root.toLevelOrderString();

        // Comparing rotation result with expected result
        if (!(expectedResult5.equals(actualResult5))) {
            return false;
        }

        // 1 shared child case
        BSTRotation<String> testTree6 = new BSTRotation<>();
        testTree6.insert("E");
        testTree6.insert("B");
        testTree6.insert("H");
        testTree6.insert("A");
        testTree6.insert("D");
        testTree6.insert("F");
        testTree6.insert("J");
        testTree6.insert("C");
        testTree6.insert("G");
        testTree6.insert("I");
        testTree6.insert("K");
        testTree6.rotate(testTree6.root.getRight().getLeft(), testTree6.root.getRight());
        String expectedResult6 = "[ E, B, F, A, D, H, C, G, J, I, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult6 = testTree6.root.toLevelOrderString();

        // Comparing rotation result with expected result
        if (!(expectedResult6.equals(actualResult6))) {
            return false;
        }

        return true;
    }

    /**
     * Tests rotate() method for when parent-child pairs of nodes that have between them 2 and 3 shared children.
     *
     * @return returns true if all cases pass and false otherwise
     */
    public boolean test04() {
        // 2 shared children case
        BSTRotation<String> testTree7 = new BSTRotation<>();
        testTree7.insert("E");
        testTree7.insert("B");
        testTree7.insert("H");
        testTree7.insert("A");
        testTree7.insert("D");
        testTree7.insert("F");
        testTree7.insert("J");
        testTree7.insert("C");
        testTree7.insert("G");
        testTree7.insert("I");
        testTree7.insert("K");
        testTree7.rotate(testTree7.root.getRight().getRight(), testTree7.root.getRight());
        String expectedResult7 = "[ E, B, J, A, D, H, K, C, F, I, G ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult7 = testTree7.root.toLevelOrderString();

        // Comparing rotation result with expected result
        if (!(expectedResult7.equals(actualResult7))) {
            return false;
        }

        // 3 shared children case
        BSTRotation<String> testTree8 = new BSTRotation<>();
        testTree8.insert("E");
        testTree8.insert("B");
        testTree8.insert("H");
        testTree8.insert("A");
        testTree8.insert("D");
        testTree8.insert("F");
        testTree8.insert("J");
        testTree8.insert("C");
        testTree8.insert("G");
        testTree8.insert("I");
        testTree8.insert("K");
        testTree8.rotate(testTree8.root.getLeft(), testTree8.root);

        String expectedResult8 = "[ B, A, E, D, H, C, F, J, G, I, K ]";
        //Updating Actual result by using toLevelOrderString() method
        String actualResult8 = testTree8.root.toLevelOrderString();

        // Comparing rotation result with expected result
        if (!(expectedResult8.equals(actualResult8))) {
            return false;
        }

        return true;
    }

    /**
     * Testcase for rotate() method when parent child pair are unrelated or one of the arguments is null.
     * Checks if nullpointer exception and illegal argument exception are properly thrown and caught.
     *
     * @return returns true if all cases pass and false otherwise
     */
    public boolean test05() {
        // Catching the illegal argument exception when parent and child not related
        BSTRotation<String> testTree9 = new BSTRotation<>();
        testTree9.insert("E");
        testTree9.insert("B");
        testTree9.insert("H");
        testTree9.insert("A");
        testTree9.insert("D");
        testTree9.insert("F");
        testTree9.insert("J");
        testTree9.insert("C");
        testTree9.insert("G");
        testTree9.insert("I");
        testTree9.insert("K");
        try {
            testTree9.rotate(testTree9.root.getLeft().getLeft(), testTree9.root);
            return false;
        } catch (IllegalArgumentException exception) {
        }

        // Catching the null pointer exception when one of the parameters are null
        try {
            testTree9.rotate(testTree9.root.getLeft().getLeft().getRight(), testTree9.root.getLeft().getLeft());
            return false;
        } catch (NullPointerException exception) {
        }

        return true;
    }


    /**
     * Runs all the test cases to check the validity of the rotate method, checking edge cases and exceptions.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        BSTRotation<String> tree = new BSTRotation<>();
        System.out.println("Test01: " + tree.test01());
        System.out.println("Test02: " + tree.test02());
        System.out.println("Test03: " + tree.test03());
        System.out.println("Test04: " + tree.test04());
        System.out.println("Test05: " + tree.test05());
    }
}

