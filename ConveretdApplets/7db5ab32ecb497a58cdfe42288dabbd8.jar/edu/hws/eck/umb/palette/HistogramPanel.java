// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Polygon;
import javax.swing.JPanel;

class HistogramPanel extends JPanel
{
    private int[] histogram;
    private Palette palette;
    private PaletteMapping paletteMapping;
    private Polygon paletteLengthTab;
    private Polygon paletteOffsetTab;
    private int paletteOffsetTabPosition;
    private int paletteLengthTabPosition;
    private double graphWidth;
    
    public HistogramPanel(final Palette palette, final PaletteMapping paletteMapping) {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(400, 180));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.palette = palette;
        this.paletteMapping = paletteMapping;
        final ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                HistogramPanel.this.repaint();
            }
        };
        palette.addChangeListener(changeListener);
        paletteMapping.addChangeListener(changeListener);
        this.addMouseListener(new Mouser());
    }
    
    public void setHistogram(final int[] histogram) {
        this.histogram = histogram;
        this.repaint();
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLUE);
        final int n = this.getHeight() - 70;
        graphics.drawLine(10, n + 1, this.getWidth() - 10, n + 1);
        graphics.drawLine(10, n + 2, this.getWidth() - 10, n + 2);
        graphics.drawLine(8, 8, 8, n + 2);
        graphics.drawLine(9, 8, 9, n + 2);
        if (this.histogram == null) {
            return;
        }
        this.graphWidth = this.getWidth() - 20;
        final double n2 = n - 10;
        double n3 = 0.0;
        for (int i = 0; i < this.histogram.length; ++i) {
            if (this.histogram[i] > n3) {
                n3 = this.histogram[i];
            }
        }
        if (n3 == 0.0) {
            return;
        }
        graphics.setColor(Color.BLACK);
        for (int j = 0; j < this.histogram.length; ++j) {
            if (this.histogram[j] > 0) {
                final int n4 = (int)(n2 * this.histogram[j] / n3);
                final int n5 = (int)(this.graphWidth * j / this.histogram.length);
                graphics.drawLine(11 + n5, n - n4 - 1, 11 + n5, n - 1);
            }
        }
        final int paletteLengthTabPosition = (this.paletteMapping.getLength() == 0) ? this.histogram.length : this.paletteMapping.getLength();
        this.paletteLengthTabPosition = paletteLengthTabPosition;
        final int[] rgBs = this.palette.makeRGBs(paletteLengthTabPosition, this.paletteMapping.getOffset());
        for (int n6 = 0; n6 < this.graphWidth; ++n6) {
            graphics.setColor(new Color(rgBs[(int)(n6 / this.graphWidth * this.histogram.length) % paletteLengthTabPosition]));
            graphics.drawLine(11 + n6, n + 25, 11 + n6, n + 45);
        }
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawRect(10, n + 24, this.getWidth() - 19, 21);
        if (this.paletteMapping.getLength() > this.histogram.length) {
            if (this.paletteMapping.getOffset() >= this.histogram.length) {
                this.paletteOffsetTabPosition = (int)this.graphWidth + 5;
            }
            else {
                this.paletteOffsetTabPosition = (int)(this.graphWidth * this.paletteMapping.getOffset() / this.histogram.length);
            }
        }
        else {
            int length;
            if (this.paletteMapping.getLength() == 0) {
                length = this.histogram.length;
            }
            else {
                length = this.paletteMapping.getLength() * (this.histogram.length / this.paletteMapping.getLength());
            }
            this.paletteOffsetTabPosition = this.paletteMapping.getOffset() % length;
            this.paletteOffsetTabPosition = (int)(this.graphWidth * this.paletteOffsetTabPosition / this.histogram.length);
        }
        this.paletteOffsetTab = new Polygon();
        final int n7 = this.paletteOffsetTabPosition + 11;
        final int n8 = n + 40;
        this.paletteOffsetTab.addPoint(n7, n8);
        this.paletteOffsetTab.addPoint(n7 + 7, n8 + 7);
        this.paletteOffsetTab.addPoint(n7 + 7, n8 + 25);
        this.paletteOffsetTab.addPoint(n7 - 6, n8 + 25);
        this.paletteOffsetTab.addPoint(n7 - 6, n8 + 7);
        graphics.setColor(new Color(50, 200, 50));
        graphics.fillPolygon(this.paletteOffsetTab);
        graphics.setColor(new Color(150, 255, 150));
        for (int k = n7 - 4; k <= n7 + 4; k += 4) {
            graphics.drawLine(k, n8 + 12, k, n8 + 20);
        }
        graphics.setColor(Color.DARK_GRAY);
        for (int l = n7 - 3; l <= n7 + 5; l += 4) {
            graphics.drawLine(l, n8 + 13, l, n8 + 21);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(this.paletteOffsetTab);
        if (this.paletteMapping.getLength() == 0 || this.paletteMapping.getLength() > this.histogram.length) {
            this.paletteLengthTabPosition = (int)this.graphWidth + 5;
        }
        else {
            this.paletteLengthTabPosition = this.paletteMapping.getLength() % this.histogram.length;
            if (this.paletteLengthTabPosition == 0) {
                this.paletteLengthTabPosition = this.histogram.length;
            }
            this.paletteLengthTabPosition = (int)(this.graphWidth * this.paletteLengthTabPosition / this.histogram.length);
        }
        final int n9 = this.paletteLengthTabPosition + 11;
        final int n10 = n + 30;
        (this.paletteLengthTab = new Polygon()).addPoint(n9, n10);
        this.paletteLengthTab.addPoint(n9 + 7, n10 - 7);
        this.paletteLengthTab.addPoint(n9 + 7, n10 - 25);
        this.paletteLengthTab.addPoint(n9 - 6, n10 - 25);
        this.paletteLengthTab.addPoint(n9 - 6, n10 - 7);
        graphics.setColor(new Color(225, 30, 30));
        graphics.fillPolygon(this.paletteLengthTab);
        graphics.setColor(new Color(255, 150, 150));
        for (int n11 = n9 - 4; n11 <= n9 + 4; n11 += 4) {
            graphics.drawLine(n11, n10 - 12, n11, n10 - 20);
        }
        graphics.setColor(Color.DARK_GRAY);
        for (int n12 = n9 - 3; n12 <= n9 + 5; n12 += 4) {
            graphics.drawLine(n12, n10 - 11, n12, n10 - 19);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(this.paletteLengthTab);
    }
    
    private class Mouser extends MouseAdapter implements MouseMotionListener
    {
        boolean dragging;
        boolean draggingOffset;
        int start;
        int offset;
        boolean lengthLocked;
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (this.dragging) {
                return;
            }
            if (HistogramPanel.this.paletteLengthTab.contains(mouseEvent.getX(), mouseEvent.getY())) {
                this.dragging = true;
                this.draggingOffset = false;
                this.lengthLocked = (HistogramPanel.this.paletteMapping.getLength() == 0 || HistogramPanel.this.paletteMapping.getLength() > HistogramPanel.this.histogram.length);
                this.start = mouseEvent.getX();
                this.offset = this.start - (HistogramPanel.this.paletteLengthTabPosition + 11);
                HistogramPanel.this.addMouseMotionListener(this);
            }
            else if (HistogramPanel.this.paletteOffsetTab.contains(mouseEvent.getX(), mouseEvent.getY())) {
                this.dragging = true;
                this.draggingOffset = true;
                this.start = mouseEvent.getX();
                this.offset = this.start - (HistogramPanel.this.paletteOffsetTabPosition + 11);
                HistogramPanel.this.addMouseMotionListener(this);
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (this.dragging) {
                this.dragging = false;
                HistogramPanel.this.removeMouseMotionListener(this);
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (!this.dragging) {
                return;
            }
            if (this.lengthLocked && this.start - mouseEvent.getX() < 15) {
                return;
            }
            this.lengthLocked = false;
            if (this.draggingOffset) {
                int n = mouseEvent.getX() - this.offset - 11;
                if (n < 0 && n > -10) {
                    n = 0;
                }
                final int n2 = (int)(n / HistogramPanel.this.graphWidth * HistogramPanel.this.histogram.length);
                int n3;
                if (HistogramPanel.this.paletteMapping.getLength() == 0) {
                    n3 = HistogramPanel.this.histogram.length;
                }
                else if (HistogramPanel.this.paletteMapping.getLength() > HistogramPanel.this.histogram.length) {
                    n3 = HistogramPanel.this.paletteMapping.getLength();
                }
                else {
                    n3 = HistogramPanel.this.paletteMapping.getLength() * (HistogramPanel.this.histogram.length / HistogramPanel.this.paletteMapping.getLength());
                }
                HistogramPanel.this.paletteMapping.setOffset(n2 - n3 * (int)Math.floor(n2 / n3));
            }
            else {
                int length = (int)((mouseEvent.getX() - this.offset - 11) / HistogramPanel.this.graphWidth * HistogramPanel.this.histogram.length);
                if (mouseEvent.getX() > HistogramPanel.this.getWidth() + 50) {
                    length = 0;
                }
                else if (length > HistogramPanel.this.histogram.length) {
                    length = HistogramPanel.this.histogram.length;
                }
                else if (length < 1) {
                    length = 1;
                }
                HistogramPanel.this.paletteMapping.setLength(length);
            }
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
