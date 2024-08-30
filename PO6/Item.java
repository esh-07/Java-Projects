/**
 * You do NOT need to submit this file to Gradescope.
 * This class represents rectangular items.
 */
public class Item {
  /** The name of this item. */
  public final String name;

  /** The width of this item. */
  public final int width;

  /** The height of this item. */
  public final int height;

  /**
   * Creates a new Item with the given name, width, and height
   * @param name this Item's name
   * @param width this Item's width
   * @param height this Item's height
   */
  public Item(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }
  @Override
  public String toString() {
    return name + "(" + width + "x" + height + ")";
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;

    if (object instanceof Item item) {
      // Check that the name and sizes are identical
      return width == item.width && height == item.height && name.equals(item.name);
    } else {
      return false;
    }
  }
}
