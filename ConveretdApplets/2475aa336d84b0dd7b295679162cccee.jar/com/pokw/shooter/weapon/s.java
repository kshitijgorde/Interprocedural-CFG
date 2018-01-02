// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class s extends n
{
    s() {
        super("Single MG", 10);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        vector.add(new j(a.getCenterX() - 2.0, a.getMinY(), 0.0, -12.0, 10));
    }
}
