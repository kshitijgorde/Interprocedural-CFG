// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Canvas;

public class ProgressIndicatorImpl extends Canvas implements ProgressIndicator
{
    private float ivProgressActual;
    private float ivProgressTotal;
    private String ivProgressText;
    private boolean ivShowPercent;
    private String ivText;
    private int ivCycle;
    
    public ProgressIndicatorImpl() {
        this.ivProgressActual = 0.5f;
        this.ivProgressTotal = 1.0f;
        this.ivProgressText = "progress:";
        this.ivShowPercent = true;
        this.initialize();
    }
    
    @Override
    public Dimension getMinimumSize() {
        if (this.ivText != null) {
            final FontMetrics fm = this.getFontMetrics(this.getFont());
            final int width = fm.stringWidth(this.ivText);
            final int height = fm.getHeight();
            return new Dimension(width, height);
        }
        return super.getMinimumSize();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    private void initialize() {
        this.setName("ProgressIndicator");
        this.updateText();
    }
    
    @Override
    public void paint(final Graphics g) {
        final Dimension d = this.getSize();
        if (this.ivProgressTotal > 0.0f && this.ivProgressActual <= this.ivProgressTotal) {
            final int x = (int)(d.width * this.ivProgressActual / this.ivProgressTotal);
            g.setColor(Color.red);
            g.fillRect(0, 0, x, d.height);
            g.setColor(Color.gray);
            g.fillRect(x, 0, d.width - x, d.height);
        }
        else {
            g.setColor(Color.gray);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.red);
            g.fillRect((this.ivCycle++ & 0xF) * d.width / 16, 0, d.width / 16, d.height);
        }
        final FontMetrics fm = g.getFontMetrics();
        final int textWidth = fm.stringWidth(this.ivText);
        final int textHeight = fm.getAscent();
        g.setColor(Color.white);
        g.drawString(this.ivText, (d.width - textWidth) / 2, (d.height + textHeight) / 2);
    }
    
    @Override
    public void setProgress(final float pProgress) {
        this.ivProgressActual = Math.max(0.0f, Math.min(pProgress, 1.0f));
        this.ivProgressTotal = 1.0f;
        this.updateText();
    }
    
    @Override
    public void setProgress(final int pActual, final int pTotal) {
        this.ivProgressActual = pActual;
        this.ivProgressTotal = pTotal;
        this.updateText();
    }
    
    @Override
    public void setProgressText(final String pProgressText) {
        this.ivProgressText = pProgressText;
        this.updateText();
    }
    
    @Override
    public void setShowPercent(final boolean pShowPercent) {
        this.ivShowPercent = pShowPercent;
        this.updateText();
    }
    
    @Override
    public void setVisible(final boolean pVisible) {
        super.setVisible(pVisible);
        if (this.getParent() != null) {
            this.getParent().validate();
        }
    }
    
    private synchronized void updateText() {
        this.ivText = ((this.ivProgressText != null) ? this.ivProgressText : "");
        if (this.ivShowPercent && this.ivProgressTotal > 0.0f && this.ivProgressActual <= this.ivProgressTotal) {
            this.ivText = String.valueOf(this.ivText) + " " + (int)(this.ivProgressActual / this.ivProgressTotal * 100.0f) + "%";
        }
        else {
            this.ivText = String.valueOf(this.ivText) + " " + (int)this.ivProgressActual;
        }
        this.repaint();
    }
}
