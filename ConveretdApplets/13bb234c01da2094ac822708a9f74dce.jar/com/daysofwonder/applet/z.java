// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.b;
import java.awt.Color;
import com.daysofwonder.b.a;

public class z
{
    protected a a;
    protected ap b;
    protected boolean c;
    
    public z() {
    }
    
    public z(final a a, final ap b, final boolean c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void a(final int n, final float n2) {
        this.a.a(n, n2);
    }
    
    public void a() {
        this.a.a();
    }
    
    public void a(final Color color) {
        this.b.U().a(color);
    }
    
    public void b() {
        this.b.U().e();
    }
    
    public void b(final Color color) {
        this.b.U().b(color);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.b.U().c(n, n2, n3, n4);
    }
    
    public boolean a(final b b, final int n, final int n2, final ImageObserver imageObserver) {
        return this.b.U().a(b, n, n2, imageObserver);
    }
    
    public boolean a(final b b, final int n, final int n2, final float n3, final ImageObserver imageObserver) {
        return this.b.U().a(b, n, n2, imageObserver);
    }
    
    public void c() {
        if (this.c) {
            this.a.g();
        }
        this.a = null;
    }
    
    public a d() {
        return this.b.U();
    }
    
    public a e() {
        return this.a;
    }
    
    public void f() {
        if (this.b.V() != null && this.a != null) {
            this.b.a(this.b.U());
            this.a.a(this.b.V(), 0, 0, null);
        }
        if (y.a) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public void a(Rectangle a) {
        if (this.b.V() != null && this.a != null) {
            a = this.b.a(this.b.U(), a);
            this.a.a(this.b.V(), a.x, a.y, a.x + a.width, a.y + a.height, a.x, a.y, a.x + a.width, a.y + a.height, null);
        }
        if (y.a) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        if (this.b.V() != null && this.a != null) {
            this.a.a(this.b.V(), n, n2, n + n3, n2 + n4, n, n2, n + n3, n2 + n4, null);
        }
        if (y.a) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
}
