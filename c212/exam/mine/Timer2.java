// Timer2.java
//

// import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.Timer; // comes from here
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Timer2 extends JFrame implements ActionListener {
  public Timer2() {
    this.setSize(200,200);
    this.setVisible(true);
  }
  int count = 0;
  public void actionPerformed(ActionEvent e) {
    System.out.println(++this.count);
  }
  public static void main(String[] args) {
    Timer2 t2 = new Timer2();
    Timer t = new Timer(1000, t2);
    t.start();
  }
}
