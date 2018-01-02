// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;

public class l extends m
{
    Color f;
    float i;
    Point h;
    Point g;
    
    public l(final Point g, final Point h, final Color color, final float n) {
        this.f = Color.blue;
        this.i = 1.0f;
        this.g = g;
        this.h = h;
    }
    
    public l(final int n, final int n2, final int n3, final int n4) {
        this.f = Color.blue;
        this.i = 1.0f;
        this.g = new Point(n, n2);
        this.h = new Point(n3, n4);
    }
    
    public l(final int n, final int n2, final Color color, final float n3) {
        this.f = Color.blue;
        this.i = 1.0f;
        this.g = new Point(n, n2);
        this.h = new Point(n, n2);
    }
    
    m do(final int n, final int n2) {
        if (n > this.g.x - 3 && n < this.g.x + 6 && n2 > this.g.y - 3 && n2 < this.g.y + 6) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < this.g.x && intValue2 < this.g.y && intValue3 > this.h.x && intValue4 > this.h.y) {
            super.a = true;
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final Point g = this.g;
        g.x += n3 - n;
        final Point g2 = this.g;
        g2.y += n4 - n2;
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.f);
        }
        else if (!super.a) {
            return;
        }
        graphics.fillRect(this.g.x, this.g.y, 6, 6);
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.do(graphics, this.g.x + 3, this.g.y + 3);
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    public Rectangle for() {
        return new Rectangle(this.g.x, this.g.y, 6, 6);
    }
    
    void a() {
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        return "" + "pin;" + this.g.x + ";" + this.g.y + ";" + "6;" + "6;" + this.f.getRGB() + ";" + (int)this.i + ";" + "0;" + "0;" + "0;";
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
