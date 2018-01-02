// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.Color;

public class o
{
    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public String f;
    public Color g;
    public URL h;
    public String i;
    public String j;
    public String k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public boolean s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;
    
    public o(final double a, final String f, final Color g, final URL h, final String j, final String k) {
        this.i = a("XOD[D");
        this.s = false;
        this.a = a;
        this.f = f;
        this.g = g;
        this.h = h;
        this.j = j;
        this.k = k;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = '<';
                    break;
                }
                case 2: {
                    c2 = '!';
                    break;
                }
                case 3: {
                    c2 = '7';
                    break;
                }
                default: {
                    c2 = '\"';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
