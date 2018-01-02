// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;

public class a
{
    Point do;
    Point a;
    i if;
    
    public a(final int x, final int y) {
        this.if = new i();
        this.a = new Point(0, 0);
        this.do = new Point(0, 0);
        this.a.x = x;
        this.a.y = y;
        this.if.a(this.a, this.do);
    }
    
    void a(final Graphics graphics) {
        graphics.drawRect(this.if.if, this.if.a, this.if.a(), this.if.if());
    }
    
    Vector a() {
        final Vector<Integer> vector = new Vector<Integer>(4);
        vector.addElement(new Integer(this.a.x));
        vector.addElement(new Integer(this.a.y));
        vector.addElement(new Integer(this.do.x));
        vector.addElement(new Integer(this.do.y));
        return vector;
    }
    
    void a(final int x, final int y) {
        this.do.x = x;
        this.do.y = y;
        this.if.a(this.a, this.do);
    }
}
