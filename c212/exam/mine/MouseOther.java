// MouseOther.java
//

import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseOther extends JFrame implements MouseListener {
  public MouseOther() {
    this.setSize(300,300);
    this.setVisible(true);
    this.addMouseListener(this);
  }
  // forgot this
  public void mouseEntered(MouseEvent e) { System.out.println("entered"); }
  // forgot this
  public void mouseExited(MouseEvent e) { System.out.println("exited"); }

  public void mousePressed(MouseEvent e) { System.out.println("pressed"); }
  public void mouseReleased(MouseEvent e) { System.out.println("released"); }
  public void mouseClicked(MouseEvent e) { System.out.println("clicked"); }
  public static void main(String[] args) {
    MouseOther m = new MouseOther();
  }
}
