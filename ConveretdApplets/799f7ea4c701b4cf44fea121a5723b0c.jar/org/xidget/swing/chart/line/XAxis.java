// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import java.awt.Component;
import javax.swing.JFrame;
import java.util.Iterator;
import java.util.List;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.xidget.chart.Scale;
import java.awt.Dimension;

public class XAxis extends Axis
{
    private boolean top;
    
    public XAxis(final boolean top) {
        this.top = top;
        this.setPreferredSize(new Dimension(-1, 30));
    }
    
    @Override
    public Scale getScale() {
        final int width = this.getWidth();
        if (this.scale == null && this.min != this.max && width > 4) {
            this.scale = new Scale(this.min, this.max, width / 4, this.log, this.format);
            this.textDepth = -1;
        }
        return this.scale;
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Scale scale = this.getScale();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        final int n = this.getWidth() - 1;
        final int height = this.getHeight();
        final int n2 = (int)Math.round(scale.plot(this.cursor) * n);
        graphics2D.setColor(Color.lightGray);
        graphics2D.drawLine(n2, 0, n2, height);
        final List<Scale.Tick> ticks = scale.getTicks();
        if (this.textDepth == -1) {
            this.textDepth = this.findTextDepth(fontMetrics);
        }
        graphics2D.setColor(Color.black);
        final int n3 = height - fontMetrics.getHeight();
        final int n4 = ticks.get(1).depth + 1;
        for (int i = 0; i < ticks.size(); ++i) {
            final Scale.Tick tick = ticks.get(i);
            final int n5 = (int)Math.round(tick.scale * n);
            final int n6 = n3 * (n4 - tick.depth) / n4;
            if (this.top) {
                graphics2D.drawLine(n5, height, n5, height - n6 + 1);
            }
            else {
                graphics2D.drawLine(n5, 0, n5, n6);
            }
            if (tick.depth <= this.textDepth) {
                final int stringWidth = fontMetrics.stringWidth(tick.label);
                int n7 = n5;
                if (i > 0) {
                    n7 -= stringWidth / 2;
                }
                if (i == ticks.size() - 1) {
                    n7 -= stringWidth / 2;
                }
                if (this.top) {
                    graphics2D.drawString(tick.label, n7, height - n6);
                }
                else {
                    graphics2D.drawString(tick.label, n7, n6 + fontMetrics.getAscent());
                }
            }
        }
    }
    
    @Override
    protected void mouseMoved(final int n, final int n2) {
        final Scale scale = this.getScale();
        this.repaint((int)Math.round(scale.plot(this.cursor)) - 1, 0, 3, this.getHeight());
        this.cursor = scale.value(n, this.getWidth());
        this.repaint((int)Math.round(scale.plot(this.cursor)) - 1, 0, 3, this.getHeight());
    }
    
    @Override
    protected void mouseWheelMoved(final int n) {
        final List<Scale.Tick> ticks = this.scale.getTicks();
        final double n2 = ticks.get(1).value - ticks.get(0).value;
        if (n < 0) {
            this.min -= n2;
            this.max += n2;
        }
        else {
            this.min += n2;
            this.max -= n2;
        }
        this.scale = null;
        this.repaint();
    }
    
    private int findTextDepth(final FontMetrics fontMetrics) {
        if (this.scale == null) {
            return 0;
        }
        final int width = this.getWidth();
        final List<Scale.Tick> ticks = this.scale.getTicks();
        for (int i = 0; i <= ticks.get(1).depth; ++i) {
            int length = 0;
            int n = 0;
            String label = "";
            for (final Scale.Tick tick : ticks) {
                if (tick.depth == i) {
                    if (length < tick.label.length()) {
                        length = tick.label.length();
                        label = tick.label;
                    }
                    ++n;
                }
            }
            if (fontMetrics.stringWidth(label) + 10 > width / n) {
                return i - 1;
            }
        }
        return ticks.get(1).depth - 1;
    }
    
    public static void main(final String[] array) throws Exception {
        final JFrame frame = new JFrame();
        final XAxis xAxis = new XAxis(false);
        xAxis.setExtrema(3.0, 5.0);
        xAxis.setFormat(Scale.Format.engineering);
        frame.getContentPane().add(xAxis);
        frame.setSize(500, 50);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
