// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

import com.pokw.shooter.planes.c;
import java.util.Vector;

class h extends B
{
    private boolean c;
    private int a;
    
    h() {
        this.c = false;
        this.a = 0;
    }
    
    public void a(final Vector vector, final c c) {
        if (this.c) {
            switch (this.a) {
                case 0: {
                    vector.add(m.a(c.d(), c.c(), -3.0, 3.0, 15));
                    break;
                }
                case 2: {
                    vector.add(m.a(c.d(), c.c(), -1.5, 3.5, 15));
                    break;
                }
                case 4: {
                    vector.add(m.a(c.d(), c.c(), 0.0, 4.0, 15));
                    break;
                }
                case 6: {
                    vector.add(m.a(c.d(), c.c(), 1.5, 3.5, 15));
                    break;
                }
                case 8: {
                    vector.add(m.a(c.d(), c.c(), 3.0, 3.0, 15));
                    break;
                }
            }
            ++this.a;
            if (this.a > 8) {
                this.c = false;
            }
        }
        else if (c.l() < 80.0 && c.l() > 40.0) {
            this.c = true;
        }
    }
}
