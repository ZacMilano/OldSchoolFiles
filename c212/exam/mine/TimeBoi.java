// TimeBoi.java
//

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;

// forgot to implement actionlistener
public class TimeBoi extends JFrame implements ActionListener {
  public TimeBoi() {
    this.setSize(200,200);
    this.setVisible(true);
  }
  public static void main(String[] args) {
    TimeBoi t = new TimeBoi();
    Timer ti = new Timer(1000, t); // forgot constructor
    ti.start(); // forgot this
  }
  int count = 0; // forgot this
  public void actionPerformed(ActionEvent e) { // forgot this
    System.out.println(++this.count);
  }
}
