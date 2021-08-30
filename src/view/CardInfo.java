package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import units.Archer;

class CardInfo extends JPanel {
	JPanel main,sub,imgPanel,info ;
	JLabel unitName,lvl,soliders,max,temp;
	Color bgcolor ;
	Color fontColor = Color.black;
	Font font = new Font("MonoSpaced",Font.PLAIN,13);
	public CardInfo(unitCard card) throws IOException {
		bgcolor = card.getColorUnitCard();
		Border redline = BorderFactory.createLineBorder(Color.red);
		setBorder(redline);
		UnitCardImage img = new UnitCardImage(card.getUnitImgPath());
		img.setPreferredSize(new Dimension(50,60));
		
		
		main = new JPanel();
		sub = new JPanel ();
		imgPanel = new JPanel();
		info = new JPanel();
		unitName = new JLabel(card.getUnitName());
		max = new JLabel("Max Soliders : "+card.getUnitFromCard().getMaxSoldierCount());
		lvl = new JLabel("Level : "+card.getUnitFromCard().getLevel());
		soliders = new JLabel("Soliders : "+card.getUnitFromCard().getCurrentSoldierCount());
		temp = new JLabel();
		
		max.setHorizontalAlignment(JLabel.CENTER);
		unitName.setHorizontalAlignment(JLabel.CENTER);
		soliders.setHorizontalAlignment(JLabel.CENTER);
		lvl.setHorizontalAlignment(JLabel.CENTER);
		
		
		imgPanel.setBackground(bgcolor);
		sub.setBackground(bgcolor);
		main.setBackground(bgcolor);
		sub.setBackground(bgcolor);
		info.setBackground(bgcolor);
		
		soliders.setFont(font);
		unitName.setFont(font);
		lvl.setFont(font);
		max.setFont(font);
		
		soliders.setForeground(fontColor);
		unitName.setForeground(fontColor);
		lvl.setForeground(fontColor);
		max.setForeground(fontColor);
		
		
		main.setPreferredSize(new Dimension(200,160));
		sub.setPreferredSize(new Dimension(200,160));
		imgPanel.setPreferredSize(new Dimension(0,80));
		
		main.setLayout(new FlowLayout());
		sub.setLayout(new BorderLayout());
		info.setLayout(new GridLayout(5,0));
		
		info.add(unitName);
		info.add(lvl);
		info.add(soliders);
		info.add(max);
		info.add(temp);
		
		imgPanel.add(img);
		sub.add(imgPanel,BorderLayout.NORTH);
		sub.add(info,BorderLayout.CENTER);
		main.add(sub);
		add(main);
		
		setSize(new Dimension(200,160));
		setVisible(true);
	}
	
//	public static void main(String[] args) throws IOException {
//		JFrame f = new JFrame();
//		Archer a = new Archer(1, 30, 40, 15, 5);
//		unitCard s = new unitCard(a);
//		f.add(new CardInfo(s));
//		f.setLayout(new FlowLayout());
//		f.setVisible(true);
//		f.setSize(400, 400);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
