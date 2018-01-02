// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;

class u extends B
{
    private boolean a;
    
    u() {
        this.a = false;
    }
    
    public void a(final Vector vector, final c c) {
        if (!this.a) {
            final int n = (int)c.l();
            if (n > 400 && n < 500) {
                vector.add(m.a(c.d(), n, 0.0, -8.0, 12));
                this.a = true;
            }
        }
    }
}
