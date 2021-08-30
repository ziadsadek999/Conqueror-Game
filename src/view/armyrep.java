package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.hamcrest.core.IsInstanceOf;

import engine.City;
import engine.Game;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;


public class armyrep extends JPanel {
    public Army army;
    public ArrayList<Unit> units;
    public Unit infantry;
    public Unit cavalry;
    public Unit archer;
    public Color color;
    public City city;
    public Game game;
    
   private Font f=new Font("Monospaced",Font.PLAIN,20);
    private Font f1=new Font("Monospaced",Font.PLAIN,15);


    public JLabel x1;
    public JButton x2;
    public JPanel t;
    boolean flag;
    public String getType(Unit u) {
        if (u instanceof Infantry) return "INFANTRY";
        if (u instanceof Cavalry) return "CAVALRY";
        if (u instanceof Archer) return "ARCHER";
        return "";
        
    }
    public armyrep(Game game,City city,ArrayList<Unit> units, Army army,Color color,JLabel a,boolean flag) {
    	this.setLayout(new GridLayout(1,1));
    	t=new JPanel();
        this.city=city;
        this.flag=flag;
        this.color=color;
        this.army=army;
        this.units=units;
        this.game=game;
        int n= (units.size()+1)<4?4:(units.size()+1);
        t.setLayout(new GridLayout(n,1));
        
        t.add(a);
       a.setPreferredSize(new Dimension(50, 100));
        
        
        for (Unit u : units) {
            JLabel z= new JLabel();
             z.setLayout(new GridLayout(4,1));
             
             JLabel y= new JLabel(getType(u));
             y.setForeground(color.white);
             y.setFont(f1);
             z.add(y);
             JLabel y1= new JLabel("LEVEL:"+u.getLevel());
             y1.setFont(f1);
             y1.setForeground(color.white);
             z.add(y1);
             JLabel y2= new JLabel("SOLIDIER COUNT:"+u.getCurrentSoldierCount());
             y2.setFont(f1);
             y2.setForeground(color.white);
             z.add(y2);
             JLabel y3= new JLabel("MAX SOLIDIER COUNT:"+u.getMaxSoldierCount());
             y3.setFont(f1);
             y3.setForeground(color.white);
             z.add(y3);
            
             x2= new JButton();
             x2.setPreferredSize(new Dimension(100, this.getWidth()));
            x2.add(z);
            x2.setLayout(new GridLayout());
            x2.setBackground(Color.black);
            x2.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                   if (flag) {
                       game.getPlayer().initiateArmy(city,u);
                       
                           removeAll();
                           setLayout(new GridLayout(8,1));
                           JTextPane v= new JTextPane();
                           JButton z = new JButton("back to armies");
                           z.setBackground(color.black);
                           z.setForeground(color.white);
                           JLabel o=new JLabel();
						   o.setLayout(new GridLayout(2,1));
						   o.add(z);
                             v.setOpaque(false);
                             v.setText("ARMY INITIATED SUCCESSFULLY!");
                             v.setEditable(false);
                             z.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									removeAll();
									 setLayout(new GridLayout());
									 add(new armyinfo(game, city.getName(), color));
									 revalidate();
									 repaint();
									
								}
							});
                               v.setForeground(new Color(44,100,23));
                               v.setFont(f1);
                               add(v);
                               add(o);
                               revalidate();
                               repaint();
                      
                   }
                   else {
                       a.removeAll();
                       a.setLayout(new GridLayout());
                       JButton v= new JButton("RELOCATE THIS UNIT");
                      v.setBackground(new Color(102,0,0));
                      v.setForeground(Color.black);
                      a.add(v);
                      v.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeAll();
                            setLayout(new GridLayout());
                            add(new armyReloc(u, game, city.getName(), color));
                            revalidate();
                            repaint();
                            
                        }
                    });
                       revalidate();
                       repaint();
                   }
                }
            });
            t.add(x2);
            
        }
        
        t.setBackground(color);
        JScrollPane scroller = new JScrollPane(t);
        this.add(scroller);
        this.setBackground(color);
        this.revalidate();
        this.repaint();
    }
    
    
    
        
        
    }