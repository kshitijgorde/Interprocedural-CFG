// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

abstract class q extends m implements Serializable
{
    Point L;
    Point K;
    
    abstract m do(final int p0, final int p1);
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        final Point k = this.K;
        k.x += n3 - n;
        final Point l = this.L;
        l.x += n3 - n;
        final Point i = this.K;
        i.y += n4 - n2;
        final Point j = this.L;
        j.y += n4 - n2;
        super.if.a(this.K, this.L);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.do(graphics, this.K.x, this.K.y);
        this.do(graphics, this.L.x, this.L.y);
        this.do(graphics, this.K.x, this.L.y);
        this.do(graphics, this.L.x, this.K.y);
    }
    
    public Rectangle for() {
        return new Rectangle((this.K.x <= this.L.x) ? this.K.x : this.L.x, (this.K.y <= this.L.y) ? this.K.y : this.L.y, Math.abs(this.L.x - this.K.x), Math.abs(this.L.y - this.K.y));
    }
    
    public void if(final int x, final int y) {
        this.L.x = x;
        this.L.y = y;
        super.if.a(this.K, this.L);
    }
}
