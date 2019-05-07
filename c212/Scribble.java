// Scribble.java
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scribble extends JComponent implements MouseMotionListener
{
  int RAD = 3;
  // ArrayList<Circle> drawing = new ArrayList<Circle>();
  ArrayList<ArrayList<Circle>> drawing = new ArrayList<ArrayList<Circle>>();
  ArrayList<Circle> row = new ArrayList<Circle>();

  public void mouseMoved(MouseEvent e)
  {
    // System.out.println("Mouse is being moved");
    // look for mousereleased
  }
  public void mouseDragged(MouseEvent e)
  {
    // // This is a bad place to do this!
    // Graphics g = this.getGraphics();

    // int x = e.getX(), y = e.getY();
    // int diameter = 30;
    // g.fillOval(x-diameter/2, y-diameter/2, diameter, diameter);
    // this.mouseMoved(e);

    this.row.add(new Circle(e.getX(), e.getY(), RAD));

    // if (this.drawing.size() == 0)
      // this.drawing.add(new Circle(e.getX(), e.getY(), RAD));
    // else
      // this.drawing.set(0, new Circle(e.getX(), e.getY(), RAD));

    this.repaint();
  }

  public void paintComponent(Graphics g)
  {
    // System.out.println(i++);
    // g.setColor(Color.BLUE);
    for (ArrayList<Circle> row : this.drawing)
    {
      for (int i = 0; i < this.drawing.size() - 1; i++)
      {
        Circle c = this.drawing.get(i);
        Circle cNext = this.drawing.get(i+1);
        g.drawLine(c.x, c.y,
            cNext.x, cNext.y);
      }
    }
    // g.setColor(Color.BLUE);
    // for (Circle c : this.drawing) c.draw(g);
  }

  public static void main(String[] args)
  {
    JFrame a = new JFrame();
    Scribble b = new Scribble();
    a.addMouseMotionListener(b);
    a.add(b);
    a.setVisible(true);
    a.setSize(500, 500);
  }
}
