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
import dlt.a.b.f;

public class a extends c
{
    private f v;
    
    public a() {
        this.v = new f(0.0, 0.0, 0.0);
    }
    
    public static void long(final String[] array) {
        try {
            final a a = new a();
            a.int();
            a.for();
            while (true) {
                a.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new dlt.a.e.a(new dlt.a.b.c(0.0, 0.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new b(new dlt.a.b.c(0.0, 0.0, 0.0));
        this.byte = new dlt.a.d.a(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(100.0, 10000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(50.0, Color.green);
        final dlt.a.c.c c = new dlt.a.c.c();
        for (int i = 0; i < 3; ++i) {
            final dlt.a.c.d a2 = c.a(a);
            this.case.do(a2);
            if (i == 2) {
                this.case.a(a2, new dlt.a.b.b(-500.0, 0.0, 1000.0));
            }
            else {
                this.case.a(a2, new dlt.a.b.b(0.0, 0.0, 1000.0));
            }
        }
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final f for1 = this.g().for();
        for (int i = 0; i < 3; ++i) {
            this.case.a(a.elementAt(i), a.elementAt(i).if(), for1);
        }
        this.case.a(a.elementAt(0), new f(1.0, 0.0, 0.0));
        this.case.a(a.elementAt(1), new f(0.0, 1.0, 0.0));
        this.case.a(a.elementAt(2), new f(0.0, 0.0, 1.0));
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
        this.v.a(n, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f g() {
        return this.v;
    }
}
