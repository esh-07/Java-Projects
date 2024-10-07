// PROVIDED -- DO NOT SUBMIT THIS FILE TO GRADESCOPE
/**
 * Interface for a mouse, which can be interacted with by the user
 */
public interface MouseListener {
  // Similar to mousePressed method from the original Toy Saga
  /**
   * Defines the behavior of this mouse listener each time it is clicked by the mouse.
   */
  public void onClick();

  // Similar to mouseReleased method from the original Toy Saga
  /**
   * Defines the behavior of this mouse listener each time the mouse is released
   */
  public void onRelease();

  // Similar to the isMouseOver method from the original Toy Saga
  /**
   * Checks whether the mouse is over this mouse listener
   * 
   * @return true if the mouse is over this mouse listener, false otherwise
   */
  public boolean isMouseOver();
}
