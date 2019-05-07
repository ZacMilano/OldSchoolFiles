import javax.swing.JFrame;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class One extends JFrame implements MouseMotionListener {
  public One() {
    this.setVisible(true);
    this.setSize(400, 400);
  }
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse being moved...");
  }
  public void mouseDragged(MouseEvent e) {
    int x = e.getX(), y = e.getY();
    System.out.println("(" + x + ", " + y + ")");
  }
  public static void main(String[] args) {
     One a = new One();
     a.addMouseMotionListener(a);
  }
}

