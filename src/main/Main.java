package main;

import controller.Controller;
import controller.DungeonController;
import model.Dungeon;
import model.DungeonImpl;
import view.DungeonView;
import view.View;

/**
 * Run a Dungeon game interactively using a GUI.
 */
public class Main {

  /**
   * Run a Dungeon game interactively using a GUI.
   */
  public static void main(String[] args) {
    Dungeon dungeon = new DungeonImpl(4, 6);
    dungeon.generate(true, 5);
    dungeon.addTreasure(50);
    dungeon.decideStartEnd();
    dungeon.addMonster(5);
    View view = new DungeonView(dungeon);
    Controller controller = new DungeonController(dungeon, view);
    controller.playGame();
  }
}
