// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;

public class Note
{
    String[] a;
    Color b;
    Color c;
    int d;
    int e;
    Font f;
    boolean g;
    boolean h;
    private Rectangle i;
    
    public void setLocation(final int d, final int e) {
        this.d = d;
        this.e = e;
    }
    
    public void setText(final String[] a) {
        this.a = a;
    }
    
    public void setFont(final Font f) {
        this.f = f;
    }
    
    public void setForeground(final Color c) {
        this.c = c;
    }
    
    public void setBackground(final Color b) {
        this.b = b;
    }
    
    public void setOpacityEnabled(final boolean g) {
        this.g = g;
    }
    
    public void setBorderEnabled(final boolean h) {
        this.h = h;
    }
    
    boolean a(final int n, final int n2) {
        return this.i.contains(n, n2);
    }
    
    void a(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        graphics.setFont(this.f);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.f);
        int max = 0;
        int height = 0;
        int n = 0;
        while (true) {
            while (true) {
                Label_0077: {
                    if (!g) {
                        break Label_0077;
                    }
                    final Note note = this;
                    max = Math.max(max, fontMetrics.stringWidth(note.a[n]));
                    height += fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
                    ++n;
                }
                if (n < this.a.length) {
                    continue;
                }
                break;
            }
            max += 6;
            height += 4;
            this.i.x = this.d;
            this.i.y = this.e;
            this.i.width = max;
            this.i.height = height;
            final Note note = this;
            if (g) {
                continue;
            }
            break;
        }
        if (this.g) {
            graphics.setColor(Color.black);
            graphics.fillRect(this.d + 2, this.e + 2, max, height);
            graphics.setColor(this.b);
            graphics.setColor(this.b);
            graphics.fillRect(this.d, this.e, max, height);
            if (this.h) {
                graphics.setColor(Color.black);
                graphics.drawRect(this.d, this.e, max, height);
            }
        }
        graphics.setColor(this.c);
        int n2 = this.e + 2 + fontMetrics.getMaxAscent();
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0305: {
                    if (!g) {
                        break Label_0305;
                    }
                    graphics.drawString(this.a[n3], this.d + 3, n2);
                    n2 += fontMetrics.getMaxDescent() + fontMetrics.getLeading() + fontMetrics.getMaxAscent();
                    ++n3;
                }
                if (n3 < this.a.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    public Note() {
        this.b = Color.white;
        this.c = Color.black;
        this.f = new Font(a("-e<E.\u001bv;P"), 0, 10);
        this.g = true;
        this.h = true;
        this.i = new Rectangle();
    }
    
    public Note(final String[] a, final int d, final int e) {
        this.b = Color.white;
        this.c = Color.black;
        this.f = new Font(a("-e<E.\u001bv;P"), 0, 10);
        this.g = true;
        this.h = true;
        this.i = new Rectangle();
        this.a = a;
        this.d = d;
        this.e = e;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '~';
                            break;
                        }
                        case 1: {
                            c2 = '\u0004';
                            break;
                        }
                        case 2: {
                            c2 = 'R';
                            break;
                        }
                        case 3: {
                            c2 = '6';
                            break;
                        }
                        default: {
                            c2 = '}';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
