package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import engine.City;
import engine.Game;
import units.Army;
import units.Unit;

public class armyinfo extends JPanel implements ActionListener {

	public Army defarmy;
	public ArrayList<Unit> defarmyu;
	public ArrayList <ArrayList<Unit>> armyhereu;
	public ArrayList <Army> armyhere;
	public String cityName;
    public Color color;
    public JPanel tmp;
    public Game game;
    public JButton initiate;
    public City city;
    
   private Font f=new Font("Monospaced",Font.PLAIN,20);
	private Font f1=new Font("Monospaced",Font.PLAIN,15);
	

	private JButton cArmy;
	private JLabel x1;
	
	
	public armyinfo(Game game,String cityName,Color color) {
		this.game=game;
		armyhereu=new ArrayList<ArrayList<Unit>>();
		armyhere=new ArrayList<Army>();
		tmp= new JPanel();
		tmp.setLayout(new FlowLayout());
		tmp.setBackground(color);
		add(tmp);
		
		for (City c : game.getPlayer().getControlledCities()) {
			if ((c.getName().toLowerCase()).equals(cityName.toLowerCase())) {
				city=c;
				defarmyu= c.getDefendingArmy().getUnits();
				defarmy= c.getDefendingArmy();
				
			}
		}
		for(Army a: game.getPlayer().getControlledArmies()) {
			if((a.getCurrentLocation().toLowerCase()).equals(cityName.toLowerCase()) && !a.equals(defarmy)){
				armyhereu.add(a.getUnits());
				armyhere.add(a);
			}
		}
		
		
		this.cityName=cityName;
		this.color=color;
		cArmy= new JButton(cityName.toUpperCase()+" DEFENDING ARMY");
		cArmy.setBackground(Color.black);
	    cArmy.setForeground(Color.white);
	    cArmy.addActionListener(this);
	    cArmy.setPreferredSize(new Dimension(250,80));
	    initiate = new JButton("INITIATE ARMY");
	    initiate.setBackground(new Color(102,0,0));
	    initiate.setForeground(Color.white);
	    initiate.addActionListener(this);
	    initiate.setPreferredSize(new Dimension(250,50));
	   tmp.add(cArmy);
	   tmp.add(initiate);
	   JLabel v = new JLabel("----------------------------------------------------",SwingConstants.CENTER);
	   v.setPreferredSize(new Dimension(tmp.getPreferredSize().width,25));
	   tmp.add(v);
		
		if(armyhere.size()!=0) {
		x1= new JLabel("Armies in "+cityName.toUpperCase());
		x1.setFont(f);
	    tmp.add(x1);
	    
	    
		 for(int i=0; i<armyhere.size();i++) {
			 JButton tmp1=new JButton("Army "+(i+1));
			 tmp1.setBackground(Color.black);
			 tmp1.setForeground(Color.white);
			 tmp1.addActionListener(this);
			 tmp1.setPreferredSize(new Dimension(250,50));
			 tmp.add(tmp1);
		 }}
		else {
			x1= new JLabel("No Armies in "+cityName.toUpperCase());
			x1.setFont(f1);
		    tmp.add(x1);
		}
	    
		
		this.setLayout(new GridLayout());
		this.setBackground(color);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel a= new JLabel();
		 a.setLayout(new GridLayout(4,1));
	     JLabel y= new JLabel();
		 y.setFont(f1);
		 a.add(y);
	     JLabel y1= new JLabel();
		 y1.setFont(f1);
		 a.add(y1);
		 JLabel y2= new JLabel();
		 y2.setFont(f1);
		 a.add(y2);
		 JLabel y3= new JLabel("UNITS:");
		 y3.setFont(f1);
		 a.add(y3);
		if(e.getSource()==cArmy) {
			this.remove(tmp);
			y.setText(cityName.toUpperCase()+" DEFENDING ARMY");
			y1.setText("CURRENT STATUS:"+ defarmy.getCurrentStatus());
			y2.setText("MAX UNITS:"+ defarmy.getMaxToHold());
			tmp= new armyrep(game,city,defarmyu,defarmy, color,a,false);
			this.add(tmp);
			this.revalidate();
			this.repaint();
		}
		else if(e.getSource()==initiate) {
			this.remove(tmp);
			y.setText(cityName.toUpperCase()+" DEFENDING ARMY");
			y1.setText("CURRENT STATUS:"+ defarmy.getCurrentStatus());
			y2.setText("MAX UNITS:"+ defarmy.getMaxToHold());
			y3.setText("CHOOSE A UNIT:");
			tmp= new armyrep(game,city,defarmyu,defarmy, color,a,true);
			this.add(tmp);
			this.revalidate();
			this.repaint();
		}
		else {
			
			this.remove(tmp);
			y.setText(e.getActionCommand());
			y1.setText("CURRENT STATUS:"+ armyhere.get(Integer.parseInt(""+(e.getActionCommand()).charAt(e.getActionCommand().length()-1))-1).getCurrentStatus());
			y2.setText("MAX UNITS:"+ armyhere.get(Integer.parseInt(""+(e.getActionCommand()).charAt(e.getActionCommand().length()-1))-1).getMaxToHold());
			tmp= new armyrep(game,city,armyhereu.get(Integer.parseInt(""+(e.getActionCommand()).charAt(e.getActionCommand().length()-1))-1),
					armyhere.get(Integer.parseInt(""+(e.getActionCommand()).charAt(e.getActionCommand().length()-1))-1) , color,a,false);
			this.add(tmp);
			this.revalidate();
			this.repaint();
		}
		
	
	
	}
	

}