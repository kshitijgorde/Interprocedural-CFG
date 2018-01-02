// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class z extends n
{
    z() {
        super("Defensive Guns", 12);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final j j = new j(a.getCenterX() + 6.0, a.getMinY(), 0.0, -12.0, 10);
        final j i = new j(a.getCenterX() - 6.0, a.getMinY(), 0.0, -12.0, 10);
        final f f = new f(a.getCenterX(), a.getMaxY(), 0.0, 12.0, 8);
        final f f2 = new f(a.getMinX(), a.getCenterY(), -12.0, 0.0, 8);
        final f f3 = new f(a.getMaxX(), a.getCenterY(), 12.0, 0.0, 8);
        vector.add(j);
        vector.add(i);
        vector.add(f);
        vector.add(f2);
        vector.add(f3);
    }
}
