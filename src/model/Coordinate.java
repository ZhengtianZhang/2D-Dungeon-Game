package model;

/**
 * The coordinate of a location. Different than traditional coordinate, 
 * I use x to represent vertical coordinate and y to represent horizontal coordinate.
 */
public class Coordinate {

  private int x;
  private int y;
  
  /**
   * Construct a Coordinate with its vertical coordinate and horizontal coordinate.
   * 
   * @param x vertical coordinate
   * @param y horizontal coordinate
   */
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Get the vertical coordinate.
   * 
   * @return the vertical coordinate
   */
  public int getX() {
    return this.x;
  }
  
  /**
   * Get the horizontal coordinate.
   * 
   * @return the horizontal coordinate
   */
  public int getY() {
    return this.y;
  }
  
  /**
   * To see if this coordinate is the same with another coordinate.
   * 
   * @param other the other coordinate
   * @return if this coordinate is the same with the other coordinate
   */
  public boolean isSame(Coordinate other) {
    return this.x == other.getX() && this.y == other.getY();
  }
}
