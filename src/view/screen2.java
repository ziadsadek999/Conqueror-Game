package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import engine.Game;

public class screen2 extends JPanel implements ActionListener {
	private Image backgroundImage;
	private JLabel GameName = new JLabel("CONQUER");
	private Font gameFont = new Font("Monospaced", Font.PLAIN, 80);
	private JLabel enterName;
	private JLabel chooseCity;
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private ButtonGroup cities;
	private JTextField name;
	private JButton start;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel main;
	private JLabel exitMessage;
	private JButton exit;
	private JLabel dummy;
	private mainScreen window;

	public screen2(mainScreen w) throws IOException {
		backgroundImage = ImageIO.read(new File("conq.jpg"));
		window = w;
		panel = new JPanel();
		panel.setOpaque(false);
		main = new JPanel();
		add(main);
		main.setOpaque(false);
		main.setLayout(new FlowLayout());

		panel.setLayout(new GridLayout(0, 1));
		panel.setPreferredSize(new Dimension(400, 850));
		main.add(panel);

		GameName.setHorizontalAlignment(JLabel.CENTER);
		GameName.setFont(gameFont);
		GameName.setForeground(Color.white);
		panel.add(GameName);

		panel2 = new JPanel();
		panel.add(panel2);
		panel2.setPreferredSize(new Dimension(250, 250));
		panel2.setLayout(new FlowLayout());
		panel2.setOpaque(false);
		enterName = new JLabel("Enter Your Name:");
		enterName.setPreferredSize(new Dimension(350, 40));
		enterName.setForeground(Color.white);
		enterName.setBackground(Color.black);

		panel2.add(enterName);

		panel3 = new JPanel();
		panel.add(panel3);
		panel3.setPreferredSize(new Dimension(250, 100));
		panel3.setLayout(new FlowLayout());
		panel3.setOpaque(false);
		name = new JTextField();
		name.setPreferredSize(new Dimension(350, 40));
		name.setForeground(Color.black);
		name.setBackground(Color.white);
		name.setFont(new Font("Monospaced", Font.PLAIN, 20));
		panel2.add(name);

		chooseCity = new JLabel("Choose a City:");
		chooseCity.setPreferredSize(new Dimension(350, 40));
		chooseCity.setForeground(Color.white);
		chooseCity.setBackground(Color.black);
		panel2.add(chooseCity);

		cities = new ButtonGroup();
		c1 = new JRadioButton("Cairo");
		c1.setPreferredSize(new Dimension(350, 40));
		c1.setForeground(Color.white);
		c1.setOpaque(false);
		c2 = new JRadioButton("Rome");
		c2.setPreferredSize(new Dimension(350, 40));
		c2.setForeground(Color.white);
		c2.setOpaque(false);
		c3 = new JRadioButton("Sparta");
		c3.setPreferredSize(new Dimension(350, 40));
		c3.setForeground(Color.white);
		c3.setOpaque(false);
		
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);

		panel2.add(c1);
		panel2.add(c2);
		panel2.add(c3);

		start = new JButton("GO!");
		start.setPreferredSize(new Dimension(350, 40));
		start.setForeground(Color.white);
		start.setBackground(Color.black);
		start.addActionListener(this);
		dummy = new JLabel();
		dummy.setPreferredSize(new Dimension(350, 150));
		dummy.setForeground(Color.RED);
		dummy.setBackground(Color.black);

		exitMessage = new JLabel("If you want to exit the game press ");
		exitMessage.setPreferredSize(new Dimension(195, 40));
		exitMessage.setForeground(Color.white);
		exitMessage.setBackground(Color.black);

		panel3.add(start);

		panel3.add(dummy);

		panel3.add(exitMessage);

		exit = new JButton("Exit");
		exit.setForeground(Color.white);
		exit.setBackground(Color.black);
		exit.addActionListener(this);
		panel3.add(exit);
		setBackground(Color.black);
		setVisible(true);
		this.revalidate();
		this.repaint();

	}

	public void actionPerformed(ActionEvent e)  {
		if (e.getSource() == exit) {
			window.dispose();
		} else {
			if (e.getSource() == start) {
				if (name.getText().equals("")) {
					dummy.setText("You need to write your name!");

				} else {
					if (c1.isSelected()) {
						try {
							window.remove(this);
							window.add(new screen3(name.getText(),"Cairo",window));
							window.revalidate();
							window.repaint();
						} catch (IOException e1) {
							dummy.setText("Failed to start the Game");
						}
					
					
						
					} else if (c2.isSelected()) {
						try {
							window.remove(this);
							window.add(new screen3(name.getText(),"Rome",window));
							window.revalidate();
							window.repaint();
						} catch (IOException e1) {
							dummy.setText("Failed to start the Game");
						}
						
					} else if (c3.isSelected()) {
						try {
							window.remove(this);
							window.add(new screen3(name.getText(),"Sparta",window));
							
							window.revalidate();
							window.repaint();
						} catch (IOException e1) {
							dummy.setText("Failed to start the Game");
						}
						
					} else {
						dummy.setText("You need to choose a city!");
					}
				}

			}
		}
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
	  }
	

}