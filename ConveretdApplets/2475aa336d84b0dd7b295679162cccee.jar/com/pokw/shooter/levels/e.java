// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.planes.d;

public class e extends G
{
    private H[] a;
    
    public e() {
        final double n = 100 + G.c.nextInt(250);
        final double n2 = -31.0;
        this.a = new H[] { new H(0, com.pokw.shooter.planes.d.e(n + 80.0, n2, 0.0, 1.25)), new H(20, com.pokw.shooter.planes.d.e(n + 40.0, n2, 0.0, 1.25)), new H(20, com.pokw.shooter.planes.d.e(n + 120.0, n2, 0.0, 1.25)), new H(40, com.pokw.shooter.planes.d.e(n, n2, 0.0, 1.25)), new H(40, com.pokw.shooter.planes.d.e(n + 160.0, n2, 0.0, 1.25)) };
    }
    
    public H[] a() {
        return this.a;
    }
}
