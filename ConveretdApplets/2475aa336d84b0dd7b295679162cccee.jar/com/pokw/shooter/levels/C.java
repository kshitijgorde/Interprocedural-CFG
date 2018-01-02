// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class C extends G
{
    private H[] a;
    
    public C() {
        final double n = 250 + G.c.nextInt(100);
        final double n2 = -45.0;
        final double n3 = -0.5;
        final double n4 = 2.0;
        final double n5 = -0.02;
        final double n6 = 0.0;
        final d p = com.pokw.shooter.planes.d.p(n, n2, n3, n4);
        final d p2 = com.pokw.shooter.planes.d.p(n + 75.0, n2, n3, n4);
        p.a(n5, n6);
        p2.a(n5, n6);
        p.a(B.c());
        p2.a(B.c());
        this.a = new H[] { new H(0, p), new H(0, p2) };
    }
    
    public H[] a() {
        return this.a;
    }
}
