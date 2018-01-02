// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public class DataPanel extends Panel
{
    GridBagLayout gridbag;
    GridBagConstraints c;
    private int iAlign;
    
    public DataPanel() {
        this(0, 0);
    }
    
    public DataPanel(final int n, final int n2) {
        this.iAlign = 2;
        this.gridbag = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.setLayout(this.gridbag);
        this.c.anchor = 10;
        this.c.fill = 1;
        if (System.getProperty("java.vendor").toLowerCase().startsWith("apple")) {
            this.c.ipadx = 1 + n;
            this.c.ipady = 1 + n2;
        }
        else {
            this.c.ipadx = -1 + n;
            this.c.ipady = -1 + n2;
        }
        this.c.insets = new Insets(0, 0, 0, 0);
    }
    
    private void addLabel(final String s, final Font font, final int n) {
        final Label label = new Label(s, n);
        label.setFont(font);
        this.add(label);
        this.gridbag.setConstraints(label, this.c);
    }
    
    public void addRow(final Nbr nbr, final Nbr nbr2, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, nbr2.getLabel(), nbr2, font, n);
    }
    
    public void addRow(final Nbr nbr, final Nbr nbr2, final Label label, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, nbr2.getLabel(), nbr2, label, font, n);
    }
    
    public void addRow(final Nbr nbr, final Slider slider, final Font font, final int n) {
        this.addRow(nbr.getLabel(), font, nbr, slider, font, n);
    }
    
    public void addRow(final Nbr nbr, final Component component, final Font font, final int n) {
        this.addRow(nbr.getLabel(), font, nbr, component, font, n);
    }
    
    public void addRow(final Nbr nbr, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, font, n);
    }
    
    public void addRow(final Nbr nbr, final Label label, final Nbr nbr2, final Label label2, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, label, nbr2.getLabel(), nbr2, label2, font, n);
    }
    
    public void addRow(final Nbr nbr, final String s, final Component component, final Font font, final int n) {
        this.addRow(nbr.getLabel(), nbr, s, component, font, n);
    }
    
    public void addRow(final Component component, final Nbr nbr, final Nbr nbr2, final Font font, final int n) {
        this.addRow(component, nbr, new Label(nbr2.getLabel(), this.iAlign), nbr2, font, n);
    }
    
    public void addRow(final Component component, final Component component2, final Component component3, final Component component4, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 3;
        this.c.anchor = 13;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        component.resize(component.size().width / 2, component.size().height);
        this.c.gridx = 3;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.c.anchor = 17;
        component2.setFont(font);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 3;
        this.c.anchor = 13;
        component3.setFont(font);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.c.anchor = 17;
        component4.setFont(font);
        this.add(component4);
        this.gridbag.setConstraints(component4, this.c);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.c.anchor = 17;
        this.addLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final Component component, final Component component2, final Font font, final int n) {
        this.addRow(component, component2, font, true, n);
    }
    
    public void addRow(final Component component, final Component component2, final Font font, final boolean b, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        component2.setFont(font);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        if (b) {
            this.c.gridx = 10;
            this.c.gridy = gridy;
            this.c.weightx = 1.0;
            this.c.gridwidth = 1;
            this.addLabel(" ", font, this.iAlign);
        }
    }
    
    public void addRow(final Component component, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridheight = 1;
        this.c.gridwidth = 0;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final Component component, final Font font, final int gridy, final int gridheight) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        final int gridheight2 = this.c.gridheight;
        this.c.gridheight = gridheight;
        this.c.gridwidth = 0;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridheight = gridheight2;
    }
    
    public void addRow(final Label label, final Label label2, final Font font, final int n) {
        this.addRow(label, (Component)label2, font, n);
    }
    
    public void addRow(final String s, final Component component, final Component component2, final Font font, final int n) {
        this.addRow(s, font, component, component2, font, n);
    }
    
    public void addRow(final String s, final Component component, final Font font, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 2;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        component.setFont(font);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final String s, final Component component, final Label label, final String s2, final Component component2, final Label label2, final Font font, final int n) {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(component);
        panel.add(label);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(component2);
        panel2.add(label2);
        this.addRow(s, panel, s2, panel2, font, n);
    }
    
    public void addRow(final String s, final Component component, final String s2, final Component component2, final Font font, final int n) {
        this.addRow(new Label(String.valueOf(s) + " ", this.iAlign), component, new Label(String.valueOf(s2) + " ", this.iAlign), component2, font, n);
    }
    
    public void addRow(final String s, final Component component, final String s2, final Component component2, final Label label, final Font font, final int n) {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(component2);
        panel.add(label);
        this.addRow(s, component, s2, panel, font, n);
    }
    
    public void addRow(final String s, final Font font, final Component component, final Component component2, final Component component3, final Component component4, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component3.setFont(font2);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 10;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component4.setFont(font2);
        this.add(component4);
        this.gridbag.setConstraints(component4, this.c);
        this.c.gridx = 12;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final String s, final Font font, final Component component, final Component component2, final Component component3, final Font font2, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 6;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component3.setFont(font2);
        this.add(component3);
        this.gridbag.setConstraints(component3, this.c);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final String s, final Font font, final Component component, final Component component2, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        component2.setFont(font2);
        this.add(component2);
        this.gridbag.setConstraints(component2, this.c);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        this.addLabel(" ", font2, this.iAlign);
    }
    
    public void addRow(final String s, final Font font, final Component component, final Font font2, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 2;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        component.setFont(font2);
        this.add(component);
        this.gridbag.setConstraints(component, this.c);
    }
    
    public void addRow(final String s, final String s2, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        this.addLabel(String.valueOf(s) + " ", font, 1);
        this.c.gridx = 5;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 5;
        this.addLabel(s2, font, 1);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final String s, final String s2, final String s3, final String s4, final Font font, final int gridy) {
        this.c.gridx = 0;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s2, font, 1);
        this.c.gridx = 6;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s3, font, 1);
        this.c.gridx = 8;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s4, font, 1);
        this.c.gridx = 10;
        this.c.gridy = gridy;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(" ", font, this.iAlign);
    }
    
    public void addRow(final String s, final String s2, final String s3, final String s4, final String s5, final Font font, final int n) {
        this.c.gridx = 0;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 4;
        this.addLabel(String.valueOf(s) + " ", font, this.iAlign);
        this.c.gridx = 4;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s2, font, 1);
        this.c.gridx = 6;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s3, font, 1);
        this.c.gridx = 8;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s4, font, 1);
        this.c.gridx = 10;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 2;
        this.addLabel(s5, font, 1);
        this.c.gridx = 12;
        this.c.gridy = n;
        this.c.weightx = 1.0;
        this.c.gridwidth = 1;
        this.addLabel(" ", font, this.iAlign);
    }
    
    public void setAlign(final int iAlign) {
        this.iAlign = iAlign;
    }
}
