// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.a.b;

import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class e implements d
{
    private static final Font a;
    private static final Color b;
    private String[] c;
    private Dimension[] d;
    private Font e;
    private Color f;
    private int g;
    private boolean h;
    private static String[] z;
    
    public e() {
        this((String[])null, null, null, 0);
    }
    
    public e(final String s, final Font font, final Color color) {
        this(s, font, color, 0);
    }
    
    public e(final String s, final Font font, final Color color, final int n) {
        this.c = new String[] { " " };
        this.e = a.b.o.a.b.e.a;
        this.f = a.b.o.a.b.e.b;
        this.g = 0;
        this.h = false;
        this.a(s);
        this.a(font);
        this.a(color);
        this.a(n);
    }
    
    public e(final String[] array, final Font font, final Color color) {
        this(array, font, color, 0);
    }
    
    public e(final String[] array, final Font font, final Color color, final int n) {
        this.c = new String[] { " " };
        this.e = a.b.o.a.b.e.a;
        this.f = a.b.o.a.b.e.b;
        this.g = 0;
        this.h = false;
        this.a(array);
        this.a(font);
        this.a(color);
        this.a(n);
    }
    
    public String[] a() {
        return this.c;
    }
    
    public void a(final String s) {
        if (s != null) {
            this.c = this.b(s);
        }
        else {
            this.c = this.b(" ");
        }
    }
    
    public void a(final String[] c) {
        if (c != null) {
            this.c = c;
        }
        else {
            this.c = new String[] { " " };
        }
    }
    
    public Font b() {
        return this.e;
    }
    
    public void a(final Font e) {
        if (e != null) {
            this.e = e;
        }
        else {
            this.e = e.a;
        }
    }
    
    public Color c() {
        return this.f;
    }
    
    public void a(final Color f) {
        if (f != null) {
            this.f = f;
        }
        else {
            this.f = a.b.o.a.b.e.b;
        }
    }
    
    public int d() {
        return this.g;
    }
    
    public void a(final int g) {
        this.g = g;
    }
    
    public void a(final boolean h) {
        this.h = h;
    }
    
    public boolean e() {
        return this.h;
    }
    
    private String[] b(final String s) {
        final Vector vector = new Vector<String>();
        int i = 0;
        int n = 0;
        while (i == 0) {
            final int index = s.indexOf(a.b.o.a.b.e.z[0], n);
            if (index >= 0) {
                vector.addElement(s.substring(n, index));
                n = index + a.b.o.a.b.e.z[0].length();
            }
            else {
                vector.addElement(s.substring(n, s.length()));
                i = 1;
            }
        }
        final String[] array = new String[vector.size()];
        for (int j = 0; j < array.length; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    public Dimension[] a(final FontMetrics fontMetrics) {
        if (this.d == null) {
            return this.b(fontMetrics);
        }
        return this.d;
    }
    
    public Dimension[] b(final FontMetrics fontMetrics) {
        final Vector vector = new Vector<Dimension>();
        int n = 0;
        if (this.e != null) {
            n = fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        }
        for (int i = 0; i < this.c.length; ++i) {
            int stringWidth;
            if (this.c[i] != null && this.e != null) {
                stringWidth = fontMetrics.stringWidth(this.c[i]);
            }
            else {
                stringWidth = 0;
            }
            vector.addElement(new Dimension(stringWidth, n));
        }
        final Dimension[] d = new Dimension[vector.size()];
        for (int j = 0; j < d.length; ++j) {
            d[j] = vector.elementAt(j);
        }
        return this.d = d;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = " O".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '|';
                    break;
                }
                case 1: {
                    c2 = '!';
                    break;
                }
                case 2: {
                    c2 = '5';
                    break;
                }
                case 3: {
                    c2 = '\u001d';
                    break;
                }
                default: {
                    c2 = 'P';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "/DGt6".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '|';
                    break;
                }
                case 1: {
                    c4 = '!';
                    break;
                }
                case 2: {
                    c4 = '5';
                    break;
                }
                case 3: {
                    c4 = '\u001d';
                    break;
                }
                default: {
                    c4 = 'P';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        e.z = z;
        a = new Font(e.z[1], 1, 16);
        b = new Color(0, 0, 0);
    }
}
