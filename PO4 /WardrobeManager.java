import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

/**
 * Driver class for users to manage their wardrobe using
 * a text interface. For use in the Wardrobe Manager project.
 * 
 * @author Michelle
 *
 */
public class WardrobeManager {

  private static Wardrobe wardrobe; // the wardrobe for the manger to manage
  private static Scanner readIn; // a scanner object used to read in user input from the console
  private static boolean keepRunning; // a boolean to keep track of if the program should continue running

  /**
   * Prints out a list of commands to the console.
   */
  private static void printCommands() {
    System.out.println("Please pick on of the following commands: ");
    System.out.println("\t [V]  View wardrobe");
    System.out.println("\t [F],<description>,<brand>  Find a piece of clothing");
    System.out.println("\t [A],<description>,<brand> Add a piece of new clothing");
    System.out.println("\t [W],<description>,<brand>,<year>,<month>,<day> Wear a piece clothing");
    System.out.println("\t [R],<description>,<brand> Remove a piece of clothing");
    System.out.println("\t [O],<year>,<month>,<day> Remove all old clothing");
    System.out.println("\t [I],<threshold> Remove all clothing infrequently worn");
    System.out.println("\t [L],<filename.txt>Load a wardobe from a file");
    System.out.println("\t [S],<filename.txt>Save the wardrobe to a file");
    System.out.println("\t [H]  View list of commands");
    System.out.println("\t [Q]  Quit");
  }

  /**
   * Parses and executes the load command. Will print out a message if something
   * went wrong parsing
   * or executing the command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void loadWardrobe(String input) {

    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 2) {
      System.out.println("Not a valid command.");
      return;
    }

    // load from file, if not successful inform the user
    if (!wardrobe.loadFromFile(new File(parts[1])))
      System.out.println("There was an issue loading from the file.");
    else
      System.out.println("Loaded successfully from file!");

  }

  /**
   * Parses and executes the save command. Will print out a message if something
   * went wrong parsing
   * or executing the command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void saveWardrobe(String input) {

    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 2) {
      System.out.println("Not a valid command.");
      return;
    }

    // save to file, if not successful inform the user
    if (!wardrobe.saveToFile(new File(parts[1])))
      System.out.println("There was an issue saving to the file.");
    else
      System.out.println("Saved successfully to file!");

  }

  /**
   * Parses and executes the remove infrequently worn clothing command.
   * Will print out a message if something went wrong parsing or executing the
   * command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void removeInfrequentlyWornClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 2) {
      System.out.println("Not a valid command.");
      return;
    }

    try {
      // remove all clothing worn less than the threshold
      wardrobe.removeAllClothingWornNumTimes(Integer.parseInt(parts[1]));
      System.out.println("Removed all infrequently worn clothing!");

    } catch (NumberFormatException e) { // threshold value isn't a number, inform user
      System.out.println("Not a valid command. Threshold must be a number.");
    }
  }

  /**
   * Parses and executes the remove old clothing command.
   * Will print out a message if something went wrong parsing or executing the
   * command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void removeOldClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 4) {
      System.out.println("Not a valid command.");
      return;
    }

    try {

      // parse argument strings to ints
      int year = Integer.parseInt(parts[1]);
      int month = Integer.parseInt(parts[2]);
      int day = Integer.parseInt(parts[3]);

      // remove all clothing based on the given date
      wardrobe.removeAllClothingWornBefore(year, month, day);
      System.out.println("Removed all old clothing!");
    } catch (NumberFormatException e) { // inform user arguments aren't ints
      System.out.println("Not a valid command. Day, month, and year must be numbers.");
    }
  }

  /**
   * Parses and executes the remove clothing command.
   * Will print out a message if something went wrong parsing or executing the
   * command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void removeClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 3) {
      System.out.println("Not a valid command.");
      return;
    }

    // check that the description and brand are not blank
    String description = parts[1];
    String brand = parts[2];
    if (description.isBlank() || brand.isBlank()) {
      System.out.println("Description and/or brand cannot be blank.");
      return;
    }

    try {
      // remove clothing
      wardrobe.removeClothing(description, brand);
      System.out.println("Removed that piece of clothing!");
    } catch (NoSuchElementException e) { // inform user unable to remove
      System.out.println("That piece of clothing is not in the wardrobe.");
    }
  }

  /**
   * Parses and executes the wear clothing command.
   * Will print out a message if something went wrong parsing or executing the
   * command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void wearClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 6) {
      System.out.println("Not a valid command.");
      return;
    }

    try {
      // create a dummy that has the same description and brand to help search
      Clothing toWear = new Clothing(parts[1], parts[2]);

      // wear the clothing
      wardrobe.wearClothing(toWear, Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
          Integer.parseInt(parts[5]));
      System.out.println("Piece of clothing has been updated!");

    } catch (NumberFormatException e) { // inform user date arguments are not numbers
      System.out.println("Not a valid command. Day, month, and year must be numbers.");
    } catch (IllegalArgumentException e) { // inform user arguments cannot be blank
      System.out.println("Description and/or brand cannot be blank.");
    }
  }

  /**
   * Parses and executes the add clothing command.
   * Will print out a message if something went wrong parsing
   * or executing the command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void addClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 3) {
      System.out.println("Not a valid command.");
      return;
    }

    try {
      // create the clothing and add it to wardrobe
      Clothing toAdd = new Clothing(parts[1], parts[2]);
      wardrobe.addClothing(toAdd);
      System.out.println("Added clothing to wardrobe!");
    } catch (IllegalStateException e) {// inform user of duplicate
      System.out.println("Cannot add a duplicate piece of clothing to the wardrobe.");
    } catch (IllegalArgumentException e) { // inform user arguments cannot be blank
      System.out.println("Cannot add a piece of clothing with no description and/or brand.");
    }
  }

  /**
   * Parses and executes the find clothing command.
   * Will print out a message if something went wrong parsing or executing the
   * command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void findClothing(String input) {
    // check it has the proper number of args
    String[] parts = input.split(",");
    if (parts.length != 3) {
      System.out.println("Not a valid command.");
      return;
    }

    try {
      // print out the piece of clothing found
      System.out.println(wardrobe.getClothing(parts[1], parts[2]));
    } catch (IllegalArgumentException e) {// inform user arguments are bad
      System.out.println("Cannot look for a piece of clothing with no description and/or brand.");
    }
  }

  /**
   * Runs and calls the corresponding method based on the user input command.
   * 
   * @param input the command String given by the user that has already been
   *              trimmed of trailing
   *              whitespace
   */
  private static void runCommand(String input) {
    switch (input.charAt(0)) {
      case 'V', 'v': // view current wardrobe
        System.out.println("Here is your current wardrobe: ");
        System.out.println(wardrobe);
        break;
      case 'F', 'f': // find a piece of clothing
        findClothing(input);
        break;
      case 'A', 'a': // add a piece of clothing
        addClothing(input);
        break;
      case 'W', 'w': // wear a piece of clothing
        wearClothing(input);
        break;
      case 'R', 'r': // remove a piece of clothing
        removeClothing(input);
        break;
      case 'O', 'o': // remove old clothing
        removeOldClothing(input);
        break;
      case 'I', 'i': // remove infrequently worn clothing
        removeInfrequentlyWornClothing(input);
        break;
      case 'L', 'l': // load wardrobe
        loadWardrobe(input);
        break;
      case 'S', 's': // save wardrobe
        saveWardrobe(input);
        break;
      case 'H', 'h': // print commands again
        printCommands();
        break;
      case 'Q', 'q': // quit
        keepRunning = false;
        break;
      default: // inform user of non-valid command
        System.out.println("Not a valid command.");
        break;

    }
  }

  public static void main(String[] args) {
    // some primary set-up
    wardrobe = new Wardrobe(10);
    readIn = new Scanner(System.in);
    keepRunning = true;

    // welcome the user
    System.out.println("--- Welcome to the Wardrobe Management System! ---");
    printCommands();

    // loop to recieve input from user and execute commands
    while (keepRunning) {
      System.out.print(">> ");
      String input = readIn.nextLine();
      runCommand(input.trim());
    }

    readIn.close(); // close all scanners to release resources and prevent memory leak

    // say farewell to user
    System.out.println("------------------- Goodbye! -------------------");

  }
}
