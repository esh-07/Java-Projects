//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Packing
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
 * Class used for packing a 2D suitcase with items using various strategies.
 */
public class Packing {
  /**
   * Tries to pack each item in the items list in-order. If an item can fit, it must be packed.
   * Otherwise, it should be skipped. Must be a recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a strategy in which the items were attempted to
   *         be packed in-order.
   */
  public static Suitcase rushedPacking(Suitcase suitcase) {
    // Invoke the helper method with the unpacked items and the suitcase
    suitcase = rushedPackingHelper(suitcase.getUnpackedItems(), suitcase);
    return suitcase;
  }

  private static Suitcase rushedPackingHelper(ArrayList<Item> items, Suitcase suitcase) {
    // Base case: If the items list is empty, end recursion and return the suitcase
    if (items.isEmpty()) {
      return suitcase;
    }

    // If the first item can be packed into the suitcase, do so. If not, leave the
    // suitcase as is.
    suitcase = suitcase.canPackItem(items.get(0)) ? suitcase.packItem(items.get(0)) : suitcase;

    // Remove the first item from the list, regardless of whether it was packed or
    // not. Skip if it cannot fit.
    items.remove(0);

    // Recursively call this method with the updated list and suitcase
    suitcase = rushedPackingHelper(items, suitcase);

    // Return the suitcase, now potentially containing more items
    return suitcase;
  }

  /**
   * Packs items by greedily packing the largest item which can currently be packed. Must be a
   * recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a greedy strategy in which at each point the
   *         largest item that can fit is packed.
   */
  public static Suitcase greedyPacking(Suitcase suitcase) {
    // Base case: if there are no more items to pack, return the suitcase as is
    if (suitcase.getUnpackedItems().isEmpty()) {
      return suitcase;
    }

    // Create a copy of the unpacked items list
    ArrayList<Item> items = new ArrayList<>(suitcase.getUnpackedItems());

    // Sort items manually using bubble sort
    for (int i = 0; i < items.size() - 1; i++) {
      for (int j = 0; j < items.size() - i - 1; j++) {
        // Get the current item and the next item
        Item item1 = items.get(j);
        Item item2 = items.get(j + 1);

        // Calculate the area of the current item and the next item
        int area1 = item1.width * item1.height;
        int area2 = item2.width * item2.height;

        // Swap items if the first item is smaller than the second item
        if (area1 < area2) {
          items.set(j, item2);
          items.set(j + 1, item1);
        }
      }
    }

    // Try to pack each item, starting with the largest
    for (Item item : items) {
      try {
        // Try to pack the item
        Suitcase newSuitcase = suitcase.packItem(item);

        // Recursively try to pack the remaining items
        return greedyPacking(newSuitcase);
      } catch (IllegalArgumentException e) {
        // The item couldn't be packed. Just continue with the next item.
        continue;
      }
    }

    // If no more items can be packed, return the suitcase as is
    return suitcase;
  }

  /**
   * Finds the optimal packing of items by trying all packing orders. Must be a recursive method.
   *
   * @param suitcase current Suitcase
   * @return a Suitcase representing the optimal outcome.
   */
  public static Suitcase optimalPacking(Suitcase suitcase) {
    // Base case: if there are no more items to pack, return the suitcase as is
    if (suitcase.getUnpackedItems().isEmpty()) {
      return suitcase;
    }

    Suitcase bestSuitcase = suitcase;
    int maxArea = 0;

    // Calculate the total area of the packed items
    for (Item i : suitcase.getPackedItems()) {
      maxArea += i.width * i.height;
    }

    // Try to pack each unpacked item
    for (Item item : new ArrayList<>(suitcase.getUnpackedItems())) {
      try {
        // Try to pack the item
        Suitcase newSuitcase = suitcase.packItem(item);

        // Recursively try to pack the remaining items
        Suitcase packedSuitcase = optimalPacking(newSuitcase);

        // Calculate the total area of the packed items
        int newArea = 0;
        for (Item i : packedSuitcase.getPackedItems()) {
          newArea += i.width * i.height;
        }

        // If the new packing is better, update the best suitcase and the max area
        if (newArea > maxArea) {
          bestSuitcase = packedSuitcase;
          maxArea = newArea;
        }
      } catch (IllegalArgumentException e) {
        // The item couldn't be packed. Just continue with the next item.
      }
    }

    return bestSuitcase;
  }
}
