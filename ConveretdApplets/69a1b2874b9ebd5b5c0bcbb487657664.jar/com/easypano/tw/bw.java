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
        this.c = new dz(this);
        this.b = b;
    }
    
    public void a(final bx bx, final cf cf) {
        final int e = dt.e(bx.a(a("X^\u0019EY\u0017E\bA")));
        try {
            int n = 0;
            while (true) {
                Label_0075: {
                    if (!h.q) {
                        break Label_0075;
                    }
                    this.a.addElement(new ct(this.b, dt.j(bx.a("a" + n + a("\u0017[\u001cX^")))));
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
    
    public ct a(final int n) {
        int n2 = n;
        if (!h.q) {
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
        dt.stopThread(this.c, a("x^\u0019EY\u0019G\u0012MR\u0019_\u0015^SXO]]CP_"), 100, 0);
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
                            c2 = '9';
                            break;
                        }
                        case 1: {
                            c2 = '+';
                            break;
                        }
                        case 2: {
                            c2 = '}';
                            break;
                        }
                        case 3: {
                            c2 = ',';
                            break;
                        }
                        default: {
                            c2 = '6';
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
