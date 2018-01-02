// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshowerawt;

import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

public final class AntiAliastedFontedTextArea extends Canvas
{
    private static final boolean DEBUGGING = false;
    private static final int BOTTOM_MARGIN = 8;
    private static final int LEFT_MARGIN = 5;
    private static final int RIGHT_MARGIN = 4;
    private static final int TOP_MARGIN = 0;
    private Dimension dimension;
    private String[] textLines;
    private boolean antialias;
    private boolean readyForDryRun;
    
    public AntiAliastedFontedTextArea() {
        this.dimension = new Dimension(10, 10);
        this.antialias = true;
        this.readyForDryRun = false;
    }
    
    public void addNotify() {
        super.addNotify();
        this.readyForDryRun = true;
        this.dryRun();
    }
    
    public Dimension getMaximumSize() {
        return this.dimension;
    }
    
    public Dimension getMinimumSize() {
        return this.dimension;
    }
    
    public Dimension getPreferredSize() {
        return this.dimension;
    }
    
    public void paint(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        if (this.antialias) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }
        this.render(g2d);
    }
    
    public void setAntialias(final boolean antialias) {
        this.antialias = antialias;
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.dryRun();
    }
    
    public void setTextLines(final String[] textLines) {
        this.textLines = textLines;
        this.dryRun();
    }
    
    private void dryRun() {
        if (!this.readyForDryRun) {
            return;
        }
        final Font font = this.getFont();
        final FontMetrics fm = this.getFontMetrics(font);
        final int leading = font.getSize() + 4;
        int width = 0;
        for (int i = 0; i < this.textLines.length; ++i) {
            width = Math.max(width, fm.stringWidth(this.textLines[i]));
        }
        width += 9;
        final int height = this.textLines.length * leading + 0 + 8;
        this.dimension = new Dimension(width, height);
        this.invalidate();
    }
    
    private void render(final Graphics2D g) {
        g.setColor(this.getForeground());
        final Font font = this.getFont();
        g.setFont(font);
        final int leading = font.getSize() + 4;
        final Rectangle r = g.getClipBounds();
        final int firstLine = Math.max(0, (r.y - 0) / leading);
        for (int lastLine = Math.min((r.y + r.height - 0 + leading - 1) / leading, this.textLines.length - 1), i = firstLine; i <= lastLine; ++i) {
            g.drawString(this.textLines[i], 5, 0 + (i + 1) * leading);
        }
    }
}
