// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a;

import java.awt.event.MouseEvent;
import java.awt.Color;
import dlt.a.f.d;
import dlt.a.a.m;
import dlt.a.a.i;
import dlt.a.a.h;
import dlt.a.d.b;
import dlt.a.e.a;
import dlt.a.b.f;

public class k extends c
{
    private int o;
    private int p;
    
    public k() {
        this.o = 0;
        this.p = 1;
    }
    
    public static void case(final String[] array) {
        try {
            final k k = new k();
            k.int();
            k.for();
            while (true) {
                k.do();
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
        this.if = new m(100.0, 900.0);
        this.if().if(this.if);
        this.case.a(false);
    }
    
    public void for() {
        final dlt.a.g.c a = dlt.a.g.a.a(75.0, 15, Color.yellow);
        final dlt.a.g.c a2 = dlt.a.g.a.a(112.0, 15, Color.yellow);
        final dlt.a.g.c a3 = dlt.a.g.a.a(150.0, 15, Color.blue);
        final dlt.a.c.c c = new dlt.a.c.c();
        final dlt.a.c.d a4 = c.a(a);
        final dlt.a.c.d a5 = c.a(a2);
        final dlt.a.c.d a6 = c.a(a3);
        this.case.do(a4);
        this.case.do(a5);
        this.case.do(a6);
        this.case.a(a4, new dlt.a.b.b(-300.0, 0.0, 500.0));
        this.case.a(a5, new dlt.a.b.b(0.0, 0.0, 500.0));
        this.case.a(a6, new dlt.a.b.b(300.0, 0.0, 500.0));
    }
    
    public void do() {
        this.if.a(300 + this.o * 10);
        this.if.if(200 + this.o * 10);
        this.o += this.p;
        if (this.o == 50 || this.o == 0) {
            if (this.p == 1) {
                this.p = -1;
            }
            else {
                this.p = 1;
            }
        }
        this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
}
