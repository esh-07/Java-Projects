//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Gradebook
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
 * This class represents a gradebook for a course, which can be iterated over to access student
 * records.
 */
public class Gradebook implements Iterable<StudentRecord> {
  public final String course; // The course for this gradebook
  public final double PASSING_GRADE; // The passing grade for this course
  private boolean passingGradeIteratorEnabled; // Whether the passing grade iterator is enabled
  private BSTNode<StudentRecord> root; // The root of the binary search tree of student records
  private int size; // The number of student records

  /**
   * Constructs a new Gradebook for the given course with the given passing grade.
   *
   * @param course       The course for this gradebook.
   * @param passingGrade The passing grade for this course.
   */
  public Gradebook(String course, double passingGrade) {
    if (course == null || course.isBlank() || passingGrade < 0 || passingGrade > 100) {
      throw new IllegalArgumentException();
    }
    this.course = course;
    this.PASSING_GRADE = passingGrade;
    this.passingGradeIteratorEnabled = false;
    this.root = null;
    this.size = 0;
  }

  /**
   * Enables the passing grade iterator.
   */
  public void enablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = true;
  }

  /**
   * Disables the passing grade iterator.
   */
  public void disablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = false;
  }

  /**
   * Returns whether this gradebook is empty.
   *
   * @return true if this gradebook is empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the number of student records in this gradebook.
   *
   * @return The number of student records.
   */
  public int size() {
    return this.size;
  }

  /**
   * Adds a student record to this gradebook.
   *
   * @param record The student record to add.
   */
  public void addStudent(StudentRecord record) {
    if (root != null && root.getData().equals(record)) {
      throw new IllegalStateException("A match with record is already in this tree");
    }
    root = addStudentHelper(record, root);
    size++;
  }

  /**
   * Helper method to add a student record to the binary search tree of student records.
   *
   * @param record The student record to add.
   * @param node   The current node in the binary search tree.
   * @return The new root of the binary search tree.
   */
  protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record,
      BSTNode<StudentRecord> node) {
    // If the current node is null, create a new node with the record and return it
    if (node == null) {
      return new BSTNode<>(record);
    }
    // Compare the record to the data in the current node
    int comparison = record.compareTo(node.getData());
    // If the record is less than the data in the current node, add the record to
    // the left subtree
    if (comparison < 0) {
      node.setLeft(addStudentHelper(record, node.getLeft()));
    }
    // If the record is greater than the data in the current node, add the record to
    // the right subtree
    else if (comparison > 0) {
      node.setRight(addStudentHelper(record, node.getRight()));
    }
    // If the record is equal to the data in the current node, throw an exception
    // because duplicate records are not allowed
    else {
      throw new IllegalStateException("The subtree rooted at node contains a duplicate record");
    }
    // Return the current node
    return node;
  }

  /**
   * This method looks up a student in the gradebook by their email.
   * 
   * @param email The email of the student to look up.
   * @return The StudentRecord of the student if found, null otherwise.
   * @throws IllegalArgumentException if the email is null or blank.
   */
  public StudentRecord lookup(String email) {
    // Check if the email is null or blank, and if so, throw an exception
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be null or blank");
    }
    // Create a dummy StudentRecord with the given email to use for comparison
    StudentRecord target = new StudentRecord("dummy", email, 0);
    // Call the helper method to perform the lookup
    return lookupHelper(target, root);
  }

  /**
   * This helper method recursively searches the BST for a student with a matching email.
   * 
   * @param target The StudentRecord to find.
   * @param node   The current node in the BST.
   * @return The StudentRecord of the student if found, null otherwise.
   */
  protected static StudentRecord lookupHelper(StudentRecord target, BSTNode<StudentRecord> node) {
    // If the node is null, we've reached the end of a branch and the student was
    // not found
    if (node == null) {
      return null;
    }
    // Compare the emails of the target and the current node
    int comparison = target.email.compareTo(node.getData().email);
    // If the target email is less than the current node's email, search the left
    // subtree
    if (comparison < 0) {
      return lookupHelper(target, node.getLeft());
    }
    // If the target email is greater than the current node's email, search the
    // right subtree
    else if (comparison > 0) {
      return lookupHelper(target, node.getRight());
    }
    // If the target email is equal to the current node's email, we've found the
    // student
    else {
      return node.getData();
    }
  }

  /**
   * Checks if the student with the given email is passing the course.
   *
   * @param email The email of the student.
   * @return A string indicating whether the student is passing or failing, or that no match was
   *         found.
   */
  public String checkPassingCourse(String email) {
    // Look up the student record for the given email
    StudentRecord record = lookup(email);
    // If no record was found, return a message indicating this
    if (record == null) {
      return "No match found.";
    }
    // Otherwise, return a string representation of the record, along with whether
    // the student is passing or failing
    return record.toString() + (record.getGrade() >= PASSING_GRADE ? ": PASS" : ": FAIL");
  }

  /**
   * Returns the student record with the minimum grade in the gradebook.
   *
   * @return The student record with the minimum grade.
   */
  protected StudentRecord getMin() {
    // Get the minimum student record from the root of the binary search tree
    return getMinHelper(root);
  }

  /**
   * Helper method to get the student record with the minimum grade from a binary search tree.
   *
   * @param node The root of the binary search tree.
   * @return The student record with the minimum grade.
   */
  protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
    // If the tree is empty, return null
    if (node == null) {
      return null;
    }
    // Otherwise, traverse the left subtree until the leftmost node is found
    while (node.getLeft() != null) {
      node = node.getLeft();
    }
    // Return the data in the leftmost node, which is the minimum student record
    return node.getData();
  }

  /**
   * Returns the successor of the target StudentRecord in the Gradebook.
   * 
   * @param target The StudentRecord whose successor is to be found.
   * @return The successor StudentRecord.
   */
  protected StudentRecord successor(StudentRecord target) {
    return successorHelper(target, root);
  }

  /**
   * Helper method for the successor method. Recursively finds the successor of the target
   * StudentRecord.
   * 
   * @param target The StudentRecord whose successor is to be found.
   * @param node   The current node in the BST.
   * @return The successor StudentRecord.
   */
  protected static StudentRecord successorHelper(StudentRecord target,
      BSTNode<StudentRecord> node) {
    if (node == null) {
      return null;
    }
    if (target.compareTo(node.getData()) < 0) {
      StudentRecord left = successorHelper(target, node.getLeft());
      // If left is not null, it is the successor
      if (left != null) {
        return left;
      }
      // If left is null, the current node is the successor
      return node.getData();
    }
    // If target is greater than or equal to the current node, the successor is in
    // the right subtree
    return successorHelper(target, node.getRight());
  }

  /**
   * Removes a StudentRecord from the Gradebook.
   * 
   * @param email The email of the StudentRecord to be removed.
   * @throws NoSuchElementException If no matching StudentRecord is found.
   */
  public void removeStudent(String email) {
    StudentRecord toDrop = lookup(email);
    if (toDrop == null) {
      throw new NoSuchElementException("No matching StudentRecord in this Gradebook");
    }
    root = removeStudentHelper(toDrop, root);
    size--;
  }

  /**
   * Helper method for the removeStudent method. Recursively removes the target StudentRecord.
   * 
   * @param toDrop The StudentRecord to be removed.
   * @param node   The current node in the BST.
   * @return The updated BSTNode after removal.
   * @throws NoSuchElementException If no matching StudentRecord is found in the subtree.
   */
  protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop,
      BSTNode<StudentRecord> node) {
    if (node == null) {
      throw new NoSuchElementException("No matching StudentRecord in this subtree");
    }
    int comparison = toDrop.compareTo(node.getData());
    if (comparison < 0) {
      // If toDrop is less than the current node, it is in the left subtree
      node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
    } else if (comparison > 0) {
      // If toDrop is greater than the current node, it is in the right subtree
      node.setRight(removeStudentHelper(toDrop, node.getRight()));
    } else {
      // If toDrop is equal to the current node, remove the current node
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else {
        // If the current node has two children, replace it with the minimum node in the
        // right subtree
        StudentRecord minRight = getMinHelper(node.getRight());
        node.setData(minRight);
        node.setRight(removeStudentHelper(minRight, node.getRight()));
      }
    }
    return node;
  }

  /**
   * Returns a string representation of the Gradebook.
   * 
   * @return A string representation of the Gradebook.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Helper method for the toString method. Recursively builds a string representation of the BST.
   * 
   * @param node The current node in the BST.
   * @return A string representation of the BST.
   */
  protected static String toStringHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return "";
    }
    // Build the string representation in in-order traversal order
    return toStringHelper(node.getLeft()) + node.getData().toString() + "\n"
        + toStringHelper(node.getRight());
  }

  /**
   * Returns a pretty string representation of the gradebook.
   *
   * @return A string representation of the gradebook.
   */
  public String prettyString() {
    // Start the pretty string helper at the root of the binary search tree with a
    // depth of 0
    return prettyStringHelper(root, 0);
  }

  /**
   * Helper method to create a pretty string representation of a binary search tree.
   *
   * @param node  The current node in the binary search tree.
   * @param depth The current depth in the binary search tree.
   * @return A string representation of the binary search tree.
   */
  protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {
    // If the current node is null, return an empty string
    if (node == null) {
      return "";
    }
    // Create an indent string based on the current depth
    String indent = " ".repeat(depth * 4);
    // Return a string representation of the right subtree, the current node's NAME,
    // and
    // the left subtree
    return prettyStringHelper(node.getRight(), depth + 1) + indent + node.getData().name + "\n"
        + prettyStringHelper(node.getLeft(), depth + 1);
  }

  /**
   * Checks if the binary search tree of this gradebook is equal to another binary search tree.
   *
   * @param node The root of the other binary search tree.
   * @return true if the binary search trees are equal, false otherwise.
   */
  public boolean equalBST(BSTNode<StudentRecord> node) {
    // Compare the root of this binary search tree to the other binary search tree
    return root.equals(node);
  }

  /**
   * Returns an iterator over the student records in the gradebook.
   *
   * @return An iterator over the student records.
   */
  @Override
  public Iterator<StudentRecord> iterator() {
    // Create a new GradebookIterator for this gradebook
    return new GradebookIterator(this);
  }
}
