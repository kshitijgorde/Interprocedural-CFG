// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.unicode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

final class BottomPanel extends JPanel
{
    private int colGroup;
    
    public BottomPanel(final TextPanel theTextPanel) {
        this.colGroup = 0;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                final int i = e.getX() / 25;
                if (BottomPanel.this.colGroup != i && i < 16 && i >= 0) {
                    BottomPanel.this.colGroup = i;
                    BottomPanel.this.repaint();
                    theTextPanel.setColGroup(i);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                final int i = e.getX() / 25;
                if (BottomPanel.this.colGroup != i && i < 16 && i >= 0) {
                    BottomPanel.this.colGroup = i;
                    BottomPanel.this.repaint();
                    theTextPanel.setColGroup(i);
                }
            }
        });
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 400, 25);
        final Color aColor = g.getColor();
        g.setColor(Color.white);
        for (int i = 0; i < 16; ++i) {
            g.drawString(Integer.toString(i, 16), i * 25 + 7, 20);
        }
        g.setColor(aColor);
        g.drawRect(0, 50, 399, 24);
        for (int i = 1; i < 16; ++i) {
            g.drawLine(i * 25, 50, i * 25, 75);
        }
        g.fillRect(this.colGroup * 25, 50, 25, 25);
    }
}
