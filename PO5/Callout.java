// PROVIDED -- DO NOT SUBMIT THIS FILE TO GRADESCOPE

import java.io.File;
import java.util.Random;

/**
 * This class models a Callout object. A Callout is a text bubble that appears
 * over a teddyBear with a specific message and follows the bear's movements
 *
 */
public class Callout extends GraphicObject {
	/**
	 * filename of the alive callout
	 */
	private static final String ALIVE = "images" + File.separator + "alive.png";

	/**
	 * filename of the hello callout
	 */
	private static final String HELLO = "images" + File.separator + "hello.png";

	/**
	 * filename of the love callout
	 */
	private static final String LOVE = "images" + File.separator + "love.png";

	/**
	 * filenames of callouts
	 */
	private static String[] filenames = { ALIVE, HELLO, LOVE };

	/**
	 * Constructs a new Callout object positioned at x and y positions within the
	 * screen and assigns it a random filename from the filenames array as its image
	 * filename using the getRandomFilename() method
	 * 
	 * @param x x-position (horizontal position) of this Callout object
	 * @param y y-position (vertical position) of this Callout object
	 */
	public Callout(int x, int y) {
		super(getRandomFilename(), x, y);
	}

	/**
	 * Provided method that randomly picks a filename of among the available callout
	 * filenames
	 * 
	 * @return a filename to be loaded as an image for a callout
	 */
	private static String getRandomFilename() {

		int index = new Random().nextInt(filenames.length);
		return filenames[index];
	}

}
