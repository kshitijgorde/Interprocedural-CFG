// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.unicode;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

final class TextPanel extends JPanel
{
    private final CharPanel theCharPanel;
    private String theFontFamily;
    private int colGroup;
    private int rowGroup;
    
    public TextPanel(final CharPanel theCharPanel) {
        this.theFontFamily = "Monospaced";
        this.colGroup = 0;
        this.rowGroup = 0;
        this.theCharPanel = theCharPanel;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                final int i = e.getX() / 25;
                final int j = e.getY() / 25;
                theCharPanel.setChar(j, i, TextPanel.this.rowGroup, TextPanel.this.colGroup);
            }
        });
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font(this.theFontFamily, 0, 16));
        g.drawRect(0, 0, 399, 399);
        for (int i = 1; i < 16; ++i) {
            g.drawLine(0, i * 25, 400, i * 25);
            g.drawLine(i * 25, 0, i * 25, 400);
        }
        final char[] Chars = { '\0' };
        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                final int anInt = k + j * 16 + this.rowGroup * 256 + this.colGroup * 4096;
                Chars[0] = (char)anInt;
                g.drawChars(Chars, 0, 1, j * 25 + 4, k * 25 + 21);
            }
        }
    }
    
    public void setColGroup(final int i) {
        this.colGroup = i;
        this.repaint();
    }
    
    public void setFontFamily(final String theFontFamily) {
        this.theFontFamily = theFontFamily;
        this.theCharPanel.setFontFamily(theFontFamily);
        this.repaint();
    }
    
    public void setRowGroup(final int j) {
        this.rowGroup = j;
        this.repaint();
    }
}
