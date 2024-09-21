//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MarkovTester
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * The MarkovTester class is used to test the functionality of the MyStack and
 * MyQueue classes. It
 * contains several static methods, each of which tests a specific functionality
 * of the MyStack or
 * MyQueue class.
 */
public class MarkovTester {

    /**
     * Tests the push operation of the MyStack class.
     * 
     * @return true if the push operation works as expected, false otherwise.
     */
    public static boolean testStackAdd() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1); // Pushing 1 to the stack
        stack.push(2); // Pushing 2 to the stack
        stack.push(3); // Pushing 3 to the stack
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3); // Adding elements to the expected list in the order they would be in the stack
        expected.add(2);
        expected.add(1);
        return stack.getList().equals(expected); // Comparing the stack's list with the expected list
    }

    /**
     * Tests the pop operation of the MyStack class.
     * 
     * @return true if the pop operation works as expected, false otherwise.
     */
    public static boolean testStackRemove() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1); // Pushing 1 to the stack
        stack.push(2); // Pushing 2 to the stack
        stack.push(3); // Pushing 3 to the stack
        stack.pop(); // Popping the top element from the stack
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2); // Adding elements to the expected list in the order they would be in the stack
        expected.add(1);
        return stack.getList().equals(expected) && stack.pop() == 2 && stack.pop() == 1
                && stack.isEmpty(); // Checking
                                    // if the
                                    // stack's
                                    // list
                                    // matches
                                    // the
                                    // expected
                                    // list and
                                    // if the
                                    // popped
                                    // elements
                                    // are as
                                    // expected
    }

    /**
     * Tests the shuffle operation of the MyStack class.
     * 
     * @return true if the shuffle operation works as expected, false otherwise.
     */
    public static boolean testStackShuffle() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1); // Pushing 1 to the stack
        stack.push(2); // Pushing 2 to the stack
        stack.push(3); // Pushing 3 to the stack
        ArrayList<Integer> original = new ArrayList<>(stack.getList()); // Saving the original list for
                                                                        // comparison
        boolean hasShuffled = false;
        for (int i = 0; i < 10; i++) {
            stack.shuffle(); // Shuffling the stack
            ArrayList<Integer> shuffled = stack.getList(); // Getting the shuffled list
            if (!original.equals(shuffled)) {
                hasShuffled = true; // If the shuffled list is different from the original, set hasShuffled
                                    // to true
            }
            if (!original.containsAll(shuffled) || !shuffled.containsAll(original)) {
                return false; // If the shuffled list doesn't contain all elements from the original list or
                              // vice versa, return false
            }
        }
        return hasShuffled; // Return whether the list has been shuffled
    }

    /**
     * Tests the enqueue operation of the MyQueue class.
     * 
     * @return true if the enqueue operation works as expected, false otherwise.
     */
    public static boolean testQueueAdd() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1); // Enqueueing 1 to the queue
        queue.enqueue(2); // Enqueueing 2 to the queue
        queue.enqueue(3); // Enqueueing 3 to the queue
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1); // Adding elements to the expected list in the order they would be in the queue
        expected.add(2);
        expected.add(3);
        return queue.getList().equals(expected); // Comparing the queue's list with the expected list
    }

    /**
     * Tests the dequeue operation of the MyQueue class.
     * 
     * @return true if the dequeue operation works as expected, false otherwise.
     */
    public static boolean testQueueRemove() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1); // Enqueueing 1 to the queue
        queue.enqueue(2); // Enqueueing 2 to the queue
        queue.enqueue(3); // Enqueueing 3 to the queue
        queue.dequeue(); // Dequeueing the front element from the queue
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2); // Adding elements to the expected list in the order they would be in the queue
        expected.add(3);
        return queue.getList().equals(expected) && queue.dequeue() == 2 && queue.peek() == 3; // Checking
                                                                                              // if the
                                                                                              // queue's
                                                                                              // list
                                                                                              // matches
                                                                                              // the
                                                                                              // expected
                                                                                              // list
                                                                                              // and if
                                                                                              // the
                                                                                              // dequeued
                                                                                              // and
                                                                                              // peeked
                                                                                              // elements
                                                                                              // are as
                                                                                              // expected
    }

    /**
     * Tests the peek operation of the MyStack and MyQueue classes.
     * 
     * @return true if the peek operation works as expected, false otherwise.
     */
    public static boolean testPeek() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1); // Pushing 1 to the stack
        stack.push(2); // Pushing 2 to the stack
        stack.push(3); // Pushing 3 to the stack
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1); // Enqueueing 1 to the queue
        queue.enqueue(2); // Enqueueing 2 to the queue
        queue.enqueue(3); // Enqueueing 3 to the queue
        int queuePeek1 = queue.peek(); // Peeking the front element from the queue
        int queuePeek2 = queue.peek(); // Peeking the front element from the queue again
        return stack.peek() == 3 && queuePeek1 == 1 && queuePeek1 == queuePeek2; // Checking if the
                                                                                 // peeked elements are
                                                                                 // as expected
    }

    /**
     * The main method of the MarkovTester class. It calls all the test methods and
     * prints the results
     * to the console.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("testStackAdd: " + (testStackAdd() ? "PASSED" : "FAILED"));
        System.out.println("testStackRemove: " + (testStackRemove() ? "PASSED" : "FAILED"));
        System.out.println("testStackShuffle: " + (testStackShuffle() ? "PASSED" : "FAILED"));
        System.out.println("testQueueAdd: " + (testQueueAdd() ? "PASSED" : "FAILED"));
        System.out.println("testQueueRemove: " + (testQueueRemove() ? "PASSED" : "FAILED"));
        System.out.println("testPeek: " + (testPeek() ? "PASSED" : "FAILED"));
    }
}
