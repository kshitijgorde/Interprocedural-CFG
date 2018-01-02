// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.Color;

public class n
{
    public double a;
    public String b;
    public Color c;
    public URL d;
    public String e;
    public String f;
    public String g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    
    public n(final double a, final String b, final Color c, final URL d, final String f, final String g) {
        this.e = a("6Yv*\"");
        this.k = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
        this.g = g;
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
                    c2 = 'i';
                    break;
                }
                case 1: {
                    c2 = '*';
                    break;
                }
                case 2: {
                    c2 = '\u0013';
                    break;
                }
                case 3: {
                    c2 = 'F';
                    break;
                }
                default: {
                    c2 = 'D';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
