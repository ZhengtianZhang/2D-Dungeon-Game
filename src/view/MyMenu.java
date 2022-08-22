package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

/**
 * The menu on the view, including restart, help, setting, and quit.
 */
public class MyMenu extends JMenu {

  private static final long serialVersionUID = 1L;
  private JMenuItem easy;
  private JMenuItem medium;
  private JMenuItem hard;
  private JMenuItem setting;
  private JMenuItem quit;
  
  /**
   * Construct the menu with the text.
   * 
   * @param s the text
   */
  public MyMenu(String s) {
    super(s);
    JMenu restart = new JMenu("Restart");
    easy = new JMenuItem("Easy");
    medium = new JMenuItem("Medium");
    hard = new JMenuItem("Hard");
    setting = new JMenuItem("Setting");
    quit = new JMenuItem("Quit");
    
    restart.add(easy);
    restart.add(medium);
    restart.add(hard);
    this.add(restart);
    this.add(setting);
    this.add(quit);
  }
  
  /**
   * Set up the controller to handle click events in this Menu.
   * 
   * @param controller the controller
   */
  public void addMenuListener(Controller controller) {
    easy.addActionListener(l -> controller.handleRestart(1));
    medium.addActionListener(l -> controller.handleRestart(2));
    hard.addActionListener(l -> controller.handleRestart(3));
    setting.addActionListener(l -> controller.handleSetting());
    quit.addActionListener(l -> controller.handleQuit());
  }

}
