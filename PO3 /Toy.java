
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Toy
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
import processing.core.PImage;
import java.io.File;

/**
 * The Toy class symbolizes a toy entity in the ToySaga application. Each toy possesses an image,
 * coordinates, rotation status, and a dragging state.
 */
public class Toy {
  public final PImage IMAGE; // The image that depicts the toy.
  private int x; // The x-coordinate of the toy's position.
  private int y; // The y-coordinate of the toy's position.
  private boolean isDragging; // Flag indicating if the toy is currently under drag operation.
  private int rotations; // The count of rotations performed on the toy.

  /**
   * Constructs a new toy with the given name and position.
   *
   * @param name the identifier of the toy
   * @param x    the x-coordinate of the toy's position
   * @param y    the y-coordinate of the toy's position
   */
  public Toy(String name, int x, int y) {
    this.x = x;
    this.y = y;
    this.isDragging = false; // By default, the toy is not under drag operation.
    this.rotations = 0; // By default, the toy has not been rotated.
    this.IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Constructs a new toy with the given name, positioning it at the center of the window.
   *
   * @param name the identifier of the toy
   */
  public Toy(String name) {
    // Use the screen center as the initial position.
    this(name, Utility.width() / 2, Utility.height() / 2);
  }

  /**
   * Retrieves the x-coordinate of the toy.
   *
   * @return the x-coordinate of the toy's position
   */
  public int getX() {
    return x; // Return the current x-coordinate of the toy.
  }

  /**
   * Retrieves the y-coordinate of the toy.
   *
   * @return the y-coordinate of the toy's position
   */
  public int getY() {
    return y; // Return the current y-coordinate of the toy.
  }

  /**
   * Updates the x-coordinate of the toy.
   *
   * @param x the new x-coordinate of the toy's position
   */
  public void setX(int x) {
    this.x = x; // Set the new x-coordinate of the toy.
  }

  /**
   * Updates the y-coordinate of the toy.
   *
   * @param y the new y-coordinate of the toy's position
   */
  public void setY(int y) {
    this.y = y; // Set the new y-coordinate of the toy.
  }

  /**
   * Retrieves the number of rotations the toy has undergone.
   *
   * @return the count of rotations
   */
  public int getRotationsCount() {
    return rotations; // Return the count of rotations of the toy.
  }

  /**
   * Verifies if the toy is currently under drag operation.
   *
   * @return true if the toy is under drag operation, false otherwise
   */
  public boolean isDragging() {
    return isDragging; // Return the current dragging state of the toy.
  }

  /**
   * Initiates the dragging operation on the toy.
   */
  public void startDragging() {
    isDragging = true; // Set the toy's state to dragging.
  }

  /**
   * Terminates the dragging operation on the toy.
   */
  public void stopDragging() {
    isDragging = false; // Set the toy's state to not dragging.
  }

  /**
   * Rotates the toy 90 degrees in the clockwise direction.
   */
  public void rotate() {
    rotations = rotations + 1; // Increase the rotation count.
  }

  /**
   * Translates the toy by the specified amount in the x and y directions. If the toy moves beyond
   * the window boundaries, it adjusts its position to remain within the window.
   *
   * @param dx the change in the x-coordinate
   * @param dy the change in the y-coordinate
   */
  public void move(int dx, int dy) {
    // Adjust toy's position
    x += dx;
    y += dy;

    // Ensure the toy stays within the window boundaries.
    if (x < 0) {
      x = 0;
    }
    if (y < 0) {
      y = 0;
    }
    // If the toy is outside the window bound, adjust accordingly
    if (x > Utility.width()) {
      x = Utility.width();
    }
    if (y > Utility.height()) {
      y = Utility.height();
    }
  }

  /**
   * Renders the toy on the screen at its current position, considering any rotations.
   */
  public void draw() {
    // If the toy is under drag operation, update its position based on the mouse
    // movement.
    if (isDragging) {
      move(Utility.mouseX() - Utility.pmouseX(), Utility.mouseY() - Utility.pmouseY());
    }

    drawToyImage(); // Render the toy image with rotations
  }

  /**
   * Determines if a specified point (x, y) lies within the toy's boundaries.
   *
   * @param x the x-coordinate of the point
   * @param y the y-coordinate of the point
   * @return true if the point is inside the toy's boundaries, false otherwise
   */
  public boolean isOver(int x, int y) {
    int toyWidth, toyHeight;
    // If the toy has been rotated an odd number of times (90 or 270 degrees),
    // the width and height are swapped.
    if (rotations % 2 != 0) { // If rotations are odd - swap dimensions
      toyWidth = IMAGE.height;
      toyHeight = IMAGE.width;
    } else { // If rotations are even - keep original dimensions
      toyWidth = IMAGE.width;
      toyHeight = IMAGE.height;
    }

    // Calculate the boundaries of the toy based on its current position and
    // dimensions.
    // The left boundary - by subtracting half the width from the toy's
    // x-coordinate.
    int leftX = this.x - toyWidth / 2;
    // The right boundary - adding half the width to the toy's x-coordinate
    int rightX = this.x + toyWidth / 2;
    // The top boundary - subtracting half the height from the toy's y-coordinate.
    int topY = this.y - toyHeight / 2;
    // The bottom boundary - adding half the height from the toy's y-coordinate.
    int bottomY = this.y + toyHeight / 2;

    // Check if the given point (x, y) falls within the calculated boundaries of the
    // toy.
    // If the point's x-coordinate is between the left and right boundaries and
    // the point's y-coordinate is between the top and bottom boundaries,
    // the method returns true, indicating the point is over the toy.
    return x >= leftX && x <= rightX && y >= topY && y <= bottomY;
  }

  /**
   * Determines if the toy overlaps with a given furniture object.
   *
   * @param other the furniture object to check for overlap
   * @return true if any part of the toy image overlaps with the furniture image, false otherwise
   */
  public boolean isOver(Furniture other) {
    int toyWidth, toyHeight;
    // If the toy has been rotated an odd number of times (90 or 270 degrees),
    // the width and height are swapped.
    if (rotations % 2 != 0) {
      toyWidth = IMAGE.height;
      toyHeight = IMAGE.width;
    } else {
      toyWidth = IMAGE.width;
      toyHeight = IMAGE.height;
    }

    int toyLeftX = this.x - toyWidth / 2;
    int toyRightX = this.x + toyWidth / 2;
    int toyTopY = this.y - toyHeight / 2;
    int toyBottomY = this.y + toyHeight / 2;

    // Calculate the boundaries of the furniture object passed as a parameter.
    // Similar to the toy, the boundaries are determined based on the
    // furniture's position and dimensions.
    int furnitureLeftX = other.getX() - other.IMAGE.width / 2;
    int furnitureRightX = other.getX() + other.IMAGE.width / 2;
    int furnitureTopY = other.getY() - other.IMAGE.height / 2;
    int furnitureBottomY = other.getY() + other.IMAGE.height / 2;

    // Check if any part of the toy overlaps with any part of the furniture.
    // This is true if the right boundary of the toy is to the right of the left
    // boundary of the
    // furniture, the left boundary of the toy is to the left of the right boundary
    // of the
    // furniture,the bottom boundary of the toy is below the top boundary of the
    // furniture, and
    // the top boundary of the toy is above the bottom boundary of the furniture. If
    // all
    // these conditions are met, it means there is an overlap between the toy and
    // the furniture.
    return toyRightX >= furnitureLeftX && toyLeftX <= furnitureRightX && toyBottomY >= furnitureTopY
        && toyTopY <= furnitureBottomY;
  }

  /**
   * A helper method to draw an image on the screen, taking into account any rotations. The details
   * of this method are provided in the write-up.
   */
  private void drawToyImage() {
    Utility.pushMatrix();
    Utility.translate(x, y);
    Utility.rotate(this.rotations * Utility.PI / 2);
    Utility.image(IMAGE, 0.0f, 0.0f);
    Utility.popMatrix();
  }

}
