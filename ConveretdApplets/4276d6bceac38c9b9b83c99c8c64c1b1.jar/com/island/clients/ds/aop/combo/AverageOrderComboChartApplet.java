// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

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
import java.text.DecimalFormat;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class AverageOrderComboChartApplet extends Applet implements ActionListener, KeyListener
{
    protected String symbol;
    protected int width;
    protected int priceHeight;
    protected int historyHeight;
    protected int msgHeight;
    protected int timeLimit;
    protected Label lSymbol;
    protected Label lShares;
    protected Label lTimeUnit;
    protected TextField tSymbol;
    protected TextField tShares;
    protected Panel content;
    protected Panel topCtrPanel;
    protected Panel botCtrPanel;
    protected ChartPanel cp;
    protected ToggleButton goButton;
    protected Button zoomInButton;
    protected Button zoomOutButton;
    protected String zoomInButtonLabel;
    protected String zoomOutButtonLabel;
    protected DecimalFormat format;
    
    public AverageOrderComboChartApplet() {
        this.symbol = "QQQ";
        this.width = 550;
        this.priceHeight = 350;
        this.historyHeight = 200;
        this.msgHeight = 20;
        this.timeLimit = 600;
        this.zoomInButtonLabel = "Decrease";
        this.zoomOutButtonLabel = "Increase";
        this.format = new DecimalFormat("##0.##");
    }
    
    public AverageOrderComboChartApplet(final int width, final int priceHeight, final int historyHeight, final int msgHeight, final String serverURL, final int timeLimit) {
        this.symbol = "QQQ";
        this.width = 550;
        this.priceHeight = 350;
        this.historyHeight = 200;
        this.msgHeight = 20;
        this.timeLimit = 600;
        this.zoomInButtonLabel = "Decrease";
        this.zoomOutButtonLabel = "Increase";
        this.format = new DecimalFormat("##0.##");
        this.width = width;
        this.priceHeight = priceHeight;
        this.historyHeight = historyHeight;
        this.msgHeight = msgHeight;
        this.lSymbol = new Label("Symbol");
        this.lShares = new Label("Shares");
        (this.tSymbol = new TextField(this.symbol, 5)).addKeyListener(this);
        (this.tShares = new TextField(Integer.toString(ChartPanel.SHARES), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.zoomInButton = new Button(this.zoomInButtonLabel)).addActionListener(this);
        (this.zoomOutButton = new Button(this.zoomOutButtonLabel)).addActionListener(this);
        (this.topCtrPanel = new Panel()).setBackground(Color.white);
        this.topCtrPanel.add(this.lSymbol);
        this.topCtrPanel.add(this.tSymbol);
        this.topCtrPanel.add(this.lShares);
        this.topCtrPanel.add(this.tShares);
        this.topCtrPanel.add(this.goButton);
        (this.cp = new ChartPanel(width, priceHeight, historyHeight, msgHeight)).setVisible(true);
        this.cp.setTimeLimit(timeLimit);
        (this.botCtrPanel = new Panel()).setBackground(Color.white);
        this.lTimeUnit = new Label(this.getTimeUnitLabel());
        this.botCtrPanel.add(this.zoomInButton);
        this.botCtrPanel.add(this.lTimeUnit);
        this.botCtrPanel.add(this.zoomOutButton);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.add(this.topCtrPanel, "North");
        this.add(this.cp, "Center");
        this.add(this.botCtrPanel, "South");
        this.setVisible(true);
        try {
            this.cp.setCodeBase(new URL(serverURL));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void init() {
        this.showStatus("Initializing applet ...");
        if (this.getParameter("symbol") != null) {
            this.symbol = this.getParameter("symbol");
        }
        try {
            this.width = Integer.parseInt(this.getParameter("width"));
        }
        catch (Exception ex) {}
        try {
            this.priceHeight = Integer.parseInt(this.getParameter("priceheight"));
        }
        catch (Exception ex2) {}
        try {
            this.historyHeight = Integer.parseInt(this.getParameter("historyheight"));
        }
        catch (Exception ex3) {}
        try {
            this.msgHeight = Integer.parseInt(this.getParameter("messageheight"));
        }
        catch (Exception ex4) {}
        try {
            this.timeLimit = Integer.parseInt(this.getParameter("timelimit"));
        }
        catch (Exception ex5) {}
        this.lSymbol = new Label("Symbol");
        this.lShares = new Label("Shares");
        (this.tSymbol = new TextField("", 5)).addKeyListener(this);
        (this.tShares = new TextField(Integer.toString(ChartPanel.SHARES), 10)).addKeyListener(this);
        (this.goButton = new ToggleButton()).addActionListener(this);
        (this.zoomInButton = new Button(this.zoomInButtonLabel)).addActionListener(this);
        (this.zoomOutButton = new Button(this.zoomOutButtonLabel)).addActionListener(this);
        (this.topCtrPanel = new Panel()).setBackground(Color.white);
        this.topCtrPanel.add(this.lSymbol);
        this.topCtrPanel.add("SymbolText", this.tSymbol);
        this.topCtrPanel.add(this.lShares);
        this.topCtrPanel.add("ShareText", this.tShares);
        this.topCtrPanel.add(this.goButton);
        (this.cp = new ChartPanel(this.width, this.priceHeight, this.historyHeight, this.msgHeight)).setCodeBase(this.getCodeBase());
        this.cp.setApplet(this);
        this.cp.setVisible(true);
        this.cp.setTimeLimit(this.timeLimit);
        (this.botCtrPanel = new Panel()).setBackground(Color.white);
        this.lTimeUnit = new Label(this.getTimeUnitLabel());
        this.botCtrPanel.add(this.zoomInButton);
        this.botCtrPanel.add(this.lTimeUnit);
        this.botCtrPanel.add(this.zoomOutButton);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.add(this.topCtrPanel, "North");
        this.add(this.cp, "Center");
        this.add(this.botCtrPanel, "South");
        this.setVisible(true);
        this.tSymbol.setText(this.symbol);
    }
    
    public void destroy() {
        this.cp.destroy();
        super.destroy();
    }
    
    public void stop() {
        this.cp.destroy();
        super.stop();
    }
    
    public void start() {
        this.cp.toLive();
        if (!this.tSymbol.getText().equals("")) {
            this.goButton.setCommand("stop");
            this.reset();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String actionCommand = e.getActionCommand();
        if (actionCommand.equals(" go ")) {
            this.reset();
        }
        else if (actionCommand.equals("stop")) {
            this.cp.setIsPaused(true);
        }
        else if (actionCommand.equals(this.zoomInButtonLabel)) {
            if (!this.cp.zoomIn()) {
                this.zoomInButton.setEnabled(false);
            }
            this.zoomOutButton.setEnabled(true);
        }
        else if (actionCommand.equals(this.zoomOutButtonLabel)) {
            if (!this.cp.zoomOut()) {
                this.zoomOutButton.setEnabled(false);
            }
            this.zoomInButton.setEnabled(true);
        }
        this.lTimeUnit.setText(this.getTimeUnitLabel());
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
    
    protected String getTimeUnitLabel() {
        final StringBuffer buffer = new StringBuffer("Each line represents ");
        final int timeUnit = this.cp.getCurrentTimeUnit();
        if (timeUnit < 60) {
            buffer.append(timeUnit).append(" seconds");
        }
        else if (timeUnit < 3600) {
            buffer.append(this.format.format(timeUnit / 60.0)).append(" minutes");
        }
        else {
            buffer.append(this.format.format(timeUnit / 60.0 / 60.0)).append(" hours");
        }
        return buffer.toString();
    }
    
    public static void main(final String[] argvs) {
        final AverageOrderComboChartApplet applet = new AverageOrderComboChartApplet(500, 350, 200, 20, argvs[0], Integer.parseInt(argvs[1]) * 1000);
        final Frame frame = new Frame("Test Applet");
        frame.add(applet);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(500, 670);
        frame.setVisible(true);
        applet.start();
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
