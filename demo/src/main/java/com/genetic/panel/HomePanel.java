package com.genetic.panel;

import java.awt.Graphics;

import javax.swing.JPanel;

public class HomePanel extends JPanel{
    public HomePanel(int width,int height) {
      setSize(width,height);
    }
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.drawRect(5, 5, 200, 100);
    }	
}
