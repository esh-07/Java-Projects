//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task
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
 * This class models Task objects.
 */
public class Task implements Comparable<Task> {

  /**
   * High priority level
   */
  public static final int HIGH = 0;

  /**
   * Low priority level
   */
  public static final int LOW = 1;

  /**
   * Indicates the comparison criteria of the tasks (i.e. their sorting order).
   */
  private static SortingOrder sortingOrder;

  /**
   * Indicates the priority level of this Task. The priority level can be either HIGH or LOW
   */
  private int priorityLevel;

  /**
   * title of this task
   */
  private String title; // title of this task (unique case insensitive comparison)

  /**
   * A short description of this task
   */
  private String description; // short description

  /**
   * Indicates whether this task is completed or not
   */
  private boolean isCompleted; // indicates whether this task is completed

  /**
   * Constructs a new Task with a given priority level. A new constructed task is incomplete by
   * default.
   * 
   * @param title           title to be assigned to this task
   * @param description     description to be assigned to this task
   * @param hasHighPriority indicates whether this task has a high priority (true: this task has a
   *                        HIGH priority, LOW otherwise).
   * @throws IllegalArgumentException with a descriptive error message if title or description is
   *                                  null or blank.
   */
  public Task(String title, String description, boolean hasHighPriority) {
    if (title == null || title.isBlank() || description == null || description.isBlank())
      throw new IllegalArgumentException(
          "Invalid title or description: should not be null or blank");
    this.title = title;
    this.description = description;
    if (hasHighPriority)
      this.priorityLevel = HIGH;
    else
      this.priorityLevel = LOW;
  }

  /**
   * Constructs a new Task with a low priority level. A new constructed task is incomplete by
   * default.
   * 
   * @param title       title to be assigned to this task
   * @param description description to be assigned to this task
   * @throws IllegalArgumentException with a descriptive error message if title or description is
   *                                  null or blank.
   */
  public Task(String title, String description) {
    this(title, description, false);
  }

  /**
   * Gets the title of this task
   * 
   * @return the title of this task
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the description of this task
   * 
   * @return the description of this task
   */
  public String getDescription() {
    return description;
  }

  /**
   * Edits the description of this task
   * 
   * @param description the description to set
   */
  public void editDescription(String description) {
    this.description = description;
  }

  /**
   * Checks whether this task is completed
   * 
   * @return true if this task is completed or false otherwise
   */
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
   * Marks this task to be completed
   */
  public void markCompleted() {
    this.isCompleted = true;
  }

  /**
   * Sets the sortingOrder of tasks to be by Title
   */
  public static void setSortingOrderByTitle() {
    sortingOrder = SortingOrder.TITLE;
  }

  /**
   * Sets the sortingOrder of tasks to be by PRIORITY level
   */
  public static void setSortingOrderByPriorityLevel() {
    sortingOrder = SortingOrder.PRIORITY;
  }

  /**
   * Gets the sorting order of tasks
   * 
   * @return the sorting order of tasks
   */
  public static SortingOrder getSortingOrder() {
    return sortingOrder;
  }

  /**
   * Returns a String representation of this task in the format:
   * 
   * title: description
   * 
   * @return String representation of this task
   */
  @Override
  public String toString() {
    return title + ": " + description;
  }

  // TODO complete the implementation of equals and compareTo methods with
  // accordance to the details
  // provided in their javadocs.

  /**
   * Compares this task to the specified object. The result is true if and only if the argument is
   * not null and is a Task object (instanceof Task) that has this same title as this task, case
   * INSENSITIVE comparison.
   * 
   * @param other other Object to compare this Task against.
   * @return true if the given object represents a Task equivalent to this task, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    // Check if the current instance is the same as the other object
    if (this == other)
      return true;
    // Check if the other object is null or not of the same class
    if (other == null || getClass() != other.getClass())
      return false;
    // Cast the other object to Task
    Task task = (Task) other;
    // Compare the titles of the tasks, ignoring case
    return title.equalsIgnoreCase(((Task) other).title);
  }

  /**
   * Compares this task to anotherTask with respect to the sorting order of tasks. Note that the
   * sorting order of these tasks can be either by TITLE or by PRIORITY.
   * 
   * If the sortingOrder is by TITLE, tasks are compared with respect to lexicographical order of
   * their titles.
   * 
   * If the sortingOrder is by PRIORITY, tasks are compared with respect to their priority level.
   * 
   * @param anotherTask Task to be compared
   * @return the value 0 if the argument Task is equal to this task with respect to the sorting
   *         order; a value less than 0 if this task is less than the Task argument; and a value
   *         greater than 0 if this task is greater than the Task argument, with respect to the
   *         sorting order.
   */
  @Override
  public int compareTo(Task anotherTask) {
    // Check if the sorting order is by title
    if (sortingOrder == SortingOrder.TITLE) {
      // Compare the titles of the tasks, ignoring case
      return this.title.compareToIgnoreCase(anotherTask.title);
    }
    // Check if the sorting order is by priority
    else if (sortingOrder == SortingOrder.PRIORITY) {
      // Compare the priority levels of the tasks
      return Integer.compare(this.priorityLevel, anotherTask.priorityLevel);
    }
    // If the sorting order is neither by title nor by priority, return 0
    return 0;
  }
}
