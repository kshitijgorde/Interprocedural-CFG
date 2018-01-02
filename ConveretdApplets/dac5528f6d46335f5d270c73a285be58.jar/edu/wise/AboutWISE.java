// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import edu.wise.correl.gui.StyleSheet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.Panel;

public class AboutWISE extends Panel
{
    private static TextArea ta;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    
    public AboutWISE(final int n, final int n2) {
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        (AboutWISE.ta = new TextArea("", 18, 20, 1)).setSize(n - 2, n2 - 2);
        AboutWISE.ta.setEditable(false);
        AboutWISE.ta.setBackground(StyleSheet.BACKGROUND);
        AboutWISE.ta.setFont(StyleSheet.f_reg);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.fill = 1;
        this.gbc.anchor = 10;
        this.gbc.insets = new Insets(1, 3, 3, 3);
        this.gbc.weightx = 1.0;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbl.setConstraints(this.add(AboutWISE.ta), this.gbc);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    public void setText(final String text) {
        AboutWISE.ta.setText(text);
    }
}
