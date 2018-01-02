// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi;

import javax.swing.event.ChangeEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel implements Colors, ChangeListener
{
    RadialGraphApplet owner;
    JSlider sliderDynWeight;
    JSlider sliderPubfilter;
    JSlider sliderConnfilter;
    JSlider sliderRotate;
    public static final Font fntHeadline;
    public static final Font fntLabel;
    
    static {
        fntHeadline = new Font("SansSerif", 1, 11);
        fntLabel = new Font("SansSerif", 0, 11);
    }
    
    public SettingsPanel(final RadialGraphApplet parent) {
        super(null, true);
        this.setOpaque(false);
        this.owner = parent;
        this.add(this.sliderDynWeight = new JSlider());
        this.sliderDynWeight.setValue(0);
        this.sliderDynWeight.setBounds(8, 18, 120, 40);
        this.sliderDynWeight.setOpaque(false);
        this.sliderDynWeight.setMaximum(60);
        this.sliderDynWeight.setMinimum(0);
        this.sliderDynWeight.addChangeListener(this);
        this.add(this.sliderPubfilter = new JSlider());
        this.sliderPubfilter.setValue(0);
        this.sliderPubfilter.setBounds(158, 18, 120, 40);
        this.sliderPubfilter.setOpaque(false);
        this.sliderPubfilter.setMaximum(60);
        this.sliderPubfilter.setMinimum(0);
        this.sliderPubfilter.addChangeListener(this);
        this.add(this.sliderConnfilter = new JSlider());
        this.sliderConnfilter.setValue(0);
        this.sliderConnfilter.setBounds(308, 18, 120, 40);
        this.sliderConnfilter.setOpaque(false);
        this.sliderConnfilter.setMaximum(60);
        this.sliderConnfilter.setMinimum(0);
        this.sliderConnfilter.addChangeListener(this);
        (this.sliderRotate = new JSlider()).setValue(0);
        this.sliderRotate.setBounds(458, 18, 120, 40);
        this.sliderRotate.setOpaque(false);
        this.sliderRotate.setMaximum(360);
        this.sliderRotate.setMinimum(0);
        this.sliderRotate.addChangeListener(this);
    }
    
    public void paint(final Graphics g) {
        g.setFont(SettingsPanel.fntHeadline);
        g.setFont(SettingsPanel.fntLabel);
        g.drawString("Co-Publications", 8, 23);
        g.drawString(Integer.toString(this.sliderDynWeight.getValue()), 110, 23);
        g.drawString("Publications", 158, 23);
        g.drawString(Integer.toString(this.sliderPubfilter.getValue()), 260, 23);
        g.drawString("Connections", 308, 23);
        g.drawString(Integer.toString(this.sliderConnfilter.getValue()), 410, 23);
        super.paint(g);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void stateChanged(final ChangeEvent c) {
        if (c.getSource() == this.sliderConnfilter) {
            this.owner.setMinCount(this.sliderConnfilter.getValue());
            this.repaint();
        }
        if (c.getSource() == this.sliderDynWeight) {
            this.owner.setMinWeight(this.sliderDynWeight.getValue());
            this.repaint();
        }
        if (c.getSource() == this.sliderPubfilter) {
            this.owner.setMinPubCount(this.sliderPubfilter.getValue());
            this.repaint();
        }
        if (c.getSource() == this.sliderRotate) {
            this.owner.setBAngle(this.sliderRotate.getValue() / 360.0);
            this.repaint();
        }
    }
}
