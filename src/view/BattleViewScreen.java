package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.*;

public class BattleViewScreen extends JPanel implements MouseInputListener, ActionListener {
	ArrayList<unitCard> playerCards;
	ArrayList<unitCard> enemyCards;
	JButton attackbtn;
	JScrollPane scroller;
	JPanel enemySide, playerSide, infoBar, logBar, centerbattle, center, mainCenter, centerLabel, temp, tempEnemey,
			infoBarBottom, infoBarTop, logBarTop, logBarBottom, logPanel;
	Color bgcolor = Color.BLACK;
	JLabel playTurn, yourArmy, EnemyArmy, gameName, playName, foodCount, turnCount, goldCount, playersideLabel,
			enemysideLabel;
	JTextPane logs;
	String playTurnstr = "YOUR TURN";
	CardInfo s;
	unitCard cardChosenPlayer, cardChosenEnemey ,tempcardChosenPlayer,tempcardChosenEnemy;
	mainScreen window;

	ArrayList<Unit> playerUnits;
	ArrayList<Unit> enemyUnits;
	
	Clip clip;
	screen3 s3;
	JPanel sAttack;
	City city;
	static int originalSol = 0;
	Game game;

	public BattleViewScreen(mainScreen m, String playerName, int turns, double gold, double food,
			ArrayList<Unit> playerUnits, ArrayList<Unit> enemyUnits,screen3 so,JPanel pan,Game g) throws IOException {
		
		game = g;
		sAttack = pan;
		playMusic();
		s3 = so;
		this.playerUnits = playerUnits;
		this.enemyUnits = enemyUnits;

		playerCards = new ArrayList<unitCard>();
		enemyCards = new ArrayList<unitCard>();

		window = m;

		enemySide = new JPanel();
		playerSide = new JPanel();
		infoBar = new JPanel();
		logBar = new JPanel();
		centerbattle = new JPanel();
		center = new JPanel();
		mainCenter = new JPanel();
		centerLabel = new JPanel();
		temp = new JPanel();
		tempEnemey = new JPanel();
		infoBarBottom = new JPanel();
		infoBarTop = new JPanel();
		logBarTop = new JPanel();
		logPanel = new JPanel();
		logBarBottom = new JPanel();
		attackbtn = new JButton("Attack");
		attackbtn.setForeground(Color.green);
		attackbtn.setFont(new Font("Courier", Font.BOLD, 28));
		attackbtn.addActionListener(this);
		playTurn = new JLabel(playTurnstr);
		playTurn.setForeground(Color.green);
		playTurn.setFont(new Font("MonoSpaced", Font.BOLD, 35));
		playTurn.setHorizontalAlignment(JLabel.CENTER);
		playersideLabel = new JLabel("YOU");
		playersideLabel.setHorizontalAlignment(JLabel.CENTER);
		playersideLabel.setFont(new Font("MonoSpaced", Font.BOLD, 20));
		playersideLabel.setForeground(Color.green);
		enemysideLabel = new JLabel("ENEMY");
		enemysideLabel.setHorizontalAlignment(JLabel.CENTER);
		enemysideLabel.setFont(new Font("MonoSpaced", Font.BOLD, 20));
		enemysideLabel.setForeground(Color.red);
		logs = new JTextPane();
		logs.setFont(new Font("MonoSpaced", Font.ITALIC, 25));
		logs.setForeground(Color.cyan);
		logs.setText("LOGS");
		logs.setEditable(false);

		StyledDocument doc = logs.getStyledDocument();
		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), keyWord, false);

		yourArmy = new JLabel("Your Army");
		yourArmy.setForeground(Color.white);
		yourArmy.setFont(new Font("MonoSpaced", Font.BOLD, 18));
		yourArmy.setHorizontalAlignment(JLabel.CENTER);
		EnemyArmy = new JLabel(" Enemy");
		EnemyArmy.setForeground(Color.white);
		EnemyArmy.setFont(new Font("MonoSpaced", Font.BOLD, 18));
		EnemyArmy.setHorizontalAlignment(JLabel.CENTER);

		center.setBackground(bgcolor);
		playerSide.setBackground(bgcolor);
		enemySide.setBackground(bgcolor);
		infoBar.setBackground(bgcolor);
		logBar.setBackground(bgcolor);
		centerbattle.setBackground(bgcolor);
		infoBarTop.setBackground(bgcolor);
		infoBarBottom.setBackground(bgcolor);
		temp.setBackground(bgcolor);
		tempEnemey.setBackground(bgcolor);
		logBarTop.setBackground(bgcolor);
		logBarBottom.setBackground(bgcolor);
		attackbtn.setBackground(bgcolor);
		centerLabel.setBackground(bgcolor);
		logs.setBackground(bgcolor);
		logPanel.setBackground(bgcolor);

		playerSide.setLayout(new GridLayout(0, 11, 10, 0));
		enemySide.setLayout(new GridLayout(0,26,10,10));
		center.setLayout(new BorderLayout());
		infoBar.setLayout(new BorderLayout());
		logBar.setLayout(new BorderLayout());
		logBarBottom.setLayout(new GridLayout(0, 1));
		infoBarBottom.setLayout(new GridLayout(2, 0));
		mainCenter.setLayout(new BorderLayout());
		centerLabel.setLayout(new GridLayout(0, 5, 5, 5));
		logPanel.setLayout(new FlowLayout());

		playerSide.setPreferredSize(new Dimension(0, 150));
//		enemySide.setPreferredSize(new Dimension(0, 180));
		
		centerbattle.setPreferredSize(new Dimension(400, 400));
		centerLabel.setPreferredSize(new Dimension(0, 25));
		infoBar.setPreferredSize(new Dimension(250, 0));
		logBar.setPreferredSize(new Dimension(250, 0));
		infoBarTop.setPreferredSize(new Dimension(0, 200));
		logBarTop.setPreferredSize(new Dimension(0, 200));
		infoBarBottom.setPreferredSize(new Dimension(0, 200));
		logBarBottom.setPreferredSize(new Dimension(0, 200));
		logs.setPreferredSize(new Dimension(240, 150));
		logPanel.setPreferredSize(new Dimension(80, 150));
		temp.setPreferredSize(new Dimension(250, 220));
		tempEnemey.setPreferredSize(new Dimension(250, 220));

		playerSide.add(yourArmy);
		enemySide.add(EnemyArmy);

		
		for (Unit d : playerUnits) {
			unitCard c = new unitCard(d, CardMode.PLAYER);
			c.addMouseListener(this);
			playerSide.add(c);
			playerCards.add(c);
		}
		
	
		
		for (Unit d : enemyUnits) {
			unitCard c = new unitCard(d, CardMode.ENEMEY);
			c.setPreferredSize(new Dimension(120,150));
			c.addMouseListener(this);
			enemySide.add(c);
			enemyCards.add(c);
		}
		
		scroller = new JScrollPane(enemySide);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(0,180));
		scroller.setOpaque(false);
		
		cardChosenPlayer = playerCards.get(0);
		tempcardChosenPlayer = cardChosenPlayer;
		s = new CardInfo(cardChosenPlayer);
		temp = s;
		infoBarTop.add(temp);

		infoBarBottom.add(attackbtn);
		infoBarBottom.add(playTurn);

		cardChosenEnemey = enemyCards.get(0);
		tempcardChosenEnemy = cardChosenEnemey;
		s = new CardInfo(cardChosenEnemey);
		tempEnemey = s;
		logBarTop.add(tempEnemey);

		logPanel.add(logs);
		logBarBottom.add(logPanel, BorderLayout.CENTER);

		logBar.add(logBarTop, BorderLayout.NORTH);
		logBar.add(enemysideLabel, BorderLayout.CENTER);
		logBar.add(logBarBottom, BorderLayout.SOUTH);

		infoBar.add(infoBarTop, BorderLayout.NORTH);
		infoBar.add(playersideLabel, BorderLayout.CENTER);
		infoBar.add(infoBarBottom, BorderLayout.SOUTH);

		gameName = new JLabel("CONCQUER");
		gameName.setForeground(Color.white);
		gameName.setHorizontalAlignment(JLabel.CENTER);
		playName = new JLabel(playerName.toUpperCase());
		playName.setForeground(Color.white);
		playName.setHorizontalAlignment(JLabel.CENTER);
		foodCount = new JLabel("FOOD : " + food);
		foodCount.setForeground(Color.white);
		foodCount.setHorizontalAlignment(JLabel.CENTER);
		goldCount = new JLabel("Gold : " + gold);
		goldCount.setForeground(Color.white);
		goldCount.setHorizontalAlignment(JLabel.CENTER);
		turnCount = new JLabel("TURN : " + turns);
		turnCount.setForeground(Color.white);
		turnCount.setHorizontalAlignment(JLabel.CENTER);

		centerLabel.add(gameName);
		centerLabel.add(playName);
		centerLabel.add(foodCount);
		centerLabel.add(goldCount);
		centerLabel.add(turnCount);

		centerbattle.add(new JLabel(new ImageIcon("battle.gif")));
		mainCenter.add(centerbattle, BorderLayout.CENTER);
		mainCenter.add(centerLabel, BorderLayout.SOUTH);

		center.add(mainCenter, BorderLayout.CENTER);
		center.add(infoBar, BorderLayout.WEST);
		center.add(logBar, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(playerSide, BorderLayout.SOUTH);
		add(scroller, BorderLayout.NORTH);

		setBackground(bgcolor);
		revalidate();
		repaint();
		setVisible(true);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (((unitCard) e.getSource()).getMode() == CardMode.PLAYER) {
			tempcardChosenPlayer.setBorder(BorderFactory.createLineBorder(Color.red));
			cardChosenPlayer = (unitCard) e.getSource();
			cardChosenPlayer.setBorder(BorderFactory.createLineBorder(Color.green,5));
			tempcardChosenPlayer = cardChosenPlayer;
			infoBarTop.remove(temp);
			s = null;
			try {
				s = new CardInfo(cardChosenPlayer);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			temp = s;
			infoBarTop.add(temp);
		} else {
			tempcardChosenEnemy.setBorder(BorderFactory.createLineBorder(Color.red));
			cardChosenEnemey = (unitCard) e.getSource();
			cardChosenEnemey.setBorder(BorderFactory.createLineBorder(Color.green,5));
			tempcardChosenEnemy = cardChosenEnemey;
			logBarTop.remove(tempEnemey);
			s = null;
			try {
				s = new CardInfo(cardChosenEnemey);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			tempEnemey = s;
			logBarTop.add(tempEnemey);
		}

		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		s = null;
		if (e.getSource() == attackbtn) {
			Unit attacker = cardChosenPlayer.getUnitFromCard();
			Unit enemy = cardChosenEnemey.getUnitFromCard();

			try {
				int originalSol = enemy.getCurrentSoldierCount();
				attacker.attack(enemy);
				logs.setText(cardChosenEnemey.getUnitName() + " of the enemy has lost "
						+ (originalSol - enemy.getCurrentSoldierCount()) + " solider(s)");
				logs.setFont(new Font("MonoSpaced", Font.ITALIC, 20));
				logs.setForeground(Color.green);
				revalidate();
				repaint();
			} catch (FriendlyFireException e1) {
				e1.printStackTrace();
			}

			if (cardChosenEnemey.getUnitFromCard().getCurrentSoldierCount() <= 0) {
				enemySide.remove(cardChosenEnemey);
				enemyCards.remove(cardChosenEnemey);
				this.enemyUnits.remove(enemy);
				if (this.enemyUnits.size() <= 0) {
					clip.stop();
					game.occupy(playerUnits.get(0).getParentArmy(), playerUnits.get(0).getParentArmy().getCurrentLocation());
					new BattleResultPage(true,s3,sAttack);
					return;
				}
				revalidate();
				repaint();
			}



			playTurnstr = "ENEMEY's TURN";
			playTurn.setText(playTurnstr);
			playTurn.setFont(new Font("MonoSpaced", Font.BOLD, 25));
			playTurn.setForeground(Color.red);

			unitCard enemyAttack = enemyCards.get((int) (Math.random() * this.enemyUnits.size()));
			int x = (int) (Math.random() * this.playerUnits.size());
			unitCard enemyPick = playerCards.get(x);
			
			
			attackbtn.setEnabled(false);

			// here the computer chooses his pick from his selections
			cardChosenEnemey = enemyAttack;
			cardChosenPlayer = enemyPick;
			logBarTop.remove(tempEnemey);
			s = null;
			try {
				s = new CardInfo(cardChosenEnemey);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			tempEnemey = s;
			logBarTop.add(tempEnemey);
			
			Timer timer = new Timer(2000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					playTurnstr = "YOUR TURN";
					playTurn.setText(playTurnstr);
					playTurn.setFont(new Font("MonoSpaced", Font.BOLD, 35));
					playTurn.setForeground(Color.green);
					attackbtn.setEnabled(true);

					logs.setText(cardChosenPlayer.getUnitName() + " in your Army has lost "
							+ (originalSol - enemyPick.getUnitFromCard().getCurrentSoldierCount()) + " solider(s)");
					logs.setFont(new Font("MonoSpaced", Font.ITALIC, 20));
					logs.setForeground(Color.red);
					cardChosenPlayer.setBorder(BorderFactory.createLineBorder(Color.red));
					cardChosenEnemey.setBorder(BorderFactory.createLineBorder(Color.red));
				}
			});
			timer.start();
			timer.setRepeats(false);

			
			// enemy tries to attack
			try {
				originalSol = cardChosenPlayer.getUnitFromCard().getCurrentSoldierCount();
				cardChosenEnemey.getUnitFromCard().attack(cardChosenPlayer.getUnitFromCard());
				revalidate();
				repaint();
			} catch (FriendlyFireException e1) {
				e1.printStackTrace();
			}
			
			if (cardChosenPlayer.getUnitFromCard().getCurrentSoldierCount() <= 0) {
				playerSide.remove(cardChosenPlayer);
				playerCards.remove(cardChosenPlayer);
				this.playerUnits.remove(cardChosenPlayer.getUnitFromCard());
				if (this.playerUnits.size() <= 0) {
					clip.stop();
					new BattleResultPage(false,s3,sAttack);
					return;
				}
				revalidate();
				repaint();
			}


			// here the computer chooses his pick from his selections
			
			infoBarTop.remove(temp);
			try {
				s = new CardInfo(cardChosenPlayer);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			temp = s;
			infoBarTop.add(temp);

			if (cardChosenPlayer.getUnitFromCard().getCurrentSoldierCount() <= 0) {
				playerSide.remove(cardChosenPlayer);
				playerCards.remove(cardChosenPlayer);
				this.playerUnits.remove(enemyPick.getUnitFromCard());
				revalidate();
				repaint();
				return;
			}
		

			revalidate();
			repaint();

		}

	}

	public void playMusic() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("bgBattlemusic.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(12);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}