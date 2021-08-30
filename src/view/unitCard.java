package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import units.*;

public class unitCard extends JPanel {
	private Unit u;
	private int level;
	private String name, ImgPath;
	private Color color;
	private CardMode mode;
	UnitCardImage unitImg;

	JPanel mainPanel, subPanel, contentPanel, InfoPanel;
	JLabel lvl, unitName;

	public unitCard(Unit u,CardMode mode) throws IOException {
		this.u = u;
		this.mode = mode;
		Border redline = BorderFactory.createLineBorder(Color.red);
		setBorder(redline);
		int height = 100;
		int width = 80;
		setPreferredSize(new Dimension(width, height));
		mainPanel = new JPanel();
		subPanel = new JPanel();
		InfoPanel = new JPanel();
		contentPanel = new JPanel();

		mainPanel.setLayout(new FlowLayout());
		subPanel.setLayout(new GridLayout(0, 1));
		contentPanel.setLayout(new GridLayout(2, 0, 0, 10));
		InfoPanel.setLayout(new GridLayout(2, 0, 0, 20));

		subPanel.setPreferredSize(new Dimension(width , height ));
		InfoPanel.setPreferredSize(new Dimension(50, 60));

		SetWhenLevel(u.getLevel());
		SetForUnitType(u);

		// Setting Corresponding color
		mainPanel.setBackground(color);
		InfoPanel.setBackground(color);
		contentPanel.setBackground(color);
		subPanel.setBackground(color);
		setBackground(color);

		// setting the imagePanel (unitImg)
		unitImg = new UnitCardImage(ImgPath);
		unitImg.setPreferredSize(new Dimension(60, 50));

		// InfoPanel Modifications
		unitName = new JLabel(name);
		unitName.setHorizontalAlignment(JLabel.CENTER);
		lvl.setHorizontalAlignment(JLabel.CENTER);
		unitName.setFont(new Font("MonoSpaced", Font.BOLD, 13));
		lvl.setFont(new Font("MonoSpaced", Font.BOLD, 13));
		InfoPanel.add(unitName);
		InfoPanel.add(lvl);

		contentPanel.add(unitImg);
		contentPanel.add(InfoPanel);

		subPanel.add(contentPanel);
		mainPanel.add(subPanel);
		add(mainPanel);
		revalidate();
		repaint();
	}
	
	public String getUnitName () {
		return this.name;
	}
	
	public String getUnitImgPath () {
		return this.ImgPath;
	}
	
	public int getLevel () {
		return this.level;
	}
	
	public Unit getUnitFromCard () {
		return this.u;
	}
	
	public Color getColorUnitCard () {
		return color;
	}
	
	public CardMode getMode () {
		return this.mode;
	}
	
	public void setMode (CardMode m) {
		this.mode = m;
	}
	
	public void SetWhenLevel(int x) {
		if (x == 1) {
			color = new Color(165, 117, 27);
			lvl = new JLabel("Level: 1");
		} else if (x == 2) {
			color = new Color(183, 206, 210);
			lvl = new JLabel("Level: 2");
		} else {
			color = new Color(248, 200, 59);
			lvl = new JLabel("Level: 3");
		}
	}

	public void SetForUnitType(Unit u) {
		if (u instanceof Archer) {
			name = "Archer";
			ImgPath = "Archer.png";
		} else if (u instanceof Cavalry) {
			name = "Cavlary";
			ImgPath = "Cavlary.png";
		} else {
			name = "Infantry";
			ImgPath = "Infantry.png";
		}
	}

//	public static void main(String[] args) throws IOException {
//		JFrame frame = new JFrame();
//		Infantry c = new Infantry(2, 1, 0.6, 0.2, 0.5);
//		Cavalry a = new Cavalry(1, 1, 0.6, 0.2, 0.5);
//		Archer b = new Archer(3, 1, 0.6, 0.2, 0.5);
//		
//		unitCard u1 = new unitCard(c);
//		unitCard u6 = new unitCard(a);
//		unitCard u5 = new unitCard(b);
//		unitCard u4 = new unitCard(a);
//		unitCard u3 = new unitCard(c);
//		unitCard u2 = new unitCard(c);
//		unitCard u7 = new unitCard(a);
//		unitCard u8 = new unitCard(b);
//		unitCard u9 = new unitCard(b);
//		unitCard u10 = new unitCard(c);
//		u1.setPreferredSize(new Dimension(10,20));
//		
//		unitCard u11 = new unitCard(c);
//		unitCard u61 = new unitCard(a);
//		unitCard u51 = new unitCard(b);
//		unitCard u41 = new unitCard(a);
//		unitCard u31 = new unitCard(c);
//		unitCard u21 = new unitCard(c);
//		unitCard u71 = new unitCard(a);
//		unitCard u81 = new unitCard(b);
//		unitCard u91 = new unitCard(b);
//		unitCard u101 = new unitCard(c);
//		
//		JPanel bottom = new JPanel();
//		bottom.setBackground(Color.black);
//		bottom.setLayout(new GridLayout(0,10,10,0));
//		bottom.setPreferredSize(new Dimension(0,150));
//		bottom.add(u11);bottom.add(u21);bottom.add(u31);bottom.add(u41);bottom.add(u51);bottom.add(u61);bottom.add(u71);bottom.add(u81);bottom.add(u91);bottom.add(u101);
//		frame.add(bottom,BorderLayout.SOUTH);
//		
//		JPanel center = new JPanel();
//		center.setLayout(new FlowLayout());
//		center.setBackground(Color.BLACK);
//		frame.add(center,BorderLayout.CENTER);
//		center.setPreferredSize(new Dimension(800,800));
//		center.add(new JLabel(new ImageIcon("image//battle.gif")));
//		
//		JPanel top = new JPanel();
//		top.setBackground(Color.black);
//		top.setLayout(new GridLayout(0,10,10,0));
//		top.setPreferredSize(new Dimension(0,150));
//		top.add(u1);top.add(u2);top.add(u3);top.add(u4);top.add(u5);top.add(u6);top.add(u7);top.add(u8);top.add(u9);top.add(u10);
//		frame.add(top,BorderLayout.NORTH);
////		frame.add(new unitCard(c));
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.revalidate();
//		frame.repaint();
//		frame.setVisible(true);
//	}

}

class UnitCardImage extends JPanel {
	private Image backgroundImage;

	public UnitCardImage(String str) throws IOException {
		backgroundImage = ImageIO.read(new File(str));
		this.setLayout(new GridLayout());
		this.revalidate();
		this.repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

}
