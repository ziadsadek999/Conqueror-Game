package view;
import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CityView extends JPanel{
	public JPanel TransparentPanel;
	private Image backgroundImage;
	public GridBagConstraints c;
	
	public CityView(String s) throws IOException {
		backgroundImage = ImageIO.read(new File(s));
		setLayout(new GridLayout());
		TransparentPanel = new JPanel();
		TransparentPanel.setLayout(new GridBagLayout());
		TransparentPanel.setOpaque(false);
		c= new GridBagConstraints();
		
		
		this.add(TransparentPanel);
		
		
		this.revalidate();
		this.repaint();
		
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
	  }
	

}
