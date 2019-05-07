// ComparableTest2.java
//

import java.util.ArrayList;
import java.util.Collections;

public class ComparableTest2
{
  public static void main(String[] args)
  {
    ArrayList<Student> s = new ArrayList<Student>();
    s.add(new Student(19, "Zac"));
    s.add(new Student(21, "Zunaeed"));
    s.add(new Student(19, "Mallory"));
    s.add(new Student(20, "Other"));
    s.add(new Student(22, "You"));
    System.out.println(s.toString() + " unsorted");
    Collections.sort(s);
    System.out.println(s.toString() + " sorted");
  }
}

class Student implements Comparable<Student> {
  int age;
  String name;
  public Student(int a, String n) {
    this.age = a;
    this.name = n;
  }
  public int compareTo(Student other) {
    if (this.age < other.age)
      return -1;
    else if (this.age > other.age)
      return 1;
    else return this.name.compareTo(other.name);
  }
  public String toString() {
    return "S(" + this.age + "," + this.name + ")";
  }
}
