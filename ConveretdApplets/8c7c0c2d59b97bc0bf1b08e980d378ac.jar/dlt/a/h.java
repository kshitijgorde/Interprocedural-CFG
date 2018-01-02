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
import dlt.a.d.b;
import dlt.a.e.a;
import dlt.a.b.f;

public class h extends c
{
    private f h;
    private int i;
    private int j;
    
    public h() {
        this.h = new f(0.0, 0.0, 0.0);
        this.i = 50;
        this.j = -1;
    }
    
    public static void new(final String[] array) {
        try {
            final h h = new h();
            h.int();
            h.for();
            while (true) {
                h.do();
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
        this.new = new dlt.a.a.h();
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
            this.case.a(a2, new dlt.a.b.b(0.0, 0.0, 1000.0));
        }
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final f for1 = this.char().for();
        if (this.i > 0) {
            a.elementAt(0).if(dlt.a.b.a.if(new dlt.a.b.c(10.0, 0.0, 0.0)));
            a.elementAt(1).if(dlt.a.b.a.if(new dlt.a.b.c(0.0, 10.0, 0.0)));
            a.elementAt(2).if(dlt.a.b.a.if(new dlt.a.b.c(0.0, 0.0, 10.0)));
        }
        else {
            a.elementAt(0).if(dlt.a.b.a.if(new dlt.a.b.c(-10.0, 0.0, 0.0)));
            a.elementAt(1).if(dlt.a.b.a.if(new dlt.a.b.c(0.0, -10.0, 0.0)));
            a.elementAt(2).if(dlt.a.b.a.if(new dlt.a.b.c(0.0, 0.0, -10.0)));
        }
        if (this.i == 50) {
            this.j = -1;
        }
        if (this.i == -49) {
            this.j = 1;
        }
        this.i += this.j;
        for (int i = 0; i < 3; ++i) {
            this.case.a(a.elementAt(i), a.elementAt(i).if(), for1);
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
        this.h.a(n, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f char() {
        return this.h;
    }
}
