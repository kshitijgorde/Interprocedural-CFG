// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.component;

import java.awt.Graphics;
import java.awt.Color;

public class InputField
{
    int maxchars;
    int cursorPos;
    DataEditor de;
    String sval;
    char[] buffer;
    int nChars;
    int width;
    int height;
    Color bgColor;
    Color fgColor;
    public static final boolean DEBUG = false;
    
    public InputField(final String sval, final DataEditor de, final int width, final int height, final Color bgColor, final Color fgColor) {
        this.maxchars = 50;
        this.cursorPos = 0;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.de = de;
        this.buffer = new char[this.maxchars];
        this.nChars = 0;
        if (sval != null) {
            sval.getChars(0, sval.length(), this.buffer, 0);
            this.nChars = sval.length();
        }
        this.sval = sval;
    }
    
    public void setText(final String s) {
        for (int i = 0; i < this.maxchars; ++i) {
            this.buffer[i] = '\0';
        }
        this.sval = new String(s);
        if (s == null || s.equals("NaN")) {
            this.sval = "";
            this.nChars = 0;
            this.buffer[0] = '\0';
        }
        else {
            this.sval.getChars(0, this.sval.length(), this.buffer, 0);
            this.nChars = s.length();
            this.sval = new String(this.buffer);
        }
    }
    
    public String getValue() {
        return this.sval;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(n, n2, this.width, this.height);
        if (this.sval != null) {
            graphics.setColor(this.fgColor);
            graphics.drawString(this.sval.trim(), n, n2 + this.height / 2 + 3);
        }
    }
    
    public void mouseUp(final int n, final int n2) {
    }
    
    public void keyDown(final int n) {
        if (this.nChars < this.maxchars) {
            switch (n) {
                case 8: {
                    --this.nChars;
                    if (this.nChars < 0) {
                        this.nChars = 0;
                    }
                    this.buffer[this.nChars] = '\0';
                    this.sval = new String(new String(this.buffer));
                    break;
                }
                case 10: {
                    this.sval = new String(new String(this.buffer));
                    this.selected();
                    break;
                }
                default: {
                    this.buffer[this.nChars++] = (char)n;
                    this.sval = new String(new String(this.buffer));
                    break;
                }
            }
            this.sval = this.sval.trim();
        }
        this.de.repaint();
    }
    
    public void selected() {
        System.out.println("InputField.selected(null)");
    }
}
