//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Toy
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
 * Represents a Toy in the ToySaga game. The Toy can be dragged around with the mouse.
 */
public class Toy extends GraphicObject implements MouseListener, Movable {
  private boolean isDragging; // Whether the Toy is currently being dragged

  /**
   * Constructs a new Toy object with the specified image file.
   *
   * @param filename the name of the image file
   */
  public Toy(String filename) {
    super(filename);
  }

  /**
   * Constructs a new Toy object with the specified image file and position.
   *
   * @param filename the name of the image file
   * @param x        the x-coordinate of the new Toy object
   * @param y        the y-coordinate of the new Toy object
   */
  public Toy(String filename, int x, int y) {
    super(filename, x, y);
  }

  /**
   * Draws this Toy object. The Toy object is moved before it is drawn.
   */
  public void draw() {
    move(); // Move the Toy object
    super.draw(); // Draw the Toy object
  }

  /**
   * Returns whether this Toy object is currently being dragged.
   *
   * @return true if this Toy object is currently being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging this Toy object.
   */
  public void startDragging() {
    isDragging = true;
  }

  /**
   * Stops dragging this Toy object.
   */
  public void stopDragging() {
    isDragging = false;
  }

  /**
   * Moves this Toy object by the specified amount. The Toy object is not moved outside the
   * boundaries of the ToySaga game.
   *
   * @param dx the amount to move in the x direction
   * @param dy the amount to move in the y direction
   */
  protected void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
    if (this.x < 0) {
      this.x = 0;
    } else if (this.x > this.toySaga.width) {
      this.x = this.toySaga.width;
    } else if (this.y < 0) {
      this.y = 0;
    } else if (this.y > this.toySaga.height) {
      this.y = this.toySaga.height;
    }
  }

  /**
   * Moves this Toy object. If the Toy object is currently being dragged, it is moved by the
   * difference between the current and previous mouse positions.
   */
  @Override
  public void move() {
    if (isDragging) {
      move(toySaga.mouseX - toySaga.pmouseX, toySaga.mouseY - toySaga.pmouseY);
    }
  }

  /**
   * Returns whether the specified point is over this Toy object.
   *
   * @param x the x-coordinate of the point
   * @param y the y-coordinate of the point
   * @return true if the specified point is over this Toy object, false otherwise
   */
  public boolean isOver(int x, int y) {
    return (x >= this.x - image.width / 2 && x <= this.x + image.width / 2
        && y >= this.y - image.height / 2 && y <= this.y + image.height / 2);
  }

  /**
   * Handles a click event. If no other Toy object is currently being dragged and the mouse is over
   * this Toy object, dragging of this Toy object is started.
   */
  @Override
  public void onClick() {
    if (toySaga.noToyIsDragging() && isOver(toySaga.mouseX, toySaga.mouseY)) {
      startDragging();
    }
  }

  /**
   * Handles a release event. Dragging of this Toy object is stopped.
   */
  @Override
  public void onRelease() {
    stopDragging();
  }

  /**
   * Returns whether the mouse is currently over this Toy object.
   *
   * @return true if the mouse is currently over this Toy object, false otherwise
   */
  public boolean isMouseOver() {
    return isOver(toySaga.mouseX, toySaga.mouseY);
  }
}
