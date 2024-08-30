//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: StudentRecord
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
 * This class represents a student record.
 */
public class StudentRecord implements Comparable<StudentRecord> {
  public final String name; // The name of the student
  public final String email; // The email of the student
  private double grade; // The grade of the student

  /**
   * Constructs a new StudentRecord with the given name, email, and grade.
   *
   * @param name  The name of the student.
   * @param email The email of the student.
   * @param grade The grade of the student.
   * @throws IllegalArgumentException if the name or email is null or blank, or if the grade is not
   *                                  between 0.0 and 100.0 (inclusive).
   */
  public StudentRecord(String name, String email, double grade) {
    // Check if the name or email is null or blank
    if (name == null || name.isBlank() || email == null || email.isBlank()) {
      throw new IllegalArgumentException("Name or email cannot be null or blank");
    }
    // Check if the grade is between 0.0 and 100.0 (inclusive)
    if (grade < 0.0 || grade > 100.0) {
      throw new IllegalArgumentException("Grade must be between 0.0 and 100.0 (inclusive)");
    }
    // Set the name, email, and grade
    this.name = name;
    this.email = email;
    this.grade = grade;
  }

  /**
   * Returns the grade of the student.
   *
   * @return The grade of the student.
   */
  public double getGrade() {
    return this.grade;
  }

  /**
   * Sets the grade of the student.
   *
   * @param grade The new grade of the student.
   */
  public void setGrade(double grade) {
    this.grade = grade;
  }

  /**
   * Returns a string representation of the student record.
   *
   * @return A string representation of the student record.
   */
  @Override
  public String toString() {
    // Format the grade to 1 decimal place if it's a whole number, or 2 decimal
    // places otherwise
    String gradeStr =
        (grade == Math.floor(grade)) ? String.format("%.1f", grade) : String.format("%.2f", grade);
    // Return the formatted string
    return String.format("%s (%s): %s", this.name, this.email, gradeStr);
  }

  /**
   * Compares this student record to another student record based on email.
   *
   * @param other The other student record to compare to.
   * @return A negative integer, zero, or a positive integer as this email is less than, equal to,
   *         or greater than the other email.
   */
  @Override
  public int compareTo(StudentRecord other) {
    return this.email.compareTo(other.email);
  }

  /**
   * Checks if this student record is equal to another object.
   *
   * @param o The object to compare to.
   * @return true if the object is a student record and has the same email, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    // Check if the object is a student record
    if (o instanceof StudentRecord) {
      StudentRecord other = (StudentRecord) o;
      // Check if the emails are equal
      return this.email.equals(other.email);
    }
    return false;
  }
}
