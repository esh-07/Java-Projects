
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hoverball
// Course:   CS 300 Spring 2024
//
// Author:   Eshaan Chaturvedi
// Email:    echaturvedi@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Sehar Randhawa
// Partner Email:   randhawa3@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import processing.core.PApplet;

/**
 * Represents a Hoverball toy in the ToySaga game.
 * The Hoverball toy has a specific behavior in night mode.
 */
public class Hoverball extends Toy {
    /**
     * Constructs a new Hoverball object at the specified x and y coordinates.
     *
     * @param x the x-coordinate of the new Hoverball object
     * @param y the y-coordinate of the new Hoverball object
     */
    public Hoverball(int x, int y) {
        super(ToySaga.HOVERBALL_OFF, x, y);
    }

    /**
     * Switches the image of the Hoverball object depending on the game mode.
     * If the game is in night mode, the Hoverball object is switched on.
     * Otherwise, the Hoverball object is switched off.
     */
    private void switchOnOff() {
        if (toySaga.getMode().equals(ToySaga.NIGHT_MODE)) {
            setImage(ToySaga.HOVERBALL_ON); // Switch on the Hoverball object in night mode
        } else {
            setImage(ToySaga.HOVERBALL_OFF); // Switch off the Hoverball object in other modes
        }
    }

    /**
     * Draws this Hoverball object.
     * The Hoverball object is switched on or off depending on the game mode, and
     * then drawn.
     */
    @Override
    public void draw() {
        switchOnOff(); // Switch on or off the Hoverball object
        super.draw(); // Draw the Hoverball object
    }

    /**
     * Moves this Hoverball object.
     * If the game is in night mode, the Hoverball object moves in a sinusoidal
     * pattern.
     * Otherwise, the Hoverball object moves normally.
     */
    @Override
    public void move() {
        if (toySaga.getMode().equals(ToySaga.NIGHT_MODE)) {
            int dy = Math.round(6 * PApplet.sin(toySaga.frameCount * 0.1f)); // Calculate the vertical displacement
            y += dy; // Move the Hoverball object vertically
        } else {
            super.move(); // Move the Hoverball object normally
        }
    }
}