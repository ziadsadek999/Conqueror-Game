package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import engine.*;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.*;

public class reachedScreen extends JPanel implements ActionListener {
	Font f1=new Font("Monospaced",Font.PLAIN,28);
	Font f=new Font("Monospaced",Font.PLAIN,20);
	City city;
	Game game;
	JPanel prompt;
	JTextPane message;
	JPanel buttons;
	JTextPane error;
	private JButton attack;
	JButton laySeige;
	private JButton auto;
	screen3 s3;
	Army army;
	JButton toMap;
	BufferedImage backgroundImage;
	public reachedScreen(Army a, Game game, screen3 s, String cityName) throws IOException {
		for(City c:game.getAvailableCities()) {
			if(c.getName().toLowerCase().equals(cityName.toLowerCase())) {
				city = c;
			}
		}
		setPreferredSize(s.mid.getPreferredSize());
		army = a;
		this.game = game;
		s3 = s;
		
		
		setLayout(new GridLayout(3,3));
		backgroundImage = ImageIO.read(new File("conq.jpg"));
		
			JPanel x = new JPanel();
			x.setOpaque(false);
			add(x);
		
		prompt = new JPanel();
		prompt.setOpaque(false);
		prompt.setLayout(new GridLayout(3,1));
		
		message = new JTextPane();
		message.setOpaque(false);
		message.setEditable(false);

		message.setText("Your Army have reached "+cityName);
		message.setForeground(Color.red);
		message.setFont(f1);
		prompt.add(message);
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,3));
		buttons.setOpaque(false);
		prompt.add(buttons);
		attack = new JButton("Attack");
		auto = new JButton("Auto Resolve");
		auto.setForeground(Color.black);
		auto.setFont(new Font("Monospaced",Font.PLAIN,15));
		auto.setOpaque(false);
		auto.setContentAreaFilled(false);
		auto.setBorderPainted(false);
		
		
		attack.setForeground(Color.black);
		attack.setOpaque(false);
		attack.setContentAreaFilled(false);
		attack.setBorderPainted(false);
		attack.setFont(f);
		laySeige = new JButton("Lay Seige");
		laySeige.addActionListener(this);
		attack.addActionListener(this);
		auto.addActionListener(this);
		laySeige.setForeground(Color.black);
		laySeige.setFont(f);
		laySeige.setOpaque(false);
		laySeige.setContentAreaFilled(false);
		laySeige.setBorderPainted(false);
		buttons.add(attack);
		buttons.add(auto);
		buttons.add(laySeige);
		add(prompt);
		setVisible(true);
		for (int i = 0; i < 7; i++) {
			JPanel d = new JPanel();
			d.setOpaque(false);
			add(d);
		}
		revalidate();
		repaint();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==attack) {
			BattleViewScreen bv;
			try {
				bv = new BattleViewScreen(s3.window, game.getPlayer().getName(), game.getCurrentTurnCount(), game.getPlayer().getTreasury(),game.getPlayer().getFood(),army.getUnits() , city.getDefendingArmy().getUnits(), s3, this,game);
				bv.setPreferredSize(s3.window.getPreferredSize());
				s3.removeAll();
				s3.add(bv);
				s3.revalidate();
				s3.repaint();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource()==laySeige){
			try {
				game.getPlayer().laySiege(army, city);
				s3.remove(this);
				s3.mid.left.setVisible(true);
				s3.mid.right.setVisible(true);
				s3.endTurn.addActionListener(s3);
			} catch (TargetNotReachedException e1) {
				prompt.removeAll();
				prompt.setLayout(new GridLayout(2,1));
				
				toMap = new JButton();
				toMap.setOpaque(false);
				toMap.setContentAreaFilled(false);
				toMap.setBorderPainted(false);
				toMap.addActionListener(this);
				toMap.setText("Back To Map");
				toMap.setForeground(Color.white);
				toMap.setFont(new Font("Monospaced",Font.PLAIN,40));
				
				error = new JTextPane();
				error.setOpaque(false);
				error.setEditable(false);
				error.setFont(new Font("Monospaced",Font.PLAIN,20));
				error.setForeground(Color.red);
				error.setText("Target Not Reached!");
				prompt.add(error);
				prompt.add(toMap);
				
			} catch (FriendlyCityException e1) {
				prompt.removeAll();
				prompt.setLayout(new GridLayout(2,1));
				toMap = new JButton();
				toMap.setOpaque(false);
				toMap.setContentAreaFilled(false);
				toMap.setBorderPainted(false);
				toMap.addActionListener(this);
				toMap.setText("Back To Map");
				toMap.setForeground(Color.white);
				toMap.setFont(new Font("Monospaced",Font.PLAIN,40));
				
				error = new JTextPane();
				error.setOpaque(false);
				error.setEditable(false);
				error.setFont(new Font("Monospaced",Font.PLAIN,20));
				error.setForeground(Color.red);
				error.setText("You can not beseige a city you control!");
				prompt.add(error);
				prompt.add(toMap);
				
				
			}
		}else if(e.getSource()==auto) {
			try {
			game.autoResolve(army, city.getDefendingArmy());
			
			if(army.getUnits().size()==0) {
				new BattleResultPage(false,s3,this);
			}
			else {
				new BattleResultPage(true,s3,this);
			}
			}
			catch (FriendlyFireException e1) {
				prompt.removeAll();
				prompt.setLayout(new GridLayout(2,1));
				toMap = new JButton();
				toMap.setOpaque(false);
				toMap.setContentAreaFilled(false);
				toMap.setBorderPainted(false);
				toMap.addActionListener(this);
				toMap.setText("Back To Map");
				toMap.setForeground(Color.white);
				toMap.setFont(new Font("Monospaced",Font.PLAIN,40));
				
				error = new JTextPane();
				error.setOpaque(false);
				error.setEditable(false);
				error.setFont(new Font("Monospaced",Font.PLAIN,20));
				error.setForeground(Color.red);
				error.setText("Can not attack your own army!");
				prompt.add(error);
				prompt.add(toMap);
			}
		}else if(e.getSource()==toMap) {
			
			s3.mid.remove(this);
			s3.mid.left.setVisible(true);
			s3.mid.right.setVisible(true);
			s3.endTurn.addActionListener(s3);
		}
		
		revalidate();
		repaint();
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
	  }
	
}
