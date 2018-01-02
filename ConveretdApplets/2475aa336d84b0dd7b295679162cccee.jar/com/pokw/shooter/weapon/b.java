// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class b extends n
{
    b() {
        super("Twin MG", 12);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final j j = new j(a.getCenterX() + 10.0, a.getMinY(), 0.0, -12.0, 10);
        final j i = new j(a.getCenterX() - 12.0, a.getMinY(), 0.0, -12.0, 10);
        vector.add(j);
        vector.add(i);
    }
}
