// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Point;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Graphics;

class a extends Eyring
{
    private double r;
    private double z;
    public static int A;
    public static int p;
    public static double v;
    public static double t;
    public static double w;
    public static double C;
    public static double u;
    private double o;
    private double s;
    private double B;
    private double q;
    
    public a(final double o, final double s, final double b, final double q) {
        this.r = 0.0;
        this.z = 0.0;
        this.o = o;
        this.s = s;
        this.B = b;
        this.q = q;
    }
    
    void int(final double r) {
        this.r = r;
    }
    
    void try(final double z) {
        this.z = z;
    }
    
    double for() {
        return this.r;
    }
    
    double int() {
        return this.z;
    }
    
    void if(final double o) {
        this.o = o;
    }
    
    void for(final double s) {
        this.s = s;
    }
    
    void new(final double b) {
        this.B = b;
    }
    
    void do(final double q) {
        this.q = q;
    }
    
    double if() {
        return this.o;
    }
    
    double do() {
        return this.s;
    }
    
    double new() {
        return this.B;
    }
    
    double a() {
        return this.q;
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.blue);
        this.a(1, graphics);
        if (a.p == 1) {
            graphics.setColor(new Color(229, 188, 32));
            this.if(graphics);
        }
    }
    
    void a(final int n, final Graphics graphics) {
        final int n2 = 620;
        final int[] array = new int[n2];
        final DecimalFormat decimalFormat = new DecimalFormat("##0.##");
        double n3;
        double n4;
        if (a.A == 0) {
            n3 = Eyring.case;
            n4 = Eyring.h;
            Eyring.null = Eyring.a(Eyring.n, Eyring.case);
            Eyring.char = Eyring.a(Eyring.n, Eyring.h);
            for (int i = 0; i < n2; ++i) {
                array[i] = (int)((Eyring.char - Eyring.a(Eyring.n, i / 620.0 * (Eyring.case - Eyring.h) + Eyring.h)) / (Eyring.char - Eyring.null) * 300.0);
            }
        }
        else {
            n3 = Eyring.int;
            n4 = Eyring.a;
            Eyring.null = Eyring.a(Eyring.a, Eyring.l);
            Eyring.char = Eyring.a(Eyring.int, Eyring.l);
            for (int j = 0; j < n2; ++j) {
                array[j] = (int)((Eyring.char - Eyring.a(j / 620.0 * (Eyring.int - Eyring.a) + Eyring.a, Eyring.l)) / (Eyring.char - Eyring.null) * 300.0);
            }
        }
        final DecimalFormat decimalFormat2 = new DecimalFormat("###0.##");
        final double n5 = (n3 - n4) / 10.0;
        final int n6 = (int)Math.round(Math.log(Eyring.char) / Math.log(10.0));
        final double n7 = (Eyring.char / Math.pow(10.0, n6) - Eyring.null / Math.pow(10.0, n6)) / 10.0;
        final double n8 = Eyring.null / Math.pow(10.0, n6);
        graphics.setColor(new Color(255, 255, 255));
        if (a.A == 0) {
            graphics.drawString("Reaction Energy (kJ/mole)", 410, 330);
        }
        else {
            graphics.drawString("Temperature (Kelvin)", 410, 330);
        }
        graphics.drawString("Rate/10^" + String.valueOf(n6) + "(1/s)", 2, 10);
        for (int k = 0; k < 10; ++k) {
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(30 + k * 62, 0, 30 + k * 62, 300);
            graphics.setColor(Color.white);
            graphics.drawString("" + decimalFormat2.format(n4 + k * n5), k * 62 + 20, 318);
        }
        for (int l = 0; l < 10; ++l) {
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(30, 300 - l * 30, 650, 300 - l * 30);
            graphics.setColor(Color.white);
            graphics.drawString("" + decimalFormat2.format(n8 + l * n7), 3, 303 - l * 30);
        }
        for (int n9 = 0; n9 + 1 < n2; ++n9) {
            if (array[n9] < 300) {
                graphics.setColor(new Color(255, 100, 0));
                graphics.drawLine(n9 + 30, array[n9], n9 + 31, array[n9 + 1]);
            }
        }
    }
    
    public void if(final Graphics graphics) {
        final int n = (int)a.v;
        final int n2 = (int)a.t;
        if (n2 < 301 && n > 30) {
            graphics.drawLine(30, n2, n - 6, n2);
            graphics.drawLine(n, n2 + 6, n, 300);
            graphics.setColor(Color.green);
            graphics.drawLine(n - 6, n2, n - 3, n2);
            graphics.drawLine(n + 3, n2, n + 6, n2);
            graphics.drawLine(n, n2 - 6, n, n2 - 3);
            graphics.drawLine(n, n2 + 3, n, n2 + 6);
        }
    }
    
    Point for(final double n, final double n2) {
        return new Point((int)((n - this.o) * 300.0 / (this.s - this.o) + 30.0), (int)((this.q - n2) * 300.0 / (this.q - this.B)));
    }
    
    static {
        a.A = 0;
        a.p = 0;
        a.v = 0.0;
        a.t = 0.0;
        a.w = 1.0E13;
        a.C = 1.0E11;
        a.u = 0.0;
    }
}
