// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class AverageOrderChartApplet extends Applet implements ActionListener, KeyListener
{
    protected int width;
    protected int height;
    protected Label lSymbol;
    protected Label lShares;
    protected TextField tSymbol;
    protected TextField tShares;
    protected Panel content;
    protected Panel ctrPanel;
    protected ChartPanel cp;
    protected ToggleButton goButton;
    protected String symbol;
    
    public AverageOrderChartApplet() {
        this.symbol = "";
    }
    
    public AverageOrderChartApplet(final int width, final int height, final String serverURL) {
        this.symbol = "";
        this.width = width;
        this.height = height;
        this.lSymbol = new Label("Symbol");
        this.lShares = new Label("Shares");
        (this.tSymbol = new TextField("QQQ", 5)).addKeyListener(this);
        (this.tShares = new TextField(Integer.toString(ChartPanel.SHARES), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.ctrPanel = new Panel()).setBackground(Color.white);
        this.ctrPanel.add(this.lSymbol);
        this.ctrPanel.add(this.tSymbol);
        this.ctrPanel.add(this.lShares);
        this.ctrPanel.add(this.tShares);
        this.ctrPanel.add(this.goButton);
        (this.cp = new ChartPanel(width, height - 50)).setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.add(this.cp, "Center");
        this.add(this.ctrPanel, "North");
        this.setVisible(true);
        try {
            this.cp.setCodeBase(new URL(serverURL));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        this.cp.toLive();
    }
    
    public void init() {
        this.showStatus("Initializing applet ...");
        if (this.getParameter("symbol") != null) {
            this.symbol = this.getParameter("symbol");
        }
        else {
            this.symbol = "";
        }
        if (this.getParameter("width") != null) {
            this.width = Integer.parseInt(this.getParameter("width"));
        }
        else {
            this.width = 500;
        }
        if (this.getParameter("height") != null) {
            this.height = Integer.parseInt(this.getParameter("height"));
        }
        else {
            this.height = 350;
        }
        final int oldShares = ChartPanel.SHARES;
        this.lSymbol = new Label("Symbol");
        this.lShares = new Label("Shares");
        (this.tSymbol = new TextField(this.symbol, 5)).addKeyListener(this);
        (this.tShares = new TextField(Integer.toString(ChartPanel.SHARES), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.ctrPanel = new Panel()).setBackground(Color.white);
        this.ctrPanel.add(this.lSymbol);
        this.ctrPanel.add(this.tSymbol);
        this.ctrPanel.add(this.lShares);
        this.ctrPanel.add(this.tShares);
        this.ctrPanel.add(this.goButton);
        (this.cp = new ChartPanel(this.width, this.height - 40)).setCodeBase(this.getCodeBase());
        this.cp.setApplet(this);
        this.cp.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.add(this.cp, "Center");
        this.add(this.ctrPanel, "North");
        this.setVisible(true);
    }
    
    public void stop() {
        this.cp.destroy();
        super.stop();
    }
    
    public void destroy() {
        this.cp.destroy();
        super.destroy();
    }
    
    public void start() {
        this.cp.toLive();
        if (!this.symbol.equals("")) {
            this.reset();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String actionCommand = e.getActionCommand();
        if (actionCommand.equals(" go ")) {
            this.reset();
            this.cp.setIsPaused(false);
        }
        else if (actionCommand.equals("stop")) {
            this.cp.setIsPaused(true);
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 10) {
            this.goButton.setCommand("stop");
            this.reset();
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    protected void reset() {
        final String symbol = this.tSymbol.getText().toUpperCase();
        this.tSymbol.setText(symbol);
        int shares = Integer.parseInt(this.tShares.getText());
        if (shares <= 0) {
            this.cp.showMessage("Shares must > 0");
            shares = ChartPanel.SHARES;
            this.tShares.setText(Integer.toString(shares));
        }
        this.cp.setSymbol(symbol);
        this.cp.setShares(shares);
        this.cp.reset();
        this.cp.setStarted();
    }
    
    public static void main(final String[] argvs) {
        final Frame frame = new Frame("Test Applet");
        frame.add(new AverageOrderChartApplet(500, 300, argvs[0]));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
    
    class ToggleButton extends Button implements ActionListener
    {
        static final String START = " go ";
        static final String STOP = "stop";
        
        public ToggleButton() {
            super(" go ");
            this.addActionListener(this);
        }
        
        public void setCommand(final String command) {
            if (command.equals(" go ")) {
                this.setLabel(" go ");
            }
            else if (command.equals("stop")) {
                this.setLabel("stop");
            }
        }
        
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand().equals(" go ")) {
                this.setLabel("stop");
            }
            else if (e.getActionCommand().equals("stop")) {
                this.setLabel(" go ");
            }
        }
    }
}
