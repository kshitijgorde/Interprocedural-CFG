// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class v extends G
{
    private H[] a;
    
    public v() {
        final double n = -60.0;
        final double n2 = 0.0;
        final double n3 = 1.25;
        final d s = com.pokw.shooter.planes.d.s(30.0, n, n2, n3);
        final d s2 = com.pokw.shooter.planes.d.s(480.0, n, n2, n3);
        s.a(B.f());
        s2.a(B.d());
        this.a = new H[] { new H(0, s), new H(0, s2) };
    }
    
    public H[] a() {
        return this.a;
    }
}
