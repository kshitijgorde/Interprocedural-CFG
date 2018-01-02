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

public class j extends c
{
    private f n;
    
    public j() {
        this.n = new f(0.0, 0.0, 0.0);
    }
    
    public static void byte(final String[] array) {
        try {
            final j j = new j();
            j.int();
            j.for();
            while (true) {
                j.do();
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
        this.case.a(false);
    }
    
    public void for() {
        final dlt.a.g.c[] array = new dlt.a.g.c[36];
        final dlt.a.c.c c = new dlt.a.c.c();
        for (int i = 0; i < 36; ++i) {
            array[i] = dlt.a.g.a.a(20.0, 60.0, 15, Color.red);
            this.case.do(c.a(array[i]));
        }
        final Vector a = this.case.a();
        for (int j = 0; j < 36; ++j) {
            final dlt.a.c.d d = a.elementAt(j);
            this.case.a(d, new f(j * 10, 0.0, 0.0));
            this.case.a(d, new dlt.a.b.b(0.0, 0.0, 150.0));
            this.case.a(a.elementAt(j), new dlt.a.b.c(0.0, 0.0, 500.0), new f(0.0, j * 10, 0.0));
        }
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        this.char.a(this.case, this.if);
        this.if().a();
        final f for1 = this.c().for();
        for (int i = 0; i < a.size(); ++i) {
            this.case.a(a.elementAt(i), new dlt.a.b.c(0.0, 0.0, 500.0), for1);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        int n = 0;
        int n2 = 0;
        if (mouseEvent.getX() > this.for) {
            n2 = 1;
        }
        if (mouseEvent.getX() < this.for) {
            n2 = 359;
        }
        if (mouseEvent.getY() > this.do) {
            n = 1;
        }
        if (mouseEvent.getY() < this.do) {
            n = 359;
        }
        this.n.a(n, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f c() {
        return this.n;
    }
}
