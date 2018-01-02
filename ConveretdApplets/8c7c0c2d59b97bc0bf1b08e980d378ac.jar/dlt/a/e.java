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
import java.util.Date;
import dlt.a.b.f;

public class e extends c
{
    private f void;
    private Date d;
    private int c;
    private double b;
    private double e;
    
    public e() {
        this.void = new f(0.0, 0.0, 0.0);
        this.d = new Date();
        this.c = 0;
        this.b = 0.0;
        this.e = 0.0;
    }
    
    public static void do(final String[] array) {
        try {
            final e e = new e();
            e.int();
            e.for();
            while (true) {
                e.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new a(new dlt.a.b.c(0.0, 0.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new b(new dlt.a.b.c(0.0, 0.0, 800.0));
        this.byte = new dlt.a.d.c(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(100.0, 10000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(75.0, 15, Color.magenta);
        final dlt.a.g.c a2 = dlt.a.g.a.a(50.0, 15, Color.green);
        final dlt.a.g.c a3 = dlt.a.g.a.a(25.0, 15, Color.blue);
        final dlt.a.g.c a4 = dlt.a.g.a.a(90.0, 15, Color.red);
        final dlt.a.c.c c = new dlt.a.c.c();
        this.case.do(c.a(a4));
        this.case.do(c.a(a3));
        this.case.do(c.a(a2));
        this.case.do(c.a(a3));
        this.case.do(c.a(a));
        this.case.do(c.a(a3));
        final Vector a5 = this.case.a();
        this.case.a(a5.elementAt(0), new dlt.a.b.b(0.0, 0.0, 800.0));
        this.case.a(a5.elementAt(1), new dlt.a.b.b(0.0, 0.0, 700.0));
        this.case.a(a5.elementAt(2), new dlt.a.b.b(0.0, 0.0, 600.0));
        this.case.a(a5.elementAt(3), new dlt.a.b.b(0.0, 0.0, 500.0));
        this.case.a(a5.elementAt(4), new dlt.a.b.b(0.0, 0.0, 400.0));
        this.case.a(a5.elementAt(5), new dlt.a.b.b(0.0, 0.0, 300.0));
        System.out.println("SIZE" + a.if().length);
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        a.elementAt(0).case();
        this.char.a(this.case, this.if);
        this.if().a();
        ++this.c;
        final Date d = new Date();
        this.b += d.getTime() - this.d.getTime();
        this.d = d;
        if (this.c == 10) {
            this.c = 0;
            this.e = 10.0 / (this.b / 1000.0);
            this.b = 0.0;
            System.out.println("Frames Per Second: " + this.e);
        }
        final f for1 = this.try().for();
        this.case.a(a.elementAt(1), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(2), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(2), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(3), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(4), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(4), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(5), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(5), a.elementAt(0).if(), for1);
        this.case.a(a.elementAt(5), a.elementAt(0).if(), for1);
        this.if(a.elementAt(0).if());
    }
    
    public void if(final dlt.a.b.c c) {
        final dlt.a.b.a a = dlt.a.b.a.a(dlt.a.b.a.a(dlt.a.b.a.if(new dlt.a.b.c(-c.for(), -c.a(), -c.int())), dlt.a.b.a.a(new f(1.0, 0.0, 0.0))), dlt.a.b.a.if(c));
        final dlt.a.b.c if1 = this.int.if();
        a.a(if1);
        this.int.a(if1);
        final f do1 = this.int.do();
        do1.a(do1.if() - 1.0, do1.a(), do1.int());
        this.int.a(do1);
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
        this.void.a(0.0, n, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f try() {
        return this.void;
    }
}
