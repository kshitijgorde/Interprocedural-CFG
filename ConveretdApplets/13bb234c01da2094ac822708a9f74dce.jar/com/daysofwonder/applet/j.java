// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.InputEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class j implements aH
{
    final /* synthetic */ Q a;
    
    protected j(final Q a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        if (this.a.I.contains(point)) {
            if (this.a.J) {
                this.a.a = true;
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        if (this.a.J && this.a.I.contains(point)) {
            if (!this.a.I.contains(point3)) {
                this.a.a = false;
            }
            else {
                this.a.a = true;
            }
            return true;
        }
        return false;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        if (this.a.I.contains(point)) {
            if (this.a.J) {
                this.a.a = false;
            }
            return true;
        }
        return false;
    }
    
    public void a(final MouseEvent mouseEvent) {
        this.a.N.a(this.a.Q, this.a.P, mouseEvent);
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.a.a(z.d());
        }
    }
}
