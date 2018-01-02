// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class GradientPanel extends JPanel
{
    private static final long serialVersionUID = -528109679758757371L;
    private Color color1;
    private Color color2;
    private Color borderColor;
    private boolean drawBorders;
    
    public void setGradientColor(final Color color1, final Color color2, final Color borderColor) {
        this.drawBorders = true;
        this.color1 = color1;
        this.color2 = color2;
        this.borderColor = borderColor;
        this.repaint();
    }
    
    public void setDrawHorizontalBorders(final boolean drawBorders) {
        this.drawBorders = drawBorders;
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        if (!this.isOpaque()) {
            super.paintComponent(g);
            return;
        }
        final int width = this.getWidth();
        final int height = this.getHeight();
        final Graphics2D g2d = (Graphics2D)g;
        final GradientPaint gp = new GradientPaint(0.0f, 0.0f, this.color1, 0.0f, height, this.color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.setPaint(this.borderColor);
        if (this.drawBorders) {
            g2d.drawRect(0, 0, width - 1, height - 1);
        }
        else {
            g2d.drawLine(0, 0, 0, height);
            g2d.drawLine(width - 1, 0, width - 1, height);
        }
        this.setOpaque(false);
        super.paintComponent(g);
        this.setOpaque(true);
    }
}
