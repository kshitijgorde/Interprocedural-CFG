// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class F extends G
{
    private H[] a;
    
    public F() {
        final double n = -60.0;
        final double n2 = 0.0;
        final double n3 = 1.25;
        final d m = com.pokw.shooter.planes.d.m(30.0, n, n2, n3);
        final d i = com.pokw.shooter.planes.d.m(250.0, n, n2, n3);
        final d j = com.pokw.shooter.planes.d.m(480.0, n, n2, n3);
        m.a(B.f());
        i.a(B.h());
        j.a(B.d());
        this.a = new H[] { new H(0, i), new H(60, m), new H(60, j) };
    }
    
    public H[] a() {
        return this.a;
    }
}
