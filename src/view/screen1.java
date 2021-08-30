package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class screen1 extends JPanel implements ActionListener {
    private Image backgroundImage;
    private JLabel GameName = new JLabel(""); 
    private Font gameFont = new Font("Monospaced",Font.PLAIN,80);
    private JButton startButton;
    private JButton exitButton;
    private JPanel panel;
    private JPanel Button;
    private JPanel main;
    private mainScreen window;
    public screen1(mainScreen w) throws IOException {
        backgroundImage = ImageIO.read(new File("conq1_auto_x2.png"));
        window = w;
        panel = new JPanel();
        setBackground(Color.black);
        main = new JPanel();
        add(main);
        main.setOpaque(false);
        main.setLayout(new FlowLayout());
       // main.setPreferredSize(new Dimension(w.getWidth(),w.getHeight()));
        panel.setLayout(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(400, 850));
        panel.setOpaque(false);
        main.add(panel);
        GameName.setHorizontalAlignment(JLabel.CENTER);
        GameName.setFont(gameFont);
        GameName.setForeground(Color.white);
        panel.add(GameName);
        Button = new JPanel();
        panel.add(Button);
        Button.setPreferredSize(new Dimension(250,250));
        Button.setLayout(new FlowLayout());
        Button.setBackground(Color.black);
        startButton = new JButton("Play");
        startButton.setPreferredSize(new Dimension(350,40));
        startButton.setForeground(Color.white);
        startButton.setBackground(Color.black);
        Button.add(startButton);
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(350,40));
        exitButton.setForeground(Color.white);
        exitButton.setBackground(Color.black);
        exitButton.addActionListener(this);
        startButton.addActionListener(this);
        Button.add(exitButton);  
        setBackground(Color.black);
        this.revalidate();
        this.repaint();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton) {
            window.remove(this);
            try {
				window.add(new screen2(window));
			} catch (IOException e1) {
				window.dispose();
			}
            window.revalidate();
            window.repaint();
        }
        else {
            window.dispose();
        }   
    }
    public JLabel getGameName() {
        return GameName;
    }
    public void setGameName(JLabel gameName) {
        GameName = gameName;
    }
    public Font getGameFont() {
        return gameFont;
    }
    public void setGameFont(Font gameFont) {
        this.gameFont = gameFont;
    }
    public JButton getStartButton() {
        return startButton;
    }
    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }
    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    public JPanel getButton() {
        return Button;
    }
    public void setButton(JPanel button) {
        Button = button;
    }
    public JPanel getMain() {
        return main;
    }
    public void setMain(JPanel main) {
        this.main = main;
    }
    public mainScreen getWindow() {
        return window;
    }
    public void setWindow(mainScreen window) {
        this.window = window;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
      } 
}