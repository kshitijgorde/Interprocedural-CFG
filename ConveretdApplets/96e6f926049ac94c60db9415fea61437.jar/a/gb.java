// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.bb;

public abstract class gb extends bb
{
    public kb d;
    public static int e;
    
    public abstract b a();
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
        super.h();
    }
    
    public gb() {
        this.d = null;
    }
}
