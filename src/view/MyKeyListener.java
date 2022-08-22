package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.Controller;
import model.Direction;

/**
 * Set up the controller to handle keyboard events in the view.
 */
public class MyKeyListener extends KeyAdapter {

  Controller controller;
  int status;
  /*
   * status == 0  Regular status
   * status == 1  After pressed 's'
   * status == 2  After pressed 's' and an arrow key
   */
  Direction direction;
  
  /**
   * Construct a MyKeyListener with a controller.
   * 
   * @param controller the controller
   */
  public MyKeyListener(Controller controller) {
    this.controller = controller;
    this.status = 0;
    this.direction = null;
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (status == 0) {
        controller.handleMove(Direction.NORTH);
      }
      if (status == 1) {
        status = 2;
        this.direction = Direction.NORTH;
        controller.refresh();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if (status == 0) {
        controller.handleMove(Direction.SOUTH);
      }
      if (status == 1) {
        status = 2;
        this.direction = Direction.SOUTH;
        controller.refresh();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (status == 0) {
        controller.handleMove(Direction.WEST);
      }
      if (status == 1) {
        status = 2;
        this.direction = Direction.WEST;
        controller.refresh();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (status == 0) {
        controller.handleMove(Direction.EAST);
      }
      if (status == 1) {
        status = 2;
        this.direction = Direction.EAST;
        controller.refresh();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_P) {
      controller.handlePick();
    }
    if (e.getKeyCode() == KeyEvent.VK_S) {
      if (this.status == 0) {
        this.status = 1;
        controller.refresh();
      } else if (this.status == 1) {
        this.status = 0;
        controller.refresh();
      }
      //System.out.println(status);
    }
    if (e.getKeyCode() == KeyEvent.VK_1) {
      if (status == 2) {
        status = 0;
        controller.handleShoot(this.direction, 1);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_2) {
      if (status == 2) {
        status = 0;
        controller.handleShoot(this.direction, 2);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_3) {
      if (status == 2) {
        status = 0;
        controller.handleShoot(this.direction, 3);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_4) {
      if (status == 2) {
        status = 0;
        controller.handleShoot(this.direction, 4);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_5) {
      if (status == 2) {
        status = 0;
        controller.handleShoot(this.direction, 5);
      }
    }
  }
  
  public int getStatus() {
    return this.status;
  }
}
