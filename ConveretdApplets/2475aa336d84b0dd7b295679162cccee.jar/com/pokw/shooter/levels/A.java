// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class A extends G
{
    private H[] a;
    
    public A() {
        final double n = 100 + G.c.nextInt(250);
        final double n2 = -31.0;
        final d o = com.pokw.shooter.planes.d.o(n, n2, 0.0, 3.0);
        final d o2 = com.pokw.shooter.planes.d.o(n + 40.0, n2, 0.0, 3.0);
        final d o3 = com.pokw.shooter.planes.d.o(n + 80.0, n2, 0.0, 3.0);
        final d o4 = com.pokw.shooter.planes.d.o(n + 120.0, n2, 0.0, 3.0);
        o.a(B.g());
        o2.a(B.g());
        o3.a(B.g());
        o4.a(B.g());
        this.a = new H[] { new H(0, o4), new H(10, o3), new H(20, o2), new H(30, o) };
    }
    
    public H[] a() {
        return this.a;
    }
}
