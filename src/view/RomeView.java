package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

import engine.Game;

public class RomeView extends  MainCityView implements ActionListener {
    
    private Game game;
    
    public RomeView(Game game,midScreen m, mainScreen w) throws IOException {
        super("rome.jpg",game,"rome",new Color(152,133,114),m,w);
        this.game=game;
        

        this.city.c.insets= new Insets(100, 0, 180, 380);
        this.city.c.gridx=1;
        this.city.c.gridy=0;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.TransparentPanel.add(army,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(0, 200, 280, 0);
        
        this.city.TransparentPanel.add(stable,this.city.c);
        this.city.c.gridx=1;
        this.city.c.gridy=1;
        this.city.c.gridheight=4;
        this.city.c.weighty=0.75;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(180, 150, 50, 10);
        this.city.TransparentPanel.add(archeryRange,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=0;
        this.city.c.gridheight=2;
        this.city.c.weighty=0.25;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(100, 70, 200, 300);
        this.city.TransparentPanel.add(barracks,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=0;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(300, 0, 0, 60);
        this.city.TransparentPanel.add(market,this.city.c);
        this.city.c.gridx=1;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(100, 200, 150, 0);
        this.city.TransparentPanel.add(farm,this.city.c);
        
         
         
         army.setBackground(new Color(152,133,114,200));
         army.addActionListener(this);
         army.setBorderPainted(false);
        
        farm.setBackground(new Color(152,133,114,200));
        farm.addActionListener(this);
        farm.setBorderPainted(false);
        
        market.setBackground(new Color(152,133,114,200));
        market.addActionListener(this);
        market.setBorderPainted(false);
        
        barracks.setBackground(new Color(152,133,114,200));
        barracks.addActionListener(this);
        barracks.setBorderPainted(false);
      
        stable.setBackground(new Color(152,133,114,200));
        stable.addActionListener(this);
        stable.setBorderPainted(false);
       
        archeryRange.setBackground(new Color(152,133,114,200));
        archeryRange.addActionListener(this);
       archeryRange.setBorderPainted(false);
       
    
       
        
        this.revalidate();
        this.repaint();
    }

}