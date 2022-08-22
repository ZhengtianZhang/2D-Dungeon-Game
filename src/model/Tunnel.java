package model;

/**
 * A tunnel connects two caves so that player can travel through it.
 */
public class Tunnel {

  Cave cave1;
  Cave cave2;
  
  /**
   * Construct a tunnel with the two caves it connects.
   * 
   * @param cave1 the first cave
   * @param cave2 the second cave
   */
  public Tunnel(Cave cave1, Cave cave2) {
    this.cave1 = cave1;
    this.cave2 = cave2;
  }
  
  /**
   * Get the first cave.
   * 
   * @return the first cave
   */
  public Cave getCave1() {
    return cave1;
  }
  
  /**
   * Get the second cave.
   * 
   * @return the second cave
   */
  public Cave getCave2() {
    return cave2;
  }
}
