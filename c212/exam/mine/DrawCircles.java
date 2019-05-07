import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawCircles extends JPanel {
  ArrayList<Circle> circles = new ArrayList<Circle>();
  public DrawCircles() {
    for (int i = 0; i < 20; i++) {
      circles.add(
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
  public void paintComponent(Graphics g) {
    for (Circle c : circles)
      c.draw(g);
  }
  public static void main(String[] args) {
    JFrame j = new JFrame();
    j.setSize(400,400);
    j.setVisible(true);
    DrawCircles d = new DrawCircles();
    j.add(d);
  }
}

class Circle {
  int x, y, r;
  Color c;
  public Circle(int x_, int y_, int r_, Color c_) {
    this.x = x_;
    this.y = y_;
    this.r = r_;
    this.c = c_;
  }
  public void draw(Graphics g) {
    g.setColor(this.c);
    g.fillOval(this.x - this.r, this.y - this.r, 2*this.r, 2*this.r);
  }
}
