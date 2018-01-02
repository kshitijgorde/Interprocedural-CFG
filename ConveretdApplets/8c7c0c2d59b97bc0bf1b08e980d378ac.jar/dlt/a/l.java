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

public class l extends c
{
    private f q;
    private boolean s;
    private int r;
    
    public l() {
        this.q = new f(0.0, 0.0, 0.0);
        this.s = false;
        this.r = 0;
    }
    
    public static void char(final String[] array) {
        try {
            final l l = new l();
            l.int();
            l.for();
            while (true) {
                l.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new a(new dlt.a.b.c(0.0, 0.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new b(new dlt.a.b.c(500.0, 0.0, 0.0));
        this.byte = new dlt.a.d.a(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(100.0, 10000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(50.0, 15, Color.yellow);
        final dlt.a.g.c a2 = dlt.a.g.a.a(50.0, 15, Color.yellow);
        final dlt.a.g.c a3 = dlt.a.g.a.a(60.0, 15, Color.blue);
        a.a(dlt.a.b.a.a(new f(0.0, 0.0, 90.0)));
        a2.a(dlt.a.b.a.a(new f(0.0, 0.0, 90.0)));
        a3.a(dlt.a.b.a.a(new f(0.0, 0.0, 90.0)));
        a.a(dlt.a.b.a.a(new f(0.0, 17.0, 0.0)));
        a2.a(dlt.a.b.a.a(new f(0.0, 17.0, 0.0)));
        a3.a(dlt.a.b.a.a(new f(0.0, 17.0, 0.0)));
        final dlt.a.g.c a4 = dlt.a.g.a.a(20.0, Color.red);
        final dlt.a.g.c a5 = dlt.a.g.a.a(20.0, Color.red);
        final dlt.a.g.c a6 = dlt.a.g.a.a(50.0, 150.0, 15, Color.yellow);
        dlt.a.g.a.a(a6, a, new dlt.a.b.b(0.0, -75.0, 0.0).do());
        dlt.a.g.a.a(a6, a2, new dlt.a.b.b(0.0, 75.0, 0.0).do());
        dlt.a.g.a.a(a6, a3, new dlt.a.b.b(0.0, 0.0, 0.0).do());
        dlt.a.g.a.a(a6, a4, new dlt.a.b.b(50.0, 0.0, 0.0).do());
        dlt.a.g.a.a(a6, a5, new dlt.a.b.b(-50.0, 0.0, 0.0).do());
        final dlt.a.c.d a7 = new dlt.a.c.c().a(a6);
        this.case.do(a7);
        this.case.a(a7, new dlt.a.b.b(0.0, 0.0, 250.0));
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        this.case.a(a.elementAt(0), a.elementAt(0).if(), this.d().for());
        ++this.r;
        if (this.r == 10) {
            this.r = 0;
            this.s = !this.s;
            if (this.s) {
                this.byte = new dlt.a.d.a(this.else, 0.3);
            }
            else {
                this.byte = new dlt.a.d.c(this.else, 0.3);
            }
            this.case.a(this.byte);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        int n = 0;
        int n2 = 0;
        if (mouseEvent.getX() > this.for) {
            n2 = 3;
        }
        if (mouseEvent.getX() < this.for) {
            n2 = 357;
        }
        if (mouseEvent.getY() > this.do) {
            n = 3;
        }
        if (mouseEvent.getY() < this.do) {
            n = 357;
        }
        this.q.a(n, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f d() {
        return this.q;
    }
}
