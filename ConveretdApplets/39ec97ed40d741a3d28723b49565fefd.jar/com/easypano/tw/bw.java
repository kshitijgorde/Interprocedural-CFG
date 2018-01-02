// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class bw
{
    volatile Vector a;
    TWViewer b;
    Thread c;
    
    public bw(final TWViewer b) {
        this.a = new Vector();
        this.c = new dy(this);
        this.b = b;
    }
    
    public void a(final bx bx, final cf cf) {
        final int e = ds.e(bx.a(a("3Xr\u0002\u0016|Cc\u0006")));
        try {
            int n = 0;
            while (true) {
                Label_0075: {
                    if (!g.q) {
                        break Label_0075;
                    }
                    this.a.addElement(new cs(this.b, ds.j(bx.a("a" + n + a("|]w\u001f\u0011")))));
                    ++n;
                }
                if (n < e) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
        this.c.start();
    }
    
    public int a() {
        return this.a.size();
    }
    
    public cs a(final int n) {
        int n2 = n;
        if (!g.q) {
            if (n < 0) {
                return null;
            }
            n2 = n;
        }
        if (n2 < this.a.size()) {
            return this.a.elementAt(n);
        }
        return null;
    }
    
    public void destroyResource() {
        ds.stopThread(this.c, a("\u0013Xr\u0002\u0016rAy\n\u001drY~\u0019\u001c3I6\u001a\f;Y"), 100, 0);
        this.c = null;
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            this.a(i).destroyResource();
        }
        this.a.removeAllElements();
        this.a = null;
        this.b = null;
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
                            c2 = 'R';
                            break;
                        }
                        case 1: {
                            c2 = '-';
                            break;
                        }
                        case 2: {
                            c2 = '\u0016';
                            break;
                        }
                        case 3: {
                            c2 = 'k';
                            break;
                        }
                        default: {
                            c2 = 'y';
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
