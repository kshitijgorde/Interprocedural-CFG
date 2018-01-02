// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class l extends G
{
    private H[] a;
    
    public l() {
        final double n = 250 + G.c.nextInt(100);
        final double n2 = -39.0;
        final double n3 = -0.5;
        final double n4 = 2.0;
        final double n5 = -0.02;
        final double n6 = 0.0;
        final d k = com.pokw.shooter.planes.d.k(n, n2, n3, n4);
        final d i = com.pokw.shooter.planes.d.k(n + 60.0, n2, n3, n4);
        k.a(n5, n6);
        i.a(n5, n6);
        k.a(B.c());
        i.a(B.c());
        this.a = new H[] { new H(0, k), new H(0, i) };
    }
    
    public H[] a() {
        return this.a;
    }
}
