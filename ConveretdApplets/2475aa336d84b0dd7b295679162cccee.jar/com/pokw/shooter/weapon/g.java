// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class g extends n
{
    g() {
        super("Dual 22mm Cannons", 18);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final d d = new d(a.getMinX() + 8.0, a.getMinY(), 0.0, -12.0, 22);
        final d d2 = new d(a.getMaxX() - 8.0, a.getMinY(), 0.0, -12.0, 22);
        vector.add(d);
        vector.add(d2);
    }
}
