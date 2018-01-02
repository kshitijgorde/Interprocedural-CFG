// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public class ProgressBar extends Canvas
{
    private boolean progressBarVisible;
    private boolean percentMessageVisible;
    private Color completedPartColor;
    private Color remainingPartColor;
    private Color textColor;
    private String message;
    private int maximumValue;
    private int currentValue;
    
    public ProgressBar(final int maximumValue, final Color completedPartColor, final Color remainingPartColor, final Color textColor, final String message) {
        if (completedPartColor == null) {
            throw new IllegalArgumentException("null completedPartColor");
        }
        this.completedPartColor = completedPartColor;
        if (remainingPartColor == null) {
            throw new IllegalArgumentException("null remainingPartColor");
        }
        this.remainingPartColor = remainingPartColor;
        if (textColor == null) {
            throw new IllegalArgumentException("null textColor");
        }
        this.textColor = textColor;
        if (maximumValue <= 0) {
            throw new IllegalArgumentException("maximumValue <= 0");
        }
        this.maximumValue = maximumValue;
        if (message == null) {
            throw new IllegalArgumentException("null message");
        }
        this.message = message;
        this.progressBarVisible = true;
        this.percentMessageVisible = true;
    }
    
    public Color getCompletedPartColor() {
        return this.completedPartColor;
    }
    
    public void setCompletedPartColor(final Color completedPartColor) {
        this.completedPartColor = completedPartColor;
        this.repaint();
    }
    
    public Color getRemainingPartColor() {
        return this.remainingPartColor;
    }
    
    public void setRemainingPartColor(final Color remainingPartColor) {
        this.remainingPartColor = remainingPartColor;
        this.repaint();
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void setTextColor(final Color textColor) {
        this.textColor = textColor;
        this.repaint();
    }
    
    public boolean isPercentMessageVisible() {
        return this.percentMessageVisible;
    }
    
    public void setPercentMessageVisible(final boolean percentMessageVisible) {
        this.percentMessageVisible = percentMessageVisible;
        this.repaint();
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
        this.repaint();
    }
    
    public void setMaximumValue(final int maximumValue) {
        this.maximumValue = maximumValue;
        this.repaint();
    }
    
    public int getMaximumValue() {
        return this.maximumValue;
    }
    
    public int getCurrentValue() {
        return this.currentValue;
    }
    
    public void setCurrentValue(final int currentValue) {
        this.currentValue = currentValue;
        this.repaint();
    }
    
    public double getPercentDone() {
        return 100.0 * this.currentValue / this.maximumValue;
    }
    
    public void increment() {
        this.changeBy(1);
    }
    
    public int changeBy(final int n) {
        int maximumValue = this.currentValue + n;
        if (maximumValue > this.maximumValue) {
            maximumValue = this.maximumValue;
        }
        else if (maximumValue < 0) {
            maximumValue = 0;
        }
        this.currentValue = maximumValue;
        this.repaint();
        return this.currentValue;
    }
    
    public void reset() {
        this.currentValue = 0;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        if (this.progressBarVisible) {
            graphics.setColor(this.remainingPartColor);
            graphics.fillRect(0, 0, width, height);
            if (this.currentValue > 0) {
                float n = this.currentValue / this.maximumValue;
                if (n > 1.0f) {
                    n = 1.0f;
                }
                final int n2 = (int)((width - 2) * n);
                graphics.setColor(this.completedPartColor);
                graphics.fillRect(1, 1, n2, height - 2);
                final int n3 = (int)(100.0 * n);
                if (this.percentMessageVisible) {
                    graphics.setColor(this.textColor);
                    graphics.drawString(this.message + " " + n3 + "%", 4, height - 5);
                }
            }
        }
        else {
            graphics.setColor(this.getParent().getBackground());
            graphics.fillRect(0, 0, width, height);
        }
    }
    
    public void setProgressBarVisible(final boolean progressBarVisible) {
        this.progressBarVisible = progressBarVisible;
        this.repaint();
    }
    
    public boolean isProgressBarVisible() {
        return this.progressBarVisible;
    }
}
