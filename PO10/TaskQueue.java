//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TaskQueue
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

import java.util.NoSuchElementException;

/**
 * This class represents a priority queue of tasks. It's implemented as a binary heap, where tasks
 * are stored based on their priority. The priority is determined by a specified comparison
 * criteria.
 */

public class TaskQueue {
  private Task[] heapData; // The heap data structure storing the tasks
  private CompareCriteria priorityCriteria; // The criteria used to prioritize tasks
  private int size; // The current number of tasks in the queue

  /**
   * Constructs a new TaskQueue with the given capacity and priority criteria.
   */
  public TaskQueue(int capacity, CompareCriteria priorityCriteria) {
    // Check if the capacity is valid
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.heapData = new Task[capacity]; // Initialize the heap data structure
    this.priorityCriteria = priorityCriteria; // Set the priority criteria
    this.size = 0; // Initialize the size
  }

  /**
   * Returns the priority criteria of the queue.
   */
  public CompareCriteria getPriorityCriteria() {
    return this.priorityCriteria;
  }

  /**
   * Checks if the queue is empty.
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the size of the queue.
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns the highest priority task without removing it from the queue.
   */
  public Task peekBest() {
    // Check if the queue is empty
    if (this.isEmpty()) {
      throw new NoSuchElementException("The queue is empty");
    }
    return this.heapData[0]; // Return the highest priority task
  }

  /**
   * Adds a new task to the queue.
   */
  public void enqueue(Task newTask) {
    // Check if the task is already completed
    if (newTask.isCompleted()) {
      throw new IllegalArgumentException("Cannot enqueue a completed task");
    }
    // Check if the queue is full
    if (this.size == this.heapData.length) {
      throw new IllegalStateException("Queue is full");
    }
    this.heapData[this.size] = newTask; // Add the new task to the heap
    this.percolateUp(this.size); // Adjust the heap
    this.size++; // Increase the size
  }

  /**
   * Adjusts the heap after a new task is added.
   */
  protected void percolateUp(int index) {
    // Continue until the root of the heap is reached
    while (index > 0) {
      int parentIndex = (index - 1) / 2; // Calculate the parent index
      // Check if the current task has higher priority than its parent
      if (this.heapData[index].compareTo(this.heapData[parentIndex], this.priorityCriteria) > 0) {
        // Swap the current task with its parent
        Task temp = this.heapData[index];
        this.heapData[index] = this.heapData[parentIndex];
        this.heapData[parentIndex] = temp;
        index = parentIndex; // Update the index
      } else {
        break; // The heap is now valid
      }
    }
  }

  /**
   * Removes and returns the highest priority task from the queue.
   */
  public Task dequeue() {
    // Check if the queue is empty
    if (this.isEmpty()) {
      throw new NoSuchElementException("The queue is empty");
    }
    Task removedTask = this.heapData[0]; // Store the highest priority task
    this.heapData[0] = this.heapData[this.size - 1]; // Replace the root of the heap with the last
                                                     // task
    this.heapData[this.size - 1] = null; // Remove the last task
    this.size--; // Decrease the size
    this.percolateDown(0); // Adjust the heap
    return removedTask; // Return the removed task
  }

  /**
   * Adjusts the heap after a task is removed.
   */
  protected void percolateDown(int index) {
    // Continue until a leaf of the heap is reached
    while (index < this.size / 2) {
      int leftChildIndex = 2 * index + 1; // Calculate the left child index
      int rightChildIndex = 2 * index + 2; // Calculate the right child index
      int maxIndex = leftChildIndex; // Assume the left child has higher priority
      // Check if the right child exists and has higher priority
      if (rightChildIndex < this.size && this.heapData[rightChildIndex]
          .compareTo(this.heapData[leftChildIndex], this.priorityCriteria) > 0) {
        maxIndex = rightChildIndex; // Update the max index
      }
      // Check if the current task has lower priority than its highest priority child
      if (this.heapData[index].compareTo(this.heapData[maxIndex], this.priorityCriteria) < 0) {
        // Swap the current task with its highest priority child
        Task temp = this.heapData[index];
        this.heapData[index] = this.heapData[maxIndex];
        this.heapData[maxIndex] = temp;
        index = maxIndex; // Update the index
      } else {
        break; // The heap is now valid
      }
    }
  }

  /**
   * Changes the priority criteria and adjusts the heap accordingly.
   */
  public void reprioritize(CompareCriteria priorityCriteria) {
    this.priorityCriteria = priorityCriteria; // Change the priority criteria
    // Adjust the heap for each non-leaf task
    for (int i = this.size / 2 - 1; i >= 0; i--) {
      this.percolateDown(i);
    }
  }

  /**
   * Returns a copy of the heap data.
   */
  public Task[] getHeapData() {
    Task[] copy = new Task[this.size]; // Create a new array
    // Copy the tasks from the heap to the new array
    System.arraycopy(this.heapData, 0, copy, 0, this.size);
    return copy; // Return the new array
  }
}
