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

final class RightPanel extends JPanel
{
    private int rowGroup;
    
    public RightPanel(final TextPanel theTextPanel) {
        this.rowGroup = 0;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                final int j = e.getY() / 25;
                if (RightPanel.this.rowGroup != j && j < 16 && j >= 0) {
                    RightPanel.this.rowGroup = j;
                    RightPanel.this.repaint();
                    theTextPanel.setRowGroup(j);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                final int j = e.getY() / 25;
                if (RightPanel.this.rowGroup != j && j < 16 && j >= 0) {
                    RightPanel.this.rowGroup = j;
                    RightPanel.this.repaint();
                    theTextPanel.setRowGroup(j);
                }
            }
        });
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 25, 400);
        final Color aColor = g.getColor();
        g.setColor(Color.white);
        for (int i = 0; i < 16; ++i) {
            g.drawString(Integer.toString(i, 16), 7, i * 25 + 20);
        }
        g.setColor(aColor);
        g.drawRect(50, 0, 24, 399);
        for (int i = 1; i < 16; ++i) {
            g.drawLine(50, i * 25, 75, i * 25);
        }
        g.fillRect(50, this.rowGroup * 25, 25, 25);
    }
}
