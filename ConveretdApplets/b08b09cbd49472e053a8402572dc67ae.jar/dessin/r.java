// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;
import java.awt.Point;

class r extends m
{
    Point A;
    Color y;
    Point x;
    float z;
    
    r(final int n, final int n2, final int n3, final int n4, final Color y, final float z) {
        this.y = Color.black;
        this.z = 1.0f;
        this.y = y;
        this.z = z;
        this.x = new Point(n, n2);
        this.A = new Point(n3, n4);
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    r(final int n, final int n2, final int n3, final int n4) {
        this.y = Color.black;
        this.z = 1.0f;
        this.x = new Point(n, n2);
        this.A = new Point(n3, n4);
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    r(final int x, final int y, final Color y2, final float z) {
        this.y = Color.black;
        this.z = 1.0f;
        this.y = y2;
        this.z = z;
        this.x = new Point(0, 0);
        this.A = new Point(0, 0);
        this.x.x = x;
        this.x.y = y;
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    m do(final int n, final int n2) {
        final i if1;
        final i i = if1 = super.if;
        if1.if -= 3;
        final i j = i;
        j.a -= 3;
        final i k = i;
        k.for += 3;
        final i l = i;
        l.if += 3;
        if (this.x.y == this.A.y) {
            int n3;
            int n4;
            if (this.A.x >= this.x.x) {
                n3 = this.x.x;
                n4 = this.A.x;
            }
            else {
                n3 = this.A.x;
                n4 = this.x.x;
            }
            if (n < n4 + 3 && n > n3 - 3 && n2 < this.A.y + 5 && n2 > this.A.y - 5) {
                return this;
            }
            return null;
        }
        else if (this.x.x == this.A.x) {
            int n5;
            int n6;
            if (this.A.y >= this.x.y) {
                n5 = this.x.y;
                n6 = this.A.y;
            }
            else {
                n5 = this.A.y;
                n6 = this.x.y;
            }
            if (n2 < n6 + 3 && n2 > n5 - 3 && n < this.A.x + 5 && n > this.A.x - 5) {
                return this;
            }
            return null;
        }
        else {
            if (!i.a(n, n2)) {
                return null;
            }
            if (Math.abs(this.x.y - this.A.y) > Math.abs(this.x.x - this.A.x)) {
                final double n7 = (this.x.x - this.A.x) / (this.x.y - this.A.y);
                if (Math.abs(-n7 * n2 + n - (-n7 * this.x.y + this.x.x)) < 7.0) {
                    return this;
                }
            }
            else {
                final double n8 = (this.x.y - this.A.y) / (this.x.x - this.A.x);
                if (Math.abs(-n8 * n + n2 - (-n8 * this.x.x + this.x.y)) < 7.0) {
                    return this;
                }
            }
            return null;
        }
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.x.x && intValue2 < this.x.y && intValue3 > this.A.x && intValue4 > this.A.y) {
            super.a = true;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        final Point x = this.x;
        x.x += n3 - n;
        final Point a = this.A;
        a.x += n3 - n;
        final Point x2 = this.x;
        x2.y += n4 - n2;
        final Point a2 = this.A;
        a2.y += n4 - n2;
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.y);
        }
        else if (!super.a) {
            return;
        }
        graphics.drawLine(this.x.x, this.x.y, this.A.x, this.A.y);
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.do(graphics, this.x.x, this.x.y);
        this.do(graphics, this.A.x, this.A.y);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        return new Rectangle((this.x.x <= this.A.x) ? this.x.x : this.A.x, (this.x.y <= this.A.y) ? this.x.y : this.A.y, Math.abs(this.A.x - this.x.x), Math.abs(this.A.y - this.x.y));
    }
    
    void a() {
    }
    
    private void do(final Point point, final Point point2) {
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        return "" + "ligne;" + this.x.x + ";" + this.x.y + ";" + this.A.x + ";" + this.A.y + ";" + this.y.getRGB() + ";" + (int)this.z + ";" + "0;" + "0;" + "0;";
    }
    
    void if(final int x, final int y) {
        this.A.x = x;
        this.A.y = y;
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    void if(final Point point) {
        this.A.x = point.x;
        this.A.y = point.y;
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    void a(final Point point) {
        this.x.x = point.x;
        this.x.y = point.y;
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    void a(final int n, final int n2, final int n3) {
        if (n3 == 2) {
            this.x.x = n;
            this.x.y = n2;
        }
        if (n3 == 3) {
            this.A.x = n;
            this.A.y = n2;
        }
        super.if.a(this.x, this.A);
        this.do(this.x, this.A);
    }
    
    String byte() {
        return null;
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) == null) {
            return -1;
        }
        if (n > this.x.x - 3 && n < this.x.x + 3 && n2 > this.x.y - 3 && n2 < this.x.y + 3) {
            return 2;
        }
        if (n > this.A.x - 3 && n < this.A.x + 3 && n2 > this.A.y - 3 && n2 < this.A.y + 3) {
            return 3;
        }
        return 1;
    }
}
