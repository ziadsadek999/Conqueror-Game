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

import engine.City;
import engine.Game;
import units.Army;
import units.Status;
public class ArmyIcon extends JButton {
	Army army;
	BufferedImage icon;
	String location;
	int status;
	public ArmyIcon(Army army,mapBackground map) {
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(50,80));
		try {
			if(army.getCurrentStatus()==Status.IDLE)
				icon = ImageIO.read(new File("idle.png"));
				else if(army.getCurrentStatus()==Status.BESIEGING)
					icon = ImageIO.read(new File("beseiging.png"));
				else {
					if(army.getTarget().toLowerCase().equals("sparta"))
					icon = ImageIO.read(new File("marchingLeft.png"));
					else
						icon = ImageIO.read(new File("marching.png"));
				}
		} catch (IOException e) {
			
		}
		this.army = army;
		addActionListener(map);
		revalidate();
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
	}
	
}
