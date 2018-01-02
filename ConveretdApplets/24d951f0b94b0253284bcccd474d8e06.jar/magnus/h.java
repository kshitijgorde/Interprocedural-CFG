// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

class h
{
    public static int new;
    public static int do;
    public static int int;
    public static int if;
    public static double case;
    public static double byte;
    private double a;
    private double try;
    private double char;
    private double for;
    
    public h(final double a, final double try1, final double char1, final double for1) {
        this.a = a;
        this.try = try1;
        this.char = char1;
        this.for = for1;
    }
    
    void a(final double a) {
        this.a = a;
    }
    
    void do(final double try1) {
        this.try = try1;
    }
    
    void for(final double char1) {
        this.char = char1;
    }
    
    void if(final double for1) {
        this.for = for1;
    }
    
    double if() {
        return this.a;
    }
    
    double do() {
        return this.try;
    }
    
    double for() {
        return this.char;
    }
    
    double a() {
        return this.for;
    }
    
    void if(final Graphics graphics) {
        if (h.int == 0) {
            this.do(graphics);
        }
        else {
            this.a(graphics);
        }
        this.a(1, graphics);
        this.a(2, graphics);
        this.a(3, graphics);
        if (Nomo.if) {
            this.a(4, graphics);
        }
        if (h.do == 1) {
            graphics.setColor(new Color(229, 188, 32));
            this.for(graphics);
        }
    }
    
    void do(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString("Boiling point (C)", 200, 335);
        graphics.drawString("Pressure (kPa)", 2, 10);
        graphics.drawString("Atmospheric pressure", 35, this.a(0.0, 100.0).y);
        for (int n = (int)((this.try - this.a) / 10.0), n2 = 193; n2 < this.try; n2 += n) {
            final int x = this.a(n2, 0.0).x;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(n2, 0.0).x, 0, this.a(n2, 0.0).x, 300);
            graphics.setColor(Color.white);
            graphics.drawString("" + (n2 - 273), x, 318);
        }
        for (int i = 0; i < 150; i += 10) {
            if (h.if == 10) {
                final int n3 = (int)(i / 10.0f);
                final int y = this.a(0.0, this.char + n3).y;
                graphics.setColor(new Color(100, 100, 100));
                graphics.drawLine(this.a(0.0, 0.0).x, y, this.a(2.0 * this.try, 0.0).x, y);
                graphics.setColor(Color.white);
                graphics.drawString("" + n3, 3, y);
            }
            else {
                final int y2 = this.a(0.0, this.char + i).y;
                graphics.setColor(new Color(100, 100, 100));
                graphics.drawLine(this.a(0.0, 0.0).x, y2, this.a(2.0 * this.try, 0.0).x, y2);
                graphics.setColor(Color.white);
                graphics.drawString("" + i, 3, y2);
            }
        }
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString("Boiling point (C)", 200, 335);
        graphics.drawString("Pressure (mmHg)", 2, 10);
        graphics.drawString("Atmospheric pressure", 35, this.a(0.0, 100.0).y);
        for (int n = (int)((this.try - this.a) / 10.0), n2 = 193; n2 < this.try; n2 += n) {
            final int x = this.a(n2, 0.0).x;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(n2, 0.0).x, 0, this.a(n2, 0.0).x, 300);
            graphics.setColor(Color.white);
            graphics.drawString("" + (n2 - 273), x, 318);
        }
        for (int i = 0; i < 150; i += 10) {
            if (h.if == 10) {
                final int n3 = (int)(i / 10.0f);
                final int y = this.a(0.0, this.char + n3).y;
                graphics.setColor(new Color(100, 100, 100));
                graphics.drawLine(this.a(0.0, 0.0).x, y, this.a(2.0 * this.try, 0.0).x, y);
                graphics.setColor(Color.white);
                graphics.drawString("" + (int)(n3 * 7.5f), 3, y);
            }
            else {
                final int y2 = this.a(0.0, this.char + i).y;
                graphics.setColor(new Color(100, 100, 100));
                graphics.drawLine(this.a(0.0, 0.0).x, y2, this.a(2.0 * this.try, 0.0).x, y2);
                graphics.setColor(Color.white);
                graphics.drawString("" + (int)(i * 7.5f), 3, y2);
            }
        }
    }
    
    void a(final int n, final Graphics graphics) {
        final double n2 = (this.try - this.a) / 100.0;
        final int[] array = new int[100];
        final int[] array2 = new int[100];
        switch (n) {
            case 1: {
                for (double a = this.a, n3 = 0.0; n3 < 100.0; ++n3, a += n2) {
                    final Point a2 = this.a(a, Nomo.if(a, 10.223719));
                    array[(int)n3] = a2.x;
                    array2[(int)n3] = a2.y;
                }
                for (int n4 = 0; n4 + 1 < 100; ++n4) {
                    if (array2[n4] <= 0) {
                        break;
                    }
                    graphics.setColor(new Color(255, 255, 0));
                    graphics.drawLine(array[n4], array2[n4], array[n4 + 1], array2[n4 + 1]);
                }
                break;
            }
            case 2: {
                for (double a3 = this.a, n5 = 0.0; n5 < 100.0; ++n5, a3 += n2) {
                    final Point a4 = this.a(a3, Nomo.if(a3, 13.12));
                    array[(int)n5] = a4.x;
                    array2[(int)n5] = a4.y;
                }
                for (int n6 = 0; n6 + 1 < 100; ++n6) {
                    if (array2[n6] <= 0) {
                        break;
                    }
                    graphics.setColor(new Color(10, 150, 230));
                    graphics.drawLine(array[n6], array2[n6], array[n6 + 1], array2[n6 + 1]);
                }
                break;
            }
            case 3: {
                for (double a5 = this.a, n7 = 0.0; n7 < 100.0; ++n7, a5 += n2) {
                    final Point a6 = this.a(a5, Nomo.if(a5, 8.8));
                    array[(int)n7] = a6.x;
                    array2[(int)n7] = a6.y;
                }
                for (int n8 = 0; n8 + 1 < 100; ++n8) {
                    if (array2[n8] <= 0) {
                        break;
                    }
                    graphics.setColor(new Color(200, 200, 200));
                    graphics.drawLine(array[n8], array2[n8], array[n8 + 1], array2[n8 + 1]);
                }
                break;
            }
            case 4: {
                for (double a7 = this.a, n9 = 0.0; n9 < 100.0; ++n9, a7 += n2) {
                    final Point a8 = this.a(a7, Nomo.if(a7, 4.4 + Math.log(Nomo.else + 273.15)));
                    array[(int)n9] = a8.x;
                    array2[(int)n9] = a8.y;
                }
                for (int n10 = 0; n10 + 1 < 100 && array2[n10] > 0; ++n10) {
                    graphics.setColor(new Color(100, 255, 100));
                    graphics.drawLine(array[n10], array2[n10], array[n10 + 1], array2[n10 + 1]);
                }
                break;
            }
        }
    }
    
    public void for(final Graphics graphics) {
        final Point a = this.a(h.case, h.byte);
        if (Nomo.if) {
            graphics.setColor(new Color(100, 255, 100));
        }
        graphics.drawLine(30, a.y, a.x, a.y);
        graphics.drawLine(a.x, 10, a.x, 300);
    }
    
    Point a(final double n, double n2) {
        if (h.new == 1) {
            n2 = 0.33333 * Math.log(n2) + 1.0;
        }
        return new Point((int)((n - this.a) * 450.0 / (this.try - this.a) + 30.0), (int)((this.for - n2) * 300.0 / (this.for - this.char)));
    }
    
    static {
        h.new = 0;
        h.do = 0;
        h.int = 0;
        h.if = 130;
        h.case = 0.0;
        h.byte = 0.0;
    }
}
