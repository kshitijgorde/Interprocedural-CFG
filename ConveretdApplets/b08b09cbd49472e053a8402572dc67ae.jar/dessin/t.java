// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;

public class t extends m
{
    Color B;
    float E;
    Point D;
    Point C;
    
    public t(final Point c, final Point d, final Color color, final float n) {
        this.B = Color.blue;
        this.E = 1.0f;
        this.C = c;
        this.D = d;
    }
    
    public t(final int n, final int n2, final int n3, final int n4) {
        this.B = Color.blue;
        this.E = 1.0f;
        this.C = new Point(n, n2);
        this.D = new Point(n3, n4);
    }
    
    public t(final int n, final int n2, final int n3, final int n4, final Color b, final float e) {
        this.B = Color.blue;
        this.E = 1.0f;
        this.B = b;
        this.E = e;
        this.C = new Point(n, n2);
        this.D = new Point(n3, n4);
    }
    
    m do(final int n, final int n2) {
        if (n > this.C.x - 1 && n < this.C.x + 2 && n2 > this.C.y - 1 && n2 < this.C.y + 2) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.C.x && intValue2 < this.C.y && intValue3 > this.D.x && intValue4 > this.D.y) {
            super.a = true;
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final Point c = this.C;
        c.x += n3 - n;
        final Point c2 = this.C;
        c2.y += n4 - n2;
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.B);
        }
        else if (!super.a) {
            return;
        }
        graphics.drawLine(this.C.x, this.C.y, this.D.x, this.D.y);
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.do(graphics, this.C.x + 1, this.C.y + 1);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        return new Rectangle(this.C.x, this.C.y, 1, 1);
    }
    
    void a() {
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        return "" + "ligne;" + this.C.x + ";" + this.C.y + ";" + this.D.x + ";" + this.D.y + ";" + this.B.getRGB() + ";" + (int)this.E + ";" + "0;" + "0;" + "0;";
    }
    
    public void if(final int n, final int n2) {
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) != null) {
            return 1;
        }
        return -1;
    }
}
