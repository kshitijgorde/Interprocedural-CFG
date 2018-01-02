// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;
import com.diginet.digichat.awt.bj;

public class PalettePanel extends bj
{
    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 20;
    private static final int CELL_SPACE = 3;
    private int iSelect;
    private int nClick;
    private int nX;
    private int nY;
    private Vector vecPalette;
    
    public PalettePanel(final i i) {
        this.nClick = 0;
        this.iSelect = -1;
        this.setBackground(i.cc.d);
    }
    
    public void setPalette(final Vector vecPalette) {
        this.vecPalette = vecPalette;
        this.repaint();
    }
    
    public void addColor(final Color color) {
        this.vecPalette.addElement(color);
        this.repaint();
    }
    
    public void insertColor(final Color color, final int n) {
        this.vecPalette.insertElementAt(color, n);
        this.repaint();
    }
    
    public void removeColor(final int n) {
        if (n == this.iSelect) {
            this.iSelect = -1;
        }
        this.vecPalette.removeElementAt(n);
        this.repaint();
    }
    
    public void setColor(final Color color, final int n) {
        this.vecPalette.setElementAt(color, n);
        this.repaint();
    }
    
    public int setSelected(final Color color) {
        this.iSelect = ((this.vecPalette == null) ? -1 : this.vecPalette.indexOf(color));
        this.repaint();
        return this.iSelect;
    }
    
    public int getSelected() {
        return this.iSelect;
    }
    
    public Color getColor(final int n) {
        return this.vecPalette.elementAt(n);
    }
    
    public void enable() {
        super.enable();
        this.repaint();
    }
    
    public void disable() {
        this.iSelect = -1;
        super.disable();
        this.repaint();
    }
    
    public void setEnabled(final boolean b) {
        this.enable(b);
    }
    
    public void enable(final boolean b) {
        if (b) {
            this.enable();
        }
        else {
            this.disable();
        }
    }
    
    public Dimension preferredSize() {
        if (this.vecPalette == null) {
            return super.preferredSize();
        }
        final int n = (int)Math.ceil(Math.sqrt(this.vecPalette.size())) * 23 - 3 + 16;
        return new Dimension(n, n);
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public boolean handleEvent(final Event event) {
        if (this.vecPalette != null) {
            switch (event.id) {
                case 501: {
                    int n = -1;
                    final Dimension size = this.size();
                    final int size2 = this.vecPalette.size();
                    final int n2 = (int)Math.ceil(Math.sqrt(size2 * size.width / size.height));
                    final int n3 = size.width - n2 * 23 - 3 >> 1;
                    int i = 0;
                    int n4 = size.height - (int)Math.ceil(size2 / n2) * 23 - 3 >> 1;
                    while (i < size2) {
                        for (int n5 = 0, n6 = n3; n5 < n2 && i < size2; ++i, ++n5, n6 += 23) {
                            if (event.x > n6 + 2 && event.x < n6 + 20 - 3 && event.y > n4 + 2 && event.y < n4 + 20 - 3) {
                                n = i;
                                break;
                            }
                        }
                        n4 += 23;
                    }
                    if (n != this.iSelect) {
                        final int iSelect = n;
                        this.iSelect = iSelect;
                        this.postEvent(new Event(this, (iSelect < 0) ? 702 : 701, null));
                        this.repaint();
                    }
                    if (this.iSelect < 0) {
                        this.nClick = 0;
                    }
                    else if (this.nClick == 0) {
                        ++this.nClick;
                        this.nX = event.x;
                        this.nY = event.y;
                    }
                    break;
                }
                case 502: {
                    if (this.nClick <= 0) {
                        break;
                    }
                    if (this.iSelect < 0 || (this.nX - event.x) * (this.nX - event.x) + (this.nY - event.y) * (this.nY - event.y) > 7) {
                        this.nClick = 0;
                        break;
                    }
                    if (++this.nClick > 2) {
                        this.postEvent(new Event(this, 1001, null));
                        this.nClick = 0;
                        break;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.vecPalette != null) {
            final Color background = this.getBackground();
            final Color brighter = background.brighter();
            final Color darker = background.darker();
            final Dimension size = this.size();
            final int size2 = this.vecPalette.size();
            final int n = (int)Math.ceil(Math.sqrt(size2 * size.width / size.height));
            final int n2 = size.width - n * 23 >> 1;
            int i = 0;
            int n3 = size.height - (int)Math.ceil(size2 / n) * 23 >> 1;
            while (i < size2) {
                for (int n4 = 0, n5 = n2; n4 < n && i < size2; ++i, ++n4, n5 += 23) {
                    if (i == this.iSelect) {
                        graphics.setColor(Color.black);
                        graphics.drawLine(n5 - 3, n3 - 3 + 3, n5 - 3 + 3, n3 - 3);
                        graphics.drawLine(n5 - 3 + 3, n3 - 3 + 0, n5 - 3 + 20 + 6 - 3, n3 - 3 + 0);
                        graphics.drawLine(n5 - 3 + 20 + 6 - 3, n3 - 3 + 0, n5 - 3 + 20 + 6, n3 - 3 + 3);
                        graphics.drawLine(n5 - 3 + 20 + 6, n3 - 3 + 3, n5 - 3 + 20 + 6, n3 - 3 + 20 + 6 - 3);
                        graphics.drawLine(n5 - 3 + 20 + 6, n3 - 3 + 20 + 6 - 3, n5 - 3 + 20 + 6 - 3, n3 - 3 + 20 + 6);
                        graphics.drawLine(n5 - 3 + 20 + 6 - 3, n3 - 3 + 20 + 6, n5 - 3 + 3, n3 - 3 + 20 + 6);
                        graphics.drawLine(n5 - 3 + 3, n3 - 3 + 20 + 6, n5 - 3 + 0, n3 - 3 + 20 + 6 - 3);
                        graphics.drawLine(n5 - 3 + 0, n3 - 3 + 20 + 6 - 3, n5 - 3 + 0, n3 - 3 + 3);
                        graphics.setColor(brighter);
                        graphics.drawLine(n5 - 3 + 1, n3 - 3 + 20 + 6 - 3, n5 - 3 + 1, n3 - 3 + 3);
                        graphics.drawLine(n5 - 3 + 1, n3 - 3 + 3, n5 - 3 + 3, n3 - 3 + 1);
                        graphics.drawLine(n5 - 3 + 3, n3 - 3 + 1, n5 - 3 + 20 + 6 - 3, n3 - 3 + 1);
                        graphics.setColor(darker);
                        graphics.drawLine(n5 - 3 + 3, n3 - 3 + 20 + 6 - 1, n5 - 3 + 20 + 6 - 3, n3 - 3 + 20 + 6 - 1);
                        graphics.drawLine(n5 - 3 + 20 + 6 - 3, n3 - 3 + 20 + 6 - 1, n5 - 3 + 20 + 6 - 1, n3 - 3 + 20 + 6 - 3);
                        graphics.drawLine(n5 - 3 + 20 + 6 - 1, n3 - 3 + 20 + 6 - 3, n5 - 3 + 20 + 6 - 1, n3 - 3 + 3);
                    }
                    graphics.setColor(Color.black);
                    graphics.drawLine(n5 + 3, n3 + 0, n5 + 20 - 1 - 2, n3 + 0);
                    graphics.drawLine(n5 + 20 - 1 - 1, n3 + 1, n5 + 20 - 1, n3 + 2);
                    graphics.drawLine(n5 + 20 - 1, n3 + 3, n5 + 20 - 1, n3 + 20 - 1 - 2);
                    graphics.drawLine(n5 + 20 - 1 - 1, n3 + 20 - 1 - 1, n5 + 20 - 1 - 2, n3 + 20 - 1);
                    graphics.drawLine(n5 + 20 - 1 - 3, n3 + 20 - 1, n5 + 2, n3 + 20 - 1);
                    graphics.drawLine(n5 + 1, n3 + 20 - 1 - 1, n5 + 0, n3 + 20 - 1 - 2);
                    graphics.drawLine(n5 + 0, n3 + 20 - 1 - 3, n5 + 0, n3 + 2);
                    graphics.drawLine(n5 + 1, n3 + 1, n5 + 2, n3 + 0);
                    graphics.setColor(background);
                    graphics.drawLine(n5 + 1, n3 + 20 - 1 - 2, n5 + 1, n3 + 2);
                    graphics.drawLine(n5 + 2, n3 + 1, n5 + 20 - 1 - 2, n3 + 1);
                    graphics.setColor(brighter);
                    graphics.drawLine(n5 + 2, n3 + 20 - 1 - 3, n5 + 2, n3 + 2);
                    graphics.drawLine(n5 + 3, n3 + 2, n5 + 20 - 1 - 2, n3 + 2);
                    graphics.setColor(darker.darker());
                    graphics.drawLine(n5 + 2, n3 + 20 - 1 - 1, n5 + 20 - 1 - 2, n3 + 20 - 1 - 1);
                    graphics.drawLine(n5 + 20 - 1 - 1, n3 + 20 - 1 - 2, n5 + 20 - 1 - 1, n3 + 2);
                    graphics.setColor(darker);
                    graphics.drawLine(n5 + 2, n3 + 20 - 1 - 2, n5 + 20 - 1 - 2, n3 + 20 - 1 - 2);
                    graphics.drawLine(n5 + 20 - 1 - 2, n3 + 20 - 1 - 3, n5 + 20 - 1 - 2, n3 + 3);
                    if (this.isEnabled()) {
                        graphics.setColor((Color)this.vecPalette.elementAt(i));
                        graphics.fillRect(n5 + 3, n3 + 3, 14, 14);
                    }
                }
                n3 += 23;
            }
        }
    }
}
