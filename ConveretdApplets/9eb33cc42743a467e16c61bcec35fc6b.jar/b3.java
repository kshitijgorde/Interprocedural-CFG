// 
// Decompiled by Procyon v0.5.30
// 

public class b3 implements Runnable
{
    public b5 e9;
    public long le;
    
    public b3(final b5 e9, final long le) {
        this.le = le;
        this.e9 = e9;
    }
    
    public final void run() {
        this.e9.le(this.le);
        if (!this.e9.lk) {
            this.e9.lc(false);
        }
    }
}
