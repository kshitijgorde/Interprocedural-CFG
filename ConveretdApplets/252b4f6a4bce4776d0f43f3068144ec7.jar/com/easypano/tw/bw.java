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
        this.c = new ea(this);
        this.b = b;
    }
    
    public void a(final bx bx, final cf cf) {
        final int e = ds.e(bx.a(a("BTmW\u0013\rO|S")));
        try {
            int n = 0;
            while (true) {
                Label_0075: {
                    if (h.q == 0) {
                        break Label_0075;
                    }
                    this.a.addElement(new cs(this.b, ds.j(bx.a("a" + n + a("\rQhJ\u0014")))));
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
        if (h.q == 0) {
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
        ds.stopThread(this.c, a("bTmW\u0013\u0003Mf_\u0018\u0003UaL\u0019BE)O\tJU"), 100, 0);
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
                            c2 = '#';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = '\t';
                            break;
                        }
                        case 3: {
                            c2 = '>';
                            break;
                        }
                        default: {
                            c2 = '|';
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
