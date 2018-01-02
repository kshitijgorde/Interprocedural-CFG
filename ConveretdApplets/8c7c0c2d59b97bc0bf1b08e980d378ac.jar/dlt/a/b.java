// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a;

import java.awt.event.MouseEvent;
import dlt.a.b.e;
import java.util.Vector;
import java.awt.Color;
import dlt.a.f.d;
import dlt.a.a.m;
import dlt.a.a.i;
import dlt.a.a.h;
import dlt.a.e.a;
import dlt.a.b.f;

public class b extends c
{
    private int long;
    private int goto;
    
    public b() {
        this.long = 0;
        this.goto = 0;
    }
    
    public static void a(final String[] array) {
        try {
            final b b = new b();
            b.int();
            b.for();
            while (true) {
                b.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void int() {
        this.int = new a(new dlt.a.b.c(0.0, 0.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new dlt.a.d.b(new dlt.a.b.c(0.0, 0.0, 0.0));
        this.byte = new dlt.a.d.a(this.else, 0.3);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new i(this.if(), this.new);
        this.if = new m(100.0, 10000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(50.0, Color.green);
        final dlt.a.g.c a2 = dlt.a.g.a.a(50.0, Color.red);
        final dlt.a.g.c a3 = dlt.a.g.a.a(50.0, Color.yellow);
        final dlt.a.c.c c = new dlt.a.c.c();
        this.case.do(c.a(a));
        this.case.do(c.a(a2));
        this.case.do(c.a(a3));
        final Vector a4 = this.case.a();
        this.case.a(a4.elementAt(0), new dlt.a.b.b(0.0, 0.0, 1000.0));
        this.case.a(a4.elementAt(1), new dlt.a.b.b(0.0, 0.0, 1000.0));
        this.case.a(a4.elementAt(2), new dlt.a.b.b(0.0, 0.0, 1000.0));
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final dlt.a.b.a do1 = new dlt.a.b.b(0.0, 0.0, -1000.0).do();
        final dlt.a.b.a do2 = new dlt.a.b.b(0.0, 0.0, 1000.0).do();
        if (this.long > 0) {
            a.elementAt(0).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(1.1, 1.0, 1.0))), do2));
            a.elementAt(1).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(1.0, 1.1, 1.0))), do2));
            a.elementAt(2).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(1.0, 1.0, 1.1))), do2));
        }
        else {
            a.elementAt(0).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(0.90909, 1.0, 1.0))), do2));
            a.elementAt(1).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(1.0, 0.90909, 1.0))), do2));
            a.elementAt(2).if(dlt.a.b.a.a(dlt.a.b.a.a(do1, dlt.a.b.a.a(new e(1.0, 1.0, 0.90909))), do2));
        }
        if (this.long == 25) {
            this.long = -25;
        }
        ++this.long;
        this.a(a.elementAt(0).if());
    }
    
    public void a(final dlt.a.b.c c) {
        this.goto += 2;
        if (this.goto > 1080) {
            this.goto = 0;
        }
        if (this.goto == 0 || this.goto == 360 || this.goto == 720) {
            this.int.a(new dlt.a.b.c(0.0, 0.0, 0.0));
            this.int.a(new f(0.0, 0.0, 0.0));
        }
        f f = null;
        if (this.goto >= 0) {
            f = new f(2.0, 0.0, 0.0);
        }
        if (this.goto >= 360) {
            f = new f(0.0, 2.0, 0.0);
        }
        if (this.goto >= 720) {
            f = new f(0.0, 0.0, 2.0);
        }
        final dlt.a.b.c if1 = this.int.if();
        dlt.a.b.a.if(new dlt.a.b.c(-c.for(), -c.a(), -c.int())).a(if1);
        dlt.a.b.a.a(f).a(if1);
        dlt.a.b.a.if(c).a(if1);
        this.int.a(if1);
        final f do1 = this.int.do();
        do1.a(do1.if() - f.if(), do1.a() - f.a(), do1.int() - f.int());
        this.int.a(do1);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
}
