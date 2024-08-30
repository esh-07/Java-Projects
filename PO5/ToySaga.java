
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ToySaga
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
import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class implements the main graphic user interface (GUI) of the p05 Toy Saga II program
 */
public class ToySaga extends PApplet {
  // CONSTANTS
  // PATH to the folder of all images
  private static final String IMAGES_PATH = "images" + File.separator;
  // filename of the day background image of this toy saga
  protected static final String DAY_BACKGROUND = IMAGES_PATH + "backgroundDay.png";
  // filename of the night background image of this toy saga
  protected static final String NIGHT_BACKGROUND = IMAGES_PATH + "backgroundNight.png";
  // filename of the image of the bed
  protected static final String BED = IMAGES_PATH + "bed.png";
  // filename of the image of the nightstand
  protected static final String NIGHTSTAND = IMAGES_PATH + "nightstand.png";
  // filename of the image of the rug
  protected static final String RUG = IMAGES_PATH + "rug.png";
  // filename of the image of the car
  protected static final String CAR = IMAGES_PATH + "car.png";
  // filename of the image of the teddy bear
  protected static final String BEAR = IMAGES_PATH + "teddyBear.png";
  // filename of the image of the hoverball when it is on (night mode)
  protected static final String HOVERBALL_ON = IMAGES_PATH + "hoverBallOn.png";
  // filename of the image of the hoverball when it is off (day mode)
  protected static final String HOVERBALL_OFF = IMAGES_PATH + "hoverBallOff.png";
  // day mode
  protected static final String DAY_MODE = "DAY";
  // night mode
  protected static final String NIGHT_MODE = "NIGHT";
  // Maximum number of visible toys that can be stored in the drawableObjects
  // list.
  private static final int MAX_TOYS_COUNT = 8;
  // other fields

  private static PImage backgroundImage;
  // PImage object that represents the background image

  private ArrayList<Drawable> drawableObjects;
  // The drawableObjects arraylist stores elements of type Drawable (interface
  // Drawable) ONLY.

  private String mode;
  // mode represents the current mode of this ToySaga application.

  /**
   * Driver method that launches the application by calling this.runApplication()
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("ToySaga");
  }

  /**
   * Gets the current mode of this Toy Saga app. The mode might be DAY or NIGHT.
   *
   * @return the current mode of this application
   */
  public String getMode() {

    // default return statement
    return this.mode;
  }

  /**
   * Returns true if this ToySaga mode is NIGHT_MODE
   *
   * @return true if this ToySaga mode is NIGHT_MODE
   */
  public boolean isNightMode() {
    return mode.equals(NIGHT_MODE);
  }

  /**
   * Switches the mode of this toy saga application and loads the background image of the switched
   * mode. <BR>
   *
   * Meaning, sets the mode to NIGHT_MODE if it was DAY_MODE and vice versa, and updates the
   * background image accordingly.
   */
  public void switchMode() {
    // Check if the current mode is DAY_MODE
    if (mode.equals(DAY_MODE)) {
      // If so, switch to NIGHT_MODE
      mode = NIGHT_MODE;
      // Load the night background image
      backgroundImage = loadImage(NIGHT_BACKGROUND);
    } else {
      // If the current mode is not DAY_MODE, switch to DAY_MODE
      mode = DAY_MODE;
      // Load the day background image
      backgroundImage = loadImage(DAY_BACKGROUND);
    }
  }

  /**
   * Sets the title and defines the initial environment properties of this graphic application. <br>
   * This method initializes all the data fields defined in this class.
   */
  @Override
  public void setup() {
    // sets the title and graphic environment properties of the display window
    this.getSurface().setTitle("P5 Toy Saga v2.0");
    this.textAlign(CENTER, CENTER);// horizontal alignment: center, vertical alignment: center
    this.imageMode(CENTER);// interprets the second and third parameters of image() as the
    // image’s center point.
    this.rectMode(CORNERS); // interprets the first two parameters of rect() as the location
    // of one corner, and the third and fourth parameters as the
    // location of the opposite corner.
    this.focused = true;// sets the processing program to be focused (true), meaning that
    // it is active and will accept input from mouse or keyboard
    mode = DAY_MODE;
    backgroundImage = loadImage(DAY_BACKGROUND);
    drawableObjects = new ArrayList<Drawable>();
    // DOUBT(this-current obj that's being used)
    SwitchButton.setProcessing(this);
    GraphicObject.setProcessing(this);

    // Add a new SwitchButton object to the drawableObjects list. The SwitchButton
    // is positioned at (565, 20) on the screen.
    drawableObjects.add(new SwitchButton(565, 20));

    // Add a new GraphicObject representing a bed to the drawableObjects list. The
    // bed image is loaded from the file path and the object is positioned at (520,
    // 270) on the screen.
    drawableObjects.add(new GraphicObject(IMAGES_PATH + "bed.png", 520, 270));

    // Add a new GraphicObject representing a rug to the drawableObjects list. The
    // rug image is loaded from the file path and the object is positioned at (220,
    // 370) on the screen.
    drawableObjects.add(new GraphicObject(IMAGES_PATH + "rug.png", 220, 370));

    // Add a new GraphicObject representing a nightstand to the drawableObjects
    // list. The nightstand image is loaded from the file path and the object is
    // positioned at (325, 240) on the screen.
    drawableObjects.add(new GraphicObject(IMAGES_PATH + "nightstand.png", 325, 240));
  }

  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   *
   * This method first draws the background image to the center of the screen. Then, it draws every
   * object stored in the drawableObjects list
   */
  @Override
  public void draw() {

    // Draw the background image centered on the screen

    image(backgroundImage, width / 2, height / 2);
    for (Drawable drawImg : drawableObjects) {
      drawImg.draw();
    }
  }

  /**
   * Callback method called once after every time the mouse button is pressed.
   *
   * This method calls the onClick() method on every instance of MouseListener stored in the
   * drawableObjects list
   *
   */
  @Override
  public void mousePressed() {
    // Iterate over each Drawable object in the drawableObjects list
    for (Drawable drawImg : drawableObjects) {
      // Check if the Drawable object is also a MouseListener
      if (drawImg instanceof MouseListener) {
        // If so, call its onClick method (this is a mouse press event)
        ((MouseListener) drawImg).onClick();
      }
    }
  }

  /**
   * Callback method called every time the mouse button is released.
   *
   * This method calls the onRelease() method on every instance of MouseListener stored in the
   * drawableObjects list DOUBT-CASTING
   *
   */
  @Override
  public void mouseReleased() {
    // Iterate over each Drawable object in the drawableObjects list
    for (Drawable drawImg : drawableObjects) {
      // Check if the Drawable object is also a MouseListener
      if (drawImg instanceof MouseListener) {
        // If so, call its onRelease method (this is a mouse release event)
        ((MouseListener) drawImg).onRelease();
      }
    }
  }

  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the this.key() this method.<BR>
   * The ToySaga.keyPressed() method performs the below actions based on the pressed key: <BR>
   *
   * - Pressing 'c' or 'C' adds a new Car object at the mouse position if the MAX TOYS COUNT is not
   * reached. <BR>
   * - Pressing 't' or 'T' adds a new TeddyBear object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'h' or 'H' adds a new Hoverball object at the mouse position if the MAX TOYS COUNT
   * is not reached. <BR>
   * - Pressing 'd' or 'D' sets/switches the mode to DAY_MODE and loads the DAY_BACKGROUND for the
   * background image of this application. <BR>
   * - Pressing 'n' or 'N' sets/switches the mode to NIGHT_MODE and loads the NIGHT_BACKGROUND for
   * the background image of this application. <BR>
   *
   */
  @Override
  public void keyPressed() {

    // Check which key was pressed
    switch (this.key) {
      case 'c':
      case 'C':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new Car(mouseX, mouseY));

        }
        break;
      case 't':
      case 'T':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new TeddyBear(mouseX, mouseY));

        }
        break;
      case 'h':
      case 'H':
        if (getToyCount() < MAX_TOYS_COUNT) {
          drawableObjects.add(new Hoverball(mouseX, mouseY));

        }
        break;
      case 'd':
      case 'D':
        mode = DAY_MODE;
        backgroundImage = loadImage(DAY_BACKGROUND);
        break;
      case 'n':
      case 'N':
        mode = NIGHT_MODE;
        backgroundImage = loadImage(NIGHT_BACKGROUND);
        break;
      default:
        // Handle other keys if needed
        break;
    }
  }

  /**
   * Checks if any toy is currently being dragged.
   *
   * @return false if at least one toy is being dragged, true otherwise.
   */
  public boolean noToyIsDragging() {
    for (Drawable obj : drawableObjects) {
      if (obj instanceof Toy && ((Toy) obj).isDragging()) {
        return false; // At least one toy is dragging
      }
    }
    return true; // No toy is dragging
  }

  /**
   * Counts the number of Toy objects in the drawableObjects list.
   *
   * @return the count of Toy objects.
   */
  public int getToyCount() {
    // Initialize a counter to keep track of the number of Toy objects
    int count = 0;

    // Iterate over each Drawable object in the drawableObjects list
    for (Drawable obj : drawableObjects) {
      // Check if the Drawable object is an instance of Toy
      if (obj instanceof Toy) {
        // If so, increment the counter
        count++;
      }
    }

    // Return the total count of Toy objects
    return count;
  }

}
