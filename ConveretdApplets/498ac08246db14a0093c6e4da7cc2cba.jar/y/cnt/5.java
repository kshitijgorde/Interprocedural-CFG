// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

public class 5
{
    int a;
    
    public 5(final int a) {
        this.a = a;
    }
    
    void g(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            this.a *= 83;
            final int n3 = i;
            array[n3] ^= (byte)this.a;
        }
    }
}
