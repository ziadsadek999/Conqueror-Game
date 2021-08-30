package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;

import engine.Game;

public class CairoView extends MainCityView implements ActionListener{
    public Game game;

     public CairoView(Game game,midScreen m, mainScreen w) throws IOException {
        super("EgyptianPyramids.jpg",game,"cairo",new Color(243,225,131),m,w);
        this.game=game;
        
        

        this.city.c.insets= new Insets(210, 100, 50, 0);
        this.city.c.gridx=1;
        this.city.c.gridy=0;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.TransparentPanel.add(barracks,this.city.c);
        this.city.c.gridx=0;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(180, 0, 70, 0);
        
        this.city.TransparentPanel.add(stable,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=0;
        this.city.c.gridheight=4;
        this.city.c.weighty=0.75;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(180, 50, 50, 10);
        this.city.TransparentPanel.add(archeryRange,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=4;
        this.city.c.gridheight=2;
        this.city.c.weighty=0.25;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(200, 20, 20, 300);
        this.city.TransparentPanel.add(army,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=0;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(320, 20, 0, 0);
        this.city.TransparentPanel.add(market,this.city.c);
        this.city.c.gridx=2;
        this.city.c.gridy=3;
        this.city.c.gridheight=3;
        this.city.c.weighty=0.5;
        this.city.c.weightx=0.5;
        this.city.c.insets= new Insets(160, 250, 0, 0);
        this.city.TransparentPanel.add(farm,this.city.c);
        
        
         
         
         army.setBackground(new Color(243,225,131,200));
         army.addActionListener(this);
         army.setBorderPainted(false);
        
        farm.setBackground(new Color(243,225,131,200));
        farm.addActionListener(this);
        farm.setBorderPainted(false);
        
        market.setBackground(new Color(243,225,131,200));
        market.addActionListener(this);
        market.setBorderPainted(false);
        
        barracks.setBackground(new Color(243,225,131,200));
        barracks.addActionListener(this);
        barracks.setBorderPainted(false);
      
        stable.setBackground(new Color(243,225,131,200));
        stable.addActionListener(this);
        stable.setBorderPainted(false);
       
        archeryRange.setBackground(new Color(243,225,131,200));
        archeryRange.addActionListener(this);
       archeryRange.setBorderPainted(false);
       
    
        this.revalidate();
        this.repaint();
        
    }
     
}