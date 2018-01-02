// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.InputEvent;
import com.daysofwonder.util.t;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class x implements aH
{
    final /* synthetic */ al b;
    
    protected x(final al b) {
        this.b = b;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        if (this.b.I.contains(point)) {
            if (this.b.J) {
                this.b.a = true;
            }
            t.a("begin : " + this.b.l() + " press:" + this.b.a);
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        if (this.b.J) {
            t.a("drag: " + mouseEvent + " f " + point3);
            if (this.b.I.contains(point)) {
                if (!this.b.I.contains(point3)) {
                    t.a("drag Pressed = false: " + point3);
                    this.b.a = false;
                }
                else {
                    t.a("drag Pressed = true: " + point3);
                    this.b.a = true;
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        t.a("end: " + point);
        if (this.b.I.contains(point)) {
            if (this.b.J) {
                this.b.a = false;
            }
            t.a("end OK: " + point);
            return true;
        }
        return false;
    }
    
    public void a(final MouseEvent mouseEvent) {
        if (this.b.J) {
            t.a("done: " + mouseEvent);
            this.b.N.a(this.b.Q, this.b.P, mouseEvent);
        }
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1 && z != null) {
            this.b.a(z.d());
        }
    }
}
