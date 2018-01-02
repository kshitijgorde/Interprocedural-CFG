// 
// Decompiled by Procyon v0.5.30
// 

package Lb2_0;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Label;
import java.awt.Button;
import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class Lb2_0 extends Applet implements ActionListener
{
    String scriptName;
    String scriptDescription;
    String scriptCopyright;
    int iPageLength;
    int iPageWidth;
    int iLeftMargin;
    int iTopMargin;
    int iTextCount;
    int iPageNumber;
    int iPageCount;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Panel p;
    FontMetrics fm;
    pageCanvas pC;
    Button Next;
    Button Prev;
    vectorCanvas vC;
    Label lCopyright;
    URL aBase;
    
    public void init() {
        this.scriptName = this.getParameter("Script Name");
        this.scriptDescription = this.getParameter("Script Description");
        this.scriptCopyright = this.getParameter("Copyright");
        this.iPageLength = 380;
        this.iPageWidth = 600;
        this.iLeftMargin = 10;
        this.iTopMargin = 3;
        this.p = new Panel();
        this.gbl = new GridBagLayout();
        this.p.setLayout(this.gbl);
        this.gbc = new GridBagConstraints();
        this.Next = new Button("Next Page");
        this.Prev = new Button("Previous");
        this.Next.addActionListener(this);
        this.Prev.addActionListener(this);
        this.lCopyright = new Label("Script copyright " + this.scriptCopyright + ".  All rights reserved by Lazy Bee Scripts");
        this.gbc.fill = 0;
        this.gbc.gridx = 2;
        this.gbc.gridy = 6;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbl.setConstraints(this.Prev, this.gbc);
        this.p.add(this.Prev);
        this.gbc.gridx = 4;
        this.gbl.setConstraints(this.Next, this.gbc);
        this.p.add(this.Next);
        this.gbc.gridx = 1;
        this.gbc.gridy = 5;
        this.gbc.gridwidth = 5;
        this.gbl.setConstraints(this.lCopyright, this.gbc);
        this.p.add(this.lCopyright);
        this.iTextCount = 1;
        this.pC = new pageCanvas(this.iTextCount);
        this.formatCanvasPanel();
        this.pC.putStuff(0, "Welcome to the Lazy Bee Scripts Viewer v2.0", 0, 1, 24, 25, 25);
        this.pC.putStuff(1, "Please wait whilst " + this.scriptDescription + " loads", 0, 3, 18, 25, 50);
        this.p.add(this.pC);
        this.pC.repaint();
        this.p.validate();
        this.p.repaint();
        this.add("Center", this.p);
        this.setSize(600, 450);
        this.setVisible(true);
        this.scriptName += ".lbs";
        this.aBase = this.getCodeBase();
        this.vC = new vectorCanvas(10);
        final AImportThread threadIn = new AImportThread(this.vC, this.aBase, this.scriptName);
        threadIn.start();
        this.iPageNumber = 0;
        while (this.vC.getCurrentPage() < this.iPageNumber) {
            final int i = this.vC.getCurrentPage();
        }
        this.iPageCount = this.vC.getPageTotal();
        this.p.remove(this.pC);
        this.pC = this.vC.getItem(this.iPageNumber);
        this.formatCanvasPanel();
        this.p.add(this.pC);
        this.pC.repaint();
        this.p.validate();
        this.p.repaint();
        this.repaint();
    }
    
    public void formatCanvasPanel() {
        this.gbc.gridwidth = 6;
        this.gbc.gridheight = 4;
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.weightx = 6.0;
        this.gbc.weighty = 100.0;
        this.gbl.setConstraints(this.pC, this.gbc);
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("Next Page")) {
            this.p.remove(this.pC);
            ++this.iPageNumber;
            if (this.iPageNumber > this.iPageCount) {
                this.iPageNumber = this.iPageCount;
            }
            while (this.vC.getCurrentPage() < this.iPageNumber) {
                final int i = this.vC.getCurrentPage();
                System.out.println("Waiting in main thread");
            }
            this.pC = this.vC.getItem(this.iPageNumber);
            this.formatCanvasPanel();
            this.p.add(this.pC);
            this.pC.repaint();
            this.p.validate();
            this.p.repaint();
            this.repaint();
        }
        else if (e.getActionCommand().equals("Previous")) {
            this.p.remove(this.pC);
            --this.iPageNumber;
            if (this.iPageNumber < 0) {
                this.iPageNumber = 0;
            }
            this.pC = this.vC.getItem(this.iPageNumber);
            this.formatCanvasPanel();
            this.p.add(this.pC);
            this.pC.repaint();
            this.p.validate();
            this.p.repaint();
            this.repaint();
        }
    }
}
