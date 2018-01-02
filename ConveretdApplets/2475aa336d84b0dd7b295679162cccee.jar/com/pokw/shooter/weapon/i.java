// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;
import java.util.Random;

class i extends B
{
    private Random d;
    private boolean c;
    private int a;
    
    i() {
        this.d = new Random();
        this.c = false;
        this.a = 0;
    }
    
    public void a(final Vector vector, final c c) {
        if (this.c) {
            if (this.a % 5 == 0) {
                vector.add(this.a(c));
            }
            ++this.a;
            if (this.a > 30) {
                this.c = false;
            }
        }
        else if (c.l() < 80.0 && c.l() > 40.0) {
            this.c = true;
        }
    }
    
    private m a(final c c) {
        return m.a(c.d(), c.c(), -this.d.nextInt(6), 5.0, 15);
    }
}
