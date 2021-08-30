package view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import engine.City;
import engine.Game;
import units.Army;

public class MainCityView extends JPanel implements ActionListener{
    public CityView city;
    public JPanel menu;
    
    public  Game game;
    public String cityName;
    public Color color;

    public JButton army;
    public JButton farm;
    public JButton market;
    public JButton barracks;
    public JButton stable;
    public JButton archeryRange;
    
    public JPanel menuTop;
    public JButton backToMap;
    
    public midScreen mid;
    public mainScreen w;
    
    
    
    public MainCityView(String s, Game game, String cityName, Color color,midScreen m ,mainScreen w) throws IOException {
         this.w=w;
        menuTop= new JPanel();
        menuTop.setBackground(color);
        menuTop.setLayout(new BorderLayout());
        backToMap= new JButton("Back To The Map");
        backToMap.addActionListener(this);
        backToMap.setBackground(Color.black);
        backToMap.setForeground(Color.white);
        backToMap.setPreferredSize(new Dimension(220, 40));
        menuTop.add(backToMap,BorderLayout.NORTH);
        
        mid=m;
        
   
        this.cityName=cityName;
        this.game=game;
        this.color=color;
        
        
        
        setLayout(new BorderLayout());
        city= new CityView(s);
        menu= new JPanel();
        
        JPanel tmp= new JPanel();
        
        tmp.setLayout(new GridLayout());
        add(tmp,BorderLayout.EAST);
        add(menuTop);
        
        tmp.setPreferredSize(new Dimension(1100, 100000));
        tmp.add(city);
    
        
        menu.setBackground(color);
        menu.setLayout(new GridLayout());
        menuTop.add(menu);
        

        army= new JButton("Armies");
         farm= new JButton("Farm");
         market= new JButton("Market");
         barracks= new JButton("Barracks");
         stable= new JButton("Stable");
         archeryRange= new JButton("Archery Range");
         
        
        
        
        
            
       this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==backToMap) {     
            
            mid.removeAll();
            mid.add(mid.left,BorderLayout.WEST);
            mid.add(mid.right,BorderLayout.EAST);
            mid.revalidate();
            mid.repaint();
            }
        else {
            
        
         if(e.getSource()==farm) {     
             menuTop.remove(menu);
            menu=new EconomicBuildingInfo("Farm", game, cityName,color);
            menuTop.add(menu);
             revalidate();
             repaint();
            }
        else if(e.getSource()==market) {
            menuTop.remove(menu);
            menu=new EconomicBuildingInfo("Market", game, cityName,color);
            menuTop.add(menu);
             revalidate();
            repaint();
            }
        else if(e.getSource()==barracks) {
            menuTop.remove(menu);
            menu=new MilitaryBuildingInfo("Barracks", game, cityName,color);
            menuTop.add(menu);
            revalidate();
            repaint();
            }
        else if(e.getSource()==stable) {
            menuTop.remove(menu);
            menu=new MilitaryBuildingInfo("Stable", game, cityName,color);
            menuTop.add(menu);
            revalidate();
            repaint();
            }
        else if(e.getSource()==archeryRange) {
            menuTop.remove(menu);
            menu=new MilitaryBuildingInfo("ArcheryRange", game, cityName,color);
            menuTop.add(menu);
            revalidate();
            repaint();
            }
        else if(e.getSource()==army) {
            menuTop.remove(menu);
            menu=new armyinfo(game, cityName,color);
            menuTop.add(menu);
            revalidate();
             repaint();
            }}
        revalidate();
        repaint();
    }
}