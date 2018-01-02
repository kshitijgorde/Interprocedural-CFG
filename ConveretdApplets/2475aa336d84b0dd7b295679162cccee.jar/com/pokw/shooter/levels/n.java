// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class n extends G
{
    private H[] a;
    
    public n() {
        final double n = 100 + G.c.nextInt(250);
        final double n2 = -31.0;
        final d u = com.pokw.shooter.planes.d.u(n, n2, 0.0, 2.5);
        final d u2 = com.pokw.shooter.planes.d.u(n + 40.0, n2, 0.0, 2.5);
        final d u3 = com.pokw.shooter.planes.d.u(n + 80.0, n2, 0.0, 2.5);
        final d u4 = com.pokw.shooter.planes.d.u(n + 120.0, n2, 0.0, 2.5);
        u.a(B.g());
        u2.a(B.g());
        u3.a(B.g());
        u4.a(B.g());
        this.a = new H[] { new H(0, u), new H(10, u2), new H(20, u3), new H(30, u4) };
    }
    
    public H[] a() {
        return this.a;
    }
}
