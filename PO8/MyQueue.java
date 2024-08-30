//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MyQueue
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
 * The MyQueue class represents a queue data structure, implementing the
 * QueueADT interface. It uses
 * a linked list to store the elements, with references to the front and back of
 * the queue for
 * efficient operations.
 * 
 * @param <T> the type of elements in this queue
 */
public class MyQueue<T> implements QueueADT<T> {
    private LinkedNode<T> front; // front of the queue
    private LinkedNode<T> back; // back of the queue
    private int size; // number of elements in the queue

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * Adds the specified element to the back of the queue.
     * 
     * @param value the element to be added
     */
    @Override
    public void enqueue(T value) {
        LinkedNode<T> node = new LinkedNode<>(value); // create a new node with the given value
        if (isEmpty()) {
            front = node; // if the queue is empty, the new node becomes the front
        } else {
            back.setNext(node); // otherwise, the new node is added after the current back
        }
        back = node; // the new node becomes the back
        size++; // increment the size
    }

    /**
     * Removes and returns the front element of the queue.
     * 
     * @return the front element, or null if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null; // if the queue is empty, there's nothing to dequeue
        }
        T value = front.getData(); // get the data from the front node
        front = front.getNext(); // move the front to the next node
        size--; // decrement the size
        if (isEmpty()) {
            back = null; // if the queue is now empty, there's no back node
        }
        return value; // return the dequeued value
    }

    /**
     * Returns, but does not remove, the front element of the queue.
     * 
     * @return the front element, or null if the queue is empty
     */
    @Override
    public T peek() {
        return isEmpty() ? null : front.getData(); // if the queue is empty, there's nothing to peek
    }

    /**
     * Returns whether the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0; // the queue is empty if its size is 0
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return the number of elements
     */
    @Override
    public int size() {
        return size; // return the size
    }

    /**
     * Returns a list containing the elements of the queue in order.
     * 
     * @return a list of the elements
     */
    public ArrayList<T> getList() {
        ArrayList<T> list = new ArrayList<>();
        LinkedNode<T> current = front; // start at the front of the queue
        while (current != null) {
            list.add(current.getData()); // add the current node's data to the list
            current = current.getNext(); // move to the next node
        }
        return list; // return the list
    }

    /**
     * Removes elements from the front of the queue until its size is no greater
     * than the specified
     * maximum size.
     * 
     * @param maxSize the maximum size
     */
    public void maintainSize(int maxSize) {
        while (size > maxSize) {
            dequeue(); // dequeue elements until the size is no greater than maxSize
        }
    }

    /**
     * Returns a string representation of the queue. The string representation
     * consists of the string
     * representations of the elements, in order, separated by spaces.
     * 
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedNode<T> current = front; // start at the front of the queue
        while (current != null) {
            sb.append(current.getData().toString()); // append the current node's data to the string
            current = current.getNext(); // move to the next node
        }
        return sb.toString(); // return the string
    }
}
