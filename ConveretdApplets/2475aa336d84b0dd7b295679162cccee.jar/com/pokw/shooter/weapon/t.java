// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class t extends n
{
    t() {
        super("Browning MG", 12);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final a a2 = new a(a.getMinX() + 5.0, a.getMinY(), 0.0, -12.0, 12);
        final a a3 = new a(a.getMinX() + 20.0, a.getMinY(), 0.0, -12.0, 12);
        final a a4 = new a(a.getMaxX() - 20.0, a.getMinY(), 0.0, -12.0, 12);
        final a a5 = new a(a.getMaxX() - 5.0, a.getMinY(), 0.0, -12.0, 12);
        vector.add(a2);
        vector.add(a3);
        vector.add(a4);
        vector.add(a5);
    }
}
