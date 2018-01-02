// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import java.util.Vector;
import java.util.Random;

public abstract class G
{
    private int b;
    private int d;
    protected static final Random c;
    
    public G() {
        this.b = 0;
        this.d = 0;
    }
    
    public boolean a(final Vector vector) {
        while (this.d < this.a().length && this.a()[this.d].a == this.b) {
            vector.add(this.a()[this.d].b);
            ++this.d;
        }
        ++this.b;
        return this.d < this.a().length;
    }
    
    public abstract H[] a();
    
    static {
        c = new Random();
    }
}
