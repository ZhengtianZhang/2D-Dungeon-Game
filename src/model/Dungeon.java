package model;

import java.util.ArrayList;

/**
 * The world for our game consists of a dungeon, 
 * a network of tunnels and caves that are interconnected so that 
 * player can explore the entire world by traveling from cave to cave 
 * through the tunnels that connect them.
 */
public interface Dungeon {

  /**
   * Generate a dungeon with its interconnectivity and whether it is wrapping or not.
   * 
   * @param iswrap whether the dungeon is wrapping or not
   * @param interconnectivity the interconnectivity
   */
  void generate(boolean iswrap, int interconnectivity);
  
  /**
   * Add treasure to a specified percentage of caves.
   * 
   * @param percentage the percentage
   */
  void addTreasure(int percentage);
  
  /**
   * Randomly selected one cave as the start and one cave to be the end.
   */
  void decideStartEnd();
  
  /**
   * Move the player from current location.
   * 
   * @param move the direction of moving
   */
  void movePlayer(Direction move);
  
  /**
   * Pick up treasure that is located in the same location with the player.
   */
  void collectTreasure();
  
  /**
   * Provide a description of the player that includes 
   * a description of what treasure the player has collected.
   * 
   * @return the description
   */
  String playerDescription();
  
  /**
   * provide a description of the player's location that includes 
   * a description of treasure in the room and the possible moves (north, east, south, west) 
   * that the player can make from their current location.
   * 
   * @return the description
   */
  String locationDescription();
  
  /**
   * Calculate the distance between two caves.
   * 
   * @param cave1 one cave
   * @param cave2 the other cave
   * @return the distance
   */
  int distance(Cave cave1, Cave cave2);
  
  /**
   * Calculate the distance between two caves using their index.
   * 
   * @param i the index of one cave
   * @param j the index of the other cave
   * @return the distance
   */
  int test(int i, int j);
  
  /**
   * To see if the player is in the end cave.
   * 
   * @return if the player is in the end cave
   */
  boolean isEnd();
  
  /**
   * Convert the information of the dungeon to a String for testing purpose. 
   * 
   * @return the String
   */
  String show();
  
  /**
   * Get the index of the starting point.
   * 
   * @return the index
   */
  int getStartIndex();
  
  /**
   * Get the index of the ending point.
   * 
   * @return the index
   */
  int getEndIndex();
  
  /**
   * Get the player.
   * 
   * @return the player
   */
  Player getPlayer();
  
  /**
   * Get the caves.
   * 
   * @return the caves
   */
  ArrayList<Cave> getCave();
  
  /**
   * Add a specified number of monsters to the dungeon.
   * 
   * @param difficulty the number of monsters
   */
  void addMonster(int difficulty);
  
  /**
   * Increase the smell level because a monster shows up.
   * 
   * @param i the index of the cave that the monster is in
   */
  void increaseSmell(int i);
  
  /**
   * Decrease the smell level because of a monster's death.
   * 
   * @param i the index of the cave that the monster is in
   */
  void decreaseSmell(int i);
  
  /**
   * Shoot an arrow to a specified direction and a specified distance.
   * 
   * @param direction the direction
   * @param distance the distance (the number of caves)
   * @return if the arrow hit the monster
   */
  boolean shootArrow(Direction direction, int distance);
  
  /**
   * Calculate the destination of an arrow.
   * 
   * @param cave the starting cave
   * @param direction the direction
   * @param distance the remaining distance (the number of caves)
   * @return the index of the destination cave
   */
  int arrowFly(Cave cave, Direction direction, int distance);
  
  /**
   * Decide if the player is eaten by the Otyugh.
   * 
   * @return if the player is eaten by the Otyugh
   */
  boolean isDead();
  
  /**
   * Decide if the player has any arrow.
   * 
   * @return if the player has any arrow
   */
  boolean haveArrow();
  
  /**
   * Get the cave the player is in.
   * 
   * @return the cave
   */
  Cave getPlayerLocation();
  
  /**
   * Get the width of the dungeon.
   * 
   * @return the width
   */
  int getM();
  
  /**
   * Get the length of the dungeon.
   * 
   * @return the length
   */
  int getN();
  
  /**
   * To see if the game is over.
   * 
   * @return if the game is over
   */
  boolean getIsOver();
  
  /**
   * Get the interconnectivity of this dungeon.
   * 
   * @return the interconnectivity
   */
  int getInterconnectivity();
  
  /**
   * To see if this dungeon is wrapping.
   * 
   * @return if this dungeon is wrapping
   */
  boolean getIsWrap();
  
  /**
   * Get the percentage of caves that have treasure in this dungeon.
   * 
   * @return the percentage of caves that have treasure
   */
  int getPercentage();
  
  /**
   * Get the number of Otyughs in this dungeon.
   * 
   * @return the number of Otyughs
   */
  int getDifficulty();
  
  /**
   * Reset the game with new settings.
   * 
   * @param m the height of the new dungeon
   * @param n the width of the new dungeon
   * @param i the interconnectivity of the new dungeon
   * @param w if the new dungeon is wrapping
   * @param p the percentage of the new dungeon
   * @param d the difficulty of the new dungeon
   */
  void reset(int m, int n, int i, boolean w, int p, int d);
  
  /**
   * Move the player from current location.
   * 
   * @param i the index of target cave
   */
  void movePlayer2(int i);
}
