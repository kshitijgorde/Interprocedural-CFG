// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;
import java.util.Random;

class x extends n
{
    private static final Random c;
    
    x() {
        super("Gatling Gun", 4);
    }
    
    public void a(final Vector vector, final c c) {
        vector.add(new f(c.a().getCenterX(), c.a().getCenterY(), x.c.nextDouble() * 5.0 - 2.5, -12.0, 6));
    }
    
    static {
        c = new Random();
    }
}
