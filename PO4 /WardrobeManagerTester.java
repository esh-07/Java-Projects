import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.io.File;
import java.text.ParseException;
import java.util.Arrays;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WardrobeManagerTester
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Sehar Randhawa
// Partner Email: randhawa3@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A tester class for the Wardrobe Manager project. It contains various tests to
 * check the
 * correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness. This
   * test accounts for
   * the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");

      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data
      // field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt"))
        return false;
      if (!c.getBrand().equals("Gildan"))
        return false;
      if (c.getNumOfTimesWorn() != 0)
        return false;
      if (c.getLastWornDate() != null)
        return false;

      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans"))
        return false;
      if (!c.getBrand().equals("Levi"))
        return false;
      if (c.getNumOfTimesWorn() != 3)
        return false;
      if (!c.getLastWornDate().equals(date))
        return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad
                            // implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests that both of the Clothing constructors throw the correct type of
   * exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an
    // IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw
    // exceptions,
    // so we test them in separate try-catch statements to verify that each
    // individual
    // case throws an exception.

    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken
                    // implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken
      // implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    // passed all the tests!
    return true;
  }

  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method.
   * This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   */
  public static boolean testClothingWear() {
    try {
      Clothing clothing = new Clothing("Shirt", "Nike");
      clothing.wearClothing(-1, 1, 1); // This should throw an exception
      return false; // If it reaches this line, it means no exception was thrown, which is a test
                    // failure
    } catch (IllegalArgumentException e) {
      if (e.getMessage().isEmpty()) {
        return false; // If the exception message is empty, the test fails
      }
    } catch (Exception e) {
      return false; // If it throws any other exception, the test fails
    }
    // If it throws the expected exception with a non-empty message, the test passes
    return true;
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness. This test
   * accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testWardrobeConstructorAndGetters() {
    try {
      // Create a new Wardrobe with a capacity of 2
      Wardrobe wardrobe = new Wardrobe(2);
      // Check if the size of the new Wardrobe is 0 and its capacity is 2
      return wardrobe.size() == 0 && wardrobe.capacity() == 2;
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s)
   * with a message in
   * situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {
    try {
      // Try to create a new Wardrobe with a negative capacity
      Wardrobe wardrobe = new Wardrobe(-1);
      // If no exception is thrown, the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // Check if the thrown exception has a non-empty message
      return e.getMessage() != null && !e.getMessage().isEmpty();
    }
  }

  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of
   * exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {
    try {
      // Create a new Wardrobe with a capacity of 1
      Wardrobe wardrobe = new Wardrobe(1);
      // Create a new Clothing item
      Clothing clothing1 = new Clothing("Shirt", "Nike");
      // Add the Clothing item to the Wardrobe
      wardrobe.addClothing(clothing1);
      // Try to add the same Clothing item to the Wardrobe again
      wardrobe.addClothing(clothing1);
      // If no exception is thrown, the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // If the exception message is empty, the test fails
      if (e.getMessage().isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      // If it throws any other exception, the test fails
      return false;
    }
    // If it throws the expected exception with a non-empty message, the test passes
    return true;
  }

  /**
   * Tests the Wardrobe's addClothing() method for correctness. This test accounts
   * for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {
    try {
      // Create a new Wardrobe with a capacity of 1
      Wardrobe wardrobe = new Wardrobe(1);
      // Create two new Clothing items
      Clothing clothing1 = new Clothing("Shirt", "Nike");
      Clothing clothing2 = new Clothing("Pants", "Adidas");
      // Add the Clothing items to the Wardrobe
      wardrobe.addClothing(clothing1);
      wardrobe.addClothing(clothing2);
      // Check if the size of the Wardrobe is now 2
      return wardrobe.size() == 2;
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests the Wardrobe's getClothing() method for correctness. This test accounts
   * for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {
    try {
      // Create a new Wardrobe with a capacity of 2
      Wardrobe wardrobe = new Wardrobe(2);
      // Create a new Clothing item
      Clothing clothing = new Clothing("Shirt", "Nike");
      // Add the Clothing item to the Wardrobe
      wardrobe.addClothing(clothing);
      // Check if the retrieved Clothing item is the same as the one that was added
      return clothing.equals(wardrobe.getClothing("Shirt", "Nike"));
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of
   * exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(1);
      wardrobe.getClothing("Shirt", "Nike"); // This should throw an exception
      return false; // If it reaches this line, it means no exception was thrown, which is a test
                    // failure
    } catch (NoSuchElementException e) {
      if (e.getMessage().isEmpty()) {
        return false; // If the exception message is empty, the test fails
      }
    } catch (Exception e) {
      return false; // If it throws any other exception, the test fails
    }
    // If it throws the expected exception with a non-empty message, the test passes
    return true;
  }

  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of
   * exception(s) with
   * a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {
    try {
      Wardrobe wardrobe = new Wardrobe(1);
      wardrobe.removeClothing("Shirt", "Nike"); // This should throw an exception
      return false; // If it reaches this line, it means no exception was thrown, which is a test
                    // failure
    } catch (IllegalStateException e) {
      if (e.getMessage().isEmpty()) {
        return false; // If the exception message is empty, the test fails
      }
    } catch (Exception e) {
      return false; // If it throws any other exception, the test fails
    }
    // If it throws the expected exception with a non-empty message, the test passes
    return true;
  }

  /**
   * Tests the Wardrobe's removeClothings() method for correctness. This test
   * accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    try {
      // Create a new Wardrobe with a capacity of 2
      Wardrobe wardrobe = new Wardrobe(2);
      // Create a new Clothing item
      Clothing clothing = new Clothing("Shirt", "Nike");
      // Add the Clothing item to the Wardrobe
      wardrobe.addClothing(clothing);
      // Remove the Clothing item from the Wardrobe
      wardrobe.removeClothing("Shirt", "Nike");
      // Check if the size of the Wardrobe is now 0
      return wardrobe.size() == 0;
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
   * This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    try {
      // Create a new Wardrobe with a capacity of 2
      Wardrobe wardrobe = new Wardrobe(2);
      // Create two new Clothing items
      Clothing clothing1 = new Clothing("Shirt", "Nike");
      Clothing clothing2 = new Clothing("Pants", "Adidas");
      // Wear the Clothing items on specific dates
      clothing1.wearClothing(2021, 1, 1);
      clothing2.wearClothing(2023, 1, 1);
      // Add the Clothing items to the Wardrobe
      wardrobe.addClothing(clothing1);
      wardrobe.addClothing(clothing2);
      // Remove all Clothing items worn before a specific date
      wardrobe.removeAllClothingWornBefore(2022, 1, 1);
      // Check if the size of the Wardrobe is now 1
      return wardrobe.size() == 1;
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
   * This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    try {
      // Create a new Wardrobe with a capacity of 2
      Wardrobe wardrobe = new Wardrobe(2);
      // Create two new Clothing items with specific wear counts
      Clothing clothing1 = new Clothing("Shirt", "Nike", 1, null);
      Clothing clothing2 = new Clothing("Pants", "Adidas", 3, null);
      // Add the Clothing items to the Wardrobe
      wardrobe.addClothing(clothing1);
      wardrobe.addClothing(clothing2);
      // Remove all Clothing items worn a specific number of times
      wardrobe.removeAllClothingWornNumTimes(2);
      // Check if the size of the Wardrobe is now 1
      return wardrobe.size() == 1;
    } catch (Exception e) {
      // If any exception is thrown, the test fails
      return false;
    }
  }

  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of
   * exception(s) with a
   * message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {
    try {
      Wardrobe.parseClothing("Invalid string"); // This should throw an exception
      return false; // If it reaches this line, it means no exception was thrown, which is a test
                    // failure
    } catch (ParseException e) {
      if (e.getMessage().isEmpty()) {
        return false; // If the exception message is empty, the test fails
      }
    } catch (Exception e) {
      return false; // If it throws any other exception, the test fails
    }
    // If it throws the expected exception with a non-empty message, the test passes
    return true;
  }

  /**
   * Tests the Wardrobe's parseClothing method for correctness. This test accounts
   * for the fact a
   * bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    try {
      // Test with valid input
      // Parse a Clothing item from a valid string
      Clothing clothing = Wardrobe.parseClothing("Shirt,Nike,null,0");
      // Check if the parsed Clothing item has the correct properties
      if (!"Shirt".equals(clothing.getDescription()) || !"Nike".equals(clothing.getBrand())
          || clothing.getLastWornDate() != null || clothing.getNumOfTimesWorn() != 0) {
        return false; // The parsed Clothing item is incorrect
      }

      // Test with invalid input
      try {
        // Try to parse a Clothing item from an invalid string
        Wardrobe.parseClothing("Shirt,Nike");
        return false; // Should have thrown an exception
      } catch (ParseException e) {
        // Expected exception
      }

      return true; // All tests passed
    } catch (Exception e) {
      return false; // An unexpected exception occurred
    }
  }

  /**
   * Runs all testing methods and prints out their results.
   * 
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14 && test15;
  }

  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
