package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.NotEnoughGoldException;
import units.Unit;

public class unitButton extends JButton {
	public unitButton(String name,int level,int currentsoldier) {
		setText(name+'\n'+level+'\n'+currentsoldier);
		setPreferredSize(new Dimension(250,80));
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		revalidate();
		repaint();
	}
}
