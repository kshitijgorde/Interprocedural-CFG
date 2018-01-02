// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;

class l extends B
{
    private boolean a;
    
    l() {
        this.a = false;
    }
    
    public void a(final Vector vector, final c c) {
        if (!this.a) {
            final int n = (int)c.c();
            if (n > 40 && n < 120) {
                vector.add(m.a(c.d(), n, 0.0, 8.0, 12));
                this.a = true;
            }
        }
    }
}
