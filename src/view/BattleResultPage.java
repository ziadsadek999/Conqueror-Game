package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattleResultPage extends JFrame implements ActionListener{
    JPanel req,questions,btns;
    JButton confirm,cancel;
    JLabel ques = new JLabel();
	boolean winState;
	Color victory = Color.green;
	Color lost = Color.red;
	screen3 s3;
	JPanel sAttack;
	public BattleResultPage(boolean winState,screen3 s,JPanel s2) {
		sAttack = s2;
		s3 = s;
		this.winState = winState;
		req = new JPanel();
        req.setLayout(new GridLayout(2,0));
        questions = new JPanel();
        questions.setLayout(new FlowLayout());
        questions.setBackground(Color.black);
        questions.add(ques);
        ques.setText(winState?"YOU HAVE WON THE BATTLE":"YOU HAVE LOST THE BATTLE !");
        ques.setForeground(winState? victory:lost);
        ques.setFont(new Font ("Monospaced",Font.PLAIN,20));
        
        btns = new JPanel();
        btns.setLayout(new FlowLayout());
        confirm = new JButton("Back to Map");
        confirm.setFont(new Font("MonoSpaced",Font.PLAIN,20));
        
        btns.add(confirm);
        
        confirm.setForeground(Color.white);
        confirm.setBackground(Color.black);
        btns.setBackground(Color.black);
        req.add(questions);
        req.add(btns);
        

        confirm.addActionListener(this);
        
    
        add(req);
        setBackground(Color.black);
        setResizable(false);
        setTitle("Your status");
        setUndecorated(true);
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, winState?victory:lost));
        setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		s3.removeAll();
		s3.add(s3.main);
		s3.mid.remove(sAttack);
		s3.mid.right.setVisible(true);
		s3.mid.left.setVisible(true);
		s3.mid.left.removeAll();
		s3.endTurn.addActionListener(s3);
		s3.revalidate();
		s3.repaint();
		dispose();
	}
}