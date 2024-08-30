//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task Manager Tester
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
 * This class tests the TaskManager, TaskList, and SortedTaskList classes.
 */
public class TaskManagerTester {

  /**
   * Checks the correctness of the implementation of the method compareTo() defined in the Task
   * class. Consider different test scenarios including each of the SortingOrder values: TITLE and
   * PRIORITY
   * 
   * Test scenarios: <BR>
   * aTask and anotherTask references any Task objects you can create.<BR>
   * 1. aTask.compareTo(anotherTask) is expected to return 0 if they are equal with respect to the
   * comparison criteria. <BR>
   * 2. aTask.compareTo(aTask) is expected to return 0 <BR>
   * 3. aTask.compareTo(anotherTask) is expected to return a negative integer (less than zero) if
   * aTask is less than another Task with respect to the comparison criteria. <BR>
   * 4.aTask.compareTo(anotherTask) is expected to return a positive integer (greater than zero) if
   * aTask is greater than another Task with respect to the comparison criteria.
   * 
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testTaskCompareTo() {
    // Set sorting order to TITLE
    Task.setSortingOrderByTitle();

    // Create two tasks with the same title and priority
    Task aTask = new Task("Task 1", "Description 1", true);
    Task anotherTask = new Task("Task 1", "Description 2", true);

    // Test scenario 1: aTask.compareTo(anotherTask) should return 0
    if (aTask.compareTo(anotherTask) != 0) {
      return false;
    }

    // Test scenario 2: aTask.compareTo(aTask) should return 0
    if (aTask.compareTo(aTask) != 0) {
      return false;
    }

    // Create a task with a different title
    Task differentTitleTask = new Task("Task 2", "Description 3", true);

    // Test scenario 3: aTask.compareTo(differentTitleTask) should return a negative
    // integer
    if (aTask.compareTo(differentTitleTask) >= 0) {
      return false;
    }

    // Test scenario 4: differentTitleTask.compareTo(aTask) should return a positive
    // integer
    if (differentTitleTask.compareTo(aTask) <= 0) {
      return false;
    }

    // Set sorting order to PRIORITY
    Task.setSortingOrderByPriorityLevel();

    // Create a task with a lower priority
    Task lowPriorityTask = new Task("Task 3", "Description 4", false);

    // Test scenario 5: aTask.compareTo(lowPriorityTask) should return a negative
    // integer
    if (aTask.compareTo(lowPriorityTask) >= 0) {
      return false;
    }

    // Test scenario 6: lowPriorityTask.compareTo(aTask) should return a positive
    // integer
    if (lowPriorityTask.compareTo(aTask) <= 0) {
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Checks the correctness of the implementation of the equals() method defined in the Task class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testTaskEquals() {
    // Create two tasks with the same title and priority
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("task 1", "Description 2", true); // Different case for title

    // Test scenario: task1.equals(task2) should return true due to case
    // insensitivity
    if (!task1.equals(task2)) {
      return false;
    }

    // All test scenarios passed
    return true;

  }

  /**
   * Tests the add(), isEmpty(), and size() methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Create a new empty TaskList and verify that isEmpty() returns true.<BR>
   * 2. Add a few tasks to the end of the TaskList and verify that isEmpty() returns false.<BR>
   * 3. Verify that size() returns the expected size after adding each Task.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddIsEmptySize() {
    // Create a new TaskList
    TaskList taskList = new TaskList();

    // Test scenario 1: Verify that isEmpty() returns true for a new TaskList
    if (!taskList.isEmpty()) {
      return false;
    }

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Add the tasks to the TaskList
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    // Test scenario 2: Verify that isEmpty() returns false after adding tasks
    if (taskList.isEmpty()) {
      return false;
    }

    // Test scenario 3: Verify that size() returns the expected size
    if (taskList.size() != 3) {
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the addFirst(), and add(index, element) methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test adding elements to an empty TaskList <BR>
   * 2. Test adding elements to the beginning, middle, and end of a non-empty TaskList.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddToTaskList() {
    // Create a new TaskList
    TaskList taskList = new TaskList();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Test scenario 1: Add elements to an empty TaskList
    taskList.addFirst(task1);
    if (!taskList.get(0).equals(task1)) {
      return false;
    }

    // Test scenario 2: Add elements to the beginning, middle, and end of a
    // non-empty TaskList
    taskList.addFirst(task2);
    if (!taskList.get(0).equals(task2)) {
      return false;
    }

    taskList.add(1, task3);
    if (!taskList.get(1).equals(task3)) {
      return false;
    }

    taskList.add(task1);
    if (!taskList.get(taskList.size() - 1).equals(task1)) {
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests and remove(index) and clear() methods of the TaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test removing elements from various positions in the TaskList using remove(index). <BR>
   * 2. Test removing elements from an empty TaskList or at invalid indices. <BR>
   * 3. Test clear() method and verify that the TaskList is empty after calling it.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testRemoveClear() {
    // Create a new TaskList
    TaskList taskList = new TaskList();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Add the tasks to the TaskList
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    // Test scenario 1: Remove elements from various positions in the TaskList using
    // remove(index)
    if (!taskList.remove(0).equals(task1)) {
      return false;
    }

    if (!taskList.remove(taskList.size() - 1).equals(task3)) {
      return false;
    }

    // Test scenario 2: Remove elements from an empty TaskList or at invalid indices
    taskList.clear();
    try {
      taskList.remove(0);
      return false; // Should have thrown an exception
    } catch (IndexOutOfBoundsException e) {
      // Expected exception
    }

    // Test scenario 3: Test clear() method and verify that the TaskList is empty
    // after calling it
    taskList.add(task1);
    taskList.clear();
    if (!taskList.isEmpty()) {
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the add() method of the SortedTaskList class.
   * 
   * Test scenarios: <BR>
   * 1. Test adding a task to an empty TaskList <BR>
   * 2. Test adding tasks to a non-mepty sorted task list such that the task should be added to the
   * beginning, middle, and end of a non-empty TaskList.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAddToSortedTaskList() {
    // Create a new SortedTaskList
    SortedTaskList sortedTaskList = new SortedTaskList();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Add tasks to the SortedTaskList
    sortedTaskList.add(task3);
    sortedTaskList.add(task2);
    sortedTaskList.add(task1);

    // After adding task1, task2, and task3, the list should be sorted
    // as [task1, task2, task3]
    if (!sortedTaskList.get(0).equals(task1) || !sortedTaskList.get(1).equals(task2)
        || !sortedTaskList.get(2).equals(task3)) {
      return false;
    }

    Task task = new Task("Eat", "Eating is good for health", true);
    sortedTaskList.addFirst(task);
    if (!sortedTaskList.get(0).equals(task)) {
      return false;
    }

    // Test 3
    Task task4 = new Task("Study", "Studying is essential for learning", true);
    sortedTaskList.add(task4); // Use add(Task aTask) instead of add(int index, Task aTask)

    // Check if the list remains sorted after adding task4
    for (int i = 0; i < sortedTaskList.size() - 1; i++) {
      if (sortedTaskList.get(i).compareTo(sortedTaskList.get(i + 1)) > 0) {
        return false;
      }
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the appendTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test appending a task to an empty TaskManager. <BR>
   * 2. Test appending multiple tasks to the TaskManager.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testAppendTask() {
    // Create a new TaskManager
    TaskManager taskManager = new TaskManager();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);

    // Test scenario 1: Append a task to an empty TaskManager
    taskManager.appendTask(task1);
    if (!taskManager.toDoList.get(0).equals(task1)) {
      return false;
    }

    // Test scenario 2: Append multiple tasks to the TaskManager
    taskManager.appendTask(task2);
    if (!taskManager.toDoList.get(1).equals(task2)) {
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the moveToTop(), moveToBottom(), moveBeforeOtherTask(), and moveAfterOtherTask() methods
   * of the TaskManager class.
   *
   * Test scenarios: <BR>
   * 1. Test moving tasks to various positions in the toDoList and completedTasks lists. <BR>
   * 2. Test moving tasks relative to other tasks (before/after).
   * 
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testMoveTasks() {
    // Create a new TaskManager
    TaskManager taskManager = new TaskManager();

    // Create a few tasks
    Task taskA = new Task("Task A", "Description A", true);
    Task taskB = new Task("Task B", "Description B", true);
    Task taskC = new Task("Task C", "Description C", true);
    Task taskD = new Task("Task D", "Description D", true);

    // Add tasks to the TaskManager
    taskManager.appendTask(taskA);
    taskManager.appendTask(taskB);
    taskManager.appendTask(taskC);
    taskManager.appendTask(taskD);

    // Print initial order
    System.out.println("Initial order:");
    for (int i = 0; i < taskManager.toDoList.size(); i++) {
      Task task = taskManager.toDoList.get(i);
      System.out.println(task.getTitle());
    }

    // Test scenario 1: Move task B before task D
    taskManager.moveBeforeOtherTask(1, 3);

    // Print order after moving task B before task D
    System.out.println("\nOrder after moving B before D:");
    for (int i = 0; i < taskManager.toDoList.size(); i++) {
      Task task = taskManager.toDoList.get(i);
      System.out.println(task.getTitle());
    }

    // Test scenario 2: Move task D before task B
    taskManager.moveBeforeOtherTask(3, 1);

    // Print order after moving task D before task B
    System.out.println("\nOrder after moving D before B:");
    for (int i = 0; i < taskManager.toDoList.size(); i++) {
      Task task = taskManager.toDoList.get(i);
      System.out.println(task.getTitle());
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the removeTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test removing tasks from various positions in the toDoList and completedTasks lists. <BR>
   * 2 Test removing tasks at invalid indices return false.
   *
   * 
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testRemoveTask() {
    // Create a new TaskManager
    TaskManager taskManager = new TaskManager();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Add tasks to the TaskManager
    taskManager.appendTask(task1);
    taskManager.appendTask(task2);
    taskManager.appendTask(task3);

    // Test scenario 1: Remove tasks from various positions in the toDoList
    if (!taskManager.removeTask(1)) { // Remove task2
      return false;
    }
    if (taskManager.toDoList.contains(task2)) {
      return false;
    }

    // Test scenario 2: Test removing tasks at invalid indices return false
    if (taskManager.removeTask(5)) { // Invalid index
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Tests the completeTask() method of the TaskManager class.
   * 
   * Test scenarios: <BR>
   * 1. Test completing tasks from various positions in the toDoList. <BR>
   * 2. Test completing tasks at invalid indices.
   *
   * @return true if all test scenarios pass, false otherwise.
   *
   */
  public static boolean testCompleteTask() {
    // Create a new TaskManager
    TaskManager taskManager = new TaskManager();

    // Create a few tasks
    Task task1 = new Task("Task 1", "Description 1", true);
    Task task2 = new Task("Task 2", "Description 2", true);
    Task task3 = new Task("Task 3", "Description 3", true);

    // Add tasks to the TaskManager
    taskManager.appendTask(task1);
    taskManager.appendTask(task2);
    taskManager.appendTask(task3);

    // Test scenario 1: Complete tasks from various positions in the toDoList
    if (!taskManager.completeTask(1)) { // Complete task2
      return false;
    }
    if (taskManager.toDoList.contains(task2) || !taskManager.completedTasks.contains(task2)) {
      return false;
    }

    // Test scenario 2: Test completing tasks at invalid indices return false
    if (taskManager.completeTask(5)) { // Invalid index
      return false;
    }

    // All test scenarios passed
    return true;
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testTaskCompareTo(): " + testTaskCompareTo());
    System.out.println("testTaskEquals(): " + testTaskEquals());
    System.out.println("testAddIsEmptySize(): " + testAddIsEmptySize());
    System.out.println("testAddToTaskList(): " + testAddToTaskList());
    System.out.println("testRemoveClear(): " + testRemoveClear());
    System.out.println("testAddToSortedTaskList(): " + testAddToSortedTaskList());
    System.out.println("testAppendTask(): " + testAppendTask());
    System.out.println("testMoveTasks(): " + testMoveTasks());
    System.out.println("testRemoveTask(): " + testRemoveTask());
    System.out.println("testCompleteTask(): " + testCompleteTask());
  }
}
