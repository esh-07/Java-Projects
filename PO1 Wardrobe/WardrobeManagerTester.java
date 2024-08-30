//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: WardrobeManagerTester
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
// Online Sources: https://www.w3schools.com/java/default.asp Used W3schools to
// learn about Java arrays in depth.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class tests the functionality of the WardrobeManager class and uses various test methods to
 * test the methods in WardrobeManager.java.
 */
public class WardrobeManagerTester {

  //// CONTAINS

  /**
   * Test method for verifying whether an item is already in the wardrobe when the wardrobe is
   * empty.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContainsEmpty() {
    // (1) Initialize an empty wardrobe with a capacity of 10 items
    String[][] wardrobe = new String[10][];
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);

    // (2) Set the current size of the wardrobe to 0, indicating that it's empty
    int size = 0;

    // (3) Set the expected return value to false, because the wardrobe is empty
    boolean expected = false;

    // (4) Call the 'containsClothing' method with a clothing item ("black t-shirt" from "Hanes")
    // The method should return false because the wardrobe is empty
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (5) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (6) another test method call, this time with a different clothing item ("blue jeans" from
    // "Levi")
    // The method should still return false because the wardrobe is empty
    actual = WardrobeManager.containsClothing("blue jeans", "Levi", wardrobe, size);
    if (expected != actual)
      return false;

    // (7) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (8) if all of those checks pass, now we can say we passed the test
    return true;
  }

  /**
   * PROVIDED: example test method for verifying whether an item is already in the wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT
    // MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) another test method call, this time with case difference (that should
    // still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual)
      return false;

    // (4) since this method should not modify the array, let's check it against our
    // copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (5) if all of those checks pass, now we can say we passed the test
    return true;
  }

  /**
   * This method tests the 'containsClothing' method of the 'WardrobeManager' class. It checks if
   * the method correctly identifies that a clothing item does not exist in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'containsClothing' method correctly identifies that
   *         the clothing item does not exist in the wardrobe), false otherwise
   */
  public static boolean testContainsFalse() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("green crop top", "H&M", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) another test method call, this time with a clothing item that does not exist in the
    // wardrobe
    actual = WardrobeManager.containsClothing("red dress", "Zara", wardrobe, size);
    if (expected != actual)
      return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (5) if all of those checks pass, now we can say we passed the test
    return true;
  }

  //// ADD

  /**
   * PROVIDED: example test method for adding a new clothing item to an EMPTY oversize array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual)
      return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null)
      return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M")
        || !empty[0][2].equals("never"))
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 1; i < empty.length; i++) {
      if (empty[i] != null)
        return false;
    }

    // (5) if all of those checks pass, now we can say that we passed the test
    return true;
  }

  /**
   * This method tests the 'addClothing' method of the 'WardrobeManager' class when adding a
   * clothing item to a non-empty wardrobe. It checks if the method correctly adds the clothing item
   * and increases the size of the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'addClothing' method correctly adds the clothing
   *         item and increases the size of the wardrobe), false otherwise
   */
  public static boolean testAddNonEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, null, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 1;
    int newSize = WardrobeManager.addClothing("dark blue jeans", "Levi", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (newSize != 2 || !"dark blue jeans".equalsIgnoreCase(wardrobe[1][0])
        || !"Levi".equalsIgnoreCase(wardrobe[1][1]))
      return false;

    // (3) since this method should modify the array, let's check it against our copy:
    if (Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, now we can say we passed the test
    return true;
  }

  /**
   * This method tests the 'addClothing' method of the 'WardrobeManager' class when adding a
   * duplicate clothing item to the wardrobe. It checks if the method correctly handles the case
   * where the clothing item is already in the wardrobe and does not increase the size of the
   * wardrobe.
   * 
   * @return true if the test passes (i.e., the 'addClothing' method correctly handles the case
   *         where the clothing item is already in the wardrobe and does not increase the size of
   *         the wardrobe), false otherwise
   */
  public static boolean testAddDuplicate() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, null, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 1;
    int newSize = WardrobeManager.addClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (newSize != size)
      return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, now we can say we passed the test
    return true;
  }

  /**
   * This method tests the 'addClothing' method of the 'WardrobeManager' class when adding a
   * clothing item to a full wardrobe. It checks if the method correctly handles the case where the
   * wardrobe is full and does not increase the size of the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'addClothing' method correctly handles the case
   *         where the wardrobe is full and does not increase the size of the wardrobe), false
   *         otherwise
   */
  public static boolean testAddToFull() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe =
        {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"}};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    int newSize = WardrobeManager.addClothing("green crop top", "H&M", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (newSize != size)
      return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, now we can say we passed the test
    return true;
  }


  //// INDEX OF

  /**
   * This method tests the 'indexOfClothing' method of the 'WardrobeManager' class when the wardrobe
   * is empty. It checks if the method correctly returns -1, indicating that the clothing item is
   * not in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'indexOfClothing' method correctly returns -1),
   *         false otherwise
   */
  public static boolean testIndexOfEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = new String[5][3];
    int size = 0;
    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    return expected == actual;
  }

  /**
   * This method tests the 'indexOfClothing' method of the 'WardrobeManager' class when the clothing
   * item is the first or last item in the wardrobe. It checks if the method correctly returns the
   * index of the clothing item in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'indexOfClothing' method correctly returns the index
   *         of the clothing item), false otherwise
   */
  public static boolean testIndexOfFirstLast() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    int expectedFirst = 0;
    int expectedLast = 1;
    int actualFirst = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);
    int actualLast = WardrobeManager.indexOfClothing("dark blue jeans", "Levi", wardrobe, size);

    // (2) verify that the expected return values and the actual return values match
    return expectedFirst == actualFirst && expectedLast == actualLast;
  }

  /**
   * This method tests the 'indexOfClothing' method of the 'WardrobeManager' class when the clothing
   * item is in the middle of the wardrobe. It checks if the method correctly returns the index of
   * the clothing item in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'indexOfClothing' method correctly returns the index
   *         of the clothing item), false otherwise
   */
  public static boolean testIndexOfMiddle() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"white shirt", "Adidas", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null};
    int size = 3;
    int expected = 1;
    int actual = WardrobeManager.indexOfClothing("white shirt", "Adidas", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    return expected == actual;
  }

  /**
   * This method tests the 'indexOfClothing' method of the 'WardrobeManager' class when the clothing
   * item is not in the wardrobe. It checks if the method correctly returns -1, indicating that the
   * clothing item is not in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'indexOfClothing' method correctly returns -1),
   *         false otherwise
   */
  public static boolean testIndexOfNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    int expected = -1;
    int actual = WardrobeManager.indexOfClothing("white t-shirt", "Hanes", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    return expected == actual;
  }

  //// WEAR

  /**
   * This method tests the 'wearClothing' method of the 'WardrobeManager' class when the clothing
   * item is in the wardrobe. It checks if the method correctly updates the last worn date of the
   * clothing item in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'wearClothing' method correctly updates the last
   *         worn date of the clothing item), false otherwise
   */
  public static boolean testWearClothingTrue() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'wearClothing' method with a clothing item ("black t-shirt" from "Hanes") that
    // exists in the wardrobe
    // The method should return true and update the last worn date of the clothing item to
    // "2022-01-01"
    boolean result =
        WardrobeManager.wearClothing("black t-shirt", "Hanes", "2022-01-01", wardrobe, size);

    // (2) verify that the method returned true and the last worn date of the clothing item was
    // updated correctly
    return result && "2022-01-01".equals(wardrobe[0][2]);
  }

  /**
   * This method tests the 'wearClothing' method of the 'WardrobeManager' class when the clothing
   * item is not in the wardrobe. It checks if the method correctly handles the case where the
   * clothing item is not in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'wearClothing' method correctly handles the case
   *         where the clothing item is not in the wardrobe), false otherwise
   */
  public static boolean testWearClothingNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'wearClothing' method with a clothing item ("white t-shirt" from "Hanes") that does
    // not exist in the wardrobe
    // The method should return false because the clothing item is not in the wardrobe
    boolean result =
        WardrobeManager.wearClothing("white t-shirt", "Hanes", "2022-01-01", wardrobe, size);

    // (2) verify that the method returned false
    return !result;
  }

  //// BRAND COUNT

  /**
   * This method tests the 'getBrandCount' method of the 'WardrobeManager' class when all clothing
   * items in the wardrobe are from the same brand. It checks if the method correctly returns the
   * total count of clothing items from the specified brand in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getBrandCount' method correctly returns the total
   *         count of clothing items from the specified brand), false otherwise
   */
  public static boolean testBrandCountAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "Hanes", "2020-03-16"}, {"dark blue jeans", "Hanes", "2024-01-25"},
        {"green cabled sweater", "Hanes", "never"}, null};
    int size = 4;
    int expected = 4;
    // Call the 'getBrandCount' method with a brand ("Hanes") that all clothing items in the
    // wardrobe are from
    // The method should return the total count of clothing items from the specified brand
    int actual = WardrobeManager.getBrandCount("Hanes", wardrobe, size);

    // (2) verify that the method returned the correct count
    return expected == actual;
  }

  /**
   * This method tests the 'getBrandCount' method of the 'WardrobeManager' class when some clothing
   * items in the wardrobe are from the specified brand. It checks if the method correctly returns
   * the count of clothing items from the specified brand in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getBrandCount' method correctly returns the count
   *         of clothing items from the specified brand), false otherwise
   */
  public static boolean testBrandCountSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "Hanes", "never"}, null};
    int size = 4;
    int expected = 2;
    // Call the 'getBrandCount' method with a brand ("Hanes") that some clothing items in the
    // wardrobe are from
    // The method should return the count of clothing items from the specified brand
    int actual = WardrobeManager.getBrandCount("Hanes", wardrobe, size);

    // (2) verify that the method returned the correct count
    return expected == actual;
  }

  /**
   * This method tests the 'getBrandCount' method of the 'WardrobeManager' class when no clothing
   * items in the wardrobe are from the specified brand. It checks if the method correctly returns
   * 0, indicating that no clothing items from the specified brand are in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getBrandCount' method correctly returns 0), false
   *         otherwise
   */
  public static boolean testBrandCountNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "Hanes", "never"}, null};
    int size = 4;
    int expected = 0;
    // Call the 'getBrandCount' method with a brand ("brandThatDoesntExist") that no clothing items
    // in the wardrobe are from
    // The method should return 0 because no clothing items from the specified brand are in the
    // wardrobe
    int actual = WardrobeManager.getBrandCount("brandThatDoesntExist", wardrobe, size);

    // (2) verify that the method returned 0
    return expected == actual;
  }

  //// COUNT UNWORN

  /**
   * This method tests the 'getNumUnwornClothes' method of the 'WardrobeManager' class when all
   * clothing items in the wardrobe have never been worn. It checks if the method correctly returns
   * the total count of unworn clothing items in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getNumUnwornClothes' method correctly returns the
   *         total count of unworn clothing items), false otherwise
   */
  public static boolean testUnwornCountAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe =
        {{"black t-shirt", "Hanes", "never"}, {"grey UW hoodie", "gildan", "never"},
            {"dark blue jeans", "Levi", "never"}, {"green cabled sweater", "Hanes", "never"}, null};
    int size = 4;
    int expected = 4;
    // Call the 'getNumUnwornClothes' method when all clothing items in the wardrobe have never been
    // worn
    // The method should return the total count of unworn clothing items
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);

    // (2) verify that the method returned the correct count
    return expected == actual;
  }

  /**
   * This method tests the 'getNumUnwornClothes' method of the 'WardrobeManager' class when some
   * clothing items in the wardrobe have been worn and some have not. It checks if the method
   * correctly returns the count of unworn clothing items in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getNumUnwornClothes' method correctly returns the
   *         count of unworn clothing items), false otherwise
   */
  public static boolean testUnwornCountSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "never"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "Hanes", "never"}, null};
    int size = 4;
    int expected = 2;
    // Call the 'getNumUnwornClothes' method when some clothing items in the wardrobe have been worn
    // and some have not
    // The method should return the count of unworn clothing items
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);

    // (2) verify that the method returned the correct count
    return expected == actual;
  }

  /**
   * This method tests the 'getNumUnwornClothes' method of the 'WardrobeManager' class when all
   * clothing items in the wardrobe have been worn. It checks if the method correctly returns 0,
   * indicating that there are no unworn clothing items in the wardrobe.
   * 
   * @return true if the test passes (i.e., the 'getNumUnwornClothes' method correctly returns 0),
   *         false otherwise
   */
  public static boolean testUnwornCountNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "Hanes", "2023-12-19"}, null};
    int size = 4;
    int expected = 0;
    // Call the 'getNumUnwornClothes' method when all clothing items in the wardrobe have been worn
    // The method should return 0 because there are no unworn clothing items in the wardrobe
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);

    // (2) verify that the method returned 0
    return expected == actual;
  }

  //// MOST RECENTLY WORN

  /**
   * PROVIDED: example test method for verifying that the most recently worn item is found
   * correctly. Note that this tester is not comprehensive; if you wish to verify additional
   * behavior you are welcome to add additional tester methods (please specify such methods to be
   * PRIVATE).
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT
    // MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since this method should not modify the array, let's check it against our
    // copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  //// REMOVE BY INDEX

  /**
   * This method tests the 'removeClothingAtIndex' method of the 'WardrobeManager' class when the
   * last item in the wardrobe is removed. It checks if the method correctly reduces the size of the
   * wardrobe and the last item is null.
   * 
   * @return true if the test passes (i.e., the 'removeClothingAtIndex' method correctly removes the
   *         last item and reduces the size of the wardrobe), false otherwise
   */
  public static boolean testRemoveLastItem() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'removeClothingAtIndex' method with the index of the last item in the wardrobe
    // The method should remove the last item and reduce the size of the wardrobe
    int newSize = WardrobeManager.removeClothingAtIndex(size - 1, wardrobe, size);

    // (2) verify that the method correctly removed the last item and reduced the size of the
    // wardrobe
    return newSize == size - 1 && wardrobe[newSize] == null;
  }

  /**
   * This method tests the 'removeClothingAtIndex' method of the 'WardrobeManager' class when the
   * first item in the wardrobe is removed. It checks if the method correctly reduces the size of
   * the wardrobe and the first item is now the second item from the original wardrobe.
   * 
   * @return true if the test passes (i.e., the 'removeClothingAtIndex' method correctly removes the
   *         first item and reduces the size of the wardrobe), false otherwise
   */
  public static boolean testRemoveFirstItem() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'removeClothingAtIndex' method with the index of the first item in the wardrobe
    // The method should remove the first item and reduce the size of the wardrobe
    int newSize = WardrobeManager.removeClothingAtIndex(0, wardrobe, size);

    // (2) verify that the method correctly removed the first item and reduced the size of the
    // wardrobe
    // Also verify that the first item is now the second item from the original wardrobe
    return newSize == size - 1 && "dark blue jeans".equalsIgnoreCase(wardrobe[0][0])
        && "Levi".equalsIgnoreCase(wardrobe[0][1]);
  }

  /**
   * This method tests the 'removeClothingAtIndex' method of the 'WardrobeManager' class when an
   * invalid index is provided. It checks if the method correctly keeps the size of the wardrobe
   * unchanged and the last item is not null.
   * 
   * @return true if the test passes (i.e., the 'removeClothingAtIndex' method correctly handles the
   *         invalid index), false otherwise
   */
  public static boolean testRemoveBadIndex() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'removeClothingAtIndex' method with an invalid index
    // The method should keep the size of the wardrobe unchanged and the last item should not be
    // null
    int newSize = WardrobeManager.removeClothingAtIndex(size + 1, wardrobe, size);

    // (2) verify that the method correctly handled the invalid index
    return newSize == size && wardrobe[newSize - 1] != null;
  }

  //// REMOVE ALL UNWORN

  /**
   * This method tests the 'removeAllUnworn' method of the 'WardrobeManager' class when all clothing
   * items in the wardrobe have been worn. It checks if the method correctly keeps the size of the
   * wardrobe unchanged.
   * 
   * @return true if the test passes (i.e., the 'removeAllUnworn' method correctly handles the case
   *         where all clothing items have been worn), false otherwise
   */
  public static boolean testRemoveUnwornNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"dark blue jeans", "Levi", "2024-01-25"}, null, null, null};
    int size = 2;
    // Call the 'removeAllUnworn' method with a wardrobe where all clothing items have been worn
    // The method should keep the size of the wardrobe unchanged
    int newSize = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the method correctly handled the case where all clothing items have been worn
    return newSize == size;
  }

  /**
   * This method tests the 'removeAllUnworn' method of the 'WardrobeManager' class when some
   * clothing items in the wardrobe have never been worn. It checks if the method correctly reduces
   * the size of the wardrobe and the remaining items are those that have been worn.
   * 
   * @return true if the test passes (i.e., the 'removeAllUnworn' method correctly removes the
   *         unworn clothing items and reduces the size of the wardrobe), false otherwise
   */
  public static boolean testRemoveUnwornSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "2024-01-25"}, null, null, null};
    int size = 2;
    // Call the 'removeAllUnworn' method with a wardrobe where some clothing items have never been
    // worn
    // The method should reduce the size of the wardrobe and the remaining items should be those
    // that have been worn
    int newSize = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the method correctly removed the unworn clothing items and reduced the size
    // of the wardrobe
    // Also verify that the remaining items are those that have been worn
    return newSize == 1 && "dark blue jeans".equalsIgnoreCase(wardrobe[0][0])
        && "Levi".equalsIgnoreCase(wardrobe[0][1]);
  }

  /**
   * This method tests the 'removeAllUnworn' method of the 'WardrobeManager' class when all clothing
   * items in the wardrobe have never been worn. It checks if the method correctly reduces the size
   * of the wardrobe to 0.
   * 
   * @return true if the test passes (i.e., the 'removeAllUnworn' method correctly removes all
   *         clothing items and reduces the size of the wardrobe to 0), false otherwise
   */
  public static boolean testRemoveUnwornAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    int size = 2;
    // Call the 'removeAllUnworn' method with a wardrobe where all clothing items have never been
    // worn
    // The method should reduce the size of the wardrobe to 0
    int newSize = WardrobeManager.removeAllUnworn(wardrobe, size);

    // (2) verify that the method correctly removed all clothing items and reduced the size of the
    // wardrobe to 0
    return newSize == 0;
  }

  /**
   * PROVIDED: calls all tester methods and displays the results of the tests.
   * 
   * All tests are called in the order they were provided in this file. The output of this method
   * will NOT be graded so you may modify it however you wish.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("CONTAINS:\n  " + (testContainsEmpty() ? "pass" : "FAIL") + ", "
        + (testContainsTrue() ? "pass" : "FAIL") + ", " + (testContainsFalse() ? "pass" : "FAIL"));
    System.out.println("ADD:\n  " + (testAddToEmpty() ? "pass" : "FAIL") + ", "
        + (testAddNonEmpty() ? "pass" : "FAIL") + ", " + (testAddDuplicate() ? "pass" : "FAIL")
        + ", " + (testAddToFull() ? "pass" : "FAIL"));
    System.out.println("INDEX OF:\n  " + (testIndexOfEmpty() ? "pass" : "FAIL") + ", "
        + (testIndexOfFirstLast() ? "pass" : "FAIL") + ", "
        + (testIndexOfMiddle() ? "pass" : "FAIL") + ", "
        + (testIndexOfNoMatch() ? "pass" : "FAIL"));
    System.out.println("WEAR:\n  " + (testWearClothingTrue() ? "pass" : "FAIL") + ", "
        + (testWearClothingNoMatch() ? "pass" : "FAIL"));
    System.out.println("BRAND COUNT:\n  " + (testBrandCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("UNWORN COUNT:\n  " + (testUnwornCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  " + (testMostRecentlyWorn() ? "pass" : "FAIL"));
    System.out.println("REMOVE BY INDEX:\n  " + (testRemoveLastItem() ? "pass" : "FAIL") + ", "
        + (testRemoveFirstItem() ? "pass" : "FAIL") + ", "
        + (testRemoveBadIndex() ? "pass" : "FAIL"));
    System.out.println("REMOVE UNWORN:\n  " + (testRemoveUnwornNoMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornSomeMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornAllMatch() ? "pass" : "FAIL"));
  }

}


