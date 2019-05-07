// ExTest.java
//

import java.util.Scanner;

public class ExTest
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    System.out.print("Give me an integer and I'll take the " +
        "square root of it!\t");
    String num = s.nextLine();

    try {
      double sqrt = Math.sqrt(Integer.parseInt(num));
      System.out.println(sqrt + " is the square root of " + num + "!");
      s.close();
    } catch(Exception e) { //forgot (Ex ... e)
      System.out.println(num + " isn't an integer...");
    }
  }
}
