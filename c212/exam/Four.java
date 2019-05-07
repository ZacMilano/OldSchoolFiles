// import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;

/*public*/
class Player implements Comparable<Player>{
  private String name;
  private int points;
  public Player(String name, int points) {
    this.name = name;
    this.points = points;
  }
  public String toString() {
    return // this.getClass().getName() +
      this.name + ":" + this.points;
  }
  public int compareTo(Player other) { // ascending
    if (this.points > other.points) return 1;
    else if (this.points < other.points) return -1;
    else return this.name.compareTo(other.name);
  }
}

// import java.util.ArrayList;
// import java.util.Collections;

public class Four {
  public static void main(String[] args) {
    ArrayList<Player> players = new ArrayList<Player>();
    players.add(new Player("Aiyun"  , 14));
    players.add(new Player("Xinya"  , 23));
    players.add(new Player("Xinhui" , 19));
    players.add(new Player("Xiaohui", 19));
    System.out.println( players );
    Collections.sort( players );
    System.out.println( players );
  }
}
