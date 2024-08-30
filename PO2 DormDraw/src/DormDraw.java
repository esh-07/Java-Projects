
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: DormDraw
// Course: CS 300 Spring 2024
//
// Author: Eshaan Chaturvedi
// Email: echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;

import processing.core.PImage;

/**
 * This class represents a dorm room drawing application. It allows users to
 * add, remove, and rotate
 * various dorm room symbols (like bed, chair, desk, etc.) on a canvas. The
 * state of the canvas can
 * be saved to a file.
 * 
 * 
 * @author Eshaan Chaturvedi
 */
public class DormDraw {
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Symbol[] symbols; // non-compact perfect size array storing
                                     // dorm symbols added to the display window

    /**
     * The main method that runs the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the application using the Utility class
        Utility.runApplication();
    }

    /**
     * Sets up the application. Loads the background image and initializes the
     * symbols array.
     */
    public static void setup() {
        // Load the background image using the Utility class
        backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
        // Initialize the symbols array with a size of 12
        symbols = new Symbol[12];
    }

    /**
     * Draws the application frame. Displays the background image and all added
     * symbols.
     */
    public static void draw() {
        // Set the background color
        Utility.background(Utility.color(255, 250, 250));
        // If a background image exists, draw it
        if (backgroundImage != null) {
            Utility.image(backgroundImage, backgroundImage.width / 2, backgroundImage.height / 2);
        }
        // Draw all symbols in the symbols array
        for (Symbol symbol : symbols) {
            if (symbol != null) {
                symbol.draw();
            }
        }
    }

    /**
     * Handles key press events. Depending on the key pressed, it adds a new symbol,
     * rotates a symbol,
     * removes a symbol, or saves the current state of the canvas.
     */
    public static void keyPressed() {
        // Get the key pressed
        char key = Utility.key();
        // Initialize a Symbol object to null
        Symbol toAdd = null;
        // Switch on the lowercase version of the key pressed
        switch (Character.toLowerCase(key)) {
            // If 'b' was pressed, create a new bed symbol at the mouse's location
            case 'b':
                toAdd = new Symbol("bed.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'c' was pressed, create a new chair symbol at the mouse's location
            case 'c':
                toAdd = new Symbol("chair.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'd' was pressed, create a new dresser symbol at the mouse's location
            case 'd':
                toAdd = new Symbol("dresser.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'k' was pressed, create a new desk symbol at the mouse's location
            case 'k':
                toAdd = new Symbol("desk.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'f' was pressed, create a new sofa symbol at the mouse's location
            case 'f':
                toAdd = new Symbol("sofa.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'g' was pressed, create a new rug symbol at the mouse's location
            case 'g':
                toAdd = new Symbol("rug.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'p' was pressed, create a new plant symbol at the mouse's location
            case 'p':
                toAdd = new Symbol("plant.png", Utility.mouseX(), Utility.mouseY());
                break;
            // If 'r' was pressed, rotate the first symbol under the mouse
            case 'r':
                rotateFirstSymbolUnderMouse();
                break;
            // If backspace was pressed, remove the first symbol under the mouse
            case Utility.BACKSPACE:
                removeFirstSymbolUnderMouse();
                break;
            // If 's' was pressed, save the current state of the canvas
            case 's':
                Utility.save("dormDraw.png");
                break;
        }
        // If a new symbol was created, add it to the symbols array
        if (toAdd != null) {
            addSymbol(symbols, toAdd);
        }
    }

    /**
     * Rotates the first symbol under the mouse. It iterates over the symbols array
     * and calls the
     * rotate() method on the first symbol that the mouse is currently over.
     */
    private static void rotateFirstSymbolUnderMouse() {
        // Iterate over the symbols array
        for (Symbol symbol : symbols) {
            // If the mouse is over a symbol, rotate it
            if (symbol != null && isMouseOver(symbol)) {
                symbol.rotate();
                break;
            }
        }
    }

    /**
     * Removes the first symbol under the mouse. It iterates over the symbols array
     * and sets the first
     * symbol that the mouse is currently over to null, effectively removing it from
     * the canvas.
     */
    private static void removeFirstSymbolUnderMouse() {
        // Iterate over the symbols array
        for (int i = 0; i < symbols.length; i++) {
            // If the mouse is over a symbol, remove it
            if (symbols[i] != null && isMouseOver(symbols[i])) {
                symbols[i] = null;
                break;
            }
        }
    }

    /**
     * Adds a new symbol to the symbols array. It iterates over the symbols array
     * and places the new
     * symbol at the first null position.
     *
     * @param symbols the array to add the symbol to
     * @param toAdd   the symbol to add
     */
    public static void addSymbol(Symbol[] symbols, Symbol toAdd) {
        // Iterate over the symbols array
        for (int i = 0; i < symbols.length; i++) {
            // Add the new symbol at the first null position
            if (symbols[i] == null) {
                symbols[i] = toAdd;
                break;
            }
        }
    }

    /**
     * Checks if the mouse is currently over the given symbol. It does this by
     * comparing the mouse's x
     * and y coordinates with the symbol's boundaries.
     *
     * @param symbol the symbol to check
     * @return true if the mouse is over the symbol, false otherwise
     */
    public static boolean isMouseOver(Symbol symbol) {
        // Get the mouse's x and y coordinates
        float mouseX = Utility.mouseX();
        float mouseY = Utility.mouseY();
        // Check if the mouse is over the given symbol
        return mouseX >= symbol.x() - symbol.width() / 2 && mouseX <= symbol.x() + symbol.width() / 2
                && mouseY >= symbol.y() - symbol.height() / 2 && mouseY <= symbol.y() + symbol.height() / 2;
    }

    /**
     * Handles mouse press events. It iterates over the symbols array and starts
     * dragging the first
     * symbol that the mouse is currently over.
     */
    public static void mousePressed() {
        // Iterate over the symbols array
        for (Symbol symbol : symbols) {
            // If the mouse is over a symbol, start dragging it
            if (symbol != null && isMouseOver(symbol)) {
                symbol.startDragging();
                break;
            }
        }
    }

    /**
     * Handles mouse release events. It iterates over the symbols array and stops
     * dragging all
     * symbols.
     */
    public static void mouseReleased() {
        // Iterate over the symbols array
        for (Symbol symbol : symbols) {
            // Stop dragging all symbols
            if (symbol != null) {
                symbol.stopDragging();
            }
        }
    }
}
