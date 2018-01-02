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

public class AverageOrderChartPApplet extends Applet implements ActionListener, KeyListener
{
    protected int width;
    protected int height;
    protected Label lSymbol;
    protected Label lDeltaPrice;
    protected TextField tSymbol;
    protected TextField tDeltaPrice;
    protected Panel content;
    protected Panel ctrPanel;
    protected PChartPanel cp;
    protected ToggleButton goButton;
    protected String symbol;
    
    public AverageOrderChartPApplet() {
    }
    
    public AverageOrderChartPApplet(final int width, final int height, final String serverURL) {
        this.width = width;
        this.height = height;
        this.lSymbol = new Label("Symbol");
        this.lDeltaPrice = new Label("Price range ±");
        (this.tSymbol = new TextField("", 5)).addKeyListener(this);
        (this.tDeltaPrice = new TextField(Float.toString(0.1f), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.ctrPanel = new Panel()).setBackground(Color.white);
        this.ctrPanel.add(this.lSymbol);
        this.ctrPanel.add(this.tSymbol);
        this.ctrPanel.add(this.lDeltaPrice);
        this.ctrPanel.add(this.tDeltaPrice);
        this.ctrPanel.add(this.goButton);
        (this.cp = new PChartPanel(width, height - 50)).setVisible(true);
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
        this.symbol = "";
        if (this.getParameter("symbol") != null) {
            this.symbol = this.getParameter("symbol");
        }
        if (this.getParameter("width") != null) {
            this.width = Integer.parseInt(this.getParameter("width"));
        }
        if (this.getParameter("height") != null) {
            this.height = Integer.parseInt(this.getParameter("height"));
        }
        final int oldShares = 100000;
        this.lSymbol = new Label("Symbol");
        this.lDeltaPrice = new Label("Price range ±");
        (this.tSymbol = new TextField(this.symbol, 5)).addKeyListener(this);
        (this.tDeltaPrice = new TextField(Float.toString(0.1f), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.ctrPanel = new Panel()).setBackground(Color.white);
        this.ctrPanel.add(this.lSymbol);
        this.ctrPanel.add(this.tSymbol);
        this.ctrPanel.add(this.lDeltaPrice);
        this.ctrPanel.add(this.tDeltaPrice);
        this.ctrPanel.add(this.goButton);
        (this.cp = new PChartPanel(this.width, this.height - 40)).setCodeBase(this.getCodeBase());
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
        float deltaPrice = new Float(this.tDeltaPrice.getText());
        if (deltaPrice < 0.01f) {
            this.cp.showMessage("Price range must not < 0.01");
            deltaPrice = 0.1f;
            this.tDeltaPrice.setText(Float.toString(deltaPrice));
        }
        this.cp.setSymbol(symbol);
        this.cp.setDeltaPrice(deltaPrice);
        this.cp.reset();
        this.cp.setStarted();
    }
    
    public static void main(final String[] argvs) {
        final Frame frame = new Frame("Test Applet");
        frame.add(new AverageOrderChartPApplet(500, 300, argvs[0]));
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
