// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.util.Enumeration;
import COM.objectspace.jgl.Array;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.text.BreakIterator;
import java.awt.FontMetrics;

public class TextWrapper
{
    int alignment;
    FontMetrics fontMetrics;
    BreakIterator textBoundary;
    Font font;
    String[] lines;
    int[] length;
    String text;
    int hintWidth;
    int lineCount;
    int charHeight;
    int maxAscent;
    Dimension size;
    
    TextWrapper(final Font font, final String text, final int alignment, final int hintWidth) {
        this.alignment = alignment;
        this.font = font;
        this.fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        this.textBoundary = BreakIterator.getWordInstance();
        this.text = text;
        this.hintWidth = hintWidth;
        this.charHeight = this.fontMetrics.getHeight();
        this.maxAscent = this.fontMetrics.getMaxAscent();
    }
    
    public Dimension getSize(final Graphics g) {
        if (this.size == null) {
            this.calcSize(g);
        }
        return this.size;
    }
    
    protected void calcSize(final Graphics g) {
        final Array words = new Array();
        this.textBoundary.setText(this.text);
        int start = this.textBoundary.first();
        for (int end = this.textBoundary.next(); end != -1; end = this.textBoundary.next()) {
            words.add(this.text.substring(start, end));
            start = end;
        }
        this.lines = new String[words.size()];
        this.length = new int[words.size()];
        this.lineCount = 0;
        int l = 0;
        int curLine = 0;
        int width = 0;
        int height = 0;
        final Enumeration enum1 = words.elements();
        while (enum1.hasMoreElements()) {
            String w = enum1.nextElement();
            int wl = this.fontMetrics.stringWidth(w);
            final int newLength = this.length[curLine] + wl;
            final boolean isCR = w.charAt(0) == '\r';
            final boolean isLF = w.charAt(0) == '\n';
            if (isCR) {
                continue;
            }
            if (!isLF && newLength <= this.hintWidth) {
                if (this.lines[curLine] == null) {
                    this.lines[curLine] = w;
                    this.length[curLine] = wl;
                    l = 0;
                    ++this.lineCount;
                    height += this.charHeight;
                    if (newLength <= width) {
                        continue;
                    }
                    width = newLength;
                }
                else {
                    this.lines[curLine] = String.valueOf(this.lines[curLine]).concat(String.valueOf(w));
                    if ((this.length[curLine] = newLength) <= width) {
                        continue;
                    }
                    width = newLength;
                }
            }
            else {
                if (isLF) {
                    wl = 0;
                    w = w.substring(1);
                }
                if (wl > width) {
                    width = wl;
                }
                if (this.length[curLine] == 0) {
                    this.lines[curLine] = w;
                    this.length[curLine] = wl;
                    ++this.lineCount;
                    ++curLine;
                }
                else {
                    ++this.lineCount;
                    ++curLine;
                    this.lines[curLine] = w;
                    this.length[curLine] = wl;
                }
                l = 0;
                height += this.charHeight;
            }
        }
        this.size = new Dimension(width, height);
    }
    
    public void paint(final Graphics g, final int x, int y, final int maxWidth, final int maxHeight) {
        final int stop = y + maxHeight + this.charHeight;
        final int hAlign = this.alignment & 0xF;
        final int vAlign = this.alignment & 0xF0;
        y -= this.charHeight - this.maxAscent;
        switch (vAlign) {
            case 32: {
                y += (maxHeight - this.lineCount * this.charHeight) / 2;
                break;
            }
            case 48: {
                y += maxHeight - this.lineCount * this.charHeight;
                break;
            }
        }
        for (int i = 0; i < this.lineCount; ++i) {
            int dx = 0;
            switch (hAlign) {
                case 2: {
                    dx = (maxWidth - this.length[i]) / 2;
                    break;
                }
                case 3: {
                    dx = maxWidth - this.length[i];
                    break;
                }
            }
            g.drawString(this.lines[i], x + dx, y + this.charHeight);
            y += this.charHeight;
            if (y > stop) {
                return;
            }
        }
    }
}
