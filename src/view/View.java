package view;

import controller.Controller;

/**
 * A view for the Dungeon Game: display the game and provide visual interface
 * for users.
 */
public interface View {

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
  
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();
  
  /**
   * Set up the controller to handle click events in this view.
   * 
   * @param controller the controller
   */
  void addButtonListener(Controller controller);
  
  /**
   * Set up the controller to handle keyboard events in this view.
   * 
   * @param controller the controller
   */
  void addKeyAdapter(Controller controller);
  
  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();
  
  /**
   * Make the howl sound effect.
   */
  void howl();
  
  /**
   * Make the victory sound effect.
   */
  void victory();
  
  /**
   * Make the eaten sound effect.
   */
  void eaten();
  
  /**
   * Set up the controller to handle click events in the Menu.
   * 
   * @param controller the controller
   */
  void addMenuListener(Controller controller);
  
  /**
   * Close this view.
   */
  void quit();
  
  /**
   * Expose all game settings.
   */
  void setting();
  
  /**
   * Restart the game.
   */
  void restart();
  
  /**
   * Set up the controller to handle click events on the Map.
   * 
   * @param controller the controller
   */
  void addMapListener(Controller controller);
}
