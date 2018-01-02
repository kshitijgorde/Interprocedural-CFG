// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

class c extends n
{
    c() {
        super("Tail Gun", 8);
    }
    
    public void a(final Vector vector, final com.pokw.shooter.planes.c c) {
        final Rectangle2D a = c.a();
        final j j = new j(a.getCenterX(), a.getMinY(), 0.0, -12.0, 10);
        final f f = new f(a.getCenterX(), a.getMaxY(), 0.0, 12.0, 8);
        vector.add(j);
        vector.add(f);
    }
}
