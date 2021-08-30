package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import engine.Game;
public class midScreen extends JPanel {
	mainScreen window;
	Game game;
	mapBackground mapView;
	JPanel left;
	JPanel right;
	
public midScreen(mainScreen w,Game g) {
	
	game = g;
	window = w;
	setLayout(new BorderLayout());
	setBackground(Color.black);
	setPreferredSize(new Dimension(window.getWidth(),window.getHeight()-130));
	left = new JPanel();
	left.setPreferredSize(new Dimension(300,getPreferredSize().height));
	left.setLayout(new GridLayout(0,1));
	add(left,BorderLayout.WEST);
	left.setBackground(new Color(209,159,102));
	right = new JPanel();
	right.setLayout(new BorderLayout());
	right.setPreferredSize(new Dimension(getPreferredSize().width-300,getPreferredSize().height));
	
	
	mapView = new mapBackground(game,this);

	
	right.add(mapView,BorderLayout.CENTER);
	add(right,BorderLayout.EAST);
	setVisible(true);
	revalidate();
	repaint();
}
}
