// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.component;

import edu.wise.utils.FormatUtils;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class DataCell
{
    public static final boolean DEBUG = false;
    boolean needRedisplay;
    boolean selected;
    boolean transientValue;
    String valueString;
    Color bgColor;
    Color fgColor;
    Color highlightColor;
    private DataEditor app;
    private boolean paused;
    private boolean active;
    Dimension dim;
    private int row;
    private int col;
    private double value;
    
    public String getPrintString() {
        if (this.value == Double.NaN) {
            return "";
        }
        return String.valueOf(this.value);
    }
    
    public DataCell(final DataEditor app, final Color bgColor, final Color fgColor, final Color highlightColor, final int n, final int n2) {
        this.selected = false;
        this.transientValue = false;
        this.valueString = "";
        this.paused = false;
        this.active = false;
        this.row = 0;
        this.col = 0;
        this.value = 0.0;
        this.app = app;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.highlightColor = highlightColor;
        this.dim = new Dimension(n, n2);
        this.needRedisplay = true;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2) {
        if (this.active) {
            graphics.setColor(this.highlightColor);
        }
        else {
            graphics.setColor(this.bgColor);
        }
        graphics.fillRect(n, n2 + 2, this.dim.width, this.dim.height - 1);
        graphics.setColor(this.fgColor);
        int n3;
        if (this.value < 0.0) {
            n3 = 3;
        }
        else {
            n3 = graphics.getFontMetrics().charWidth('-') + 3;
        }
        if (this.valueString.length() > 14) {
            try {
                this.valueString = FormatUtils.rounder_str(Double.valueOf(this.valueString), 6);
            }
            catch (NumberFormatException ex) {}
            graphics.drawString(this.valueString, n + n3, n2 + this.dim.height / 2 + 5);
        }
        else {
            graphics.drawString(this.valueString, n + n3, n2 + this.dim.height / 2 + 5);
        }
    }
    
    public double getValue() {
        return this.value;
    }
    
    public boolean getActive() {
        return this.active;
    }
    
    public int getWidth() {
        return this.dim.width;
    }
    
    public int getHeight() {
        return this.dim.height;
    }
    
    public int[] getPos() {
        return new int[] { this.row, this.col };
    }
    
    public void setPos(final int row, final int col) {
        this.row = row;
        this.col = col;
    }
    
    public void setValue(final double value) {
        if (Double.isNaN(value)) {
            this.valueString = "";
        }
        else {
            this.valueString = String.valueOf(FormatUtils.rounder_str(value, 5));
        }
        this.value = value;
        this.setActive(false);
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public String toString() {
        return this.active + " [" + this.row + ", " + this.col + "] " + this.getValue();
    }
}
