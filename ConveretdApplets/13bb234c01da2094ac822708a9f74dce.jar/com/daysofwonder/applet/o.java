// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.b.a;
import java.awt.Font;
import java.awt.Color;

class o implements Cloneable
{
    public int a;
    public int b;
    public String c;
    public Color d;
    public Color e;
    public Color f;
    public Font g;
    
    public Object clone() {
        return super.clone();
    }
    
    public void a(final int n) {
        this.a |= n;
    }
    
    public void b(final int n) {
        this.a &= ~n;
    }
    
    public void a(final a a) {
        this.g = a.b();
        int style = this.g.getStyle();
        if ((this.a & 0x1) != 0x0) {
            ++style;
        }
        if ((this.a & 0x2) != 0x0) {
            style += 2;
        }
        if (this.a == 0) {
            style = 0;
        }
        a.a(new Font((this.c == null) ? this.g.getName() : this.c, style, this.g.getSize() + this.b));
        if (this.d != null && this.f == null) {
            this.e = a.c();
            a.a(this.d);
        }
        else if (this.f != null) {
            this.e = a.c();
            a.a(this.f);
        }
    }
    
    public void b(final a a) {
        a.a(this.g);
        if (this.e != null) {
            a.a(this.e);
        }
    }
}
