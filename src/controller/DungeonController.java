package controller;

import model.Direction;
import model.Dungeon;
import view.View;

/**
 * Implementation of the Controller.
 */
public class DungeonController implements Controller {

  Dungeon dungeon;
  View view;
  
  /**
   * Construct a dungeon controller with a model and a view.
   * 
   * @param dungeon the model
   * @param view the view
   */
  public DungeonController(Dungeon dungeon, View view) {
    this.dungeon = dungeon;
    this.view = view;
  }
  
  @Override
  public void playGame() {
    view.addButtonListener(this);
    view.addKeyAdapter(this);
    view.addMenuListener(this);
    view.addMapListener(this);
    view.makeVisible();
  }

  @Override
  public void handleMove(Direction direction) {
    if (!dungeon.getIsOver()) {
      dungeon.movePlayer(direction);
      view.refresh();
      if (dungeon.isDead()) {
        view.eaten();
        return;
      }
      if (dungeon.isEnd()) {
        view.victory();
      }
    }
  }

  @Override
  public void handlePick() {
    if (!dungeon.getIsOver()) {
      dungeon.collectTreasure();
      view.refresh();
    }
  }

  @Override
  public void handleShoot(Direction direction, int distance) {
    if (!dungeon.getIsOver()) {
      if (dungeon.shootArrow(direction, distance)) {
        view.howl();
      }
      view.refresh();
    }
  }

  @Override
  public void refresh() {
    view.refresh();
  }

  @Override
  public void handleQuit() {
    view.quit();
  }

  @Override
  public void handleSetting() {
    view.setting();
  }

  @Override
  public void handleRestart(int difficulty) {
    if (difficulty == 1) {
      dungeon.reset(4, 6, 5, false, 50, 2);
    }
    if (difficulty == 2) {
      dungeon.reset(4, 6, 5, true, 50, 5);
    }
    if (difficulty == 3) {
      dungeon.reset(5, 6, 6, true, 20, 6);
    }
    view.restart();
    view.addMapListener(this);
  }

  @Override
  public void handelMove2(int i) {
    if (dungeon.test(dungeon.getPlayerLocation().getIndex(), i) == 1) {
      if (!dungeon.getIsOver()) {
        dungeon.movePlayer2(i);
        view.refresh();
        if (dungeon.isDead()) {
          view.eaten();
          return;
        }
        if (dungeon.isEnd()) {
          view.victory();
        }
      }
    }
  }
}
