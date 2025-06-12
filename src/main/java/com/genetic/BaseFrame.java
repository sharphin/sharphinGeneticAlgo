package com.genetic;

import javax.swing.JFrame;

import com.genetic.entity.AppParam;
import com.genetic.panel.HomePanel;

public class BaseFrame extends JFrame {
    public BaseFrame(AppParam param) {
        super.add(new HomePanel(100, 100,param));
        setVisible(true);
        setBounds(50,50,700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
