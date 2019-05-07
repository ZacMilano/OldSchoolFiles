import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Nine extends JFrame implements ActionListener {
  JTextField t;
  JLabel l;
  public Nine() {
    this.t = new JTextField();
    t.setPreferredSize(new Dimension(60, 20));
    this.l = new JLabel("Welcome");
    JButton b = new JButton("Push me!");
    b.addActionListener(this);
    JPanel p = new JPanel(); // brings the flow layout with it
    p.add(this.l);
    p.add(b);
    p.add(this.t);
    this.add(p);
    this.setVisible(true);
    this.setSize(400, 200);
  }
  public void actionPerformed(ActionEvent e) {
    this.l.setText(this.t.getText());
  }
  public static void main(String[] args) {
    JFrame a = new Nine();
  }
}
