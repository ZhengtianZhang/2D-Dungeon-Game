package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import controller.Controller;
import model.Direction;
import model.Dungeon;

/**
 * Implementation of the View.
 */
public class DungeonView extends JFrame implements View {

  private static final long serialVersionUID = 1L;
  private Dungeon dungeon;
  private JButton upButton;
  private JButton downButton;
  private JButton leftButton;
  private JButton rightButton;
  private JPanel panel;
  private JScrollPane scrollpane;
  private PlayerDescription playerDescription;
  private CaveDescription caveDescription;
  private ButtonMap map;
  private MyKeyListener myKeyListener;
  private MyMenu menu;

  /**
   * Construct a DungeonView with a dungeon model.
   * 
   * @param dungeon the dungeon model
   */
  public DungeonView(Dungeon dungeon) {
    super("Dungeon Game");
    this.dungeon = dungeon;
    this.setSize(1000, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.myKeyListener = null;
    
    panel = new JPanel();
    panel.setLayout(null);
    
    playerDescription = new PlayerDescription();
    playerDescription.setBounds(0, 0, 200, 250);
    panel.add(playerDescription);
    
    caveDescription = new CaveDescription(
        dungeon.getPlayerLocation().getDiamond(), 
        dungeon.getPlayerLocation().getRuby(), 
        dungeon.getPlayerLocation().getSapphire(), 
        dungeon.getPlayerLocation().getArrow());
    caveDescription.setBounds(200, 0, 200, 250);
    panel.add(caveDescription);
    
    map = new ButtonMap(dungeon);
    map.setPreferredSize(new Dimension(1000, 1000));
    scrollpane = new JScrollPane(map, 
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollpane.setBounds(400, 0, 500, 500);
    panel.add(scrollpane);
    
    JMenuBar menubar = new JMenuBar();
    menu = new MyMenu("Menu");
    menubar.add(menu);
    this.setJMenuBar(menubar);
    
    upButton = new JButton("Up");
    downButton = new JButton("Down");
    leftButton = new JButton("Left");
    rightButton = new JButton("Right");
    
    upButton.setBounds(100, 300, 100, 40);
    downButton.setBounds(100, 400, 100, 40);
    leftButton.setBounds(0, 350, 100, 40);
    rightButton.setBounds(200, 350, 100, 40);
    
    
    
    panel.add(upButton);
    panel.add(downButton);
    panel.add(leftButton);
    panel.add(rightButton);
    
    this.setContentPane(panel);
    this.resetFocus();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.playerDescription.refresh(dungeon.getPlayer().getDiamond(), 
        dungeon.getPlayer().getRuby(), 
        dungeon.getPlayer().getSapphire(), 
        dungeon.getPlayer().getArrow());
    this.caveDescription.refresh(dungeon.getPlayerLocation().getDiamond(), 
        dungeon.getPlayerLocation().getRuby(), 
        dungeon.getPlayerLocation().getSapphire(), 
        dungeon.getPlayerLocation().getArrow());
    this.map.refresh(myKeyListener);
    this.resetFocus();
  }

  @Override
  public void addButtonListener(Controller controller) {
    upButton.addActionListener(l -> controller.handleMove(Direction.NORTH));
    downButton.addActionListener(l -> controller.handleMove(Direction.SOUTH));
    leftButton.addActionListener(l -> controller.handleMove(Direction.WEST));
    rightButton.addActionListener(l -> controller.handleMove(Direction.EAST));
  }

  @Override
  public void addKeyAdapter(Controller controller) {
    this.myKeyListener = new MyKeyListener(controller);
    this.addKeyListener(this.myKeyListener);
    this.scrollpane.addKeyListener(this.myKeyListener);
  }
  
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void howl() {
    Sound sound = new Sound();
    sound.play("res/sound-effect/Howl.wav");
  }

  @Override
  public void victory() {
    Sound sound = new Sound();
    sound.play("res/sound-effect/Victory.wav");
    
  }

  @Override
  public void eaten() {
    Sound sound = new Sound();
    sound.play("res/sound-effect/Eaten.wav");
  }

  @Override
  public void addMenuListener(Controller controller) {
    this.menu.addMenuListener(controller);
  }

  @Override
  public void quit() {
    System.exit(0);
  }

  @Override
  public void setting() {
    Setting setting = new Setting(dungeon.getM(), 
        dungeon.getN(), dungeon.getInterconnectivity(), dungeon.getIsWrap(), 
        dungeon.getPercentage(), dungeon.getDifficulty());
    setting.setVisible(true);
  }

  @Override
  public void restart() {
    this.remove(scrollpane);
    map = new ButtonMap(dungeon);
    map.setPreferredSize(new Dimension(1000, 1000));
    scrollpane = new JScrollPane(map, 
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollpane.setBounds(400, 0, 500, 500);
    panel.add(scrollpane);
    this.revalidate();
    this.repaint();
    this.refresh();
  }

  @Override
  public void addMapListener(Controller controller) {
    this.map.addMapListener(controller);
  }
}
