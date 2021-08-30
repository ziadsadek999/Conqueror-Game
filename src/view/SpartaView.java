package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

import engine.Game;

public class SpartaView extends MainCityView implements ActionListener{
    public Game game;

    public SpartaView(Game game,midScreen m, mainScreen w) throws IOException {
        super("sparta.png",game,"sparta",new Color(150,120,83),m,w);
        this.game=game;

        this.city.c.insets= new Insets(200, 200, 80, 60);
        this.city.c.gridx=0;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.TransparentPanel.add(army,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(0, 100, 280, 100);
        
        this.city.TransparentPanel.add(stable,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=1;
        this.city.c.gridheight=4;
        this.city.c.weighty=0.75;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(150, 50, 150, 150);
        this.city.TransparentPanel.add(archeryRange,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=0;
        this.city.c.gridheight=2;
        this.city.c.weighty=0.25;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(100, 70, 200, 300);
        this.city.TransparentPanel.add(barracks,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=1;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(0, 250, 0, 0);
        this.city.TransparentPanel.add(market,this.city.c);
        this.city.c.gridx=1;
        this.city.c.gridy=0;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(150, 100, 100, 100);
        this.city.TransparentPanel.add(farm,this.city.c);
        
         
         army.setBackground(new Color(150,120,83,200));
         army.addActionListener(this);
         army.setBorderPainted(false);
        
        farm.setBackground(new Color(150,120,83,200));
        farm.addActionListener(this);
        farm.setBorderPainted(false);
        
        market.setBackground(new Color(150,120,83,200));
        market.addActionListener(this);
        market.setBorderPainted(false);
        
        barracks.setBackground(new Color(150,120,83,200));
        barracks.addActionListener(this);
        barracks.setBorderPainted(false);
      
        stable.setBackground(new Color(150,120,83,200));
        stable.addActionListener(this);
        stable.setBorderPainted(false);
       
        archeryRange.setBackground(new Color(150,120,83,200));
        archeryRange.addActionListener(this);
       archeryRange.setBorderPainted(false);
       
    
       
        
        
        this.revalidate();
        this.repaint();
    }

}