// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.InputEvent;
import com.daysofwonder.util.t;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class ak implements aH
{
    private int b;
    final /* synthetic */ E a;
    
    protected ak(final E a) {
        this.a = a;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point) {
        final E a = this.a;
        final int a2 = this.a(point);
        a.f = a2;
        this.b = a2;
        return a2 != -1;
    }
    
    private int a(final Point point) {
        for (int i = 0; i < this.a.d.size(); ++i) {
            if (((Rectangle)this.a.d.elementAt(i)).contains(point)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean a(final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3) {
        final int a = this.a(point3);
        t.a("SelectionAction: drag sub " + a);
        if (this.a.f != -1 && a != this.a.f) {
            this.a.f = -1;
        }
        else if (this.a.f == -1 && a == this.b) {
            this.a.f = this.b;
        }
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final Point point) {
        return true;
    }
    
    public void a(final MouseEvent mouseEvent) {
        if (this.a.g) {
            if (this.a.P != null) {
                this.a.N.a(this.a.Q, this.a.P, mouseEvent);
            }
            else {
                this.a.N.a(this.a.Q, this.a.F, mouseEvent);
            }
        }
    }
    
    public void a(final z z, final MouseEvent mouseEvent, final Point point, final Point point2, final Point point3, final int n) {
        if (n == 1) {
            this.a.a(z.d());
        }
    }
}
