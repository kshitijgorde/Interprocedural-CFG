// 
// Decompiled by Procyon v0.5.30
// 

package a.b.p;

import a.b.f.d;
import a.b.f.h;
import a.b.f.i;

public abstract class c extends b
{
    private i a;
    
    protected c(final h h) {
        this.a = null;
        this.a(h);
    }
    
    public void a(final h h) {
        try {
            this.a = (i)h;
        }
        catch (ClassCastException ex) {
            this.a = new d(h);
        }
    }
    
    protected i a() {
        return this.a;
    }
}
