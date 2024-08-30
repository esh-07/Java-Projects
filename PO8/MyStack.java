//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MyStack
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
import java.util.Collections;

/**
 * The MyStack class represents a stack data structure, implementing the StackADT interface. It uses
 * a linked list to store the elements, with a reference to the top of the stack for efficient
 * operations.
 * 
 * @param <T> the type of elements in this stack
 */
public class MyStack<T> implements StackADT<T> {
  private LinkedNode<T> top; // top of the stack

  /**
   * Pushes the specified element onto the top of the stack.
   * 
   * @param value the element to be pushed
   */
  @Override
  public void push(T value) {
    LinkedNode<T> node = new LinkedNode<>(value); // create a new node with the given value
    node.setNext(top); // set the new node's next reference to the current top
    top = node; // the new node becomes the top of the stack
  }

  /**
   * Pops and returns the top element of the stack.
   * 
   * @return the top element, or null if the stack is empty
   */
  @Override
  public T pop() {
    if (isEmpty()) {
      return null; // if the stack is empty, there's nothing to pop
    }
    T value = top.getData(); // get the data from the top node
    top = top.getNext(); // move the top to the next node
    return value; // return the popped value
  }

  /**
   * Returns, but does not remove, the top element of the stack.
   * 
   * @return the top element, or null if the stack is empty
   */
  @Override
  public T peek() {
    return isEmpty() ? null : top.getData(); // if the stack is empty, there's nothing to peek
  }

  /**
   * Returns whether the stack is empty.
   * 
   * @return true if the stack is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return top == null; // the stack is empty if there's no top node
  }

  /**
   * Returns a list containing the elements of the stack in top-to-bottom order.
   * 
   * @return a list of the elements
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = top; // start at the top of the stack
    while (current != null) {
      list.add(current.getData()); // add the current node's data to the list
      current = current.getNext(); // move to the next node
    }
    return list; // return the list
  }

  /**
   * Shuffles the elements of the stack. This is done by converting the stack to a list, shuffling
   * the list, and then rebuilding the stack.
   */
  public void shuffle() {
    ArrayList<T> list = getList(); // convert the stack to a list
    Collections.shuffle(list); // shuffle the list
    top = null; // clear the stack
    for (int i = list.size() - 1; i >= 0; i--) {
      push(list.get(i)); // push the elements from the list back onto the stack
    }
  }
}
