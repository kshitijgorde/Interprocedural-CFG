// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import com.daysofwonder.util.y;
import java.awt.Point;
import java.awt.Dimension;
import com.daysofwonder.b.a;
import com.daysofwonder.util.K;
import java.net.URL;

class as extends B
{
    public URL a;
    public K b;
    public K c;
    
    public as(final K k, final URL a) {
        this.a = a;
        this.b = k;
        this.c = k;
    }
    
    public void a(final a a, final K k, final Dimension dimension, final Point point) {
        final K b = new K();
        final y e = this.c.e();
        while (e.a()) {
            ((B)e.b()).a(a, b, dimension, point);
        }
        this.b = b;
        k.b(this);
    }
    
    public void a(final a a, final Point point, Color color) {
        final y e = this.b.e();
        while (e.a()) {
            final B b = (B)e.b();
            if (this.d) {
                color = Color.red;
            }
            else {
                color = Color.blue;
            }
            b.a(a, point, color);
            if (!(b instanceof ay) && b instanceof d) {
                final int a2 = ((d)b).a();
                final Color c = a.c();
                a.a(this.d ? Color.red : Color.blue);
                a.e(b.e.x, a2, b.e.x + b.e.width, a2);
                a.a(c);
            }
        }
    }
}
