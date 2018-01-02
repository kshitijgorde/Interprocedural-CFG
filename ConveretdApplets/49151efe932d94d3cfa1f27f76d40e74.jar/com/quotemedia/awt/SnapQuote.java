// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.awt;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class SnapQuote extends Frame implements ActionListener
{
    public QuotePage qp;
    Button oneDay;
    Button fiveDay;
    Button oneMonth;
    Button threeMonth;
    Button oneYear;
    Button fiveYear;
    Button tenYear;
    Button refresh;
    int THIS_WIDTH;
    int THIS_HEIGHT;
    
    public SnapQuote() {
        this.THIS_WIDTH = 510;
        this.THIS_HEIGHT = 215;
    }
    
    public SnapQuote(final String symbol, final Applet applet, final String wmid) {
        this.THIS_WIDTH = 510;
        this.THIS_HEIGHT = 215;
        this.oneDay = new Button("Today");
        this.fiveDay = new Button("5d");
        this.oneMonth = new Button("1mo");
        this.threeMonth = new Button("3m");
        this.oneYear = new Button("1y");
        this.fiveYear = new Button("5y");
        this.tenYear = new Button("10y");
        this.refresh = new Button("Refresh");
        this.oneDay.addActionListener(this);
        this.fiveDay.addActionListener(this);
        this.oneMonth.addActionListener(this);
        this.threeMonth.addActionListener(this);
        this.oneYear.addActionListener(this);
        this.fiveYear.addActionListener(this);
        this.tenYear.addActionListener(this);
        this.refresh.addActionListener(this);
        this.oneDay.setFont(new Font("SansSerif", 0, 9));
        this.fiveDay.setFont(new Font("SansSerif", 0, 9));
        this.oneMonth.setFont(new Font("SansSerif", 0, 9));
        this.threeMonth.setFont(new Font("SansSerif", 0, 9));
        this.oneYear.setFont(new Font("SansSerif", 0, 9));
        this.fiveYear.setFont(new Font("SansSerif", 0, 9));
        this.tenYear.setFont(new Font("SansSerif", 0, 9));
        this.setButtonBackground();
        this.oneDay.setBackground(Color.white);
        this.oneDay.setForeground(Color.black);
        this.fiveDay.setForeground(Color.black);
        this.oneMonth.setForeground(Color.black);
        this.threeMonth.setForeground(Color.black);
        this.oneYear.setForeground(Color.black);
        this.fiveYear.setForeground(Color.black);
        this.tenYear.setForeground(Color.black);
        this.refresh.setFont(new Font("SansSerif", 0, 10));
        this.refresh.setForeground(Color.black);
        final Panel blankLeft = new Panel();
        blankLeft.setSize(5, 5);
        final Panel blankRight = new Panel();
        blankRight.setSize(5, 5);
        final Panel bWest = new Panel();
        bWest.setLayout(new FlowLayout(0, 6, 2));
        bWest.add(this.oneDay);
        bWest.add(this.fiveDay);
        bWest.add(this.threeMonth);
        bWest.add(this.oneYear);
        bWest.add(this.fiveYear);
        bWest.add(this.tenYear);
        bWest.add(blankRight);
        final Panel blank1 = new Panel();
        blank1.setSize(10, 10);
        final Panel blank2 = new Panel();
        blank2.setSize(10, 10);
        final Panel beEast = new Panel();
        beEast.setLayout(new FlowLayout());
        beEast.add(this.refresh);
        beEast.add(blank1);
        final Panel bEast = new Panel();
        bEast.setLayout(new BorderLayout());
        bEast.add("East", beEast);
        final Panel bottom = new Panel();
        bottom.setLayout(new GridLayout(1, 2));
        bottom.add(bWest);
        bottom.add(bEast);
        this.setBackground(Color.white);
        (this.qp = new QuotePage(symbol, applet, wmid)).setBackground(Color.white);
        this.qp.setSize(510, 180);
        final Panel bbottom = new Panel();
        bbottom.setLayout(new BorderLayout());
        bbottom.add("Center", bottom);
        bbottom.add("South", blank2);
        this.setLayout(new BorderLayout());
        this.add("Center", this.qp);
        this.add("South", bbottom);
        this.repaint();
        this.setSize(this.THIS_WIDTH, this.THIS_HEIGHT);
        final Dimension sS = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((sS.width - this.THIS_WIDTH) / 2, (sS.height - this.THIS_HEIGHT) / 2);
        this.setResizable(false);
        this.setTitle("SnapQuote");
        this.addWindowListener(new CloseWindow());
        this.pack();
        this.setSymbol(symbol);
    }
    
    public void setSymbol(final String symbol) {
        this.qp.setSymbol(symbol);
        final String tchartScale = this.qp.getLastScale();
        this.setButtonBackground();
        if (tchartScale != null) {
            this.setSelectedBackground(tchartScale);
        }
    }
    
    public void highlight5Day() {
        this.setButtonBackground();
        this.fiveDay.setBackground(Color.white);
    }
    
    private void setButtonBackground() {
        this.oneDay.setBackground(Color.decode("#E7E7E7"));
        this.fiveDay.setBackground(Color.decode("#E7E7E7"));
        this.oneMonth.setBackground(Color.decode("#E7E7E7"));
        this.threeMonth.setBackground(Color.decode("#E7E7E7"));
        this.oneYear.setBackground(Color.decode("#E7E7E7"));
        this.fiveYear.setBackground(Color.decode("#E7E7E7"));
        this.tenYear.setBackground(Color.decode("#E7E7E7"));
        this.refresh.setBackground(Color.decode("#E7E7E7"));
    }
    
    public void update(final Graphics g) {
        super.repaint();
    }
    
    private void setSelectedBackground(final String cScale) {
        if (cScale.equals("1d")) {
            this.oneDay.setBackground(Color.white);
        }
        else if (cScale.equals("5d")) {
            this.fiveDay.setBackground(Color.white);
        }
        else if (cScale.equals("1m")) {
            this.oneMonth.setBackground(Color.white);
        }
        else if (cScale.equals("3m")) {
            this.threeMonth.setBackground(Color.white);
        }
        else if (cScale.equals("1y")) {
            this.oneYear.setBackground(Color.white);
        }
        else if (cScale.equals("5y")) {
            this.fiveYear.setBackground(Color.white);
        }
        else if (cScale.equals("10y")) {
            this.tenYear.setBackground(Color.white);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        final Object obj = e.getSource();
        String chScale = null;
        this.setButtonBackground();
        if (obj == this.refresh) {
            this.qp.refreshData();
            chScale = this.qp.getLastScale();
        }
        else {
            if (obj == this.oneDay) {
                chScale = "1d";
            }
            else if (obj == this.fiveDay) {
                chScale = "5d";
            }
            else if (obj == this.oneMonth) {
                chScale = "1m";
            }
            else if (obj == this.threeMonth) {
                chScale = "3m";
            }
            else if (obj == this.oneYear) {
                chScale = "1y";
            }
            else if (obj == this.fiveYear) {
                chScale = "5y";
            }
            else if (obj == this.tenYear) {
                chScale = "10y";
            }
            this.qp.getChartImage(this.qp.getSymbol(), chScale);
        }
        this.setSelectedBackground(chScale);
    }
    
    class CloseWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent e) {
            final Window w = (Window)e.getSource();
            w.setVisible(false);
        }
    }
}
