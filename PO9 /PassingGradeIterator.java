//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PassingGradeIterator
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
 * This class represents an iterator for a Gradebook that only iterates over student records with
 * passing grades.
 */
public class PassingGradeIterator extends GradebookIterator {
  private StudentRecord next; // The next student record with a passing grade
  private double passingGrade; // The passing grade

  /**
   * Constructs a new PassingGradeIterator for the given gradebook.
   *
   * @param gradebook The gradebook to iterate over.
   */
  public PassingGradeIterator(Gradebook gradebook) {
    super(gradebook);
    // Get the passing grade from the gradebook
    this.passingGrade = gradebook.PASSING_GRADE;
    // Advance to the next student record with a passing grade
    advanceToNextPassingGrade();
  }

  /**
   * Advances to the next student record with a passing grade.
   */
  private void advanceToNextPassingGrade() {
    // Iterate over all student records
    while (super.hasNext()) {
      StudentRecord current = super.next();
      // If the current student record has a passing grade, set it as the next student
      // record and return
      if (current.getGrade() >= passingGrade) {
        next = current;
        return;
      }
    }
    // If no more student records have a passing grade, set next to null
    next = null;
  }

  /**
   * Returns whether there is a next student record with a passing grade.
   *
   * @return true if there is a next student record with a passing grade, false otherwise.
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next student record with a passing grade.
   *
   * @return The next student record with a passing grade.
   * @throws NoSuchElementException if there is no next student record with a passing grade.
   */
  @Override
  public StudentRecord next() {
    // Throw an exception if there is no next student record with a passing grade
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    // Get the next student record with a passing grade
    StudentRecord record = next;
    // Advance to the next student record with a passing grade
    advanceToNextPassingGrade();
    // Return the next student record with a passing grade
    return record;
  }
}
