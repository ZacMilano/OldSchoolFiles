// Gui2.java
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Gui2 {
  JLabel l;
  JTextField t;
  public Gui2() {
    this.l = new JLabel("Hello there!");
    this.t = new JTextField();
    this.t.setPreferredSize(new Dimension(60,20));
  }
  public static void main(String[] args) {
  }
}
