// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Panel;

public abstract class Plot extends Panel
{
    protected Applet applet;
    protected double xmin;
    protected double xmax;
    protected double ymin;
    protected double ymax;
    protected double xrange;
    protected double yrange;
    protected double zoom;
    protected double mouseX;
    protected double mouseY;
    protected int topOffset;
    protected int rightOffset;
    protected int leftOffset;
    protected int bottomOffset;
    protected int numIntervals;
    protected DataToScreen dts;
    protected String title;
    protected String subtitle;
    protected String xName;
    protected String yName;
    protected Image image;
    protected Graphics graphics;
    protected boolean enabled;
    protected Color background;
    protected Color highlight;
    protected Color foreground;
    protected Color gray;
    public static final boolean DEBUG = false;
    protected Dimension d;
    
    public Plot(final int n, final int n2) {
        this.xmin = 0.0;
        this.xmax = 1.0;
        this.ymin = 0.0;
        this.ymax = 1.0;
        this.xrange = 1.0;
        this.yrange = 1.0;
        this.zoom = 0.0;
        this.topOffset = 15;
        this.rightOffset = 10;
        this.leftOffset = 40;
        this.bottomOffset = 30;
        this.numIntervals = 5;
        this.title = "Plot";
        this.xName = "X";
        this.yName = "Y";
        this.image = null;
        this.enabled = true;
        this.background = StyleSheet.ENABLED_BACKGROUND;
        this.highlight = StyleSheet.ENABLED_HIGHLIGHT;
        this.foreground = StyleSheet.ENABLED_FOREGROUND;
        this.gray = StyleSheet.ENABLED_GRAY;
        this.d = null;
        this.setSize(n, n2);
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        this.d = this.size();
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        graphics.drawRect(this.leftOffset, this.topOffset, this.getSize().width - this.leftOffset - this.rightOffset + 1, this.getSize().height - this.bottomOffset - this.topOffset + 1);
        graphics.setFont(StyleSheet.LABEL_FONT);
        graphics.drawString((this.title == null) ? "Plot" : this.title, this.leftOffset + (this.d.width - this.leftOffset - this.rightOffset - graphics.getFontMetrics().stringWidth(this.title)) / 2, 12);
        int i = 0;
        final int n = (this.d.height + this.topOffset - this.bottomOffset - this.yName.length() * graphics.getFontMetrics().getHeight()) / 2;
        while (i < this.yName.length()) {
            final char char1 = this.yName.charAt(i);
            graphics.drawString(String.valueOf(char1), 2 + (11 - graphics.getFontMetrics().charWidth(char1)) / 2, n + graphics.getFontMetrics().getHeight() * i);
            ++i;
        }
        graphics.drawString((this.title == null) ? "X" : this.xName, (this.d.width - this.leftOffset - this.rightOffset - graphics.getFontMetrics().stringWidth(this.xName)) / 2 + this.leftOffset, this.d.height - 3);
        graphics.setFont(StyleSheet.SMALL_FONT);
        final String[] labels = this.dts.getLabels(this.numIntervals, 2, true);
        final String[] labels2 = this.dts.getLabels(this.numIntervals, 2, false);
        final int n2 = this.getSize().height - this.bottomOffset - this.topOffset;
        final int n3 = this.getSize().width - this.leftOffset - this.rightOffset;
        for (int j = 0; j < this.numIntervals; ++j) {
            graphics.drawString(labels2[this.numIntervals - j - 1], this.leftOffset - 4 - graphics.getFontMetrics().stringWidth(labels2[this.numIntervals - j - 1]) - 2, this.topOffset + graphics.getFontMetrics().getHeight() / 2 + n2 / (this.numIntervals - 1) * j);
            graphics.drawString(labels[j], this.leftOffset - graphics.getFontMetrics().stringWidth(labels[j]) / 2 + n3 / (this.numIntervals - 1) * j, this.getSize().height - 3 - graphics.getFontMetrics().getHeight());
        }
    }
    
    public void setMinMax(final double n, final double n2) {
        this.ymin = n;
        this.xmin = n;
        this.ymax = n2;
        this.xmax = n2;
        this.xrange = this.xmax - this.xmin;
        this.yrange = this.ymax - this.ymin;
    }
    
    public void setMinMax(final double xmin, final double xmax, final double ymin, final double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.xrange = xmax - xmin;
        this.yrange = ymax - ymin;
    }
    
    protected String getXName() {
        return this.xName;
    }
    
    protected String getXYName() {
        return this.yName;
    }
    
    protected String getTitle() {
        return this.title;
    }
    
    protected String getSubtitle() {
        return this.subtitle;
    }
    
    public double getZoom() {
        return this.zoom;
    }
    
    public double getXmin() {
        return this.xmin;
    }
    
    public double getYmin() {
        return this.ymin;
    }
    
    public double getXmax() {
        return this.xmax;
    }
    
    public double getYmax() {
        return this.ymax;
    }
    
    public double getXRange() {
        return this.xrange;
    }
    
    public double getYRange() {
        return this.yrange;
    }
    
    public abstract double[] getXArr();
    
    public abstract double[] getYArr();
    
    protected void setNumIntervals(final int numIntervals) {
        if (numIntervals > 1) {
            this.numIntervals = numIntervals;
        }
    }
    
    protected void setXName(final String xName) {
        this.xName = xName;
    }
    
    protected void setYName(final String yName) {
        this.yName = yName;
    }
    
    protected void setTitle(final String title) {
        this.title = title;
    }
    
    protected void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }
    
    protected int[] getCenter() {
        try {
            this.d = this.size();
        }
        catch (Exception ex) {
            System.err.println("Plot.d.size() returned error");
        }
        return new int[] { (int)Math.round((double)((this.d.width - this.leftOffset - this.rightOffset) / 2)) + this.leftOffset, (int)Math.round((double)((this.d.height - this.topOffset - this.bottomOffset) / 2)) + this.topOffset };
    }
    
    protected void setOffsets(final int topOffset, final int rightOffset, final int bottomOffset, final int leftOffset) {
        this.topOffset = topOffset;
        this.leftOffset = leftOffset;
        this.bottomOffset = bottomOffset;
        this.rightOffset = rightOffset;
    }
    
    public void enable(final boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            this.background = StyleSheet.ENABLED_BACKGROUND;
            this.highlight = StyleSheet.ENABLED_HIGHLIGHT;
            this.foreground = StyleSheet.ENABLED_FOREGROUND;
            this.gray = StyleSheet.ENABLED_GRAY;
        }
        else {
            this.background = StyleSheet.DISABLED_BACKGROUND;
            this.highlight = StyleSheet.DISABLED_HIGHLIGHT;
            this.foreground = StyleSheet.DISABLED_FOREGROUND;
            this.gray = StyleSheet.DISABLED_GRAY;
        }
    }
}
