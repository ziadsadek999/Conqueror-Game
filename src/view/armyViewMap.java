package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.City;
import engine.Game;
import units.*;
import javax.swing.*;

public class armyViewMap extends JPanel implements ActionListener {
	JButton setTarget;
	Army army;
	JButton back;
	JPanel choosePanel;
	private Font f = new Font("Monospaced", Font.PLAIN, 18);
	private Font f1 = new Font("Monospaced", Font.PLAIN, 15);
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private ButtonGroup cities;
	private JButton choose;
	Game game;
	mapBackground map;
	midScreen mid;
	JButton att;

	public armyViewMap(Army a, Game g, mapBackground m, midScreen ms) {
		setBackground(new Color(209,159,102));
		mid = ms;
		map = m;
		game = g;
		army = a;
		setLayout(new GridLayout(15, 1));
		setPreferredSize(new Dimension(ms.left.getWidth(),1500));
		setTarget = new JButton("SET TARGET");
		setTarget.setFont(f);
		setTarget.setBackground(Color.black);
		setTarget.setForeground(Color.white);
		
		add(setTarget);
		setTarget.addActionListener(this);
		if (a.getCurrentStatus() == Status.IDLE) {

			JTextPane x1 = new JTextPane();
			x1.setText("Army is idle at " + a.getCurrentLocation());
			x1.setEditable(false);
			x1.setOpaque(false);
			x1.setFont(f);
			add(x1);

			for (Unit u : a.getUnits()) {
				JLabel z = new JLabel();
				z.setLayout(new GridLayout(5, 1));

				JLabel y = new JLabel(getType(u));
				
				y.setForeground(Color.black);
				y.setFont(f1);
				z.add(y);
				JLabel y1 = new JLabel("LEVEL:" + u.getLevel());
				
				y1.setFont(f1);
				y1.setForeground(Color.black);
				z.add(y1);
				JLabel y2 = new JLabel("CURRENT SOLIDIER COUNT:" + u.getCurrentSoldierCount());
				
				y2.setFont(f1);
				y2.setForeground(Color.black);
				z.add(y2);
				JLabel y3 = new JLabel("MAX SOLIDIER COUNT:" + u.getMaxSoldierCount());
				
				y3.setFont(f1);
				y3.setForeground(Color.black);
				z.add(y3);
				JLabel y4 = new JLabel("--------------------",SwingConstants.CENTER);
				y4.setFont(f1);
				y4.setForeground(Color.black);
				z.add(y4);
				add(z);
			}

		} else if (a.getCurrentStatus() == Status.MARCHING) {

			
			JTextPane x1 = new JTextPane();
			x1.setText("Army is marching to " + a.getTarget());
			x1.setEditable(false);
			x1.setOpaque(false);
			x1.setFont(f);
			add(x1);
			JLabel x2 = new JLabel("Turns left to reach target: " + a.getDistancetoTarget());
			x2.setFont(f1);
			add(x2);
			for (Unit u : a.getUnits()) {
				JLabel z = new JLabel();
				z.setLayout(new GridLayout(5, 1));

				JLabel y = new JLabel(getType(u));
				y.setForeground(Color.black);
				y.setFont(f1);
				z.add(y);
				JLabel y1 = new JLabel("LEVEL:" + u.getLevel());
				y1.setFont(f1);
				y1.setForeground(Color.black);
				z.add(y1);
				JLabel y2 = new JLabel("CURRENT SOLIDIER COUNT:" + u.getCurrentSoldierCount());
				y2.setFont(f1);
				y2.setForeground(Color.black);
				z.add(y2);
				JLabel y3 = new JLabel("MAX SOLIDIER COUNT:" + u.getMaxSoldierCount());
				y3.setFont(f1);
				y3.setForeground(Color.black);
				z.add(y3);
				JLabel y4 = new JLabel("--------------------",SwingConstants.CENTER);
				y4.setFont(f1);
				y4.setForeground(Color.black);
				z.add(y4);
				add(z);
			}

		} else {
			att = new JButton("Attack");
			att.setFont(f);
			att.setBackground(Color.black);
			att.setForeground(Color.white);
			
			add(att);
			att.addActionListener(this);
			JTextPane x1 = new JTextPane();
			x1.setText("Army is beseiging " + a.getCurrentLocation());
			x1.setEditable(false);
			x1.setOpaque(false);
			x1.setFont(f);
			add(x1);
			
			City s = new City("Mango");
			for (City c : game.getAvailableCities()) {
				if (c.getName().toLowerCase().equals(a.getCurrentLocation().toLowerCase())) {
					s = c;
					break;
				}
			}
			JLabel x2 = new JLabel("Turns under seige " + s.getTurnsUnderSiege());
			x2.setFont(f);
			add(x2);
			for (Unit u : a.getUnits()) {
				JLabel z = new JLabel();
				z.setLayout(new GridLayout(5, 1));

				JLabel y = new JLabel(getType(u));
				y.setForeground(Color.black);
				y.setFont(f1);
				z.add(y);
				JLabel y1 = new JLabel("LEVEL:" + u.getLevel());
				y1.setFont(f1);
				y1.setForeground(Color.black);
				z.add(y1);
				JLabel y2 = new JLabel("CURRENT SOLIDIER COUNT:" + u.getCurrentSoldierCount());
				y2.setFont(f1);
				y2.setForeground(Color.black);
				z.add(y2);
				JLabel y3 = new JLabel("MAX SOLIDIER COUNT:" + u.getMaxSoldierCount());
				y3.setFont(f1);
				y3.setForeground(Color.black);
				z.add(y3);
				JLabel y4 = new JLabel("--------------------",SwingConstants.CENTER);
				y4.setFont(f1);
				y4.setForeground(Color.black);
				z.add(y4);
				add(z);
			}

		}

		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setTarget) {
			cities = new ButtonGroup();
			c1 = new JRadioButton("Cairo");
			c1.setPreferredSize(new Dimension(350, 20));
			c1.setForeground(Color.white);
			c1.setOpaque(false);
			c2 = new JRadioButton("Rome");
			c2.setPreferredSize(new Dimension(350, 20));
			c2.setForeground(Color.white);
			c2.setOpaque(false);
			c3 = new JRadioButton("Sparta");
			c3.setPreferredSize(new Dimension(350, 20));
			c3.setForeground(Color.white);
			c3.setOpaque(false);
			cities.add(c1);
			cities.add(c2);
			cities.add(c3);
			choose = new JButton("CHOOSE");
			choose.setFont(f);
			choose.addActionListener(this);
			back = new JButton("BACK");
			back.setFont(f);
			back.addActionListener(this);
			back.setBackground(Color.black);
			back.setForeground(Color.white);
			choose.setBackground(Color.black);
			choose.setForeground(Color.white);
			choosePanel = new JPanel();
			choosePanel.setLayout(new GridLayout(15, 1));
			choosePanel.setPreferredSize(new Dimension(mid.left.getPreferredSize().width,0));
			choosePanel.add(c1);
			choosePanel.add(c2);
			choosePanel.add(c3);
			choosePanel.add(choose);
			choosePanel.add(back);
			choosePanel.setOpaque(false);
			setLayout(new GridLayout(1, 1));
			removeAll();
			setPreferredSize(new Dimension(mid.left.getWidth(),mid.left.getHeight()-50));
			add(choosePanel);
		} else if (e.getSource() == back) {
			mid.left.removeAll();
			JScrollPane s = new JScrollPane(new armyViewMap(army,game, map, mid));
			s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			mid.left.add(s);
		} else if (e.getSource() == choose) {
			if (c1.isSelected()) {
				if(army.getCurrentLocation().toLowerCase().equals("cairo")) {
					JLabel message = new JLabel("You can not target your own location!");

					message.setOpaque(false);
					message.setForeground(Color.red);
					choosePanel.add(message);
				}
				else {
					if(army.getCurrentStatus()==Status.BESIEGING) {
						for(City c:game.getAvailableCities()) {
							if(c.getName().toLowerCase().equals(army.getCurrentLocation().toLowerCase())) {
								c.setUnderSiege(false);
							}
						}
					}
				game.targetCity(army, "Cairo");
				mid.left.removeAll();
				JScrollPane s = new JScrollPane(new armyViewMap(army,game, map, mid));
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				mid.left.add(s);
				}
			} else if (c2.isSelected()) {
				if(army.getCurrentLocation().toLowerCase().equals("rome")) {
					JLabel message = new JLabel("You can not target your own location!");
					message.setOpaque(false);
					message.setForeground(Color.red);
					choosePanel.add(message);
				}
				else {
					if(army.getCurrentStatus()==Status.BESIEGING) {
						for(City c:game.getAvailableCities()) {
							if(c.getName().toLowerCase().equals(army.getCurrentLocation().toLowerCase())) {
								c.setUnderSiege(false);
							}
						}
					}
				game.targetCity(army, "Rome");
				mid.left.removeAll();
				JScrollPane s = new JScrollPane(new armyViewMap(army,game, map, mid));
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				mid.left.add(s);
				}
			} else if (c3.isSelected()) {
				if(army.getCurrentLocation().toLowerCase().equals("sparta")) {
					JLabel message = new JLabel("You can not target your own location!");

					message.setOpaque(false);
					message.setForeground(Color.red);
					choosePanel.add(message);
				}
				else {
					if(army.getCurrentStatus()==Status.BESIEGING) {
						for(City c:game.getAvailableCities()) {
							if(c.getName().toLowerCase().equals(army.getCurrentLocation().toLowerCase())) {
								c.setUnderSiege(false);
							}
						}
					}
				game.targetCity(army, "Sparta");
				mid.left.removeAll();
				JScrollPane s = new JScrollPane(new armyViewMap(army,game, map, mid));
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				mid.left.add(s);
				}
			} else {
				JLabel message = new JLabel("You must choose a city!");
				message.setOpaque(false);
				message.setForeground(Color.red);
				choosePanel.add(message);
			}
		}else if(e.getSource()==att) {
			City x = new City("Mango");
			for(City c:game.getAvailableCities()) {
				if(c.getName().toLowerCase().equals(army.getCurrentLocation().toLowerCase())) {
					c.setUnderSiege(false);
					x = c;
				}
			}
			mid.left.setVisible(false);
			mid.right.setVisible(false);
	
			try {
				mid.add(new attackWhileBeseige(x,game,(screen3)(mid.getParent().getParent()),army,x.getDefendingArmy()));
			} catch (IOException e1) {
				((screen3)(mid.getParent().getParent())).window.dispose();
			}
			((screen3)(mid.getParent().getParent())).endTurn.removeActionListener((screen3)(mid.getParent().getParent()));
			
		}
		revalidate();
		repaint();

	}

	public static String getType(Unit u) {
		if (u instanceof Infantry)
			return "INFANTRY";
		if (u instanceof Cavalry)
			return "CAVALRY";
		if (u instanceof Archer)
			return "ARCHER";
		return "";

	}
}