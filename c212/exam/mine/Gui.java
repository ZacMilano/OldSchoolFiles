// Gui.java
//

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension; // location of this
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame implements ActionListener {
  JTextField text;
  JLabel label;
  public Gui() {
    this.setSize(300,200);
    this.setVisible(true);

    JPanel p = new JPanel();
    this.text = new JTextField();
    this.text.setPreferredSize(new Dimension(60,20));
    this.label = new JLabel("Enter some text!");
    JButton b = new JButton("Push me!");
    b.addActionListener(this); // do this!!!
    p.add(this.text);
    p.add(this.label);
    p.add(b);
    this.add(p);
  }
  public void actionPerformed(ActionEvent e) {
    this.label.setText(this.text.getText());
  }
  public static void main(String[] args)
  {
    Gui g = new Gui();
  }
}
