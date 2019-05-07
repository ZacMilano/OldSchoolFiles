// CircleDraw.java
//

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JComponent; //forgot about this
// import javax.swing.Graphics; // oops
import java.awt.Graphics;
import java.awt.Color;

public class CircleDraw extends JComponent {  // I thought it needed to
  ArrayList<Circle> circles = new             // implement something, and
    ArrayList<Circle>();                      // extend JFrame
  public CircleDraw() {
    for (int i = 0; i < 20; i++) {
      this.circles.add(
          new Circle(
            (int)(Math.random()*400),
            (int)(Math.random()*400),
            (int)(Math.random()*10 + 30),
            new Color(
              (float)Math.random(),
              (float)Math.random(),
              (float)Math.random()
              )
            )
          );
    }
  }
  public static void main(String[] args) {
    JFrame j = new JFrame();
    j.setSize(400,400);
    j.setVisible(true);
    j.add(new CircleDraw());
  }
  public void paintComponent(Graphics g) {
    for (Circle c : circles) {
      c.draw(g);
    }
  }
}

class Circle {
  int x, y, rad;
  Color c; //forgot about random color
  public Circle(int x_, int y_, int rad_, Color c_) {
    this.x = x_;
    this.y = y_;
    this.rad = rad_;
    this.c = c_;
  }

  public void draw(Graphics g) {
    g.setColor(this.c);
    g.fillOval(this.x - this.rad, this.y - this.rad, // subtract rad from x,y
        2*this.rad, 2*this.rad);
  }
}
