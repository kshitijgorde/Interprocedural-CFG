// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.awt.FontMetrics;
import com.diginet.digichat.util.n;

public class SmilesArray extends n
{
    public int a(final String s, final FontMetrics fontMetrics) {
        int n = 0;
        for (int i = 0; i < this.b(); ++i) {
            final b1 b1 = (b1)this.d(i);
            int n2 = 0;
            for (int j = s.indexOf(b1.c, 0); j != -1; j = s.indexOf(b1.c, j + 1)) {
                if (com.diginet.digichat.common.b1.a(s, j - 1) && com.diginet.digichat.common.b1.a(s, j + b1.c.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(b1.c);
        }
        return n;
    }
    
    public int ax(final String s) {
        int n = 0;
        for (int i = 0; i < this.b(); ++i) {
            final b1 b1 = (b1)this.d(i);
            int n2 = 0;
            for (int j = s.indexOf(b1.c, 0); j != -1; j = s.indexOf(b1.c, j + 1)) {
                if (com.diginet.digichat.common.b1.a(s, j - 1) && com.diginet.digichat.common.b1.a(s, j + b1.c.length())) {
                    ++n2;
                }
            }
            n += n2;
        }
        return n;
    }
    
    public int b(final String s, final int n) {
        int length = s.length();
        for (int i = 0; i < this.b(); ++i) {
            final b1 b1 = (b1)this.d(i);
            int n2 = s.indexOf(b1.c, n);
            while (n2 != -1 && n2 < length) {
                if (com.diginet.digichat.common.b1.a(s, n2 - 1) && com.diginet.digichat.common.b1.a(s, n2 + b1.c.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(b1.c, n2 + 1);
                }
            }
        }
        return (length != s.length()) ? length : -1;
    }
    
    public synchronized b1 bx(final String s) {
        for (int i = 0; i < this.b(); ++i) {
            final b1 b1;
            if ((b1 = (b1)this.d(i)).c.equals(s)) {
                return b1;
            }
        }
        return null;
    }
}
