// Pair.java
//

public class Pair<T, S> {
  private T first;
  private S second;

  public Pair(T first, S second) {
    this.first = first;
    this.second = second;
  }

  public String toString() {
    return "(" + this.first + ", " + this.second + ")";
  }

  public T getFirst() {
    return this.first;
  }

  public S getSecond() {
    return this.second;
  }

  public void setFirst(T first_) {
    this.first = first_;
  }

  public void setSecond(S second_) {
    this.second = second_;
  }

  public static void main(String[] args) {
    Pair<String, Double> myGPA = new Pair<String, Double>("Zac", 4.0);
    Pair<String, Double> zuGPA = new Pair<String, Double>("Zunaeed", 3.5);
    System.out.println(myGPA);
    System.out.println(zuGPA);
  }
}
