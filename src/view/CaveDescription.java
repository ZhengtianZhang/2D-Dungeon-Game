package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The cave description area on the screen.
 */
public class CaveDescription extends JPanel {

  private static final long serialVersionUID = 1L;
  private JLabel[] number;

  /**
   * Construct a CaveDescription class.
   */
  public CaveDescription(int d, int r, int s, int a) {
    this.setLayout(null);
    JLabel[] treasure = new JLabel[4];
    number = new JLabel[4];
    
    treasure[0] = new JLabel(new ImageIcon("res/dungeon-images-bw/diamond.png"));
    treasure[1] = new JLabel(new ImageIcon("res/dungeon-images-bw/ruby.png"));
    treasure[2] = new JLabel(new ImageIcon("res/dungeon-images-bw/emerald.png"));
    treasure[3] = new JLabel(new ImageIcon("res/dungeon-images-bw/arrow-black.png"));
    
    for (int i = 0; i < 4; i++) {
      treasure[i].setBounds(20, 50 + i * 50, 64, 64);
      number[i] = new JLabel("0");
      number[i].setBounds(120, 50 + i * 50, 64, 64);
      number[i].setFont(new Font("Serif", Font.PLAIN, 30));
      this.add(treasure[i]);
      this.add(number[i]);
    }
    
    JLabel text = new JLabel("Treasure");
    text.setBounds(20, 0, 200, 64);
    text.setFont(new Font("Serif", Font.PLAIN, 30));
    this.add(text);
    
    number[0].setText(d + "");
    number[1].setText(r + "");
    number[2].setText(s + "");
    number[3].setText(a + "");
    
    this.setVisible(true);
  }
  
  /**
   * Refresh this area with the number of diamonds, rubies, sapphires and arrows the player has.
   * 
   * @param d the number of diamonds
   * @param r the number of rubies
   * @param s the number of sapphires
   * @param a the number of arrows
   */
  public void refresh(int d, int r, int s, int a) {
    number[0].setText(d + "");
    number[1].setText(r + "");
    number[2].setText(s + "");
    number[3].setText(a + "");
  }
}
