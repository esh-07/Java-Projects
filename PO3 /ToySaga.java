
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ToySaga
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
import java.util.ArrayList;
import java.io.File;

/**
 * The ToySaga class represents a game where players can interact with toys like teddy bears and
 * cars. The game allows players to add, move, and rotate toys on the screen. It includes methods
 * for handling mouse events, drawing furniture and toys, and managing the toy box.
 */
public class ToySaga {
  // The background image displayed in the game.
  private static PImage backgroundImage;

  // The list of furniture objects in the game.
  private static ArrayList<Furniture> furnitureList;

  // The list of toy objects in the game.
  private static ArrayList<Toy> toyList;

  // The name of the box furniture. Used to identify the box furniture object.
  private static final String BOX_NAME = "box";

  // The maximum number of toy objects allowed in the game.
  private static final int MAX_TOYS_COUNT = 8;

  /**
   * The main method of the program. It initializes the game and starts the processing loop.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Initializes the game state. This method loads the background image and creates the initial
   * furniture objects.
   */
  public static void setup() {
    // Load the background image from the specified path.
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    // Initialize the furniture and toy lists.
    furnitureList = new ArrayList<>();
    toyList = new ArrayList<>();

    // Add initial furniture objects to the furniture list at a given position.
    furnitureList.add(new Furniture("bed", 520, 270));
    furnitureList.add(new Furniture("rug", 220, 370));
    furnitureList.add(new Furniture("nightstand", 325, 240));
    furnitureList.add(new Furniture("box", 90, 230));
  }

  /**
   * This method is responsible for rendering the background image, furniture, and toys on the
   * display. It is invoked right after setup() and keeps executing until the program is halted.
   * Initially, it places the background image at the center of the screen. Then, it updates the
   * toyList to eliminate any toy that is over the box furniture, and renders furniture and toy
   * objects.
   */
  public static void draw() {
    // Place the backgroundImage in the middle of the screen (application).
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // Render each furniture object.
    for (int i = 0; i < furnitureList.size(); i++) {
      furnitureList.get(i).draw();
    }

    // Refresh toy list by eliminating any toys that are over the toy box.
    for (int i = toyList.size() - 1; i >= 0; i--) {
      if (toyList.get(i).isOver(getToyBox())) {
        toyList.remove(i);
      }
    }
    // Render each toy object.
    for (int i = 0; i < toyList.size(); i++) {
      toyList.get(i).draw();
    }
  }

  /**
   * Fetches the Furniture object that represents the toy box. It looks through the list of
   * furniture objects to find the one with a name that matches BOX_NAME.
   * 
   * @return the Furniture object that represents the toy box, or null if no match is found.
   */
  public static Furniture getToyBox() {
    for (int i = 0; i < furnitureList.size(); i++) {
      Furniture furniture = furnitureList.get(i);
      if (furniture.name().equalsIgnoreCase(BOX_NAME)) {
        return furniture; // Box furniture found
      }
    }
    return null; // return null if box is not found
  }

  /**
   * Provides the toy object that is currently under mouse drag. It is assumed that only one toy
   * object can be dragged at any given time.
   *
   * @return the toy under drag, or null if no toy is currently being dragged.
   */
  public static Toy getDraggingToy() {
    for (int i = 0; i < toyList.size(); i++) {
      if (toyList.get(i).isDragging()) {
        return toyList.get(i); // Provide the toy currently under drag
      }
    }
    return null; // Provide null if no object is under drag
  }

  /**
   * Callback method invoked once after every mouse button press. If no toy is under drag, this
   * method verifies if the mouse is over a toy and initiates dragging it. If the mouse is over
   * multiple toys, only the toy at the lowest index will initiate dragging.
   */
  public static void mousePressed() {
    for (int i = 0; i < toyList.size(); i++) {
      Toy toy = toyList.get(i);
      if (toy.isOver(Utility.mouseX(), Utility.mouseY())) {
        toy.startDragging(); // Initiate dragging the toy if the cursor is over the toy
        break; // Only the first toy under the mouse cursor initiates dragging
      }
    }
  }

  /**
   * Callback method invoked every time the mouse button is released. This method halts dragging any
   * toy that is currently in the toy list.
   */
  public static void mouseReleased() {
    for (int i = 0; i < toyList.size(); i++) {
      Toy toy = toyList.get(i);
      toy.stopDragging(); // Halt dragging the toy
      break; // Since only one toy can be dragged, break after halting the drag
    }
  }

  /**
   * Callback method invoked each time a key is pressed. It carries out actions based on the key
   * pressed as follows: - 'c' or 'C': Introduces a new toy car at the mouse location if the MAX
   * TOYS COUNT hasn't been exceeded. - 't' or 'T': Introduces a teddy bear toy at the mouse
   * location if the MAX TOYS COUNT hasn't been exceeded. - 'r' or 'R': Rotates a toy if the mouse
   * is over it. Only a single toy is rotated at a time.
   */
  public static void keyPressed() {
    // Carry out actions based on the key pressed
    char key = Utility.key();
    switch (key) {
      case 'c':
      case 'C':
        // Introduce a new toy car if maximum count hasn't been exceeded
        if (toyList.size() < MAX_TOYS_COUNT) {
          toyList.add(new Toy("car", Utility.mouseX(), Utility.mouseY()));
        }
        break;
      case 't':
      case 'T':
        // Introduce a new teddy bear toy if maximum count hasn't been exceeded
        if (toyList.size() < MAX_TOYS_COUNT) {
          toyList.add(new Toy("teddyBear", Utility.mouseX(), Utility.mouseY()));
        }
        break;
      case 'r':
      case 'R':
        // Rotate the toy under the mouse cursor
        for (int i = 0; i < toyList.size(); i++) {
          Toy toy = toyList.get(i);
          if (toy.isOver(Utility.mouseX(), Utility.mouseY())) {
            toy.rotate();
            break; // Only rotate the first toy located under the mouse
          }
        }
        break;
    }
  }
}
