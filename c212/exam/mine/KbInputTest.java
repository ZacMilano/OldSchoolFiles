// KbInputTest.java
//

import java.awt.event.KeyEvent;
// import java.awt.event.KeyEventListener; // had this at first!
import java.awt.event.KeyListener;
import javax.swing.JFrame; //forgot this

public class KbInputTest extends JFrame implements KeyListener {
  public void keyPressed(KeyEvent e) {
    System.out.println("key pressed");
  }
  public void keyReleased(KeyEvent e) {
    System.out.println("key released");
  }
  public void keyTyped(KeyEvent e) {
    System.out.println("key typed");
  }
  public KbInputTest() {
    this.setSize(200,200);
    this.setVisible(true);
    this.addKeyListener(this); //forgot this
  }

  public static void main(String[] args)
  {
    KbInputTest k = new KbInputTest();
  }
}
