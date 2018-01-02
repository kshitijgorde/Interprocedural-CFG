// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.util.Stack;
import dlt.mandelbrot.a.j;

public class d
{
    private int do;
    private double a;
    private double case;
    private double for;
    private j try;
    private int[][] byte;
    private static Stack int;
    private boolean char;
    private Vector new;
    private Object if;
    
    public void if(final c c) {
        if (!this.new.contains(c)) {
            this.new.addElement(c);
        }
    }
    
    public void a(final c c) {
        if (this.new.contains(c)) {
            this.new.removeElement(c);
        }
    }
    
    public void else() {
        for (int i = 0; i < this.new.size(); ++i) {
            ((c)this.new.elementAt(i)).plotted(this.byte);
        }
    }
    
    public d() {
        this.char = false;
        this.new = new Vector();
        this.if = new Object();
        this.char();
    }
    
    public void a() {
        d d = null;
        while (dlt.mandelbrot.d.int.size() > 0) {
            d = dlt.mandelbrot.d.int.pop();
        }
        if (d != null) {
            this.do = d.do;
            this.a = d.a;
            this.case = d.case;
            this.for = d.for;
            this.try = d.try;
        }
    }
    
    public boolean case() {
        if (d.int.size() > 0) {
            final d d = dlt.mandelbrot.d.int.pop();
            this.do = d.do;
            this.a = d.a;
            this.case = d.case;
            this.for = d.for;
            this.try = d.try;
            return true;
        }
        return false;
    }
    
    public j if() {
        return this.try;
    }
    
    public double new() {
        return this.try.if() * 8;
    }
    
    public int int() {
        return this.do;
    }
    
    public double for() {
        return this.a;
    }
    
    public double goto() {
        return this.for;
    }
    
    public double do() {
        return this.case;
    }
    
    private void char() {
        this.do = 1;
        this.a = -2.2;
        this.case = -1.5;
        this.for = 3.0;
        this.try = g.do();
        this.byte = new int[1][1];
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.try();
        final d d = new d();
        d.do = this.do;
        d.a = this.a;
        d.for = this.for;
        d.case = this.case;
        d.try = this.try;
        dlt.mandelbrot.d.int.push(d);
        final double n5 = this.for / n * n3;
        final double n6 = this.for / n2 * n4;
        final double n7 = this.for / 4.0;
        this.a = this.a + n5 - n7;
        this.case = this.case + n6 - n7;
        this.for /= 2.0;
    }
    
    public void a(final j try1) {
        this.try();
        this.try = try1;
    }
    
    public void a(int do1) {
        this.try();
        if (do1 < 1) {
            do1 = 1;
        }
        if (do1 > 15) {
            do1 = 15;
        }
        this.do = do1;
    }
    
    public void if(final int n) {
        synchronized (this.if) {
            this.char = true;
            int n2 = n / this.do;
            final double n3 = this.for / n2;
            if (n2 * this.do < n) {
                ++n2;
            }
            final double new1 = this.new();
            if (this.byte.length != n2) {
                this.byte = new int[n2][n2];
            }
            double case1 = this.case;
            for (int n4 = 0; n4 < n2 && this.char; ++n4) {
                double a = this.a;
                for (int n5 = 0; n5 < n2 && this.char; ++n5) {
                    this.byte[n5][n4] = this.a(a, case1, new1);
                    a += n3;
                }
                case1 += n3;
            }
            this.char = false;
        }
        this.else();
    }
    
    private int a(final double n, final double n2, final double n3) {
        int n4 = 1;
        double n5 = n;
        double n6 = n2;
        while (this.char) {
            final double n7 = n5 * n5 - n6 * n6 + n;
            n6 = 2.0 * n5 * n6 + n2;
            n5 = n7;
            if (n5 * n5 + n6 * n6 > 4.0) {
                return n4;
            }
            if (n4 > n3) {
                return 0;
            }
            ++n4;
        }
        return n4;
    }
    
    public void try() {
        this.char = false;
    }
    
    public void long() {
        synchronized (this.if) {
        }
        // monitorexit(this.if)
    }
    
    public boolean byte() {
        return this.char;
    }
    
    public BufferedImage do(final int n) {
        final BufferedImage bufferedImage;
        synchronized (this.if) {
            this.char = true;
            bufferedImage = new BufferedImage(n, n, 1);
            final Graphics graphics = bufferedImage.getGraphics();
            final int length = this.byte.length;
            int n2 = n / length;
            if (n2 * length < n) {
                ++n2;
            }
            final Color[] a = this.try.a();
            double n3 = 0.0;
            for (int i = 0; i < length; ++i) {
                double n4 = 0.0;
                for (int j = 0; j < length; ++j) {
                    graphics.setColor(a[this.try.if(this.byte[j][i])]);
                    graphics.fillRect((int)n4, (int)n3, n2, n2);
                    n4 += n2;
                }
                n3 += n2;
            }
            this.char = false;
        }
        return bufferedImage;
    }
    
    static {
        d.int = new Stack();
    }
}
