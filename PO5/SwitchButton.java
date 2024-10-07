// PROVIDED -- DO NOT SUBMIT IT TO GRADESCOPE

/**
 * Provided class for the switch button that changes the scene from night to day
 */
public class SwitchButton implements Drawable, MouseListener {
  
  /**
   * ToySaga PApplet object where the button will be displayed
   */
  protected static ToySaga toySaga; // 

  /**
   * Width of the Button
   */
  private static final int WIDTH = 50; // Width of the Button
  
  
  /**
   * Height of the Button
   */
  private static final int HEIGHT = 20; // Height of the Button
  
  
  /**
   * text/label that represents the button
   */
  private String label; // text/label that represents the button

  /**
   * x-position of the center of this button
   */
  private int x; // x-position of the center of this button
  
  
  /**
   * y-position of the center of this button
   */
  private int y; // y-position of the center of this button

  /**
   * Constructs a new SwitchButton at a specific x, y position, and assigns its label to "Switch"
   * 
   * @param x x-position of the center of this button
   * @param y y-position of the center of this button
   */
  public SwitchButton(int x, int y) {
    this("Switch", x, y);
  }

  /**
   * Constructs a new SwitchButton with a specific label and x, y position
   * 
   * @param label to be assigned to this SwitchButton
   * @param x     x-position of the center of this button
   * @param y     y-position of the center of this button
   */
  public SwitchButton(String label, int x, int y) {
    this.x = x;
    this.y = y;
    this.label = label;
  }

  /**
   * Switches the toySaga mode if the mouse is over this button
   */
  @Override
  public void onClick() {
    if (isMouseOver()) {
      toySaga.switchMode();
    }
  }

  /**
   * This button does nothing if the mouse is released.
   */
  @Override
  public void onRelease() {
    // DO NOTHING leave blank!
    // Students can print to the console "MODE SUCCESSFULLY SWITCHED!"
  }

  /**
   * Returns true if the mouse is over this button
   * 
   * @return true if the mouse is over this button
   */
  @Override
  public boolean isMouseOver() {
    return toySaga.mouseX > this.x - WIDTH / 2 && toySaga.mouseX < this.x + WIDTH / 2
        && toySaga.mouseY > this.y - HEIGHT / 2 && toySaga.mouseY < this.y + HEIGHT / 2;
  }

  /**
   * Provided method to draw this button to the display window
   */
  @Override
  public void draw() {
    toySaga.stroke(0);// set line value to black
    if (isMouseOver())
      toySaga.fill(100); // set the fill color to dark gray if the mouse is over the button
    else
      toySaga.fill(200); // set the fill color to light gray otherwise

    // draw the button (rectangle with a centered text)
    toySaga.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    toySaga.fill(0); // set the fill color to black
    toySaga.text(label, x, y); // display the text of the current button
  }

  /**
   * Sets the PApplet object display window where this button will be drawn
   * 
   * @param processing PApplet object that represents the display window
   */
  public static void setProcessing(ToySaga processing) {
    SwitchButton.toySaga = processing;
  }
}
