// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cU
{
    private String a;
    private String b;
    
    public cU(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final String b() {
        return this.b;
    }
    
    public final String toString() {
        return this.getClass().getName() + "[name=" + this.a + ",value=" + this.b + "]";
    }
}
