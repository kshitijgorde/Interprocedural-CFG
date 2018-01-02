// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;

public class n extends m
{
    int p;
    int n;
    int[] m;
    Color j;
    float o;
    Point l;
    Point k;
    
    public n(final int n, final int n2, final int n3, final int n4, final Color j, final float o, final int[] m) {
        this.j = Color.black;
        this.o = 1.0f;
        this.k = new Point(n, n2);
        this.l = new Point(n + n3, n2 + n4);
        this.j = j;
        this.o = o;
        this.m = m;
        this.p = m[0];
        this.n = m[1];
        super.if.a(this.k, this.l);
    }
    
    public n(final Point k, final Point l, final Color j, final float o, final int[] m) {
        this.j = Color.black;
        this.o = 1.0f;
        this.j = j;
        this.o = o;
        this.m = m;
        this.p = m[0];
        this.n = m[1];
        this.k = k;
        this.l = l;
        super.if.a(this.k, this.l);
    }
    
    public n(final int x, final int y, final int x2, final int y2) {
        this.j = Color.black;
        this.o = 1.0f;
        this.k = new Point(0, 0);
        this.l = new Point(0, 0);
        this.k.x = x;
        this.k.y = y;
        this.l.x = x2;
        this.l.y = y2;
        super.if.a(this.k, this.l);
    }
    
    public n(final int x, final int y, final Color j, final float o, final int[] m) {
        this.j = Color.black;
        this.o = 1.0f;
        this.j = j;
        this.o = o;
        this.m = m;
        this.p = m[0];
        this.n = m[1];
        this.k = new Point(0, 0);
        this.l = new Point(0, 0);
        this.k.x = x;
        this.k.y = y;
        super.if.a(this.k, this.l);
    }
    
    String new() {
        return "";
    }
    
    m do(final int n, final int n2) {
        if (n > this.k.x - 3 && n < this.l.x + 3 && n2 > this.k.y - 3 && n2 < this.l.y + 3) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.k.x && intValue2 < this.k.y && intValue3 > this.l.x && intValue4 > this.l.y) {
            super.a = true;
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final Point k = this.k;
        k.x += n3 - n;
        final Point l = this.l;
        l.x += n3 - n;
        final Point i = this.k;
        i.y += n4 - n2;
        final Point j = this.l;
        j.y += n4 - n2;
        super.if.a(this.k, this.l);
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.j);
        }
        else if (!super.a) {
            return;
        }
        final int n = (super.if.a() >= super.if.if()) ? super.if.a() : super.if.if();
        graphics.drawArc(super.if.if, super.if.a, n, n, this.p, this.n);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.do(graphics, this.k.x, this.k.y);
        this.do(graphics, this.l.x, this.l.y);
        this.do(graphics, this.k.x, this.l.y);
        this.do(graphics, this.l.x, this.k.y);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        return new Rectangle((this.k.x <= this.l.x) ? this.k.x : this.l.x, (this.k.y <= this.l.y) ? this.k.y : this.l.y, Math.abs(this.l.x - this.k.x), Math.abs(this.l.y - this.k.y));
    }
    
    void if(final Point k, final Point l) {
        this.k = k;
        this.l = l;
        super.if.a(this.k, this.l);
        this.a();
    }
    
    void a() {
        final Point k = this.k;
        final Point l = this.l;
        if (this.k.x <= this.l.x && this.k.y <= this.l.y) {
            return;
        }
        if (this.k.x > this.l.x && this.k.y < this.l.y) {
            final int x = l.x;
            final int y = k.y;
            final int x2 = k.x;
            final int y2 = l.y;
            this.k = new Point(x, y);
            this.l = new Point(x2, y2);
        }
        else if (this.k.x > this.l.x && this.k.y > this.l.y) {
            final int x3 = l.x;
            final int y3 = l.y;
            final int x4 = k.x;
            final int y4 = k.y;
            this.k = new Point(x3, y3);
            this.l = new Point(x4, y4);
        }
        else if (this.k.x < this.l.x && this.k.y > this.l.y) {
            final int x5 = k.x;
            final int y5 = l.y;
            final int x6 = l.x;
            final int y6 = k.y;
            this.k = new Point(x5, y5);
            this.l = new Point(x6, y6);
        }
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        final String s = "";
        final int n = (super.if.a() >= super.if.if()) ? super.if.a() : super.if.if();
        return s + "arc;" + super.if.if + ";" + super.if.a + ";" + n + ";" + n + ";" + this.j.getRGB() + ";" + (int)this.o + ";" + this.m[0] + ";" + this.m[1] + ";" + "0;";
    }
    
    public void if(final int x, final int y) {
        this.l.x = x;
        this.l.y = y;
        final int n = (int)(Math.atan((this.k.y - this.l.y) / (this.l.x - this.k.x)) * 180.0 / 3.14);
        if (this.l.x - this.k.x >= 0) {
            if (n <= 45 && n >= -45) {
                this.p = -90;
            }
            else if (n > 45) {
                this.p = 0;
            }
            else {
                this.p = 180;
            }
        }
        else if (n <= -45) {
            this.p = 0;
        }
        else if (n > 45) {
            this.p = 180;
        }
        else {
            this.p = 90;
        }
        this.m = new int[] { this.p, this.n };
        super.if.a(this.k, this.l);
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
        if (n3 == 4) {
            this.k.y = n2;
        }
        if (n3 == 5) {
            this.l.x = n;
            this.k.y = n2;
        }
        if (n3 == 6) {
            this.l.x = n;
        }
        if (n3 == 7) {
            this.l.x = n;
            this.l.y = n2;
        }
        if (n3 == 8) {
            this.l.y = n2;
        }
        if (n3 == 9) {
            this.k.x = n;
            this.l.y = n2;
        }
        if (n3 == 10) {
            this.k.x = n;
        }
        if (n3 == 11) {
            this.k.x = n;
            this.k.y = n2;
        }
        super.if.a(this.k, this.l);
        this.a();
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) == null) {
            return -1;
        }
        if (n > this.k.x && n < this.l.x && n2 > this.k.y - 3 && n2 < this.k.y + 3) {
            return 4;
        }
        if (n > this.l.x - 3 && n < this.l.x + 3 && n2 > this.k.y - 3 && n2 < this.k.y + 3) {
            return 5;
        }
        if (n > this.l.x - 3 && n < this.l.x + 3 && n2 > this.k.y && n2 < this.l.y) {
            return 6;
        }
        if (n > this.l.x - 3 && n < this.l.x + 3 && n2 > this.l.y - 3 && n2 < this.l.y + 3) {
            return 7;
        }
        if (n > this.k.x && n < this.l.x && n2 > this.l.y - 3 && n2 < this.l.y + 3) {
            return 8;
        }
        if (n > this.k.x - 3 && n < this.k.x + 3 && n2 > this.l.y - 3 && n2 < this.l.y + 3) {
            return 9;
        }
        if (n > this.k.x - 3 && n < this.k.x + 3 && n2 > this.k.y && n2 < this.l.y) {
            return 10;
        }
        if (n > this.k.x - 3 && n < this.k.x + 3 && n2 > this.k.y - 3 && n2 < this.k.y + 3) {
            return 11;
        }
        return 1;
    }
}
