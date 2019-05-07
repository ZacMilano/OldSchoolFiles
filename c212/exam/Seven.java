import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Seven extends JFrame implements KeyListener {
  public static void main(String[] args) {
    Seven seven = new Seven();
    seven.setVisible(true);
    seven.setSize(400, 400);
    seven.addKeyListener(seven);
  }
  public void keyPressed(KeyEvent e) { }
  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) {
    System.out.println("Howdy " + e);
  }
}

