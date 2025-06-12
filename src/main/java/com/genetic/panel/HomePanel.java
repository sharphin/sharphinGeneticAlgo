package com.genetic.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.mainlogic.CullIndividual;
import com.genetic.mainlogic.GenerateImage;

public class HomePanel extends JPanel implements MouseListener,Runnable{
    private int x,y,now_gen =1;
    private static boolean processing;
    AppParam param;
    Rectangle start = new Rectangle(400,250,100,50);
    private int[][] image = new int[50][50];
    public HomePanel(int width,int height ,AppParam param) {
        this.param = param;
        setSize(width,height);
        addMouseListener(this);
        Thread th = new Thread(this);
        th.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(15, 15, 215, 215);
        for(int y = 0; y < 50; y++) {
            for(int x = 0; x < 50; x++) {
                g.setColor(new Color(image[y][x]));
                g.fillRect(x*6+15, y*6+15, 6, 6);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("max_individual: "+param.individual_max(), 400, 100);
        g.drawString("max_generation: "+param.generation_max(), 400, 130);
        g.drawString("survived_individual: "+param.survived_individual(), 400, 160);
        g.drawString("mutation: "+param.mutation(), 400, 190);
        g.drawString("now generation: "+now_gen, 400, 220);
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
            processing = true;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) { 
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100); // 1秒間停止
            } catch (InterruptedException e) {
                System.out.println("割り込みが発生");
            }
            if(!processing) continue;
            System.out.println("処理開始");
            String originFilePath = param.originfilepath();
            String learnFilePath = param.learnfilepath();
            int individual_max = param.individual_max();
            int generation_max = param.generation_max();
            int survived_individual = param.survived_individual();
            boolean file_out = param.file_out();
            FileEdit genf = new FileEdit();
            GenerateImage genImage = new GenerateImage();
            int generationImages[][][] = genImage.createFirstGeneration(individual_max);
            int originImage[][] = genf.readImageFile(originFilePath,-1,-1);
            if(file_out) genf.fileWriteNewGeneration(learnFilePath,1,generationImages);

            for(int gen = 2; gen <= generation_max; gen++) {
                CullIndividual cullIndividual = new CullIndividual(generationImages,survived_individual);
                cullIndividual.cull(originImage);
                generationImages = cullIndividual.createNextGeneration(individual_max, param.mutation());
                image = generationImages[0];
                if(file_out) genf.fileWriteNewGeneration(learnFilePath,gen,generationImages);
                now_gen++;
                repaint();
            }
            System.out.println("処理終了");
            processing = false;
        }
    }
}
