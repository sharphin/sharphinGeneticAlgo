package com.genetic;

import javax.swing.JFrame;

import com.genetic.entity.AppParam;
import com.genetic.panel.HomePanel;

public class BaseFrame extends JFrame {
    public BaseFrame(AppParam param,int originImage[][]) {
        super.add(new HomePanel(param,originImage));
        setVisible(true);
        setBounds(50,50,700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
