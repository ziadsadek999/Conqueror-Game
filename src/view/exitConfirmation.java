package view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Timer;

public class exitConfirmation extends JFrame implements ActionListener{
    JPanel req,questions,btns;
    JButton confirm,cancel;
    JLabel ques = new JLabel("Do want to Exit ?");
    Timer timer;
    
    public exitConfirmation(Timer timer) {
        // TODO Auto-generated constructor stub
        this.timer=timer;
        req = new JPanel();
        req.setLayout(new GridLayout(2,0));
        questions = new JPanel();
        questions.setLayout(new FlowLayout());
        questions.setBackground(Color.black);
        questions.add(ques);
        ques.setForeground(Color.white);
        ques.setFont(new Font ("Monospaced",Font.PLAIN,25));
        
        btns = new JPanel();
        btns.setLayout(new FlowLayout());
        confirm = new JButton("Exit");
        cancel = new JButton("Canel");
        btns.add(confirm);
        confirm.setForeground(Color.white);
        confirm.setBackground(Color.black);
        btns.add(cancel);
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        btns.setBackground(Color.black);
        req.add(questions);
        req.add(btns);
        
        cancel.addActionListener(this);
        confirm.addActionListener(this);
        
    
        add(req);
        setBackground(Color.black);
        setResizable(false);
        setTitle("Exit Confirmation");
        setUndecorated(true);
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == confirm) {
            timer.stop();
            System.exit(EXIT_ON_CLOSE);}
        else
            this.dispose();
    }
}