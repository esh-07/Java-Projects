//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: GradebookTester
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

public class GradebookTester {

  public static void main(String[] args) {
    System.out.println("constructorTester: " + (constructorTester() ? "PASSED" : "FAILED"));
    System.out.println("isEmptySizeAddTester: " + (isEmptySizeAddTester() ? "PASSED" : "FAILED"));
    System.out.println("lookupTester: " + (lookupTester() ? "PASSED" : "FAILED"));
    System.out.println("toStringTester: " + (toStringTester() ? "PASSED" : "FAILED"));
    System.out.println("getMinTester: " + (getMinTester() ? "PASSED" : "FAILED"));
    System.out.println("removeStudentTester: " + (removeStudentTester() ? "PASSED" : "FAILED"));
    System.out.println("iteratorTester: " + (iteratorTester() ? "PASSED" : "FAILED"));
    System.out.println("passingIteratorTester: " + (passingIteratorTester() ? "PASSED" : "FAILED"));
    System.out.println("successorTester: " + (successorTester() ? "PASSED" : "FAILED"));
    System.out.println("prettyStringTester: " + (prettyStringTester() ? "PASSED" : "FAILED"));
  }

  public static boolean constructorTester() {
    try {
      // Create a new Gradebook
      Gradebook gradebook = new Gradebook("Math", 60.0);

      // Check if the course name and passing grade are correctly set
      if (!gradebook.course.equals("Math") || gradebook.PASSING_GRADE != 60.0) {
        System.out.println("Constructor test failed: Expected values do not match actual values.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Constructor tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean isEmptySizeAddTester() {
    try {
      // Create a new Gradebook
      Gradebook gradebook = new Gradebook("Math", 60.0);

      // Check if the Gradebook is initially empty and has size 0
      boolean initialCheck = gradebook.isEmpty() && gradebook.size() == 0;

      // Add a student to the Gradebook
      gradebook.addStudent(new StudentRecord("John", "john@example.com", 70.0));

      // Check if the Gradebook is not empty and has size 1 after adding a student
      boolean afterAddCheck = !gradebook.isEmpty() && gradebook.size() == 1;

      if (!initialCheck || !afterAddCheck) {
        System.out
            .println("isEmptySizeAdd test failed: Expected values do not match actual values.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("isEmptySizeAdd tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean lookupTester() {
    // Create a new Gradebook for the course "Math" with a passing grade of 70.0
    Gradebook gradebook = new Gradebook("Math", 70.0);

    // Create StudentRecord objects for each student
    StudentRecord student1 = new StudentRecord("Adam", "adam@wisc.edu", 81.0);
    StudentRecord student2 = new StudentRecord("Jay", "jay@wisc.edu", 99.0);
    StudentRecord student3 = new StudentRecord("Eshaan", "eshaan@wisc.edu", 90.0);
    StudentRecord student4 = new StudentRecord("James", "james@wisc.edu", 81.0);
    StudentRecord student5 = new StudentRecord("Pona", "pona@wisc.edu", 88.0);
    StudentRecord student6 = new StudentRecord("Ava", "ava@wisc.edu", 85.0);

    // Add each student to the Gradebook
    gradebook.addStudent(student1);
    gradebook.addStudent(student2);
    gradebook.addStudent(student3);
    gradebook.addStudent(student4);
    gradebook.addStudent(student5);
    gradebook.addStudent(student6);

    // Look up a student by email in the Gradebook
    StudentRecord matchedRecord = gradebook.lookup("ava@wisc.edu");

    // Check if the matched record is the same as the original record
    if (!matchedRecord.equals(student6)) {
      return false;
    }

    // Check if the name and grade of the matched record are the same as the
    // original record
    if (!matchedRecord.name.equals(student6.name)) {
      return false;
    }
    if (matchedRecord.getGrade() != student6.getGrade()) {
      return false;
    }

    // Look up a student by email that does not exist in the Gradebook
    StudentRecord matchedRecord2 = gradebook.lookup("charit@wisc.edu");

    // Check if the lookup returns null for a non-existent student
    if (matchedRecord2 != null) {
      return false;
    }

    // If all tests pass, the test is successful
    return true;
  }

  public static boolean toStringTester() {

    try {
      // Create a new Gradebook and add several students to it
      Gradebook gb = new Gradebook("CS", 35.0);

      // Adding students
      gb.addStudent(new StudentRecord("A", "a@gmail.com", 85));
      gb.addStudent(new StudentRecord("C", "c@gmail.com", 90));
      gb.addStudent(new StudentRecord("D", "d@gmail.com", 75));
      gb.addStudent(new StudentRecord("B", "b@gmail.com", 72));

      // Expected output with name, email, and grade
      String expected = "A (a@gmail.com): 85.0\n" + "B (b@gmail.com): 72.0\n" + "C (c@gmail.com): "
          + "90.0\n" + "D (d@gmail.com): 75.0\n";

      // To get the actual output from the Gradebook's toString method

      String actual = gb.toString();
      // Check if the actual output matches the expected output

      if (!actual.equals(expected)) {
        System.out.println("ToString test failed: Expected output does not match actual output.");
        return false;
      }
      // Catch and report any unexpected exceptions during the test
    } catch (Exception e) {
      System.out.println("toString test failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean getMinTester() {
    try {
      // Create a new Gradebook and add two students to it
      Gradebook gradebook = new Gradebook("Math", 60.0);
      gradebook.addStudent(new StudentRecord("John", "john@example.com", 70.0));
      gradebook.addStudent(new StudentRecord("Jane", "jane@example.com", 60.0));

      // Get the student with the minimum grade
      StudentRecord minRecord = gradebook.getMin();

      // Check if the student with the minimum grade is Jane
      if (minRecord == null || !minRecord.email.equals("jane@example.com")) {
        System.out.println(
            "getMin test failed: Expected minimum record does not match actual minimum record.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("getMin tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean removeStudentTester() {
    try {
      // Initialize the gradebook
      Gradebook gb = new Gradebook("CS", 35.0);

      // Add students to the gradebook
      StudentRecord A = new StudentRecord("A", "a@gmail.com", 88);
      StudentRecord B = new StudentRecord("B", "b@gmail.com", 72);
      StudentRecord C = new StudentRecord("C", "c@gmail.com", 90);
      StudentRecord D = new StudentRecord("D", "d@gmail.com", 85);
      StudentRecord F = new StudentRecord("F", "f@gmail.com", 88);

      gb.addStudent(D);
      gb.addStudent(B);
      gb.addStudent(C);
      gb.addStudent(A);
      gb.addStudent(F);

      // Remove a student with two children
      gb.removeStudent("b@gmail.com");

      // Expected prettyString after removal
      String expectedPrettyString = "    F\n" + "D\n" + "    C\n" + "        A\n";

      // Compare the actual and expected prettyStrings
      String actualPrettyString = gb.prettyString();
      if (!expectedPrettyString.equals(actualPrettyString)) {
        System.out
            .println("Remove student tester failed - Tree structure after removal is incorrect.");
        System.out.println("Expected prettyString:\n" + expectedPrettyString);
        System.out.println("Actual prettyString:\n" + actualPrettyString);
        return false;
      }

      // If all checks pass, the test is successful
      return true;
    } catch (Exception e) {
      System.out.println("Remove student tester failed with unexpected exception: " + e);
      return false;
    }
  }

  public static boolean iteratorTester() {
    try {
      // Create a new Gradebook and add a student to it
      Gradebook gradebook = new Gradebook("Math", 60.0);
      gradebook.addStudent(new StudentRecord("John", "john@example.com", 70.0));

      // Get an iterator for the Gradebook
      Iterator<StudentRecord> iterator = gradebook.iterator();
      boolean hasNext = iterator.hasNext();

      // Check if the iterator has a next element
      if (hasNext) {
        // Get the next element from the iterator
        StudentRecord record = iterator.next();

        // Check if the next element is the expected student
        if (!record.email.equals("john@example.com")) {
          System.out
              .println("Iterator test failed - Expected record does not match actual record.");
          return false;
        }
      } else {
        System.out.println("Iterator test failed: No records found.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Iterator tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean passingIteratorTester() {
    try {
      // Setup a Gradebook with a specified course and passing grade
      Gradebook gradebook = new Gradebook("CS300", 70.0);
      // Enable the passing grade iterator to filter for students passing the course
      gradebook.enablePassingGradeIterator();
      // Add students with varying grades to the gradebook
      gradebook.addStudent(new StudentRecord("Axle", "Axle@email.com", 65));
      gradebook.addStudent(new StudentRecord("Ben", "Ben@email.com", 85));
      gradebook.addStudent(new StudentRecord("Charlie", "Charlie@email.com", 75));
      gradebook.addStudent(new StudentRecord("Dane", "Dane@email.com", 55));
      gradebook.addStudent(new StudentRecord("Eshaan", "Eshaan@email.com", 95));

      // Initialize the iterator for students with passing grades only
      PassingGradeIterator passingIterator = new PassingGradeIterator(gradebook);

      // The expected order of students with passing grades
      String[] expectedPassingStudents = {"Ben", "Charlie", "Eshaan"};
      int index = 0;

      // Iterate through the passing students and check if they are in expected order
      while (passingIterator.hasNext()) {
        StudentRecord record = passingIterator.next();
        if (!record.name.equals(expectedPassingStudents[index])) {
          System.out.println("Iterator test failed: Expected " + expectedPassingStudents[index]
              + ", but got " + record.name);
          return false;
        }
        index++;
      }

      // Ensure that all passing records have been iterated over
      if (index != expectedPassingStudents.length) {
        System.out.println("Iterator test failed: Not all passing records were iterated over.");
        return false;
      }

      // Check that the NoSuchElementException is thrown if next() is called without
      // more elements
      try {
        passingIterator.next();
        System.out.println(
            "Iterator test failed: Expected NoSuchElementException for calling next() with no more"
                + " passing elements.");
        return false;
      } catch (NoSuchElementException expected) {
        // This is expected, so the test should pass
      }
      // Handle and report any unexpected exceptions during the test
    } catch (Exception e) {
      System.out.println("Remove student tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

  public static boolean successorTester() {
    Gradebook gradebook = new Gradebook("English", 80.0);
    StudentRecord student1 = new StudentRecord("Alexander", "alexander@wisc.edu", 81.0);
    StudentRecord student2 = new StudentRecord("Megan", "megan@wisc.edu", 99.0);
    StudentRecord student3 = new StudentRecord("Daniel", "daniel@wisc.edu", 90.0);
    StudentRecord student4 = new StudentRecord("Matthew", "matthew@wisc.edu", 81.0);
    StudentRecord student5 = new StudentRecord("Emily", "emily@wisc.edu", 88.0);
    StudentRecord student6 = new StudentRecord("Robert", "robert@wisc.edu", 85.0);
    // Adding students to the gradebook
    gradebook.addStudent(student1);
    gradebook.addStudent(student2);
    gradebook.addStudent(student3);
    gradebook.addStudent(student4);
    gradebook.addStudent(student5);
    gradebook.addStudent(student6);

    // Checking the successor of student1, it should be student6
    if (!gradebook.successor(student1).equals(student6)) {
      return false;
    }

    // Checking the successor of student3, it should be student1
    if (!gradebook.successor(student3).equals(student1)) {
      return false;
    }

    // Checking the successor of student5, it should be null
    if (gradebook.successor(student5) != null) {
      return false;
    }

    // If all checks pass, return true
    return true;
  }

  public static boolean prettyStringTester() {
    try {
      // Create a new Gradebook and add a student to it
      Gradebook gradebook = new Gradebook("Math", 60.0);
      gradebook.addStudent(new StudentRecord("John", "john@example.com", 70.0));

      // Get the pretty string representation of the Gradebook
      String prettyString = gradebook.prettyString();

      // Check if the pretty string contains the expected information
      if (!prettyString.contains("John")) {
        System.out
            .println("PrettyString test failed: Expected string does not match actual string.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("PrettyString tester failed with an unexpected exception: " + e);
      return false;
    }
    return true;
  }

}
