// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;

class e extends B
{
    private int a;
    
    e() {
        this.a = -10;
    }
    
    public void a(final Vector vector, final c c) {
        ++this.a;
        if (this.a > 30) {
            vector.add(m.a(c.d(), c.c(), 0.0, 8.0, 12));
            this.a = 0;
        }
    }
}
