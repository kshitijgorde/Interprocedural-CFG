import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fW
{
    public boolean a;
    rp_dZ a;
    String a;
    
    public rp_fW(final boolean a, final rp_dZ a2) {
        this.a = null;
        this.a = null;
        this.a = a;
        this.a = a2;
    }
    
    public rp_fW(final String a) {
        this.a = null;
        this.a = null;
        this.a = a;
    }
    
    public final boolean a() {
        return this.a != null;
    }
    
    public final Color a() {
        if (this.a()) {
            return null;
        }
        return this.a.a;
    }
    
    public final Color b() {
        if (this.a()) {
            return null;
        }
        return this.a.b;
    }
}
