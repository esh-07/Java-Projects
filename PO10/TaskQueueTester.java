//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TaskQueueTester
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

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TaskQueueTester {
  public static void main(String[] args) {
    System.out.println("testCompareToTime: " + (testCompareToTime() ? "PASSED" : "FAILED"));
    System.out.println("testCompareToTitle: " + (testCompareToTitle() ? "PASSED" : "FAILED"));
    System.out.println("testCompareToLevel: " + (testCompareToLevel() ? "PASSED" : "FAILED"));
    System.out.println("testEnqueue: " + (testEnqueue() ? "PASSED" : "FAILED"));
    System.out.println("testDequeue: " + (testDequeue() ? "PASSED" : "FAILED"));
    System.out.println("testPeek: " + (testPeek() ? "PASSED" : "FAILED"));
    System.out.println("testReprioritize: " + (testReprioritize() ? "PASSED" : "FAILED"));
  }

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TIME.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   */
  public static boolean testCompareToTime() {
    // Create tasks with different times
    Task taskA = new Task("TaskA", "DescriptionA", 1, PriorityLevel.LOW);
    Task taskB = new Task("TaskB", "DescriptionB", 2, PriorityLevel.LOW);
    Task taskC = new Task("TaskC", "DescriptionC", 3, PriorityLevel.LOW);
    Task taskD = new Task("TaskD", "DescriptionD", 4, PriorityLevel.HIGH);

    // Check if tasks are correctly ordered by time
    if (taskA.compareTo(taskB, CompareCriteria.TIME) >= 0) {
      return false; // taskA should be before taskB
    }

    if (taskA.compareTo(taskC, CompareCriteria.TIME) >= 0) {
      return false; // taskA should be before taskC
    }

    if (taskA.compareTo(taskD, CompareCriteria.TIME) >= 0) {
      return false; // taskA should be before taskD
    }

    if (taskA.compareTo(taskA, CompareCriteria.TIME) != 0) {
      return false; // taskA should be equal to itself
    }

    return true; // All tests passed
  }

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is TITLE.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   */
  public static boolean testCompareToTitle() {
    // Create tasks with different titles
    Task task1 = new Task("task1", "task1", 1, PriorityLevel.LOW);
    Task task2 = new Task("tAsk2", "tAsk2", 1, PriorityLevel.LOW);
    Task task3 = new Task("task3", "task3", 3, PriorityLevel.LOW);
    Task task4 = new Task("Task4", "Taskfour", 1, PriorityLevel.HIGH);
    Task task5 = new Task("tinhxja", "EshaanC", 0, PriorityLevel.URGENT);

    // Check if tasks are correctly ordered by title
    if (task1.compareTo(task2, CompareCriteria.TITLE) >= 0) {
      return false; // task1 should be before task2
    }

    if (task1.compareTo(task3, CompareCriteria.TITLE) <= 0) {
      return false; // task1 should be before task3
    }

    if (task1.compareTo(task4, CompareCriteria.TITLE) >= 0) {
      return false; // task1 should be before task4
    }

    if (task1.compareTo(task5, CompareCriteria.TITLE) <= 0) {
      return false; // task1 should be before task5
    }

    if (task1.compareTo(task1, CompareCriteria.TITLE) != 0) {
      return false; // task1 should be equal to itself
    }

    return true; // All tests passed
  }

  /**
   * Tests the correctness of a Task compareTo() method implementation when the criteria parameter
   * is LEVEL.
   * 
   * @return true if all the implementation passes all test cases, false otherwise
   */
  public static boolean testCompareToLevel() {
    // Create tasks with different priority levels
    Task taskJ = new Task("TaskJ", "DescriptionJ", 2, PriorityLevel.MEDIUM);
    Task taskK = new Task("TaskK", "DescriptionK", 45, PriorityLevel.LOW);
    Task taskL = new Task("TaskL", "DescriptionL", 2, PriorityLevel.HIGH);
    Task taskM = new Task("TaskM", "DescriptionM", 2, PriorityLevel.LOW);
    Task taskN = new Task("TaskN", "DescriptionN", 32, PriorityLevel.URGENT);

    // Check if tasks are correctly ordered by priority level
    if (taskJ.compareTo(taskK, CompareCriteria.LEVEL) <= 0) {
      return false; // taskJ should be before taskK
    }

    if (taskJ.compareTo(taskL, CompareCriteria.LEVEL) >= 0) {
      return false; // taskJ should be before taskL
    }

    if (taskJ.compareTo(taskM, CompareCriteria.LEVEL) <= 0) {
      return false; // taskJ should be before taskM
    }

    if (taskJ.compareTo(taskN, CompareCriteria.LEVEL) >= 0) {
      return false; // taskJ should be before taskN
    }

    if (taskJ.compareTo(taskJ, CompareCriteria.LEVEL) != 0) {
      return false; // taskJ should be equal to itself
    }

    return true; // All tests passed
  }

  /**
   * This method tests the enqueue operation of the TaskQueue. It creates a queue with a capacity of
   * 6 and enqueues tasks with different time values. It checks if the tasks are ordered correctly
   * based on the time. It also tests the behavior when trying to enqueue a completed task and when
   * the queue is full.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testEnqueue() {
    TaskQueue queue = new TaskQueue(6, CompareCriteria.TIME); // Initialize a new TaskQueue

    try {
      // Create tasks with different time values
      Task task1 = new Task("TaskOne", "DescriptionOne", 30, PriorityLevel.MEDIUM);
      Task task2 = new Task("TaskTwo", "DescriptionTwo", 20, PriorityLevel.HIGH);
      Task task3 = new Task("TaskThree", "DescriptionThree", 10, PriorityLevel.LOW);
      Task task4 = new Task("TaskFour", "DescriptionFour", 50, PriorityLevel.URGENT);
      Task task5 = new Task("TaskFive", "DescriptionFive", 5, PriorityLevel.OPTIONAL);
      Task task6 = new Task("TaskSix", "DescriptionSix", 15, PriorityLevel.HIGH);

      // Enqueue the tasks into the queue
      queue.enqueue(task1);
      queue.enqueue(task2);
      queue.enqueue(task3);
      queue.enqueue(task4);
      queue.enqueue(task5);
      queue.enqueue(task6);

      // Define the expected order of tasks based on time
      Task[] expectedArray = new Task[] {task4, task1, task6, task2, task5, task3};

      // Get the actual order of tasks in the queue
      Task[] heapData = queue.getHeapData();
      // Check if the actual order matches the expected order
      if (!Arrays.equals(heapData, expectedArray)) {
        System.out.println("Enqueue Test: Tasks are not ordered as expected.");
        return false;
      }

      // Test the behavior when trying to enqueue a completed task
      Task task7 = new Task("TaskSeven", "DescriptionSeven", 40, PriorityLevel.HIGH);
      task7.markCompleted(); // Mark task7 as completed
      try {
        queue.enqueue(task7);
        System.out.println("Enqueue Test: Exception expected when enqueuing a completed task.");
        return false;
      } catch (IllegalArgumentException e) {
        // This is the expected behavior
      }

      // Test the behavior when the queue is full
      try {
        queue.enqueue(new Task("TaskEight", "DescriptionEight", 25, PriorityLevel.MEDIUM));
        System.out.println("Enqueue Test: Exception expected when queue is full.");
        return false;
      } catch (IllegalStateException e) {
        // This is the expected behavior
      }

    } catch (Exception e) {
      System.out.println("Enqueue Test: Unexpected exception: " + e.getMessage());
      return false;
    }
    return true; // If all tests pass, return true
  }

  /**
   * This method tests the dequeue operation of the TaskQueue. It creates a queue with a capacity of
   * 6 and enqueues tasks with different time values. It checks if the tasks are ordered correctly
   * after a dequeue operation. It also tests the behavior when trying to dequeue from an empty
   * queue.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testDequeue() {
    TaskQueue queue = new TaskQueue(6, CompareCriteria.TIME); // Initialize a new TaskQueue

    try {
      // Create tasks with different time values
      Task task1 = new Task("TaskOne", "DescriptionOne", 30, PriorityLevel.MEDIUM);
      Task task2 = new Task("TaskTwo", "DescriptionTwo", 20, PriorityLevel.HIGH);
      Task task3 = new Task("TaskThree", "DescriptionThree", 10, PriorityLevel.LOW);
      Task task4 = new Task("TaskFour", "DescriptionFour", 5, PriorityLevel.URGENT);
      Task task5 = new Task("TaskFive", "DescriptionFive", 25, PriorityLevel.OPTIONAL);
      Task task6 = new Task("TaskSix", "DescriptionSix", 15, PriorityLevel.HIGH);

      // Enqueue the tasks into the queue
      queue.enqueue(task1);
      queue.enqueue(task2);
      queue.enqueue(task3);
      queue.enqueue(task4);
      queue.enqueue(task5);
      queue.enqueue(task6);

      // Dequeue a task from the queue
      queue.dequeue();

      // Define the expected order of tasks after one dequeue
      Task[] expectedArray = new Task[] {task5, task2, task6, task4, task3, null};

      // Get the actual order of tasks in the queue
      Task[] heapData = queue.getHeapData();
      // Check if the actual order matches the expected order
      if (!Arrays.equals(heapData, expectedArray)) {
        System.out.println("Dequeue Test: Tasks are not ordered as expected after one dequeue.");
        return false;
      }

      // Dequeue all tasks until the queue is empty
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();

      // Test the behavior when trying to dequeue from an empty queue
      try {
        queue.dequeue();
        System.out.println("Dequeue Test: Exception expected when dequeuing from an empty queue.");
        return false;
      } catch (NoSuchElementException e) {
        // This is the expected behavior
      }

    } catch (Exception e) {
      System.out.println("Dequeue Test: Unexpected exception: " + e.getMessage());
      return false;
    }
    return true; // If all tests pass, return true
  }

  /**
   * This method tests the peek operation of the TaskQueue. It creates a queue with a capacity of 5
   * and enqueues tasks with different time values. It checks if the peek operation returns the
   * highest priority task without removing it. It also tests the behavior when trying to peek from
   * an empty queue.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testPeek() {
    TaskQueue queue = new TaskQueue(5, CompareCriteria.TIME); // Initialize a new TaskQueue

    try {
      // Test peeking on an empty queue
      queue.peekBest();
      System.out.println("Peek Test: Exception expected when peeking on an empty queue.");
      return false; // If no exception is thrown, the test fails
    } catch (NoSuchElementException e) {
      // This is the expected outcome, so we continue
    } catch (Exception e) {
      System.out.println("Peek Test: Wrong type of exception thrown for empty queue.");
      return false; // If any other exception is thrown, the test fails
    }

    // Create tasks with different time values
    Task task1 = new Task("TaskOne", "DescriptionOne", 10, PriorityLevel.LOW);
    Task task2 = new Task("TaskTwo", "DescriptionTwo", 5, PriorityLevel.HIGH);
    Task task3 = new Task("TaskThree", "DescriptionThree", 3, PriorityLevel.URGENT);

    // Enqueue the tasks into the queue
    queue.enqueue(task1);
    queue.enqueue(task2);
    queue.enqueue(task3);

    try {
      // Test if peeking returns the highest priority task without removing it
      Task peekedTask = queue.peekBest();
      if (!peekedTask.equals(task1)) {
        System.out.println("Peek Test: The peeked task is not the most urgent as expected.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Peek Test: Unexpected exception: " + e.getMessage());
      return false;
    }

    // Check if the size of the queue has not changed after peeking
    if (queue.size() != 3) {
      System.out.println("Peek Test: The size of the queue should not change after peeking.");
      return false;
    }

    // Dequeue a task from the queue
    queue.dequeue();

    try {
      // Test if peeking returns the next highest priority task after dequeuing
      Task peekedTask = queue.peekBest();
      if (!peekedTask.equals(task2)) {
        System.out.println(
            "Peek Test: The peeked task after dequeuing is not the next highest priority task.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Peek Test: Unexpected exception: " + e.getMessage());
      return false;
    }

    // Check if the size of the queue is correct after dequeuing
    if (queue.size() != 2) {
      System.out.println("Peek Test: The size of the queue should be 2 after dequeuing one task.");
      return false;
    }

    return true; // If all tests pass, return true
  }

  /**
   * This method tests the reprioritize operation of the TaskQueue. It creates a queue with a
   * capacity of 3 and enqueues tasks with different priority levels. It then reprioritizes the
   * queue based on the LEVEL criteria and checks if the highest priority task is as expected.
   * 
   * @return true if the highest priority task after reprioritization is as expected, false
   *         otherwise
   */

  public static boolean testReprioritize() {
    TaskQueue queue = new TaskQueue(3, CompareCriteria.TIME); // Create a new TaskQueue
    Task task1 = new Task("Task 1", "Description 1", 3, PriorityLevel.MEDIUM); // Create two tasks
                                                                               // with different
                                                                               // priority levels
    Task task2 = new Task("Task 2", "Description 2", 2, PriorityLevel.HIGH);
    queue.enqueue(task1); // Add the tasks to the queue
    queue.enqueue(task2);
    queue.reprioritize(CompareCriteria.LEVEL); // Change the prioritization criteria of the queue
    return queue.peekBest().equals(task2); // Check if the task with the highest priority is at the
                                           // top of the queue
  }
}
