
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: GraphicObject
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
import processing.core.PImage;

/**
 * Represents a graphic object in the ToySaga game. A graphic object has an image that can be drawn
 * on the screen at a specific position.
 */
public class GraphicObject implements Drawable {
  protected static ToySaga toySaga; // The ToySaga instance that this object belongs to
  protected PImage image; // The image of this object
  protected int x; // The x-coordinate of this object
  protected int y; // The y-coordinate of this object

  /**
   * Constructs a new GraphicObject with the specified image file. The object is positioned at the
   * center of the screen.
   *
   * @param filename the name of the image file
   */
  public GraphicObject(String filename) {
    this(filename, toySaga.width / 2, toySaga.height / 2); // Position the object at the center of
                                                           // the screen
  }

  /**
   * Constructs a new GraphicObject with the specified image file and position.
   *
   * @param filename the name of the image file
   * @param x        the x-coordinate of the new object
   * @param y        the y-coordinate of the new object
   */
  public GraphicObject(String filename, int x, int y) {
    this.x = x; // Set the x-coordinate of the object
    this.y = y; // Set the y-coordinate of the object
    setImage(filename); // Set the image of the object
  }

  /**
   * Sets the image of this object to the specified image file.
   *
   * @param filename the name of the image file
   */
  public void setImage(String filename) {
    this.image = toySaga.loadImage(filename); // Load the image from the file
  }

  /**
   * Draws this object on the screen.
   */
  public void draw() {
    toySaga.image(this.image, this.x, this.y); // Draw the image at the object's position
  }

  /**
   * Returns the x-coordinate of this object.
   *
   * @return the x-coordinate of this object
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y-coordinate of this object.
   *
   * @return the y-coordinate of this object
   */
  public int getY() {
    return this.y;
  }

  /**
   * Sets the ToySaga instance that this object belongs to.
   *
   * @param toySaga the ToySaga instance
   */
  public static void setProcessing(ToySaga toySaga) {
    GraphicObject.toySaga = toySaga; // Set the ToySaga instance
  }
}
