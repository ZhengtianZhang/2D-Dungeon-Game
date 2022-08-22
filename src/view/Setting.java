package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The window that expose all game settings.
 *
 */
public class Setting extends JFrame {

  private static final long serialVersionUID = 1L;
  
  /**
   * Construct a Setting with the game settings.
   * 
   * @param m the height of the new dungeon
   * @param n the width of the new dungeon
   * @param i the interconnectivity of the new dungeon
   * @param w if the new dungeon is wrapping
   * @param p the percentage of the new dungeon
   * @param d the difficulty of the new dungeon
   */
  public Setting(int m, int n, int i, boolean w, int p, int d) {
    super("Setting");
    this.setSize(300, 250);
    this.setLayout(null);
    this.setResizable(false);
    
    int width = getWidth();
    
    JLabel size = new JLabel("Size: " + (m + "") + " * " + (n + ""));
    size.setBounds(10, 0, width - 20, 20);
    JLabel inter = new JLabel("Interconnectivity: " + (i + ""));
    inter.setBounds(10, 40, width - 20, 20);
    JLabel wrap = new JLabel("Wrapping: " + w);
    wrap.setBounds(10, 80, width - 20, 20);
    JLabel per = new JLabel("Percentage of caves that have treasure: " + (p + ""));
    per.setBounds(10, 120, width - 20, 20);
    JLabel dif = new JLabel("The number of Otyughs: " + (d + ""));
    dif.setBounds(10, 160, width - 20, 20);
    this.add(size);
    this.add(inter);
    this.add(wrap);
    this.add(per);
    this.add(dif);
  }

}
