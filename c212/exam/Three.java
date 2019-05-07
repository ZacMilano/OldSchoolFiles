import java.awt.event.*;
import javax.swing.*;

public class Three extends JFrame implements ActionListener {
  public static void main(String[] args) {
    Three three = new Three();
    Timer timer = new Timer(1000, three);
    timer.start();
  }
  public Three() {
    this.setSize(500,500);
    this.setVisible(true);
  }
  int count = 0;
  public void actionPerformed(ActionEvent e) {
    System.out.println( ++this.count );
  }
}
