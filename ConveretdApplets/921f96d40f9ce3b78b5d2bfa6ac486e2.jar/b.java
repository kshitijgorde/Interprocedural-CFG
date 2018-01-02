// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private String[] p;
    private long[] p;
    private boolean p;
    private long p;
    
    final void p(final String s) {
        for (int i = 11; i >= 1; --i) {
            this.p[i] = this.p[i - 1];
            this.p[i] = this.p[i - 1];
        }
        this.p[0] = s;
        this.p[0] = System.currentTimeMillis();
        if (this.p[0] - this.p[11] < 11000L) {
            this.p = true;
            this.p = this.p[0];
        }
    }
    
    final boolean p() {
        if (this.p && System.currentTimeMillis() - this.p > 600000L) {
            this.p = false;
        }
        return this.p;
    }
    
    public b() {
        this.p = new String[12];
        this.p = new long[12];
        this.p = false;
        this.p = System.currentTimeMillis();
    }
}
