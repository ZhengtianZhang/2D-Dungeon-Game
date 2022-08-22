package model;

/**
 * A player can enter the dungeon at the start, move from their current location 
 * and pick up treasure that is located in their same location.
 */
public interface Player {
  
  /**
   * Move to a certain coordinate.
   * 
   * @param coordinate the coordinate
   */
  void moveTo(Coordinate coordinate);
  
  /**
   * Collect a treasure.
   * 
   * @param treasure the treasure
   */
  void collectTreasure(Treasure treasure);
  
  /**
   * Get the coordinate of this player.
   * @return the coordinate
   */
  Coordinate getCoordinate();
  
  /**
   * Use one of the arrows.
   */
  void useArrow();
  
  /**
   * Get the number of arrows that the player has.
   * 
   * @return the number of arrows
   */
  int getArrow();
  
  /**
   * Get the number of diamonds that the player has.
   * 
   * @return the number of diamonds
   */
  int getDiamond();
  
  /**
   * Get the number of rubies that the player has.
   * 
   * @return the number of rubies
   */
  int getRuby();
  
  /**
   * Get the number of sapphires that the player has.
   * 
   * @return the number of sapphires
   */
  int getSapphire();
}
