package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The dungeon should be able to represented on a 2-D grid.
 * There should be a path from every cave in the dungeon to every other cave in the dungeon.
 * Each dungeon can be constructed with a degree of interconnectivity.
 * Not all dungeons "wrap" from one side to the other.
 * One cave is randomly selected as the start and one cave is randomly selected to be the end. 
 * The path between the start and the end locations should be at least of length 5.
 */
public class DungeonImpl implements Dungeon {

  private int m;
  private int n;
  private ArrayList<Cave> location;
  private ArrayList<Tunnel> tunnels;
  private ArrayList<Tunnel> necessary;
  private ArrayList<Tunnel> leftover;
  private Player player;
  private Coordinate start;
  private Coordinate end;
  private boolean isOver;
  private int interconnectivity;
  private boolean isWrap;
  private int percentage;
  private int difficulty;
  
  /**
   * Construct a DungeonImpl with its size.
   * 
   * @param m width of the dungeon
   * @param n length of the dungeon
   */
  public DungeonImpl(int m, int n) {
    this.m = m;
    this.n = n;
    location = new ArrayList<Cave>();
    tunnels = new ArrayList<Tunnel>();
    necessary = new ArrayList<Tunnel>();
    leftover = new ArrayList<Tunnel>();
    player = new PlayerImpl(0, 0);
    start = null;
    end = null;
    isOver = false;
    this.isWrap = false;
    this.interconnectivity = 0;
    this.percentage = 0;
    this.difficulty = 0;
  }
  
  @Override
  public void generate(boolean iswrap, int interconnectivity) {
    this.isWrap = iswrap;
    this.interconnectivity = interconnectivity;
    Random r = new Random(100);
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        location.add(new Cave(i, j, i * n + j));
      }
    }
    
    int num = 0;
    
    if (iswrap) {
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          tunnels.add(new Tunnel(location.get(i * n + j),
              location.get(i * n + (j + 1) % n)));
          tunnels.add(new Tunnel(location.get(i * n + j), location.get((i + 1) % m * n + j)));
        }
      }
      
      num = 2 * m * n;
    }
    
    if (!iswrap) {
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (j != n - 1) {
            tunnels.add(new Tunnel(location.get(i * n + j),
                location.get(i * n + (j + 1) % n)));
          }
          if (i != m - 1) {
            tunnels.add(new Tunnel(location.get(i * n + j), location.get((i + 1) % m * n + j)));
          }
        }
      }
      
      num = 2 * (m - 1) * (n - 1) + m + n - 2;
    }
    
    for (int i = 0; i < num; i++) {
      int a = r.nextInt(num);
      tunnels.add(tunnels.get(a));
      tunnels.remove(a);
    }
    
    for (Tunnel tunnel: tunnels) {
      if (tunnel.getCave1().getSet() != tunnel.getCave2().getSet()) {
        tunnel.getCave2().connect(tunnel.getCave1());
        tunnel.getCave1().connect(tunnel.getCave2());
        tunnel.getCave2().sets(tunnel.getCave1());
        necessary.add(tunnel);
      } else {
        leftover.add(tunnel);
      }
    }
    
    for (int i = 0; i < interconnectivity; i++) {
      if (leftover.size() == 0) {
        break;
      }
      int a = r.nextInt(leftover.size());
      necessary.add(leftover.get(a));
      leftover.remove(a);
    }
    
    for (Tunnel tunnel: necessary) {
      tunnel.getCave2().connect(tunnel.getCave1());
      tunnel.getCave1().connect(tunnel.getCave2());
      //System.out.println(tunnel.getCave1().getIndex() + " - " + tunnel.getCave2().getIndex());
    }
    
    for (Cave cave: location) {
      cave.decideType();
      //System.out.println(cave.getType());
    }
  }
  
  @Override
  public void addTreasure(int percentage) {
    this.percentage = percentage;
    Random r = new Random(50);
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for (int i = 0; i < m * n; i++) {
      temp.add(i);
    }
    int num = m * n * percentage / 100;
    for (int i = 0; i < num; i++) {
      int index = r.nextInt(temp.size());
      int times = r.nextInt(3) + 1;
      for (int j = 0; j < times; j++) {
        int type = r.nextInt(4);
        if (type == 0) {
          location.get(temp.get(index)).addTreasure(Treasure.DIAMOND);
        }
        if (type == 1) {
          location.get(temp.get(index)).addTreasure(Treasure.RUBY);
        }
        if (type == 2) {
          location.get(temp.get(index)).addTreasure(Treasure.SAPPHIRE);
        }
        if (type == 3) {
          location.get(temp.get(index)).addTreasure(Treasure.ARROW);
        }
      }
      temp.remove(index);
    }
    /*
    System.out.println(location.size() + "  " + num);
    for (int i = 0; i < location.size(); i++) {
      System.out.println(location.get(i).getTreasures().size());
    }*/
  }

  @Override
  public void decideStartEnd() {
    Random r = new Random(50);
    this.start = new Coordinate(r.nextInt(m), r.nextInt(n));
    player = new PlayerImpl(start.getX(), start.getY());
    int startindex = start.getX() * n + start.getY();
    location.get(startindex).visit();
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for (int i = 0; i < m * n; i++) {
      if (test(startindex, i) >= 5) {
        temp.add(i);
      }
    }
    int endindex = temp.get(r.nextInt(temp.size()));
    this.end = location.get(endindex).getCoordinate();
    //System.out.println(startindex + "\n" + endindex);
  }
  
  @Override
  public void movePlayer(Direction move) {
    int index = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    for (Direction direction: location.get(index).getPossibleMoves()) {
      if (direction == move) {
        if (move == Direction.NORTH) {
          player.moveTo(location.get(index).getUp().getCoordinate());
        }
        if (move == Direction.EAST) {
          player.moveTo(location.get(index).getRight().getCoordinate());
        }
        if (move == Direction.SOUTH) {
          player.moveTo(location.get(index).getDown().getCoordinate());
        }
        if (move == Direction.WEST) {
          player.moveTo(location.get(index).getLeft().getCoordinate());
        }
      }
    }
    int newindex = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    location.get(newindex).visit();
    //System.out.println(newindex);
  }

  @Override
  public void collectTreasure() {
    int index = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    for (Treasure treasure: location.get(index).getTreasures()) {
      player.collectTreasure(treasure);
    }
    location.get(index).removeTreasure();
  }

  @Override
  public String playerDescription() {
    String s = "The treasure you have collected:\n";
    s += String.format("Diamond: %d\n", player.getDiamond());
    s += String.format("Ruby: %d\n", player.getRuby());
    s += String.format("Sapphire: %d\n", player.getSapphire());
    s += String.format("Arrow: %d\n", player.getArrow());
    return s;
  }

  @Override
  public String locationDescription() {
    String s = "\n";
    int index = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    
    if (location.get(index).getSmell() == 1) {
      s += "You smell something terrible nearby\n\n";
    }
    if (location.get(index).getSmell() > 1) {
      s += "You smell something EXTREMELY terrible nearby\n\n";
    }
    
    s += "Treasure in the room:\n";
    
    for (Treasure treasure: location.get(index).getTreasures()) {
      s += treasure + "\n";
    }
    s += "\nPossible Moves:\n";
    for (Direction direction: location.get(index).getPossibleMoves()) {
      s += direction + "\n";
    }
    return s;
  }

  @Override
  public int distance(Cave cave1, Cave cave2) {
    if (cave1.equals(cave2)) {
      return 0;
    }
    if (cave1.getUp() == cave2 || cave1.getDown() == cave2
        || cave1.getLeft() == cave2 || cave1.getRight() == cave2) {
      return 1;
    }
    int d = 2147483646;
    if (cave1.getUp() != null) {
      if (!cave1.getUp().isused()) {
        cave1.use();
        if (this.distance(cave1.getUp(), cave2) < d) {
          d = this.distance(cave1.getUp(), cave2);
        }
        cave1.unuse();
      }
    }
    if (cave1.getDown() != null) {
      if (!cave1.getDown().isused()) {
        cave1.use();
        if (this.distance(cave1.getDown(), cave2) < d) {
          d = this.distance(cave1.getDown(), cave2);
        }
        cave1.unuse();
      }
    }
    if (cave1.getLeft() != null) {
      if (!cave1.getLeft().isused()) {
        cave1.use();
        if (this.distance(cave1.getLeft(), cave2) < d) {
          d = this.distance(cave1.getLeft(), cave2);
        }
        cave1.unuse();
      }
    }
    if (cave1.getRight() != null) {
      if (!cave1.getRight().isused()) {
        cave1.use();
        if (this.distance(cave1.getRight(), cave2) < d) {
          d = this.distance(cave1.getRight(), cave2);
        }
        cave1.unuse();
      }
    }
    return d + 1;
  }

  @Override
  public int test(int i, int j) {
    return distance(location.get(i), location.get(j));
  }

  @Override
  public boolean isEnd() {
    if (player.getCoordinate().isSame(end)) {
      this.isOver = true;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String show() {
    String s = "";
    for (Tunnel tunnel: necessary) {
      s += String.format("%d - %d\n", tunnel.getCave1().getIndex(), tunnel.getCave2().getIndex());
    }
    return s;
  }

  @Override
  public int getStartIndex() {
    return start.getX() * n + start.getY();
  }

  @Override
  public int getEndIndex() {
    return end.getX() * n + end.getY();
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public ArrayList<Cave> getCave() {
    return location;
  }

  @Override
  public void addMonster(int difficulty) {
    this.difficulty = difficulty;
    Random r = new Random(50);
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for (int i = 0; i < m * n; i++) {
      temp.add(i);
    }
    temp.remove(this.getStartIndex());
    location.get(this.getEndIndex()).addOtyugh();
    increaseSmell(this.getEndIndex());
    temp.remove(this.getEndIndex());
    for (int i = 0; i < difficulty - 1; i++) {
      int index = r.nextInt(temp.size());
      //System.out.print(temp.get(index) + " ");
      location.get(temp.get(index)).addOtyugh();
      increaseSmell(temp.get(index));
      temp.remove(index);
    }
    //System.out.print("\n");
    /*for (int i = 0; i < location.size(); i++) {
      System.out.println(location.get(i).getSmell());
      
      if (location.get(i).getOtyugh()) {
        System.out.println(i);
      }
    }*/
  }

  @Override
  public void increaseSmell(int i) {
    for (int j = 0; j < location.size(); j++) {
      if (test(i, j) == 1) {
        location.get(j).smellIncrease(2);
      }
      if (test(i, j) == 2) {
        location.get(j).smellIncrease(1);
      }
    }
  }

  @Override
  public void decreaseSmell(int i) {
    for (int j = 0; j < location.size(); j++) {
      if (test(i, j) == 1) {
        location.get(j).smellDecrease(2);
      }
      if (test(i, j) == 2) {
        location.get(j).smellDecrease(1);
      }
    }
  }
  
  @Override
  public boolean shootArrow(Direction direction, int distance) {
    player.useArrow();
    int index = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    int landing = arrowFly(location.get(index), direction, distance);
    if (location.get(landing).getOtyugh()) {
      location.get(landing).healthDecrease();
      if (!location.get(landing).getOtyugh()) {
        decreaseSmell(landing);
      }
      return true;
    }
    return false;
  }

  @Override
  public int arrowFly(Cave cave, Direction direction, int distance) {
    if (distance == 0) {
      return cave.getIndex();
    }
    if (direction == Direction.EAST) {
      if (cave.getRight() == null) {
        return cave.getIndex();
      } else {
        return arrowFly(cave.getRight(), direction, distance - 1);
      }
    }
    if (direction == Direction.NORTH) {
      if (cave.getUp() == null) {
        return cave.getIndex();
      } else {
        return arrowFly(cave.getUp(), direction, distance - 1);
      }
    }
    if (direction == Direction.WEST) {
      if (cave.getLeft() == null) {
        return cave.getIndex();
      } else {
        return arrowFly(cave.getLeft(), direction, distance - 1);
      }
    }
    if (direction == Direction.SOUTH) {
      if (cave.getDown() == null) {
        return cave.getIndex();
      } else {
        return arrowFly(cave.getDown(), direction, distance - 1);
      }
    }
    return 0;
  }

  @Override
  public boolean isDead() {
    int index = player.getCoordinate().getX() * n + player.getCoordinate().getY();
    if (location.get(index).getOtyugh()) {
      if (location.get(index).getHealth() == 2) {
        this.isOver = true;
        return true;
      } else {
        Random r = new Random(50);
        int d = r.nextInt(2);
        if (d == 0) {
          this.isOver = true;
          return true;
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean haveArrow() {
    return player.getArrow() > 0;
  }

  @Override
  public Cave getPlayerLocation() {
    return location.get(player.getCoordinate().getX() * n + player.getCoordinate().getY());
  }

  @Override
  public int getM() {
    return this.m;
  }

  @Override
  public int getN() {
    return this.n;
  }

  @Override
  public boolean getIsOver() {
    return this.isOver;
  }

  @Override
  public int getInterconnectivity() {
    return this.interconnectivity;
  }

  @Override
  public boolean getIsWrap() {
    return this.isWrap;
  }

  @Override
  public int getPercentage() {
    return this.percentage;
  }

  @Override
  public int getDifficulty() {
    return this.difficulty;
  }

  @Override
  public void reset(int m, int n, int i, boolean w, int p, int d) {
    this.m = m;
    this.n = n;
    location = new ArrayList<Cave>();
    tunnels = new ArrayList<Tunnel>();
    necessary = new ArrayList<Tunnel>();
    leftover = new ArrayList<Tunnel>();
    player = new PlayerImpl(0, 0);
    start = null;
    end = null;
    isOver = false;
    
    this.generate(w, i);
    this.addTreasure(p);
    this.decideStartEnd();
    this.addMonster(d);
  }

  @Override
  public void movePlayer2(int i) {
    player.moveTo(location.get(i).getCoordinate());
    location.get(i).visit();
  }
}
