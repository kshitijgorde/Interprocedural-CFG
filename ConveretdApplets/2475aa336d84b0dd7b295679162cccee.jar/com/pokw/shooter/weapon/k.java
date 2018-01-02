// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import java.awt.geom.Rectangle2D;
import com.pokw.shooter.planes.c;
import java.util.Vector;

class k extends n
{
    private int c;
    
    k() {
        super("Twin MG w/Missiles", 15);
        this.c = 0;
    }
    
    public void a(final Vector vector, final c c) {
        final Rectangle2D a = c.a();
        final j j = new j(a.getCenterX() + 10.0, a.getMinY(), 0.0, -12.0, 10);
        final j i = new j(a.getCenterX() - 14.0, a.getMinY(), 0.0, -12.0, 10);
        vector.add(j);
        vector.add(i);
        ++this.c;
        if (this.c >= 3) {
            final y y = new y(a.getMinX(), a.getMinY(), -1.0, 3.0, 40);
            final y y2 = new y(a.getMaxX(), a.getMinY(), 1.0, 3.0, 40);
            vector.add(y);
            vector.add(y2);
            this.c = 0;
        }
    }
}
