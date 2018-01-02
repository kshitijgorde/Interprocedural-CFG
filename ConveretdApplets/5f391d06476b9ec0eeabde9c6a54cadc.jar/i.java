// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    public boolean p;
    
    public final synchronized void p(final int[] array, final int n, final int n2) {
        if (this.p) {
            return;
        }
        for (int n3 = n * n2, i = 0; i < n3; ++i) {
            final int n4 = array[i];
            if ((n4 & 0xFF000000) != 0x0) {
                array[i] = ((n4 & 0xFF) << 16 | (n4 & 0xFF00) >> 8 << 8 | 255 - ((n4 & 0xFF0000) >> 16) | (n4 & 0xFF000000));
            }
        }
        this.p = true;
    }
    
    public final synchronized void d(final int[] array, final int n, final int n2) {
        if (!this.p) {
            return;
        }
        for (int n3 = n * n2, i = 0; i < n3; ++i) {
            final int n4 = array[i];
            if ((n4 & 0xFF000000) != 0x0) {
                array[i] = (255 - (n4 & 0xFF) << 16 | (n4 & 0xFF00) >> 8 << 8 | (n4 & 0xFF0000) >> 16 | (n4 & 0xFF000000));
            }
        }
        this.p = false;
    }
    
    public final synchronized void p() {
    }
    
    public i() {
        this.p = false;
    }
}
