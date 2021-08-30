package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import engine.City;
import engine.Game;
import units.Army;

public class attackWhileBeseige extends attackScreen{
	JLabel mes;
	public attackWhileBeseige(City c, Game g, screen3 s, Army attacker, Army defender) throws IOException {
		super(c, g, s, attacker, defender);
		prompt.remove(message);
		mes = new JLabel("SEIGE IS BROKEN",SwingConstants.CENTER);
		mes.setOpaque(false);
		mes.setForeground(Color.red);
		mes.setFont(f1);
		prompt.remove(buttons);
		prompt.add(mes);
		prompt.add(buttons);
		
	}

}
