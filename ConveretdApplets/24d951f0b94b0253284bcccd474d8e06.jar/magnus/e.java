// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

class e
{
    private double byte;
    public static int for;
    public static int if;
    public static double try;
    public static double new;
    private double a;
    private double int;
    private double case;
    private double do;
    
    public e(final double a, final double int1, final double case1, final double do1) {
        this.byte = 273.0;
        this.a = a;
        this.int = int1;
        this.case = case1;
        this.do = do1;
    }
    
    void do(final double byte1) {
        this.byte = byte1;
    }
    
    double int() {
        return this.byte;
    }
    
    void a(final double a) {
        this.a = a;
    }
    
    void for(final double int1) {
        this.int = int1;
    }
    
    void int(final double case1) {
        this.case = case1;
    }
    
    void if(final double do1) {
        this.do = do1;
    }
    
    double if() {
        return this.a;
    }
    
    double do() {
        return this.int;
    }
    
    double for() {
        return this.case;
    }
    
    double a() {
        return this.do;
    }
    
    void if(final Graphics graphics) {
        this.a(graphics);
        graphics.setColor(Color.blue);
        this.a(1, graphics);
        if (e.if == 1) {
            graphics.setColor(new Color(229, 188, 32));
            this.do(graphics);
        }
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString("Energy difference (kJ/mole)", 100, 330);
        graphics.drawString("Ratio", 2, 10);
        for (int i = 0; i < 10; ++i) {
            final int x = this.a(this.a + i, 0.0).x;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(this.a + i, 0.0).x, 0, this.a(this.a + i, 0.0).x, 300);
            graphics.setColor(Color.white);
            graphics.drawString("" + i, x, 318);
        }
        for (int j = 0; j < 10; ++j) {
            final int y = this.a(0.0, this.case + 0.2 * j).y;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(0.0, this.case + 0.2 * j).x, y, this.a(2.0 * this.int, this.case + 0.2 * j).x, this.a(2.0 * this.int, this.case + 0.2 * j).y);
            graphics.setColor(Color.white);
            if (j < 5) {
                graphics.drawString("0." + 2 * j, 3, y);
            }
        }
    }
    
    void a(final int n, final Graphics graphics) {
        final double n2 = 0.05;
        final int[] array = new int[200];
        final int[] array2 = new int[200];
        switch (n) {
            case 1: {
                for (double a = this.a, n3 = 0.0; n3 < 200.0; ++n3, a += n2) {
                    final Point a2 = this.a(a, Math.exp(-1000.0 * a / (8.314 * this.byte)));
                    array[(int)n3] = a2.x;
                    array2[(int)n3] = a2.y;
                }
                for (int n4 = 0; n4 + 1 < 200 && array2[n4] < 1000; ++n4) {
                    graphics.setColor(new Color(255, 255, 0));
                    graphics.drawLine(array[n4], array2[n4], array[n4 + 1], array2[n4 + 1]);
                }
                break;
            }
        }
    }
    
    public void do(final Graphics graphics) {
        final Point a = this.a(e.try, e.new);
        graphics.drawLine(30, a.y, a.x, a.y);
        graphics.drawLine(a.x, 10, a.x, 300);
    }
    
    Point a(final double n, double n2) {
        if (e.for == 1) {
            n2 = 0.33333 * Math.log(n2) + 1.0;
        }
        return new Point((int)((n - this.a) * 300.0 / (this.int - this.a) + 30.0), (int)((this.do - n2) * 300.0 / (this.do - this.case)));
    }
    
    static {
        e.for = 0;
        e.if = 0;
        e.try = 0.0;
        e.new = 0.0;
    }
}
