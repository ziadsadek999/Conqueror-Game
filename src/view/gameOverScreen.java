package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOverScreen extends JPanel implements ActionListener {
	boolean winning;
	JPanel panel, main, Button;
	JLabel winStatus, gameOver, GameName;
	JButton exitButton;

	Font gameOverfont = new Font("Monospaced", Font.PLAIN, 130);
	Font winOrloseFont = new Font("Monospaced", Font.BOLD, 30);
	mainScreen window;
	BufferedImage backgroundImage;
	public gameOverScreen(boolean winning,mainScreen w) throws IOException {
		window = w;
		panel = new JPanel();
		backgroundImage = ImageIO.read(new File("conq.jpg"));
		setBackground(Color.black);
		main = new JPanel();
		add(main);
		main.setOpaque(false);
		main.setLayout(new FlowLayout());

		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(new Color (0,0,0,150) );
		panel.setPreferredSize(new Dimension(650, window.getHeight() - 45));
//		panel.setOpaque(false);
		main.add(panel);
		
		GameName = new JLabel("CONQUER");
		GameName.setHorizontalAlignment(JLabel.CENTER);
		GameName.setFont(gameOverfont);
		GameName.setForeground(Color.white);
		panel.add(GameName);
		
		
		gameOver = new JLabel("GAME OVER !");
		gameOver.setHorizontalAlignment(JLabel.CENTER);
		gameOver.setFont(new Font("Monospaced", Font.PLAIN, 50));
		gameOver.setForeground(Color.white);
		panel.add(gameOver);
		JPanel tmp = new JPanel();
		tmp.setOpaque(false);
		panel.add(tmp);
		winStatus = new JLabel();
		winStatus.setHorizontalAlignment(JLabel.CENTER);
	
		winStatus.setOpaque(true);;
		if(winning==true) {
			winStatus.setText("YOU WON !");
			winStatus.setForeground(Color.green);
			winStatus.setFont(winOrloseFont);
		}else {
			winStatus.setText("YOU LOST !");
//			winStatus.setBackground(new Color(147,18, 18, 150));
			winStatus.setForeground(Color.red);
			winStatus.setFont(winOrloseFont);
		}
		winStatus.setOpaque(false);
		winStatus.setPreferredSize(new Dimension(200,80));
		tmp.add(winStatus);
		
		

		Button = new JPanel();
		panel.add(Button);
		Button.setPreferredSize(new Dimension(250, 250));
		Button.setLayout(new FlowLayout());
		Button.setOpaque(false);
		

	
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(350, 40));
		exitButton.setForeground(Color.white);
		exitButton.setBackground(Color.black);

		exitButton.addActionListener(this);
		
		Button.add(exitButton);

		
		setBackground(Color.black);
		
		this.revalidate();
		this.repaint();
		setVisible(true);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			window.dispose();
		} else {
			
		}

	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
	  }
}
