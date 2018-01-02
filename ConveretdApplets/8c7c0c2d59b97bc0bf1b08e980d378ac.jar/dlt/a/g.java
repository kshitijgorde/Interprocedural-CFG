// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a;

import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Color;
import dlt.a.f.d;
import dlt.a.a.m;
import dlt.a.a.i;
import dlt.a.a.h;
import dlt.a.d.b;
import dlt.a.e.a;
import dlt.a.b.f;

public class g extends c
{
    private f g;
    
    public g() {
        this.g = new f(0.0, 0.0, 0.0);
    }
    
    public static void int(final String[] array) {
        try {
            final g g = new g();
            g.int();
            g.for();
            while (true) {
                g.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new a(new dlt.a.b.c(0.0, 0.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new b(new dlt.a.b.c(0.0, 0.0, 0.0));
        this.byte = new dlt.a.d.a(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(100.0, 10000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(50.0, Color.magenta);
        final dlt.a.g.c a2 = dlt.a.g.a.a(130.0, 15, Color.green);
        final dlt.a.c.c c = new dlt.a.c.c();
        this.case.do(c.a(a));
        this.case.do(c.a(a2));
        final Vector a3 = this.case.a();
        this.case.a(a3.elementAt(0), new dlt.a.b.b(0.0, 0.0, 500.0));
        this.case.a(a3.elementAt(1), new dlt.a.b.b(0.0, 0.0, 700.0));
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final f for1 = this.case().for();
        this.case.a(a.elementAt(0), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(1), a.elementAt(0).if(), for1);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        int n = 0;
        if (mouseEvent.getX() > this.for) {
            n = 3;
        }
        if (mouseEvent.getX() < this.for) {
            n = 357;
        }
        if (mouseEvent.getY() > this.do) {}
        if (mouseEvent.getY() < this.do) {}
        this.g.a(0.0, n, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f case() {
        return this.g;
    }
}
