package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;


public class MilitaryBuildingInfo extends JPanel implements ActionListener{
	private Game game;
	private MilitaryBuilding b;
    boolean flag;
    public String cityName;
    public String type;
    public String type2;
    public Timer timer;
    
	
	private ArrayList <MilitaryBuilding> mBuildings;
	
	private Font f1=new Font("Monospaced",Font.PLAIN,30);
	
	private Font f=new Font("Monospaced",Font.PLAIN,20);
	private Font f2=new Font("Dialog",Font.PLAIN,10);
	
	private JButton build;
	private JButton upgrade;
	private JButton recruit;
	private JLabel Upcost;
	private JLabel level;
	private JLabel recost;
	private JTextPane message;
	
	private JLabel tmp;
	
	 public MilitaryBuildingInfo(String s,Game game,String cityName,Color color) {
		 
		 tmp=new JLabel();
		 tmp.setLayout(new GridLayout(14,1));
		 tmp.setPreferredSize(this.getPreferredSize());
	     tmp.setBackground(color);
	     add(tmp);
	    
	    		 
			timer=new Timer(2000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeAll();
					add(new MilitaryBuildingInfo(s, game, cityName, color));	
					revalidate();
					repaint();
				}
			});
			timer.setRepeats(false);
		 
		 for (City c : game.getPlayer().getControlledCities()) {
				if ((c.getName().toLowerCase()).equals(cityName)) {
					mBuildings= c.getMilitaryBuildings();
				}
			}
		 message= new JTextPane();
		 message.setOpaque(false);
		 message.setPreferredSize(tmp.getPreferredSize());
		 type=s;
		 this.cityName=cityName;
		 this.game=game;
		 JLabel x1= new JLabel(s.toUpperCase(),SwingConstants.CENTER);
		 x1.setPreferredSize(tmp.getPreferredSize());
		 x1.setFont(f1);
		 tmp.add(x1);
		 JLabel x2= new JLabel("--------------------",SwingConstants.CENTER);
		 x2.setPreferredSize(tmp.getPreferredSize());
		 x2.setFont(f);
		 tmp.add(x2);
		 flag= true;
		 for (MilitaryBuilding e : mBuildings) {
				if (e instanceof ArcheryRange && type.equals("ArcheryRange")
						|| e instanceof Barracks && type.equals("Barracks")
						|| e instanceof Stable && type.equals("Stable")) {
					b=e;
					flag=false;
					
				}
					
			}
		
		 int cost=0;
		 build=new JButton("Build");
		 build.setPreferredSize(new Dimension(100,50));
	     upgrade=new JButton("Upgrade");
	     upgrade.setPreferredSize(new Dimension(100,50));
	     recruit=new JButton("Recruit");
	     recruit.setPreferredSize(new Dimension(100,50));
	     switch (type.toLowerCase()) {
			case "archeryrange":
				recruit=new JButton("Recruit Archer");
				cost= 1500;
				type2="Archer";
				break;
			case "barracks":
				recruit=new JButton("Recruit Infantry");
				cost=2000;
				type2="Infantry";
				break;
			case "stable":
				recruit=new JButton("Recruit Cavalry");
				cost=2500;
				type2= "Cavalry";
				break;
	     }
	     build.setBackground(Color.black);
	     build.setForeground(Color.white);
	     upgrade.setBackground(Color.black);
	     upgrade.setForeground(Color.white);
	     recruit.setBackground(Color.black);
	     recruit.setForeground(Color.white);
	     
	
	    
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
			 
			 recost= new JLabel("Recruitment Cost "+b.getRecruitmentCost(),SwingConstants.CENTER);
			 recost.setFont(f);
			 
			 tmp.add(upgrade);
			 tmp.add(Upcost);
			 tmp.add(recruit);
			 tmp.add(recost);
			 
			 recruit.addActionListener(this);
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
					message.setText("NOW YOU HAVE "+type.toUpperCase()+" IN "+cityName.toUpperCase()+"!");
					
					tmp.add(message);
					timer.start();
					message.setFont(f2);
					message.setForeground(new Color(44,100,23));
					message.setEditable(false);
					
					
				} catch (NotEnoughGoldException e1) {
					
					tmp.add(message);
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
					
					tmp.add(message);
					timer.start();
					message.setFont(f2);
					message.setForeground(new Color(44,100,23));
					message.setEditable(false);
				} catch (NotEnoughGoldException e1) {
					
					tmp.add(message);
					message.setText("YOU DON'T HAVE ENOUGH GOLD!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);
				}

				catch (BuildingInCoolDownException e1) {
					
					tmp.add(message);
					message.setText("THIS BUILDING IS COOLING DOWN! YOU CAN UPGRADE IT IN THE NEXT TURN");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));	
					message.setEditable(false);
				} catch (MaxLevelException e1) {
					
					tmp.add(message);
					message.setText("YOU HAVE REACHED THE MAXIMUM LEVEL!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);

				}
				
			}
			else {
			
				try {
					game.getPlayer().recruitUnit(type2,cityName);
					
					message.setText("RECRUITMENT DONE SUCCESSFULLY!");
					
					tmp.add(message);
					timer.start();
					message.setFont(f2);
					message.setForeground(new Color(44,100,23));
					message.setEditable(false);
				} catch (NotEnoughGoldException e1) {
					
					tmp.add(message);
					message.setText("YOU DON'T HAVE ENOUGH GOLD!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);
				}

				catch (BuildingInCoolDownException e1) {
					
					tmp.add(message);
					message.setText("THIS BUILDING IS COOLING DOWN! YOU HAVE TO WAIT TILL THE NEXT TURN!");
					message.setFont(f2);
					message.setForeground(new Color(102,0,0));
					message.setEditable(false);
				} catch (MaxRecruitedException e1) {
					
					tmp.add(message);
					message.setText("YOU HAVE REACHED THE MAXIMUM RECRUITMENT FOR THIS TURN!");
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