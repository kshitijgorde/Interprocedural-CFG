// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ao
{
    public rp_ao a;
    public rp_ao b;
    String a;
    int a;
    public boolean a;
    public Object a;
    
    public rp_ao(final String a, final Object a2) {
        this.a = false;
        this.a = a;
        this.a = a2;
        final rp_ao rp_ao = null;
        this.a = rp_ao;
        this.b = rp_ao;
    }
    
    public final String toString() {
        return this.a;
    }
    
    public final boolean a() {
        return this.b != null;
    }
    
    public final boolean b() {
        return this.a() && !this.a && (this.a = true);
    }
    
    public final boolean a(final rp_ao rp_ao) {
        if (rp_ao == null) {
            return false;
        }
        if (this.a == rp_ao) {
            this.a = rp_ao.a;
            return true;
        }
        return (this.a != null && this.a.a(rp_ao)) || (this.b != null && this.b.a(rp_ao));
    }
}
