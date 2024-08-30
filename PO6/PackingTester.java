//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PackingTester
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
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

import java.util.ArrayList;

/**
 * Class used for testing the methods in the Packing class.
 */
public class PackingTester {
  /**
   * Tester method for the Packing.rushedPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingBaseTest() {
    // Test case 1: No items left to pack
    {
      // Creating an empty suitcase
      Suitcase emptySuitcase = new Suitcase(10, 10, new ArrayList<>());

      try {
        // Attempting to pack the empty suitcase
        emptySuitcase = Packing.rushedPacking(emptySuitcase);
      } catch (Exception e) {
        // Test 1a: This scenario should not result in an exception
        System.out.println("Test 1a: Unexpected exception thrown!");
        return false;
      }

      // Test 1b: In this scenario, no items should be packed
      if (!emptySuitcase.getPackedItems().isEmpty()) {
        System.out.println("Test 1b: Items were packed when none should have been!");
        return false;
      }
    }

    // Test case 2: Items left to pack, but none of them fit
    {
      // Creating a list of items
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 11, 11)); // Item too big for the suitcase

      // Creating a suitcase with the items
      Suitcase fullSuitcase = new Suitcase(10, 10, items);

      try {
        // Attempting to pack the suitcase
        fullSuitcase = Packing.rushedPacking(fullSuitcase);
      } catch (Exception e) {
        // Test 2a: This scenario should not result in an exception
        System.out.println("Test 2a: Unexpected exception thrown!");
        return false;
      }

      // Test 2b: In this scenario, no items should be packed
      if (!fullSuitcase.getPackedItems().isEmpty()) {
        System.out.println("Test 2b: Items were packed when none should have been!");
        return false;
      }

      // Test 2c: The unpacked items list should match the original items list
      if (!fullSuitcase.getUnpackedItems().equals(items)) {
        System.out.println("Test 2c: Unpacked items list does not match expected!");
        return false;
      }
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tester method for the Packing.rushedPacking() method recursive cases. It should test at least
   * the following scenarios: - All the items remaining can fit in the suitcase - At least one item
   * remaining cannot fit in the suitcase
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingRecursiveTest() {

    // Test case 1: The suitcase can accommodate all items
    {
      ArrayList<Item> items = new ArrayList();

      // Adding items to the list
      items.add(new Item("Mysterious Box", 1, 1));
      items.add(new Item("Guitar", 1, 3));
      items.add(new Item("Stack of Shirts", 2, 2));
      items.add(new Item("Syrup", 1, 1));

      // Creating a new suitcase with the items
      Suitcase suitcase = new Suitcase(6, 4, items);

      try {
        // Attempting to pack the suitcase
        suitcase = Packing.rushedPacking(suitcase);
      } catch (Exception e) {
        // Test 1a: This scenario should not result in an exception
        System.out.println("Test 1a: Unexpected exception thrown!");
        return false;
      }

      // Test 1b: In this scenario, all items should be packed
      suitcase = Packing.rushedPacking(suitcase);
      int expectedPackedItems = items.size(); // assuming all items can fit in the suitcase

      if (suitcase.numItemsPacked() != expectedPackedItems) {
        System.out.println("Test 1b: Not all items were packed!");
        return false;
      }

      // Test 1c: All items from the items list should be moved to the itemsPackedList
      if (suitcase.numItemsPacked() < items.size()) {
        System.out.println("Test 1c: Packed items list is missing some items!");
        return false;
      }

      // Test 1d: All items should be packed in the order they were added
      if (suitcase.numItemsPacked() == items.size() && !suitcase.getPackedItems().equals(items)) {
        System.out.println("Test 1d: Packed items are not in the expected order!");
        return false;
      }
    }

    // Test case 2: At least one item remaining cannot fit in the suitcase
    {
      ArrayList<Item> items = new ArrayList();

      int suitcaseWidth = 4;
      int suitcaseHeight = 4;

      // Creating new items
      Item mysteriousBox = new Item("Mysterious Box", 2, 2);
      Item stick = new Item("Stick", 1, 3);
      Item food = new Item("Food", 2, 2);
      Item largeBox = new Item("largeBox", suitcaseWidth, suitcaseHeight);
      Item sand = new Item("Sand", 1, 1);
      Item guitar = new Item("Guitar", 1, 5);
      Item rocks = new Item("Rocks", 1, 2);

      // Adding items to the list
      items.add(mysteriousBox); // should be added 1st
      items.add(largeBox); // should NOT be added
      items.add(stick); // should be added 2nd
      items.add(food); // should be added 3rd
      items.add(sand); // should be added 4th
      items.add(guitar); // should NOT be added
      items.add(rocks); // should be added 5th

      // Creating expected packed and unpacked lists
      ArrayList<Item> expectedPackedList = new ArrayList();
      expectedPackedList.add(mysteriousBox);
      expectedPackedList.add(stick);
      expectedPackedList.add(food);
      expectedPackedList.add(sand);
      expectedPackedList.add(rocks);

      ArrayList<Item> expectedUnpackedList = new ArrayList();
      expectedUnpackedList.add(largeBox);
      expectedUnpackedList.add(guitar);

      // Creating a new suitcase with the items
      Suitcase suitcase = new Suitcase(suitcaseWidth, suitcaseHeight, items);

      try {
        // Attempting to pack the suitcase
        suitcase = Packing.rushedPacking(suitcase);
      } catch (Exception e) {
        // Test 2a: No exception should be thrown in this scenario
        System.out.println("Test 2a: Unexpected exception thrown!");
        return false;
      }

      int itemsThatCanBePacked = 5; // Variable to decide how many items this call should pack i.e.
                                    // "Mysterious
                                    // Box", etc
      int itemsThatCannotBePacked = 2; // Variable to decide how many items this call cannot pack
                                       // i.e. "Sand", "Rocks"

      // Test 2b: One item should be left unpacked
      if (suitcase.numItemsUnpacked() != itemsThatCannotBePacked) {
        System.out.println("Test 2b: Not all items were packed!");
        return false;
      }

      // Test 2c: One item should be packed
      if (suitcase.numItemsPacked() != itemsThatCanBePacked) {
        System.out.println("Test 2c: Some items that could fit were not included!");
        return false;
      }

      // Test 2d: unpackedItems should match expectedUnpackedList
      if (!suitcase.getUnpackedItems().equals(expectedUnpackedList)) {
        System.out.println("Test 2d: Unpacked items list does not match expected!");
        return false;
      }

      // Test 2e: packedItems should match expectedPackedList
      if (!suitcase.getPackedItems().equals(expectedPackedList)) {
        System.out.println("Test 2e: Packed items list does not match expected!");
        return false;
      }

      // Test 2f: The suitcase should have 14 area used, if it is packed correctly
      if (suitcase.areaPacked() != 14) {
        System.out.println("Test 2f: Suitcase is not filled as expected!");
        return false;
      }
    }

    return true;
  }

  /**
   * Tester method for the Packing.greedyPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingBaseTest() {
    { // greedyPackingBaseTest() 1
      // Test case: There are no items left to pack in the suitcase
      Suitcase emptySuitcase = new Suitcase(10, 10, new ArrayList<>());
      try {
        // Try to pack the suitcase using the greedy packing method
        emptySuitcase = Packing.greedyPacking(emptySuitcase);
      } catch (Exception e) {
        // If an exception is thrown, print an error message and return false
        System.out.println("Test 1a: Unexpected exception thrown!");
        return false;
      }
      // Check that no items were packed
      if (!emptySuitcase.getPackedItems().isEmpty()) {
        System.out.println("Test 1b: Items were packed when none should have been!");
        return false;
      }
    }

    { // greedyPackingBaseTest() 2
      // Test case: There are items left to pack, but none of them fit
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 11, 11)); // Item too big for the suitcase
      Suitcase fullSuitcase = new Suitcase(10, 10, items);
      try {
        // Try to pack the suitcase using the greedy packing method
        fullSuitcase = Packing.greedyPacking(fullSuitcase);
      } catch (Exception e) {
        // If an exception is thrown, print an error message and return false
        System.out.println("Test 2a: Unexpected exception thrown!");
        return false;
      }
      // Check that no items were packed and the unpacked items list matches the
      // original items list
      if (!fullSuitcase.getPackedItems().isEmpty()) {
        System.out.println("Test 2b: Items were packed when none should have been!");
        return false;
      }
      if (!fullSuitcase.getUnpackedItems().equals(items)) {
        System.out.println("Test 2c: Unpacked items list does not match expected!");
        return false;
      }
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tester method for the Packing.greedyPacking() method recursive cases. It should test at least
   * the following scenarios: - At least one item is packed out of order (an item with a higher
   * index is packed before an item with a lower index) - A scenario where the greedy packing method
   * packs more of the suitcase than the rushed packing (you can use the example given in the
   * writeup)
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingRecursiveTest() {
    { // greedyPackingRecursiveTest() 1
      // Test case 1: One or more items are packed in incorrect sequence
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 2, 4));
      items.add(new Item("B", 4, 1));
      items.add(new Item("C", 3, 4));
      items.add(new Item("D", 3, 3));
      Suitcase suitcase = new Suitcase(5, 5, items);
      try {
        // Try to pack the suitcase using the greedy packing method
        suitcase = Packing.greedyPacking(suitcase);
      } catch (Exception e) {
        // If an exception is thrown, print an error message and return false
        System.out.println("Test 1a: Unexpected exception thrown!");
        return false;
      }
      // Check that the items were packed in decreasing order of area
      ArrayList<Item> packedItems = suitcase.getPackedItems();
      for (int i = 1; i < packedItems.size(); i++) {
        int prevArea = packedItems.get(i - 1).width * packedItems.get(i - 1).height;
        int currArea = packedItems.get(i).width * packedItems.get(i).height;
        if (prevArea < currArea) {
          System.out.println("Test 1b: Items were packed in wrong order!");
          return false;
        }
      }
    }

    // greedyPackingRecursiveTest() 2
    // Test case 2: The greedy packing method should pack more of the suitcase than
    // the rushed packing
    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 5));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));
      Suitcase suitcase = new Suitcase(10, 10, items);
      try {
        // Try to pack the suitcase using both the greedy and rushed packing methods
        Suitcase greedyPackedSuitcase = Packing.greedyPacking(suitcase);
        int totalPackedAreaGreedy = 0;
        for (Item item : greedyPackedSuitcase.getPackedItems()) {
          totalPackedAreaGreedy += item.width * item.height;
        }
        Suitcase rushedPackedSuitcase = Packing.rushedPacking(suitcase);
        int totalPackedAreaRushed = 0;
        for (Item item : rushedPackedSuitcase.getPackedItems()) {
          totalPackedAreaRushed += item.width * item.height;
        }
        // Check that the greedy packing method packed more of the suitcase than the
        // rushed packing method
        if (totalPackedAreaGreedy <= totalPackedAreaRushed) {
          System.out.println("Test 2b: Greedy packing did not pack more than rushed packing!");
          return false;
        }
      } catch (Exception e) {
        // If an exception is thrown, print an error message and return false
        System.out.println("Test 2a: Unexpected exception thrown!");
        return false;
      }
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tester method for the Packing.optimalPacking() method. This tester should test the
   * optimalPacking() method by randomly generating at least TEN (10) different scenarios, and
   * randomly generating at least ONE-HUNDRED (100) different packing solutions for EACH of the
   * scenarios. Each scenario should have at least FIVE (5) random items, and the suitcases should
   * be of size at least 5x5. If any random solution is better than the optimal packing then it is
   * not actually optimal, so the method does not pass the test. You should use the Utilities method
   * to generate random lists of items, and to randomly pack the suitcases.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean optimalPackingRandomTest() {
    // Define the dimensions of the suitcase
    int suitcaseWidth = 5;
    int suitcaseHeight = 5;

    // Run 10 different scenarios
    for (int i = 0; i < 10; i++) {
      // Generate 5 random items for each scenario
      ArrayList<Item> items = Utilities.randomItems(5);
      // Create a new suitcase with the generated items
      Suitcase suitcase = new Suitcase(suitcaseWidth, suitcaseHeight, items);
      Suitcase optimalSuitcase;
      try {
        // Try to pack the suitcase optimally
        optimalSuitcase = Packing.optimalPacking(suitcase);
      } catch (Exception e) {
        // If an exception is thrown, print an error message and return false
        System.out.println("Test " + (i + 1) + "a: Unexpected exception thrown!");
        return false;
      }
      // Calculate the total area of the items packed in the optimal suitcase
      int totalAreaPackedOptimal = 0;
      for (Item item : optimalSuitcase.getPackedItems()) {
        totalAreaPackedOptimal += item.width * item.height;
      }
      // Generate 100 random packings for each scenario
      for (int j = 0; j < 100; j++) {
        // Create a new suitcase with the same items and pack it randomly
        Suitcase randomSuitcase = Utilities.randomlyPack(new Suitcase(suitcaseWidth, suitcaseHeight,
            new ArrayList<>(suitcase.getUnpackedItems())));
        // Calculate the total area of the items packed in the random suitcase
        int totalAreaPackedRandom = 0;
        for (Item item : randomSuitcase.getPackedItems()) {
          totalAreaPackedRandom += item.width * item.height;
        }
        // If the random packing is better than the optimal packing, print an error
        // message and return false
        if (totalAreaPackedRandom > totalAreaPackedOptimal) {
          System.out.println("Test " + (i + 1) + "." + (j + 1)
              + "b: Random packing was better than optimal packing!");
          return false;
        }
      }
    }
    // If all tests pass, return true
    return true;
  }

  public static void main(String[] args) {
    boolean allPass = true;
    String printFormat = "%-29s %s\n";

    boolean rushedBase = rushedPackingBaseTest();
    allPass &= rushedBase;
    System.out.printf(printFormat, "rushedPackingBaseTest():", rushedBase);

    boolean rushedRecur = rushedPackingRecursiveTest();
    allPass &= rushedRecur;
    System.out.printf(printFormat, "rushedPackingRecursiveTest():", rushedRecur);

    boolean greedyBase = greedyPackingBaseTest();
    allPass &= greedyBase;
    System.out.printf(printFormat, "greedyPackingBaseTest():", greedyBase);

    boolean greedyRecur = greedyPackingRecursiveTest();
    allPass &= greedyRecur;
    System.out.printf(printFormat, "greedyPackingRecursiveTest():", greedyRecur);

    boolean optimalRandom = optimalPackingRandomTest();
    allPass &= optimalRandom;
    System.out.printf(printFormat, "optimalPackingRandomTest():", optimalRandom);

    System.out.printf(printFormat, "All tests:", allPass);
  }
}
