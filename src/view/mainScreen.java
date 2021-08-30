package view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import engine.*;

public class mainScreen extends JFrame  {

	public mainScreen() throws IOException {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		add(new screen1(this));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.black);
		this.revalidate();
		this.repaint();
		setVisible(true);
		//pack();
	}
	public static void main(String[] args) throws IOException {
		new mainScreen();
	}
	

}
