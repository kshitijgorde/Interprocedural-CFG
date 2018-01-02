// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import dlt.a.a.m;
import dlt.a.a.h;
import dlt.a.b.a;
import java.awt.Color;
import java.util.Vector;
import dlt.a.c.d;
import dlt.a.g.b;
import dlt.a.b.f;

public class i extends c
{
    private f k;
    private f m;
    private int width;
    private int height;
    private b[][] l;
    
    public i() {
        this.k = new f(0.0, 0.0, 0.0);
        this.m = new f(0.0, 0.0, 0.0);
        this.width = 40;
        this.height = 40;
        this.l = new b[this.width][this.height];
    }
    
    public static void try(final String[] array) {
        try {
            final i i = new i();
            i.int();
            i.for();
            while (true) {
                i.do();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private d else() {
        final Vector<dlt.a.g.d> vector = new Vector<dlt.a.g.d>();
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                final double n = Math.random() * 50.0;
                double a = 0.0;
                double a2 = 0.0;
                if (i > 1) {
                    a = this.l[i - 1][j].a().a();
                }
                if (j > 1) {
                    a2 = this.l[i][j - 1].a().a();
                }
                final double n2 = (a + a2) / 2.0;
                if (Math.random() > 0.5) {
                    this.l[i][j] = new b(new dlt.a.b.c(i * 100, n2 + n, j * 100));
                }
                else {
                    this.l[i][j] = new b(new dlt.a.b.c(i * 100, n2 - n, j * 100));
                }
            }
        }
        for (int k = 0; k < this.width - 1; ++k) {
            for (int l = 0; l < this.height - 1; ++l) {
                final b b = this.l[k][l];
                final b b2 = this.l[k + 1][l];
                final b b3 = this.l[k + 1][l + 1];
                final b b4 = this.l[k][l + 1];
                vector.add(new dlt.a.g.d(new b[] { b, b3, b2 }, Color.green));
                vector.add(new dlt.a.g.d(new b[] { b, b4, b3 }, Color.green));
            }
        }
        final dlt.a.g.c c = new dlt.a.g.c(vector.toArray(new dlt.a.g.d[(this.width - 1) * (this.height - 1) * 2]));
        c.do();
        return new dlt.a.c.c().a(c);
    }
    
    private d goto() {
        final b b = new b(new dlt.a.b.c(0.0, 0.0, 0.0));
        final b b2 = new b(new dlt.a.b.c(0.0, 0.0, this.height * 100));
        final b b3 = new b(new dlt.a.b.c(this.width * 100, 0.0, this.height * 100));
        final dlt.a.g.c c = new dlt.a.g.c(new dlt.a.g.d[] { new dlt.a.g.d(new b[] { b, b2, b3 }, Color.blue.brighter()), new dlt.a.g.d(new b[] { b, b3, new b(new dlt.a.b.c(this.width * 100, 0.0, 0.0)) }, Color.blue.brighter()) });
        c.do();
        return new dlt.a.c.c().a(c);
    }
    
    private dlt.a.g.c long() {
        final b b = new b(new dlt.a.b.c(50.0, 80.0, 50.0));
        final b b2 = new b(new dlt.a.b.c(50.0, 80.0, -50.0));
        final b b3 = new b(new dlt.a.b.c(-50.0, 80.0, -50.0));
        final b b4 = new b(new dlt.a.b.c(-50.0, 80.0, 50.0));
        final b b5 = new b(new dlt.a.b.c(0.0, 300.0, 0.0));
        return new dlt.a.g.c(new dlt.a.g.d[] { new dlt.a.g.d(new b[] { b, b2, b5 }, Color.green), new dlt.a.g.d(new b[] { b2, b3, b5 }, Color.green), new dlt.a.g.d(new b[] { b3, b4, b5 }, Color.green), new dlt.a.g.d(new b[] { b4, b, b5 }, Color.green), new dlt.a.g.d(new b[] { b, b3, b2 }, Color.green), new dlt.a.g.d(new b[] { b, b4, b3 }, Color.green) });
    }
    
    private d void() {
        final dlt.a.g.c long1 = this.long();
        final dlt.a.g.c long2 = this.long();
        long2.a(dlt.a.b.a.a(new f(0.0, 45.0, 0.0)));
        final dlt.a.g.c a = dlt.a.g.a.a(20.0, 80.0, 15, Color.magenta);
        a.a(dlt.a.b.a.a(new dlt.a.b.b(0.0, 40.0, 0.0)));
        dlt.a.g.a.a(a, long1, dlt.a.b.a.a(new dlt.a.b.b(0.0, 0.0, 0.0)));
        dlt.a.g.a.a(a, long2, dlt.a.b.a.a(new dlt.a.b.b(0.0, 0.0, 0.0)));
        return new dlt.a.c.c().a(a);
    }
    
    public void int() {
        this.a.setBackground(Color.blue);
        this.int = new dlt.a.e.a(new dlt.a.b.c(0.0, 1000.0, 0.0), new f(0.0, 0.0, 0.0));
        this.else = new dlt.a.d.b(new dlt.a.b.c(0.0, 10000.0, -80000.0));
        this.byte = new dlt.a.d.c(this.else, 0.0);
        this.case = new dlt.a.e.b(this.byte, this.int);
        this.new = new h();
        this.char = new dlt.a.a.i(this.if(), this.new);
        this.if = new m(100.0, 100000.0);
        this.if().if(this.if);
    }
    
    public void for() {
        final d a = new dlt.a.c.c().a(dlt.a.g.a.a(5000.0, 15, Color.red));
        a.if(dlt.a.b.a.if(new dlt.a.b.c(0.0, 10000.0, -80000.0)));
        this.case.do(a);
        this.case.do(this.else());
        this.case.do(this.goto());
        for (int i = 0; i < this.width - 1; ++i) {
            for (int j = 0; j < this.width - 1; ++j) {
                if (Math.random() > 0.97) {
                    final b b = this.l[i][j];
                    if (b.a().a() > 50.0) {
                        final d void1 = this.void();
                        void1.if(dlt.a.b.a.if(b.a()));
                        this.case.do(void1);
                    }
                }
            }
        }
    }
    
    public void do() {
        final Vector a = this.case.a();
        this.case.if();
        a.elementAt(0).case();
        this.char.a(this.case, this.if);
        this.if().a();
        final f b = this.b();
        final f null = this.null();
        final f do1 = this.int.do();
        do1.a(do1.if() + b.if(), do1.a(), do1.int());
        this.int.a(do1);
        for (int i = 0; i < a.size(); ++i) {
            this.case.a(a.elementAt(i), this.int.if(), null);
        }
        final dlt.a.b.c a2 = this.else.a();
        dlt.a.b.a.a(null).a(a2);
        this.else.a(a2);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        int n = 0;
        int n2 = 0;
        if (mouseEvent.getX() < this.for) {
            n2 = 3;
        }
        if (mouseEvent.getX() > this.for) {
            n2 = 357;
        }
        if (mouseEvent.getY() < this.do) {
            n = 3;
        }
        if (mouseEvent.getY() > this.do) {
            n = 357;
        }
        this.k.a(n, 0.0, 0.0);
        this.m.a(0.0, n2, 0.0);
        this.for = mouseEvent.getX();
        this.do = mouseEvent.getY();
    }
    
    public f b() {
        final f k = this.k;
        this.k = new f(0.0, 0.0, 0.0);
        return k;
    }
    
    public f null() {
        final f m = this.m;
        this.m = new f(0.0, 0.0, 0.0);
        return m;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final dlt.a.b.c if1 = this.int.if();
        if (keyEvent.getKeyChar() == 'q') {
            if1.a(if1.for(), if1.a() + 100.0, if1.int());
        }
        if (keyEvent.getKeyChar() == 'a') {
            if1.a(if1.for(), if1.a() - 100.0, if1.int());
        }
        if (keyEvent.getKeyChar() == 's') {
            if1.a(if1.for(), if1.a(), if1.int() + 100.0);
        }
        if (keyEvent.getKeyChar() == 'x') {
            if1.a(if1.for(), if1.a(), if1.int() - 100.0);
        }
        if (keyEvent.getKeyChar() == 'z') {
            if1.a(if1.for() - 100.0, if1.a(), if1.int());
        }
        if (keyEvent.getKeyChar() == 'c') {
            if1.a(if1.for() + 100.0, if1.a(), if1.int());
        }
        this.int.a(if1);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
}
