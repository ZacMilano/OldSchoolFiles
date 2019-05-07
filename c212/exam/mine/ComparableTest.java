// ComparableTest.java
//

import java.util.ArrayList;
import java.util.Collections; // this

public class ComparableTest {
  public static void main(String[] args) {
    ArrayList<Student> a = new ArrayList<Student>();
    a.add(new Student(19, "Zac"));
    a.add(new Student(20, "Bill"));
    a.add(new Student(4, "Zunaeed"));
    a.add(new Student(21, "Mallory"));
    a.add(new Student(20, "Abe"));

    System.out.println(a.toString());
    Collections.sort(a);
    System.out.println(a.toString());
  }
}

class Student implements Comparable<Student> { // Forgot <Student>
  int age;
  String name;
  public Student(int a, String s) {
    this.age = a;
    this.name = s;
  }
  public int compareTo(Student other) {
    if (this.age < other.age) {
      return -1;
    } else if (this.age > other.age) {
      return 1;
    } else {
      return this.name.compareTo(other.name);
    }
  }
  public String toString() { // forgot this method
    return "S(" + this.age + "," + this.name + ")";
  }
}
