// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import com.pokw.shooter.weapon.B;
import com.pokw.shooter.planes.d;

public abstract class j extends G
{
    protected d b() {
        final double n = 100 + G.c.nextInt(400);
        final double n2 = 499.0;
        final double n3 = G.c.nextDouble() * 2.0 - 1.0;
        final double n4 = -4.5;
        d d = null;
        switch (G.c.nextInt(4)) {
            case 0: {
                d = com.pokw.shooter.planes.d.q(n, n2, n3, n4);
                break;
            }
            case 1: {
                d = com.pokw.shooter.planes.d.a(n, n2, n3, n4);
                break;
            }
            case 2: {
                d = com.pokw.shooter.planes.d.l(n, n2, n3, n4);
                break;
            }
            case 3: {
                d = com.pokw.shooter.planes.d.r(n, n2, n3, n4);
                break;
            }
            default: {
                d = com.pokw.shooter.planes.d.r(n, n2, n3, n4);
                break;
            }
        }
        d.a(B.e());
        return d;
    }
    
    protected d c() {
        final double n = 100 + G.c.nextInt(400);
        final double n2 = -31.0;
        final double n3 = G.c.nextDouble() * 2.0 - 1.0;
        final double n4 = 4.5;
        d d = null;
        switch (G.c.nextInt(4)) {
            case 0: {
                d = com.pokw.shooter.planes.d.i(n, n2, n3, n4);
                break;
            }
            case 1: {
                d = com.pokw.shooter.planes.d.o(n, n2, n3, n4);
                break;
            }
            case 2: {
                d = com.pokw.shooter.planes.d.u(n, n2, n3, n4);
                break;
            }
            case 3: {
                d = com.pokw.shooter.planes.d.f(n, n2, n3, n4);
                break;
            }
            default: {
                d = com.pokw.shooter.planes.d.f(n, n2, n3, n4);
                break;
            }
        }
        d.a(B.g());
        return d;
    }
}
