// Circle.java
//

import javax.swing.*;
import java.awt.*;

public class Circle
{
  int x, y, r;

  public Circle(int x_, int y_, int r_)
  {
    this.x = x_;
    this.y = y_;
    this.r = r_;
  }

  public void draw(Graphics g)
  {
    g.drawOval(this.x - this.r, this.y - this.r, 2*this.r, 2*this.r);
  }
}
