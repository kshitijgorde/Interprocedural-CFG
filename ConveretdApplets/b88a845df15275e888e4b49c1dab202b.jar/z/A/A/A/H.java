// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A;

public class H extends B
{
    public H(final A a) {
        super(a);
    }
    
    public String G(final int n) {
        String s;
        for (s = Integer.toHexString(n).toUpperCase(); s.length() < 4; s = "0" + s) {}
        return "Unknown tag 0x" + s;
    }
    
    public String A(final int n) {
        return this.A.K(n);
    }
}
