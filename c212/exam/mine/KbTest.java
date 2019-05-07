// KbTest.java
//

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KbTest extends JFrame implements KeyListener
{
  public KbTest() {
    this.setSize(200,200);
    this.setVisible(true);
    this.addKeyListener(this);
  }
  public void keyPressed(KeyEvent e) {
    System.out.println("a key was pressed");
  }
  public void keyReleased(KeyEvent e) {
    System.out.println("a key was released");
  }
  public void keyTyped(KeyEvent e) {
    System.out.println("a key was typed");
  }

  public static void main(String[] args)
  {
    KbTest k = new KbTest();
  }
}
