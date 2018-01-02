// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;

public class SliceLegend
{
    public static final int VALUE = 0;
    public static final int PERCENT = 1;
    public static final int LABEL = 2;
    public static final int LABEL_AND_VALUE = 3;
    public static final int LABEL_AND_PERCENT = 4;
    public static final int VALUE_AND_PERCENT = 5;
    public static final int INSIDE = 0;
    public static final int OUTSIDE = 1;
    public static final int SHADOWED = 0;
    public static final int LOWERED_BEVEL = 1;
    public static final int RAISED_BEVEL = 2;
    Font a;
    private Color b;
    private Color c;
    private Color d;
    private Slice e;
    private DecimalFormat f;
    private int g;
    private double h;
    private boolean i;
    private int j;
    
    public void setBackground(final Color b) {
        this.b = b;
    }
    
    public void setForeground(final Color c) {
        this.c = c;
    }
    
    public void setFont(final Font a) {
        this.a = a;
    }
    
    public void setContent(final int j) {
        this.j = j;
    }
    
    public void setBorder(final int g) {
        this.g = g;
    }
    
    public void setVertexDistance(double h) {
        if (h > 1.0) {
            h = 1.0;
        }
        if (h < 0.0) {
            h = 0.0;
        }
        this.h = h;
    }
    
    public void setOpacityEnabled(final boolean i) {
        this.i = i;
    }
    
    public void setArrowColor(final Color d) {
        this.d = d;
    }
    
    void a(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        int n = 0;
        int n2 = 0;
        final String a = this.a();
        graphics.setFont(this.a);
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.a);
        final int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + 4;
        final int n4 = fontMetrics.stringWidth(a) + 4;
        final double n5 = this.e.g();
        final double n6 = (this.e.h() - n5) / 2.0 + n5;
        boolean a2 = false;
        boolean a3 = false;
        Label_0603: {
            if (this.e.o.z == 1) {
                this.e.a((int)n6, this.e.j);
                final i j = this.e.j;
                a2 = this.e.o.d.bJ.a(1, (int)n6);
                a3 = this.e.o.d.bJ.a(2, (int)n6);
                final boolean a4 = this.e.o.d.bJ.a(3, (int)n6);
                final boolean a5 = this.e.o.d.bJ.a(4, (int)n6);
                Label_0415: {
                    if (a2) {
                        n = (int)j.a;
                        n2 = (int)j.b - n3 - 10;
                        if (!g) {
                            break Label_0415;
                        }
                    }
                    if (a3) {
                        n = (int)(j.a - n4);
                        n2 = (int)(j.b - n3) - 10;
                        if (!g) {
                            break Label_0415;
                        }
                    }
                    if (a4) {
                        n = (int)(j.a - n4);
                        n2 = (int)j.b + 10 + (this.e.o.d.bc ? this.e.o.x : 0);
                        if (!g) {
                            break Label_0415;
                        }
                    }
                    if (a5) {
                        n = (int)j.a;
                        n2 = (int)j.b + 10 + (this.e.o.d.bc ? this.e.o.x : 0);
                    }
                }
                graphics.setColor(this.d);
                Label_0487: {
                    if (a2 || a3) {
                        graphics.drawLine((int)j.a, (int)j.b, n + n4 / 2, n2 + n3);
                        if (!g) {
                            break Label_0487;
                        }
                    }
                    graphics.drawLine((int)j.a, (int)j.b, n + n4 / 2, n2);
                }
                if (!g) {
                    break Label_0603;
                }
            }
            final double n7 = this.e.a((int)n6) * this.h;
            final double n8 = Math.cos(n6 * 3.141592653589793 / 180.0) * this.e.l * n7;
            final double n9 = Math.sin(n6 * 3.141592653589793 / 180.0) * n7;
            n = (int)(this.e.q.a + n8 - n4 / 2.0);
            n2 = (int)(this.e.q.b - n9 - n3 / 2.0);
        }
        Label_0866: {
            if (this.i) {
                Label_0754: {
                    if (this.g == 0) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(n + 4, n2 + 4, n4, n3);
                        if (!g) {
                            break Label_0754;
                        }
                    }
                    if (this.g == 2) {
                        graphics.setColor(Color.lightGray);
                        graphics.fill3DRect(n - 2, n2 - 2, n4 + 4, n3 + 4, true);
                        graphics.fill3DRect(n - 1, n2 - 1, n4 + 2, n3 + 2, true);
                        if (!g) {
                            break Label_0754;
                        }
                    }
                    if (this.g == 1) {
                        graphics.setColor(Color.lightGray);
                        graphics.fill3DRect(n - 2, n2 - 2, n4 + 4, n3 + 4, true);
                        graphics.fill3DRect(n - 1, n2 - 1, n4 + 2, n3 + 2, false);
                    }
                }
                graphics.setColor(this.b);
                graphics.fillRect(n, n2, n4, n3);
                if (this.g == 0) {
                    graphics.setColor(Color.black);
                    graphics.drawRect(n, n2, n4, n3);
                }
                if (!g) {
                    break Label_0866;
                }
            }
            if (this.e.o.z == 1) {
                graphics.setColor(this.d);
                if (a2 || a3) {
                    graphics.drawLine(n, n2 + n3, n + n4, n2 + n3);
                    if (!g) {
                        break Label_0866;
                    }
                }
                graphics.drawLine(n, n2, n + n4, n2);
            }
        }
        graphics.setColor(this.c);
        graphics.drawString(a, n + 3, n2 + 2 + fontMetrics.getMaxAscent());
    }
    
    String a() {
        String s = null;
        switch (this.j) {
            case 0: {
                s = this.b(this.e.a);
                break;
            }
            case 1: {
                s = this.a(this.e.a);
                break;
            }
            case 2: {
                s = this.e.o.d.g[this.e.getIndex()];
                break;
            }
            case 3: {
                s = this.e.o.d.g[this.e.getIndex()] + a("B\u001eq") + this.b(this.e.a);
                break;
            }
            case 4: {
                s = this.e.o.d.g[this.e.getIndex()] + a("B\u001eq") + this.a(this.e.a);
                break;
            }
            case 5: {
                s = this.b(this.e.a) + a("B\u001eq") + this.a(this.e.a);
                if (GraphSerie.G) {}
                break;
            }
        }
        return s;
    }
    
    private String a(final double n) {
        final double n2 = n * 100.0 / this.e.o.d.bJ.g();
        if (this.e.o.B != null) {
            this.f.applyPattern(this.e.o.B);
        }
        return this.f.format(n2) + a("B\u0016");
    }
    
    private String b(final double n) {
        final String bw = this.e.o.d.bw;
        String s;
        if (bw != null) {
            this.f.applyPattern(bw);
            s = this.f.format(n);
        }
        else {
            s = Double.toString(n);
        }
        return s;
    }
    
    SliceLegend(final Slice e) {
        this.a = new Font(a("1R?!2\u0007A84"), 0, 10);
        this.b = new Color(255, 255, 204);
        this.c = Color.black;
        this.d = Color.black;
        this.g = 0;
        this.h = 0.7;
        this.j = 0;
        this.e = e;
        this.f = new DecimalFormat();
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
                            c2 = 'b';
                            break;
                        }
                        case 1: {
                            c2 = '3';
                            break;
                        }
                        case 2: {
                            c2 = 'Q';
                            break;
                        }
                        case 3: {
                            c2 = 'R';
                            break;
                        }
                        default: {
                            c2 = 'a';
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
