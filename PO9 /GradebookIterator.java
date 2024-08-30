//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: GradebookIterator
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents an iterator for a Gradebook.
 */
public class GradebookIterator implements Iterator<StudentRecord> {
  private StudentRecord current; // The current student record
  private Gradebook gradebook; // The gradebook to iterate over

  /**
   * Constructs a new GradebookIterator for the given gradebook.
   *
   * @param gradebook The gradebook to iterate over.
   */
  public GradebookIterator(Gradebook gradebook) {
    this.gradebook = gradebook;
    // Start at the student record with the minimum grade
    this.current = gradebook.getMin();
  }

  /**
   * Returns whether there is a next student record in the gradebook.
   *
   * @return true if there is a next student record, false otherwise.
   */
  @Override
  public boolean hasNext() {
    // There is a next student record if the current student record is not null
    return current != null;
  }

  /**
   * Returns the next student record in the gradebook.
   *
   * @return The next student record.
   * @throws NoSuchElementException if there is no next student record.
   */
  @Override
  public StudentRecord next() {
    // Throw an exception if there is no next student record
    if (current == null) {
      throw new NoSuchElementException();
    }
    // Get the next student record
    StudentRecord record = current;
    // Move to the successor of the current student record
    current = gradebook.successor(current);
    // Return the next student record
    return record;
  }
}
