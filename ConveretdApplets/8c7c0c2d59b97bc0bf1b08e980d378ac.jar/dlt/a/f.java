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

public class f extends c
{
    private dlt.a.b.f f;
    
    public f() {
        this.f = new dlt.a.b.f(0.0, 0.0, 0.0);
    }
    
    public static void for(final String[] array) {
        try {
            final f f = new f();
            f.int();
            f.for();
            while (true) {
                f.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new a(new dlt.a.b.c(0.0, 0.0, 0.0), new dlt.a.b.f(0.0, 0.0, 0.0));
        this.else = new b(new dlt.a.b.c(0.0, 0.0, 0.0));
        this.byte = new dlt.a.d.a(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(500.0, 1000.0);
        this.if().if(this.if);
        this.case.a(false);
    }
    
    public void for() {
        final dlt.a.g.c[][] array = new dlt.a.g.c[6][3];
        for (int i = 0; i < 6; ++i) {
            array[i][0] = dlt.a.g.a.a(100.0, Color.red);
            array[i][1] = dlt.a.g.a.a(100.0, Color.orange);
            array[i][2] = dlt.a.g.a.a(100.0, Color.green);
        }
        final dlt.a.c.c c = new dlt.a.c.c();
        for (int j = 0; j < 6; ++j) {
            for (int k = 0; k < 3; ++k) {
                this.case.do(c.a(array[j][k]));
            }
        }
        final Vector a = this.case.a();
        this.case.a(a.elementAt(0), new dlt.a.b.b(-400.0, 0.0, 1250.0));
        this.case.a(a.elementAt(1), new dlt.a.b.b(0.0, 0.0, 1000.0));
        this.case.a(a.elementAt(2), new dlt.a.b.b(400.0, 0.0, 750.0));
        this.case.a(a.elementAt(3), new dlt.a.b.b(-1000.0, 400.0, 750.0));
        this.case.a(a.elementAt(4), new dlt.a.b.b(-750.0, 0.0, 750.0));
        this.case.a(a.elementAt(5), new dlt.a.b.b(-500.0, -400.0, 750.0));
        this.case.a(a.elementAt(6), new dlt.a.b.b(1000.0, -400.0, 750.0));
        this.case.a(a.elementAt(7), new dlt.a.b.b(750.0, 0.0, 750.0));
        this.case.a(a.elementAt(8), new dlt.a.b.b(500.0, 400.0, 750.0));
        this.case.a(a.elementAt(9), new dlt.a.b.b(-400.0, -1000.0, 750.0));
        this.case.a(a.elementAt(10), new dlt.a.b.b(0.0, -750.0, 750.0));
        this.case.a(a.elementAt(11), new dlt.a.b.b(400.0, -500.0, 750.0));
        this.case.a(a.elementAt(12), new dlt.a.b.b(400.0, 1000.0, 750.0));
        this.case.a(a.elementAt(13), new dlt.a.b.b(0.0, 750.0, 750.0));
        this.case.a(a.elementAt(14), new dlt.a.b.b(-400.0, 500.0, 750.0));
        this.case.a(a.elementAt(15), new dlt.a.b.b(0.0, 0.0, 250.0));
        this.case.a(a.elementAt(16), new dlt.a.b.b(-200.0, 0.0, 500.0));
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final dlt.a.b.f for1 = this.byte().for();
        for (int i = 0; i < a.size(); ++i) {
            this.case.a(a.elementAt(i), a.elementAt(i).if(), for1);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        int n = 0;
        if (mouseEvent.getX() > this.for) {
            n = 1;
        }
        if (mouseEvent.getX() < this.for) {
            n = 359;
        }
        if (mouseEvent.getY() > this.do) {}
        if (mouseEvent.getY() < this.do) {}
        this.f.a(0.0, n, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public dlt.a.b.f byte() {
        return this.f;
    }
}
