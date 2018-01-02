// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseListener;

public class ct
{
    private String a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    g g;
    
    public ct(final g g) {
        this.a = "";
        this.b = "";
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = null;
        (this.g = g).addMouseListener(new v(this));
    }
    
    private void a(final Point point) {
        final boolean q = com.easypano.tw.g.q;
        final Rectangle bounds = this.g.getBounds();
        int n2;
        int y;
        final int n = y = (n2 = point.x);
        if (!q) {
            if (n <= 0) {
                return;
            }
            final int n3;
            y = (n3 = (n2 = point.x));
        }
        if (!q) {
            if (n >= bounds.width) {
                return;
            }
            n2 = (y = point.y);
        }
        if (!q) {
            if (y <= 0) {
                return;
            }
            n2 = point.y;
        }
        if (n2 < bounds.height) {
            this.a(true);
        }
    }
    
    public void a(final String a) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.a.equals(a)) {
                return;
            }
            this.a = a;
            ct = this;
        }
        ct.g.f();
    }
    
    public void b(final String b) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.b.equals(b)) {
                return;
            }
            this.b = b;
            ct = this;
        }
        ct.g.f();
    }
    
    public String a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public boolean c() {
        return this.g.isEnabled();
    }
    
    public void a(final boolean c) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.c == c) {
                return;
            }
            this.c = c;
            this.g.a.firePropertyChange(c("4\u0001cW~6\u0007uXh!\u001fiDo!\u0017"), this.g, new Boolean(c));
            ct = this;
        }
        ct.g.f();
    }
    
    public void b(final boolean c) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.c == c) {
                return;
            }
            this.c = c;
            ct = this;
        }
        ct.g.f();
    }
    
    public boolean d() {
        return this.c;
    }
    
    public void c(final boolean d) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.d == d) {
                return;
            }
            this.d = d;
            ct = this;
        }
        ct.g.f();
    }
    
    public boolean e() {
        return this.d;
    }
    
    public void d(final boolean e) {
        ct ct = this;
        if (!com.easypano.tw.g.q) {
            if (this.e == e) {
                return;
            }
            this.e = e;
            ct = this;
        }
        ct.g.f();
    }
    
    public boolean f() {
        return this.e;
    }
    
    public void e(final boolean f) {
        this.f = f;
    }
    
    public boolean g() {
        return this.f;
    }
    
    static boolean a(final ct ct) {
        return ct.f;
    }
    
    static void a(final ct ct, final Point point) {
        ct.a(point);
    }
    
    private static String c(final String s) {
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
                            c2 = 'd';
                            break;
                        }
                        case 1: {
                            c2 = 'S';
                            break;
                        }
                        case 2: {
                            c2 = ',';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = ';';
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
