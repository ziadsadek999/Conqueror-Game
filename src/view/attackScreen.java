package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

import engine.*;
import exceptions.FriendlyFireException;
import units.Army;
import units.Unit;

public class attackScreen extends JPanel implements ActionListener {
	City city;
	Game game;
	JPanel prompt;
	JTextPane message;
	JPanel buttons;
	private JButton attack;
	private JButton auto;
	JButton toMap;
	JTextPane error;
	screen3 s3;
	Font f1 = new Font("Monospaced", Font.PLAIN, 30);
	Font f = new Font("Monospaced", Font.PLAIN, 20);
	private Army a;
	private Army d;
	BufferedImage backgroundImage;
	public attackScreen(City c, Game g, screen3 s, Army attacker, Army defender) throws IOException {
		a = attacker;
		d = defender;
		backgroundImage = ImageIO.read(new File("conq.jpg"));
		s3 = s;
		city = c;
		game = g;
		setPreferredSize(new Dimension(s.getWidth(),s.getHeight()-130));
		setLayout(new GridLayout(3, 3));
		
		
			JPanel x = new JPanel();
			x.setOpaque(false);
			add(x);
		
		
		prompt = new JPanel();
		prompt.setOpaque(false);
		prompt.setLayout(new GridLayout(2, 1));

		message = new JTextPane();
		message.setOpaque(false);
		message.setEditable(false);

		message.setText(c.getName() + " was under seige for three turns!");
		message.setForeground(Color.red);
		message.setFont(f1);
		prompt.add(message);
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		prompt.add(buttons);
		attack = new JButton("Attack");

		attack.setForeground(Color.black);
		attack.setOpaque(false);
		attack.setContentAreaFilled(false);
		attack.setBorderPainted(false);
		attack.setFont(f);
		auto = new JButton("Auto Resolve");
		auto.addActionListener(this);
		attack.addActionListener(this);
		auto.setForeground(Color.black);
		auto.setFont(f);
		auto.setOpaque(false);
		auto.setContentAreaFilled(false);
		auto.setBorderPainted(false);
		buttons.add(attack);
		buttons.add(auto);
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == attack) {
			try {
				BattleViewScreen bv = new BattleViewScreen(s3.window, game.getPlayer().getName(), game.getCurrentTurnCount(), game.getPlayer().getTreasury(),game.getPlayer().getFood(),a.getUnits() , d.getUnits(), s3, this,game);
				bv.setPreferredSize(s3.window.getPreferredSize());
				s3.removeAll();
				s3.add(bv);
				s3.revalidate();
				s3.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == auto) {
			try {
				game.autoResolve(a, d);
				if(a.getUnits().size()==0) {
					new BattleResultPage(false,s3,this);
				}
				else {
					new BattleResultPage(true,s3,this);
				}
			} catch (FriendlyFireException e1) {
				prompt.removeAll();
				prompt.setLayout(new GridLayout(2, 1));
				toMap = new JButton();
				toMap.setOpaque(false);
				toMap.setContentAreaFilled(false);
				toMap.setBorderPainted(false);
				toMap.addActionListener(this);
				toMap.setText("Back To Map");
				toMap.setForeground(Color.white);
				toMap.setFont(new Font("Monospaced", Font.PLAIN, 40));

				error = new JTextPane();
				error.setOpaque(false);
				error.setEditable(false);
				error.setFont(new Font("Monospaced", Font.PLAIN, 20));
				error.setForeground(Color.red);
				error.setText("Can not attack your own army!");
				prompt.add(error);
				prompt.add(toMap);
			}

		} else if (e.getSource() == toMap) {

			s3.remove(this);
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
