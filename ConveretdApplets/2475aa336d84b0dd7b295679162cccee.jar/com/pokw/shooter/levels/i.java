// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public class i extends G
{
    private H[] a;
    
    public i() {
        final double n = -73.0;
        final double n2 = 0.0;
        final double n3 = 1.25;
        final d s = com.pokw.shooter.planes.d.s(30.0, n, n2, n3);
        final d s2 = com.pokw.shooter.planes.d.s(250.0, n, n2, n3);
        final d s3 = com.pokw.shooter.planes.d.s(480.0, n, n2, n3);
        s.a(B.f());
        s2.a(B.h());
        s3.a(B.d());
        this.a = new H[] { new H(0, s2), new H(80, s), new H(80, s3) };
    }
    
    public H[] a() {
        return this.a;
    }
}
