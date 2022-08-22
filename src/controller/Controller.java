package controller;

import model.Direction;

/**
 * Represents a Controller for the Dungeon game: 
 * use the input from the user to navigate the player through the dungeon;
 * output clues about the nearby caves and other relevant aspects of the current game state.
 */
public interface Controller {

  /**
   * Execute a single Dungeon game. When the game is over, the playGame method ends.
   */
  void playGame();
  
  /**
   * Handle a move command.
   * 
   * @param direction the direction of this move
   */
  void handleMove(Direction direction);
  
  /**
   * Handle a pick command.
   */
  void handlePick();
  
  /**
   * Handle a shoot command.
   * 
   * @param direction the direction of this shoot
   * @param distance the distance of this shoot
   */
  void handleShoot(Direction direction, int distance);
  
  /**
   * Refresh the screen.
   */
  void refresh();
  
  /**
   * Handle a quit command.
   */
  void handleQuit();
  
  /**
   * Handle a setting command.
   */
  void handleSetting();
  
  /**
   * Handle a restart command.
   * 
   * @param difficulty the difficulty of the new game
   */
  void handleRestart(int difficulty);
  
  /**
   * Handle a move command.
   * 
   * @param i the index of the target cave.
   */
  void handelMove2(int i);
}
