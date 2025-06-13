package com.genetic.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.genetic.BaseFrame;
import com.genetic.entity.AppParam;
import com.genetic.entity.GenParam;
import com.genetic.mainlogic.AreaSplit;
import com.genetic.mainlogic.GenerateThread;

public class HomePanel extends JPanel implements MouseListener,Runnable{
    private final int MAX_PIXEL = 300;
    private int x,y;
    public static int now_gen;
    AppParam param;
    Rectangle start = new Rectangle(400,250,100,50);
    private final int originImage[][];
    private int image[][];
    public static int sharedGeneratedImages[][][];
    public static boolean processing;
    public HomePanel(AppParam param,int originImage[][]) {
        sharedGeneratedImages = new int[param.individual_max()][originImage.length][originImage.length];
        this.param = param;
        this.originImage = originImage;
        image = new int[originImage.length][originImage.length];
        setSize(BaseFrame.WIDTH,BaseFrame.HEIGHT);
        addMouseListener(this);
        Thread th1 = new Thread(this);
        th1.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int revision = MAX_PIXEL/image.length;
        g.drawRect(15, 15, 215, 215);
        for(int y = 0; y < image.length; y++) {
            for(int x = 0; x < image[y].length; x++) {
                g.setColor(new Color(image[y][x]));
                g.fillRect(x*revision+15, y*revision+15, 6, 6);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("max_individual: "+param.individual_max(), 400, 100);
        g.drawString("max_generation: "+param.generation_max(), 400, 130);
        g.drawString("survived_individual: "+param.mating_max(), 400, 160);
        g.drawString("mutation: "+param.mutation(), 400, 190);
        g.drawString("now generation: "+now_gen/4, 400, 220);
        g.drawRect(start.x, start.y, start.width,start.height);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("START", 417, 280);
    }
    @Override
    public void mouseClicked(MouseEvent e){
        if(processing) return;
        x = e.getX();
        y = e.getY();
        if(start.contains(x, y)) {
            now_gen = 1;
            processing = true;
            AreaSplit split = new AreaSplit(param.thread_max(), originImage.length, originImage.length);
            GenParam genparams[] = split.responsibleArea();
            for (GenParam genparam: genparams) {
                GenerateThread gThread =  new GenerateThread(param, genparam, originImage);
                Thread th = new Thread(gThread);
                th.start();
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("割り込みが発生");
            }
            repaint();
            image = sharedGeneratedImages[0];
        }
    }
}
