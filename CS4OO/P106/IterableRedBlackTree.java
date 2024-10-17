import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class extends RedBlackTree into a tree that supports iterating over the
 * values it stores in sorted, ascending order.
 */
public class IterableRedBlackTree<T extends Comparable<T>>
        extends RedBlackTree<T> implements IterableSortedCollection<T> {

    // Field to store the minimum for the iterator, or null if no minimum is set
    private Comparable<T> iteratorMin;

    // Field to store the maximum for the iterator, or null if no maximum is set
    private Comparable<T> iteratorMax;

    /**
     * Allows setting the start (minimum) value of the iterator.
     * 
     * @param min the minimum for iterators created for this tree, or null for no
     *            minimum
     */
    public void setIteratorMin(Comparable<T> min) {
        this.iteratorMin = min;
    }

    /**
     * Allows setting the stop (maximum) value of the iterator.
     * 
     * @param max the maximum for iterators created for this tree, or null for no
     *            maximum
     */
    public void setIteratorMax(Comparable<T> max) {
        this.iteratorMax = max;
    }

    /**
     * Returns an iterator over the values stored in this tree.
     * 
     * @return an iterator over the values stored in this tree
     */
    @Override
    public Iterator<T> iterator() {
        return new RBTIterator<T>(this.root, this.iteratorMin, this.iteratorMax);
    }

    /**
     * Nested class for Iterator objects created for this tree and returned by the
     * iterator method.
     */
    protected static class RBTIterator<R extends Comparable<R>> implements Iterator<R> {

        private final Comparable<R> min;
        private final Comparable<R> max;
        private final Stack<BSTNode<R>> stack;

        /**
         * Constructor for a new iterator.
         * 
         * @param root root node of the tree to traverse
         * @param min  the minimum value that the iterator will return
         * @param max  the maximum value that the iterator will return
         */
        public RBTIterator(BSTNode<R> root, Comparable<R> min, Comparable<R> max) {
            this.min = min;
            this.max = max;
            this.stack = new Stack<>();
            buildStackHelper(root);
        }

        /**
         * Helper method for initializing and updating the stack.
         *
         * @param node the root node of the subtree to process
         */
        private void buildStackHelper(BSTNode<R> node) {
            if (node == null) {
                return; // Base case: node is null
            }

            if (min == null || min.compareTo(node.data) <= 0) {
                // If node's value is >= min, push it and go left
                stack.push(node);
                buildStackHelper(node.left);
            } else {
                // If node's value < min, go right
                buildStackHelper(node.right);
            }
        }

        /**
         * Returns true if the iterator has another value to return, and false
         * otherwise.
         *
         * @return true if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                BSTNode<R> top = stack.peek();
                if (max != null && max.compareTo(top.data) < 0) {
                    stack.pop();
                } else {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns the next value of the iterator.
         * 
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public R next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iteration");
            }

            BSTNode<R> node = stack.pop();
            R result = node.data;

            if (node.right != null) {
                buildStackHelper(node.right);
            }

            return result;
        }
    }

    /**
     * Test method for iterating over a tree of integers with no min or max set.
     */
    @Test
    public void testIntegerIteratorNoMinMax() {
        IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
        // Add elements to the tree
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        // Create iterator with no min or max
        Iterator<Integer> iterator = tree.iterator();

        // Check if elements are returned in order
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertEquals(7, iterator.next());
        Assertions.assertEquals(9, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    /**
     * Test method for iterating over a tree of strings with a specified min value.
     */
    @Test
    public void testStringIteratorWithMin() {
        IterableRedBlackTree<String> tree = new IterableRedBlackTree<>();
        // Add elements to the tree
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("cherry");
        tree.insert("date");
        tree.insert("elderberry");

        // Set min value and create iterator
        tree.setIteratorMin("cherry");
        Iterator<String> iterator = tree.iterator();

        // Check if only elements >= "cherry" are returned
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("cherry", iterator.next());
        Assertions.assertEquals("date", iterator.next());
        Assertions.assertEquals("elderberry", iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    /**
     * Test method for iterating over a tree of integers with duplicates and both
     * min and max set.
     */
    @Test
    public void testIntegerIteratorWithDuplicatesAndMinMax() {
        IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
        // Add elements to the tree, including duplicates
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(3);
        tree.insert(5);
        tree.insert(9);

        // Set min and max values and create iterator
        tree.setIteratorMin(3);
        tree.setIteratorMax(7);
        Iterator<Integer> iterator = tree.iterator();

        // Check if elements between 3 and 7 (inclusive) are returned
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertEquals(5, iterator.next());
        Assertions.assertEquals(7, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }
}