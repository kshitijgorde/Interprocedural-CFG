// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.easypano.tw.c.c;
import java.awt.Insets;
import com.easypano.tw.c.p;
import java.awt.Image;
import com.easypano.tw.c.e;
import com.easypano.tw.c.f;

public class i extends g
{
    private g r;
    private f s;
    private com.easypano.tw.c.e t;
    private g u;
    private dg v;
    private cu w;
    private cu x;
    private boolean y;
    
    public i() {
        this(null, "");
    }
    
    public i(final Image image, final String s) {
        this.r = new g();
        this.s = new f(this);
        this.t = new com.easypano.tw.c.e(this.r);
        this.u = new g();
        this.v = null;
        this.w = new cv(this, this.r);
        this.x = new cw(this, this.u);
        this.y = false;
        this.a(this.s);
        this.r.a(this.t);
        this.t.a(new Insets(0, 0, 0, 0));
        this.t.d(image);
        this.t.f(33);
        this.r.a(this.w);
        this.u.a(new c(this.u, false));
        this.u.a(this.x);
        this.u.e().a(s);
        this.setLayout(new BorderLayout());
        this.add(this.r, b("4\u001a\u001cgO\u0005"));
        this.add(this.u, b("$\u0010\u0007gB"));
        this.r.a(new de(this));
        this.u.a(new df(this));
    }
    
    public void d(final boolean visible) {
        this.u.setVisible(visible);
    }
    
    public void a(final Color color) {
        ((c)this.u.a()).d(color);
    }
    
    public void a(final dg v) {
        this.v = v;
    }
    
    public void destroyResource() {
        this.r.destroyResource();
        this.r = null;
        this.u.destroyResource();
        this.u = null;
        this.v = null;
    }
    
    private static String b(final String s) {
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
                            c2 = 'w';
                            break;
                        }
                        case 1: {
                            c2 = '\u007f';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = '\u0013';
                            break;
                        }
                        default: {
                            c2 = '*';
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
