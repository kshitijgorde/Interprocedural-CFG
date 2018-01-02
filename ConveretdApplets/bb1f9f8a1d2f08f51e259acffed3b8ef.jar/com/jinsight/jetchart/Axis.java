// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.Observable;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import java.util.Observer;

public abstract class Axis implements Observer
{
    protected static final int a = 0;
    protected static final int b = 1;
    Color c;
    Color d;
    int e;
    int f;
    int g;
    int h;
    Insets i;
    Graphics j;
    Graph k;
    boolean l;
    boolean m;
    Font n;
    String o;
    Color p;
    Color q;
    Font r;
    private a s;
    
    public void setFont(final Font n) {
        this.n = n;
        this.k.cg = true;
    }
    
    public void setColor(final Color c) {
        this.c = c;
    }
    
    public void setFill3DColor(final Color d) {
        this.d = d;
    }
    
    public void setTitle(final String o) {
        this.o = o;
        this.k.cg = true;
    }
    
    public void setTitleForeground(final Color p) {
        this.p = p;
    }
    
    public void setTitleBackground(final Color q) {
        this.q = q;
    }
    
    public void setTitleFont(final Font r) {
        this.r = r;
        this.k.cg = true;
    }
    
    void a(final boolean l) {
        this.l = l;
    }
    
    void b(final boolean m) {
        this.m = m;
    }
    
    Image a(final int n) {
        final boolean g = GraphSerie.G;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.r);
        final int stringWidth = fontMetrics.stringWidth(this.o);
        final int n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final Image image = this.k.createImage(stringWidth, n2);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.r);
        Label_0125: {
            if (this.k.bk) {
                graphics.setColor(this.k.getBackground());
                if (!g) {
                    break Label_0125;
                }
            }
            if (this.p.equals(Color.white)) {
                graphics.setColor(Color.black);
                if (!g) {
                    break Label_0125;
                }
            }
            graphics.setColor(Color.white);
        }
        graphics.fillRect(0, 0, stringWidth, n2);
        graphics.setColor(this.p);
        graphics.drawString(this.o, 0, fontMetrics.getMaxAscent() + fontMetrics.getLeading());
        double angle = 0.0;
        Label_0183: {
            if (n == 0) {
                angle = 0.0;
                if (!g) {
                    break Label_0183;
                }
            }
            angle = 1.5707963267948966;
        }
        Label_0217: {
            if (this.s == null) {
                this.s = new a(angle);
                if (!g) {
                    break Label_0217;
                }
            }
            this.s.setAngle(angle);
        }
        final Image image2 = this.k.createImage(new FilteredImageSource(image.getSource(), new b(this.p)));
        FilteredImageSource filteredImageSource;
        if (this.k.bk) {
            filteredImageSource = new FilteredImageSource(image.getSource(), this.s);
        }
        else {
            filteredImageSource = new FilteredImageSource(image2.getSource(), this.s);
        }
        return this.k.createImage(filteredImageSource);
    }
    
    public abstract void update(final Observable p0, final Object p1);
    
    Axis(final Graph k) {
        this.c = Color.black;
        this.d = Color.white;
        this.l = true;
        this.m = true;
        this.n = new Font(a("x\u001fvV\u007fN\fqC"), 1, 10);
        this.p = Color.black;
        this.q = Color.white;
        this.r = new Font(a("x\u001fvV\u007fN\fqC"), 1, 10);
        this.k = k;
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
                            c2 = '+';
                            break;
                        }
                        case 1: {
                            c2 = '~';
                            break;
                        }
                        case 2: {
                            c2 = '\u0018';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = ',';
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
