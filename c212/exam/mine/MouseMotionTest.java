// MouseMotionTest.java
//

import javax.swing.JFrame;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class MouseMotionTest extends JFrame implements MouseMotionListener
{
  public MouseMotionTest() {
    this.setSize(300,300);
    this.setVisible(true);
    this.addMouseMotionListener(this); // forgot this
  }
  public void mouseDragged(MouseEvent E) {
    System.out.println("Mouse dragged");
  }
  public void mouseMoved(MouseEvent E) {
    System.out.println("Mouse moved");
  }
  public static void main(String[] args)
  {
    MouseMotionTest m = new MouseMotionTest();
  }
}
