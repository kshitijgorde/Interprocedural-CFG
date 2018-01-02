// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.NoSuchElementException;
import java.util.Enumeration;

class e implements Enumeration
{
    boolean a;
    int b;
    d[] c;
    d d;
    
    public boolean hasMoreElements() {
        if (this.d != null) {
            return true;
        }
        while (true) {
            Label_0034: {
                if (!GraphSerie.G) {
                    break Label_0034;
                }
                if ((this.d = this.c[this.b]) != null) {
                    return true;
                }
            }
            if (this.b-- <= 0) {
                return false;
            }
            continue;
        }
    }
    
    public Object nextElement() {
        if (this.d == null) {
            if (GraphSerie.G) {}
            while (this.b-- > 0 && (this.d = this.c[this.b]) == null) {}
        }
        if (this.d != null) {
            final d d = this.d;
            this.d = d.d;
            return this.a ? new Integer(d.b) : d.c;
        }
        throw new NoSuchElementException(a("\u0019R\u0005\fr#T\u0005%q<Y4*f=Y\u0003%g?N"));
    }
    
    e(final d[] c, final boolean a) {
        this.c = c;
        this.a = a;
        this.b = c.length;
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
                            c2 = 'P';
                            break;
                        }
                        case 1: {
                            c2 = '<';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = 'D';
                            break;
                        }
                        default: {
                            c2 = '\u0013';
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
