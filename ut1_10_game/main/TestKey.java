package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class TestKey {
  public static void main(String[] argv) throws Exception {
    JFrame myJFrame = new JFrame();

    myJFrame.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
          System.out.println("Up Arrrow-Key is pressed!");
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
          System.out.println("Down Arrrow-Key is pressed!");
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
          System.out.println("Left Arrrow-Key is pressed!");
        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
        System.out.println("Right Arrrow-Key is pressed!");
        }
      }
    });

    myJFrame.setVisible(true);
  }
}