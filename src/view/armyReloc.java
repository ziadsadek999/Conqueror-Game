package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import org.hamcrest.core.IsInstanceOf;

import engine.City;
import engine.Game;
import exceptions.MaxCapacityException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class armyReloc extends JPanel  {
	public Army defarmy;
	public ArrayList <Army> armyhere;
    public Color color;
    public Game game;
    public String cityName;
    public Unit unit;
    
    
   private Font f=new Font("Monospaced",Font.PLAIN,20);
	private Font f1=new Font("Monospaced",Font.PLAIN,15);


	
	public JButton x2;
   public JLabel a;
   public JLabel y;
   public JLabel y1;
   public JLabel y2;
    public armyReloc(Unit u,Game game,String cityName,Color color) {
    	this.game=game;
		armyhere=new ArrayList<Army>();
		this.game=game;
		this.cityName= cityName;
		this.unit=u;
		

		for (City c : game.getPlayer().getControlledCities()) {
			if ((c.getName().toLowerCase()).equals(cityName.toLowerCase())) {
				defarmy= c.getDefendingArmy();
				
			}
		}
		armyhere.add(defarmy);
		for(Army a: game.getPlayer().getControlledArmies()) {
			if((a.getCurrentLocation().toLowerCase()).equals(cityName.toLowerCase()) && !a.equals(defarmy)){
				armyhere.add(a);
			}
		}
		
		int n= (armyhere.size())<5?5:(armyhere.size());
		this.setLayout(new GridLayout(n,1));
		
		
		
	    int i=1;
		for (Army army : armyhere) {
			 a= new JLabel();
			 a.setLayout(new GridLayout(3,1));
		     y= new JLabel();
			 y.setFont(f1);
			 y.setForeground(color.WHITE);
			 a.add(y);
		     y1= new JLabel();
			 y1.setFont(f1);
			 y1.setForeground(color.WHITE);
			 a.add(y1);
			 y2= new JLabel();
			 y2.setFont(f1);
			 y2.setForeground(color.WHITE);
			 a.add(y2);

			if(army.equals(defarmy)) {
				y.setText(cityName.toUpperCase()+" DEFENDING ARMY");
				y1.setText("CURRENT STATUS:"+ defarmy.getCurrentStatus());
				y2.setText("MAX UNITS:"+ defarmy.getMaxToHold());
			}
			else {
			y.setText("ARMY "+i++);
			y1.setText("CURRENT STATUS:"+ army.getCurrentStatus());
			y2.setText("MAX UNITS:"+ army.getMaxToHold());}
            
			x2=new JButton();
			x2.setLayout(new GridLayout(1,1));
			x2.add(a);
			x2.setBackground(Color.black);
			add(x2);
			x2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
	
					 removeAll();
					   setLayout(new GridLayout(6,1));
				       try {
						army.relocateUnit(unit);
						 JTextPane v= new JTextPane();
						 v.setOpaque(false);
					     v.setText("UNIT RELOCATED SUCCESSFULLY!");
					     JButton z = new JButton("back to armies");
                         z.setBackground(color.black);
                         z.setForeground(color.white);
                         JLabel o=new JLabel();
						   o.setLayout(new GridLayout(2,1));
						   o.add(z);
					     z.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									removeAll();
									 setLayout(new GridLayout());
									 add(new armyinfo(game, cityName, color));
									 revalidate();
									 repaint();
									
								}
							});
					     v.setEditable(false);
						   v.setForeground(new Color(44,100,23));
						   v.setFont(f1);
						   add(v);
						   add(o);
					} catch (MaxCapacityException e1) {
						 JTextPane v= new JTextPane();
						 v.setOpaque(false);
					     v.setText("THIS ARMY HAS REACHED IT'S MAXIMUM CAPACITY");
					     v.setEditable(false);
						v.setFont(f1);
						   v.setForeground(new Color(102,0,0));
						   add(v);
						   JLabel o=new JLabel();
						   o.setLayout(new GridLayout(2,1));
						   add(o);
						   JButton b= new JButton("back");
						   b.setBackground(Color.black);
						   b.setForeground(Color.white);
						   o.add(b);
						   b.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								removeAll();
								setLayout(new GridLayout());
								add(new armyReloc(u, game, cityName, color));
								revalidate();
								repaint();
								
							}
						});
						
					}
						 
					   
					   revalidate();
						repaint();
				   }
			});
			
		}
		
		
		this.setBackground(color);
		this.revalidate();
		this.repaint();
	}
	
	
	
		
		
	}