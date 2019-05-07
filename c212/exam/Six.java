import java.util.Scanner;

public class Six {
  public static void main(String[] args) {
    while (true) {
      System.out.print("Please enter an integer: ");
      Scanner s = new Scanner(System.in);
      String n = s.nextLine();
      try {
        int number = Integer.parseInt(n);
        System.out.println("You have entered " + n + " with square root " +
            Math.sqrt(number));
        s.close();
        break;
      } catch (Exception e) {
        System.out.println("Sorry, " + n + " not an integer.");
      }
    }
  }
}
