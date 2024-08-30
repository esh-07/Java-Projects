// DO NOT SUBMIT THIS FILE TO GRADESCOPE

/**
 * This class models LinkedNodes used to build a doubly linked list
 *
 * @param <T> Type of the element stored within this list
 */
public class LinkedNode<T> {
  /**
   * data carried by this node
   */
  private T data; // data carried by this node
  
  /**
   * reference to the previous node
   */
  private LinkedNode<T> prev; // reference to the previous node
  
  /**
   * reference to the next node
   */
  private LinkedNode<T> next; // reference to the next node

  /**
   * Creates a new LinkedNode with a given data and no previous or next nodes in the chain of linked
   * nodes
   * 
   * @param data data to be carried by this node
   */
  public LinkedNode(T data) {
    this.data = data;
  }

  /**
   * Creates a new LinkedNode with a given data, previous, and next nodes
   * 
   * @param data data to be carried by this node
   * @param prev reference to the previous node
   * @param next reference to the next node
   */
  public LinkedNode(T data, LinkedNode<T> prev, LinkedNode<T> next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  /**
   * Returns the data carried by this node
   * 
   * @return the data
   */
  public T getData() {
    return this.data;
  }

  /**
   * Sets the data of this node
   *
   * @param data the data to set
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * Returns the previous node of this node
   *
   * @return the previous node
   */
  public LinkedNode<T> getPrev() {
    return this.prev;
  }

  /**
   * Sets the previous node of this node
   *
   * @param prev the prev to set
   */
  public void setPrev(LinkedNode<T> prev) {
    this.prev = prev;
  }

  /**
   * Gets the next node of this node
   * 
   * @return the next node
   */
  public LinkedNode<T> getNext() {
    return this.next;
  }

  /**
   * Sets the next node of this node
   * 
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

}
