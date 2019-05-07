// ComparatorTest2.java
//

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class ComparatorTest2
{
  public static void main(String[] args)
  {
    ArrayList<Gpa2> gpas = new ArrayList<Gpa2>();
    gpas.add(new Gpa2(4.0, "Zac"));
    gpas.add(new Gpa2(0.0, "Zac's dog"));
    gpas.add(new Gpa2(3.0, "Mallory"));
    gpas.add(new Gpa2(1.0, "Zunaeed"));
    gpas.add(new Gpa2(2.9, "Billy"));

    System.out.println(gpas.toString() + " unsorted");
    Collections.sort(gpas, new Inc());
    System.out.println(gpas.toString() + " increasing");
    Collections.sort(gpas, new Dec());
    System.out.println(gpas.toString() + " decreasing");
  }
}

class Gpa2 {
  double num;
  String student;
  public Gpa2(double n, String s) {
    this.num = n;
    this.student = s;
  }
  public String toString() {
    return "GPA(" + this.num + "," + this.student + ")";
  }
}

class Inc implements Comparator<Gpa2> {
  public int compare(Gpa2 a, Gpa2 b) {
    if (a.num < b.num)
      return -1;
    else if (a.num > b.num)
      return 1;
    else
      return a.student.compareTo(b.student);
  }
}
class Dec implements Comparator<Gpa2> {
  public int compare(Gpa2 a, Gpa2 b) {
    if (a.num < b.num)
      return 1;
    else if (a.num > b.num)
      return -1;
    else
      return a.student.compareTo(b.student);
  }
}
