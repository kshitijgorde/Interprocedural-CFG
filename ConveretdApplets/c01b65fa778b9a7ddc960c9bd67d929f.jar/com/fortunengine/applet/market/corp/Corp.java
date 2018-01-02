// 
// Decompiled by Procyon v0.5.30
// 

package com.fortunengine.applet.market.corp;

import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.fortunengine.chart.ChartPanel;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class Corp extends Applet implements KeyListener, MouseListener, MouseMotionListener, ItemListener, Runnable
{
    private Thread thread;
    private FetchData agent;
    private char mkt;
    private String scode;
    private Label label;
    private Label appletTitle;
    private Choice choice;
    private ChartPanel[] chart;
    private String[][] tseArray;
    private String[][] otcArray;
    private String[][] allArray;
    
    public Corp() {
        this.tseArray = new String[][] { { "\u4e0a\u5e02\u6295\u4fe1", "X9999" }, { "\u4e0a\u5e02QFII", "Y9991" }, { "\u4e0a\u5e02\u5883\u5916\u83ef\u50d1", "Y9992" }, { "\u4e0a\u5e02\u5883\u5916\u57fa\u91d1", "Y9993" }, { "\u4e0a\u5e02\u5916\u8cc7\u5408\u8a08", "Y9999" }, { "\u4e0a\u5e02\u81ea\u71df\u5546", "Z9999" }, { "\u4e0a\u5e02\u6cd5\u4eba\u5408\u8a08", "T9999" } };
        this.otcArray = new String[][] { { "\u4e0a\u6ac3\u6295\u4fe1", "OX999" }, { "\u4e0a\u6ac3\u5916\u8cc7", "OY999" }, { "\u4e0a\u6ac3\u81ea\u71df\u5546", "OZ999" }, { "\u4e0a\u6ac3\u6cd5\u4eba\u5408\u8a08", "OT999" } };
        this.allArray = new String[][] { { "\u4e0a\u5e02\u6ac3\u5916\u8cc7", "TY999" }, { "\u4e0a\u5e02\u6ac3\u81ea\u71df\u5546", "TZ999" }, { "\u4e0a\u5e02\u6ac3\u6295\u4fe1", "TX999" }, { "\u4e0a\u5e02\u6ac3\u6cd5\u4eba\u5408\u8a08", "TT999" } };
        this.agent = null;
        this.mkt = 'T';
        this.scode = null;
        this.label = null;
        this.choice = null;
        this.chart = new ChartPanel[2];
    }
    
    public void destroy() {
        super.destroy();
    }
    
    public String getAppletInfo() {
        return "Buy\n\n\u8acb\u65bc\u6b64\u8655\u52a0\u5165\u985e\u578b\u7684\u8aaa\u660e\u3002\n\u5efa\u7acb\u65e5\u671f\uff1a(2001/11/28 \u4e0b\u5348 05:22:44)\n@\u4f5c\u8005\uff1a\n";
    }
    
    public void init() {
        super.init();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        final String upperCase = this.getParameter("mkt").trim().toUpperCase();
        if (upperCase.indexOf("TSE") >= 0) {
            this.mkt = 'T';
        }
        else if (upperCase.indexOf("OTC") >= 0) {
            this.mkt = 'O';
        }
        else if (upperCase.indexOf("ALL") >= 0) {
            this.mkt = 'A';
        }
        final int n = 2;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setBackground(Color.white);
        this.setLayout(layout);
        this.addKeyListener(this);
        final Font font = new Font("Dialog", 0, 15);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 13;
        (this.label = new Label()).setAlignment(2);
        this.label.setFont(font);
        this.label.setForeground(new Color(81, 0, 81));
        this.add(this.label);
        layout.setConstraints(this.label, gridBagConstraints);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 17;
        (this.choice = new Choice()).setFont(font);
        this.choice.setForeground(new Color(81, 0, 81));
        this.choice.addItemListener(this);
        this.add(this.choice);
        layout.setConstraints(this.choice, gridBagConstraints);
        switch (this.mkt) {
            case 'T': {
                for (int i = 0; i < this.tseArray.length; ++i) {
                    this.choice.add(this.tseArray[i][0]);
                }
                this.scode = this.tseArray[0][1];
                break;
            }
            case 'O': {
                for (int j = 0; j < this.otcArray.length; ++j) {
                    this.choice.add(this.otcArray[j][0]);
                }
                this.scode = this.otcArray[0][1];
                break;
            }
            case 'A': {
                for (int k = 0; k < this.allArray.length; ++k) {
                    this.choice.add(this.allArray[k][0]);
                }
                this.scode = this.allArray[0][1];
                break;
            }
        }
        this.agent = new FetchData(this.getCodeBase().getHost(), this.scode);
        final Font font2 = new Font("Dialog", 1, 20);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        final String parameter = this.getParameter("title");
        int int1 = 20;
        if (parameter != null && parameter.length() > 0) {
            final String parameter2 = this.getParameter("title_size");
            if (parameter2 != null && parameter2.length() > 0) {
                try {
                    int1 = Integer.parseInt(parameter2);
                    if (int1 <= 0) {
                        int1 = 20;
                    }
                }
                catch (Exception ex) {}
            }
            this.appletTitle = new Label(parameter);
        }
        else {
            this.appletTitle = new Label(null);
        }
        this.appletTitle.setAlignment(0);
        this.appletTitle.setFont(new Font("Dialog", 1, int1));
        this.appletTitle.setForeground(new Color(81, 0, 81));
        this.add(this.appletTitle);
        layout.setConstraints(this.appletTitle, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        (this.chart[0] = new ChartPanel(this.getBounds().width, (this.getBounds().height - 32) / 2)).addMouseMotionListener(this);
        this.chart[0].addKeyListener(this);
        this.chart[0].setDataPieces(4);
        this.chart[0].setDisplayTimeLabel(true);
        this.chart[0].setLabelFormat("###0.##");
        this.chart[0].setChartType(2);
        this.chart[0].setLineColor(1, new Color(122, 119, 0));
        this.chart[0].setXCoordFormat(n);
        this.chart[0].setSDate(this.agent.getSDate());
        this.chart[0].addChartData(this.agent.getAmt_b(), "\u8cb7\u9032 ", "\u5104\u5143");
        this.chart[0].addChartData(this.agent.getAmt_s(), "\u8ce3\u51fa ", "\u5104\u5143");
        this.add(this.chart[0]);
        layout.setConstraints(this.chart[0], gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        (this.chart[1] = new ChartPanel(this.getBounds().width, (this.getBounds().height - 32) / 2)).addMouseMotionListener(this);
        this.chart[1].addKeyListener(this);
        this.chart[1].setDataPieces(4);
        this.chart[1].setDisplayTimeLabel(false);
        this.chart[1].setLabelFormat("###0.##");
        this.chart[1].setChartType(1);
        this.chart[1].setXCoordFormat(n);
        this.chart[1].setSDate(this.agent.getSDate());
        this.chart[1].addChartData(this.agent.getAmt_net(), "\u8cb7\u8ce3\u5dee ", "\u5104\u5143");
        this.add(this.chart[1]);
        layout.setConstraints(this.chart[1], gridBagConstraints);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        boolean b = false;
        switch (keyEvent.getKeyCode()) {
            case 37: {
                b = false;
                break;
            }
            case 39: {
                b = true;
                break;
            }
        }
        try {
            int n = 0;
            while (true) {
                this.chart[n++].changeCursorPosition(b);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        mouseEvent.getComponent().requestFocus();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        try {
            int n = 0;
            while (true) {
                this.chart[n++].changeCursorPosition(x);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public void run() {
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.chart[0].clearChartData();
        this.chart[1].clearChartData();
        switch (this.mkt) {
            case 'T': {
                this.scode = this.tseArray[this.choice.getSelectedIndex()][1];
                break;
            }
            case 'O': {
                this.scode = this.otcArray[this.choice.getSelectedIndex()][1];
                break;
            }
            case 'A': {
                this.scode = this.allArray[this.choice.getSelectedIndex()][1];
                break;
            }
        }
        this.agent = new FetchData(this.getCodeBase().getHost(), this.scode);
        this.chart[0].setSDate(this.agent.getSDate());
        this.chart[0].addChartData(this.agent.getAmt_b(), "\u8cb7\u8d85 ", "\u5104\u5143");
        this.chart[0].addChartData(this.agent.getAmt_s(), "\u8ce3\u8d85 ", "\u5104\u5143");
        this.chart[1].setSDate(this.agent.getSDate());
        this.chart[1].addChartData(this.agent.getAmt_net(), "\u8cb7\u8ce3\u5dee ", "\u5104\u5143");
    }
}
