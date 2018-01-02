// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

public class f extends a
{
    public static final int A = -1;
    int B;
    int C;
    int z;
    
    public f(final int n, final int z) {
        this.B = 0;
        this.C = 0;
        this.z = 0;
        this.B = n;
        this.C = n;
        this.z = z;
    }
    
    public void goto() {
        this.C = this.B;
    }
    
    public boolean char() {
        boolean b = false;
        if (this.C == -1) {
            b = true;
        }
        else if (this.C > 1) {
            b = true;
            --this.C;
        }
        return b;
    }
    
    public int else() {
        return this.z;
    }
}
