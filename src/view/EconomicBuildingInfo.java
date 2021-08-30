package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.NotEnoughGoldException;

public class EconomicBuildingInfo extends JPanel implements ActionListener{
	
	private Game game;
	private EconomicBuilding b;
    boolean flag;
    public String cityName;
    public String type;
    public Timer timer;
    
	
	private ArrayList <EconomicBuilding> eBuildings;
	
	private Font f1=new Font("Monospaced",Font.PLAIN,40);
	
	private Font f=new Font("Monospaced",Font.PLAIN,20);
	private Font f2=new Font("Dialog",Font.PLAIN,10);
	
	private JButton build;
	private JButton upgrade;
	private JLabel Upcost;
	private JLabel level;
	private JTextPane message;
	
	private JLabel tmp;
	
	 public EconomicBuildingInfo(String s,Game game,String cityName,Color color) {
		 
		 tmp=new JLabel();
		 tmp.setLayout(new GridLayout(14,1));
		 tmp.setPreferredSize(this.getPreferredSize());
	     tmp.setBackground(color);
	     add(tmp);
	    
		 
			timer=new Timer(2000, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					removeAll();
					add(new EconomicBuildingInfo(s, game, cityName, color));	
					revalidate();
					repaint();
				}
			});
			timer.setRepeats(false);
		 for (City c : game.getPlayer().getControlledCities()) {
				if ((c.getName().toLowerCase()).equals(cityName)) {
					eBuildings=c.getEconomicalBuildings();
					
				}
			}
		 message= new JTextPane();
		 message.setPreferredSize(tmp.getPreferredSize());
		 message.setOpaque(false);
		 type=s;
		 this.cityName=cityName;
		 this.game=game;
		 JLabel x1= new JLabel(s.toUpperCase(),SwingConstants.CENTER);
		 x1.setFont(f1);
		 tmp.add(x1);
		 JLabel x2= new JLabel("--------------------",SwingConstants.CENTER);
		 x2.setFont(f);
		 tmp.add(x2);
		 
		 flag= true;
		 for (EconomicBuilding e : eBuildings) {
				if (e instanceof Farm && s.equals("Farm") || e instanceof Market && s.equals("Market")) {
					b=e;
					flag=false;
				    
			}}
		 
		 build=new JButton("Build");
		 build.setPreferredSize(new Dimension(100,50));
		 
	     upgrade=new JButton("Upgrade");
	     upgrade.setPreferredSize(new Dimension(100,50));
	     build.setBackground(Color.black);
	     build.setForeground(Color.white);
	     upgrade.setBackground(Color.black);
	     upgrade.setForeground(Color.white);
	     
	     int cost=0;
	     if(s.equals("Farm")) cost= 1000;
	     else cost=1500;

		 JLabel buildcost= new JLabel("Cost "+cost,SwingConstants.CENTER);
		buildcost.setFont(f);
	     
	     
		 
		 if(flag) {
			 tmp.add(build);
			 tmp.add(buildcost);
			
			 build.addActionListener(this);
		 }
		 else {
			 
			 level= new JLabel("Level "+b.getLevel(),SwingConstants.CENTER);
			 level.setFont(f);
			 
			 tmp.add(level);
			 
			 Upcost= new JLabel("Upgrade Cost "+b.getUpgradeCost(),SwingConstants.CENTER);
			 Upcost.setFont(f);
			 
			 tmp.add(upgrade);
			 tmp.add(Upcost);
			 
			 upgrade.addActionListener(this);
		 }
		 
		 
		tmp.add(message);
		message.setEditable(false);
	     
		 
		this.setLayout(new GridLayout());
		this.setBackground(color);
		this.revalidate();
		this.repaint();
		 
	 }

	
	public void actionPerformed(ActionEvent e) {
		
		if (flag) {
			if(e.getSource().equals(build)) {
				try {
					game.getPlayer().build(type, cityName);
					message.setText("NOW YOU HAVE A "+type.toUpperCase()+" IN "+cityName.toUpperCase()+"!");
					timer.start();
					message.setFont(f2);
					message.setForeground(new Color(44,100,23));
					message.setEditable(false);
					
					
				} catch (NotEnoughGoldException e1) {
					message.setText("YOU DON'T HAVE ENOUGH GOLD!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);
				}
			}
		}
		else {
			if(e.getSource().equals(upgrade)) {
	
				try {
					game.getPlayer().upgradeBuilding(b);
					message.setText("YOU HAVE UPDATED YOUR "+type.toUpperCase()+" TO LEVEL "+b.getLevel());
					timer.start();
					message.setFont(f2);
					message.setForeground(new Color(44,100,23));
					message.setEditable(false);
				} catch (NotEnoughGoldException e1) {
					
					message.setText("YOU DON'T HAVE ENOUGH GOLD!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);
				}
				catch (BuildingInCoolDownException e1) {
					
					message.setText("THIS BUILDING IS COOLING DOWN! YOU CAN UPGRADE IT IN THE NEXT TURN!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));	
					message.setEditable(false);
				} catch (MaxLevelException e1) {
					message.setText("YOU HAVE REACHED THE MAXIMUM LEVEL!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);

				}
				
			}
		}
		revalidate();
		repaint();
		
	}

}