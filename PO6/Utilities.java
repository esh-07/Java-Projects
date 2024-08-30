import java.util.ArrayList;
import java.util.Random;

/**
 * You do NOT need to submit this file to Gradescope.
 * Utility class for random item list and packing order generation
 */
public class Utilities {
  /** Shared random object. Feel free to change the seed value. **/
  private static final Random RANDOM = new Random(2808771981L);

  /**
   * Generates a random list of Items
   * @param numItems Number of Items to generate
   * @return an ArrayList containing the given number of random Items
   */
  public static ArrayList<Item> randomItems(int numItems) {
    if (numItems < 0) {
      throw new IllegalArgumentException("numItems cannot be negative");
    }

    int maxWidth = 4;
    int maxHeight = 4;

    ArrayList<Item> items = new ArrayList<>();

    for (int i = 0; i < numItems; i++) {
      // Make the names alphabet characters A,B,...,Z
      String name = String.valueOf((char)('A' + i));
      // Randomly generate width and height
      int width = RANDOM.nextInt(1, maxWidth + 1);
      int height = RANDOM.nextInt(1, maxHeight + 1);
      items.add(new Item(name, width, height));
    }

    return items;
  }

  /**
   * Randomly packs the given suitcase with items until no space is available.
   * @param suitcase suitcase to pack
   * @return the suitcase after randomly packing items
   */
  public static Suitcase randomlyPack(Suitcase suitcase) {
    // Keep track of the current state of the suitcase
    Suitcase curSuitcase = suitcase;

    // Keep going until we can't pack anything...
    while (curSuitcase.numItemsUnpacked() > 0) {
      // Make a list of all the items that we can currently pack
      ArrayList<Item> canPack = new ArrayList<>();
      for (Item i : curSuitcase.getUnpackedItems()) {
        if (curSuitcase.canPackItem(i)) {
          canPack.add(i);
        }
      }

      // Nothing else fits, so we can return
      if (canPack.isEmpty()) {
        return curSuitcase;
      }

      // Pick a random item that fits to pack next
      Item toPack = canPack.get(RANDOM.nextInt(0, canPack.size()));
      // Pack the item and update the suitcase
      curSuitcase = curSuitcase.packItem(toPack);
    }

    // Everything has been packed, so we can return
    return curSuitcase;
  }
}
