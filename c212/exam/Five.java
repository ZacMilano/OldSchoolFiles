import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Five {
  public static void main(String[] args) {
    Student.main(args);
  }
}

/*public*/
class Student {
  String name;
  int age; // not private so I can simplify a little the comparisons
  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String toString() {
    return "Student(" + this.name + ", " + this.age + ")";
  }
  public static void main(String[] args) {
    ArrayList<Student> students = new ArrayList<Student>();
    students.add( new Student("Laura" , 12) );
    students.add( new Student("Larry" , 13) );
    students.add( new Student("Leslie", 11) );
    students.add( new Student("Les", 11) );
    System.out.println( students );
    Collections.sort( students, new Ascending() );
    System.out.println( students );
    Collections.sort( students, new Descending() );
    System.out.println( students );

  }
}

// import java.util.Comparator;

/*public*/
class Ascending implements Comparator<Student> {
  public int compare(Student a, Student b) {
    if (a.age < b.age) return -1;
    else if (a.age > b.age) return 1;
    else return a.name.compareTo(b.name);
  }
}

// import java.util.Comparator;

/*public*/
class Descending implements Comparator<Student> {
  public int compare(Student a, Student b) {
    if (a.age < b.age) return 1;
    else if (a.age > b.age) return -1;
    else return (-1) * a.name.compareTo(b.name);
  }
}
