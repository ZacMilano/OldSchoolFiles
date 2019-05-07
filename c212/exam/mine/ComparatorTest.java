// ComparatorTest.java
//

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

public class ComparatorTest {
  public static void main(String[] args) {
    ArrayList<GPA> a = new ArrayList<GPA>();
    a.add(new GPA(4.0, "Zac"));
    a.add(new GPA(0.0, "Zac's dog"));
    a.add(new GPA(4.0, "Gandhi"));
    a.add(new GPA(2.9, "Jake"));
    a.add(new GPA(3.0, "John"));

    System.out.println("UNSORTED");
    System.out.println(a.toString());
    Collections.sort(a, new Ascending());
    System.out.println("Ascending");
    System.out.println(a.toString());
    Collections.sort(a, new Descending());
    System.out.println("Descending");
    System.out.println(a.toString());
  }
}

class GPA {
  double num;
  String student;
  public GPA(double n, String s) {
    this.num = n;
    this.student = s;
  }
  public String toString() {
    return "GPA(" + this.num + "," + this.student + ")";
  }
}

class Ascending implements Comparator<GPA> {
  public int compare(GPA a, GPA b) {
    if (a.num < b.num)
      return -1;
    else if (a.num > b.num)
      return 1;
    else return a.student.compareTo(b.student);
  }
}

class Descending implements Comparator<GPA> {
  public int compare(GPA a, GPA b) {
    if (a.num < b.num)
      return 1;
    else if (a.num > b.num)
      return -1;
    else return a.student.compareTo(b.student);
  }
}
