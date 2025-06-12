package com.genetic.panel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.genetic.entity.AppParam;

public class HomePanel extends JPanel implements MouseListener{

  private int x,y,now_gen;
  private boolean processing;
  AppParam param;
  Rectangle start = new Rectangle(300,250,100,50);
  Rectangle previous_gen = new Rectangle();
  Rectangle next_gen = new Rectangle();
  public HomePanel(int width,int height ,AppParam param) {
    this.param = param;
    setSize(width,height);
    addMouseListener(this);
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawRect(15, 15, 200, 200);
    g.drawString("max_individual: "+param.individual_max(), 300, 100);
    g.drawString("max_generation: "+param.generation_max(), 300, 130);
    g.drawString("survived_individual: "+param.survived_individual(), 300, 160);
    g.drawString("mutation: "+param.mutation(), 300, 190);
    g.drawString("now generation: "+now_gen, 300, 220);
    g.drawRect(start.x, start.y, start.width,start.height);
    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.drawString("START", 317, 280);
  }
  @Override
  public void mouseClicked(MouseEvent e){
    if(processing) return;
    x = e.getX();
    y = e.getY();
    if(start.contains(x, y)) {
      System.out.println("処理開始");
    }
    if(previous_gen.contains(x, y)) {
      now_gen--;
      System.out.println("--");
      repaint();
    }
    if(next_gen.contains(x, y)) {
      now_gen++;
      System.out.println("++");
      repaint();
    }
  }
  @Override
  public void mouseEntered(MouseEvent e){
  }
  @Override
  public void mouseExited(MouseEvent e) {
  }
  @Override
  public void mousePressed(MouseEvent e) {
  }
  @Override
  public void mouseReleased(MouseEvent e) { 
  }
}
