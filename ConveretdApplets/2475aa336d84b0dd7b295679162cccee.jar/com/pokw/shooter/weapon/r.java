// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;
import java.util.Random;

class r extends n
{
    private static final Random c;
    
    r() {
        super("Dual Gatling Gun", 4);
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final f f = new f(a.getMinX() + 5.0, a.getMinY(), r.c.nextDouble() * 5.0 - 2.5, -12.0, 6);
        final f f2 = new f(a.getMaxX() - 5.0, a.getMinY(), r.c.nextDouble() * 5.0 - 2.5, -12.0, 6);
        vector.add(f);
        vector.add(f2);
    }
    
    static {
        c = new Random();
    }
}
