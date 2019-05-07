import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

public class Eight extends JComponent {
  ArrayList<Circle> circles = new ArrayList<Circle>();
  public Eight() {
    for (int i = 0; i < 30; i++) {
      this.circles.add(new Circle((int)(Math.random()*400),
                                  (int)(Math.random()*400),
                                  (int)(Math.random() * 10 + 30),
                                  new Color((float)Math.random(),
                                            (float)Math.random(),
                                            (float)Math.random())));
    }
  }
  public void paintComponent(Graphics g) {
    g.drawOval(40, 40, 100, 100); // likewise, not needed, just a sanity check
    for (Circle c : this.circles)
      c.draw(g);
    System.out.println("I am being called." +
        this.circles); // for illustration only
  }
  public static void main(String[] args) {
    JFrame a = new JFrame();
    a.add( new Eight() );
    a.setSize(400, 400);
    a.setVisible(true);
  }
}

// import java.awt.Color;
// import java.awt.Graphics;

/*public*/ class Circle {
  int x, y;
  int radius;
  Color c;
  public String toString() {
    return this.c + " circle (" + this.x + ", " +
      this.y + ") with radius + " + this.radius;
  } // I needed this for debugging
  public Circle(int x, int y, int r, Color c) {
    this.x = x;
    this.y = y;
    this.radius = r;
    this.c = c;
  }
  public void draw(Graphics g) {
    g.setColor(this.c);
    g.fillOval(this.x - this.radius,
               this.y - this.radius,
               this.radius * 2,
               this.radius * 2);
    g.setColor(Color.BLACK);
    g.drawOval(this.x - this.radius,
               this.y - this.radius,
               this.radius * 2,
               this.radius * 2);

  }
}

// this looks much like the first stage of your project
