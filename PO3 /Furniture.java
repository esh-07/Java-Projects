
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Furniture
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
 * This class defines furniture items that are part of the ToySaga application. Each furniture item
 * is characterized by its name, location (x, y), and an associated image.
 */
public class Furniture {

  public final PImage IMAGE; // The image that represents this furniture item
  private String name; // The identifier of the furniture
  private int x; // The x-coordinate of the furniture's location
  private int y; // The y-coordinate of the furniture's location

  /**
   * Constructs a furniture item with a given name and location.
   *
   * @param name the identifier of the furniture
   * @param x    the x-coordinate of the furniture's location
   * @param y    the y-coordinate of the furniture's location
   */
  public Furniture(String name, int x, int y) {
    this.name = name; // Set the name
    this.x = x; // Set the x-coordinate
    this.y = y; // Set the y-coordinate
    // Load the image for this furniture using the Utility class
    this.IMAGE = Utility.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Constructs a furniture item with a given name. The location is set to the center of the
   * application window.
   *
   * @param name the identifier of the furniture
   */
  public Furniture(String name) {
    // Invoke the other constructor with default location parameters
    this(name, Utility.width() / 2, Utility.height() / 2);
  }

  /**
   * Retrieves the x-coordinate of the furniture's location.
   *
   * @return the x-coordinate of the furniture's location
   */
  public int getX() {
    return x; // Provide the x-coordinate
  }

  /**
   * Retrieves the y-coordinate of the furniture's location.
   *
   * @return the y-coordinate of the furniture's location
   */
  public int getY() {
    return y; // Provide the y-coordinate
  }

  /**
   * Retrieves the name of the furniture.
   *
   * @return the identifier of the furniture
   */
  public String name() {
    return name; // Provide the name
  }

  /**
   * Renders the furniture item. It uses the associated image and location to render the furniture.
   */
  public void draw() {
    Utility.image(IMAGE, x, y); // Render the image at the given location
  }
}
