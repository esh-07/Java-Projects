import java.util.ArrayList;

/**
 * You do NOT need to submit this file to Gradescope.
 * This class represents a suitcase consisting of a 2D grid in which
 * we wish to pack rectangular items.
 */
public class Suitcase {
  /** A 2D array indicating whether each grid position is filled or not. */
  private final boolean[][] occupied;

  /** The width of the grid. */
  private final int width;

  /** The height of the grid. */
  private final int height;

  /** The items that have already been packed in the grid. */
  private final ArrayList<Item> itemsAdded;

  /** The corresponding positions that items have been packed in the grid. */
  private final ArrayList<Position> positions;

  /** The items that have not yet been packed in the grid. */
  private final ArrayList<Item> itemsRemaining;

  /** The total area of items that have been packed in the grid. */
  private int areaPacked;

  /**
   * Creates a new empty Suitcase object with the given width, height, and items to pack.
   *
   * @param width width of the grid
   * @param height height of the grid
   * @param items items to pack in the grid
   * @throws IllegalArgumentException if the width or height are not positive numbers
   */
  public Suitcase(int width, int height, ArrayList<Item> items) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must be positive numbers");
    }

    this.occupied = new boolean[width][height];
    this.width = width;
    this.height = height;
    this.itemsAdded = new ArrayList<>();
    this.itemsRemaining = new ArrayList<>(items);
    this.positions = new ArrayList<>();
    this.areaPacked = 0;
  }

  /**
   * Returns the total area of items that have been packed in the grid.
   * @return the total area of items that have been packed in the grid
   */
  public int areaPacked() {
    return areaPacked;
  }

  /**
   * Returns the number of items that have not yet been packed.
   * @return the number of items that have not yet been packed
   */
  public int numItemsUnpacked() {
    return itemsRemaining.size();
  }

  /**
   * Returns the number of items that have been packed.
   * @return the number of items that have been packed
   */
  public int numItemsPacked() {
    return itemsAdded.size();
  }

  /**
   * Returns an ArrayList containing all the items not yet packed in this suitcase
   * @return an ArrayList containing all the items not yet packed in this suitcase
   */
  public ArrayList<Item> getUnpackedItems() {
    return new ArrayList<>(itemsRemaining);
  }

  /**
   * Returns an ArrayList containing all the items packed in this suitcase
   * @return an ArrayList containing all the items packed in this suitcase
   */
  public ArrayList<Item> getPackedItems() {
    return new ArrayList<>(itemsAdded);
  }

  /**
   * Returns whether the unpacked item can fit in this suitcase.
   * @param item the unpacked item
   * @return whether the unpacked item can fit in the suitcase
   * @throws IllegalArgumentException if the given item is not waiting to be
   *         packed in this suitcase
   */
  public boolean canPackItem(Item item) {
    int itemIdx = itemsRemaining.indexOf(item);
    if (itemIdx == -1) {
      throw new IllegalArgumentException("Given Item is not waiting to be packed");
    }

    return packPosition(item) != null;
  }

  /**
   * Returns whether an item can fit at given position.
   * @param item the unpacked item
   * @param p the grid position to try and fit the item into
   * @return whether the unpacked item can fit at given position
   */
  private boolean canPackItemAt(Item item, Position p) {
    // The position must be valid, and the item can't fall out of the grid
    if (p.x < 0 || p.x + item.width > this.width || p.y < 0 || p.y + item.height > this.height) {
      return false;
    }

    // None of the spaces the item takes up can be occupied
    for (int i = p.x; i < p.x + item.width; i++) {
      for (int j = p.y; j < p.y + item.height; j++) {
        if (occupied[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Returns the preferred position, if one exists, for the given item.
   * The preferred position of an item is the top-left-most position that it can fit into.
   * Positions are ordered lexicographically by x and then y position, so that for instance
   * (0,2) would be preferable to (2,0).
   * This corresponds to the strategy of starting from the top-left corner of the grid,
   * and sliding the item top-to-bottom, and after each column is completed moving one
   * position to the right, until an open position is found.
   * If there is no position on the grid into which the item fits, the method returns null.
   *
   * @param item the unpacked item
   * @return the preferred position for the unpacked item at the given index.
   */
  private Position packPosition(Item item) {
    // Left-to-right
    for (int i = 0; i < width; i++) {
      // Top-to-bottom
      for (int j = 0; j < height; j++) {
        Position p = new Position(i, j);
        // Return the first matching position
        if (canPackItemAt(item, p)) {
          return p;
        }
      }
    }
    // There were no matching positions, so return null
    return null;
  }

  /**
   * Creates a new Suitcase by packing the unpacked item at the given position.
   * If the item cannot be packed at the position, throws an IllegalArgumentException.
   *
   * @param item the unpacked item
   * @param position the position to pack the item at
   * @return a new Suitcase item with the given item now packed at its preferred position
   * @throws IllegalArgumentException if the item cannot be packed
   */
  private Suitcase packItemAt(Item item, Position position) throws IllegalArgumentException {
    // If it cannot be packed, throw an exception
    if (!itemsRemaining.contains(item) || !canPackItemAt(item, position)) {
      throw new IllegalArgumentException("Cannot add item " + item + " at position " + position);
    }

    // Make a copy of this object to modify
    Suitcase copy = new Suitcase(this.width, this.height, this.itemsRemaining);
    copy.itemsAdded.addAll(this.itemsAdded);
    copy.areaPacked = this.areaPacked;
    copy.positions.addAll(this.positions);
    for (int i = 0; i < width; i++) {
      System.arraycopy(this.occupied[i], 0, copy.occupied[i], 0, height);
    }

    // Set all spaces taken by the item to be occupied
    for (int i = position.x; i <= position.x + item.width - 1; i++) {
      for (int j = position.y; j <= position.y + item.height - 1; j++) {
        copy.occupied[i][j] = true;
      }
    }

    // Set the item as packed
    copy.itemsAdded.add(item);
    copy.itemsRemaining.remove(item);
    copy.positions.add(position);
    copy.areaPacked += item.width * item.height;

    return copy;
  }

  /**
   * Creates a new Suitcase by packing the unpacked item
   * at the given index at its preferred position.
   * If the item cannot be packed, throws an IllegalArgumentException.
   *
   * @param item the unpacked item
   * @return a new Suitcase item with the given item now packed at its preferred position
   * @throws IllegalArgumentException if the item cannot be packed
   */
  public Suitcase packItem(Item item) throws IllegalArgumentException {
    // Get the item's preferred position
    Position p = packPosition(item);

    // If it cannot be packed, throw an exception
    if (p == null) {
      throw new IllegalArgumentException("Cannot add item " + item);
    }

    // Call the helper method
    return packItemAt(item, p);
  }

  /**
   * Makes a deep copy of this Suitcase object.
   * @return a deep copy of this Suitcase object
   */
  private Suitcase deepCopy() {
    Suitcase copy = new Suitcase(width, height, itemsRemaining);
    for (int i = 0; i < width; i++) {
      System.arraycopy(occupied[i], 0, copy.occupied[i], 0, height);
    }
    copy.itemsAdded.addAll(itemsAdded);
    copy.positions.addAll(positions);
    copy.areaPacked = areaPacked;
    return copy;
  }

  /**
   * Creates a visualization of this Suitcase object.
   * Unfilled grid positions are represented by ░
   * Packed items are represented in different ways depending on their sizes:
   * - 1x1 Item:
   * █
   * - 1xn Item:
   * ┳    ┳
   * ┃ or ┻
   * ┻
   * - mx1 Item:
   * ┣━━┫ or ┣┫
   * - mxn Item:
   * ┏━━━━┓    ┏━┓
   * ┃████┃ or ┗━┛
   * ┃████┃
   * ┗━━━━┛
   *
   * @return a string representation of this Suitcase object
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    // First display what items have been packed
    sb.append("Items Added: [");
    for (int i = 0; i < itemsAdded.size(); i++) {
      sb.append(itemsAdded.get(i));
      sb.append("@");
      sb.append(positions.get(i));
      if (i != itemsAdded.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]\n");

    // Display all unpacked items
    sb.append("Items Remaining: [");
    for (int i = 0; i < itemsRemaining.size(); i++) {
      sb.append(itemsRemaining.get(i));
      if (i != itemsRemaining.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]\n");

    // Show the area currently packed
    sb.append("Area Packed: ");
    sb.append(areaPacked);
    sb.append("\n");

    // Top border of the grid
    sb.append("╔");
    sb.append("═".repeat(width));
    sb.append("╗");
    sb.append("\n");

    // Display the currently packed items nicely
    char[][] display = new char[height][width];

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        if (occupied[i][j]) {
          display[j][i] = '█';
        } else {
          display[j][i] = '░';
        }
      }
    }

    for (int i = 0; i < itemsAdded.size(); i++) {
      Item item = itemsAdded.get(i);
      Position position = positions.get(i);
      int maxY = position.y + item.height - 1;
      int maxX = position.x + item.width - 1;

      display[position.y][position.x] = '┏';
      display[position.y][maxX] = '┓';
      display[maxY][maxX] = '┛';
      display[maxY][position.x] = '┗';

      if (item.width == 1) {
        if (item.height == 1) {
          display[position.y][position.x] = '█';
        } else {
          display[position.y][position.x] = '┳';
          display[maxY][position.x] = '┻';
        }
      } else if (item.height == 1) {
        display[position.y][position.x] = '┣';
        display[position.y][maxX] = '┫';
      }

      for (int x = position.x + 1; x < maxX; x++) {
        display[position.y][x] = '━';
        display[maxY][x] = '━';
      }

      for (int y = position.y + 1; y < maxY; y++) {
        display[y][position.x] = '┃';
        display[y][maxX] = '┃';
      }
    }

    for (char[] row : display) {
      sb.append("║");
      sb.append(row);
      sb.append("║\n");
    }

    // Bottom border of the grid
    sb.append("╚");
    sb.append("═".repeat(width));
    sb.append("╝");

    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Suitcase suitcase) {
      // Check that all packed and unpacked items are identical
      return itemsAdded.equals(suitcase.itemsAdded)
          && positions.equals(suitcase.positions)
          && itemsRemaining.equals(suitcase.itemsRemaining);
    } else {
      return false;
    }
  }
}
