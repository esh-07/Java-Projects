
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TeddyBear
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
import processing.core.PApplet;

/**
 * Represents a TeddyBear toy in the ToySaga game. The TeddyBear toy has a specific behavior in
 * night mode.
 */
public class TeddyBear extends Toy {
  private float rotation; // The rotation of the TeddyBear toy
  private boolean rotationDirection; // The direction of rotation of the TeddyBear toy
  private Callout callout; // The callout of the TeddyBear toy

  /**
   * Constructs a new TeddyBear object at the specified x and y coordinates.
   *
   * @param x the x-coordinate of the new TeddyBear object
   * @param y the y-coordinate of the new TeddyBear object
   */
  public TeddyBear(int x, int y) {
    super(ToySaga.BEAR, x, y);
    this.callout = new Callout(this.x, this.y); // Initialize the callout at the TeddyBear's
                                                // position
  }

  /**
   * Returns the rotation of this TeddyBear object.
   *
   * @return the rotation of this TeddyBear object
   */
  public float getRotation() {
    return this.rotation;
  }

  /**
   * Sets the rotation of this TeddyBear object.
   *
   * @param rotation the new rotation of this TeddyBear object
   */
  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  /**
   * Sets the rotation direction of this TeddyBear object.
   *
   * @param direction the new rotation direction of this TeddyBear object
   */
  public void setRotationDirection(boolean direction) {
    rotationDirection = direction;
  }

  /**
   * Returns the rotation direction of this TeddyBear object.
   *
   * @return the rotation direction of this TeddyBear object
   */
  public boolean getRotationDirection() {
    return rotationDirection;
  }

  /**
   * Draws this TeddyBear object. If the game is in night mode, the TeddyBear object is drawn in a
   * specific way. Otherwise, the TeddyBear object is drawn normally.
   */
  @Override
  public void draw() {
    if (toySaga.getMode().equals(toySaga.NIGHT_MODE)) {
      drawTeddyBearNightMode(); // Draw the TeddyBear object in night mode
    } else {
      super.draw(); // Draw the TeddyBear object normally
    }
  }

  /**
   * Draws this TeddyBear object in night mode. The TeddyBear object is moved, rotated, and then
   * drawn with a callout.
   */
  private void drawTeddyBearNightMode() {
    move(); // Move the TeddyBear object
    toySaga.pushMatrix(); // Save the current transformation matrix
    toySaga.translate(x, y); // Translate to the TeddyBear's position
    toySaga.rotate(rotation * PApplet.PI / 2); // Apply rotation
    if (toySaga.getMode() == "NIGHT") {
      toySaga.image(callout.image, 20f, -90f); // Draw the callout
    }
    toySaga.image(image, 0.0f, 0.0f); // Draw the image at the rotated position
    toySaga.popMatrix(); // Restore the previous transformation matrix
  }

  /**
   * Moves this TeddyBear object. If the game is in night mode, the TeddyBear object moves in a
   * specific way. Otherwise, the TeddyBear object moves normally.
   */
  public void move() {
    if (toySaga.getMode().equals(toySaga.NIGHT_MODE)) {
      // Night mode movement
      float rotationIncrement = PApplet.radians(3); // Convert to radians
      if (rotationDirection) {
        rotation += rotationIncrement; // Increase the rotation
        if (rotation >= PApplet.radians(30)) {
          rotationDirection = false; // Change the rotation direction
        }
      } else {
        rotation -= rotationIncrement; // Decrease the rotation
        if (rotation <= PApplet.radians(-30)) {
          rotationDirection = true; // Change the rotation direction
        }
      }
    } else {
      super.move(); // Ordinary toy movement during the day mode
    }
  }
}
