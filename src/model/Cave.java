package model;

import java.util.ArrayList;

/**
 * Each location in the grid represents a cave in the dungeon where a player can explore 
 * and can be connected to at most four (4) other caves: 
 * one to the north, one to the east, one to the south, and one to the west.
 */
public class Cave {

  private int index;
  private Coordinate coordinate;
  private Cave set;
  private Cave n;
  private Cave s;
  private Cave w;
  private Cave e;
  private ArrayList<Treasure> treasures;
  private boolean used;
  private boolean hasOtyugh;
  private int smell;
  private int health;
  private int type;
  private boolean visited;
  private int arrow;
  private int diamond;
  private int ruby;
  private int sapphire;
  
  /**
   * Construct a Cave with its coordinate and index.
   * 
   * @param x vertical coordinate of this cave
   * @param y horizontal coordinate of this cave
   * @param index index of this cave
   */
  public Cave(int x, int y, int index) {
    this.index = index;
    this.coordinate = new Coordinate(x, y);
    this.set = this;
    this.n = null;
    this.s = null;
    this.w = null;
    this.e = null;
    this.treasures = new ArrayList<Treasure>();
    this.used = false;
    this.hasOtyugh = false;
    this.smell = 0;
    this.health = 0;
    this.type = -1;
    this.visited = false;
    this.arrow = 0;
    this.diamond = 0;
    this.ruby = 0;
    this.sapphire = 0;
  }
  
  /**
   * Get the cave's coordinate.
   * 
   * @return the coordinate
   */
  public Coordinate getCoordinate() {
    return this.coordinate;
  }
  
  /**
   * Get the cave's index.
   * 
   * @return the index
   */
  public int getIndex() {
    return this.index;
  }
  
  /**
   * Get the set this cave belongs to.
   * 
   * @return the set
   */
  public Cave getSet() {
    return this.set;
  }
  
  /**
   * Get the cave in the north of this cave.
   * 
   * @return the cave in the north
   */
  public Cave getUp() {
    return this.n;
  }
  
  /**
   * Get the cave in the south of this cave.
   * 
   * @return the cave in the south
   */
  public Cave getDown() {
    return this.s;
  }
  
  /**
   * Get the cave in the west of this cave.
   * 
   * @return the cave in the west
   */
  public Cave getLeft() {
    return this.w;
  }
  
  /**
   * Get the cave in the east of this cave.
   * 
   * @return the cave in the east
   */
  public Cave getRight() {
    return this.e;
  }
  
  /**
   * Connect this cave with another cave.
   * 
   * @param other the other cave
   */
  public void connect(Cave other) {
    if (other.getCoordinate().getX() == this.coordinate.getX() + 1
        || other.getCoordinate().getX() < this.coordinate.getX() - 1) {
      s = other;
    }
    if (other.getCoordinate().getX() == this.coordinate.getX() - 1
        || other.getCoordinate().getX() > this.coordinate.getX() + 1) {
      n = other;
    }
    if (other.getCoordinate().getY() == this.coordinate.getY() + 1
        || other.getCoordinate().getY() < this.coordinate.getY() - 1) {
      e = other;
    }
    if (other.getCoordinate().getY() == this.coordinate.getY() - 1
        || other.getCoordinate().getY() > this.coordinate.getY() + 1) {
      w = other;
    }
  }
  
  /**
   * Put this cave into another cave's set.
   * 
   * @param other the other cave
   */
  public void sets(Cave other) {
    this.set = other.getSet();
    if (e != null) {
      if (e.getSet() != this.set) {
        e.sets(this);
      }
    }
    if (w != null) {
      if (w.getSet() != this.set) {
        w.sets(this);
      }
    }
    if (s != null) {
      if (s.getSet() != this.set) {
        s.sets(this);
      }
    }
    if (n != null) {
      if (n.getSet() != this.set) {
        n.sets(this);
      }
    }
  }
  
  /**
   * Get the treasure in this cave.
   * 
   * @return the treasure
   */
  public ArrayList<Treasure> getTreasures() {
    return treasures;
  }
  
  /**
   * Add a treasure to this cave.
   * 
   * @param treasure the treasure
   */
  public void addTreasure(Treasure treasure) {
    this.treasures.add(treasure);
    switch (treasure) {
      case DIAMOND:
        diamond++;
        break;
      case RUBY:
        ruby++;
        break;
      case SAPPHIRE:
        sapphire++;
        break;
      case ARROW:
        arrow++;
        break;
      default:
        break;
    }
  }
  
  /**
   * Get all the possible moves you can make in this cave.
   * 
   * @return the moves
   */
  public ArrayList<Direction> getPossibleMoves() {
    ArrayList<Direction> directions = new ArrayList<Direction>();
    if (e != null) {
      directions.add(Direction.EAST);
    }
    if (w != null) {
      directions.add(Direction.WEST);
    }
    if (s != null) {
      directions.add(Direction.SOUTH);
    }
    if (n != null) {
      directions.add(Direction.NORTH);
    }
    return directions;
  }
  
  /**
   * This cave has been covered.
   * The next three methods is used to prevent endless loop when finding a path.
   */
  public void use() {
    this.used = true;
  }
  
  /**
   * This cave has not been covered.
   */
  public void unuse() {
    this.used = false;
  }
  
  /**
   * To see if this cave has been covered.
   * 
   * @return if this cave has been covered
   */
  public boolean isused() {
    return this.used;
  }
  
  /**
   * Remove the treasure in this cave.
   */
  public void removeTreasure() {
    this.treasures = new ArrayList<Treasure>();
    this.arrow = 0;
    this.diamond = 0;
    this.ruby = 0;
    this.sapphire = 0;
  }
  
  /**
   * Add a Otyugh to this cave.
   */
  public void addOtyugh() {
    this.hasOtyugh = true;
    this.health = 2;
  }
  
  /**
   * To see if this cave has Otyugh.
   * 
   * @return if this cave has Otyugh
   */
  public boolean getOtyugh() {
    return this.hasOtyugh;
  }
  
  /**
   * Get the level of smell in this cave.
   * 
   * @return the level of smell
   */
  public int getSmell() {
    return this.smell;
  }
  
  /**
   * Increase the smell level of this cave.
   * 
   * @param smell the smell level increment
   */
  public void smellIncrease(int smell) {
    this.smell += smell;
  }
  
  /**
   * Decrease the smell level of this cave.
   * 
   * @param smell the smell level decrement
   */
  public void smellDecrease(int smell) {
    this.smell -= smell;
  }
  
  /**
   * Get the health of the Otyugh in this cave.
   * 
   * @return the health
   */
  public int getHealth() {
    return this.health;
  }
  
  /**
   * Decrease the health of the Otyugh in this cave by 1.
   */
  public void healthDecrease() {
    this.health--;
    if (this.health == 0) {
      this.hasOtyugh = false;
    }
  }
  
  /**
   * Decide the type of the cave.
   */
  public void decideType() {
    if (this.n == null && this.s == null && this.w == null && this.e != null) {
      this.type = 0;
    }
    if (this.n != null && this.s == null && this.w == null && this.e == null) {
      this.type = 1;
    }
    if (this.n == null && this.s == null && this.w != null && this.e == null) {
      this.type = 2;
    }
    if (this.n == null && this.s != null && this.w == null && this.e == null) {
      this.type = 3;
    }
    if (this.n == null && this.s == null && this.w != null && this.e != null) {
      this.type = 4;
    }
    if (this.n != null && this.s != null && this.w == null && this.e == null) {
      this.type = 5;
    }
    if (this.n != null && this.s == null && this.w != null && this.e == null) {
      this.type = 6;
    }
    if (this.n == null && this.s != null && this.w == null && this.e != null) {
      this.type = 7;
    }
    if (this.n == null && this.s != null && this.w != null && this.e == null) {
      this.type = 8;
    }
    if (this.n != null && this.s == null && this.w == null && this.e != null) {
      this.type = 9;
    }
    if (this.n != null && this.s == null && this.w != null && this.e != null) {
      this.type = 10;
    }
    if (this.n != null && this.s != null && this.w != null && this.e == null) {
      this.type = 11;
    }
    if (this.n == null && this.s != null && this.w != null && this.e != null) {
      this.type = 12;
    }
    if (this.n != null && this.s != null && this.w == null && this.e != null) {
      this.type = 13;
    }
    if (this.n != null && this.s != null && this.w != null && this.e != null) {
      this.type = 14;
    }
  }
  
  /**
   * Get the type of this cave.
   * 
   * @return the type
   */
  public int getType() {
    return this.type;
  }
  
  /**
   * Visit this cave.
   */
  public void visit() {
    this.visited = true;
  }
  
  /**
   * To see whether this cave has been visited.
   * 
   * @return whether this cave has been visited
   */
  public boolean getVisited() {
    return this.visited;
  }
  
  /**
   * Get the number of arrows in this cave.
   * 
   * @return the number of arrows
   */
  public int getArrow() {
    return this.arrow;
  }

  /**
   * Get the number of diamonds in this cave.
   * 
   * @return the number of diamonds
   */
  public int getDiamond() {
    return this.diamond;
  }

  /**
   * Get the number of rubies in this cave.
   * 
   * @return the number of rubies
   */
  public int getRuby() {
    return this.ruby;
  }

  /**
   * Get the number of sapphires in this cave.
   * 
   * @return the number of sapphires
   */
  public int getSapphire() {
    return this.sapphire;
  }
}
