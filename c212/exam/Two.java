import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Two extends JFrame implements MouseListener {
  public Two() {
    this.setVisible(true);
    this.setSize(400, 400);
  }
  public void mouseEntered(MouseEvent e) { System.out.println("In..."); }
  public void mouseExited(MouseEvent e) { System.out.println("... out!"); }
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public static void main(String[] args) {
     Two b = new Two();
     b.addMouseListener(b);
  }
}

