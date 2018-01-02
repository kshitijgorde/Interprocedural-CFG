// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.Graphics;
import java.awt.Color;

public class Line
{
    TextDisplay TD;
    boolean Chosen;
    int Block;
    int Pos;
    int Posend;
    static final int NONE = 0;
    static final int START = 1;
    static final int END = 2;
    static final int FULL = 4;
    Color C;
    char[] a;
    
    public Line(final String s, final TextDisplay textDisplay) {
        this(s, textDisplay, Color.black);
    }
    
    public Line(final String s, final TextDisplay td, final Color c) {
        this.TD = td;
        this.C = c;
        this.Block = 0;
        this.a = s.toCharArray();
    }
    
    int length() {
        return this.a.length;
    }
    
    int getpos(final int n, final int n2) {
        final int[] getwidth = this.TD.getwidth(this.a);
        int n3 = n2 - this.TD.Offset * this.TD.FM.charWidth(' ');
        if (n < n3) {
            return 0;
        }
        int n4;
        for (n4 = 0; n > n3 && n4 < this.a.length; n3 += getwidth[n4], ++n4) {}
        return n4;
    }
    
    public void draw(final Graphics graphics, int n, final int n2) {
        n -= this.TD.Offset * this.TD.FM.charWidth(' ');
        if (this.Chosen || (this.Block & 0x4) != 0x0) {
            graphics.setColor(Color.darkGray);
            graphics.fillRect(n, n2 - this.TD.Ascent, this.TD.FM.charsWidth(this.a, 0, this.a.length), this.TD.Height);
            graphics.setColor(Color.white);
            graphics.drawChars(this.a, 0, this.a.length, n, n2);
            return;
        }
        if ((this.Block & 0x1) == 0x0) {
            if ((this.Block & 0x2) != 0x0) {
                final int charsWidth = this.TD.FM.charsWidth(this.a, 0, this.Posend);
                graphics.setColor(Color.darkGray);
                graphics.fillRect(n, n2 - this.TD.Ascent, charsWidth, this.TD.Height);
                graphics.setColor(Color.white);
                graphics.drawChars(this.a, 0, this.Posend, n, n2);
                graphics.setColor(this.C);
                n += charsWidth;
                if (this.a.length > this.Posend) {
                    graphics.drawChars(this.a, this.Posend, this.a.length - this.Posend, n, n2);
                }
            }
            else {
                graphics.setColor(this.C);
                graphics.drawChars(this.a, 0, this.a.length, n, n2);
            }
            return;
        }
        if (this.Pos > 0) {
            graphics.drawChars(this.a, 0, this.Pos, n, n2);
            n += this.TD.FM.charsWidth(this.a, 0, this.Pos);
        }
        if ((this.Block & 0x2) != 0x0) {
            if (this.Posend > this.Pos) {
                final int charsWidth2 = this.TD.FM.charsWidth(this.a, this.Pos, this.Posend - this.Pos);
                graphics.setColor(Color.darkGray);
                graphics.fillRect(n, n2 - this.TD.Ascent, charsWidth2, this.TD.Height);
                graphics.setColor(Color.white);
                graphics.drawChars(this.a, this.Pos, this.Posend - this.Pos, n, n2);
                graphics.setColor(this.C);
                n += charsWidth2;
                if (this.a.length > this.Posend) {
                    graphics.drawChars(this.a, this.Posend, this.a.length - this.Posend, n, n2);
                }
            }
            else {
                graphics.drawChars(this.a, this.Pos, this.a.length - this.Pos, n, n2);
            }
            return;
        }
        final int charsWidth3 = this.TD.FM.charsWidth(this.a, this.Pos, this.a.length - this.Pos);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(n, n2 - this.TD.Ascent, charsWidth3, this.TD.Height);
        graphics.setColor(Color.white);
        graphics.drawChars(this.a, this.Pos, this.a.length - this.Pos, n, n2);
    }
    
    void append(final String s) {
        this.a = (String.valueOf(new String(this.a)) + s).toCharArray();
    }
    
    void chosen(final boolean chosen) {
        this.Chosen = chosen;
    }
    
    void block(final int n, final int n2) {
        switch (n2) {
            case 0: {
                this.Block = 0;
            }
            case 4: {
                this.Block = 4;
            }
            case 1: {
                this.Block |= 0x1;
                this.Pos = n;
            }
            case 2: {
                this.Block |= 0x2;
                this.Posend = n;
            }
            default: {}
        }
    }
    
    String getblock() {
        if (this.Block == 4) {
            return new String(this.a, 0, this.a.length);
        }
        if ((this.Block & 0x1) != 0x0) {
            if ((this.Block & 0x2) != 0x0) {
                return new String(this.a, this.Pos, this.Posend - this.Pos);
            }
            return new String(this.a, this.Pos, this.a.length - this.Pos);
        }
        else {
            if ((this.Block & 0x2) != 0x0) {
                return new String(this.a, 0, this.Posend);
            }
            return "";
        }
    }
}
