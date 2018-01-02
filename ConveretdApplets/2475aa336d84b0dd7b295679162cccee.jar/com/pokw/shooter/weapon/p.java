// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class p extends n
{
    p() {
        super("Quad MG Rack", 14);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final j j = new j(a.getMinX(), a.getMinY(), 0.0, -12.0, 10);
        final j i = new j(a.getMaxX(), a.getMinY(), 0.0, -12.0, 10);
        final j k = new j(a.getMinX() + 15.0, a.getMinY(), 0.0, -12.0, 10);
        final j l = new j(a.getMaxX() - 15.0, a.getMinY(), 0.0, -12.0, 10);
        vector.add(j);
        vector.add(i);
        vector.add(k);
        vector.add(l);
    }
}
