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

public class n extends c
{
    private f u;
    
    public n() {
        this.u = new f(0.0, 0.0, 0.0);
    }
    
    public static void goto(final String[] array) {
        try {
            final n n = new n();
            n.int();
            n.for();
            while (true) {
                n.do();
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
        final dlt.a.g.c a = dlt.a.g.a.a(130.0, 15, Color.green);
        final dlt.a.g.c a2 = dlt.a.g.a.a(30.0, 15, Color.white);
        final dlt.a.c.c c = new dlt.a.c.c();
        this.case.do(c.a(a));
        this.case.do(c.a(a2));
        this.case.a(this.case.a().elementAt(0), new dlt.a.b.b(0.0, 0.0, 500.0));
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        a.elementAt(1).case();
        this.char.a(this.case, this.if);
        this.if().a();
        final f for1 = this.f().for();
        final dlt.a.b.c a2 = this.else.a();
        final dlt.a.b.c if1 = a.elementAt(0).if();
        final dlt.a.b.a a3 = dlt.a.b.a.a(dlt.a.b.a.a(dlt.a.b.a.if(new dlt.a.b.c(-if1.for(), -if1.a(), -if1.int())), for1.do()), if1.do());
        a3.a(a2);
        this.else.a(a2);
        a.elementAt(1).if(a3);
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
        this.u.a(n, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f f() {
        return this.u;
    }
}
