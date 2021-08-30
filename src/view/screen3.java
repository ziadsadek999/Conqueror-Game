package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import engine.City;
import engine.Game;
import units.Army;

public class screen3 extends JPanel implements ActionListener {
	
	private Game game;
	public JPanel main;
	private JPanel header;
	private JPanel footer;
	public midScreen mid;
	public Timer timer;

	private JLabel GameName;
	private JLabel playerName;
	private JLabel turnsCount;
	private JLabel food;
	private JLabel gold;

	private JPanel exitPanel;
	private JPanel endTurnPanel;
	private JButton exit;
	private JLabel exitMessage;
	private JLabel endTurnMessage;
	 JButton endTurn;
	private JLabel endTurnWarning;
	 mainScreen window;
	
	public screen3(String name, String city,mainScreen w) throws IOException {
		
		
		
		window = w;
		this.game = new Game(name, city);
		main = new JPanel();
		add(main);
		main.setBackground(Color.red);
		main.setLayout(new BorderLayout());

		header = new JPanel();
		header.setBackground(Color.black);
		main.add(header, BorderLayout.NORTH);
		header.setPreferredSize(new Dimension(window.getWidth(), 80));
		header.setLayout(new GridLayout(1, 5));
		GameName = new JLabel("CONQUER");
		GameName.setBackground(Color.black);
		GameName.setFont(new Font("Monospaced", Font.PLAIN, 50));
		GameName.setForeground(Color.white);
		header.add(GameName);

		playerName = new JLabel(name);
		playerName.setBackground(Color.black);
		playerName.setFont(new Font("Monospaced", Font.PLAIN, 30));
		playerName.setForeground(Color.white);
		header.add(playerName);

		turnsCount = new JLabel("Turn:" + game.getCurrentTurnCount());
		turnsCount.setBackground(Color.black);
		turnsCount.setFont(new Font("Monospaced", Font.PLAIN, 30));
		turnsCount.setForeground(Color.white);
		header.add(turnsCount);

		food = new JLabel("Food:" + game.getPlayer().getFood());
		food.setBackground(Color.black);
		food.setFont(new Font("Monospaced", Font.PLAIN, 30));
		food.setForeground(Color.white);
		header.add(food);

		gold = new JLabel("Gold:" + game.getPlayer().getTreasury());
		gold.setBackground(Color.black);
		gold.setFont(new Font("Monospaced", Font.PLAIN, 30));
		gold.setForeground(Color.white);
		header.add(gold);

		footer = new JPanel();
		footer.setBackground(Color.black);
		main.add(footer, BorderLayout.SOUTH);
		footer.setPreferredSize(new Dimension(window.getWidth(), 50));
		footer.setLayout(new BorderLayout());

		exitPanel = new JPanel();
		exitPanel.setBackground(Color.black);
		footer.add(exitPanel, BorderLayout.WEST);
		exitPanel.setPreferredSize(new Dimension(300, 50));
		exitPanel.setLayout(new FlowLayout());

		endTurnPanel = new JPanel();
		endTurnPanel.setBackground(Color.black);
		footer.add(endTurnPanel, BorderLayout.EAST);
		endTurnPanel.setPreferredSize(new Dimension(800, 50));
		endTurnPanel.setLayout(new FlowLayout());

		exitMessage = new JLabel("If you want to exit the game press ");
		exitMessage.setPreferredSize(new Dimension(195, 40));
		exitMessage.setForeground(Color.white);
		exitMessage.setBackground(Color.black);
		exitPanel.add(exitMessage);

		exit = new JButton("Exit");
		exit.setForeground(Color.white);
		exit.setBackground(Color.black);
		exit.addActionListener(this);
		exitPanel.add(exit);

		endTurnMessage = new JLabel("If you want to end the current turn press");
		endTurnMessage.setPreferredSize(new Dimension(230, 40));
		endTurnMessage.setForeground(Color.white);
		endTurnMessage.setBackground(Color.black);
		endTurnPanel.add(endTurnMessage);

		endTurn = new JButton("End Turn");
		endTurn.setForeground(Color.white);
		endTurn.setBackground(Color.black);
		endTurn.addActionListener(this);
		endTurnPanel.add(endTurn);

		endTurnWarning = new JLabel(" Note:You only have 50 turns!");
		endTurnWarning.setPreferredSize(new Dimension(200, 40));
		endTurnWarning.setForeground(Color.RED);
		endTurnWarning.setBackground(Color.black);
		endTurnPanel.add(endTurnWarning);

		mid = new midScreen(w,game);
		main.add(mid);
		
		DecimalFormat approx = new DecimalFormat();
		approx.setMaximumFractionDigits(3);
		String g = approx.format(game.getPlayer().getTreasury());
		gold.setText("Gold:" + g);
	
		setBackground(Color.black);
		
		//timer to handle updating gold
				//********************************************************
				
				timer=new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						DecimalFormat approx = new DecimalFormat();
						approx.setMaximumFractionDigits(3);
						String g = approx.format(game.getPlayer().getTreasury());
						gold.setText("Gold:" + g);
						mid.mapView.armyIcons = new ArrayList<ArmyIcon>();
						mid.mapView.cairoArmy.removeAll();
						mid.mapView.romeArmy.removeAll();
						mid.mapView.spartaArmy.removeAll();
						mid.mapView.ToCairo.removeAll();
						mid.mapView.ToRome.removeAll();
						mid.mapView.ToSparta.removeAll();
						for (Army a : game.getPlayer().getControlledArmies()) {
							if (a.getUnits().size() == 0) {
								//game.getPlayer().getControlledArmies().remove(a);
							} else {
								ArmyIcon x = new ArmyIcon(a, mid.mapView);
								mid.mapView.armyIcons.add(x);
								if (a.getCurrentLocation().toLowerCase().equals("rome")) {
									mid.mapView.romeArmy.add(x);
								} else if (a.getCurrentLocation().toLowerCase().equals("sparta")) {
									mid.mapView.spartaArmy.add(x);
								} else if (a.getCurrentLocation().toLowerCase().equals("cairo")) {
									mid.mapView.cairoArmy.add(x);
								} else if (a.getTarget().toLowerCase().equals("cairo")) {
									mid.mapView.ToCairo.add(x);
								} else if (a.getTarget().toLowerCase().equals("sparta")) {
									mid.mapView.ToSparta.add(x);
								} else {
									mid.mapView.ToRome.add(x);
								}
							}
						}
						mid.mapView.revalidate();
						mid.mapView.repaint();
						gold.revalidate();
						gold.repaint();
					}
				});
				//**********************************************************
				timer.start();
		
		this.revalidate();
		this.repaint();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			 new exitConfirmation(timer);
			
		} else {
			if (e.getSource() == endTurn) {
				if (game.isGameOver()) {
					if (game.getAvailableCities().size() == game.getPlayer().getControlledCities().size()) {
						try {
							window.add(new gameOverScreen(true,window));
						} catch (IOException e1) {
							window.dispose();
						}
					} else {
						try {
							window.add(new gameOverScreen(false,window));
						} catch (IOException e1) {
							window.dispose();
						}
					}
					window.remove(this);
					window.revalidate();
					window.repaint();
				} else {
					game.endTurn();
					mid.left.removeAll();
					
					for (City c : game.getAvailableCities()) {
						if(c.getTurnsUnderSiege()==3) {
							Army att = new Army("");
							for(Army a : game.getPlayer().getControlledArmies()) {
								if(a.getCurrentLocation().toLowerCase().equals(c.getName().toLowerCase())&&a.getUnits().size()>0) {
									att = a;
								}
							}
							if(!att.getCurrentLocation().equals("")) {
							
							mid.left.setVisible(false);
							mid.right.setVisible(false);
							
							
							try {
								mid.add(new attackScreen(c,game,this,att,c.getDefendingArmy()));
							} catch (IOException e1) {
								window.dispose();
							}
							
							endTurn.removeActionListener(this);
							
							}
						}
					}
					for (Army c:game.getPlayer().getControlledArmies()) {
						if(c.getDistancetoTarget()==0&&c.getUnits().size()!=0) {
							c.setDistancetoTarget(-1);
							City z = new City("Mango");
							for(City a:game.getAvailableCities()) {
								if(a.getName().toLowerCase().equals(c.getCurrentLocation().toLowerCase())) {
									z = a;
									break;
								}
							}
							if(game.getPlayer().getControlledCities().contains(z)) {
								
							}
							else if(z.getTurnsUnderSiege()<3){
								mid.left.setVisible(false);
								mid.right.setVisible(false);
						
							try {
								mid.add(new reachedScreen(c,game,this,c.getCurrentLocation()));
							} catch (IOException e1) {
								window.dispose();
							}
							
							endTurn.removeActionListener(this);
							}else {
								mid.left.setVisible(false);
								mid.right.setVisible(false);
						
								try {
									mid.add(new attackScreen(z,game,this,c,z.getDefendingArmy()));
								} catch (IOException e1) {
									window.dispose();
								}
								endTurn.removeActionListener(this);
							}
						}
					}
					turnsCount.setText("Turn:" + game.getCurrentTurnCount());
					DecimalFormat approx = new DecimalFormat();
					approx.setMaximumFractionDigits(3);
					String f = approx.format(game.getPlayer().getFood());
					food.setText("Food:" + f);
					String g = approx.format(game.getPlayer().getTreasury());
					gold.setText("Gold:" + g);
				}
			}
		}
		window.revalidate();
		window.repaint();
	}

}
