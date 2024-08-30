//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Task
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

/**
 * Instantiable class for Task objects. These Tasks can be given different priority levels and
 * compared based on different criteria.
 *
 * @author Michelle & Eshaan
 *
 * 
 */
public class Task {

  /** the title of this Task */
  private String title;

  /** the description of this Task */
  private String description;

  /** the priority of this Task */
  private PriorityLevel priorityLevel;

  /** the amount of estimated time to complete this Task */
  private int time;

  /** denotes whether or not this task has been completed */
  private boolean isCompleted;

  /**
   * Creates a new Task object using the given information. By default, newly created Tasks are NOT
   * completed.
   * 
   * @param title         the title of the Task
   * @param description   the description of the Task
   * @param time          the estimated time to complete the Task
   * @param priorityLevel the priority level of the Task
   */
  public Task(String title, String description, int time, PriorityLevel priorityLevel) {
    this.title = title;
    this.description = description;
    this.time = time;
    this.priorityLevel = priorityLevel;
  }

  /**
   * Gets a Task's title.
   * 
   * @return this Task's title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Gets a Task's description.
   * 
   * @return this Task's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets a Task's estimated completion time.
   * 
   * @return this Task's estimated completion time
   */
  public int getTime() {
    return this.time;
  }

  /**
   * Reports if a Task has been completed.
   * 
   * @return true if this Task is completed, false otherwise
   */
  public boolean isCompleted() {
    return this.isCompleted;
  }

  /**
   * Marks this Task as completed.
   */
  public void markCompleted() {
    this.isCompleted = true;
  }

  /**
   * Returns a String representation of a Task.
   * 
   * @return this Task as a String
   */
  @Override
  public String toString() {
    return title + ": " + description + "(" + this.priorityLevel + "), ETA " + this.time
        + " minutes";
  }

  /**
   * Determines if another object is equal to a Task.
   * 
   * @param other the object to check is equal to this Task
   * @return true if other is a Task and has the same title, time, description and priority level,
   *         false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Task) {
      Task t = (Task) other;
      return this.title.equals(t.title) && this.description.equals(t.description)
          && this.priorityLevel == t.priorityLevel && this.time == t.time;
    }

    return false;
  }

  /**
   * Compares the other Task to this Task based on the given PrioritizationMode.
   * 
   * @param other    the Task to compare to this
   * @param criteria the criteria use to determine how to compare them
   * @return &lt; 0 if this is smaller than other, &gt; 0 of this is larger than other, and 0 if
   *         they are equal
   *
   * @author Eshaan Chaturvedi
   */
  public int compareTo(Task other, CompareCriteria criteria) {
    switch (criteria) {
      case TITLE:
        // Compare tasks based on their titles. The order is reversed to sort in
        // descending order.
        return other.title.compareTo(this.title);
      case TIME:
        // Compare tasks based on their time. The order is ascending.
        return Integer.compare(this.time, other.time);
      case LEVEL:
        // Compare tasks based on their priority levels. The order is ascending.
        return this.priorityLevel.compareTo(other.priorityLevel);
      default:
        // Throw an exception if an invalid comparison criteria is provided.
        throw new IllegalArgumentException("Invalid comparison criteria");
    }
  }
}
