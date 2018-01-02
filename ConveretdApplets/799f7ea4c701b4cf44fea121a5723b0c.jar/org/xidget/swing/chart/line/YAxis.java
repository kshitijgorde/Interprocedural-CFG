// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import java.awt.Component;
import javax.swing.JFrame;
import java.util.Iterator;
import java.awt.FontMetrics;
import java.util.List;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.xidget.chart.Scale;
import java.awt.Dimension;
import java.awt.Font;

public class YAxis extends Axis
{
    private boolean left;
    private int[] maxWidths;
    private Font[] fonts;
    
    public YAxis(final boolean left) {
        this.left = left;
        this.setPreferredSize(new Dimension(30, -1));
    }
    
    @Override
    public Scale getScale() {
        final int height = this.getHeight();
        if (this.scale == null && this.min != this.max && height > 4) {
            this.scale = new Scale(this.min, this.max, height / 4, this.log, this.format);
            this.textDepth = -1;
        }
        return this.scale;
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Scale scale = this.getScale();
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        final int width = this.getWidth();
        final int n = this.getHeight() - 1;
        final int n2 = (int)Math.round(scale.plot(this.cursor) * n);
        graphics2D.setColor(Color.lightGray);
        graphics2D.drawLine(0, n2, width, n2);
        final Font[] labelFonts = this.getLabelFonts(graphics2D);
        if (this.textDepth == -1) {
            this.textDepth = this.findTextDepth(graphics2D);
            this.findMaxWidths(graphics2D);
        }
        graphics2D.setColor(Color.black);
        final List<Scale.Tick> ticks = scale.getTicks();
        final double n3 = ticks.get(1).depth + 1;
        for (int i = 0; i < ticks.size(); ++i) {
            final Scale.Tick tick = ticks.get(i);
            final int n4 = (int)((n3 - tick.depth) / n3 * (width - this.maxWidths[tick.depth] - 2));
            int n5 = (int)Math.round((1.0 - tick.scale) * n);
            if (this.left) {
                graphics2D.drawLine(width, n5, width - n4, n5);
            }
            else {
                graphics2D.drawLine(0, n5, n4, n5);
            }
            if (tick.depth <= this.textDepth) {
                graphics2D.setFont(labelFonts[(tick.depth < labelFonts.length) ? tick.depth : (labelFonts.length - 1)]);
                final FontMetrics fontMetrics = graphics2D.getFontMetrics();
                final int n6 = fontMetrics.getAscent() / 2;
                if (i == 0) {
                    n5 -= n6;
                }
                if (i == ticks.size() - 1) {
                    n5 += n6;
                }
                final int stringWidth = fontMetrics.stringWidth(tick.label);
                if (this.left) {
                    graphics2D.drawString(tick.label, width - n4 - stringWidth - 2, n5 + n6 - 1);
                }
                else {
                    graphics2D.drawString(tick.label, n4 + 2, n5 + n6 - 1);
                }
            }
        }
        graphics2D.setFont(labelFonts[0]);
    }
    
    private Font[] getLabelFonts(final Graphics2D graphics2D) {
        if (this.fonts == null || graphics2D.getFont() != this.fonts[0]) {
            (this.fonts = new Font[4])[0] = this.getFont();
            for (int i = 1; i < this.fonts.length; ++i) {
                this.fonts[i] = this.fonts[i - 1].deriveFont(this.fonts[i - 1].getSize() * 0.85f);
            }
        }
        return this.fonts;
    }
    
    private void findMaxWidths(final Graphics2D graphics2D) {
        final List<Scale.Tick> ticks = this.scale.getTicks();
        this.maxWidths = new int[ticks.get(1).depth + 1];
        for (final Scale.Tick tick : ticks) {
            if (tick.depth <= this.textDepth) {
                final int stringWidth = graphics2D.getFontMetrics(this.fonts[tick.depth]).stringWidth(tick.label);
                if (stringWidth <= this.maxWidths[tick.depth]) {
                    continue;
                }
                this.maxWidths[tick.depth] = stringWidth;
            }
        }
    }
    
    private int findTextDepth(final Graphics2D graphics2D) {
        final int height = this.getHeight();
        final List<Integer> tickCounts = this.scale.getTickCounts();
        for (int i = tickCounts.size() - 1; i >= 0; --i) {
            if (i < this.fonts.length && graphics2D.getFontMetrics(this.fonts[i]).getHeight() <= height / tickCounts.get(i)) {
                return i;
            }
        }
        return 0;
    }
    
    @Override
    protected void mouseMoved(final int n, final int n2) {
        this.repaint(0, (int)Math.round(this.scale.plot(this.cursor)) - 1, this.getWidth(), 3);
        this.cursor = this.scale.value(n2, this.getHeight());
        this.repaint(0, (int)Math.round(this.scale.plot(this.cursor)) - 1, this.getWidth(), 3);
    }
    
    @Override
    protected void mouseWheelMoved(final int n) {
    }
    
    public static void main(final String[] array) throws Exception {
        final JFrame frame = new JFrame();
        final YAxis yAxis = new YAxis(true);
        yAxis.setExtrema(30.0, 40000.0);
        yAxis.setFormat(Scale.Format.scientific);
        frame.getContentPane().add(yAxis);
        frame.setSize(10, 500);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
