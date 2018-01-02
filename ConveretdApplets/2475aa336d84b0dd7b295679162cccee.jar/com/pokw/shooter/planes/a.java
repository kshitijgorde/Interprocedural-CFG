// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.planes;

import java.awt.Image;
import com.pokw.shooter.weapon.A;

class a extends d
{
    private A f;
    
    a(final double n, final double n2, final double n3, final double n4, final Image[] array, final A f) {
        super(n, n2, n3, n4, 60, array, 31, 29);
        this.f = f;
    }
    
    public void a(final b b) {
        b.a(this.f);
        this.l = 99;
        this.p = 0;
    }
}
