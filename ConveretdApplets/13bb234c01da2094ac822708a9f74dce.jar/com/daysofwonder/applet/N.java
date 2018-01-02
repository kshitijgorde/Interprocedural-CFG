// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import com.daysofwonder.b.a;
import com.daysofwonder.util.UIProperties;
import java.util.Vector;
import java.awt.event.InputEvent;
import com.daysofwonder.util.t;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class N implements aH
{
    int a;
    final /* synthetic */ L b;
    
    protected N(final L b) {
        this.b = b;
        this.a = -1;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        if (this.b.a != null) {
            this.a = this.b.a.b();
        }
        final int f = this.b.f(point);
        if (f != -1 && this.b.a != null) {
            this.b.a.b(f);
        }
        return true;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        final int f = this.b.f(point3);
        if (this.b.a != null) {
            this.b.a.b(f);
        }
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        final int f = this.b.f(point);
        if (this.b.a != null) {
            this.b.a.b(f);
        }
        return true;
    }
    
    public void a(final MouseEvent mouseEvent) {
        t.a("done: " + mouseEvent);
        this.b.N.X();
        final Object q = this.b.Q;
        if (this.b.a != null && this.b.a.b() != -1) {
            this.b.N.a(this.b.d.elementAt(this.b.a.b()), this.b.P, mouseEvent);
        }
        else if (this.b.a != null && this.b.a.b() == -1 && this.a != -1) {
            this.b.N.a(this.b.d.elementAt(this.a), this.b.P, mouseEvent);
        }
        if (this.b.a != null) {
            this.b.a.c();
        }
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.b.a(z.d());
        }
    }
}
