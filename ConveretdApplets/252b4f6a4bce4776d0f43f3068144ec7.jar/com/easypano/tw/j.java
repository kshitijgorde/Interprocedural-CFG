// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.easypano.tw.d.c;
import java.awt.Insets;
import com.easypano.tw.d.p;
import java.awt.Image;
import com.easypano.tw.d.e;
import com.easypano.tw.d.f;

public class j extends h
{
    private h r;
    private com.easypano.tw.d.f s;
    private e t;
    private h u;
    private df v;
    private ct w;
    private ct x;
    private boolean y;
    
    public j() {
        this(null, "");
    }
    
    public j(final Image image, final String s) {
        this.r = new h();
        this.s = new com.easypano.tw.d.f(this);
        this.t = new e(this.r);
        this.u = new h();
        this.v = null;
        this.w = new cu(this, this.r);
        this.x = new cv(this, this.u);
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
        this.add(this.r, b("\u0011AJ\u0014\b "));
        this.add(this.u, b("\u0001KQ\u0014\u0005"));
        this.r.a(new dd(this));
        this.u.a(new de(this));
    }
    
    public void d(final boolean visible) {
        this.u.setVisible(visible);
    }
    
    public void a(final Color color) {
        ((c)this.u.a()).d(color);
    }
    
    public void a(final df v) {
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
                            c2 = 'R';
                            break;
                        }
                        case 1: {
                            c2 = '$';
                            break;
                        }
                        case 2: {
                            c2 = '$';
                            break;
                        }
                        case 3: {
                            c2 = '`';
                            break;
                        }
                        default: {
                            c2 = 'm';
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
