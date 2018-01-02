// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.utils;

import edu.wise.correl.gui.StyleSheet;
import java.awt.Graphics;
import java.awt.Canvas;

public class TableFormat extends Canvas
{
    private int[] cols;
    private int width;
    private int colWidth;
    private int rowHt;
    private int y_start;
    private int lastRowDrawnAt;
    private int maxRow;
    private int maxRowLast;
    
    public TableFormat(final int width, final int n, final int n2) {
        this.maxRow = 0;
        this.maxRowLast = 0;
        this.width = width;
        this.y_start = n2 + 2;
        this.cols = new int[n];
        this.setup();
    }
    
    private void setup() {
        this.colWidth = this.width / this.cols.length;
        for (int i = 0; i < this.cols.length; ++i) {
            this.cols[i] = this.colWidth * i;
        }
    }
    
    public void drawString(final String s, final int n, final int n2, final Graphics graphics) {
        this.drawString(s, n, n2, graphics, false, false);
    }
    
    public void drawString(final String s, final int n, final int maxRow, final Graphics graphics, final boolean b, final boolean b2) {
        graphics.setFont(StyleSheet.f_reg);
        int charWidth = 0;
        try {
            if (Double.valueOf(s) < 0.0) {
                charWidth = graphics.getFontMetrics().charWidth('-');
            }
        }
        catch (NumberFormatException ex) {}
        this.rowHt = graphics.getFontMetrics().getHeight() + 2;
        this.lastRowDrawnAt = this.y_start + maxRow * this.rowHt;
        if (maxRow > this.maxRow) {
            this.maxRowLast = this.lastRowDrawnAt;
            this.maxRow = maxRow;
        }
        if (b2) {
            graphics.setFont(StyleSheet.labelFont);
            this.setFont(StyleSheet.labelFont);
        }
        graphics.drawString(s, this.cols[n] + 5 - charWidth, this.lastRowDrawnAt);
        graphics.setFont(StyleSheet.f_reg);
        if (b) {
            graphics.drawLine(this.cols[n] + 5 - charWidth, this.y_start + maxRow * this.rowHt + 1, graphics.getFontMetrics().stringWidth(s) + this.cols[n] + 5 - charWidth, this.y_start + maxRow * this.rowHt + 1);
        }
    }
    
    public void reset() {
        this.maxRow = 0;
        this.maxRowLast = 0;
    }
    
    public void setColWidth(final int n, final int n2) {
        if (n + 1 < this.cols.length) {
            final int n3 = n2 - (this.cols[n + 1] - this.cols[n]);
            for (int i = n + 1; i < this.cols.length; ++i) {
                final int[] cols = this.cols;
                final int n4 = i;
                cols[n4] += n3;
            }
        }
    }
    
    public int getColWidth() {
        return this.colWidth;
    }
    
    public int getPreferredHeight() {
        return this.maxRowLast + this.rowHt;
    }
}
