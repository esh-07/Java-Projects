//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Car
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
 * Represents a Car toy in the ToySaga game. The Car toy has a specific speed and direction of
 * movement.
 */
public class Car extends Toy {
  // The absolute speed of all Car objects
  private static int absoluteSpeed = 8;
  // The speed of this Car object
  private int speed = absoluteSpeed;
  // The direction of movement of this Car object (true for rightward, false for
  // leftward)
  private boolean isMovingRightward = true;

  /**
   * Constructs a new Car object at the specified x and y coordinates.
   *
   * @param x the x-coordinate of the new Car object
   * @param y the y-coordinate of the new Car object
   */
  public Car(int x, int y) {
    super(ToySaga.CAR, x, y);
  }

  /**
   * Draws this Car object. If the game is in night mode, the Car object moves and is drawn in night
   * mode. Otherwise, the Car object is drawn normally.
   */
  @Override
  public void draw() {
    if (toySaga.isNightMode()) {
      move(); // If it's night mode, move the car first
      drawCarNightMode(); // Then draw the car in night mode
    } else {
      super.draw(); // If it's not night mode, use the draw method from the superclass
    }
  }

  /**
   * Draws this Car object in night mode. The car is translated to its current position and then
   * drawn. If the car is moving leftward, it is flipped horizontally.
   */
  private void drawCarNightMode() {
    toySaga.pushMatrix(); // Save the current transformation matrix
    toySaga.rotate(0.0f); // No rotation is applied
    toySaga.translate(x, y); // Move the car to its current position
    if (!isMovingRightward) {
      toySaga.scale(-1.0f, 1.0f); // If the car is moving leftward, flip it horizontally
    }
    toySaga.image(image, 0.0f, 0.0f); // Draw the car
    toySaga.popMatrix(); // Restore the previous transformation matrix
  }

  /**
   * Moves this Car object. If the game is in night mode, the Car object moves rightward or leftward
   * across the screen. Otherwise, the Car object moves normally.
   */
  @Override
  public void move() {
    if (!toySaga.isNightMode()) {
      super.move(); // If it's not night mode, use the move method from the superclass
    } else {
      x += speed; // If it's night mode, adjust the x-coordinate by the speed
      if (x >= toySaga.width || x <= 0) {
        flipMoveDirection(); // If the car has reached the edge of the screen, flip its direction
      }
    }
  }

  /**
   * Flips the direction of movement of this Car object and reverses its speed. If the car was
   * moving rightward, it will now move leftward, and vice versa. The speed of the car is also
   * reversed.
   */
  public void flipMoveDirection() {
    isMovingRightward = !isMovingRightward; // Flip the direction of movement
    speed = -speed; // Reverse the speed
  }

  /**
   * Returns the absolute speed of all Car objects.
   *
   * @return the absolute speed of all Car objects
   */
  public static int getSpeed() {
    return absoluteSpeed;
  }

  /**
   * Sets the absolute speed of all Car objects to the specified speed.
   *
   * @param speed the new absolute speed of all Car objects
   */
  public static void setSpeed(int speed) {
    absoluteSpeed = speed;
  }
}
