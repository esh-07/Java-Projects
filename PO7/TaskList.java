//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task List
// Course: CS 300 Spring 2024
//
// Author: Sehar Randhawa
// Email: randhawa3@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Eshaan Chaturvedi
// Partner Email: echaturvedi@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements a doubly linked list of tasks.
 */
public class TaskList implements ListADT<Task> {

  // Reference to the first node in the list
  private LinkedNode<Task> head;

  // Reference to the last node in the list
  private LinkedNode<Task> tail;

  // Total number of tasks stored in the list
  private int size;

  /**
   * Constructs an empty TaskList.
   */
  public TaskList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Checks if the list is empty.
   *
   * @return true if the list is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return the number of tasks in the list.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds a new task to the end (tail) of the list.
   *
   * @param newElement the task to be added.
   */
  @Override
  public void add(Task newElement) {
    // Create a new node with the given task
    LinkedNode<Task> newNode = new LinkedNode<>(newElement);
    if (isEmpty()) {
      // If the list is empty, the new node is both the head and tail
      head = tail = newNode;
    } else {
      // Otherwise, add the new node at the end of the list
      tail.setNext(newNode);
      newNode.setPrev(tail);
      tail = newNode;
    }
    // Increment the size of the list
    size++;
  }

  /**
   * Adds a new task to the head of the list.
   *
   * @param newElement the task to be added.
   */
  @Override
  public void addFirst(Task newElement) {
    // Create a new node with the given task
    LinkedNode<Task> newNode = new LinkedNode<>(newElement);
    if (isEmpty()) {
      // If the list is empty, the new node is both the head and tail
      head = tail = newNode;
    } else {
      // Otherwise, add the new node at the start of the list
      newNode.setNext(head);
      head.setPrev(newNode);
      head = newNode;
    }
    // Increment the size of the list
    size++;
  }

  /**
   * Adds a new task at the specified index in the list.
   *
   * @param index      the index at which the task is to be inserted.
   * @param newElement the task to be added.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  @Override
  public void add(int index, Task newElement) {
    // Check if the index is valid
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    // If the index is 0, add the task at the start of the list
    if (index == 0) {
      addFirst(newElement);
    }
    // If the index is the size of the list, add the task at the end of the list
    else if (index == size) {
      add(newElement);
    } else {
      // Otherwise, add the task at the specified index
      LinkedNode<Task> newNode = new LinkedNode<>(newElement);
      LinkedNode<Task> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      newNode.setNext(current.getNext());
      newNode.setPrev(current);
      current.getNext().setPrev(newNode);
      current.setNext(newNode);
      size++;
    }
  }

  /**
   * Returns the task at the specified index in the list.
   *
   * @param index the index of the task to return.
   * @return the task at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  @Override
  public Task get(int index) {
    // Check if the index is valid
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    // Start from the head of the list
    LinkedNode<Task> current = head;
    // Move to the node at the specified index
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    // Return the task at the specified index
    return current.getData();
  }

  /**
   * Checks if the list contains a specific task.
   *
   * @param toFind the task to find.
   * @return true if the list contains the task, false otherwise.
   */
  @Override
  public boolean contains(Task toFind) {
    // Start from the head of the list
    LinkedNode<Task> current = head;
    // Traverse the list until the task is found or the end of the list is reached
    while (current != null) {
      if (current.getData().equals(toFind)) {
        return true;
      }
      current = current.getNext();
    }
    // If the task was not found, return false
    return false;
  }

  /**
   * Removes the task at the specified index from the list.
   *
   * @param index the index of the task to remove.
   * @return the task that was removed.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  @Override
  public Task remove(int index) {
    // Check if the index is valid
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    // Start from the head of the list
    LinkedNode<Task> current = head;
    // Move to the node at the specified index
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    // If the node to remove is the head, update the head
    if (current == head) {
      head = current.getNext();
    } else {
      // Otherwise, update the next pointer of the previous node
      current.getPrev().setNext(current.getNext());
    }
    // If the node to remove is the tail, update the tail
    if (current == tail) {
      tail = current.getPrev();
    } else {
      // Otherwise, update the previous pointer of the next node
      current.getNext().setPrev(current.getPrev());
    }
    // Decrement the size of the list
    size--;
    // Return the task that was removed
    return current.getData();
  }

  /**
   * Removes all tasks from the list, making it empty.
   */
  @Override
  public void clear() {
    // Remove all tasks from the list
    head = tail = null;
    size = 0;
  }

  /**
   * Returns a String representation of the list.
   *
   * @param forward true if traversal direction is forward, false otherwise.
   * @return a String representation of the list.
   */
  protected String traverseForward() {
    // Create a StringBuilder to build the string representation
    StringBuilder sb = new StringBuilder();
    // Start from the head of the list
    LinkedNode<Task> current = head;
    // Traverse the list in the forward direction
    while (current != null) {
      // Append the string representation of each task to the StringBuilder
      sb.append(current.getData().toString()).append("\n");
      // Move to the next node
      current = current.getNext();
    }
    // Return the string representation of the list
    return sb.toString();
  }

  /**
   * Returns a String representation of the list traversed in the forward direction.
   *
   * @return a String representation of the list.
   */
  protected String traverseBackward() {
    // Create a StringBuilder to build the string representation
    StringBuilder sb = new StringBuilder();
    // Start from the tail of the list
    LinkedNode<Task> current = tail;
    // Traverse the list in the backward direction
    while (current != null) {
      // Append the string representation of each task to the StringBuilder
      sb.append(current.getData().toString()).append("\n");
      // Move to the previous node
      current = current.getPrev();
    }
    // Return the string representation of the list
    return sb.toString();
  }

  /**
   * Returns a String representation of the list traversed in the backward direction.
   *
   * @return a String representation of the list.
   */
  public String traverse(boolean forward) {
    // If the forward parameter is true, traverse the list in the forward direction
    // Otherwise, traverse the list in the backward direction
    return forward ? traverseForward() : traverseBackward();
  }
}
