// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;
import java.util.Random;

public class I extends G
{
    private static Random c;
    private H[] a;
    
    public I() {
        final d i = com.pokw.shooter.planes.d.i(100.0, -30.0, 0.0, 3.75);
        final d j = com.pokw.shooter.planes.d.i(200.0, -30.0, 0.0, 3.75);
        final d k = com.pokw.shooter.planes.d.i(300.0, -30.0, 0.0, 3.75);
        final d l = com.pokw.shooter.planes.d.i(400.0, -30.0, 0.0, 3.75);
        final d m = com.pokw.shooter.planes.d.i(500.0, -30.0, 0.0, 3.75);
        i.a(B.g());
        j.a(B.g());
        k.a(B.g());
        l.a(B.g());
        m.a(B.g());
        this.a = new H[] { new H(0, i), new H(0, j), new H(0, k), new H(0, l), new H(0, m) };
    }
    
    public H[] a() {
        return this.a;
    }
    
    static {
        I.c = new Random();
    }
}
