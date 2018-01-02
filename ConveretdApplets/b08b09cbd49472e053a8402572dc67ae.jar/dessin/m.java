// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.io.Serializable;

abstract class m implements Serializable
{
    i if;
    boolean a;
    
    m() {
        this.a = true;
        this.if = new i();
    }
    
    abstract m do(final int p0, final int p1);
    
    abstract void a(final Vector p0);
    
    abstract void a(final int p0, final int p1, final int p2, final int p3);
    
    abstract void if(final Graphics p0, final int p1, final int p2);
    
    abstract void a(final Graphics p0, final boolean p1);
    
    protected void do(final Graphics graphics, final int n, final int n2) {
        graphics.drawRect(n - 4, n2 - 4, 8, 8);
    }
    
    abstract void a(final Graphics p0);
    
    abstract void a(final Graphics p0, final int p1, final int p2);
    
    abstract Rectangle for();
    
    abstract void a();
    
    abstract Color do();
    
    abstract String if();
    
    abstract void if(final int p0, final int p1);
    
    abstract void if(final Point p0);
    
    abstract void a(final Point p0);
    
    abstract void a(final int p0, final int p1, final int p2);
    
    int a(final int n, final int n2) {
        return this.a(n, n2);
    }
}
