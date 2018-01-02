// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.unicode;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

final class CharPanel extends JPanel
{
    private String theFontFamily;
    private char theChar;
    private int digit1;
    private int digit2;
    private int digit3;
    private int digit4;
    
    public CharPanel() {
        this.theFontFamily = "Monospaced";
        this.digit1 = 0;
        this.digit2 = 0;
        this.digit3 = 0;
        this.digit4 = 0;
        this.theChar = ' ';
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final String aString = Integer.toString(this.digit1, 16) + Integer.toString(this.digit2, 16) + Integer.toString(this.digit3, 16) + Integer.toString(this.digit4, 16);
        g.drawString(aString, 28, 22);
        g.drawRect(25, 25, 49, 49);
        g.drawRect(26, 26, 47, 47);
        final Font fontMed = g.getFont();
        final Font fontBig = new Font(this.theFontFamily, fontMed.getStyle(), fontMed.getSize() * 3);
        g.setFont(fontBig);
        final char[] Chars = { this.theChar };
        g.drawChars(Chars, 0, 1, 35, 66);
    }
    
    public void setChar(final int a, final int b, final int c, final int d) {
        this.digit1 = d;
        this.digit2 = c;
        this.digit3 = b;
        this.digit4 = a;
        final int i = this.digit4 + this.digit3 * 16 + this.digit2 * 256 + this.digit1 * 4096;
        this.theChar = (char)i;
        this.repaint();
    }
    
    public void setFontFamily(final String theFont) {
        this.theFontFamily = theFont;
        this.repaint();
    }
}
