// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.planes.d;

public class w extends G
{
    private H[] a;
    
    public w() {
        final double n = 100 + G.c.nextInt(400);
        this.a = new H[] { new H(40, com.pokw.shooter.planes.d.j(n, -28.0, (n > 300.0) ? -0.5 : 0.5, 1.25)) };
    }
    
    public H[] a() {
        return this.a;
    }
}
