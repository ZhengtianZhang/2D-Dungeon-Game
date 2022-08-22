package view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Dungeon;

/**
 * The Map of the dungeon game with buttons.
 */
public class ButtonMap extends JPanel {

  private static final long serialVersionUID = 1L;
  private Dungeon dungeon;
  private ImageIcon[] stench;
  private ImageIcon[] status;
  private ImageIcon gate;
  private ArrayList<JLabel> map;
  private ArrayList<JLabel> smell;
  private ArrayList<JLabel> monster;
  private ArrayList<JButton> button;
  private JLabel player;
  private int m;
  private int n;
  
  /**
   * Construct a ButtonMap with a dungeon model.
   * 
   * @param dungeon the model
   */
  public ButtonMap(Dungeon dungeon) {
    this.dungeon = dungeon;
    this.m = dungeon.getM();
    this.n = dungeon.getN();
    this.setLayout(null);
    
    button = new ArrayList<JButton>();
    map = new ArrayList<JLabel>();
    smell = new ArrayList<JLabel>();
    monster = new ArrayList<JLabel>();
    
    ImageIcon[] caves = new ImageIcon[15];
    caves[0] = new ImageIcon("res/dungeon-images-bw/E.png");
    caves[1] = new ImageIcon("res/dungeon-images-bw/N.png");
    caves[2] = new ImageIcon("res/dungeon-images-bw/W.png");
    caves[3] = new ImageIcon("res/dungeon-images-bw/S.png");
    caves[4] = new ImageIcon("res/dungeon-images-bw/EW.png");
    caves[5] = new ImageIcon("res/dungeon-images-bw/NS.png");
    caves[6] = new ImageIcon("res/dungeon-images-bw/WN.png");
    caves[7] = new ImageIcon("res/dungeon-images-bw/ES.png");
    caves[8] = new ImageIcon("res/dungeon-images-bw/SW.png");
    caves[9] = new ImageIcon("res/dungeon-images-bw/NE.png");
    caves[10] = new ImageIcon("res/dungeon-images-bw/NEW.png");
    caves[11] = new ImageIcon("res/dungeon-images-bw/SWN.png");
    caves[12] = new ImageIcon("res/dungeon-images-bw/ESW.png");
    caves[13] = new ImageIcon("res/dungeon-images-bw/NES.png");
    caves[14] = new ImageIcon("res/dungeon-images-bw/NESW.png");
    
    stench = new ImageIcon[2];
    stench[0] = new ImageIcon("res/dungeon-images-bw/stench01.png");
    stench[1] = new ImageIcon("res/dungeon-images-bw/stench02.png");
    
    ImageIcon otyugh = new ImageIcon("res/dungeon-images-bw/otyugh.png");
    
    status = new ImageIcon[3];
    status[0] = new ImageIcon("res/dungeon-images-bw/player.png");
    status[1] = new ImageIcon("res/dungeon-images-bw/bow.png");
    status[2] = new ImageIcon("res/dungeon-images-bw/arrow.png");
    
    gate = new ImageIcon("res/dungeon-images-bw/gate.png");
    gate.setImage(gate.getImage().getScaledInstance(44, 64,
        Image.SCALE_DEFAULT));
    
    player = new JLabel(status[0]);
    int x = dungeon.getPlayer().getCoordinate().getX();
    int y = dungeon.getPlayer().getCoordinate().getY();
    player.setBounds(y * 64, x * 64, 64, 64);
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        button.add(new JButton());
        button.get(i * n + j).setBounds(j * 64, i * 64, 64, 64);
        button.get(i * n + j).setContentAreaFilled(false);
        button.get(i * n + j).setBorder(null);
        monster.add(new JLabel(otyugh));
        monster.get(i * n + j).setVisible(false);
        monster.get(i * n + j).setBounds(j * 64, i * 64, 64, 64);
        this.add(button.get(i * n + j));
        this.add(monster.get(i * n + j));
      }
    }
    
    this.add(player);
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        map.add(new JLabel(caves[dungeon.getCave().get(i * n + j).getType()]));
        smell.add(new JLabel());
        
        smell.get(i * n + j).setVisible(false);
        
        if (dungeon.getCave().get(i * n + j).getVisited()) {
          
          if (dungeon.getCave().get(i * n + j).getSmell() == 1) {
            smell.get(i * n + j).setIcon(stench[0]);
            smell.get(i * n + j).setVisible(true);
          }
          if (dungeon.getCave().get(i * n + j).getSmell() > 1 
              || dungeon.getCave().get(i * n + j).getOtyugh()) {
            smell.get(i * n + j).setIcon(stench[1]);
            smell.get(i * n + j).setVisible(true);
          }
        } else {
          map.get(i * n + j).setVisible(false);
        }
        
        smell.get(i * n + j).setBounds(j * 64, i * 64, 64, 64);
        map.get(i * n + j).setBounds(j * 64, i * 64, 64, 64);
        
        this.add(smell.get(i * n + j)); 
        this.add(map.get(i * n + j));
        
      }
    }
  }
  
  /**
   * Refresh this map.
   * 
   * @param myKeyListener the key listener
   */
  public void refresh(MyKeyListener myKeyListener) {
    int x = dungeon.getPlayer().getCoordinate().getX();
    int y = dungeon.getPlayer().getCoordinate().getY();
    player.setBounds(y * 64, x * 64, 64, 64);
    
    
    if (dungeon.isEnd() && !dungeon.isDead()) {
      player.setIcon(gate);
    } else {
      player.setIcon(status[myKeyListener.getStatus()]);
    }
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dungeon.getCave().get(i * n + j).getVisited()) {
          if (dungeon.getCave().get(i * n + j).getOtyugh()) {
            monster.get(i * n + j).setVisible(true);
          } else {
            monster.get(i * n + j).setVisible(false);
          }
          if (dungeon.getCave().get(i * n + j).getSmell() == 0) {
            smell.get(i * n + j).setVisible(false);
          }
          if (dungeon.getCave().get(i * n + j).getSmell() == 1) {
            smell.get(i * n + j).setIcon(stench[0]);
            smell.get(i * n + j).setVisible(true);
          }
          if (dungeon.getCave().get(i * n + j).getSmell() > 1 
              || dungeon.getCave().get(i * n + j).getOtyugh()) {
            smell.get(i * n + j).setIcon(stench[1]);
            smell.get(i * n + j).setVisible(true);
          }
          map.get(i * n + j).setVisible(true);
        }
      }
    }
  }
  
  /**
   * Set up the controller to handle click events on this Map.
   * 
   * @param controller the controller
   */
  public void addMapListener(Controller controller) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int num = i * n + j;
        button.get(i * n + j).addActionListener(l -> controller.handelMove2(num));
      }
    }
  }

}
