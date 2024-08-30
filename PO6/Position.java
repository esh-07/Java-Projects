/**
 * You do NOT need to submit this file to Gradescope.
 * This class represents a position on a 2D grid.
 */
public class Position {
  /** The x coordinate. */
  public final int x;

  /** The y coordinate. */
  public final int y;

  /**
   * Creates a new Position object at the given coordinates
   * @param x x/horizontal coordinate
   * @param y y/vertical coordinate
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Position p) {
      // Check that the x and y coordinates are identical
      return x == p.x && y == p.y;
    } else {
      return false;
    }
  }
}
