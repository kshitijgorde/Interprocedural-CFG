// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.InputEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;

class t extends x
{
    private boolean c;
    final /* synthetic */ r a;
    
    private t(final r a) {
        this.a = a;
        super(a);
        this.c = this.a.a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        if (this.a.I.contains(point)) {
            if (this.a.J) {
                this.a.a = !this.a.a;
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        if (this.a.J) {
            if (this.a.I.contains(point)) {
                if (!this.a.I.contains(point3)) {
                    this.a.a = this.c;
                }
                else {
                    this.a.a = !this.c;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        return this.a.I.contains(point);
    }
    
    public void a(final MouseEvent mouseEvent) {
        if (this.a.J) {
            this.a.N.a(this.a.Q, this.a.P, mouseEvent);
        }
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.a.a(z.d());
        }
    }
}
