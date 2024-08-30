//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: SortedTaskList
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

import java.util.NoSuchElementException;

/**
 * A SortedTaskList is a TaskList where tasks are always sorted. It provides methods to add, peek
 * at, and remove tasks, ensuring tasks are not null and unique. Developed by Sehar Randhawa and
 * Eshaan Chaturvedi for CS 300 Spring 2024.
 * 
 */
public class SortedTaskList extends TaskList {

  /**
   * Adds a task to the sorted task list in its correct position.
   * 
   * @param aTask The task to add.
   * @throws NullPointerException     If the task is null.
   * @throws IllegalArgumentException If the task already exists in the list.
   */
  @Override
  public void add(Task taskToInsert) throws NullPointerException {

    if (taskToInsert == null) {
      throw new NullPointerException("The provided task is null!");
    }

    int taskListSize = this.size();
    for (int index = 0; index < taskListSize; index++) { // Loop through the tasks

      if (this.get(index).equals(taskToInsert)) { // Check for duplicate task
        throw new IllegalArgumentException("The sorted list already contains this task! "
            + "Duplicate items are not allowed in this list.");
      }

      if (this.get(index).compareTo(taskToInsert) >= 0) { // Check if the next Task is greater than
                                                          // the task to
                                                          // insert
        super.add(index, taskToInsert); // Insert taskToInsert before the larger Task that is next
        return;
      }
    }

    super.add(taskToInsert); // If no larger task is found, add the task to the end as it is the
                             // largest
  }

  /**
   * Adds a task to the beginning of the sorted task list.
   * 
   * @param aTask The task to add.
   * @throws NullPointerException  If the task is null.
   * @throws IllegalStateException If the task is not the smallest task in the list.
   */
  @Override
  public void addFirst(Task aTask) throws NullPointerException {
    // Check if the task is null
    if (aTask == null) {
      throw new NullPointerException("Task cannot be null");
    }
    // Check if the task is not the smallest task in the list
    if (!isEmpty() && get(0).compareTo(aTask) < 0) {
      throw new IllegalStateException("Task cannot be added to the head of the list");
    }
    // Add the task to the beginning of the list
    super.addFirst(aTask);
  }

  /**
   * Adds a task at a specific position in the sorted task list.
   * 
   * @param index The position at which to add the task.
   * @param aTask The task to add.
   * @throws NullPointerException      If the task is null.
   * @throws IndexOutOfBoundsException If the index is out of range.
   */
  @Override
  public void add(int index, Task aTask) throws NullPointerException, IndexOutOfBoundsException {
    // Check if the task is null
    if (aTask == null) {
      throw new NullPointerException("Task cannot be null");
    }
    // Check if the index is out of range
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index is out of range");
    }
    // Add the task at the specified index
    super.add(index, aTask);
  }

  /**
   * Returns the smallest task in the sorted task list without removing it.
   * 
   * @return The smallest task in the list.
   * @throws NoSuchElementException If the list is empty.
   */
  public Task peekBest() {
    // Check if the list is empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    // Return the smallest task in the list without removing it
    return get(0);
  }

  /**
   * Removes and returns the smallest task in the sorted task list.
   * 
   * @return The smallest task in the list.
   * @throws NoSuchElementException If the list is empty.
   */
  public Task removeBest() {
    // Check if the list is empty
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    // Remove and return the smallest task in the list
    return remove(0);
  }
}
