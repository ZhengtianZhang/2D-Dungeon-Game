package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Cave;
import model.Direction;
import model.Dungeon;
import model.DungeonImpl;

/**
 * Class for testing the Dungeon class.
 */
public class DungeonTest {

  private Dungeon dungeon1;
  private Dungeon dungeon2;
  
  @Before
  public void setUp() {
    dungeon1 = new DungeonImpl(4, 6);
    dungeon2 = new DungeonImpl(6, 8);
    dungeon1.generate(false, 5);
    dungeon2.generate(true, 10);
    dungeon1.decideStartEnd();
    dungeon1.addTreasure(50);
    dungeon1.addMonster(5);
  }
  
  @Test
  public void testNonWrapping() {
    assertEquals("0 - 6\n" + "3 - 9\n" + "8 - 9\n" + "9 - 15\n" + "10 - 11\n" + "10 - 16\n" 
        + "13 - 19\n" + "16 - 17\n" + "16 - 22\n" + "20 - 21\n" + "21 - 22\n" + "12 - 18\n" 
        + "1 - 2\n" + "15 - 21\n" + "19 - 20\n" + "3 - 4\n" + "13 - 14\n" + "18 - 19\n" 
        + "0 - 1\n" + "4 - 5\n" + "2 - 8\n" + "7 - 8\n" + "22 - 23\n" + "6 - 7\n" + "12 - 13\n" 
        + "11 - 17\n" + "15 - 16\n" + "14 - 15\n", dungeon1.show());
  }
  
  @Test
  public void testWrapping() {
    assertEquals("1 - 2\n" + "1 - 9\n" + "3 - 11\n" + "6 - 7\n" + "6 - 14\n" + "7 - 0\n" 
        + "7 - 15\n" + "10 - 11\n" + "11 - 12\n" + "11 - 19\n" + "13 - 14\n" + "15 - 8\n" 
        + "15 - 23\n" + "16 - 17\n" + "16 - 24\n" + "19 - 27\n" + "20 - 21\n" + "20 - 28\n" 
        + "22 - 30\n" + "23 - 16\n" + "24 - 25\n" + "26 - 27\n" + "26 - 34\n" + "27 - 35\n" 
        + "30 - 31\n" + "32 - 33\n" + "32 - 40\n" + "33 - 34\n" + "35 - 43\n" + "37 - 45\n" 
        + "38 - 46\n" + "39 - 47\n" + "41 - 42\n" + "43 - 44\n" + "46 - 47\n" + "46 - 6\n" 
        + "29 - 37\n" + "23 - 31\n" + "18 - 19\n" + "0 - 1\n" + "5 - 6\n" + "4 - 12\n" 
        + "40 - 0\n" + "12 - 20\n" + "41 - 1\n" + "28 - 29\n" + "35 - 36\n" + "47 - 7\n" 
        + "14 - 15\n" + "24 - 32\n" + "45 - 5\n" + "39 - 32\n" + "31 - 24\n" + "21 - 29\n" 
        + "44 - 45\n" + "17 - 18\n" + "22 - 23\n", dungeon2.show());
  }
  
  @Test
  public void testConnect() {
    for (int i = 0; i < 24; i++) {
      for (int j = 0; j < 24; j++) {
        assertTrue(dungeon1.test(i, j) >= 0);
        assertTrue(dungeon1.test(i, j) < 23);
      }
    }
  }
  
  @Test
  public void testStartEndDistance() {
    assertTrue(dungeon1.test(dungeon1.getStartIndex(), dungeon1.getEndIndex()) >= 5);
  }
  
  @Test
  public void testTreasure() {
    int num = 0;
    for (Cave cave: dungeon1.getCave()) {
      if (cave.getTreasures().size() > 0) {
        num++;
      }
    }
    assertEquals(num, 12);
  }
  
  @Test
  public void testStartPosition() {
    int index = dungeon1.getPlayer().getCoordinate().getX() * 6 
        + dungeon1.getPlayer().getCoordinate().getY();
    assertEquals(dungeon1.getStartIndex(), index);
  }
  
  @Test
  public void testReachTheEnd() {
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.NORTH);
    dungeon1.movePlayer(Direction.WEST);
    dungeon1.movePlayer(Direction.NORTH);
    dungeon1.movePlayer(Direction.WEST);
    assertTrue(dungeon1.isEnd());
  }
  
  @Test
  public void testPlayerDescription() {
    dungeon1.collectTreasure();
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 4\n", dungeon1.playerDescription());
  }
  
  @Test
  public void testLocationDescription() {
    assertEquals("\nTreasure in the room:\n" + "ARROW\n" + "\n" 
        + "Possible Moves:\n" + "EAST\n" + "SOUTH\n", dungeon1.locationDescription());
  }
  
  @Test
  public void testMove() {
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    
    dungeon1.movePlayer(Direction.EAST);
    assertEquals(16, dungeon1.getPlayer().getCoordinate().getX() * 6 
        + dungeon1.getPlayer().getCoordinate().getY());
    
    dungeon1.movePlayer(Direction.WEST);
    assertEquals(15, dungeon1.getPlayer().getCoordinate().getX() * 6 
        + dungeon1.getPlayer().getCoordinate().getY());
    
    dungeon1.movePlayer(Direction.NORTH);
    assertEquals(9, dungeon1.getPlayer().getCoordinate().getX() * 6 
        + dungeon1.getPlayer().getCoordinate().getY());
    
    dungeon1.movePlayer(Direction.SOUTH);
    assertEquals(15, dungeon1.getPlayer().getCoordinate().getX() * 6 
        + dungeon1.getPlayer().getCoordinate().getY());
  }
  
  @Test
  public void testPickUpTreasure() {
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 3\n", dungeon1.playerDescription());
    dungeon1.collectTreasure();
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 4\n", dungeon1.playerDescription());
  }
  
  @Test
  public void testMonster() {
    assertTrue(dungeon1.getCave().get(1).getOtyugh());
    assertTrue(dungeon1.getCave().get(15).getOtyugh());
    assertTrue(dungeon1.getCave().get(17).getOtyugh());
    assertTrue(dungeon1.getCave().get(21).getOtyugh());
  }
  
  @Test
  public void testInitialArrow() {
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 3\n", dungeon1.playerDescription());
  }
  
  @Test
  public void testPickUpArrow() {
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 3\n", dungeon1.playerDescription());
    dungeon1.collectTreasure();
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 4\n", dungeon1.playerDescription());
  }
  
  @Test
  public void testUseArrow() {
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 3\n", dungeon1.playerDescription());
    dungeon1.shootArrow(Direction.EAST, 1);
    assertEquals("The treasure you have collected:\n" + "Diamond: 0\n" 
        + "Ruby: 0\n" + "Sapphire: 0\n" + "Arrow: 2\n", dungeon1.playerDescription());
  }
  
  @Test
  public void testShootingArrow() {
    assertEquals(13, dungeon1.arrowFly(dungeon1.getCave().get(12), Direction.EAST, 1));
  }
  
  @Test(expected = IllegalStateException.class)
  public void testNoArrow() {
    dungeon1.shootArrow(Direction.EAST, 1);
    dungeon1.shootArrow(Direction.EAST, 1);
    dungeon1.shootArrow(Direction.EAST, 1);
    dungeon1.shootArrow(Direction.EAST, 1);
  }

  @Test
  public void testSmell1() {
    assertEquals(0, dungeon1.getCave().get(12).getSmell());
    assertEquals(1, dungeon1.getCave().get(13).getSmell());
    assertEquals(3, dungeon1.getCave().get(14).getSmell());
  }
  
  @Test
  public void testSmell2() {
    dungeon1.shootArrow(Direction.EAST, 3);
    dungeon1.shootArrow(Direction.EAST, 3);
    assertEquals(0, dungeon1.getCave().get(12).getSmell());
    assertEquals(0, dungeon1.getCave().get(13).getSmell());
    assertEquals(1, dungeon1.getCave().get(14).getSmell());
  }
  
  @Test
  public void testEaten1() {
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    assertTrue(dungeon1.isDead());
  }
  
  @Test
  public void testEaten2() {
    dungeon1.shootArrow(Direction.EAST, 3);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    assertFalse(dungeon1.isDead());
  }
  
  @Test
  public void testEaten3() {
    dungeon1.shootArrow(Direction.EAST, 3);
    dungeon1.shootArrow(Direction.EAST, 3);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    assertFalse(dungeon1.isDead());
  }
  
}
