//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ToySagaTester
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements tester methods to ensure the correctness of the implementation of Furniture
 * and Toy classes in p03 Toy Saga I program.
 */
public class ToySagaTester {

  /**
   * This tester ensures the Furniture constructor which takes a String as input Furniture(String
   * name) correctly constructs a new Furniture object located at the center of the display window,
   * and assigned it a PImage and the name passed as input to the method call.
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor1Getters() {

    // Create at least one new Furniture object by called the constructor
    // Furniture(String). The
    // String passed as input to the constructor call should be either "bed", "box",
    // "nightstand",
    // or "rug".

    Furniture furniture = new Furniture("bed");

    // Ensure that the call of getX() on that Furniture object equals
    // Utility.width() / 2
    // Ensure that the call of getY() on that Furniture object equals
    // Utility.height() / 2

    boolean test1 =
        furniture.getX() == Utility.width() / 2 && furniture.getY() == Utility.height() / 2;

    // Ensure that the call of name() on that Furniture object returns the name
    // passed as input
    // to the constructor call

    boolean test2 = furniture.name().equals("bed");
    // Ensure that the value of the image field on that Furniture object returns a
    // NON-null
    // reference.

    boolean test3 = furniture.IMAGE != null;
    // This test should fail if any of the above requirements is NOT satisfied

    return test1 && test2 && test3;
  }

  /**
   * This tester ensures the Furniture constructor which takes a String, and two integers as input
   * Furniture(String name, int x, int y) correctly constructs a new Furniture object located at the
   * (x,y) input position, assigned it the name passed as input to the method call, and an image.
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor2Getters() {
    // Create at least one new Furniture object by called the constructor
    // Furniture(String, int,
    // int). The String passed as input to the constructor call should be either
    // "bed", "box",
    // "nightstand", or "rug".

    Furniture furniture1 = new Furniture("rug", 105, 205);
    // Ensure that the call of getX() on that Furniture object equals the input x
    boolean test1 = furniture1.getX() == 105 && furniture1.getY() == 205;
    // Ensure that the call of getY() on that Furniture object equals the input y

    // Ensure that the call of name() on that Furniture object returns the name
    // passed as input
    // to the constructor call
    boolean test2 = furniture1.name().equals("rug");
    // Ensure that the value of the image field on that Furniture object returns a
    // NON-null
    // reference.

    boolean test3 = furniture1.IMAGE != null;
    // This test should fail if any of the above requirements is NOT satisfied

    return test1 && test2 && test3; // default return statement
  }

  /**
   * This tester ensures the Toy constructors, getters and setters of the x and y positions, and the
   * image field.
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyConstructorsGettersSetters() {
    // Instantiate a Toy object using the constructor that takes a string argument
    Toy toy1 = new Toy("car");

    // Verify that the Toy object is centered in the display window
    boolean test1 = toy1.getX() == Utility.width() / 2 && toy1.getY() == Utility.height() / 2;
    // Check if the IMAGE field of the toy is not null
    boolean test2 = toy1.IMAGE != null;
    // Check if the toy is not being dragged
    boolean test3 = !toy1.isDragging();
    // Set new x and y positions for the toy
    toy1.setX(150);
    toy1.setY(250);
    // Check if the new positions have been set correctly
    boolean test4 = toy1.getX() == 150 && toy1.getY() == 250;

    // Instantiate another Toy object using the constructor that takes a string and
    // two integers
    Toy toy2 = new Toy("teddyBear", 200, 500);
    // Set new x and y positions for the second toy
    toy2.setX(300);
    toy2.setY(400);
    // Check if the new positions have been set correctly
    boolean test5 = toy2.getX() == 300 && toy2.getY() == 400;
    // Check if the IMAGE field of the second toy is not null
    boolean test6 = toy2.IMAGE != null;
    // Check if the second toy is not being dragged
    boolean test7 = !toy2.isDragging();
    // Return true if all tests pass, false otherwise
    return test1 && test2 && test3 && test4 && test5 && test6 && test7;
  }

  /**
   * This tester ensures the correctness of Toy.startDragging() and Toy.stopDragging instance
   * methods
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyStartStopDragging() {
    // This tester should construct at least one Toy object and call startDragging()
    // and stopDragging() methods on that object.
    Toy toy = new Toy("teddyBear");
    toy.startDragging();
    boolean test1;
    // Ensure the isDragging() method call returns true after every call of the
    // startDragging()
    if (toy.isDragging()) {
      test1 = true;
    } else {
      test1 = false;
    }
    toy.stopDragging();
    boolean test2;
    // Ensure the isDragging() method call returns true after every call of the
    // startDragging()
    if (!toy.isDragging()) {
      test2 = true;
    } else {
      test2 = false;
    }
    return test1 && test2;
  }

  /**
   * This tester ensures the correctness of Toy.move() method
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyMove() {
    // This tester should construct at least one Toy object at a given x,y position
    Toy toy = new Toy("teddyBear", 100, 100);
    toy.move(50, 60);

    // Every call on move(int dx, int dy) method should add dx and dy to the current
    // x and y position of the Toy object, respectively.

    boolean test1;
    if (toy.getX() == 150 && toy.getY() == 160) {
      test1 = true;
    } else {
      test1 = false;
    }
    // Try calling move method with positive and negative dx and dy inputs

    toy.move(-50, -60);

    // Declare a boolean variable 'test2'
    boolean test2;

    // Check if the x and y positions of the toy are both 100
    // If they are, set 'test2' to true
    if (toy.getX() == 100 && toy.getY() == 100) {
      test2 = true;
    } else {
      // If the x and y positions of the toy are not both 100, set 'test2' to false
      test2 = false;
    }

    // Return the logical AND of 'test1' and 'test2'
    // This will be true only if both 'test1' and 'test2' are true
    // If either 'test1' or 'test2' is false, this will return false
    return test1 && test2; // default return statement
  }

  /**
   * This tester ensures the correctness of Toy.rotate() method.
   *
   * @author Mouna
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   *
   */
  public static boolean testToyRotate() {
    // This method's implementation is entirely provided to you
    // Create two Toy objects
    Toy car1 = new Toy("car");
    Toy car2 = new Toy("car");

    // Ensures getRotationsCount() returns zero when called on newly constructed Toy
    // objects
    if (car1.getRotationsCount() != 0) {
      System.out
          .println("Toy.getRotationsCount() should return zero when called on a new created Toy "
              + "object.");
      return false;
    }

    if (car2.getRotationsCount() != 0) {
      System.out
          .println("Toy.getRotationsCount() should return zero when called on a new created Toy "
              + "object.");
      return false;
    }
    // rotate car1 5 times
    for (int i = 0; i < 5; i++) {
      car1.rotate();
    }
    // Ensure the getRotationsCount returns the expected output
    if (car1.getRotationsCount() != 5) {
      System.out
          .println("Toy.getRotationsCount() did not return the expected output after calling the "
              + "rotate() " + "method multiple times.");
      return false;
    }
    // rotate car2 3 times
    for (int i = 0; i < 3; i++) {
      car2.rotate();
    }
    // Ensure the getRotationsCount returns the expected output
    if (car2.getRotationsCount() != 3) {
      System.out
          .println("Toy.getRotationsCount() did not return the expected output after calling the "
              + "rotate() " + "method multiple times.");
      return false;
    }
    return true; // Test passes with no errors
  }

  /**
   * This tester checks the correctness of Toy.isOver(int, int) method
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverPoint() {
    // This tester should check for the correctness of Toy.isOver(int x, int y)
    // method
    // This tester should construct at least one Toy object at a given (x,y)
    // position
    // At least 4 scenarios should be considered.

    Toy toy = new Toy("car", 51, 51);
    boolean test1;

    // (1) Calling the isOver(int, int) method on that Toy object passing it a point
    // defined by
    // x and y coordinates defined inside the area of the image of the toy should
    // return true
    if (toy.isOver(51, 51)) {
      test1 = true;
    } else {
      test1 = false;
    }

    boolean test2;
    // (2) Calling the isOver(int, int) method with a point (x,y) defined outside of
    // the area of
    // the image of the toy should return false.
    if (toy.isOver(61, 61)) {
      test2 = true;
    } else {
      test2 = false;
    }

    // Call the rotate() method one time or an odd number of times on the toy. This
    // should
    // rotate the image of the Toy object PI/2 clockwise so that the width and
    // height of the toy
    // are expected to be switched.

    toy.rotate();

    // (3) Call the isOver(int, int) method with a point (x,y) inside the area of
    // the Toy,
    // considering this change on the width and height dimensions, should return
    // true.
    boolean test3;

    // Check if the width and height are switched after rotation
    if (toy.isOver(51, 51)) {
      test3 = true;
    } else {
      test3 = false;
    }

    // (4) Call the isOver(int, int) method with a point (x,y) outside the area of
    // the Toy,
    // considering this change on the width and height dimensions, should return
    // false.

    boolean test4;
    if (toy.isOver(61, 61)) {
      test4 = true;
    } else {
      test4 = false;
    }

    return test1 && test2 && test3 && test4;
  }

  /**
   * This tester checks the correctness of Toy.isOver(Furniture) method
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverFurniture() {
    // Create a Furniture object at a given (x, y) position.
    Furniture furniture = new Furniture("bed", 50, 50);

    // Create three Toy objects: one intersecting, one enclosed, and one
    // non-overlapping with the Furniture.

    // (1) Intersecting Toy
    // Create a Toy object 'intersectToy' at position (50, 50)
    Toy intersectToy = new Toy("car", 50, 50);
    // Declare a boolean variable 'test1'
    boolean test1;
    // Check if 'intersectToy' is overlapping with 'furniture'
    // If it is, set 'test1' to true
    // If it isn't, set 'test1' to false
    if (intersectToy.isOver(furniture)) {
      test1 = true;
    } else {
      test1 = false;
    }

    // (2) Enclosed Toy
    // Create a Toy object 'enclosedToy' at position (52, 50)
    Toy enclosedToy = new Toy("teddyBear", 52, 50);
    // Declare a boolean variable 'test2'
    boolean test2;
    // Check if 'enclosedToy' is overlapping with 'furniture'
    // If it is, set 'test2' to true
    // If it isn't, set 'test2' to false
    if (enclosedToy.isOver(furniture)) {
      test2 = true;
    } else {
      test2 = false;
    }

    // (3) Non-overlapping Toy
    // Create a Toy object 'notOverlapToy' at position (999, 999)
    Toy notOverlapToy = new Toy("car", 999, 999);
    // Declare a boolean variable 'test3'
    boolean test3;
    // Check if 'notOverlapToy' is NOT overlapping with 'furniture'
    // If it isn't, set 'test3' to true
    // If it is, set 'test3' to false
    if (!notOverlapToy.isOver(furniture)) {
      test3 = true;
    } else {
      test3 = false;
    }
    // Return the logical AND of 'test1', 'test2', and 'test3'
    // This will be true only if all three tests pass (i.e., all are true)
    // If any of the tests fail (i.e., is false), this will return false
    return test1 && test2 && test3;
  }

  /**
   * Runs all the tester methods defined in this class
   *
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean runAllTests() {
    System.out.println("Class Furniture Testers:");
    boolean test1Result = testFurnitureConstructor1Getters();
    System.out.println("testFurnitureConstructor1Getters: " + (test1Result ? "PASS" : "FAIL"));

    boolean test2Result = testFurnitureConstructor2Getters();
    System.out.println("testFurnitureConstructor2Getters: " + (test2Result ? "PASS" : "FAIL"));

    System.out.println();
    System.out.println("Class Toy Testers:");
    boolean test3Result = testToyConstructorsGettersSetters();
    System.out.println("testToyConstructorsGettersSetters: " + (test3Result ? "PASS" : "FAIL"));

    boolean test4Result = testToyStartStopDragging();
    System.out.println("testToyStartStopDragging: " + (test4Result ? "PASS" : "FAIL"));

    boolean testToyMove = testToyMove();
    System.out.println("testToyMove: " + (testToyMove ? "PASS" : "FAIL"));

    boolean testToyRotate = testToyRotate();
    System.out.println("testToyRotate: " + (testToyRotate ? "PASS" : "FAIL"));

    boolean testToyIsOverPoint = testToyIsOverPoint();
    System.out.println("testToyIsOverPoint: " + (testToyIsOverPoint ? "PASS" : "FAIL"));

    boolean testToyIsOverFurniture = testToyIsOverFurniture();
    System.out.println("testToyIsOverFurniture: " + (testToyIsOverFurniture ? "PASS" : "FAIL"));

    return test1Result && test2Result && test3Result && test4Result && testToyMove && testToyRotate
        && testToyIsOverPoint && testToyIsOverFurniture;
  }

  /**
   * Driver method to run all the tests defined in this class
   *
   * @param args list of command-line input arguments if any.
   */
  public static void main(String[] args) {
    // DO NOT MAKE ANY CHANGES TO THE IMPLEMENTATION OF THIS METHOD
    Utility.runTester();
  }

}
