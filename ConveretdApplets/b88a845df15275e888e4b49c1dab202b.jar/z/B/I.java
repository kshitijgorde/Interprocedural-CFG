// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

public class I implements A
{
    private boolean A;
    private Long B;
    private boolean C;
    
    public I() {
        this.A = false;
        this.B = null;
        this.C = true;
    }
    
    public void A(final boolean a) {
        this.A = a;
    }
    
    public boolean C() {
        return this.A;
    }
    
    public byte A(final int n) {
        return (byte)(n >> 8 & 0xFFFF);
    }
    
    public byte A(final short n) {
        return (byte)(n - 32768 >> 8);
    }
    
    public void A(final long n) {
        this.B = new Long(n);
    }
    
    public Long B() {
        return this.B;
    }
    
    public void B(final boolean c) {
        this.C = c;
    }
    
    public boolean A() {
        return this.C;
    }
}
