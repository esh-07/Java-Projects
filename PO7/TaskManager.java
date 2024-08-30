//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Manager
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
 * This class models TaskManager objects. A TaskManager has a to do list of incomplete tasks, and a
 * list of completed tasks. This class allows the user to manage the tasks in the to do list.
 */
public class TaskManager {

  /**
   * to do list of incomplete tasks
   */
  protected TaskList toDoList; // list of incomplete tasks

  /**
   * TaskList of completed tasks
   */
  protected TaskList completedTasks; // list of completed tasks

  /**
   * Constructs a TaskManager with empty to-do and completed task lists.
   */
  public TaskManager() {
    this.toDoList = new TaskList();
    this.completedTasks = new TaskList();
  }

  /**
   * Appends a task to the end of the to-do list.
   *
   * @param task The task to be added to the to-do list.
   */
  public void appendTask(Task task) {
    this.toDoList.add(task);
  }

  /**
   * Moves a specified task to the top (head) of the to-do list.
   *
   * @param indexTaskToMove The index of the task to move to the top (head) of the to-do list.
   * @throws IndexOutOfBoundsException if indexTaskToMove is out of bounds with respect to the to-do
   *                                   list valid range of indexes.
   */
  public void movetoTop(int indexTaskToMove) {
    Task task = this.toDoList.remove(indexTaskToMove);
    this.toDoList.addFirst(task);
  }

  /**
   * Moves a specified task to the bottom (tail) of the to-do list.
   *
   * @param indexTaskToMove The index of the task to move to the bottom (tail) of the to-do list.
   * @throws IndexOutOfBoundsException if indexTaskToMove is out of bounds with respect to the to-do
   *                                   list valid range of indexes.
   */
  public void moveToBottom(int indexTaskToMove) {
    Task task = this.toDoList.remove(indexTaskToMove);
    this.toDoList.add(task);
  }

  /**
   * Moves a specified task to a position before another task in the to-do list given their indexes.
   *
   * @param indexTaskToMove The index of the task to move within the to-do list.
   * @param indexOtherTask  The index of the other task before which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   *
   */
  public boolean moveBeforeOtherTask(int indexTaskToMove, int indexOtherTask) {
    if (indexTaskToMove < 0 || indexTaskToMove >= toDoList.size() || indexOtherTask < 0
        || indexOtherTask >= toDoList.size()) {
      return false;
    }

    // Remove the task to move
    Task taskToMove = toDoList.remove(indexTaskToMove);

    // Find the correct index to insert the task before the other task
    int newIndex = indexOtherTask;
    if (indexTaskToMove < indexOtherTask) {
      newIndex--; // If the task to move was before the other task, decrement the index
    }

    // Insert the task at the new index
    toDoList.add(newIndex, taskToMove);
    return true;
    // Task moved successfully

  }

  /**
   * Moves a specified task to a position after another task in the to-do list, given their indexes.
   *
   * @param indexTaskToMove The index of the task to move.
   * @param indexOtherTask  The index of the other task after which the first task will be moved.
   * @return true if the task was successfully moved; false otherwise.
   */
  public boolean moveAfterOtherTask(int indexTaskToMove, int indexOtherTask) {
    // Check if the indexes are valid
    if (indexTaskToMove < 0 || indexTaskToMove >= this.toDoList.size() || indexOtherTask < 0
        || indexOtherTask >= this.toDoList.size()) {
      return false;
    }
    // Remove the task to move from its current position
    Task task = this.toDoList.remove(indexTaskToMove);
    // Add the task after the other task
    this.toDoList.add(indexOtherTask + 1, task);
    return true;
  }

  /**
   * Removes a task from the to-do list based on its index.
   *
   * @param index The index of the task to remove.
   * @return true if the task was successfully removed; false if the index was invalid.
   */
  public boolean removeTask(int index) {
    // Check if the index is valid
    if (index < 0 || index >= this.toDoList.size()) {
      return false;
    }
    // Remove the task at the given index
    this.toDoList.remove(index);
    return true;
  }

  /**
   * Marks the task at index in the to-do list as complete and moves it to the completed tasks list.
   * The completed task can be added to the beginning OR the end of the completedTasks list.
   *
   * @param index The index of the task to mark as complete.
   * @return true if the task was successfully marked as complete and moved; false if the index was
   *         invalid.
   */
  public boolean completeTask(int index) {
    // Check if the index is valid
    if (index < 0 || index >= this.toDoList.size()) {
      return false;
    }
    // Remove the task from the to-do list and add it to the completed tasks list
    Task task = this.toDoList.remove(index);
    this.completedTasks.add(task);
    return true;
  }

  /**
   * Returns a new TaskList that contains all tasks sorted in the increasing order
   * 
   * @return a new TaskList that contains all tasks sorted in the increasing order
   */
  public TaskList sortTasks() {
    // Create a new sorted task list
    SortedTaskList sortedTasks = new SortedTaskList();
    // Add all tasks from the to-do list to the sorted task list
    for (int i = 0; i < this.toDoList.size(); i++) {
      sortedTasks.add(this.toDoList.get(i));
    }
    // Return the sorted task list
    return sortedTasks;
  }
}
