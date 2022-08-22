package model;

/**
 * Implementation of the Player.
 */
public class PlayerImpl implements Player {

  private Coordinate coordinate;
  private int arrow;
  private int diamond;
  private int ruby;
  private int sapphire;

  /**
   * Construct a Player with its coordinate.
   * 
   * @param x vertical coordinate
   * @param y horizontal coordinate
   */
  public PlayerImpl(int x, int y) {
    this.coordinate = new Coordinate(x, y);
    this.arrow = 3;
    this.diamond = 0;
    this.ruby = 0;
    this.sapphire = 0;
  }
  
  @Override
  public void moveTo(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  @Override
  public void collectTreasure(Treasure treasure) {
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

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }
  
  @Override
  public void useArrow() throws IllegalStateException {
    this.arrow--;
    if (this.arrow < 0) {
      throw new IllegalStateException("Negative arrow is not supported");
    }
  }

  @Override
  public int getArrow() {
    return this.arrow;
  }

  @Override
  public int getDiamond() {
    return this.diamond;
  }

  @Override
  public int getRuby() {
    return this.ruby;
  }

  @Override
  public int getSapphire() {
    return this.sapphire;
  }
  
}
