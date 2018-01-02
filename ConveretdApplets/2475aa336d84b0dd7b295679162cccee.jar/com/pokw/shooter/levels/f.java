// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class f extends G
{
    private H[] a;
    
    public f() {
        final double n = G.c.nextInt(200) + 100;
        final double n2 = -60.0;
        final double n3 = 0.0;
        final double n4 = 1.75;
        double n5 = 0.01;
        if (n < 200.0) {
            n5 = -0.01;
        }
        final d m = com.pokw.shooter.planes.d.m(150 + G.c.nextInt(250), n2, n3, n4);
        m.a(B.h());
        m.a(n5, 0.0);
        this.a = new H[] { new H(0, m) };
    }
    
    public H[] a() {
        return this.a;
    }
}
