// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.planes.d;

public class a extends G
{
    private H[] a;
    
    public a() {
        final double n = 100.0;
        final double n2 = -31.0;
        final double n3 = 0.5;
        final double n4 = 1.75;
        this.a = new H[] { new H(0, com.pokw.shooter.planes.d.e(n, n2, n3, n4)), new H(0, com.pokw.shooter.planes.d.e(n + 40.0, n2, n3, n4)), new H(0, com.pokw.shooter.planes.d.e(n + 80.0, n2, n3, n4)), new H(0, com.pokw.shooter.planes.d.e(n + 120.0, n2, n3, n4)) };
    }
    
    public H[] a() {
        return this.a;
    }
}
