package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.StyleConstants;

import engine.City;
import engine.Game;
import units.Archer;
import units.Army;

import java.util.*;

public class mapBackground extends JPanel implements ActionListener {
	BufferedImage mapPic;
	Game game;
	JPanel commands;
	midScreen mid;
	JButton cairo = new JButton();
	JButton rome = new JButton();
	JButton sparta = new JButton();
	Font cityFont = new Font("Monospaced", Font.PLAIN, 35);
	ArrayList<ArmyIcon> armyIcons = new ArrayList<ArmyIcon>();
	JPanel[][] coOrdinates;
	JPanel cairoArmy;
	JPanel romeArmy;
	JPanel spartaArmy;
	public Timer timer;
	JPanel ToRome;
	JPanel ToSparta;
	JPanel ToCairo;
	JTextPane t;
	MainCityView maincityview;

	public mapBackground(Game game, midScreen m) {
		try {

			cairoArmy = new JPanel();
			cairoArmy.setOpaque(false);

			romeArmy = new JPanel();
			romeArmy.setOpaque(false);

			spartaArmy = new JPanel();
			spartaArmy.setOpaque(false);

			ToRome = new JPanel();
			ToRome.setOpaque(false);

			ToSparta = new JPanel();
			ToSparta.setPreferredSize(new Dimension(100, 100));
			ToSparta.setOpaque(false);
			ToSparta.setLayout(new FlowLayout());

			ToCairo = new JPanel();
			ToCairo.setPreferredSize(new Dimension(100, 100));
			ToCairo.setOpaque(false);
			ToCairo.setLayout(new FlowLayout());

			this.game = game;
			setLayout(new BorderLayout());
			mid = m;
			setPreferredSize(new Dimension(mid.getPreferredSize().width - 250, mid.getPreferredSize().height));
			mapPic = ImageIO.read(new File("hh.jpg"));
			commands = new JPanel();
			commands.setPreferredSize(getPreferredSize());
			commands.setLayout(new GridLayout(9, 9));
			coOrdinates = new JPanel[9][9];

			for (int i = 0; i < coOrdinates.length; i++) {
				for (int j = 0; j < coOrdinates.length; j++) {
					coOrdinates[j][i] = new JPanel();

					coOrdinates[j][i].setOpaque(false);
					;
					commands.add(coOrdinates[j][i]);
				}
			}
			commands.setOpaque(false);
			sparta.setOpaque(false);
			sparta.setContentAreaFilled(false);
			sparta.setBackground(new Color(233, 232, 190));
			sparta.setBorderPainted(false);
			sparta.setText("Sparta");
			sparta.setFont(cityFont);
			sparta.setForeground(Color.black);
			sparta.addActionListener(this);

			coOrdinates[1][2].add(sparta);

			cairo.setOpaque(false);
			cairo.setContentAreaFilled(false);
			cairo.setBackground(new Color(233, 232, 190));
			cairo.setBorderPainted(false);
			cairo.setText("Cairo");
			cairo.setFont(cityFont);
			cairo.setForeground(Color.black);
			cairo.addActionListener(this);

			coOrdinates[4][7].add(cairo);

			rome.setOpaque(false);
			rome.setContentAreaFilled(false);
			rome.setBackground(new Color(233, 232, 190));
			rome.setBorderPainted(false);
			rome.setText("Rome");
			rome.setFont(cityFont);
			rome.setForeground(Color.black);
			rome.addActionListener(this);

			coOrdinates[5][2].add(rome);
			Dimension d = new Dimension(commands.getPreferredSize().width / 9, commands.getPreferredSize().height / 9);
			spartaArmy.setPreferredSize(d);
			spartaArmy.setLayout(new GridLayout(1,0));
			coOrdinates[1][1].add(spartaArmy);

			cairoArmy.setPreferredSize(d);
			cairoArmy.setLayout(new GridLayout(1, 0));
			coOrdinates[3][7].add(cairoArmy);

			romeArmy.setPreferredSize(d);
			romeArmy.setLayout(new GridLayout(1, 0));
			coOrdinates[6][3].add(romeArmy);

			ToSparta.setPreferredSize(d);
			ToSparta.setLayout(new GridLayout(1, 1));
			coOrdinates[2][3].add(ToSparta);

			ToCairo.setPreferredSize(d);
			ToCairo.setLayout(new GridLayout(1, 1));
			coOrdinates[3][5].add(ToCairo);

			ToRome.setPreferredSize(d);
			ToRome.setLayout(new GridLayout(1, 1));
			coOrdinates[4][3].add(ToRome);

			for (Army a : game.getPlayer().getControlledArmies()) {
				if (a.getUnits().size() == 0) {
					game.getPlayer().getControlledArmies().remove(a);
				} else {
					ArmyIcon x = new ArmyIcon(a, this);
					armyIcons.add(x);
					if (a.getCurrentLocation().toLowerCase().equals("rome")) {
						romeArmy.add(x);
					} else if (a.getCurrentLocation().toLowerCase().equals("sparta")) {
						spartaArmy.add(x);
					} else if (a.getCurrentLocation().toLowerCase().equals("cairo")) {
						cairoArmy.add(x);
					} else if (a.getTarget().toLowerCase().equals("cairo")) {
						ToCairo.add(x);
					} else if (a.getTarget().toLowerCase().equals("sparta")) {
						ToSparta.add(x);
					} else {
						ToRome.add(x);
					}
				}
			}

			add(commands, BorderLayout.CENTER);

		} catch (IOException e) {

		}

		revalidate();
		repaint();
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapPic, 0, 0, getWidth(), getHeight(), this);
	}

	public void actionPerformed(ActionEvent e) {
		if (armyIcons.contains(e.getSource())) {
			armyViewMap x = new armyViewMap(((ArmyIcon) e.getSource()).army, game, this, mid);
			mid.left.removeAll();
			JScrollPane s= new JScrollPane(x);
			s.setPreferredSize(mid.left.getPreferredSize());
			s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			mid.left.add(s);
			mid.revalidate();
			mid.repaint();
			return;
		}

		String s = ((JButton) e.getSource()).getText();

		for (int i = 0; i < game.getAvailableCities().size(); i++) {
			if (game.getAvailableCities().get(i).getName().equals(s)) {

				if (game.getPlayer().getControlledCities().contains(game.getAvailableCities().get(i))) {
					try {
						String n = game.getAvailableCities().get(i).getName();

						if (n.toLowerCase().equals("rome")) {

							mid.removeAll();
							mid.add(new RomeView(game, mid, mid.window));
							mid.revalidate();
							mid.repaint();
						} else if (n.toLowerCase().equals("sparta")) {
							mid.removeAll();
							mid.add(new SpartaView(game, mid, mid.window));

							mid.revalidate();
							mid.repaint();
						} else {
							mid.removeAll();
							mid.add(new CairoView(game, mid, mid.window));
							mid.revalidate();
							mid.repaint();
						}
						mid.revalidate();
						mid.repaint();
					} catch (IOException f) {
						mid.window.dispose();
					}
				} else {
					Timer x = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mid.left.remove(t);
							mid.revalidate();
							mid.repaint();

						}
					});
					x.setRepeats(false);

					int n = game.getAvailableCities().get(i).getTurnsUnderSiege();
					String f;
					if (n == -1) {
						f = "THE CITY IS NOT UNDER SEIGE";
					} else {
						f = "TURNS UNDER SEIGE: " + n;
					}
					x.start();
					t = new JTextPane();
					t.setPreferredSize(mid.left.getPreferredSize());
					t.setText(f);
					t.setOpaque(false);
					mid.left.removeAll();
					mid.left.add(t);
					t.setForeground(Color.red);
					t.setOpaque(false);

					t.setFont(new Font("Monospace", Font.PLAIN, 20));
					revalidate();
					repaint();
				}
			}

		}
		revalidate();
		repaint();
	}

}