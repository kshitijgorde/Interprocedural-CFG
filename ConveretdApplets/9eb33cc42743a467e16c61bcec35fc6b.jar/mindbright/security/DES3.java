// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class DES3 extends ci
{
    public DES nk;
    public DES nj;
    public DES ni;
    
    public final synchronized void nb(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        this.nk.nb(array, n, array2, n2, n3);
        this.nj.na(array2, n2, array2, n2, n3);
        this.ni.nb(array2, n2, array2, n2, n3);
    }
    
    public final synchronized void na(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        this.ni.na(array, n, array2, n2, n3);
        this.nj.nb(array2, n2, array2, n2, n3);
        this.nk.na(array2, n2, array2, n2, n3);
    }
    
    public final void m9(final byte[] array) {
        final byte[] array2 = new byte[8];
        this.nk.m9(array);
        System.arraycopy(array, 8, array2, 0, 8);
        this.nj.m9(array2);
        System.arraycopy(array, 16, array2, 0, 8);
        this.ni.m9(array2);
    }
    
    public DES3() {
        this.nk = new DES();
        this.nj = new DES();
        this.ni = new DES();
    }
}
