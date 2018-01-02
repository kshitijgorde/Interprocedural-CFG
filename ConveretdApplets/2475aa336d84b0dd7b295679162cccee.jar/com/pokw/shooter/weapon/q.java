// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class q extends n
{
    q() {
        super("Spread Cannon", 25);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final f f = new f(a.getMinX(), a.getMinY(), -5.0, -12.0, 10);
        final f f2 = new f(a.getMinX() + 5.0, a.getMinY(), -2.0, -12.0, 10);
        final f f3 = new f(a.getCenterX(), a.getMinY(), 0.0, -12.0, 10);
        final f f4 = new f(a.getMaxX() - 5.0, a.getMinY(), 2.0, -12.0, 10);
        final f f5 = new f(a.getMaxX(), a.getMinY(), 5.0, -12.0, 10);
        vector.add(f);
        vector.add(f2);
        vector.add(f3);
        vector.add(f4);
        vector.add(f5);
    }
}
